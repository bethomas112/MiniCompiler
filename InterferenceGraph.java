import java.util.*;
import java.util.Map.Entry;
public class InterferenceGraph {
   private HashMap<Register, Node<Register>> graph = new HashMap<>();

   public void addNode(Node<Register> node) {
      graph.put(node.getData(), node);
   }

   public Node<Register> getNode(Register register) {
      return graph.get(register);
   }

   public boolean containsRegister(Register register) {
      return graph.containsKey(register);
   }

   public Set<Node<Register>> getNodes() {
      return new HashSet<Node<Register>>(graph.values());
   }

   public Node<Register> removeNode() {
      Node<Register> node;
      if ((node = removeUnconstrainedNode()) != null) {
         return node;
      }
      if ((node = removePossiblyConstrainedNode()) != null) {
         return node;
      }
      if ((node = removeRequiredNode()) != null) {
         return node;
      }
      return null;
   }

   private Node<Register> removeUnconstrainedNode() {
      for (Node<Register> node : graph.values()) {
         if (isUnconstrainedNode(node)) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            graph.remove(node.getData());
            return node;
         }
      }
      return null;
   }

   private Node<Register> removePossiblyConstrainedNode() {
      for (Node<Register> node : graph.values()) {
         if (!isUnconstrainedNode(node) && !isRequiredRegister(node.getData())) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            graph.remove(node.getData());
            return node;
         }
      }
      return null;
   }

   private Node<Register> removeRequiredNode() {
      for (Node<Register> node : graph.values()) {
         if (isRequiredRegister(node.getData())) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            graph.remove(node.getData());
            return node;
         }
      }
      return null;
   }

   public static boolean isUnconstrainedNode(Node<Register> node) {
      return !isRequiredRegister(node.getData()) && node.degree() < Register.COLORING_REGISTERS.size();
   }

   public static HashSet<Register> getColorings(Node<Register> node) {
      HashSet<Register> availableColors = new HashSet<>(Register.COLORING_REGISTERS);
      for (Node<Register> adj : node.getAdj()) {
         availableColors.remove(adj.getColor());
      }
      return availableColors;
   }


   public static boolean isRequiredRegister(Register register) {
      return Register.REQUIRED_REGISTERS.contains(register);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Entry<Register, Node<Register>> entry : graph.entrySet()) {
         sb.append(entry.getValue());
      }
      return sb.toString();
   }
}