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
   import java.util.ArrayList;
   import java.util.HashMap;
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
            System.out.println(cfg.entryBlock.label);
            System.out.println(cfg.locals);
            System.out.println(cfg.params);
         } 
      }
   |  {  }
   ;

function
   @init 
   { 
      CFG cfg = new CFG();
      Register.resetRegisters();
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
         cfg.exitBlock = $s.resultBlock;
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
   :  ^(ast=ASSIGN e=expression[] l=lvalue[])
      {
         
      }
   ;

print[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init {  }
   :  ^(ast=PRINT e=expression[] (ENDL {  })?)
      {
         
      }
   ;

read[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=READ l=lvalue[])
      {
         
      }
   ;

conditional[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=IF g=expression[] t=block[cfg, block] (e=block[cfg, block])?)
      {
         
      }
   ;

loop[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=WHILE e=expression[] b=block[cfg, block] expression[])
      {
         
      }
   ;

delete[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=DELETE e=expression[])
      {
         
      }
   ;

return_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   :  ^(ast=RETURN (e=expression[])?)
      {
         
      }
   ;

invocation_stmt[CFG cfg, BasicBlock block]
   returns [BasicBlock resultBlock = null]
   @init { int argIdx = 0; }
   :  ^(INVOKE id=ID 
         { 
           
         } 
         ^(ARGS (e=expression[] 
            {  
               
            })*))
      {
      }
   ;

lvalue[]
   returns []
   :  id=ID
      {
         
      }
   |  ^(ast=DOT l=lvalue[] id=ID)
      {
         
      }
   ;

expression[]
   returns []
   :  ^((ast=AND | ast=OR)
         lft=expression[] rht=expression[])
      {
         
      }
   //Comparisons
   |  ^((ast=EQ | ast=LT | ast=GT | ast=NE | ast=LE | ast=GE)
         lft=expression[] rht=expression[])
      {
         
      }
   //Arithmetic
   |  ^((ast=PLUS | ast=MINUS | ast=TIMES | ast=DIVIDE)
         lft=expression[] rht=expression[])
      {
         
      }
   |  ^(ast=NOT e=expression[])
      {
         
      }
   |  ^(ast=NEG e=expression[])
      {
         
      }
   |  ^(ast=DOT e=expression[] id=ID)
      {
         
      }
   |  e=invocation_exp[] 
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

invocation_exp[]
   returns []
   @init { int argIdx = 0; }
   :  ^(INVOKE id=ID
         { 
            
         }
      ^(ARGS (e=expression[] 
         {  
            
         })*))
      {
         
      }
   ;