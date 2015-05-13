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
   
   public static final Set<Register> REQUIRED_REGISTERS = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RAX, RDX, RDI, RSI, RDX, RCX, R8, R9)));
   public static final Set<Register> COLORING_REGISTERS = Collections.unmodifiableSet(new HashSet<Register>(Arrays.asList(RDI, RSI, RDX, RCX, R8, R9, R10, R11, R12, R13, R14, R15, RBX)));
}