import java.util.*;

public class RegisterAllocator {
   private ILOCGenerator.ILOCResult result;
   public RegisterAllocator(ILOCGenerator.ILOCResult result) {
      this.result = result;
   }

   public ILOCGenerator.ILOCResult allocate() {
      for (CFG cfg : result.cfgs) {
         cfg.calculateLiveOut();
         allocateCFG(cfg);
      }
      return result;
   }

   public void allocateCFG(CFG cfg) {
      List<Node<Register>> stack = new LinkedList<>();
      Node<Register> node;
      while ((node = removeUnconstrainedNode()) != null) {
         stack.add(node);
      }
      while ((node = removeConstrainedNode()) != null) {
         stack.add(node);
      }
      while ((node = removeRequiredNode()) != null) {
         stack.add(node);
      }
   }

   public void debugPrint(CFG cfg) {
      RegisterAllocaterTester tst = new RegisterAllocaterTester();
      cfg.calculateLiveOut();
      System.out.println("CFG: " + cfg.entryBlock.label);
      for (BasicBlock block : cfg.bfsBlocks()) {
         System.out.println("\tBlock: " + block.label);
         System.out.println("\tGen:");
         System.out.println("\t\t" + block.getGenSet());
         System.out.println("\tKill:");
         System.out.println("\t\t" + block.getKillSet());
         System.out.println("\tLiveOut:");
         System.out.println("\t\t" + block.getLiveOut());    
      }
      System.out.println("\tInterference:");
      for (Node<Register> node : cfg.getInterference().getNodes()) {
         System.out.println(node);
      }
   }
}