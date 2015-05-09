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

   /* Return value Register */
   public static final Register RAX = new Register("%rax");
}