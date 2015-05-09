import java.util.*;
public class BasicBlock {
      public List<BasicBlock> prev;
      public List<BasicBlock> next;      
      public String label;

      private List<IInstruction> instructions;
      public BasicBlock() {
         prev = new ArrayList<>();
         next = new ArrayList<>();
         instructions = new ArrayList<>();
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
   }