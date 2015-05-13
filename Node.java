import java.util.*;
public class Node<T> {
   private T t;
   private HashSet<Node<T>> adj;
   private Register color;

   public Node(T t) {
      this.t = t;
      this.adj = new HashSet<>();
   }

   public T getData() {
      return t;
   }

   public HashSet<Node<T>> getAdj() {
      return adj;
   }

   public void setColor(Register color) {
      this.color = color;
   }

   public Register getColor() {
      if (color == null) {
         throw new RuntimeException("Node is not colored");
      }
      return color;
   }

   public void connect(Node<T> other) {
      adj.add(other);
      other.adj.add(this);
   }

   public void disconnectFrom(Node<T> other) {
      adj.remove(other);
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