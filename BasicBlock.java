import java.util.*;
import java.util.Map.*;
public class BasicBlock {   
   private List<BasicBlock> prev;
   private List<BasicBlock> next;      
   public String label;
   private List<IInstruction> instructions;
   private LazyValue<LiveSets> liveSets;
   private HashSet<Register> liveOut;
   private HashSet<Copy> cpin;

   public class CopySets {
      public HashSet<Copy> copy;
      public HashSet<Copy> kill;

      @Override
      public boolean equals(Object other) {
         if (other == null) {
            return false;
         }
         if (other.getClass() != this.getClass()) {
            return false;
         }
         else {
            CopySets otherSet = (CopySets)other;
            boolean equal = true;
            equal &= otherSet.copy.equals(copy);
            equal &= otherSet.kill.equals(kill);
            return equal;
         }
      }
   }

   private class Copy {
      Register src, dest;
      IInstruction instruction;
      BasicBlock block;

      public Copy(Register src, Register dest, IInstruction instruction, BasicBlock block) {
         this.src = src;
         this.dest = dest;
         this.instruction = instruction;
         this.block = block;
      }

      @Override
      public boolean equals(Object other) {
         if (other == null) {
            return false;
         }
         if (!(other.getClass() == this.getClass())) {
            return false;
         }
         else {
            return this.instruction == ((Copy)other).instruction;
         }
      }

      public String toString() {
         return "Copy instruction: " + instruction.getText();
      }

      @Override
      public int hashCode() {
         return instruction.hashCode();
      }
   }

   private class LiveSets {
      public HashSet<Register> gen = new HashSet<>();
      public HashSet<Register> kill = new HashSet<>();      
      public LiveSets() {
         for (IInstruction instruction : instructions) {
            for (Register src : instruction.getSource()) {
               if (!kill.contains(src)) {
                  gen.add(src);
               }               
            }
            kill.addAll(instruction.getDest());
         }         
      }
   }

   public BasicBlock() {
      prev = new ArrayList<>();
      next = new ArrayList<>();
      instructions = new ArrayList<>();
      liveSets = new LazyValue<LiveSets>() {
         public LiveSets createValue() {
            return new LiveSets();
         }
      };
      liveOut = new HashSet<>();
      cpin = new HashSet<>();
   }

   public void removeUselessInstructions() {
      int instrCount = -1;
      while (instrCount != instructions.size()) {
         instrCount = instructions.size();
         HashMap<IInstruction, HashSet<Register>> instructionToLiveNow = new HashMap<>();
         HashSet<Register> liveNow = new HashSet<>(liveOut);
         List<IInstruction> reversed = new ArrayList<>(instructions);
         Collections.reverse(reversed);
         for (IInstruction instr : reversed) {
            instructionToLiveNow.put(instr, new HashSet<>(liveNow));
            for (Register dest : instr.getDest()) {
               liveNow.remove(dest);            
            }
            liveNow.addAll(instr.getSource());
         }

         for (Entry<IInstruction, HashSet<Register>> entry : instructionToLiveNow.entrySet()) {
            Set<Register> dest = entry.getKey().getDest();
            HashSet<Register> live = entry.getValue();
            if (entry.getKey().removable() && Collections.disjoint(dest, live)) {
               instructions.remove(entry.getKey());
            }
         }
      }
   }

   public InterferenceGraph getInterference(InterferenceGraph interference) {
      HashSet<Register> liveNow = new HashSet<>(liveOut);
      List<IInstruction> reversed = new ArrayList<>(instructions);
      Collections.reverse(reversed);
      for (IInstruction instr : reversed) {
         for (Register dest : instr.getDest()) {
            if (!interference.containsRegister(dest)) {
               interference.addNode(new Node<Register>(dest));
            }
            for (Register register : liveNow) {
               if (!register.equals(dest)) {
                  if (!interference.containsRegister(register)) {
                     interference.addNode(new Node<Register>(register));
                  }
                  interference.getNode(dest).connect(interference.getNode(register));
               }
            }
            liveNow.remove(dest);
            liveNow.addAll(instr.getSource());
         }
      }
      return interference;
   }

   public boolean genLiveOut() {
      HashSet<Register> newLiveOut = new HashSet<>();
      for (BasicBlock succ : next) {
         newLiveOut.addAll(succ.getGenSet());
         HashSet<Register> diff = succ.getLiveOut();
         diff.removeAll(succ.getKillSet());
         newLiveOut.addAll(diff);
      }
      boolean changed = !newLiveOut.equals(liveOut);
      liveOut = newLiveOut;
      return changed;
   }

   public HashSet<Register> getLiveOut() {
      return new HashSet<Register>(liveOut);
   }

   public List<BasicBlock> getNext() {
      return next;
   }

   public List<BasicBlock> getPrev() {
      return prev;
   }

   public void addNext(BasicBlock nextBlock) {
      next.add(nextBlock);
      nextBlock.prev.add(this);
   }

   public boolean isNextEmpty() {
      return next.isEmpty();
   }

   public List<IInstruction> getILOC() { 
      return instructions; 
   }

   public void addInstruction(IInstruction instruction) {
      instructions.add(instruction);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(label + ":\n");
      for (IInstruction instruction : instructions) {
         sb.append("\t" + instruction.getText() + "\n");
      }
      return sb.toString();
   }

   public String getX86(CFG cfg) {
      StringBuilder sb = new StringBuilder();
      sb.append(label + ":\n");
      if (this == cfg.entryBlock) {
         int spillCount = cfg.params.size() > IInstruction.SPILL_THRESHOLD ? cfg.params.size() - IInstruction.SPILL_THRESHOLD : 0;
         spillCount += cfg.spills.size();
         int localCount = cfg.localsOrdered.size();
         int frameSize = (spillCount + localCount) * 8;
         sb.append("\tpushq %rbp\n");
         sb.append("\tmovq %rsp, %rbp\n");
         sb.append("\tsubq $" + frameSize + ", %rsp\n");      
      }
      
      for (IInstruction instruction : instructions) {
         sb.append(instruction.getX86(cfg));
      }
      return sb.toString();
   }

   public HashSet<Register> getGenSet() {
      return new HashSet<Register>(liveSets.get().gen);
   }

   public HashSet<Register> getKillSet() {
      return new HashSet<Register>(liveSets.get().kill);
   }

   public void resetSets() {
      liveSets = new LazyValue<LiveSets>() {
         public LiveSets createValue() {
            return new LiveSets();
         }
      };
      liveOut = new HashSet<>();
   }

   public CopySets getCopySets() {
      CopySets copySet = new CopySets();
      HashSet<Copy> copies = new HashSet<>();
      HashSet<Copy> killedCopies = new HashSet<>();
      HashSet<Register> destinations = new HashSet<>();
      List<IInstruction> reversed = new ArrayList<>(instructions);

      Collections.reverse(reversed);
      for (IInstruction instruction : reversed) {         
         if (instruction.isCopy()) {
            if (!(instruction.getSource().size() == 1 && instruction.getDest().size() == 1)) {
               throw new RuntimeException("Copy instruction has more than 1 source");
            }
            Register src = instruction.getSource().iterator().next();
            Register dest = instruction.getDest().iterator().next();
            Copy copy = new Copy(src, dest, instruction, this);
            if (!(destinations.contains(src) || destinations.contains(dest))) {               
               copies.add(copy);
            }
            else {
               killedCopies.add(copy);
            }            
         }
         destinations.addAll(instruction.getDest());
      }

      for (Copy copy : cpin) {
         if (destinations.contains(copy.src) || destinations.contains(copy.dest)) {
            killedCopies.add(copy);
         }
      }
      copySet.copy = copies;
      copySet.kill = killedCopies;
      return copySet;
   }

   public boolean genCPIN() {
      HashSet<Copy> newCPIN = null;
      for (BasicBlock block : prev) {
         HashSet<Copy> predCPIN = new HashSet<Copy>(block.getCPIN());
         CopySets copySets = block.getCopySets();
         HashSet<Copy> copySet = new HashSet<Copy>(copySets.copy);
         HashSet<Copy> killSet = new HashSet<Copy>(copySets.kill);
         predCPIN.removeAll(killSet);
         copySet.addAll(predCPIN);
         if (newCPIN == null) {
            newCPIN = copySet;
         }
         else {
            newCPIN.retainAll(copySet);
         }
      }
      if (newCPIN == null) {
         newCPIN = new HashSet<Copy>();
      }
      boolean changed = !newCPIN.equals(cpin);
      cpin = newCPIN;

      return changed;
   }

   public HashSet<Copy> getCPIN() {
      return cpin;
   }

   public void substituteCopies() {
      HashSet<Copy> copyNow = new HashSet<Copy>(cpin);
      for (IInstruction instruction : instructions) {
         for (Register srcReg : instruction.getSource()) {
            for (Copy copy : copyNow) {
               if (copy.dest.equals(srcReg)) {                  
                  HashMap<Register, Register> replacement = new HashMap<>();
                  replacement.put(srcReg, copy.src);
                  for (Register otherSrc : instruction.getSource()) {
                     if (otherSrc != srcReg) {
                        replacement.put(otherSrc, otherSrc);
                     }
                  }
                  instruction.applyColoring(replacement, true);
               }
            }
         }
         
         if (instruction.isCopy()) {
            Register src = instruction.getSource().iterator().next();
            Register dest = instruction.getDest().iterator().next();
            Copy copy = new Copy(src, dest, instruction, this);            
            copyNow.add(copy);
         }
         Iterator<Copy> it = copyNow.iterator();
         while (it.hasNext()) {
            Copy copy = it.next();            
            if (!copy.instruction.equals(instruction)) {
               if (instruction.getDest().contains(copy.src) || instruction.getDest().contains(copy.dest)) {
                  it.remove();
               }
            }
         }
      }
   }
   
}