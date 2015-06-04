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
   public LazyValue<List<Register>> usedCalleeRegisters;
   public CFG(HashMap<String, MiniType.StructType> structTypes) {
      this.locals = new HashMap<>();
      this.localsOrdered = new ArrayList<>();
      this.params = new HashMap<>();
      this.structTypes = structTypes;
      this.spills = new HashMap<>();
      usedCalleeRegisters = new LazyValue<List<Register>>() {
         public List<Register> createValue() {
            return calculateUsedCalleeRegisters();
         }
      };
   }

   public List<Register> getUsedCalleeRegisters() {
      return usedCalleeRegisters.get();
   }

   private List<Register> calculateUsedCalleeRegisters() {
      HashSet<Register> result = new HashSet<Register>();
      for (BasicBlock block : bfsBlocks()) {
         for (IInstruction instr : block.getILOC()) {
            for (Register register : instr.getDest()) {
               if (Register.CALLEE_SAVED.contains(register)) {
                  result.add(register);
               }
            }   
         }    
      }
      return new ArrayList<Register>(result);
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

   public List<BasicBlock> dfsBlocks() {
      return dfsRec(entryBlock, new HashSet<BasicBlock>(), new ArrayList<BasicBlock>());
   }

   private List<BasicBlock> dfsRec(BasicBlock current, HashSet<BasicBlock> visited, List<BasicBlock> result) {
      visited.add(current);
      result.add(current);
      for (BasicBlock block : current.getNext()) {
         if (!visited.contains(block)) {            
            dfsRec(block, visited, result);
         }
      }
      return result;
   }

   public InterferenceGraph getInterference() {
      InterferenceGraph interference = new InterferenceGraph(this);
      for (BasicBlock block : bfsBlocks()) {
         block.getInterference(interference);
      }
      //System.out.println("INTERFERENCE: " + interference);
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

   public void removeUselessInstructions() {
      resetLiveAnalysis();
      calculateLiveOut();
      List<BasicBlock> blocks = bfsBlocks();
      for (BasicBlock block : blocks) {
         block.removeUselessInstructions();
      }
   }

   public void runCopyPropagation() {
      List<BasicBlock> blocks = bfsBlocks();

      boolean changed = true;
      while (changed) {
         changed = false;
         for (BasicBlock block : blocks) {
            changed |= block.genCPIN();
         }
      }

      for (BasicBlock block : blocks) {
         block.substituteCopies();
      }
   }

   public void debugPrint() {
      resetLiveAnalysis();
      calculateLiveOut();
      System.out.println("CFG: " + entryBlock.label);
      for (BasicBlock block : bfsBlocks()) {
         System.out.println("\tBlock: " + block.label);
         System.out.println("\tGen:");
         System.out.println("\t\t" + block.getGenSet());
         System.out.println("\tKill:");
         System.out.println("\t\t" + block.getKillSet());
         System.out.println("\tLiveOut:");
         System.out.println("\t\t" + block.getLiveOut());    
      }
      //System.out.println("\tInterference:");
      //for (Node<Register> node : getInterference().getNodes()) {
         //System.out.println(node);
      //}
   }
}