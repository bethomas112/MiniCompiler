import java.util.*;
public class BasicBlock {   
   private List<BasicBlock> prev;
   private List<BasicBlock> next;      
   public String label;
   private List<IInstruction> instructions;
   private LazyValue<LiveSets> liveSets;
   private HashSet<Register> liveOut;
   private LazyValue<Map<Register, Node<Register>>> interference;

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
      interference = new LazyValue<Map<Register, Node<Register>>>() {
         public Map<Register, Node<Register>> createValue() {
            return generateInterference();
         }
      };
   }

   private HashMap<Register, Node<Register>> generateInterference() {
      HashMap<Register, Node<Register>> interference = new HashMap<>();
      HashSet<Register> liveNow = new HashSet<>(liveOut);
      List<IInstruction> reversed = new ArrayList<>(instructions);
      Collections.reverse(reversed);
      for (IInstruction instr : reversed) {
         for (Register dest : instr.getDest()) {
            if (!interference.containsKey(dest)) {
               interference.put(dest, new Node<Register>(dest));
            }
            for (Register register : liveNow) {
               if (!register.equals(dest)) {
                  if (!interference.containsKey(register)) {
                     interference.put(register, new Node<Register>(register));
                  }
                  interference.get(dest).connect(interference.get(register));
               }
            }
            liveNow.remove(dest);
            liveNow.addAll(instr.getSource());
         }
      }
      return interference;
   }

   public Map<Register, Node<Register>> getInterference() {
      return interference.get();
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
}