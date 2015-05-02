
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class IInstruction {  
   private static final int SPILL_THRESHOLD = 6;
   /* Arithmetic*/
   public static class ADD extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "add " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", " + dest + "\n");
         builder.append("addq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

   }

   public static class ADDI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "addi " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq ", + source + ", " + dest + "\n");
         builder.append("addq $" + immediate + ", " + dest + "\n");
         return builder.toString();
      }
   }
 
   /* Returns the address of the local */
   public static class ADDILOCAL extends IInstruction {
      public Register dest;
      public String localName;
      public String getText() {
         return "addi rarp, " + localName + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = cfg.localsOrdered.indexOf(localName);
         if (offset == -1) {
            offset = 8 * offset + 8;
            // Because the locals are below the %rbp
            offset *= -1;
            builder.append("movq %rbp, " + dest + "\n");
            builder.append("addq $" + offset + ", " + dest);
         }
         else {
            throw new RuntimeException("ADDILOCAL: Could not find local");
         }
         return builder.toString();
      }
   }

   /* Address of the field of the struct */
   public static class ADDISTRUCT extends IInstruction {
      public Register source, dest;
      public String fieldName;
      public MiniType.StructType structType;
      public String getText() {
         return "addi " + source + ", " + fieldName + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);
         if (offset != -1) {
            offset = offset * 8;
            builder.append("movq " + source + ", " + dest + "\n");
            builder.append("addq $" + offset + ", " + dest + "\n");
         }
         else {
            throw new RuntimeException("ADDISTRUCT: Unable to find field");
         }
         return builder.toString();
      }
   }

   public static class DIV extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "div " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", %rax\n");
         builder.append("movq " + sourceA + ", %rdx\n");
         builder.append("sarq $63, %rdx\n");
         builder.append("idivq " + sourceB + "\n");
         builder.append("movq %rax, " + dest);
         return builder.toString();
      }
   }

   public static class MULT extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "mult " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", " + dest + "\n");
         builder.append("imulq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }
   }

   public static class SUB extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "sub " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", " + dest + "\n");
         builder.append("subq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }
   }

   public static class SUBI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "subi " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + source ", " + dest + "\n");
         builder.append("subq $" + immediate + ", " + dest + "\n");
         return builder.toString();
      }
   }

   /* Boolean */
   public static class AND extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "and " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", " + dest + "\n");
         builder.append("andq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }
   }

   public static class OR extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "or " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + sourceA + ", " + dest + "\n");
         builder.append("orq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }
   }

   public static class XORI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "xori " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + source + ", " + dest + "\n");
         builder.append("xorq $" + immediate + ", " + dest + "\n");
      }
   }

   /* Loads */
   public static class LOADI extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "loadi " + immediate + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         return "movq $" + immediate + ", " + dest + "\n";
      }
   }

   /* Loads the field at the address in source into dest*/
   public static class LOADAIFIELD extends IInstruction {
      public Register source, dest;
      public String fieldName;
      public MiniType.StructType structType;
      public String getText() {
         return "loadai " + source + ", " + fieldName + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);

         if (offset != -1) {
            offset = offset * 8;
            builder.append("movq " + offset + "(" + source + "), " + dest +"\n");
         }
         else {
            throw new RuntimeException("LOADAIFIELD: Unable to find field: " + fieldName);
         }
         return builder.toString();
      }
   }

   /* Loads the local from the stack into the destination register */
   public static class LOADAILOCAL extends IInstruction {
      public String localName;
      public Register dest;
      public String getText() {
         return "loadai rarp, " + localName + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = cfg.localsOrdered.indexOf(localName);

         if (offset != -1) {
            offset = 8 * offset + 8;
            // Because the locals are below the %rbp
            offset *= -1;
            builder.append("movq " + offset + "(%rbp), " + dest + "\n");
         }
         else {
            throw new RuntimeException("LOADAILOCAL: Unable to find local: " + localName);
         }
         return builder.toString();
      }
   }

   public static class LOADINARGUMENT extends IInstruction {
      public String variable;
      public int argIdx;
      public Register dest;

      public List<String> argRegisters = Arrays.asList("%rdi", "%rsi", "%rdx", "%rcx", "%r8", "%r9");

      public String getText() {
         return "loadinargument " + variable + ", " + argIdx + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();

         if (argIdx >= 6) {
            // Calculate the args address on the stack 
            int offset = 16 + (6 - argIdx) * 8;
            builder.append("movq " + offset + "(%rsp), " + dest + "\n");
         }
         else {
            builder.append("movq " + argRegisters.get(argIdx) + ", " + dest + "\n");
         }

      }
   }

   public static class LOADGLOBAL extends IInstruction {
      public String globalName;
      public Register dest;
      public String getText() {
         return "loadglobal " + globalName + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq " + globalName + "(%rip), " + dest + "\n");
         return builder.toString();
      }
      
   }

   public static class LOADRET extends IInstruction {
      public Register dest;
      public String getText() {
         return "loadret " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq %rax, " + dest + "\n");
         return builder.toString();
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
      public MiniType.StructType structType;
      public String getText() {
         return "storeai " + source + ", " + dest + ", " + fieldName;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);

         if (offset != -1) {
            offset *= 8;
            builder.append("movq " + source + ", " + offset + "(" + dest + ")\n");
         }
         else {
            throw new Runtimeexception("STOREAIFIELD: unable to find field: " + fieldName);
         }
         return builder.toString();
      } 
   }

   public static class STOREOUTARGUMENT extends IInstruction {
      public Register source;
      public int argIdx;
      public List<String> argRegisters = Arrays.asList("%rdi", "%rsi", "%rdx", "%rcx", "%r8", "%r9");

      public String getText() {
         return "storeoutargument " + source + ", " + argIdx;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();

         if (argIdx >= 6) {
            int offset = argIdx - 6;
            offset *= 8;

            builder.append("movq " + source + ", " + offset + "(%rsp)\n");
         }
         else {
            builder.append("movq " + source + ", " + argRegisters.get(argIdx) + "\n");
         }
         return builder.toString();
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
         return "jumpi " + label;
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

      public String getX86(ILOCGenerator.CFG cfg) {
         int spillCount = cfg.params.size() > SPILL_THRESHOLD ? cfg.params.size() - SPILL_THRESHOLD : 0;
         int localCount = cfg.localsOrdered.size();
         int frameSize = (spillCount + localCount) * 8;
         StringBuilder builder = new StringBuilder();
         return "";
      }
   }

   /* Allocation */

   public static class NEW extends IInstruction {
      public MiniType.StructType struct;
      public Register dest;

      public String getText() {
         return "new " + struct.name + ", " + struct.fieldsOrdered + ", " + dest;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         int byteSize = struct.fieldsOrdered.size() * 8;
         StringBuilder builder = new StringBuilder();

         builder.append("movl $" + byteSize + ", %edi\n");
         builder.append("call malloc\n");
         builder.append("movq %rax, " + "%" + dest + "\n");
         return builder.toString();
      }
   }

   public static class DEL extends IInstruction {
      public Register source;

      public String getText() {
         return "del " + source;
      }

      public String getX86(ILOCGenerator.CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("movq %" + source + ", %rdi\n");
         builder.append("call free\n");
         return builder.toString();
      }
   }

   public abstract String getText();
   //public abstract String getX86(ILOCGenerator.CFG cfg);
}