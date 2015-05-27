import java.util.*;
public class Register {
   public static int NEXT_REGISTER = 0;
   public static void resetRegisters() {
      NEXT_REGISTER = 0;
   }

   public static Register newRegister() {
      return new Register();
   }
   
   public String name;
   private Register() {
      name = "r" + NEXT_REGISTER++;
   }

   private Register(String name) {
      this.name = name;
   }

   public String toString() {
      return name;
   }

   @Override
   public boolean equals(Object other) {
      if (other == null) {
         return false;
      }
      if (!(other.getClass() == this.getClass())) {
         return false;
      }
      else {
         return name.equals(((Register)other).name);
      }
   }  

   @Override
   public int hashCode() {
      return name.hashCode();
   }

   /* Argument Registers */
   public static final Register RDI = new Register("%rdi");
   public static final Register RSI = new Register("%rsi");
   public static final Register RDX = new Register("%rdx");
   public static final Register RCX = new Register("%rcx");
   public static final Register R8 = new Register("%r8");
   public static final Register R9 = new Register("%r9");

   /* General Purpose */
   public static final Register R10 = new Register("%r10");
   public static final Register R11 = new Register("%r11");
   public static final Register R12 = new Register("%r12");
   public static final Register R13 = new Register("%r13");
   public static final Register R14 = new Register("%r14");
   public static final Register R15 = new Register("%r15");
   public static final Register RBX = new Register("%rbx");

   /* Return value Register */
   public static final Register RAX = new Register("%rax");
   
   /* Stack Registers */
   public static final Register RBP = new Register("%rbp");
   public static final Register RSP = new Register("%rsp");

   public static final Set<Register> REQUIRED_REGISTERS = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RDI, RSI, RDX, RCX, R8, R9, RSP, RBP, RAX)));
   public static final Set<Register> COLORING_REGISTERS = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RDI, RSI, RDX, RCX, R8, R9, R10, R11, R12, R13, R14, R15, RBX)));
   public static final Set<Register> CALLER_SAVED = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RAX, RCX, RDX, RSI, RDI, R8, R9, R10, R11)));
   public static final Set<Register> CALLEE_SAVED = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RBX, RSP, RBP, R12, R13, R14, R15)));
}