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

   }
}