import java.io.File;
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

   }
}