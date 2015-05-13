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
      Stack<Node<Register>> stack = new Stack<>();
      Node<Register> node;
      InterferenceGraph interference = cfg.getInterference();

      while ((node = interference.removeUnconstrainedNode()) != null) {
         stack.push(node);
      }
      while ((node = interference.removeConstrainedNode()) != null) {
         stack.push(node);
      }
      while ((node = interference.removeRequiredNode()) != null) {
         stack.push(node);
      }
      

      HashMap<Register, Register> colorings = new HashMap<>();
      while (!stack.isEmpty()) {
         Node<Register> node = stack.pop();
         if (InterferenceGraph.isRequiredRegister(node.getData())) {
            node.setColor(node.getData());
            colorings.put(node.getData(), node.getData());
         }
         else if (InterferenceGraph.isConstrainedNode(node)) {

         }
         else if (InterferenceGraph.isUnconstrainedNode(node)) {
            HashSet<Register> availableColors = new HashSet<>(Register.COLORING_REGISTERS);
            
            for (Node<Register> adj : node.getAdj()) {
               availableColors.remove(adj.getColor());
            }

            Register color = availableColors.iterator().next();
            node.setColor(color);
            colorings.put(node.getData(), color)
         }
         else {
            throw new RuntimeException("Uncategorized register" + node);
         }
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