import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class IInstruction {  
   public static class MOV extends IInstruction {
      public Register dest, source;
      
      public String getText() {
         return "mov " + dest.toString() + ", "+ source.toString();
      }

      public List<Register> getSourceRegisters() {
         return Arrays.asList(source);
      }
   
      public List<Register> getDestRegisters() {
         return Arrays.asList(dest);
      }
   }
   
   public IInstruction() {}
   
   public abstract String getText();
   
   public List<Register> getSourceRegisters() {
      return new ArrayList<>();
   }
   
   public List<Register> getDestRegisters() {
      return new ArrayList<>();
   }      
}