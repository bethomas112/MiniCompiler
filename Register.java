public class Register {
   public static int NEXT_REGISTER = 0;
   
   public static void resetRegisters() {
      NEXT_REGISTER = 0;
   }

   public static Register newRegister() {
      return new Register();
   }
   
   public final int n;
   private Register() {
      n = NEXT_REGISTER++;
   }

   public String toString() {
      return "r" + n;
   }      
}