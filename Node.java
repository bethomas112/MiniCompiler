import java.util.*;
public class Node<T> {
   private T t;
   private HashSet<Node<T>> adj;

   public Node(T t) {
      this.t = t;
      this.adj = new HashSet<>();
   }

   public T getData() {
      return t;
   }

   public List<Node<T>> getAdj() {
      return new ArrayList<Node<T>>(adj);
   }

   public void connect(Node<T> other) {
      adj.add(other);
      other.adj.add(this);
   }

   public void disconnect(Node<T> other) {
      
   }

   public int degree() {
      return adj.size();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(((Object)t).toString() + "\n");
      for (Node<T> node : adj) {
         sb.append("\t -> " + ((Object)node.t).toString() + "\n");
      }      
      return sb.toString();
   }
}