import java.util.*;
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

   public Node<Register> removeUnconstrainedNode() {
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

   public Node<Register> removeConstrainedNode() {
      for (Node<Register> node : graph.values()) {
         if (isConstrainedNode(node)) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            graph.remove(node.getData());
            return node;
         }
      }
      return null;
   }

   public Node<Register> removeRequiredNode() {
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

   public static boolean isConstrainedNode(Node<Register> node) {
      return !isRequiredRegister(node.getData()) && node.degree() >= Register.COLORING_REGISTERS.size();
   }

   public static boolean isRequiredRegister(Register register) {
      return Register.REQUIRED_REGISTERS.contains(register);
   }

   



}