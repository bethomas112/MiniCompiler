import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class MiniType {
   public static class StructType extends MiniType {
      public List<String> fieldsOrdered = new ArrayList<>();
      public HashMap<String, MiniType> fields = new HashMap<>();

      public StructType() {

      }
      
      public String toString() {
         StringBuilder builder = new StringBuilder();

         builder.append(name + "\n");
         for (String id : fieldsOrdered) {
            builder.append("Field: " + id + " -> " + fields.get(id).name + "\n");
         }
         return builder.toString();
      }
   }
   public static final MiniType INT = new MiniType("INT");
   public static final MiniType BOOL = new MiniType("BOOL");
   public static final MiniType VOID = new MiniType("VOID");
   public static final MiniType NULL = new MiniType("NULL");
   public String name;
   public MiniType() {

   }

   public MiniType(String name) {
      this.name = name;
   }

   public String toString() {
      return name;
   }
}

