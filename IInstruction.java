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
         return "mov " + source + ", "+ dest;
      }
   }

   /* Comparison and Branching */
   public static class COMP extends IInstruction {
      public Register sourceA, sourceB;
      
      public String getText() {
         return "comp " + sourceA + ", " + sourceB;
      }
   }

   public static class COMPI extends IInstruction {
      public Register source;
      public int immediate;

      public String getText() {
         return "compi " + source + ", " + immediate;
      }
   }

   public static class CBREQ extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbreq " + labelA + ", " + labelB;
      }
   }

   public static class CBRGE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrge " + labelA + ", " + labelB;
      }
   }

   public static class CBRGT extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrgt " + labelA + ", " + labelB;
      }
   }

   public static class CBRLE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrle " + labelA + ", " + labelB;
      }
   }

   public static class CBRLT extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrlt " + labelA + ", " + labelB;
      }
   }

   public static class CBRNE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrne " + labelA + ", " + labelB;
      }
   }

   public static class JUMPI extends IInstruction {
      public String label;

      public String getText() {
         return "cbrge " + label;
      }
   }

   /* I/O */
   public static class PRINT extends IInstruction {
      public Register source;

      public String getText() {
         return "print " + source;
      }
   }

   public static class PRINTLN extends IInstruction {
      public Register source;

      public String getText() {
         return "println " + source;
      }
   }

   public static class READ extends IInstruction {
      public Register dest;

      public String getText() {
         return "read " + dest;
      }
   }

   /* Invocation */

   public static class CALL extends IInstruction {
      public String label;

      public String getText() {
         return "call " + label;
      }
   }

   public static class RET extends IInstruction {
      public String getText() {
         return "ret";
      }
   }

   /* Allocation */

   public static class NEW extends IInstruction {
      public StructType struct;
      public Register dest;

      public String getText() {
         return "new " + struct.name + ", " + struct + ", " + dest;
      }
   }

   public static class DEL extends IInstruction {
      public Register source;

      public String getText() {
         return "del " + source;
      }
   }

   public abstract String getText();
}