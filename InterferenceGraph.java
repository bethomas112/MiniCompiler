import java.util.*;
public class InterferenceGraph {
   private HashMap<Register, Node<Register>> graph = new HashMap<>();
   private HashSet<Register> visited;

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
      for (Node node : graph.values()) {
         if (isUnconstrainedNode(node)) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            return node;
         }
      }
      return null;
   }

   public Node<Register> removeConstrainedNode() {
      for (Node node : graph.values()) {
         if (isConstrainedNode(node)) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            return node;
         }
      }
      return null;
   }

   public Node<Register> removeRequiredNode() {
      for (Node node : graph.values()) {
         if (isRequiredRegister(node.getData())) {
            for (Node<Register> adj : node.getAdj()) {
               adj.disconnectFrom(node);
            }
            return node;
         }
      }
      return null;
   }

   public boolean isUnconstrainedNode(Node<Register> node) {
      return !isRequiredRegister(node.getData()) && node.degree < Register.COLORING_REGISTERS.size();
   }

   public boolean isConstrainedNode(Node<Register> node) {
      return !isRequiredRegister(node.getData()) && node.degree >= Register.COLORING_REGISTERS.size();
   }

   public boolean isRequiredRegister(Register register) {
      return Register.REQUIRED_REGISTERS.contains(register);
   }

   



}