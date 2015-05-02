import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class X86Mapper {
   private static class X86Function {
      public static X86Function fromCFG(ILOCGenerator.CFG cfg) {
         X86Function x86Function = new X86Function();
         return x86Function;
      }
   }

   private ILOCGenerator.ILOCResult ilocResult;
   public X86Mapper(ILOCGenerator.ILOCResult ilocResult) {
      this.ilocResult = ilocResult;
   }

   public void process(File toFile) {
      try {
         FileWriter writer = new FileWriter(toFile);

         for (ILOCGenerator.CFG cfg : ilocResult.cfgs) {
            for (ILOCGenerator.BasicBlock block : cfg.bfsBlocks()) {
               writer.write(block.getX86(cfg));
            }            
         }
         writer.close();
      } 
      catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}