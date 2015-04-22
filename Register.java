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
}