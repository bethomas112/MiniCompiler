
import java.util.*;

public abstract class IInstruction {  
   public static final int SPILL_THRESHOLD = 6;
   /* Arithmetic*/
   public static class ADD extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "add " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", " + dest + "\n");
         builder.append("\taddq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class ADDI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "addi " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + source + ", " + dest + "\n");
         builder.append("\taddq $" + immediate + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }
 
   /* Returns the address of the local */
   public static class ADDILOCAL extends IInstruction {
      public Register dest;
      public String localName;
      public String getText() {
         return "addi rarp, " + localName + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = cfg.localsOrdered.indexOf(localName);
         if (offset != -1) {
            offset = 8 * offset + 8;
            // Because the locals are below the %rbp
            offset *= -1;
            builder.append("\tmovq %rbp, " + dest + "\n");
            builder.append("\taddq $" + offset + ", " + dest + "\n");
         }
         else {
            throw new RuntimeException("ADDILOCAL: Could not find local: " + localName);
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
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

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);
         if (offset != -1) {
            offset = offset * 8;
            builder.append("\tmovq " + source + ", " + dest + "\n");
            builder.append("\taddq $" + offset + ", " + dest + "\n");
         }
         else {
            throw new RuntimeException("ADDISTRUCT: Unable to find field");
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class DIV extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "div " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", %rax\n");
         builder.append("\tmovq " + sourceA + ", %rdx\n");
         builder.append("\tsarq $63, %rdx\n");
         builder.append("\tidivq " + sourceB + "\n");
         builder.append("\tmovq %rax, " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, Register.RAX, Register.RDX));
      }

      public Set<Register> getDest() {
         return new HashSet<Register>(Arrays.asList(dest, Register.RAX, Register.RDX));
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MULT extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "mult " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", " + dest + "\n");
         builder.append("\timulq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class SUB extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "sub " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", " + dest + "\n");
         builder.append("\tsubq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class SUBI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "subi " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + source + ", " + dest + "\n");
         builder.append("\tsubq $" + immediate + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   /* Boolean */
   public static class AND extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "and " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", " + dest + "\n");
         builder.append("\tandq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class OR extends IInstruction {
      public Register sourceA, sourceB, dest;
      public String getText() {
         return "or " + sourceA + ", " + sourceB + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + sourceA + ", " + dest + "\n");
         builder.append("\torq " + sourceB + ", " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.asList(sourceA, sourceB, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class XORI extends IInstruction {
      public Register source, dest;
      public int immediate;
      public String getText() {
         return "xori " + source + ", " + immediate + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + source + ", " + dest + "\n");
         builder.append("\txorq $" + immediate + ", " + dest + "\n");

         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   /* Loads */
   public static class LOADI extends IInstruction {
      public int immediate;
      public Register dest;
      public String getText() {
         return "loadi " + immediate + ", " + dest;
      }

      public String getX86(CFG cfg) {
         return "\tmovq $" + immediate + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return Collections.emptySet();
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class LOADAISPILL extends IInstruction {
      public Register dest;
      public int offset;
      public String getText() {
         return "loadai rarp, " + offset + ", " + dest;
      }

      public Set<Register> getSource() {
         return Collections.singleton(Register.RSP);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + offset + "(%rsp), " + dest + "\n");
         return builder.toString();
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
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

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);

         if (offset != -1) {
            offset = offset * 8;
            builder.append("\tmovq " + offset + "(" + source + "), " + dest +"\n");
         }
         else {
            System.out.println(structType.fieldsOrdered);
            throw new RuntimeException("LOADAIFIELD: Unable to find field: " + fieldName + " in struct " + structType);
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   /* Loads the local from the stack into the destination register */
   public static class LOADAILOCAL extends IInstruction {
      public String localName;
      public Register dest;
      public String getText() {
         return "loadai rarp, " + localName + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = cfg.localsOrdered.indexOf(localName);

         if (offset != -1) {
            offset = 8 * offset + 8;
            // Because the locals are below the %rbp
            offset *= -1;
            builder.append("\tmovq " + offset + "(%rbp), " + dest + "\n");
         }
         else {
            throw new RuntimeException("LOADAILOCAL: Unable to find local: " + localName);
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.emptySet();
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class LOADINARGUMENT extends IInstruction {
      public String variable;
      public int argIdx;
      public Register dest;

      public List<Register> argRegisters = Arrays.asList(Register.RDI, Register.RSI, Register.RDX, Register.RCX, Register.R8, Register.R9);

      public String getText() {
         return "loadinargument " + variable + ", " + argIdx + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         if (argIdx >= 6) {
            // Calculate the args address on the stack 
            int offset = 16 + (6 - argIdx) * 8;
            builder.append("\tmovq " + offset + "(%rsp), " + dest + "\n");
         }
         else {
            builder.append("\tmovq " + argRegisters.get(argIdx) + ", " + dest + "\n");
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         if (argIdx >= 6) {
            return Collections.emptySet();
         }
         else {
            return Collections.singleton(argRegisters.get(argIdx));
         }
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class LOADGLOBAL extends IInstruction {
      public String globalName;
      public Register dest;
      public String getText() {
         return "loadglobal " + globalName + ", " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + globalName + "(%rip), " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.emptySet();
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }
      
      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class LOADRET extends IInstruction {
      public Register dest;
      public String getText() {
         return "loadret " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq %rax, " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(Register.RAX);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class COMPUTEGLOBALADDRESS extends IInstruction {
      public Register dest;
      public String globalName;
      public String getText() {
         return "computeglobaladdress " + globalName + ", " + dest;
      }

      public String getX86(CFG cfg) {
         return "\tmovq $" + globalName + ", " + dest + "\n"; 
      }

      public Set<Register> getSource() {
         return Collections.emptySet();
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class STOREAISPILL extends IInstruction {
      public Register source;
      public int offset;
      public String getText() {
         return "storeai " + source + ", " + "rarp" + ", " + offset;
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return new HashSet<Register>(Arrays.<Register>asList(Register.RSP));
      }

      public String getX86(CFG cfg) {
         return "\tmovq " + source + ", " + offset + "(%rsp)\n";
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
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

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         int offset = structType.fieldsOrdered.indexOf(fieldName);

         if (offset != -1) {
            offset *= 8;
            builder.append("\tmovq " + source + ", " + offset + "(" + dest + ")\n");
         }
         else {
            throw new RuntimeException("STOREAIFIELD: unable to find field: " + fieldName);
         }
         return builder.toString();
      } 

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class STOREOUTARGUMENT extends IInstruction {
      public Register source;
      public int argIdx;
      public List<Register> argRegisters = Arrays.asList(Register.RDI, Register.RSI, Register.RDX, Register.RCX, Register.R8, Register.R9);

      public String getText() {
         return "storeoutargument " + source + ", " + argIdx;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         if (argIdx >= 6) {
            int offset = argIdx - 6;
            offset *= 8;

            builder.append("\tmovq " + source + ", " + offset + "(%rsp)\n");
         }
         else {
            builder.append("\tmovq " + source + ", " + argRegisters.get(argIdx) + "\n");
         }
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         if (argIdx >= 6) {
            return Collections.emptySet();
         }
         else {
            return Collections.singleton(argRegisters.get(argIdx));
         }
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class STOREGLOBAL extends IInstruction {
      public Register source;
      public String globalName;
      public String getText() {
         return "storeglobal " + source + ", " + globalName;
      }

      public String getX86(CFG cfg) {
         return "\tmovq " + source + ", " + globalName + "(%rip)\n";
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.emptySet();
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class STORERET extends IInstruction {
      public Register source;
      public String getText() {
         return "storeret " + source;
      }

      public String getX86(CFG cfg) {
         return "\tmovq " + source + ", %rax\n";
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(Register.RAX);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   /* Moves */
   public static class MOV extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "mov " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tmovq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }

      public boolean isCopy() {
         return true;
      }
   }

   public static class MOVEQ extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "moveq " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmoveq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MOVGE extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "movge " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmovgeq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MOVGT extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "movgt " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmovgq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MOVLE extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "movle " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmovleq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MOVLT extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "movlt " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmovlq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class MOVNE extends IInstruction {
      public Register source, dest;
      public String getText() {
         return "movne " + source + ", "+ dest;
      }

      public String getX86(CFG cfg) {
         return "\tcmovneq " + source + ", " + dest + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(source, dest));
      }

      public Set<Register> getDest() {
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   /* Comparison and Branching */
   public static class COMP extends IInstruction {
      public Register sourceA, sourceB;
      
      public String getText() {
         return "comp " + sourceA + ", " + sourceB;
      }

      public String getX86(CFG cfg) {
         return "\tcmpq " + sourceB + ", " + sourceA + "\n";
      }

      public Set<Register> getSource() {
         return new HashSet<Register>(Arrays.<Register>asList(sourceA, sourceB));
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         sourceA = coloring.get(sourceA);
         sourceB = coloring.get(sourceB);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class COMPI extends IInstruction {
      public Register source;
      public int immediate;

      public String getText() {
         return "compi " + source + ", " + immediate;
      }

      public String getX86(CFG cfg) {
         return "\tcmpq $" + immediate + ", " + source + "\n";
      }

      public Set<Register> getSource() {
         return Collections.singleton(source);
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBREQ extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbreq " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tje " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBRGE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrge " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjge " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBRGT extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrgt " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjg " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBRLE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrle " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjle " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBRLT extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrlt " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjl " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class CBRNE extends IInstruction {
      public String labelA, labelB;

      public String getText() {
         return "cbrne " + labelA + ", " + labelB;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjne " + labelA + "\n");
         builder.append("\tjmp " + labelB + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   public static class JUMPI extends IInstruction {
      public String label;

      public String getText() {
         return "jumpi " + label;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tjmp " + label + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         return Collections.<Register>emptySet();
      }

      public Set<Register> getDest() {
         return Collections.<Register>emptySet();
      }

      public boolean removable() {
         return false;
      }
   }

   /* I/O */
   public static class PRINT extends IInstruction {
      public static final String PRINT_STRING_LABEL = ".PRINTSTRING";
      public Register source;

      public String getText() {
         return "print " + source;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tmovq $" + PRINT_STRING_LABEL + ", %rdi\n");
         builder.append("\tmovq " + source + ", %rsi\n");
         builder.append("\tmovq $0, %rax\n");
         builder.append("\tcall printf\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(source);
         return result;
      }

      public Set<Register> getDest() {
         return Register.CALLER_SAVED;
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class PRINTLN extends IInstruction {
      public static final String PRINTLN_STRING_LABEL = ".PRINTLNSTRING";
      public Register source;

      public String getText() {
         return "println " + source;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tmovq $" + PRINTLN_STRING_LABEL + ", %rdi\n");
         builder.append("\tmovq " + source + ", %rsi\n");
         builder.append("\tmovq $0, %rax\n");
         builder.append("\tcall printf\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(source);
         return result;
      }

      public Set<Register> getDest() {
         return Register.CALLER_SAVED;
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         source = coloring.get(source);
      }

      public boolean removable() {
         return false;
      }
   }

   public static class READ extends IInstruction {
      public static final String READ_STRING_LABEL = ".READSTRING";
      public Register dest;

      public String getText() {
         return "read " + dest;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();

         builder.append("\tmovq $" + READ_STRING_LABEL + ", %rdi\n");
         builder.append("\tmovq " + dest + ", %rsi\n");
         builder.append("\tmovq $0, %rax\n");
         builder.append("\tcall scanf\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(dest);
         return Collections.singleton(dest);
      }

      public Set<Register> getDest() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(dest);
         return Collections.singleton(dest);
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }

      public boolean removable() {
         return false;
      }
   }

   /* Invocation */

   public static class CALL extends IInstruction {
      public String label;

      public String getText() {
         return "call " + label;
      }

      public String getX86(CFG cfg) {
         return "\tcall " + label + "\n";
      }

      public Set<Register> getSource() {
         return Register.CALLER_SAVED;
      }

      public Set<Register> getDest() {
         return Register.CALLER_SAVED;
      }

      public boolean removable() {
         return false;
      }
   }

   public static class RET extends IInstruction {
      public String getText() {
         return "ret";
      }

      public String getX86(CFG cfg) {
         List<Register> usedCalleeRegs = new ArrayList<Register>(cfg.getUsedCalleeRegisters());
         int spillCount = cfg.params.size() > SPILL_THRESHOLD ? cfg.params.size() - SPILL_THRESHOLD : 0;
         spillCount += cfg.spills.size();
         int localCount = cfg.localsOrdered.size();
         int frameSize = (spillCount + localCount) * 8;
         StringBuilder builder = new StringBuilder();

         builder.append("\taddq $" + frameSize + ", %rsp\n");
         Collections.reverse(usedCalleeRegs);
         for (Register register : usedCalleeRegs) {
            builder.append("\tpopq " + register + "\n");
         }
         builder.append("\tmovq %rbp, %rsp\n");        
         builder.append("\tpopq %rbp\n");

         builder.append("\tret\n");
         return builder.toString();
      }

      public boolean removable() {
         return false;
      }

      public Set<Register> getSource() {
         return Collections.singleton(Register.RBP);
      }

      public Set<Register> getDest() {
         return new HashSet<Register>(Arrays.asList(Register.RBP, Register.RSP));
      }
   }

   /* Allocation */

   public static class NEW extends IInstruction {
      public MiniType.StructType struct;
      public Register dest;

      public String getText() {
         return "new " + struct.name + ", " + struct.fieldsOrdered + ", " + dest;
      }

      public String getX86(CFG cfg) {
         int byteSize = struct.fieldsOrdered.size() * 8;
         StringBuilder builder = new StringBuilder();

         builder.append("\tmovl $" + byteSize + ", %edi\n");
         builder.append("\tcall malloc\n");
         builder.append("\tmovq %rax, " + dest + "\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(Register.RAX);
         return result;
      }

      public Set<Register> getDest() {
         HashSet<Register> registers = new HashSet<>(Arrays.asList(dest));
         registers.addAll(Register.CALLER_SAVED);
         return registers;
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {
         if (!sourceOnly)
         dest = coloring.get(dest);
      }
   }

   public static class DEL extends IInstruction {
      public Register source;

      public String getText() {
         return "del " + source;
      }

      public String getX86(CFG cfg) {
         StringBuilder builder = new StringBuilder();
         builder.append("\tmovq " + source + ", %rdi\n");
         builder.append("\tcall free\n");
         return builder.toString();
      }

      public Set<Register> getSource() {
         HashSet<Register> result = new HashSet<>(Register.CALLER_SAVED);
         result.add(source);
         return result;
      }

      public Set<Register> getDest() {
         return Register.CALLER_SAVED;
      }

      public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {         
         source = coloring.get(source);
      }
   }

   public abstract String getText();
   public abstract String getX86(CFG cfg);
   public abstract Set<Register> getSource();
   public abstract Set<Register> getDest();

   public void applyColoring(HashMap<Register, Register> coloring) {
      applyColoring(coloring, false);
   }

   public void applyColoring(HashMap<Register, Register> coloring, boolean sourceOnly) {

   }

   public boolean removable() {
      return true;
   }

   public boolean isCopy() {
      return false;
   }

   public boolean hasSource() {
      return !getSource().isEmpty();
   }

   public boolean hasDest() {
      return !getDest().isEmpty();
   }
}
