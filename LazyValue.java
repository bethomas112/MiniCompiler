public abstract class LazyValue<T> {
   private T t;

   public abstract T createValue();
   
   public T get() {
      if (t == null) {
         t = createValue();
      }
      return t;
   }
}