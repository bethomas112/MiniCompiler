import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class X86Mapper {
   private ILOCGenerator.ILOCResult ilocResult;
   private HashMap<String, MiniType> globalTypes;
   public X86Mapper(ILOCGenerator.ILOCResult ilocResult, HashMap<String, MiniType> globalTypes) {
      this.ilocResult = ilocResult;
      this.globalTypes = globalTypes;
   }

   public void process(File toFile) {
      try {
         FileWriter writer = new FileWriter(toFile);
         for (String globalName : globalTypes.keySet()) {
            writer.write("\t.comm " + globalName + ",8,8\n");
         }
         writer.write("\t.section\t.rodata\n");
         writer.write(IInstruction.PRINT.PRINT_STRING_LABEL + ":\n");
         writer.write("\t.string \"%ld\"\n");
         writer.write(IInstruction.PRINTLN.PRINTLN_STRING_LABEL + ":\n");
         writer.write("\t.string \"%ld\\n\"\n");
         writer.write(IInstruction.READ.READ_STRING_LABEL + ":\n");
         writer.write("\t.string \"%ld\"\n");
         for (CFG cfg : ilocResult.cfgs) {
            writer.write("\t.text\n");
            writer.write(".global " + cfg.entryBlock.label + "\n");
            writer.write("\t.type\t" + cfg.entryBlock.label + ", @function\n");           
            for (BasicBlock block : cfg.bfsBlocks()) {
               writer.write(block.getX86(cfg));
            }
            writer.write("\t.size\t" + cfg.entryBlock.label + ", .-" + cfg.entryBlock.label + "\n");            
         }
         writer.close();
      } 
      catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}