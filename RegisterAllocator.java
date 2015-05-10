public class RegisterAllocator {
   private ILOCGenerator.ILOCResult result;
   public RegisterAllocator(ILOCGenerator.ILOCResult result) {
      this.result = result;
   }

   public ILOCGenerator.ILOCResult allocate() {
      for (CFG cfg : result.cfgs) {
         System.out.println("CFG: " + cfg.entryBlock.label);
         for (BasicBlock block : cfg.bfsBlocks()) {
            System.out.println("\tBlock: " + block.label);
            System.out.println("\tGen:");
            System.out.println("\t\t" + block.getGenSet());
            System.out.println("\tKill:");
            System.out.println("\t\t" + block.getKillSet());
         }
      }
      return result;
   }
}