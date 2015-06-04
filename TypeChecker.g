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

   public static class TypeException extends RuntimeException {
      public TypeException(String msg) {
         super(msg);
      }
   }

   static class FunctionPrototype {
      MiniType returnType;
      List<MiniType> argTypes = new ArrayList<>();
      HashMap<String, MiniType> localTypes = new HashMap<>();
   } 

   public HashMap<String, MiniType> structTypes = new HashMap<>();
   public HashMap<String, MiniType> globalTypeEnv = new HashMap<>();
   public HashMap<String, FunctionPrototype> functionDefs = new HashMap<>();
}


translate
   returns [MiniType miniType = null]
   :  ^(PROGRAM t=types d=declarations[globalTypeEnv] f=functions)
         {
            if (functionDefs.get("main") == null) {
               throw new TypeException("undefined reference to \'main\'");
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
   @init { MiniType.StructType structType = new MiniType.StructType(); }
   :  ^(ast=STRUCT 
         id=ID 
            { 
               structType.name = "Struct " + $id.text;
               if (structTypes.put($id.text, structType) != null) {
                  throw new TypeException("Redefinition of struct: " + $id.text);
               }
            } 
         n=nested_decl[structType])
      {
        
      }
   ;

nested_decl [MiniType.StructType structType]
   returns [MiniType miniType = null]
   @init{  }
   :  (f=field_decl[structType] {  })+
      { $miniType = structType; }
   ;

field_decl [MiniType.StructType structType]
   returns [MiniType miniType = null]
   :  ^(DECL ^(TYPE t=type) id=ID)
      {
         if (structType.fields.put($id.text, $t.miniType) != null) {
            throw new TypeException("Member already decleared: " + $id.text);
         }
         structType.fieldsOrdered.add($id.text);
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
            throw new TypeException("Undefined struct type: " + $id.text);
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
                  throw new TypeException("Redefinition of symbol: " + $id.text);
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
      FunctionPrototype proto = new FunctionPrototype();
      HashMap<String, MiniType> typeEnv = proto.localTypes;
   }
   :  ^(ast=FUN 
         id=ID 
            {
               if (functionDefs.put($id.text, proto) != null) {
                  throw new TypeException("Redefinition of function" + $id.text);
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
         if (!$s.hasReturn && proto.returnType != MiniType.VOID) {
            throw new TypeException("Missing return statement");
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
            throw new TypeException("Redefinition of symbol: " + $id.text);
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
            if (!($l.miniType instanceof MiniType.StructType && $e.miniType == MiniType.NULL)) {
               throw new TypeException("Type mismatch in assignment: " + $l.miniType.name + ", " + $e.miniType.name);               
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
            throw new TypeException("Attempt to print a non-integer");
         }
      }
   ;

read[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=READ l=lvalue[typeEnv])
      {
         if ($l.miniType != MiniType.INT) {
            throw new TypeException("Attempt to read into a non-integer");
         }
      }
   ;

conditional[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=IF g=expression[typeEnv] t=block[proto, typeEnv] (e=block[proto, typeEnv])?)
      {
         if ($g.miniType != MiniType.BOOL) {
            throw new TypeException("If statement guard must be a boolean expression");
         }
         $hasReturn = $t.hasReturn && $e.hasReturn;
      }
   ;

loop[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=WHILE e=expression[typeEnv] b=block[proto, typeEnv] expression[typeEnv])
      {
         if ($e.miniType != MiniType.BOOL) {
            throw new TypeException("While statement guard must be a boolean expression");
         }
      }
   ;

delete[HashMap<String, MiniType> typeEnv]
   returns [boolean hasReturn = false]
   :  ^(ast=DELETE e=expression[typeEnv])
      {
         if (!($e.miniType instanceof MiniType.StructType)) {
            throw new TypeException("Cannot delete a non-struct type " + $e.miniType.name);
         }
      }
   ;

return_stmt[FunctionPrototype proto, HashMap<String, MiniType> typeEnv]
   returns 
   [
      boolean hasReturn = true
   ]
   @init 
   {
      boolean hasExpression = false;
   }
   :  ^(ast=RETURN (e=expression[typeEnv] { hasExpression = true; })?)
      {
         // Case where there is no value after a return 
         if (!hasExpression && proto.returnType != MiniType.VOID) {
            throw new TypeException("Return type mismatch: Expected " 
               + proto.returnType.name + ", found void");
         }
         else if (hasExpression && proto.returnType == MiniType.VOID) {
            throw new TypeException("Void functions may not return an expression.");
         }
         else if (!hasExpression && proto.returnType == MiniType.VOID) {
            // Case where there is no return expression and the function returns void
         }
         else if ($e.miniType != proto.returnType) {
            if (!($e.miniType == MiniType.NULL && proto.returnType instanceof MiniType.StructType)) {
               throw new TypeException("Return type mismatch: Expected " 
                  + proto.returnType.name + ", found " + $e.miniType.name);
            }
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
               throw new TypeException("Undefined reference to function: " + $id.text);
            }
         } 
         ^(ARGS (e=expression[typeEnv] 
            {  
               if (argIdx >= proto.argTypes.size()) {
                  throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
               }
               if (proto.argTypes.get(argIdx) != $e.miniType) {
                  if (!(proto.argTypes.get(argIdx) instanceof MiniType.StructType && $e.miniType == MiniType.NULL)) {
                     throw new TypeException("Argument " + argIdx + " type mismatch: Expected " 
                        + proto.argTypes.get(argIdx).name + ", given " + $e.miniType.name);
                  }
               }
               argIdx++;
            })*))
      {
         if (argIdx != proto.argTypes.size()) {
            throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
         }
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
               throw new TypeException("Undefined symbol: " + $id.text);
            }
         }
      }
   |  ^(ast=DOT l=lvalue[typeEnv] id=ID)
      {
         if ($l.miniType instanceof MiniType.StructType) {
            MiniType.StructType structType = (MiniType.StructType)$l.miniType;
            if (!structType.fields.containsKey($id.text)) {
               throw new TypeException("Identifier is not a member of struct: " + $id.text);
            }
            $miniType = structType.fields.get($id.text);
         }
         else {
            throw new TypeException("Requested member of non-struct: " + $id.text);
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
            throw new TypeException("Type Error: " + $ast.text + " expects BOOL BOOL");
         }
      }
   //Comparisons
   |  ^((ast=EQ | ast=NE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         //Great programming style incoming
         if ($lft.miniType == MiniType.INT && $rht.miniType == MiniType.INT) {
            
         }
         else if ($lft.miniType instanceof MiniType.StructType && $rht.miniType instanceof MiniType.StructType) {
            
         }
         else if ($lft.miniType == MiniType.NULL && $rht.miniType instanceof MiniType.StructType) {
            
         }
         else if ($lft.miniType instanceof MiniType.StructType && $rht.miniType == MiniType.NULL) {
            
         }
         else if ($lft.miniType == MiniType.NULL && $rht.miniType == MiniType.NULL) {
            
         }
         else if ($lft.miniType == MiniType.BOOL && $rht.miniType == MiniType.BOOL) {
            
         }
         else {
            throw new TypeException("Type Error: " + $ast.text + " expects INT INT");
         }
         $miniType = MiniType.BOOL;
      }
   |  ^((ast=LT | ast=GT | ast=LE | ast=GE)
         lft=expression[typeEnv] rht=expression[typeEnv])
      {
         if ($lft.miniType == MiniType.INT && $rht.miniType == MiniType.INT) {
            $miniType = MiniType.BOOL;
         }
         else {
            throw new TypeException("Type Error: " + $ast.text + " expects INT INT");
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
            throw new TypeException("Type Error: " + $ast.text + " expects INT INT");
         }
      }
   |  ^(ast=NOT e=expression[typeEnv])
      {
         if ($e.miniType != MiniType.BOOL) {
            throw new TypeException("Type Error: " + $ast.text + " expects BOOL");
         }
         $miniType = MiniType.BOOL;
      }
   |  ^(ast=NEG e=expression[typeEnv])
      {
         if ($e.miniType != MiniType.INT) {
            throw new TypeException("Type Error: " + $ast.text + " expects BOOL");
         }
         $miniType = MiniType.INT;
      }
   |  ^(ast=DOT e=expression[typeEnv] id=ID)
      {
         if ($e.miniType instanceof MiniType.StructType) {
            MiniType.StructType structType = (MiniType.StructType)$e.miniType;
            if (!structType.fields.containsKey($id.text)) {
               throw new TypeException("Struct does not contain member: " + $id.text);   
            }
            $miniType = structType.fields.get($id.text);
         }
         else {
            throw new TypeException("Request for member " + $id.text + " in non-struct");
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
               throw new TypeException("Undefined identifier: " + $id.text);
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
            throw new TypeException("Undefined identifier: " + $id.text);
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
               throw new TypeException("Undefined reference to function: " + $id.text);
            }
         }
      ^(ARGS (e=expression[typeEnv] 
         {  
            if (proto.argTypes.get(argIdx) != $e.miniType) {
               if (!(proto.argTypes.get(argIdx) instanceof MiniType.StructType && $e.miniType == MiniType.NULL)) {
                  throw new TypeException("Argument " + argIdx + " type mismatch: Expected " 
                        + proto.argTypes.get(argIdx).name + ", given " + $e.miniType.name);
               }
            }
            argIdx++;
         })*))
      {
         if (argIdx != proto.argTypes.size()) {
            throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
         }
         if (proto.returnType == MiniType.VOID) {
            throw new TypeException("Void value from call to " +  $id.text + " not ignored as it ought to be.");
         }
         $miniType = proto.returnType;
      }
   ;