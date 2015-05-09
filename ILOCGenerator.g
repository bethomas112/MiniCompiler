tree grammar ILOCGenerator;

options
{
   tokenVocab=Mini;
   ASTLabelType=CommonTree;
}

/*
   Type Checker -- Checks types
*/
@header
{
   import java.util.List;
   import java.util.LinkedList;
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.HashSet;
   import java.util.Map;
   import java.io.*;
   import javax.json.*;
}

@members
{   
   public static class ILOCResult {
      public List<CFG> cfgs;
   }

   private static int labelCount = 0;
   private static String getNextLabel() {
      return "L" + labelCount++;
   }

   private HashMap<String, MiniType.StructType> structTypes = new HashMap<>();
   private List<CFG> cfgs = new ArrayList<>();
   private ILOCResult result = new ILOCResult();
   private File outputFile;
   private HashMap<String, HashMap<String, MiniType>> functionLocalTypes = new HashMap<>();
   private HashMap<String, MiniType> globalTypes = new HashMap<>();
   private HashMap<String, MiniType> functionReturnTypes = new HashMap<>();
   public void setOutputFile(File file) {
      this.outputFile = file;
   }

   public ILOCResult getResult() {
      return result;
   }

   public void setFunctionTypeinfo(HashMap<String, TypeChecker.FunctionPrototype> localTypes) {
      for (Map.Entry<String, TypeChecker.FunctionPrototype> entry : localTypes.entrySet()) {
         this.functionLocalTypes.put(entry.getKey(), entry.getValue().localTypes);
         this.functionReturnTypes.put(entry.getKey(), entry.getValue().returnType);
      }
   }

   public void setGlobalTypes(HashMap<String, MiniType> globalTypes) {
      this.globalTypes = globalTypes;
   }
   
   public HashMap<String, MiniType> getGlobalTypes() {
      return globalTypes;
   }
   
}


translate
   :  ^(PROGRAM t=types d=declarations[null] f=functions)
         {
         
         }
   ;

types
   @init {  }
   :  ^(TYPES (t=type_decl {  })*)
      {  }
   |  {  }
   ;

type_decl
   @init { MiniType.StructType structType = new MiniType.StructType(); }
   :  ^(ast=STRUCT 
         id=ID 
            { 
               structType.name = $id.text;
               structTypes.put($id.text, structType);               
            } 
         n=nested_decl[structType])
      {
        
      }
   ;

nested_decl [MiniType.StructType structType]
   @init{  }
   :  (f=field_decl[structType] {  })+
   ;

field_decl [MiniType.StructType structType]
   returns [MiniType miniType = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         structType.fieldsOrdered.add($id.text);         
         structType.fields.put($id.text, $t.miniType);      
         $miniType = structType;
      }
   ;

type
   returns [MiniType miniType = null]
   :  INT { $miniType = MiniType.INT; }
   |  BOOL { $miniType = MiniType.BOOL; }
   |  ^(STRUCT id=ID) 
      { 
         $miniType = structTypes.get($id.text);
      }
   ;

declarations[CFG cfg]
   @init {}
   :  ^(DECLS (d=decl_list[cfg])*)
      {  }
   |  {  }
   ;

decl_list[CFG cfg]
   :  ^(DECLLIST ^(TYPE t=type)
         (id=ID
            {
               if (cfg != null) {
                  cfg.locals.put($id.text, Register.newRegister());
                  cfg.localsOrdered.add($id.text);
               }
            }
         )+
      )
   ;

functions
   returns []
   @init{  }
   :  ^(FUNCS (f=function {  })*)
      { 
         StringBuilder sb = new StringBuilder();
         result.cfgs = new ArrayList<>(cfgs);
         for (CFG cfg : cfgs) {
            List<BasicBlock> blocks = cfg.bfsBlocks();
            
            for (BasicBlock block : blocks) {            
               sb.append(block);               
            }           
         } 
         if (outputFile != null) {
            try {
               FileWriter writer = new FileWriter(outputFile);
               writer.write(sb.toString());
               writer.close();
            }
            catch (IOException e) {
               System.err.println("Error writing .il file: " + e);
            }
         }
      }
   |  {  }
   ;

function
   @init 
   { 
      boolean hasStatements = false;
      CFG cfg = new CFG(structTypes);
      Register.resetRegisters();
      BasicBlock exitBlock = new BasicBlock();
      exitBlock.label = getNextLabel();
      cfg.exitBlock = exitBlock;
      exitBlock.addInstruction(new IInstruction.RET());
   }
   :  ^(ast=FUN 
         id=ID
         {
            cfg.localTypes = functionLocalTypes.get($id.text);
         }        
         p=parameters[cfg] 
         {
            cfg.entryBlock = $p.entryBlock;
            cfg.entryBlock.label = $id.text;
         } 
         r=return_type
         d=declarations[cfg]
         s=statement_list[cfg, cfg.entryBlock] 
         {
            hasStatements = true;
         })
      {
         if (!hasStatements) {
            cfg.entryBlock.next.add(exitBlock);
            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
            jumpi.label = exitBlock.label;
            cfg.entryBlock.addInstruction(jumpi);
         }
         else if ($s.resultBlock != null && $s.resultBlock.next.isEmpty()) {
            $s.resultBlock.next.add(exitBlock);
            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
            jumpi.label = exitBlock.label;
            $s.resultBlock.addInstruction(jumpi);
         }
         cfgs.add(cfg);
      }
   ;

parameters[CFG cfg]
   returns [BasicBlock entryBlock = null]
   @init
   { 
      int paramNum = 0;
      BasicBlock block = new BasicBlock();
   }
   :  ^(PARAMS (p=param_decl[] 
      { 
         Register paramReg = Register.newRegister();
         cfg.params.put($p.paramId, paramNum);
         cfg.locals.put($p.paramId, paramReg);         
         //TODO: Generate code for setting up params.
         IInstruction.LOADINARGUMENT loadinargument = new IInstruction.LOADINARGUMENT();
         loadinargument.variable = $p.paramId;
         loadinargument.argIdx = paramNum;
         loadinargument.dest = paramReg;
         block.addInstruction(loadinargument);
         paramNum++;
      })*)
      { 
         $entryBlock = block; 
      }
   ;

param_decl[]
   returns [String paramId = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         $paramId = $id.text;
      }
   ;

return_type
   :  ^(RETTYPE (r=rtype)) {  }
   ;

rtype
   :  t=type {  }
   |  VOID {  }
   ;

statement[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  (s=block[cfg, block]
      |  s=assignment[cfg, block]
      |  s=print[cfg, block]
      |  s=read[cfg, block]
      |  s=conditional[cfg, block]
      |  s=loop[cfg, block]
      |  s=delete[cfg, block]
      |  s=return_stmt[cfg, block]
      |  s=invocation_stmt[cfg, block]
      )
      { $resultBlock = $s.resultBlock; }
   ;

block[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(BLOCK s=statement_list[cfg, block])
      {
         $resultBlock = $s.resultBlock;
      }
   ;

statement_list[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init{ BasicBlock currentBlock = block; }
   :  ^(STMTS (s=statement[cfg, currentBlock] {         
         currentBlock = $s.resultBlock;
      })*)
      { $resultBlock = currentBlock; }
   ;

assignment[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=ASSIGN e=expression[cfg, block] l=lvalue[cfg, block, false])
      {
         if ($l.wasGlobal) {
            IInstruction.STOREGLOBAL storeglobal = new IInstruction.STOREGLOBAL();
            storeglobal.source = $e.resultRegister;
            storeglobal.globalName = $l.globalName;
            block.addInstruction(storeglobal);
         }
         else if ($l.wasField) {
            IInstruction.STOREAIFIELD storeaifield = new IInstruction.STOREAIFIELD();
            storeaifield.structType = $l.structType;
            storeaifield.source = $e.resultRegister;
            storeaifield.dest = $l.resultRegister;
            storeaifield.fieldName = $l.fieldName;
            block.addInstruction(storeaifield);
         }
         else {
            IInstruction.MOV mov = new IInstruction.MOV();
            mov.source = $e.resultRegister;
            mov.dest = $l.resultRegister;
            block.addInstruction(mov);
         }
         $resultBlock = block;
      }
   ;

print[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init {  }
   :  ^(ast=PRINT e=expression[cfg, block] (ENDL {  })?)
      {
         $resultBlock = block;
         IInstruction.PRINTLN println = new IInstruction.PRINTLN();
         println.source = $e.resultRegister;
         block.addInstruction(println);
      }
   ;

read[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=READ l=lvalue[cfg, block, false])
      {
         if ($l.wasGlobal) {
           IInstruction.COMPUTEGLOBALADDRESS computeglobaladdress = new IInstruction.COMPUTEGLOBALADDRESS();
           computeglobaladdress.dest = Register.newRegister();
           computeglobaladdress.globalName = $l.globalName;
           block.addInstruction(computeglobaladdress);
           IInstruction.READ read = new IInstruction.READ();
           read.dest = computeglobaladdress.dest;
           block.addInstruction(read);
         }
         else if ($l.wasField) {
            IInstruction.ADDISTRUCT addistruct = new IInstruction.ADDISTRUCT();
            addistruct.source = $l.resultRegister;
            addistruct.fieldName = $l.fieldName;
            addistruct.dest = Register.newRegister();
            addistruct.structType = $l.structType;
            block.addInstruction(addistruct);
            IInstruction.READ read = new IInstruction.READ();
            read.dest = addistruct.dest;
            block.addInstruction(read);
         }
         else {
            IInstruction.ADDILOCAL addilocal = new IInstruction.ADDILOCAL();
            addilocal.localName = $l.localName;
            addilocal.dest = Register.newRegister();
            block.addInstruction(addilocal);
            IInstruction.READ read = new IInstruction.READ();
            read.dest = addilocal.dest;
            block.addInstruction(read);
            IInstruction.LOADAILOCAL loadailocal = new IInstruction.LOADAILOCAL();
            loadailocal.localName = $l.localName;
            loadailocal.dest = $l.resultRegister;
            block.addInstruction(loadailocal);
         }
         $resultBlock = block;
      }
   ;

conditional[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init 
   {
      BasicBlock guardBlock = new BasicBlock();
      guardBlock.label = getNextLabel();
      BasicBlock thenBlock = new BasicBlock();
      thenBlock.label = getNextLabel();
      BasicBlock elseBlock = new BasicBlock();
      elseBlock.label = getNextLabel();
      BasicBlock afterBlock = new BasicBlock();
      afterBlock.label = getNextLabel();
      boolean hasElseBlock = false;
   }
   :  ^(ast=IF g=expression[cfg, guardBlock] t=block[cfg, thenBlock] (e=block[cfg, elseBlock]
         {
            hasElseBlock = true;
         }
      )?)      
      {
         block.next.add(guardBlock);
         guardBlock.next.add(thenBlock);
         
         IInstruction.COMPI compi = new IInstruction.COMPI();
         compi.source = $g.resultRegister;
         compi.immediate = 1;
         guardBlock.addInstruction(compi);

         IInstruction.CBREQ cbreq = new IInstruction.CBREQ();
         cbreq.labelA = thenBlock.label;
         cbreq.labelB = hasElseBlock ? elseBlock.label : afterBlock.label;
         guardBlock.addInstruction(cbreq);
         if ($t.resultBlock != null) {
            $t.resultBlock.next.add(afterBlock);
            
            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
            jumpi.label = afterBlock.label;
            $t.resultBlock.addInstruction(jumpi);
         }
         if (hasElseBlock) {
            guardBlock.next.add(elseBlock);   
            if ($e.resultBlock != null) {
               $e.resultBlock.next.add(afterBlock);

               IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
               jumpi.label = afterBlock.label;
               $e.resultBlock.addInstruction(jumpi);
            }
         }
         $resultBlock = afterBlock;
      }
   ;

loop[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init 
   {
      BasicBlock guardBlock = new BasicBlock();
      guardBlock.label = getNextLabel();
      BasicBlock afterBlock = new BasicBlock();
      afterBlock.label = getNextLabel();
      BasicBlock bodyBlock = new BasicBlock();
      bodyBlock.label = getNextLabel();
   }
   :  ^(ast=WHILE e=expression[cfg, guardBlock] b=block[cfg, bodyBlock] expression[cfg, new BasicBlock()])
      {
         block.next.add(guardBlock);
         guardBlock.next.add(bodyBlock);
         guardBlock.next.add(afterBlock);

         IInstruction.COMPI compi = new IInstruction.COMPI();
         compi.source = $e.resultRegister;
         compi.immediate = 1;
         guardBlock.addInstruction(compi);

         IInstruction.CBREQ cbreq = new IInstruction.CBREQ();
         cbreq.labelA = bodyBlock.label;
         cbreq.labelB = afterBlock.label;
         guardBlock.addInstruction(cbreq);
         if ($b.resultBlock != null) {
            $b.resultBlock.next.add(guardBlock);

            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
            jumpi.label = guardBlock.label;
            $b.resultBlock.addInstruction(jumpi);
         }
         $resultBlock = afterBlock;
      }
   ;

delete[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=DELETE e=expression[cfg, block])
      {
         IInstruction.DEL del = new IInstruction.DEL();
         del.source = $e.resultRegister;
         block.addInstruction(del);
         $resultBlock = block;
      }
   ;

return_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init 
   {
      boolean hasExpression = false;
   }
   :  ^(ast=RETURN (e=expression[cfg, block] { hasExpression = true; })?)
      {
         if (hasExpression) {
            IInstruction.STORERET storeret = new IInstruction.STORERET();
            storeret.source = $e.resultRegister;
            block.addInstruction(storeret);            
         }
         block.next.add(cfg.exitBlock);
         IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
         jumpi.label = cfg.exitBlock.label;
         block.addInstruction(jumpi);
      }
   ;

invocation_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init 
   { 
      int argIdx = 0; 
      List<IInstruction> instructions = new ArrayList<IInstruction>();
   }
   :  ^(INVOKE id=ID 
         { 
           
         } 
         ^(ARGS (e=expression[cfg, block] 
         {  
            IInstruction.STOREOUTARGUMENT storeoutargument = new IInstruction.STOREOUTARGUMENT();
            storeoutargument.source = $e.resultRegister;
            storeoutargument.argIdx = argIdx;
            instructions.add(storeoutargument);
            argIdx++;
         })*))
      {
         for (IInstruction instruction : instructions) {
            block.addInstruction(instruction);
         }
         
         IInstruction.CALL call = new IInstruction.CALL();
         call.label = $id.text;
         block.addInstruction(call);
         $resultBlock = block;
      }
   ;

lvalue[CFG cfg, BasicBlock block, boolean nested]
   returns 
      [
         Register resultRegister = null, 
         boolean wasGlobal = false, 
         boolean wasField = false,
         String fieldName = null,
         String globalName = null,
         String localName = null,
         MiniType.StructType structType = null
      ]
   :  id=ID
      {
         if (cfg.locals.get($id.text) != null) {
            $resultRegister = cfg.locals.get($id.text);
            $localName = $id.text;
            MiniType localType = cfg.localTypes.get($id.text);
            if (localType instanceof MiniType.StructType) {
               $structType = (MiniType.StructType)localType;
            }
         }
         else {
            if (nested == true) {
               IInstruction.LOADGLOBAL instruction = new IInstruction.LOADGLOBAL();
               instruction.globalName = $id.text;
               instruction.dest = Register.newRegister();
               block.addInstruction(instruction);
               $resultRegister = instruction.dest;
            }
            $wasGlobal = true;
            $globalName = $id.text;
            MiniType globalType = globalTypes.get($id.text);
            if (globalType instanceof MiniType.StructType) {
               $structType = (MiniType.StructType)globalType;
            }
         }
      }
   |  ^(ast=DOT l=lvalue[cfg, block, true] id=ID)
      {         
         if (nested) {
            IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
            loadaifield.source = $l.resultRegister;
            loadaifield.fieldName = $id.text;
            loadaifield.dest = Register.newRegister();
            loadaifield.structType = $l.structType;
            block.addInstruction(loadaifield);
            $resultRegister = loadaifield.dest;            
            MiniType fieldType = $l.structType.fields.get($id.text);
            if (fieldType instanceof MiniType.StructType) {
               $structType = (MiniType.StructType)fieldType;
            }
         }
         else {
            $resultRegister = $l.resultRegister;
            $structType = $l.structType;
         }
         $fieldName = $id.text;
         $wasField = true;

      }
   ;

expression[CFG cfg, BasicBlock block]
   returns
   [
      Register resultRegister = null,
      MiniType.StructType structType = null
   ]
   @init 
   {
      int argIdx = 0; 
      List<IInstruction> instructions = new ArrayList<IInstruction>();
   }
   :  ^((ast=AND | ast=OR)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         Register leftReg = $lft.resultRegister;
         Register rightReg = $rht.resultRegister;
         Register result = Register.newRegister();
         
         if ($ast.text.equals("&&")) {
            IInstruction.AND instruction = new IInstruction.AND();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         else {
            IInstruction.OR instruction = new IInstruction.OR();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         $resultRegister = result;
      }
   //Comparisons
   |  ^((ast=EQ | ast=LT | ast=GT | ast=NE | ast=LE | ast=GE)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         $resultRegister = Register.newRegister();

         IInstruction.COMP comp = new IInstruction.COMP();
         comp.sourceA = $lft.resultRegister;
         comp.sourceB = $rht.resultRegister;
         block.addInstruction(comp);

         IInstruction.LOADI loadi = new IInstruction.LOADI();
         loadi.immediate = 0;
         loadi.dest = $resultRegister;
         block.addInstruction(loadi);

         IInstruction.LOADI loadi1 = new IInstruction.LOADI();
         loadi1.immediate = 1;
         loadi1.dest = Register.newRegister();
         block.addInstruction(loadi1);         
         switch ($ast.text) {
            case "==":
               IInstruction.MOVEQ moveq = new IInstruction.MOVEQ();
               moveq.source = loadi1.dest;
               moveq.dest = $resultRegister;
               block.addInstruction(moveq);
               break;
            case "<":
               IInstruction.MOVLT movlt = new IInstruction.MOVLT();
               movlt.source = loadi1.dest;
               movlt.dest = $resultRegister;
               block.addInstruction(movlt);
               break;
            case ">":
               IInstruction.MOVGT movgt = new IInstruction.MOVGT();
               movgt.source = loadi1.dest;
               movgt.dest = $resultRegister;
               block.addInstruction(movgt);
               break;
            case "!=":
               IInstruction.MOVNE movne = new IInstruction.MOVNE();
               movne.source = loadi1.dest;
               movne.dest = $resultRegister;
               block.addInstruction(movne);
               break;
            case "<=":
               IInstruction.MOVLE movle = new IInstruction.MOVLE();
               movle.source = loadi1.dest;
               movle.dest = $resultRegister;
               block.addInstruction(movle);
               break;
            case ">=":
               IInstruction.MOVGE movge = new IInstruction.MOVGE();
               movge.source = loadi1.dest;
               movge.dest = $resultRegister;
               block.addInstruction(movge);
               break;
         }
      }
   //Arithmetic
   |  ^((ast=PLUS | ast=MINUS | ast=TIMES | ast=DIVIDE)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         Register leftReg = $lft.resultRegister;
         Register rightReg = $rht.resultRegister;
         Register result = Register.newRegister();
         if ($ast.text.equals("+")) {
            IInstruction.ADD instruction = new IInstruction.ADD();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         else if ($ast.text.equals("-")) {
            IInstruction.SUB instruction = new IInstruction.SUB();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         else if ($ast.text.equals("*")) {
            IInstruction.MULT instruction = new IInstruction.MULT();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         else if ($ast.text.equals("/")) {
            IInstruction.DIV instruction = new IInstruction.DIV();
            instruction.sourceA = leftReg;
            instruction.sourceB = rightReg;
            instruction.dest = result;
            block.addInstruction(instruction);
         }
         $resultRegister = result;
      }
   |  ^(ast=NOT e=expression[cfg, block])
      {
         IInstruction.XORI xori = new IInstruction.XORI();
         xori.source = $e.resultRegister;
         xori.immediate = 1;
         xori.dest = Register.newRegister();
         block.addInstruction(xori);
         $resultRegister = xori.dest;
      }
   |  ^(ast=NEG e=expression[cfg, block])
      {
         IInstruction.LOADI loadi = new IInstruction.LOADI();
         loadi.immediate = -1;
         loadi.dest = Register.newRegister();
         block.addInstruction(loadi);
         IInstruction.MULT mult = new IInstruction.MULT();
         mult.sourceA = $e.resultRegister;
         mult.sourceB = loadi.dest;
         mult.dest = loadi.dest;
         block.addInstruction(mult);
         $resultRegister = mult.dest;
      }
   |  ^(ast=DOT e=expression[cfg, block] id=ID)
      {
         IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
         loadaifield.source = $e.resultRegister;
         loadaifield.fieldName = $id.text;
         loadaifield.dest = Register.newRegister();
         loadaifield.structType = (MiniType.StructType)$e.structType;
         block.addInstruction(loadaifield);
         $resultRegister = loadaifield.dest;

         MiniType fieldType = $e.structType.fields.get($id.text);
         if (fieldType instanceof MiniType.StructType) {
            $structType = (MiniType.StructType)fieldType;
         }
      }
   |  ^(INVOKE id=ID
         { 
            
         }
      ^(ARGS (e=expression[cfg, block] 
         {  
            IInstruction.STOREOUTARGUMENT storeoutargument = new IInstruction.STOREOUTARGUMENT();
            storeoutargument.source = $e.resultRegister;
            storeoutargument.argIdx = argIdx;
            instructions.add(storeoutargument);
            argIdx++;
         })*))
      {
         for (IInstruction instruction : instructions) {
            block.addInstruction(instruction);
         }
         IInstruction.CALL call = new IInstruction.CALL();
         call.label = $id.text;
         block.addInstruction(call);

         if (functionReturnTypes.get($id.text) instanceof MiniType.StructType) {
            $structType = (MiniType.StructType)functionReturnTypes.get($id.text);   
         } 
         IInstruction.LOADRET loadret = new IInstruction.LOADRET();
         loadret.dest = Register.newRegister();
         block.addInstruction(loadret);
         $resultRegister = loadret.dest;  
      }
   |  id=ID
      {              
         if (cfg.locals.get($id.text) != null) {
            $resultRegister = cfg.locals.get($id.text);
            if (cfg.localTypes.get($id.text) instanceof MiniType.StructType) {
               $structType = (MiniType.StructType)cfg.localTypes.get($id.text);
            }            
         }
         else {
            IInstruction.LOADGLOBAL instruction = new IInstruction.LOADGLOBAL();
            instruction.globalName = $id.text;
            instruction.dest = Register.newRegister();
            block.addInstruction(instruction);
            $resultRegister = instruction.dest;            
            if (globalTypes.get($id.text) instanceof MiniType.StructType) {
               $structType = (MiniType.StructType)globalTypes.get($id.text);
            }
         }
      }
   |  i=INTEGER
      {
         IInstruction.LOADI instruction = new IInstruction.LOADI();
         instruction.immediate = Integer.parseInt($i.text);
         instruction.dest = Register.newRegister();
         block.addInstruction(instruction);
         $resultRegister = instruction.dest;
      }
   |  ast=TRUE
      { 
         IInstruction.LOADI instruction = new IInstruction.LOADI();
         instruction.immediate = 1;
         instruction.dest = Register.newRegister();
         block.addInstruction(instruction);
         $resultRegister = instruction.dest;  
      }
   |  ast=FALSE
      {  
         IInstruction.LOADI instruction = new IInstruction.LOADI();
         instruction.immediate = 0;
         instruction.dest = Register.newRegister();
         block.addInstruction(instruction);
         $resultRegister = instruction.dest;
      }
   |  ^(ast=NEW id=ID)
      {
         IInstruction.NEW instruction = new IInstruction.NEW();
         instruction.struct = (MiniType.StructType)structTypes.get($id.text);;
         instruction.dest = Register.newRegister();
         block.addInstruction(instruction);
         $resultRegister = instruction.dest;
         $structType = instruction.struct;
      }
   |  ast=NULL
      {
         IInstruction.LOADI instruction = new IInstruction.LOADI();
         instruction.immediate = 0;
         instruction.dest = Register.newRegister();
         block.addInstruction(instruction);
         $resultRegister = instruction.dest;
      }
   ;