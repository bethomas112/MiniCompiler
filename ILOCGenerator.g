tree grammar ILOCBuilder;

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
   public static CFG {
      public EntryBlock entry;
      public ExitBlock exit;
      public HashMap<String, Register> locals;
      public HashMap<String, Integer> params;

      public CFG() {
         this.locals = new HashMap<>();
         this.entry = new EntryBlock();
         this.exit = new ExitBlock();
      }
   }

   public abstract static class BasicBlock {
      public List<BasicBlock> prev;
      public List<BasicBlock> next;
      public BasicBlock() {
         prev = new ArrayList<>();
         next = new ArrayList<>();
      }

      public abstract List<IInstruction> getILOC();
   }

   public static class EntryBlock extends BasicBlock {
      public abstract List<IInstruction> getILOC() {
         return new ArrayList<>();
      }
   }

   public static class ExitBlock extends BasicBlock {
      public abstract List<IInstruction> getILOC() {
         return new ArrayList<>();
      }
   }   
}


translate
   returns [MiniType miniType = null]
   :  ^(PROGRAM t=types d=declarations[] f=functions)
         {
            
         }
   ;

types
   returns []
   @init {  }
   :  ^(TYPES (t=type_decl {  })*)
      {  }
   |  {  }
   ;

type_decl
   returns []
   @init {  }
   :  ^(ast=STRUCT 
         id=ID 
            { 
               
            } 
         n=nested_decl[])
      {
        
      }
   ;

nested_decl []
   returns []
   @init{  }
   :  (f=field_decl[structType] {  })+
      {  }
   ;

field_decl [StructType structType]
   returns [MiniType miniType = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         
      }
   ;

type
   returns [MiniType miniType = null]
   :  INT {  }
   |  BOOL {  }
   |  ^(STRUCT id=ID) 
      { 
         
      }
   ;

declarations[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   @init {}
   :  ^(DECLS (d=decl_list[typeEnv])*)
      {  }
   |  {  }
   ;

decl_list[HashMap<String, MiniType> typeEnv]
   :  ^(DECLLIST ^(TYPE t=type)
         (id=ID
            {
               
            }
         )+
      )
   ;

functions
   returns []
   @init{  }
   :  ^(FUNCS (f=function {  })*)
      {  }
   |  {  }
   ;

function
   @init 
   { 
      
   }
   :  ^(ast=FUN 
         id=ID 
            {
               
            }
         p=parameters[proto, typeEnv] 
         r=return_type 
            {
               
            }
         d=declarations[typeEnv]
         s=statement_list[proto, typeEnv])
      {
         
      }
   ;

parameters[]
   returns []
   @init{  }
   :  ^(PARAMS (p=param_decl[] { })*)
      {  }
   ;

param_decl[]
   returns []
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         
      }
   ;

return_type
   returns []
   :  ^(RETTYPE (r=rtype)) {  }
   ;

rtype
   returns []
   :  t=type { $miniType = $t.miniType; }
   |  VOID {  }
   ;

statement[]
   returns []
   :  (s=block[proto, typeEnv]
      |  s=assignment[typeEnv]
      |  s=print[typeEnv]
      |  s=read[typeEnv]
      |  s=conditional[proto, typeEnv]
      |  s=loop[proto, typeEnv]
      |  s=delete[typeEnv]
      |  s=return_stmt[proto, typeEnv]
      |  s=invocation_stmt[typeEnv]
      )
      {  }
   ;

block[]
   returns []
   :  ^(BLOCK s=statement_list[proto, typeEnv])
      {
         
      }
   ;

statement_list[]
   returns []
   @init{  }
   :  ^(STMTS (s=statement[] {         
         
      })*)
      {  }
   ;

assignment[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=ASSIGN e=expression[typeEnv] l=lvalue[typeEnv])
      {
         
      }
   ;

print[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   @init {  }
   :  ^(ast=PRINT e=expression[typeEnv] (ENDL {  })?)
      {
         
      }
   ;

read[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=READ l=lvalue[typeEnv])
      {
         
      }
   ;

conditional[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=IF g=expression[typeEnv] t=block[proto, typeEnv] (e=block[proto, typeEnv])?)
      {
         
      }
   ;

loop[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=WHILE e=expression[typeEnv] b=block[proto, typeEnv] expression[typeEnv])
      {
         
      }
   ;

delete[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=DELETE e=expression[typeEnv])
      {
         
      }
   ;

return_stmt[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = true]
   :  ^(ast=RETURN (e=expression[typeEnv])?)
      {
         
      }
   ;

invocation_stmt[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   @init { FunctionPrototype proto = null; int argIdx = 0; }
   :  ^(INVOKE id=ID 
         { 
           
         } 
         ^(ARGS (e=expression[typeEnv] 
            {  
               
            })*))
      {
      }
   ;

lvalue[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType]
   :  id=ID
      {
         
      }
   |  ^(ast=DOT l=lvalue[typeEnv] id=ID)
      {
         
      }
   ;

expression[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   :  ^((ast=AND | ast=OR)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         
      }
   //Comparisons
   |  ^((ast=EQ | ast=LT | ast=GT | ast=NE | ast=LE | ast=GE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         
      }
   //Arithmetic
   |  ^((ast=PLUS | ast=MINUS | ast=TIMES | ast=DIVIDE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         
      }
   |  ^(ast=NOT e=expression[typeEnv])
      {
         
      }
   |  ^(ast=NEG e=expression[typeEnv])
      {
         
      }
   |  ^(ast=DOT e=expression[typeEnv] id=ID)
      {
         
      }
   |  e=invocation_exp[typeEnv] 
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

invocation_exp[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   @init { FunctionPrototype proto = null; int argIdx = 0; }
   :  ^(INVOKE id=ID
         { 
            
         }
      ^(ARGS (e=expression[typeEnv] 
         {  
            
         })*))
      {
         
      }
   ;