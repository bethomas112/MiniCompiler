 import java.util.*;

 public class CFG {
   public BasicBlock entryBlock;
   public BasicBlock exitBlock;
   public HashMap<String, Register> locals;
   public HashMap<String, MiniType> localTypes;
   public List<String> localsOrdered;
   public HashMap<String, Integer> params;
   public HashMap<String, MiniType.StructType> structTypes;
   public CFG(HashMap<String, MiniType.StructType> structTypes) {
      this.locals = new HashMap<>();
      this.localsOrdered = new ArrayList<>();
      this.params = new HashMap<>();
      this.structTypes = structTypes;
   }

   public List<BasicBlock> bfsBlocks() {
      HashSet<BasicBlock> visited = new HashSet<>();
      LinkedList<BasicBlock> queue = new LinkedList<>();
      List<BasicBlock> result = new ArrayList<>();

      queue.add(entryBlock);
      while(!queue.isEmpty()) {
         BasicBlock block = queue.poll();
         if (!visited.contains(block)) {
            result.add(block);
            visited.add(block);
            for (BasicBlock nextBlock : block.getNext()) {               
               queue.add(nextBlock);     
            }
         }
      }
      return result;
   }

   public InterferenceGraph getInterference() {
      InterferenceGraph interference = new InterferenceGraph();
      for (BasicBlock block : bfsBlocks()) {
         block.getInterference(interference);
      }
      return interference;
   }

   public void calculateLiveOut() {
      List<BasicBlock> blocks = bfsBlocks();
      boolean changed = true;
      while (changed) {
         changed = false;
         for (BasicBlock block : blocks) {
            changed |= block.genLiveOut();
         }
      }
   }
}