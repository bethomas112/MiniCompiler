// $ANTLR 3.5.2 TypeChecker.g 2015-05-03 20:55:24

   import java.util.HashMap;
   import javax.json.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class TypeChecker extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "ARGS", "ASSIGN", "BLOCK", 
		"BOOL", "COMMA", "COMMENT", "DECL", "DECLLIST", "DECLS", "DELETE", "DIVIDE", 
		"DOT", "ELSE", "ENDL", "EQ", "FALSE", "FUN", "FUNCS", "GE", "GT", "ID", 
		"IF", "INT", "INTEGER", "INVOKE", "LBRACE", "LE", "LPAREN", "LT", "MINUS", 
		"NE", "NEG", "NEW", "NOT", "NULL", "OR", "PARAMS", "PLUS", "PRINT", "PROGRAM", 
		"RBRACE", "READ", "RETTYPE", "RETURN", "RPAREN", "SEMI", "STMTS", "STRUCT", 
		"TIMES", "TRUE", "TYPE", "TYPES", "VOID", "WHILE", "WS"
	};
	public static final int EOF=-1;
	public static final int AND=4;
	public static final int ARGS=5;
	public static final int ASSIGN=6;
	public static final int BLOCK=7;
	public static final int BOOL=8;
	public static final int COMMA=9;
	public static final int COMMENT=10;
	public static final int DECL=11;
	public static final int DECLLIST=12;
	public static final int DECLS=13;
	public static final int DELETE=14;
	public static final int DIVIDE=15;
	public static final int DOT=16;
	public static final int ELSE=17;
	public static final int ENDL=18;
	public static final int EQ=19;
	public static final int FALSE=20;
	public static final int FUN=21;
	public static final int FUNCS=22;
	public static final int GE=23;
	public static final int GT=24;
	public static final int ID=25;
	public static final int IF=26;
	public static final int INT=27;
	public static final int INTEGER=28;
	public static final int INVOKE=29;
	public static final int LBRACE=30;
	public static final int LE=31;
	public static final int LPAREN=32;
	public static final int LT=33;
	public static final int MINUS=34;
	public static final int NE=35;
	public static final int NEG=36;
	public static final int NEW=37;
	public static final int NOT=38;
	public static final int NULL=39;
	public static final int OR=40;
	public static final int PARAMS=41;
	public static final int PLUS=42;
	public static final int PRINT=43;
	public static final int PROGRAM=44;
	public static final int RBRACE=45;
	public static final int READ=46;
	public static final int RETTYPE=47;
	public static final int RETURN=48;
	public static final int RPAREN=49;
	public static final int SEMI=50;
	public static final int STMTS=51;
	public static final int STRUCT=52;
	public static final int TIMES=53;
	public static final int TRUE=54;
	public static final int TYPE=55;
	public static final int TYPES=56;
	public static final int VOID=57;
	public static final int WHILE=58;
	public static final int WS=59;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public TypeChecker(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public TypeChecker(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return TypeChecker.tokenNames; }
	@Override public String getGrammarFileName() { return "TypeChecker.g"; }


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



	// $ANTLR start "translate"
	// TypeChecker.g:40:1: translate returns [MiniType miniType = null] : ^( PROGRAM t= types d= declarations[globalTypeEnv] f= functions ) ;
	public final MiniType translate() throws RecognitionException {
		MiniType miniType =  null;


		MiniType t =null;
		MiniType d =null;
		MiniType f =null;

		try {
			// TypeChecker.g:42:4: ( ^( PROGRAM t= types d= declarations[globalTypeEnv] f= functions ) )
			// TypeChecker.g:42:7: ^( PROGRAM t= types d= declarations[globalTypeEnv] f= functions )
			{
			match(input,PROGRAM,FOLLOW_PROGRAM_in_translate60); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				pushFollow(FOLLOW_types_in_translate64);
				t=types();
				state._fsp--;

				pushFollow(FOLLOW_declarations_in_translate68);
				d=declarations(globalTypeEnv);
				state._fsp--;

				pushFollow(FOLLOW_functions_in_translate73);
				f=functions();
				state._fsp--;

				match(input, Token.UP, null); 
			}


			            if (functionDefs.get("main") == null) {
			               throw new TypeException("undefined reference to \'main\'");
			            }
			         
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "translate"



	// $ANTLR start "types"
	// TypeChecker.g:50:1: types returns [MiniType miniType = null] : ( ^( TYPES (t= type_decl )* ) |);
	public final MiniType types() throws RecognitionException {
		MiniType miniType =  null;


		MiniType t =null;

		  
		try {
			// TypeChecker.g:53:4: ( ^( TYPES (t= type_decl )* ) |)
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==TYPES) ) {
				alt2=1;
			}
			else if ( (LA2_0==UP||LA2_0==DECLS||LA2_0==FUNCS||LA2_0==STMTS) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// TypeChecker.g:53:7: ^( TYPES (t= type_decl )* )
					{
					match(input,TYPES,FOLLOW_TYPES_in_types117); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// TypeChecker.g:53:15: (t= type_decl )*
						loop1:
						while (true) {
							int alt1=2;
							int LA1_0 = input.LA(1);
							if ( (LA1_0==STRUCT) ) {
								alt1=1;
							}

							switch (alt1) {
							case 1 :
								// TypeChecker.g:53:16: t= type_decl
								{
								pushFollow(FOLLOW_type_decl_in_types122);
								t=type_decl();
								state._fsp--;

								  
								}
								break;

							default :
								break loop1;
							}
						}

						match(input, Token.UP, null); 
					}

					  
					}
					break;
				case 2 :
					// TypeChecker.g:55:7: 
					{
					  
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "types"



	// $ANTLR start "type_decl"
	// TypeChecker.g:58:1: type_decl returns [MiniType miniType = null] : ^(ast= STRUCT id= ID n= nested_decl[structType] ) ;
	public final MiniType type_decl() throws RecognitionException {
		MiniType miniType =  null;


		CommonTree ast=null;
		CommonTree id=null;
		MiniType n =null;

		 MiniType.StructType structType = new MiniType.StructType(); 
		try {
			// TypeChecker.g:61:4: ( ^(ast= STRUCT id= ID n= nested_decl[structType] ) )
			// TypeChecker.g:61:7: ^(ast= STRUCT id= ID n= nested_decl[structType] )
			{
			ast=(CommonTree)match(input,STRUCT,FOLLOW_STRUCT_in_type_decl177); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_type_decl191); 
			 
			               structType.name = "Struct " + (id!=null?id.getText():null);
			               if (structTypes.put((id!=null?id.getText():null), structType) != null) {
			                  throw new TypeException("Redefinition of struct: " + (id!=null?id.getText():null));
			               }
			            
			pushFollow(FOLLOW_nested_decl_in_type_decl220);
			n=nested_decl(structType);
			state._fsp--;

			match(input, Token.UP, null); 


			        
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "type_decl"



	// $ANTLR start "nested_decl"
	// TypeChecker.g:75:1: nested_decl[MiniType.StructType structType] returns [MiniType miniType = null] : (f= field_decl[structType] )+ ;
	public final MiniType nested_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType miniType =  null;


		MiniType f =null;

		  
		try {
			// TypeChecker.g:78:4: ( (f= field_decl[structType] )+ )
			// TypeChecker.g:78:7: (f= field_decl[structType] )+
			{
			// TypeChecker.g:78:7: (f= field_decl[structType] )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==DECL) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// TypeChecker.g:78:8: f= field_decl[structType]
					{
					pushFollow(FOLLOW_field_decl_in_nested_decl265);
					f=field_decl(structType);
					state._fsp--;

					  
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			 miniType = structType; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "nested_decl"



	// $ANTLR start "field_decl"
	// TypeChecker.g:82:1: field_decl[MiniType.StructType structType] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final MiniType field_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// TypeChecker.g:84:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// TypeChecker.g:84:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_field_decl304); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_field_decl307); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_field_decl311);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_field_decl316); 
			match(input, Token.UP, null); 


			         if (structType.fields.put((id!=null?id.getText():null), t) != null) {
			            throw new TypeException("Member already decleared: " + (id!=null?id.getText():null));
			         }
			         structType.fieldsOrdered.add((id!=null?id.getText():null));
			         miniType = structType;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "field_decl"



	// $ANTLR start "type"
	// TypeChecker.g:94:1: type returns [MiniType miniType = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final MiniType type() throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;

		try {
			// TypeChecker.g:96:4: ( INT | BOOL | ^( STRUCT id= ID ) )
			int alt4=3;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt4=1;
				}
				break;
			case BOOL:
				{
				alt4=2;
				}
				break;
			case STRUCT:
				{
				alt4=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// TypeChecker.g:96:7: INT
					{
					match(input,INT,FOLLOW_INT_in_type348); 
					 miniType = MiniType.INT; 
					}
					break;
				case 2 :
					// TypeChecker.g:97:7: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type358); 
					 miniType = MiniType.BOOL; 
					}
					break;
				case 3 :
					// TypeChecker.g:98:7: ^( STRUCT id= ID )
					{
					match(input,STRUCT,FOLLOW_STRUCT_in_type369); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_type373); 
					match(input, Token.UP, null); 

					 
					         if (structTypes.get((id!=null?id.getText():null)) != null) {
					            miniType = structTypes.get((id!=null?id.getText():null));
					         }
					         else {
					            throw new TypeException("Undefined struct type: " + (id!=null?id.getText():null));
					         }
					      
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "type"



	// $ANTLR start "declarations"
	// TypeChecker.g:109:1: declarations[HashMap<String, MiniType> typeEnv] returns [MiniType miniType = null] : ( ^( DECLS (d= decl_list[typeEnv] )* ) |);
	public final MiniType declarations(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType =  null;


		try {
			// TypeChecker.g:112:4: ( ^( DECLS (d= decl_list[typeEnv] )* ) |)
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==DECLS) ) {
				alt6=1;
			}
			else if ( (LA6_0==UP||LA6_0==FUNCS||LA6_0==STMTS) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// TypeChecker.g:112:7: ^( DECLS (d= decl_list[typeEnv] )* )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations416); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// TypeChecker.g:112:15: (d= decl_list[typeEnv] )*
						loop5:
						while (true) {
							int alt5=2;
							int LA5_0 = input.LA(1);
							if ( (LA5_0==DECLLIST) ) {
								alt5=1;
							}

							switch (alt5) {
							case 1 :
								// TypeChecker.g:112:16: d= decl_list[typeEnv]
								{
								pushFollow(FOLLOW_decl_list_in_declarations421);
								decl_list(typeEnv);
								state._fsp--;

								}
								break;

							default :
								break loop5;
							}
						}

						match(input, Token.UP, null); 
					}

					  
					}
					break;
				case 2 :
					// TypeChecker.g:114:7: 
					{
					  
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "declarations"



	// $ANTLR start "decl_list"
	// TypeChecker.g:117:1: decl_list[HashMap<String, MiniType> typeEnv] : ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) ;
	public final void decl_list(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		CommonTree id=null;
		MiniType t =null;

		try {
			// TypeChecker.g:118:4: ( ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) )
			// TypeChecker.g:118:7: ^( DECLLIST ^( TYPE t= type ) (id= ID )+ )
			{
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list459); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list462); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list466);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			// TypeChecker.g:119:10: (id= ID )+
			int cnt7=0;
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==ID) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// TypeChecker.g:119:11: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_decl_list481); 

					               if (typeEnv.put((id!=null?id.getText():null), t) != null) {
					                  throw new TypeException("Redefinition of symbol: " + (id!=null?id.getText():null));
					               }
					            
					}
					break;

				default :
					if ( cnt7 >= 1 ) break loop7;
					EarlyExitException eee = new EarlyExitException(7, input);
					throw eee;
				}
				cnt7++;
			}

			match(input, Token.UP, null); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_list"



	// $ANTLR start "functions"
	// TypeChecker.g:129:1: functions returns [MiniType miniType = null] : ( ^( FUNCS (f= function )* ) |);
	public final MiniType functions() throws RecognitionException {
		MiniType miniType =  null;


		  
		try {
			// TypeChecker.g:132:4: ( ^( FUNCS (f= function )* ) |)
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==FUNCS) ) {
				alt9=1;
			}
			else if ( (LA9_0==UP) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// TypeChecker.g:132:7: ^( FUNCS (f= function )* )
					{
					match(input,FUNCS,FOLLOW_FUNCS_in_functions546); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// TypeChecker.g:132:15: (f= function )*
						loop8:
						while (true) {
							int alt8=2;
							int LA8_0 = input.LA(1);
							if ( (LA8_0==FUN) ) {
								alt8=1;
							}

							switch (alt8) {
							case 1 :
								// TypeChecker.g:132:16: f= function
								{
								pushFollow(FOLLOW_function_in_functions551);
								function();
								state._fsp--;

								  
								}
								break;

							default :
								break loop8;
							}
						}

						match(input, Token.UP, null); 
					}

					  
					}
					break;
				case 2 :
					// TypeChecker.g:134:7: 
					{
					  
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "functions"



	// $ANTLR start "function"
	// TypeChecker.g:137:1: function : ^(ast= FUN id= ID p= parameters[proto, typeEnv] r= return_type d= declarations[typeEnv] s= statement_list[proto, typeEnv] ) ;
	public final void function() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;
		MiniType p =null;
		MiniType r =null;
		MiniType d =null;
		boolean s =false;

		    
		      FunctionPrototype proto = new FunctionPrototype();
		      HashMap<String, MiniType> typeEnv = proto.localTypes;
		   
		try {
			// TypeChecker.g:143:4: ( ^(ast= FUN id= ID p= parameters[proto, typeEnv] r= return_type d= declarations[typeEnv] s= statement_list[proto, typeEnv] ) )
			// TypeChecker.g:143:7: ^(ast= FUN id= ID p= parameters[proto, typeEnv] r= return_type d= declarations[typeEnv] s= statement_list[proto, typeEnv] )
			{
			ast=(CommonTree)match(input,FUN,FOLLOW_FUN_in_function603); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function617); 

			               if (functionDefs.put((id!=null?id.getText():null), proto) != null) {
			                  throw new TypeException("Redefinition of function" + (id!=null?id.getText():null));
			               }
			            
			pushFollow(FOLLOW_parameters_in_function645);
			p=parameters(proto, typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_return_type_in_function660);
			r=return_type();
			state._fsp--;


			               proto.returnType = r;
			            
			pushFollow(FOLLOW_declarations_in_function688);
			d=declarations(typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function702);
			s=statement_list(proto, typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         if (!s && proto.returnType != MiniType.VOID) {
			            throw new TypeException("Missing return statement");
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "function"



	// $ANTLR start "parameters"
	// TypeChecker.g:164:1: parameters[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [MiniType miniType = null] : ^( PARAMS (p= param_decl[proto, typeEnv] )* ) ;
	public final MiniType parameters(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType =  null;


		MiniType p =null;

		  
		try {
			// TypeChecker.g:167:4: ( ^( PARAMS (p= param_decl[proto, typeEnv] )* ) )
			// TypeChecker.g:167:7: ^( PARAMS (p= param_decl[proto, typeEnv] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters744); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// TypeChecker.g:167:16: (p= param_decl[proto, typeEnv] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==DECL) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// TypeChecker.g:167:17: p= param_decl[proto, typeEnv]
						{
						pushFollow(FOLLOW_param_decl_in_parameters749);
						p=param_decl(proto, typeEnv);
						state._fsp--;

						 
						}
						break;

					default :
						break loop10;
					}
				}

				match(input, Token.UP, null); 
			}

			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "parameters"



	// $ANTLR start "param_decl"
	// TypeChecker.g:171:1: param_decl[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final MiniType param_decl(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// TypeChecker.g:173:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// TypeChecker.g:173:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_param_decl788); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_param_decl791); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_param_decl795);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_param_decl800); 
			match(input, Token.UP, null); 


			         proto.argTypes.add(t);
			         if (typeEnv.put((id!=null?id.getText():null), t) != null) {
			            throw new TypeException("Redefinition of symbol: " + (id!=null?id.getText():null));
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "param_decl"



	// $ANTLR start "return_type"
	// TypeChecker.g:182:1: return_type returns [MiniType miniType = null] : ^( RETTYPE (r= rtype ) ) ;
	public final MiniType return_type() throws RecognitionException {
		MiniType miniType =  null;


		MiniType r =null;

		try {
			// TypeChecker.g:184:4: ( ^( RETTYPE (r= rtype ) ) )
			// TypeChecker.g:184:7: ^( RETTYPE (r= rtype ) )
			{
			match(input,RETTYPE,FOLLOW_RETTYPE_in_return_type833); 
			match(input, Token.DOWN, null); 
			// TypeChecker.g:184:17: (r= rtype )
			// TypeChecker.g:184:18: r= rtype
			{
			pushFollow(FOLLOW_rtype_in_return_type838);
			r=rtype();
			state._fsp--;

			}

			match(input, Token.UP, null); 

			 miniType = r; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "return_type"



	// $ANTLR start "rtype"
	// TypeChecker.g:187:1: rtype returns [MiniType miniType = null] : (t= type | VOID );
	public final MiniType rtype() throws RecognitionException {
		MiniType miniType =  null;


		MiniType t =null;

		try {
			// TypeChecker.g:189:4: (t= type | VOID )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==BOOL||LA11_0==INT||LA11_0==STRUCT) ) {
				alt11=1;
			}
			else if ( (LA11_0==VOID) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// TypeChecker.g:189:7: t= type
					{
					pushFollow(FOLLOW_type_in_rtype867);
					t=type();
					state._fsp--;

					 miniType = t; 
					}
					break;
				case 2 :
					// TypeChecker.g:190:7: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_rtype877); 
					 miniType = MiniType.VOID; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "rtype"



	// $ANTLR start "statement"
	// TypeChecker.g:193:1: statement[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : (s= block[proto, typeEnv] |s= assignment[typeEnv] |s= print[typeEnv] |s= read[typeEnv] |s= conditional[proto, typeEnv] |s= loop[proto, typeEnv] |s= delete[typeEnv] |s= return_stmt[proto, typeEnv] |s= invocation_stmt[typeEnv] ) ;
	public final boolean statement(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		boolean s =false;

		try {
			// TypeChecker.g:195:4: ( (s= block[proto, typeEnv] |s= assignment[typeEnv] |s= print[typeEnv] |s= read[typeEnv] |s= conditional[proto, typeEnv] |s= loop[proto, typeEnv] |s= delete[typeEnv] |s= return_stmt[proto, typeEnv] |s= invocation_stmt[typeEnv] ) )
			// TypeChecker.g:195:7: (s= block[proto, typeEnv] |s= assignment[typeEnv] |s= print[typeEnv] |s= read[typeEnv] |s= conditional[proto, typeEnv] |s= loop[proto, typeEnv] |s= delete[typeEnv] |s= return_stmt[proto, typeEnv] |s= invocation_stmt[typeEnv] )
			{
			// TypeChecker.g:195:7: (s= block[proto, typeEnv] |s= assignment[typeEnv] |s= print[typeEnv] |s= read[typeEnv] |s= conditional[proto, typeEnv] |s= loop[proto, typeEnv] |s= delete[typeEnv] |s= return_stmt[proto, typeEnv] |s= invocation_stmt[typeEnv] )
			int alt12=9;
			switch ( input.LA(1) ) {
			case BLOCK:
				{
				alt12=1;
				}
				break;
			case ASSIGN:
				{
				alt12=2;
				}
				break;
			case PRINT:
				{
				alt12=3;
				}
				break;
			case READ:
				{
				alt12=4;
				}
				break;
			case IF:
				{
				alt12=5;
				}
				break;
			case WHILE:
				{
				alt12=6;
				}
				break;
			case DELETE:
				{
				alt12=7;
				}
				break;
			case RETURN:
				{
				alt12=8;
				}
				break;
			case INVOKE:
				{
				alt12=9;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// TypeChecker.g:195:8: s= block[proto, typeEnv]
					{
					pushFollow(FOLLOW_block_in_statement906);
					s=block(proto, typeEnv);
					state._fsp--;

					}
					break;
				case 2 :
					// TypeChecker.g:196:10: s= assignment[typeEnv]
					{
					pushFollow(FOLLOW_assignment_in_statement920);
					s=assignment(typeEnv);
					state._fsp--;

					}
					break;
				case 3 :
					// TypeChecker.g:197:10: s= print[typeEnv]
					{
					pushFollow(FOLLOW_print_in_statement934);
					s=print(typeEnv);
					state._fsp--;

					}
					break;
				case 4 :
					// TypeChecker.g:198:10: s= read[typeEnv]
					{
					pushFollow(FOLLOW_read_in_statement948);
					s=read(typeEnv);
					state._fsp--;

					}
					break;
				case 5 :
					// TypeChecker.g:199:10: s= conditional[proto, typeEnv]
					{
					pushFollow(FOLLOW_conditional_in_statement962);
					s=conditional(proto, typeEnv);
					state._fsp--;

					}
					break;
				case 6 :
					// TypeChecker.g:200:10: s= loop[proto, typeEnv]
					{
					pushFollow(FOLLOW_loop_in_statement976);
					s=loop(proto, typeEnv);
					state._fsp--;

					}
					break;
				case 7 :
					// TypeChecker.g:201:10: s= delete[typeEnv]
					{
					pushFollow(FOLLOW_delete_in_statement990);
					s=delete(typeEnv);
					state._fsp--;

					}
					break;
				case 8 :
					// TypeChecker.g:202:10: s= return_stmt[proto, typeEnv]
					{
					pushFollow(FOLLOW_return_stmt_in_statement1004);
					s=return_stmt(proto, typeEnv);
					state._fsp--;

					}
					break;
				case 9 :
					// TypeChecker.g:203:10: s= invocation_stmt[typeEnv]
					{
					pushFollow(FOLLOW_invocation_stmt_in_statement1018);
					s=invocation_stmt(typeEnv);
					state._fsp--;

					}
					break;

			}

			 hasReturn = s; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "statement"



	// $ANTLR start "block"
	// TypeChecker.g:208:1: block[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^( BLOCK s= statement_list[proto, typeEnv] ) ;
	public final boolean block(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		boolean s =false;

		try {
			// TypeChecker.g:210:4: ( ^( BLOCK s= statement_list[proto, typeEnv] ) )
			// TypeChecker.g:210:7: ^( BLOCK s= statement_list[proto, typeEnv] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block1060); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block1064);
			s=statement_list(proto, typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         hasReturn = s;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "block"



	// $ANTLR start "statement_list"
	// TypeChecker.g:216:1: statement_list[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^( STMTS (s= statement[proto, typeEnv] )* ) ;
	public final boolean statement_list(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		boolean s =false;

		  
		try {
			// TypeChecker.g:219:4: ( ^( STMTS (s= statement[proto, typeEnv] )* ) )
			// TypeChecker.g:219:7: ^( STMTS (s= statement[proto, typeEnv] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list1106); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// TypeChecker.g:219:15: (s= statement[proto, typeEnv] )*
				loop13:
				while (true) {
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= ASSIGN && LA13_0 <= BLOCK)||LA13_0==DELETE||LA13_0==IF||LA13_0==INVOKE||LA13_0==PRINT||LA13_0==READ||LA13_0==RETURN||LA13_0==WHILE) ) {
						alt13=1;
					}

					switch (alt13) {
					case 1 :
						// TypeChecker.g:219:16: s= statement[proto, typeEnv]
						{
						pushFollow(FOLLOW_statement_in_statement_list1111);
						s=statement(proto, typeEnv);
						state._fsp--;


						         hasReturn |= s; 
						         
						      
						}
						break;

					default :
						break loop13;
					}
				}

				match(input, Token.UP, null); 
			}

			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "statement_list"



	// $ANTLR start "assignment"
	// TypeChecker.g:226:1: assignment[HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= ASSIGN e= expression[typeEnv] l= lvalue[typeEnv] ) ;
	public final boolean assignment(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType e =null;
		MiniType l =null;

		try {
			// TypeChecker.g:228:4: ( ^(ast= ASSIGN e= expression[typeEnv] l= lvalue[typeEnv] ) )
			// TypeChecker.g:228:7: ^(ast= ASSIGN e= expression[typeEnv] l= lvalue[typeEnv] )
			{
			ast=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1152); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment1156);
			e=expression(typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment1161);
			l=lvalue(typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         if (!(e == l)) {
			            if (!(l instanceof MiniType.StructType && e == MiniType.NULL)) {
			               throw new TypeException("Type mismatch in assignment: " + l.name + ", " + e.name);               
			            }
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "assignment"



	// $ANTLR start "print"
	// TypeChecker.g:238:1: print[HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= PRINT e= expression[typeEnv] ( ENDL )? ) ;
	public final boolean print(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType e =null;

		  
		try {
			// TypeChecker.g:241:4: ( ^(ast= PRINT e= expression[typeEnv] ( ENDL )? ) )
			// TypeChecker.g:241:7: ^(ast= PRINT e= expression[typeEnv] ( ENDL )? )
			{
			ast=(CommonTree)match(input,PRINT,FOLLOW_PRINT_in_print1206); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print1210);
			e=expression(typeEnv);
			state._fsp--;

			// TypeChecker.g:241:41: ( ENDL )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ENDL) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// TypeChecker.g:241:42: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print1214); 
					  
					}
					break;

			}

			match(input, Token.UP, null); 


			         if (e != MiniType.INT) {
			            throw new TypeException("Attempt to print a non-integer");
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "print"



	// $ANTLR start "read"
	// TypeChecker.g:249:1: read[HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= READ l= lvalue[typeEnv] ) ;
	public final boolean read(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType l =null;

		try {
			// TypeChecker.g:251:4: ( ^(ast= READ l= lvalue[typeEnv] ) )
			// TypeChecker.g:251:7: ^(ast= READ l= lvalue[typeEnv] )
			{
			ast=(CommonTree)match(input,READ,FOLLOW_READ_in_read1254); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read1258);
			l=lvalue(typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         if (l != MiniType.INT) {
			            throw new TypeException("Attempt to read into a non-integer");
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "read"



	// $ANTLR start "conditional"
	// TypeChecker.g:259:1: conditional[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= IF g= expression[typeEnv] t= block[proto, typeEnv] (e= block[proto, typeEnv] )? ) ;
	public final boolean conditional(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType g =null;
		boolean t =false;
		boolean e =false;

		try {
			// TypeChecker.g:261:4: ( ^(ast= IF g= expression[typeEnv] t= block[proto, typeEnv] (e= block[proto, typeEnv] )? ) )
			// TypeChecker.g:261:7: ^(ast= IF g= expression[typeEnv] t= block[proto, typeEnv] (e= block[proto, typeEnv] )? )
			{
			ast=(CommonTree)match(input,IF,FOLLOW_IF_in_conditional1295); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional1299);
			g=expression(typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_block_in_conditional1304);
			t=block(proto, typeEnv);
			state._fsp--;

			// TypeChecker.g:261:62: (e= block[proto, typeEnv] )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BLOCK) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// TypeChecker.g:261:63: e= block[proto, typeEnv]
					{
					pushFollow(FOLLOW_block_in_conditional1310);
					e=block(proto, typeEnv);
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null); 


			         if (g != MiniType.BOOL) {
			            throw new TypeException("If statement guard must be a boolean expression");
			         }
			         hasReturn = t && e;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "conditional"



	// $ANTLR start "loop"
	// TypeChecker.g:270:1: loop[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= WHILE e= expression[typeEnv] b= block[proto, typeEnv] expression[typeEnv] ) ;
	public final boolean loop(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType e =null;
		boolean b =false;

		try {
			// TypeChecker.g:272:4: ( ^(ast= WHILE e= expression[typeEnv] b= block[proto, typeEnv] expression[typeEnv] ) )
			// TypeChecker.g:272:7: ^(ast= WHILE e= expression[typeEnv] b= block[proto, typeEnv] expression[typeEnv] )
			{
			ast=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_loop1349); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop1353);
			e=expression(typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_block_in_loop1358);
			b=block(proto, typeEnv);
			state._fsp--;

			pushFollow(FOLLOW_expression_in_loop1361);
			expression(typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         if (e != MiniType.BOOL) {
			            throw new TypeException("While statement guard must be a boolean expression");
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "loop"



	// $ANTLR start "delete"
	// TypeChecker.g:280:1: delete[HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^(ast= DELETE e= expression[typeEnv] ) ;
	public final boolean delete(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;
		MiniType e =null;

		try {
			// TypeChecker.g:282:4: ( ^(ast= DELETE e= expression[typeEnv] ) )
			// TypeChecker.g:282:7: ^(ast= DELETE e= expression[typeEnv] )
			{
			ast=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete1398); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete1402);
			e=expression(typeEnv);
			state._fsp--;

			match(input, Token.UP, null); 


			         if (!(e instanceof MiniType.StructType)) {
			            throw new TypeException("Cannot delete a non-struct type " + e.name);
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "delete"



	// $ANTLR start "return_stmt"
	// TypeChecker.g:290:1: return_stmt[FunctionPrototype proto, HashMap<String, MiniType> typeEnv] returns [\n boolean hasReturn = true\n ] : ^(ast= RETURN (e= expression[typeEnv] )? ) ;
	public final boolean return_stmt(FunctionPrototype proto, HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  true;


		CommonTree ast=null;
		MiniType e =null;


		      boolean hasExpression = false;
		   
		try {
			// TypeChecker.g:299:4: ( ^(ast= RETURN (e= expression[typeEnv] )? ) )
			// TypeChecker.g:299:7: ^(ast= RETURN (e= expression[typeEnv] )? )
			{
			ast=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_stmt1455); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// TypeChecker.g:299:20: (e= expression[typeEnv] )?
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==AND||(LA16_0 >= DIVIDE && LA16_0 <= DOT)||(LA16_0 >= EQ && LA16_0 <= FALSE)||(LA16_0 >= GE && LA16_0 <= ID)||(LA16_0 >= INTEGER && LA16_0 <= INVOKE)||LA16_0==LE||(LA16_0 >= LT && LA16_0 <= OR)||LA16_0==PLUS||(LA16_0 >= TIMES && LA16_0 <= TRUE)) ) {
					alt16=1;
				}
				switch (alt16) {
					case 1 :
						// TypeChecker.g:299:21: e= expression[typeEnv]
						{
						pushFollow(FOLLOW_expression_in_return_stmt1460);
						e=expression(typeEnv);
						state._fsp--;

						 hasExpression = true; 
						}
						break;

				}

				match(input, Token.UP, null); 
			}


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
			         else if (e != proto.returnType) {
			            throw new TypeException("Return type mismatch: Expected " 
			               + proto.returnType.name + ", found " + e.name);
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "return_stmt"



	// $ANTLR start "invocation_stmt"
	// TypeChecker.g:319:1: invocation_stmt[HashMap<String, MiniType> typeEnv] returns [boolean hasReturn = false] : ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) ) ;
	public final boolean invocation_stmt(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree id=null;
		MiniType e =null;

		 FunctionPrototype proto = null; int argIdx = 0; 
		try {
			// TypeChecker.g:322:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) ) )
			// TypeChecker.g:322:7: ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_stmt1507); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_stmt1511); 
			 
			            if (functionDefs.containsKey((id!=null?id.getText():null))) {
			               proto = functionDefs.get((id!=null?id.getText():null));
			            } 
			            else {
			               throw new TypeException("Undefined reference to function: " + (id!=null?id.getText():null));
			            }
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_stmt1536); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// TypeChecker.g:331:17: (e= expression[typeEnv] )*
				loop17:
				while (true) {
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)) ) {
						alt17=1;
					}

					switch (alt17) {
					case 1 :
						// TypeChecker.g:331:18: e= expression[typeEnv]
						{
						pushFollow(FOLLOW_expression_in_invocation_stmt1541);
						e=expression(typeEnv);
						state._fsp--;

						  
						               if (argIdx >= proto.argTypes.size()) {
						                  throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
						               }
						               if (proto.argTypes.get(argIdx) != e) {
						                  if (!(proto.argTypes.get(argIdx) instanceof MiniType.StructType && e == MiniType.NULL)) {
						                     throw new TypeException("Argument " + argIdx + " type mismatch: Expected " 
						                        + proto.argTypes.get(argIdx).name + ", given " + e.name);
						                  }
						               }
						               argIdx++;
						            
						}
						break;

					default :
						break loop17;
					}
				}

				match(input, Token.UP, null); 
			}

			match(input, Token.UP, null); 


			         if (argIdx != proto.argTypes.size()) {
			            throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
			         }
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return hasReturn;
	}
	// $ANTLR end "invocation_stmt"



	// $ANTLR start "lvalue"
	// TypeChecker.g:351:1: lvalue[HashMap<String, MiniType> typeEnv] returns [MiniType miniType] : (id= ID | ^(ast= DOT l= lvalue[typeEnv] id= ID ) );
	public final MiniType lvalue(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType = null;


		CommonTree id=null;
		CommonTree ast=null;
		MiniType l =null;

		try {
			// TypeChecker.g:353:4: (id= ID | ^(ast= DOT l= lvalue[typeEnv] id= ID ) )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ID) ) {
				alt18=1;
			}
			else if ( (LA18_0==DOT) ) {
				alt18=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// TypeChecker.g:353:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1595); 

					         if (typeEnv.get((id!=null?id.getText():null)) != null) {
					            miniType = typeEnv.get((id!=null?id.getText():null));
					         }
					         else {
					            if (globalTypeEnv.get((id!=null?id.getText():null)) != null) {
					               miniType = globalTypeEnv.get((id!=null?id.getText():null));
					            }
					            else {            
					               throw new TypeException("Undefined symbol: " + (id!=null?id.getText():null));
					            }
					         }
					      
					}
					break;
				case 2 :
					// TypeChecker.g:367:7: ^(ast= DOT l= lvalue[typeEnv] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_lvalue1614); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_lvalue_in_lvalue1618);
					l=lvalue(typeEnv);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1623); 
					match(input, Token.UP, null); 


					         if (l instanceof MiniType.StructType) {
					            MiniType.StructType structType = (MiniType.StructType)l;
					            if (!structType.fields.containsKey((id!=null?id.getText():null))) {
					               throw new TypeException("Identifier is not a member of struct: " + (id!=null?id.getText():null));
					            }
					            miniType = structType.fields.get((id!=null?id.getText():null));
					         }
					         else {
					            throw new TypeException("Requested member of non-struct: " + (id!=null?id.getText():null));
					         }
					      
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "lvalue"



	// $ANTLR start "expression"
	// TypeChecker.g:382:1: expression[HashMap<String, MiniType> typeEnv] returns [MiniType miniType = null] : ( ^( (ast= AND |ast= OR ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^(ast= NOT e= expression[typeEnv] ) | ^(ast= NEG e= expression[typeEnv] ) | ^(ast= DOT e= expression[typeEnv] id= ID ) |e= invocation_exp[typeEnv] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL );
	public final MiniType expression(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree ast=null;
		CommonTree id=null;
		CommonTree i=null;
		MiniType lft =null;
		MiniType rht =null;
		MiniType e =null;

		try {
			// TypeChecker.g:384:4: ( ^( (ast= AND |ast= OR ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[typeEnv] rht= expression[typeEnv] ) | ^(ast= NOT e= expression[typeEnv] ) | ^(ast= NEG e= expression[typeEnv] ) | ^(ast= DOT e= expression[typeEnv] id= ID ) |e= invocation_exp[typeEnv] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL )
			int alt22=13;
			switch ( input.LA(1) ) {
			case AND:
			case OR:
				{
				alt22=1;
				}
				break;
			case EQ:
			case GE:
			case GT:
			case LE:
			case LT:
			case NE:
				{
				alt22=2;
				}
				break;
			case DIVIDE:
			case MINUS:
			case PLUS:
			case TIMES:
				{
				alt22=3;
				}
				break;
			case NOT:
				{
				alt22=4;
				}
				break;
			case NEG:
				{
				alt22=5;
				}
				break;
			case DOT:
				{
				alt22=6;
				}
				break;
			case INVOKE:
				{
				alt22=7;
				}
				break;
			case ID:
				{
				alt22=8;
				}
				break;
			case INTEGER:
				{
				alt22=9;
				}
				break;
			case TRUE:
				{
				alt22=10;
				}
				break;
			case FALSE:
				{
				alt22=11;
				}
				break;
			case NEW:
				{
				alt22=12;
				}
				break;
			case NULL:
				{
				alt22=13;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// TypeChecker.g:384:7: ^( (ast= AND |ast= OR ) lft= expression[typeEnv] rht= expression[typeEnv] )
					{
					// TypeChecker.g:384:9: (ast= AND |ast= OR )
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==AND) ) {
						alt19=1;
					}
					else if ( (LA19_0==OR) ) {
						alt19=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 19, 0, input);
						throw nvae;
					}

					switch (alt19) {
						case 1 :
							// TypeChecker.g:384:10: ast= AND
							{
							ast=(CommonTree)match(input,AND,FOLLOW_AND_in_expression1660); 
							}
							break;
						case 2 :
							// TypeChecker.g:384:20: ast= OR
							{
							ast=(CommonTree)match(input,OR,FOLLOW_OR_in_expression1666); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1680);
					lft=expression(typeEnv);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1685);
					rht=expression(typeEnv);
					state._fsp--;

					match(input, Token.UP, null); 


					         if (lft == MiniType.BOOL && rht == MiniType.BOOL) {
					            miniType = MiniType.BOOL;
					         }
					         else {
					            throw new TypeException("Type Error: " + (ast!=null?ast.getText():null) + " expects BOOL BOOL");
					         }
					      
					}
					break;
				case 2 :
					// TypeChecker.g:395:7: ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[typeEnv] rht= expression[typeEnv] )
					{
					// TypeChecker.g:395:9: (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE )
					int alt20=6;
					switch ( input.LA(1) ) {
					case EQ:
						{
						alt20=1;
						}
						break;
					case LT:
						{
						alt20=2;
						}
						break;
					case GT:
						{
						alt20=3;
						}
						break;
					case NE:
						{
						alt20=4;
						}
						break;
					case LE:
						{
						alt20=5;
						}
						break;
					case GE:
						{
						alt20=6;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 20, 0, input);
						throw nvae;
					}
					switch (alt20) {
						case 1 :
							// TypeChecker.g:395:10: ast= EQ
							{
							ast=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expression1711); 
							}
							break;
						case 2 :
							// TypeChecker.g:395:19: ast= LT
							{
							ast=(CommonTree)match(input,LT,FOLLOW_LT_in_expression1717); 
							}
							break;
						case 3 :
							// TypeChecker.g:395:28: ast= GT
							{
							ast=(CommonTree)match(input,GT,FOLLOW_GT_in_expression1723); 
							}
							break;
						case 4 :
							// TypeChecker.g:395:37: ast= NE
							{
							ast=(CommonTree)match(input,NE,FOLLOW_NE_in_expression1729); 
							}
							break;
						case 5 :
							// TypeChecker.g:395:46: ast= LE
							{
							ast=(CommonTree)match(input,LE,FOLLOW_LE_in_expression1735); 
							}
							break;
						case 6 :
							// TypeChecker.g:395:55: ast= GE
							{
							ast=(CommonTree)match(input,GE,FOLLOW_GE_in_expression1741); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1755);
					lft=expression(typeEnv);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1760);
					rht=expression(typeEnv);
					state._fsp--;

					match(input, Token.UP, null); 


					         if (lft == MiniType.INT && rht == MiniType.INT) {
					            miniType = MiniType.BOOL;
					         }
					         else {
					            throw new TypeException("Type Error: " + (ast!=null?ast.getText():null) + " expects INT INT");
					         }
					      
					}
					break;
				case 3 :
					// TypeChecker.g:406:7: ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[typeEnv] rht= expression[typeEnv] )
					{
					// TypeChecker.g:406:9: (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE )
					int alt21=4;
					switch ( input.LA(1) ) {
					case PLUS:
						{
						alt21=1;
						}
						break;
					case MINUS:
						{
						alt21=2;
						}
						break;
					case TIMES:
						{
						alt21=3;
						}
						break;
					case DIVIDE:
						{
						alt21=4;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 21, 0, input);
						throw nvae;
					}
					switch (alt21) {
						case 1 :
							// TypeChecker.g:406:10: ast= PLUS
							{
							ast=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expression1786); 
							}
							break;
						case 2 :
							// TypeChecker.g:406:21: ast= MINUS
							{
							ast=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression1792); 
							}
							break;
						case 3 :
							// TypeChecker.g:406:33: ast= TIMES
							{
							ast=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expression1798); 
							}
							break;
						case 4 :
							// TypeChecker.g:406:45: ast= DIVIDE
							{
							ast=(CommonTree)match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1804); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1818);
					lft=expression(typeEnv);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1823);
					rht=expression(typeEnv);
					state._fsp--;

					match(input, Token.UP, null); 


					         if (lft == MiniType.INT && rht == MiniType.INT) {
					            miniType = MiniType.INT;
					         }
					         else {
					            throw new TypeException("Type Error: " + (ast!=null?ast.getText():null) + " expects INT INT");
					         }
					      
					}
					break;
				case 4 :
					// TypeChecker.g:416:7: ^(ast= NOT e= expression[typeEnv] )
					{
					ast=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expression1844); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1848);
					e=expression(typeEnv);
					state._fsp--;

					match(input, Token.UP, null); 


					         if (e != MiniType.BOOL) {
					            throw new TypeException("Type Error: " + (ast!=null?ast.getText():null) + " expects BOOL");
					         }
					         miniType = MiniType.BOOL;
					      
					}
					break;
				case 5 :
					// TypeChecker.g:423:7: ^(ast= NEG e= expression[typeEnv] )
					{
					ast=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expression1869); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1873);
					e=expression(typeEnv);
					state._fsp--;

					match(input, Token.UP, null); 


					         if (e != MiniType.INT) {
					            throw new TypeException("Type Error: " + (ast!=null?ast.getText():null) + " expects BOOL");
					         }
					         miniType = MiniType.INT;
					      
					}
					break;
				case 6 :
					// TypeChecker.g:430:7: ^(ast= DOT e= expression[typeEnv] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_expression1894); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1898);
					e=expression(typeEnv);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1903); 
					match(input, Token.UP, null); 


					         if (e instanceof MiniType.StructType) {
					            MiniType.StructType structType = (MiniType.StructType)e;
					            if (!structType.fields.containsKey((id!=null?id.getText():null))) {
					               throw new TypeException("Struct does not contain member: " + (id!=null?id.getText():null));   
					            }
					            miniType = structType.fields.get((id!=null?id.getText():null));
					         }
					         else {
					            throw new TypeException("Request for member " + (id!=null?id.getText():null) + " in non-struct");
					         }
					      
					}
					break;
				case 7 :
					// TypeChecker.g:443:7: e= invocation_exp[typeEnv]
					{
					pushFollow(FOLLOW_invocation_exp_in_expression1922);
					e=invocation_exp(typeEnv);
					state._fsp--;


					         miniType = e;
					      
					}
					break;
				case 8 :
					// TypeChecker.g:447:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1942); 
					     
					         miniType = typeEnv.get((id!=null?id.getText():null)); 
					         if (miniType == null) {
					            miniType = globalTypeEnv.get((id!=null?id.getText():null));
					            if (miniType == null) {
					               throw new TypeException("Undefined identifier: " + (id!=null?id.getText():null));
					            } 
					         }
					        
					      
					}
					break;
				case 9 :
					// TypeChecker.g:458:7: i= INTEGER
					{
					i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1960); 

					         miniType = MiniType.INT;
					      
					}
					break;
				case 10 :
					// TypeChecker.g:462:7: ast= TRUE
					{
					ast=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression1978); 
					 
					         miniType = MiniType.BOOL;
					      
					}
					break;
				case 11 :
					// TypeChecker.g:466:7: ast= FALSE
					{
					ast=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression1996); 

					         miniType = MiniType.BOOL;
					      
					}
					break;
				case 12 :
					// TypeChecker.g:470:7: ^(ast= NEW id= ID )
					{
					ast=(CommonTree)match(input,NEW,FOLLOW_NEW_in_expression2015); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2019); 
					match(input, Token.UP, null); 


					         if (structTypes.containsKey((id!=null?id.getText():null))) {
					            miniType = structTypes.get((id!=null?id.getText():null));
					         }
					         else {
					            throw new TypeException("Undefined identifier: " + (id!=null?id.getText():null));
					         }
					      
					}
					break;
				case 13 :
					// TypeChecker.g:479:7: ast= NULL
					{
					ast=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2038); 

					         miniType = MiniType.NULL; 
					      
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "expression"



	// $ANTLR start "invocation_exp"
	// TypeChecker.g:485:1: invocation_exp[HashMap<String, MiniType> typeEnv] returns [MiniType miniType = null] : ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) ) ;
	public final MiniType invocation_exp(HashMap<String, MiniType> typeEnv) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType e =null;

		 FunctionPrototype proto = null; int argIdx = 0; 
		try {
			// TypeChecker.g:488:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) ) )
			// TypeChecker.g:488:7: ^( INVOKE id= ID ^( ARGS (e= expression[typeEnv] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_exp2079); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_exp2083); 
			 
			            if (functionDefs.containsKey((id!=null?id.getText():null))) {
			               proto = functionDefs.get((id!=null?id.getText():null));
			            } 
			            else {
			               throw new TypeException("Undefined reference to function: " + (id!=null?id.getText():null));
			            }
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_exp2103); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// TypeChecker.g:497:14: (e= expression[typeEnv] )*
				loop23:
				while (true) {
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==AND||(LA23_0 >= DIVIDE && LA23_0 <= DOT)||(LA23_0 >= EQ && LA23_0 <= FALSE)||(LA23_0 >= GE && LA23_0 <= ID)||(LA23_0 >= INTEGER && LA23_0 <= INVOKE)||LA23_0==LE||(LA23_0 >= LT && LA23_0 <= OR)||LA23_0==PLUS||(LA23_0 >= TIMES && LA23_0 <= TRUE)) ) {
						alt23=1;
					}

					switch (alt23) {
					case 1 :
						// TypeChecker.g:497:15: e= expression[typeEnv]
						{
						pushFollow(FOLLOW_expression_in_invocation_exp2108);
						e=expression(typeEnv);
						state._fsp--;

						  
						            if (proto.argTypes.get(argIdx) != e) {
						               if (!(proto.argTypes.get(argIdx) instanceof MiniType.StructType && e == MiniType.NULL)) {
						                  throw new TypeException("Argument " + argIdx + " type mismatch: Expected " 
						                        + proto.argTypes.get(argIdx).name + ", given " + e.name);
						               }
						            }
						            argIdx++;
						         
						}
						break;

					default :
						break loop23;
					}
				}

				match(input, Token.UP, null); 
			}

			match(input, Token.UP, null); 


			         if (argIdx != proto.argTypes.size()) {
			            throw new TypeException("Argument count mismatch expected " + proto.argTypes.size());
			         }
			         if (proto.returnType == MiniType.VOID) {
			            throw new TypeException("Void value from call to " +  (id!=null?id.getText():null) + " not ignored as it ought to be.");
			         }
			         miniType = proto.returnType;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return miniType;
	}
	// $ANTLR end "invocation_exp"

	// Delegated rules



	public static final BitSet FOLLOW_PROGRAM_in_translate60 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_types_in_translate64 = new BitSet(new long[]{0x0000000000402008L});
	public static final BitSet FOLLOW_declarations_in_translate68 = new BitSet(new long[]{0x0000000000400008L});
	public static final BitSet FOLLOW_functions_in_translate73 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TYPES_in_types117 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_decl_in_types122 = new BitSet(new long[]{0x0010000000000008L});
	public static final BitSet FOLLOW_STRUCT_in_type_decl177 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type_decl191 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_nested_decl_in_type_decl220 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_field_decl_in_nested_decl265 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_DECL_in_field_decl304 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_field_decl307 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_field_decl311 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_field_decl316 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INT_in_type348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type369 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type373 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DECLS_in_declarations416 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_decl_list_in_declarations421 = new BitSet(new long[]{0x0000000000001008L});
	public static final BitSet FOLLOW_DECLLIST_in_decl_list459 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decl_list462 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decl_list466 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_decl_list481 = new BitSet(new long[]{0x0000000002000008L});
	public static final BitSet FOLLOW_FUNCS_in_functions546 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_function_in_functions551 = new BitSet(new long[]{0x0000000000200008L});
	public static final BitSet FOLLOW_FUN_in_function603 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_function617 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_parameters_in_function645 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_return_type_in_function660 = new BitSet(new long[]{0x0008000000002000L});
	public static final BitSet FOLLOW_declarations_in_function688 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function702 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters744 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_decl_in_parameters749 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_param_decl788 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_param_decl791 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_param_decl795 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_param_decl800 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETTYPE_in_return_type833 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_rtype_in_return_type838 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_type_in_rtype867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_rtype877 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement906 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement920 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement934 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement962 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement976 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_stmt_in_statement1004 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_stmt_in_statement1018 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block1060 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block1064 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_STMTS_in_statement_list1106 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list1111 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment1152 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment1156 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment1161 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRINT_in_print1206 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print1210 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print1214 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read1254 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read1258 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional1295 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional1299 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional1304 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional1310 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop1349 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop1353 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop1358 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop1361 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete1398 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete1402 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_return_stmt1455 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_return_stmt1460 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_stmt1507 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_stmt1511 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_stmt1536 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_stmt1541 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ID_in_lvalue1595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lvalue1614 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_lvalue1618 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue1623 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_AND_in_expression1660 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_OR_in_expression1666 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1680 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1685 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression1711 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LT_in_expression1717 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GT_in_expression1723 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NE_in_expression1729 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LE_in_expression1735 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GE_in_expression1741 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1755 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1760 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression1786 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_MINUS_in_expression1792 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TIMES_in_expression1798 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1804 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1818 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1823 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1844 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1848 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1869 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1873 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1894 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1898 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1903 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_invocation_exp_in_expression1922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expression1942 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression1960 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression1978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression1996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression2015 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression2019 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULL_in_expression2038 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_exp2079 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_exp2083 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_exp2103 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_exp2108 = new BitSet(new long[]{0x006005FEB3998018L});
}
