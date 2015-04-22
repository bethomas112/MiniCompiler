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

   public static class ADDILOCAL extends IInstruction {
      public Register dest;
      public String localName;
      public String getText() {
         return "addi rarp, " + localName + ", " + dest;
      }
   }

   public static class ADDISTRUCT extends IInstruction {
      public Register source, dest;
      public String fieldName;
      public String getText() {
         return "addi " + source + ", " + fieldName + ", " + dest;
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

   public static class XORI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "xori " + source + ", " + immediate + ", " + dest;
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

   public static class LOADAIFIELD extends IInstruction {
      public Register source, dest;
      public String fieldName;
      public String getText() {
         return "loadai " + source + ", " + fieldName + ", " + dest;
      }
   }

   public static class LOADAILOCAL extends IInstruction {
      public String localName;
      public Register dest;
      public String getText() {
         return "loadai rarp, " + localName + ", " + dest;
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

   public static class COMPUTEGLOBALADDRESS extends IInstruction {
      public Register dest;
      public String globalName;
      public String getText() {
         return "computeglobaladdress " + globalName + ", " + dest;
      }
   }

   /* Stores */
   public static class STOREAIFIELD extends IInstruction {
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

   /* Moves */
   public static class MOV extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "mov " + source + ", "+ dest;
      }
   }

   public static class MOVEQ extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "moveq " + immediate + ", "+ dest;
      }
   }

   public static class MOVGE extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "movge " + immediate + ", "+ dest;
      }
   }

   public static class MOVGT extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "movgt " + immediate + ", "+ dest;
      }
   }

   public static class MOVLE extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "movle " + immediate + ", "+ dest;
      }
   }

   public static class MOVLT extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "movlt " + immediate + ", "+ dest;
      }
   }

   public static class MOVNE extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "movne " + immediate + ", "+ dest;
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
      public MiniType.StructType struct;
      public Register dest;

      public String getText() {
         return "new " + struct.name + ", " + struct.fieldsOrdered + ", " + dest;
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