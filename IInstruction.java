import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class IInstruction {  

   /* Arithmetic*/
   public static class ADD extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "add " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   public static class ADDI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "addi " + source + ", " + immediate + ", " + dest;
      }
   }

   public static class DIV extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "div " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   public static class MULT extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "mult " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   public static class SUB extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "sub " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   public static class SUBI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "subi " + source + ", " + immediate + ", " + dest;
      }
   }

   /* Boolean */
   public static class AND extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "and " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   public static class OR extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "or " + sourceA + ", " + sourceB + ", " + dest;
      }
   }

   /* Loads */
   public static class LOADI extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "loadi " + immediate + ", " + dest;
      }
   }

   public static class LOADINARGUMENT extends IInstruction {
      public String variable;
      public int argIdx;
      public Register dest;
      public String getText() {
         return "loadinargument " + variable + ", " + argIdx + ", " + dest;
      }
   }

   public static class LOADGLOBAL extends IInstruction {
      public String globalName;
      public Register dest;
      public String getText() {
         return "loadglobal " + globalName + ", " + dest;
      }
   }

   public static class LOADRET extends IInstruction {
      public Register dest;
      public String getText() {
         return "loadret " + dest;
      }
   }

   /* Stores */
   public static class STOREAIFIELD {
      public Register source, dest;
      public String fieldName;
      public String getText() {
         return "storeai " + source + ", " + dest + ", " + fieldName;
      }
   }

   public static class STOREOUTARGUMENT extends IInstruction {
      public Register source;
      public int argIdx;
      public String getText() {
         return "storeoutargument " + source + ", " + argIdx;
      }
   }

   public static class STOREGLOBAL extends IInstruction {
      public Register source;
      public String globalName;
      public String getText() {
         return "storeglobal " + source + ", " + globalName;
      }
   }

   public static class STORERET extends IInstruction {
      public Register source;
      public String getText() {
         return "storeret " + source;
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