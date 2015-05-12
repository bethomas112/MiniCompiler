public class RegisterAllocator {
   private ILOCGenerator.ILOCResult result;
   public RegisterAllocator(ILOCGenerator.ILOCResult result) {
      this.result = result;
   }

   public ILOCGenerator.ILOCResult allocate() {
      RegisterAllocaterTester tst = new RegisterAllocaterTester();

      for (CFG cfg : tst.getCFG()) {
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
            System.out.println("\tInterference:");
            for (Node<Register> node : block.getInterference().values()) {
               System.out.println(node);
            }            
         }
      }
      return result;
   }
}