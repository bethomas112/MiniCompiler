import java.util.*;
public class BasicBlock {   
   public List<BasicBlock> prev;
   public List<BasicBlock> next;      
   public String label;
   private List<IInstruction> instructions;
   private LazyValue<LiveSets> liveSets;

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
      return liveSets.get().gen;
   }

   public HashSet<Register> getKillSet() {
      return liveSets.get().kill;
   }
}