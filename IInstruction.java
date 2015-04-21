import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class IInstruction {  

   /* Arithmetic*/
   public static class ADD extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "add " + sourceA.toString() + ", " + sourceB.toString() + ", " + dest.toString();
      }
   }   

   public static class MOV extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "mov " + dest.toString() + ", "+ source.toString();
      }
   }

   public abstract String getText();
}