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
      public List<IInstruction> getILOC() {
         return new ArrayList<>();
      }
   }

   public static class ExitBlock extends BasicBlock {
      public List<IInstruction> getILOC() {
         return new ArrayList<>();
      }
   }

   private HashMap<String, MiniType> structTypes = new HashMap<>();
}


translate
   :  ^(PROGRAM t=types d=declarations[] f=functions)
         {
            for (MiniType type : structTypes.values()) {
               System.out.println(type);
            }
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

declarations[]
   @init {}
   :  ^(DECLS (d=decl_list[])*)
      {  }
   |  {  }
   ;

decl_list[]
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
         p=parameters[] 
         r=return_type 
            {
               
            }
         d=declarations[]
         s=statement_list[])
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
   :  t=type {  }
   |  VOID {  }
   ;

statement[]
   returns []
   :  (s=block[]
      |  s=assignment[]
      |  s=print[]
      |  s=read[]
      |  s=conditional[]
      |  s=loop[]
      |  s=delete[]
      |  s=return_stmt[]
      |  s=invocation_stmt[]
      )
      {  }
   ;

block[]
   returns []
   :  ^(BLOCK s=statement_list[])
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

assignment[]
   returns [boolean hasReturn = false]
   :  ^(ast=ASSIGN e=expression[] l=lvalue[])
      {
         
      }
   ;

print[]
   returns [boolean hasReturn = false]
   @init {  }
   :  ^(ast=PRINT e=expression[] (ENDL {  })?)
      {
         
      }
   ;

read[]
   returns [boolean hasReturn = false]
   :  ^(ast=READ l=lvalue[])
      {
         
      }
   ;

conditional[]
   returns [boolean hasReturn = false]
   :  ^(ast=IF g=expression[] t=block[] (e=block[])?)
      {
         
      }
   ;

loop[]
   returns [boolean hasReturn = false]
   :  ^(ast=WHILE e=expression[] b=block[] expression[])
      {
         
      }
   ;

delete[]
   returns [boolean hasReturn = false]
   :  ^(ast=DELETE e=expression[])
      {
         
      }
   ;

return_stmt[]
   returns [boolean hasReturn = true]
   :  ^(ast=RETURN (e=expression[])?)
      {
         
      }
   ;

invocation_stmt[]
   returns [boolean hasReturn = false]
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