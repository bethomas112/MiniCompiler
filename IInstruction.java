import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class IInstruction {  


   public static class MOV extends UnaryIInstruction {
      public String getText() {
         return "mov " + dest.toString() + ", "+ source.toString();
      }
   }

   public static abstract class UnaryIInstruction extends IInstruction {
      public Register source, dest;
      public abstract String getText();
   
      public List<Register> getSourceRegisters() {
         return Arrays.asList(source);
      }
      
      public List<Register> getDestRegisters() {
         return Arrays.asList(dest);
      }  
   }

   public static abstract class BinaryIInstruction extends IInstruction {
      public Register sourceA, sourceB, dest;
      public abstract String getText();
   
      public List<Register> getSourceRegisters() {
         return Arrays.asList(sourceA, sourceB);
      }
      
      public List<Register> getDestRegisters() {
         return new ArrayList<>(dest);
      }  
   }

   public static abstract class SpecialIInstruction extends IInstruction {
      public Register source;
      public abstract String getText();
   
      public List<Register> getSourceRegisters() {
         return new ArrayList<>(source);
      }
      
      public List<Register> getDestRegisters() {
         return new ArrayList<>();
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