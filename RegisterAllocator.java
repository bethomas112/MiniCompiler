import java.util.*;
import java.io.*;
public class RegisterAllocator {
   private ILOCGenerator.ILOCResult result;
   public RegisterAllocator(ILOCGenerator.ILOCResult result) {
      this.result = result;
   }

   public ILOCGenerator.ILOCResult allocate() {
      for (CFG cfg : result.cfgs) {
         cfg.resetLiveAnalysis();
         cfg.calculateLiveOut();
         while (!allocateCFG(cfg)) {
            writeILOC(cfg);         
            cfg.resetLiveAnalysis();
            cfg.calculateLiveOut();
         }
      }
      return result;
   }

   public boolean allocateCFG(CFG cfg) {
      Stack<Node<Register>> stack = new Stack<>();
      Node<Register> node;
      InterferenceGraph interference = cfg.getInterference();
      //System.out.println("Interference: \n" + interference);
      while ((node = interference.removeNode()) != null) {
         stack.push(node);
      }

      HashMap<Register, Register> colorings = new HashMap<>();
      while (!stack.isEmpty()) {
         node = stack.pop();
         if (InterferenceGraph.isRequiredRegister(node.getData())) {
            node.setColor(node.getData());
            colorings.put(node.getData(), node.getData());
         }
         else if (InterferenceGraph.isUnconstrainedNode(node)) {
            Register color = InterferenceGraph.getColorings(node).iterator().next();
            node.setColor(color);
            colorings.put(node.getData(), color);
         }
         else {
            HashSet<Register> colors = InterferenceGraph.getColorings(node);
            if (colors.isEmpty()) {
               spillRegister(cfg, node.getData());
               return false;
            }
            else {
               Register color = colors.iterator().next();
               node.setColor(color);
               colorings.put(node.getData(), color);
            }
         }
      }
      // BasicBlock l11 = cfg.entryBlock.getNext().get(0);
      //System.out.println(cfg.entryBlock.label);
      //System.out.println("Interference: \n" + cfg.getInterference());
      // System.out.println(l11.label + " LiveOut: " + l11.getLiveOut());
      //System.out.println(colorings);
      for (BasicBlock block : cfg.bfsBlocks()) {
         for (IInstruction instruction : block.getILOC()) {
            instruction.applyColoring(colorings);
         }
      }

      return true;
   }

   private void spillRegister(CFG cfg, Register register) {
      //System.out.println("Spilling register: " + register);      
      for (BasicBlock block : cfg.bfsBlocks()) {
         List<IInstruction> instructions = block.getILOC();
         for (int idx = 0; idx < instructions.size(); idx++) {
            Integer rspOffset = cfg.allocateSpill(register);
            IInstruction instruction = instructions.get(idx);

            if (instruction.getSource().contains(register)) {
               IInstruction.LOADAISPILL loadaispill = new IInstruction.LOADAISPILL();
               loadaispill.dest = register;
               loadaispill.offset = rspOffset;
               instructions.add(idx, loadaispill);
               idx++;
            }

            if (instruction.getDest().contains(register)) {
               IInstruction.STOREAISPILL storeaispill = new IInstruction.STOREAISPILL();
               storeaispill.source = register;
               storeaispill.offset = rspOffset;
               instructions.add(idx + 1, storeaispill);
               idx++;
            }
         }
      }
   }

   public void debugPrint(CFG cfg) {
      RegisterAllocaterTester tst = new RegisterAllocaterTester();
      cfg.calculateLiveOut();
      System.out.println("CFG: " + cfg.entryBlock.label);
      for (BasicBlock block : cfg.bfsBlocks()) {
         System.out.println("\tBlock: " + block.label);
         System.out.println("\tGen:");
         System.out.println("\t\t" + block.getGenSet());
         System.out.println("\tKill:");
         System.out.println("\t\t" + block.getKillSet());
         System.out.println("\tLiveOut:");
         System.out.println("\t\t" + block.getLiveOut());    
      }
      System.out.println("\tInterference:");
      for (Node<Register> node : cfg.getInterference().getNodes()) {
         System.out.println(node);
      }
   }

   public void writeILOC(CFG cfg) {
      StringBuilder sb = new StringBuilder();
      List<BasicBlock> blocks = cfg.bfsBlocks();
      
      for (BasicBlock block : blocks) {            
         sb.append(block);               
      }           
      try {
         FileWriter writer = new FileWriter(new File("debug.il"));
         writer.write(sb.toString());
         writer.close();
      }
      catch (IOException e) {
         System.err.println("Error writing .il file: " + e);
      }
      
   }
}