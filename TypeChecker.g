tree grammar TypeChecker;

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
   import java.util.HashMap;
   import javax.json.*;
}

@members
{
   private JsonBuilderFactory factory = Json.createBuilderFactory(null);
   
   static class FunctionPrototype {
      MiniType returnType;
      List<MiniType> argTypes = new ArrayList<>();
   }

   static class MiniType {
      static final MiniType INT = new MiniType("INT");
      static final MiniType BOOL = new MiniType("BOOL");
      static final MiniType VOID = new MiniType("VOID");
      static final MiniType NULL = new MiniType("NULL");
      String name;
      public MiniType() {

      }
      public MiniType(String name) {
         this.name = name;
      }
   }

   class StructType extends MiniType {
      HashMap<String, MiniType> fields = new HashMap<>();
   }

   // class FunctionScope {
   //    boolean returns;
   //    FunctionPrototype proto;
   //    HashMap<String, MiniType> typeEnv;

   //    public FunctionScope
   // }

   HashMap<String, MiniType> structTypes = new HashMap<>();
   HashMap<String, MiniType> globalTypeEnv = new HashMap<>();
   HashMap<String, FunctionPrototype> functionDefs = new HashMap<>();
}


translate
   returns [MiniType miniType = null]
   :  ^(PROGRAM t=types d=declarations[globalTypeEnv] f=functions)
         {
            if (functionDefs.get("main") == null) {
               throw new RuntimeException("undefined reference to \'main\'");
            }
         }
   ;

types
   returns [MiniType miniType = null]
   @init {  }
   :  ^(TYPES (t=type_decl {  })*)
      {  }
   |  {  }
   ;

type_decl
   returns [MiniType miniType = null]
   @init { StructType structType = new StructType(); }
   :  ^(ast=STRUCT 
         id=ID 
            { 
               structType.name = "Struct " + $id.text;
               if (structTypes.put($id.text, structType) != null) {
                  throw new RuntimeException("Redefinition of struct: " + $id.text);
               }
            } 
         n=nested_decl[structType])
      {
        
      }
   ;

nested_decl [StructType structType]
   returns [MiniType miniType = null]
   @init{  }
   :  (f=field_decl[structType] {  })+
      { $miniType = structType; }
   ;

field_decl [StructType structType]
   returns [MiniType miniType = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
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
         if (structTypes.get($id.text) != null) {
            $miniType = structTypes.get($id.text);
         }
         else {
            throw new RuntimeException("Undefined struct type: " + $id.text);
         }
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
               if (typeEnv.put($id.text, $t.miniType) != null) {
                  throw new RuntimeException("Redefinition of symbol: " + $id.text);
               }
            }
         )+
      )
   ;

functions
   returns [MiniType miniType = null]
   @init{  }
   :  ^(FUNCS (f=function {  })*)
      {  }
   |  {  }
   ;

function
   @init 
   { 
      HashMap<String, MiniType> typeEnv = new HashMap<>();
      FunctionPrototype proto = new FunctionPrototype();
   }
   :  ^(ast=FUN 
         id=ID 
            {
               if (functionDefs.put($id.text, proto) != null) {
                  throw new RuntimeException("Redefinition of function" + $id.text);
               }
            }
         p=parameters[proto, typeEnv] 
         r=return_type 
            {
               proto.returnType = $r.miniType;
            }
         d=declarations[typeEnv]
         s=statement_list[proto, typeEnv])
      {
         if (!$s.hasReturn) {
            throw new RuntimeException("Missing return statement");
         }
      }
   ;

parameters[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   @init{  }
   :  ^(PARAMS (p=param_decl[proto, typeEnv] { })*)
      {  }
   ;

param_decl[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         proto.argTypes.add($t.miniType);
         if (typeEnv.put($id.text, $t.miniType) != null) {
            throw new RuntimeException("Redefinition of symbol: " + $id.text);
         }
      }
   ;

return_type
   returns [MiniType miniType = null]
   :  ^(RETTYPE (r=rtype)) { $miniType = $r.miniType; }
   ;

rtype
   returns [MiniType miniType = null]
   :  t=type { $miniType = $t.miniType; }
   |  VOID { $miniType = MiniType.VOID; }
   ;

statement[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
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
      { $hasReturn = $s.hasReturn; }
   ;

block[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(BLOCK s=statement_list[proto, typeEnv])
      {
         $hasReturn = $s.hasReturn;
      }
   ;

statement_list[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   @init{  }
   :  ^(STMTS (s=statement[proto, typeEnv] {
         $hasReturn |= $s.hasReturn; 
         
      })*)
      {  }
   ;

assignment[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=ASSIGN e=expression[typeEnv] l=lvalue[typeEnv])
      {
         if (!($e.miniType == $l.miniType)) {
            if (!($l.miniType instanceof StructType && $e.miniType == MiniType.NULL)) {
               throw new RuntimeException("Type mismatch in assignment: " + $l.miniType.name + ", " + $e.miniType.name);               
            }
         }
      }
   ;

print[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   @init {  }
   :  ^(ast=PRINT e=expression[typeEnv] (ENDL {  })?)
      {
         if ($e.miniType != MiniType.INT) {
            throw new RuntimeException("Attempt to print a non-integer");
         }
      }
   ;

read[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=READ l=lvalue[typeEnv])
      {
         if ($l.miniType != MiniType.INT) {
            throw new RuntimeException("Attempt to read into a non-integer");
         }
      }
   ;

conditional[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=IF g=expression[typeEnv] t=block[proto, typeEnv] (e=block[proto, typeEnv])?)
      {
         if ($g.miniType != MiniType.BOOL) {
            throw new RuntimeException("If statement guard must be a boolean expression");
         }
         $hasReturn = $t.hasReturn && $e.hasReturn;
      }
   ;

loop[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=WHILE e=expression[typeEnv] b=block[proto, typeEnv] expression[typeEnv])
      {
         if ($e.miniType != MiniType.BOOL) {
            throw new RuntimeException("While statement guard must be a boolean expression");
         }
      }
   ;

delete[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=DELETE e=expression[typeEnv])
      {
         if (!($e.miniType instanceof StructType)) {
            throw new RuntimeException("Cannot delete a non-struct type " + $e.miniType.name);
         }
      }
   ;

return_stmt[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = true]
   :  ^(ast=RETURN (e=expression[typeEnv])?)
      {
         // Case where there is no value after a return 
         if ($e.miniType == null && proto.returnType != MiniType.VOID) {
            throw new RuntimeException("Return type mismatch: Expected " 
               + proto.returnType.name + ", found void");
         }
         if ($e.miniType != proto.returnType) {
            throw new RuntimeException("Return type mismatch: Expected " 
               + proto.returnType.name + ", found " + $e.miniType.name);
         }
      }
   ;

invocation_stmt[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   @init { FunctionPrototype proto = null; int argIdx = 0; }
   :  ^(INVOKE id=ID 
         { 
            if (functionDefs.containsKey($id.text)) {
               proto = functionDefs.get($id.text);
            } 
            else {
               throw new RuntimeException("Undefined reference to function: " + $id.text);
            }
         } 
         ^(ARGS (e=expression[typeEnv] 
            {  
               if (argIdx >= proto.argTypes.size()) {
                  throw new RuntimeException("Argument count mismatch expected " + proto.argTypes.size());
               }
               if (proto.argTypes.get(argIdx) != $e.miniType) {
                  if (!(proto.argTypes.get(argIdx) instanceof StructType && $e.miniType == MiniType.NULL)) {
                     throw new RuntimeException("Argument " + argIdx + " type mismatch: Expected " 
                        + proto.argTypes.get(argIdx).name + ", given " + $e.miniType.name);
                  }
               }
               argIdx++;
            })*))
      {
      }
   ;

lvalue[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType]
   :  id=ID
      {
         if (typeEnv.get($id.text) != null) {
            $miniType = typeEnv.get($id.text);
         }
         else {
            if (globalTypeEnv.get($id.text) != null) {
               $miniType = globalTypeEnv.get($id.text);
            }
            else {            
               throw new RuntimeException("Undefined symbol: " + $id.text);
            }
         }
      }
   |  ^(ast=DOT l=lvalue[typeEnv] id=ID)
      {
         if ($l.miniType instanceof StructType) {
            StructType structType = (StructType)$l.miniType;
            if (!structType.fields.containsKey($id.text)) {
               throw new RuntimeException("Identifier is not a member of struct: " + $id.text);
            }
         }
         else {
            throw new RuntimeException("Requested member of non-struct: " + $id.text);
         }
      }
   ;

expression[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   :  ^((ast=AND | ast=OR)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         if ($lft.miniType == MiniType.BOOL && $rht.miniType == MiniType.BOOL) {
            $miniType = MiniType.BOOL;
         }
         else {
            throw new RuntimeException("Type Error: " + $ast.text + " expects BOOL BOOL");
         }
      }
   //Comparisons
   |  ^((ast=EQ | ast=LT | ast=GT | ast=NE | ast=LE | ast=GE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         if ($lft.miniType == MiniType.INT && $rht.miniType == MiniType.INT) {
            $miniType = MiniType.BOOL;
         }
         else {
            throw new RuntimeException("Type Error: " + $ast.text + " expects INT INT");
         }
      }
   //Arithmetic
   |  ^((ast=PLUS | ast=MINUS | ast=TIMES | ast=DIVIDE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         if ($lft.miniType == MiniType.INT && $rht.miniType == MiniType.INT) {
            $miniType = MiniType.INT;
         }
         else {
            throw new RuntimeException("Type Error: " + $ast.text + " expects INT INT");
         }
      }
   |  ^(ast=NOT e=expression[typeEnv])
      {
         if ($e.miniType != MiniType.BOOL) {
            throw new RuntimeException("Type Error: " + $ast.text + " expects BOOL");
         }
         $miniType = MiniType.BOOL;
      }
   |  ^(ast=NEG e=expression[typeEnv])
      {
         if ($e.miniType != MiniType.INT) {
            throw new RuntimeException("Type Error: " + $ast.text + " expects BOOL");
         }
         $miniType = MiniType.INT;
      }
   |  ^(ast=DOT e=expression[typeEnv] id=ID)
      {
         if ($e.miniType instanceof StructType) {
            StructType structType = (StructType)$e.miniType;
            if (!structType.fields.containsKey($id.text)) {
               throw new RuntimeException("Struct does not contain member: " + $id.text);   
            }
            $miniType = structType.fields.get($id.text);
         }
         else {
            throw new RuntimeException("Request for member " + $id.text + " in non-struct");
         }
      }
   |  e=invocation_exp[typeEnv] 
      {
         $miniType = $e.miniType;
      }
   |  id=ID
      {     
         $miniType = typeEnv.get($id.text); 
         if ($miniType == null) {
            $miniType = globalTypeEnv.get($id.text);
            if ($miniType == null) {
               throw new RuntimeException("Undefined identifier: " + $id.text);
            } 
         }
        
      }
   |  i=INTEGER
      {
         $miniType = MiniType.INT;
      }
   |  ast=TRUE
      { 
         $miniType = MiniType.BOOL;
      }
   |  ast=FALSE
      {
         $miniType = MiniType.BOOL;
      }
   |  ^(ast=NEW id=ID)
      {
         if (structTypes.containsKey($id.text)) {
            $miniType = structTypes.get($id.text);
         }
         else {
            throw new RuntimeException("Undefined identifier: " + $id.text);
         }
      }
   |  ast=NULL
      {
         $miniType = MiniType.NULL; 
      }
   ;

invocation_exp[HashMap<String, MiniType> typeEnv]
   returns [MiniType miniType = null]
   @init { FunctionPrototype proto = null; int argIdx = 0; }
   :  ^(INVOKE id=ID
         { 
            if (functionDefs.containsKey($id.text)) {
               proto = functionDefs.get($id.text);
            } 
            else {
               throw new RuntimeException("Undefined reference to function: " + $id.text);
            }
         }
      ^(ARGS (e=expression[typeEnv] 
         {  
            if (proto.argTypes.get(argIdx) != $e.miniType) {
               if (!(proto.argTypes.get(argIdx) instanceof StructType && $e.miniType == MiniType.NULL)) {
                  throw new RuntimeException("Argument " + argIdx + " type mismatch: Expected " 
                        + proto.argTypes.get(argIdx).name + ", given " + $e.miniType.name);
               }
            }
            argIdx++;
         })*))
      {
         $miniType = proto.returnType;
      }
   ;