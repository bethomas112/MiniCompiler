public class Register {
   public static int NEXT_REGISTER = 0;
   public static void resetRegisters() {
      NEXT_REGISTER = 0;
   }
   
   public final int n;
   public Register() {
      n = NEXT_REGISTER++;
   }

   public String toString() {
      return "r" + n;
   }      
}