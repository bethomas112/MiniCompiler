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
   import javax.json.*;
}

@members
{
   public static class CFG {
      public BasicBlock entryBlock;
      public BasicBlock exitBlock;
      public HashMap<String, Register> locals;
      public HashMap<String, Integer> params;

      public CFG() {
         this.locals = new HashMap<>();
         this.params = new HashMap<>();
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
               for (BasicBlock nextBlock : block.next) {               
                  queue.add(nextBlock);     
               }
            }
         }
         return result;
      }
   }

   public static class BasicBlock {
      public List<BasicBlock> prev;
      public List<BasicBlock> next;
      public String label;
      public BasicBlock() {
         prev = new ArrayList<>();
         next = new ArrayList<>();
      }

      public List<IInstruction> getILOC() { 
         return new ArrayList<>(); 
      };
   }

   private static int labelCount = 0;
   private static String getNextLabel() {
      return "L" + labelCount++;
   }

   private HashMap<String, MiniType> structTypes = new HashMap<>();
   private List<CFG> cfgs = new ArrayList<>();   
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
               structType.name = "Struct " + $id.text;
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
         for (CFG cfg : cfgs) {
            List<BasicBlock> blocks = cfg.bfsBlocks();
            for (BasicBlock block : blocks) {
               StringBuilder sb = new StringBuilder();
               sb.append(block.label + " -> ");
               for (BasicBlock nextBlock : block.next) {
                  sb.append(nextBlock.label + ", ");
               }
               System.out.println(sb.toString());
            }
         } 
      }
   |  {  }
   ;

function
   @init 
   { 
      CFG cfg = new CFG();
      Register.resetRegisters();
      BasicBlock exitBlock = new BasicBlock();
      exitBlock.label = getNextLabel();
      cfg.exitBlock = exitBlock;
   }
   :  ^(ast=FUN 
         id=ID          
         p=parameters[cfg] 
         {
            cfg.entryBlock = $p.entryBlock;
            cfg.entryBlock.label = $id.text;
         } 
         r=return_type
         d=declarations[cfg]
         s=statement_list[cfg, cfg.entryBlock])
      {
         cfgs.add(cfg);
      }
   ;

parameters[CFG cfg]
   returns [BasicBlock entryBlock = null]
   @init{ int paramNum = 0; }
   :  ^(PARAMS (p=param_decl[] 
      { 
         cfg.params.put($p.paramId, paramNum++);
         cfg.locals.put($p.paramId, Register.newRegister());         
         //TODO: Generate code for setting up params.
      })*)
      { 
         $entryBlock = new BasicBlock(); 
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
   :  ^(ast=ASSIGN e=expression[cfg, block] l=lvalue[cfg, block])
      {
         $resultBlock = block;
      }
   ;

print[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init {  }
   :  ^(ast=PRINT e=expression[cfg, block] (ENDL {  })?)
      {
         $resultBlock = block;
      }
   ;

read[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=READ l=lvalue[cfg, block])
      {
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
         
         if ($t.resultBlock != null) {
            $t.resultBlock.next.add(afterBlock);
         }
         if (hasElseBlock) {
            guardBlock.next.add(elseBlock);   
            if ($e.resultBlock != null) {
               $e.resultBlock.next.add(afterBlock);
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
         if ($b.resultBlock != null) {
            $b.resultBlock.next.add(guardBlock);
         }
         $resultBlock = afterBlock;
      }
   ;

delete[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=DELETE e=expression[cfg, block])
      {
         $resultBlock = block;
      }
   ;

return_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=RETURN (e=expression[cfg, block])?)
      {
         block.next.add(cfg.exitBlock);
      }
   ;

invocation_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init { int argIdx = 0; }
   :  ^(INVOKE id=ID 
         { 
           
         } 
         ^(ARGS (e=expression[cfg, block] 
            {  
               
            })*))
      {
         $resultBlock = block;
      }
   ;

lvalue[CFG cfg, BasicBlock block]
   returns []
   :  id=ID
      {
         
      }
   |  ^(ast=DOT l=lvalue[cfg, block] id=ID)
      {
         
      }
   ;

expression[CFG cfg, BasicBlock block]
   returns [Register resultRegister = null]
   :  ^((ast=AND | ast=OR)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         
      }
   //Comparisons
   |  ^((ast=EQ | ast=LT | ast=GT | ast=NE | ast=LE | ast=GE)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         
      }
   //Arithmetic
   |  ^((ast=PLUS | ast=MINUS | ast=TIMES | ast=DIVIDE)
         lft=expression[cfg, block] rht=expression[cfg, block])
      {
         
      }
   |  ^(ast=NOT e=expression[cfg, block])
      {
         
      }
   |  ^(ast=NEG e=expression[cfg, block])
      {
         
      }
   |  ^(ast=DOT e=expression[cfg, block] id=ID)
      {
         
      }
   |  e=invocation_exp[cfg, block] 
      {
         
      }
   |  id=ID
      {     
                 
      }
   |  i=INTEGER
      {
         
      }
   |  ast=TRUE
      { 
         
      }
   |  ast=FALSE
      {
         
      }
   |  ^(ast=NEW id=ID)
      {
         
      }
   |  ast=NULL
      {
         
      }
   ;

invocation_exp[CFG cfg, BasicBlock block]
   returns [Register resultRegister = null]
   @init { int argIdx = 0; }
   :  ^(INVOKE id=ID
         { 
            
         }
      ^(ARGS (e=expression[cfg, block] 
         {  
            
         })*))
      {
         
      }
   ;