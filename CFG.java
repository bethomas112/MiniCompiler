 import java.util.*;

 public class CFG {
   public BasicBlock entryBlock;
   public BasicBlock exitBlock;
   public HashMap<String, Register> locals;
   public HashMap<String, MiniType> localTypes;
   public List<String> localsOrdered;
   public HashMap<String, Integer> params;
   public HashMap<String, MiniType.StructType> structTypes;
   public HashMap<Register, Integer> spills;
   public CFG(HashMap<String, MiniType.StructType> structTypes) {
      this.locals = new HashMap<>();
      this.localsOrdered = new ArrayList<>();
      this.params = new HashMap<>();
      this.structTypes = structTypes;
      this.spills = new HashMap<>();
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
      InterferenceGraph interference = new InterferenceGraph(this);
      for (BasicBlock block : bfsBlocks()) {
         block.getInterference(interference);
      }
       System.out.println("INTERFERENCE: " + interference);
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

   public boolean isSpilled(Register register) {
      return spills.containsKey(register);
   }

   public Integer allocateSpill(Register register) {
      if (!spills.containsKey(register)) {
         spills.put(register, spills.size());
      }      
      return spills.get(register) * 8;
   }

   public void resetLiveAnalysis() {
      List<BasicBlock> blocks = bfsBlocks();
      for (BasicBlock block : blocks) {
         block.resetSets();
      }
   }
}