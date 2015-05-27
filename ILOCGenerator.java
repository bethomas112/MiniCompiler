// $ANTLR 3.5.2 ILOCGenerator.g 2015-05-26 20:08:49

   import java.util.List;
   import java.util.LinkedList;
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.HashSet;
   import java.util.Map;
   import java.io.*;
   import javax.json.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ILOCGenerator extends TreeParser {
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


	public ILOCGenerator(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public ILOCGenerator(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return ILOCGenerator.tokenNames; }
	@Override public String getGrammarFileName() { return "ILOCGenerator.g"; }

	   
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
	   private HashMap<String, HashMap<String, MiniType>> functionLocalTypes = new HashMap<>();
	   private HashMap<String, MiniType> globalTypes = new HashMap<>();
	   private HashMap<String, MiniType> functionReturnTypes = new HashMap<>();

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
	   



	// $ANTLR start "translate"
	// ILOCGenerator.g:64:1: translate : ^( PROGRAM t= types d= declarations[null] f= functions ) ;
	public final void translate() throws RecognitionException {
		try {
			// ILOCGenerator.g:65:4: ( ^( PROGRAM t= types d= declarations[null] f= functions ) )
			// ILOCGenerator.g:65:7: ^( PROGRAM t= types d= declarations[null] f= functions )
			{
			match(input,PROGRAM,FOLLOW_PROGRAM_in_translate53); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				pushFollow(FOLLOW_types_in_translate57);
				types();
				state._fsp--;

				pushFollow(FOLLOW_declarations_in_translate61);
				declarations(null);
				state._fsp--;

				pushFollow(FOLLOW_functions_in_translate66);
				functions();
				state._fsp--;

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
	}
	// $ANTLR end "translate"



	// $ANTLR start "types"
	// ILOCGenerator.g:71:1: types : ( ^( TYPES (t= type_decl )* ) |);
	public final void types() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:73:4: ( ^( TYPES (t= type_decl )* ) |)
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
					// ILOCGenerator.g:73:7: ^( TYPES (t= type_decl )* )
					{
					match(input,TYPES,FOLLOW_TYPES_in_types103); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:73:15: (t= type_decl )*
						loop1:
						while (true) {
							int alt1=2;
							int LA1_0 = input.LA(1);
							if ( (LA1_0==STRUCT) ) {
								alt1=1;
							}

							switch (alt1) {
							case 1 :
								// ILOCGenerator.g:73:16: t= type_decl
								{
								pushFollow(FOLLOW_type_decl_in_types108);
								type_decl();
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
					// ILOCGenerator.g:75:7: 
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
	}
	// $ANTLR end "types"



	// $ANTLR start "type_decl"
	// ILOCGenerator.g:78:1: type_decl : ^(ast= STRUCT id= ID n= nested_decl[structType] ) ;
	public final void type_decl() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;

		 MiniType.StructType structType = new MiniType.StructType(); 
		try {
			// ILOCGenerator.g:80:4: ( ^(ast= STRUCT id= ID n= nested_decl[structType] ) )
			// ILOCGenerator.g:80:7: ^(ast= STRUCT id= ID n= nested_decl[structType] )
			{
			ast=(CommonTree)match(input,STRUCT,FOLLOW_STRUCT_in_type_decl156); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_type_decl170); 
			 
			               structType.name = (id!=null?id.getText():null);
			               structTypes.put((id!=null?id.getText():null), structType);               
			            
			pushFollow(FOLLOW_nested_decl_in_type_decl199);
			nested_decl(structType);
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
	}
	// $ANTLR end "type_decl"



	// $ANTLR start "nested_decl"
	// ILOCGenerator.g:92:1: nested_decl[MiniType.StructType structType] : (f= field_decl[structType] )+ ;
	public final void nested_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType f =null;

		  
		try {
			// ILOCGenerator.g:94:4: ( (f= field_decl[structType] )+ )
			// ILOCGenerator.g:94:7: (f= field_decl[structType] )+
			{
			// ILOCGenerator.g:94:7: (f= field_decl[structType] )+
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
					// ILOCGenerator.g:94:8: f= field_decl[structType]
					{
					pushFollow(FOLLOW_field_decl_in_nested_decl237);
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
	// $ANTLR end "nested_decl"



	// $ANTLR start "field_decl"
	// ILOCGenerator.g:97:1: field_decl[MiniType.StructType structType] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final MiniType field_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:99:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:99:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_field_decl268); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_field_decl271); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_field_decl275);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_field_decl280); 
			match(input, Token.UP, null); 


			         structType.fieldsOrdered.add((id!=null?id.getText():null));         
			         structType.fields.put((id!=null?id.getText():null), t);      
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
	// ILOCGenerator.g:107:1: type returns [MiniType miniType = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final MiniType type() throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;

		try {
			// ILOCGenerator.g:109:4: ( INT | BOOL | ^( STRUCT id= ID ) )
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
					// ILOCGenerator.g:109:7: INT
					{
					match(input,INT,FOLLOW_INT_in_type312); 
					 miniType = MiniType.INT; 
					}
					break;
				case 2 :
					// ILOCGenerator.g:110:7: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type322); 
					 miniType = MiniType.BOOL; 
					}
					break;
				case 3 :
					// ILOCGenerator.g:111:7: ^( STRUCT id= ID )
					{
					match(input,STRUCT,FOLLOW_STRUCT_in_type333); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_type337); 
					match(input, Token.UP, null); 

					 
					         miniType = structTypes.get((id!=null?id.getText():null));
					      
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
	// ILOCGenerator.g:117:1: declarations[CFG cfg] : ( ^( DECLS (d= decl_list[cfg] )* ) |);
	public final void declarations(CFG cfg) throws RecognitionException {
		try {
			// ILOCGenerator.g:119:4: ( ^( DECLS (d= decl_list[cfg] )* ) |)
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
					// ILOCGenerator.g:119:7: ^( DECLS (d= decl_list[cfg] )* )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations373); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:119:15: (d= decl_list[cfg] )*
						loop5:
						while (true) {
							int alt5=2;
							int LA5_0 = input.LA(1);
							if ( (LA5_0==DECLLIST) ) {
								alt5=1;
							}

							switch (alt5) {
							case 1 :
								// ILOCGenerator.g:119:16: d= decl_list[cfg]
								{
								pushFollow(FOLLOW_decl_list_in_declarations378);
								decl_list(cfg);
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
					// ILOCGenerator.g:121:7: 
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
	}
	// $ANTLR end "declarations"



	// $ANTLR start "decl_list"
	// ILOCGenerator.g:124:1: decl_list[CFG cfg] : ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) ;
	public final void decl_list(CFG cfg) throws RecognitionException {
		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:125:4: ( ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) )
			// ILOCGenerator.g:125:7: ^( DECLLIST ^( TYPE t= type ) (id= ID )+ )
			{
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list416); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list419); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list423);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			// ILOCGenerator.g:126:10: (id= ID )+
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
					// ILOCGenerator.g:126:11: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_decl_list438); 

					               if (cfg != null) {
					                  cfg.locals.put((id!=null?id.getText():null), Register.newRegister());
					                  cfg.localsOrdered.add((id!=null?id.getText():null));
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
	// ILOCGenerator.g:137:1: functions returns [] : ( ^( FUNCS (f= function )* ) |);
	public final void functions() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:140:4: ( ^( FUNCS (f= function )* ) |)
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
					// ILOCGenerator.g:140:7: ^( FUNCS (f= function )* )
					{
					match(input,FUNCS,FOLLOW_FUNCS_in_functions503); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:140:15: (f= function )*
						loop8:
						while (true) {
							int alt8=2;
							int LA8_0 = input.LA(1);
							if ( (LA8_0==FUN) ) {
								alt8=1;
							}

							switch (alt8) {
							case 1 :
								// ILOCGenerator.g:140:16: f= function
								{
								pushFollow(FOLLOW_function_in_functions508);
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


					         result.cfgs = new ArrayList<>(cfgs);          
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:144:7: 
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
	}
	// $ANTLR end "functions"



	// $ANTLR start "function"
	// ILOCGenerator.g:147:1: function : ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) ;
	public final void function() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;
		BasicBlock p =null;
		BasicBlock s =null;

		 
		      boolean hasStatements = false;
		      CFG cfg = new CFG(structTypes);
		      Register.resetRegisters();
		      BasicBlock exitBlock = new BasicBlock();
		      exitBlock.label = getNextLabel();
		      cfg.exitBlock = exitBlock;
		      exitBlock.addInstruction(new IInstruction.RET());
		   
		try {
			// ILOCGenerator.g:158:4: ( ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) )
			// ILOCGenerator.g:158:7: ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] )
			{
			ast=(CommonTree)match(input,FUN,FOLLOW_FUN_in_function560); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function574); 

			            cfg.localTypes = functionLocalTypes.get((id!=null?id.getText():null));
			         
			pushFollow(FOLLOW_parameters_in_function606);
			p=parameters(cfg);
			state._fsp--;


			            cfg.entryBlock = p;
			            cfg.entryBlock.label = (id!=null?id.getText():null);
			         
			pushFollow(FOLLOW_return_type_in_function633);
			return_type();
			state._fsp--;

			pushFollow(FOLLOW_declarations_in_function646);
			declarations(cfg);
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function660);
			s=statement_list(cfg, cfg.entryBlock);
			state._fsp--;


			            hasStatements = true;
			         
			match(input, Token.UP, null); 


			         if (!hasStatements) {
			            cfg.entryBlock.addNext(exitBlock);
			            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			            jumpi.label = exitBlock.label;
			            cfg.entryBlock.addInstruction(jumpi);
			         }
			         else if (s != null && s.isNextEmpty()) {
			            s.addNext(exitBlock);
			            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			            jumpi.label = exitBlock.label;
			            s.addInstruction(jumpi);
			         }
			         cfgs.add(cfg);
			      
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
	// ILOCGenerator.g:191:1: parameters[CFG cfg] returns [BasicBlock entryBlock = null] : ^( PARAMS (p= param_decl[] )* ) ;
	public final BasicBlock parameters(CFG cfg) throws RecognitionException {
		BasicBlock entryBlock =  null;


		String p =null;

		 
		      int paramNum = 0;
		      BasicBlock block = new BasicBlock();
		   
		try {
			// ILOCGenerator.g:198:4: ( ^( PARAMS (p= param_decl[] )* ) )
			// ILOCGenerator.g:198:7: ^( PARAMS (p= param_decl[] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters718); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:198:16: (p= param_decl[] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==DECL) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// ILOCGenerator.g:198:17: p= param_decl[]
						{
						pushFollow(FOLLOW_param_decl_in_parameters723);
						p=param_decl();
						state._fsp--;

						 
						         Register paramReg = Register.newRegister();
						         cfg.params.put(p, paramNum);
						         cfg.locals.put(p, paramReg);         
						         IInstruction.LOADINARGUMENT loadinargument = new IInstruction.LOADINARGUMENT();
						         loadinargument.variable = p;
						         loadinargument.argIdx = paramNum;
						         loadinargument.dest = paramReg;
						         block.addInstruction(loadinargument);
						         paramNum++;
						      
						}
						break;

					default :
						break loop10;
					}
				}

				match(input, Token.UP, null); 
			}

			 
			         entryBlock = block; 
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return entryBlock;
	}
	// $ANTLR end "parameters"



	// $ANTLR start "param_decl"
	// ILOCGenerator.g:215:1: param_decl[] returns [String paramId = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final String param_decl() throws RecognitionException {
		String paramId =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:217:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:217:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_param_decl769); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_param_decl772); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_param_decl776);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_param_decl781); 
			match(input, Token.UP, null); 


			         paramId = (id!=null?id.getText():null);
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return paramId;
	}
	// $ANTLR end "param_decl"



	// $ANTLR start "return_type"
	// ILOCGenerator.g:223:1: return_type : ^( RETTYPE (r= rtype ) ) ;
	public final void return_type() throws RecognitionException {
		try {
			// ILOCGenerator.g:224:4: ( ^( RETTYPE (r= rtype ) ) )
			// ILOCGenerator.g:224:7: ^( RETTYPE (r= rtype ) )
			{
			match(input,RETTYPE,FOLLOW_RETTYPE_in_return_type807); 
			match(input, Token.DOWN, null); 
			// ILOCGenerator.g:224:17: (r= rtype )
			// ILOCGenerator.g:224:18: r= rtype
			{
			pushFollow(FOLLOW_rtype_in_return_type812);
			rtype();
			state._fsp--;

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
	// $ANTLR end "return_type"



	// $ANTLR start "rtype"
	// ILOCGenerator.g:227:1: rtype : (t= type | VOID );
	public final void rtype() throws RecognitionException {
		MiniType t =null;

		try {
			// ILOCGenerator.g:228:4: (t= type | VOID )
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
					// ILOCGenerator.g:228:7: t= type
					{
					pushFollow(FOLLOW_type_in_rtype834);
					t=type();
					state._fsp--;

					  
					}
					break;
				case 2 :
					// ILOCGenerator.g:229:7: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_rtype844); 
					  
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
	}
	// $ANTLR end "rtype"



	// $ANTLR start "statement"
	// ILOCGenerator.g:232:1: statement[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] ) ;
	public final BasicBlock statement(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		try {
			// ILOCGenerator.g:234:4: ( (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] ) )
			// ILOCGenerator.g:234:7: (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] )
			{
			// ILOCGenerator.g:234:7: (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] )
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
					// ILOCGenerator.g:234:8: s= block[cfg, block]
					{
					pushFollow(FOLLOW_block_in_statement873);
					s=block(cfg, block);
					state._fsp--;

					}
					break;
				case 2 :
					// ILOCGenerator.g:235:10: s= assignment[cfg, block]
					{
					pushFollow(FOLLOW_assignment_in_statement887);
					s=assignment(cfg, block);
					state._fsp--;

					}
					break;
				case 3 :
					// ILOCGenerator.g:236:10: s= print[cfg, block]
					{
					pushFollow(FOLLOW_print_in_statement901);
					s=print(cfg, block);
					state._fsp--;

					}
					break;
				case 4 :
					// ILOCGenerator.g:237:10: s= read[cfg, block]
					{
					pushFollow(FOLLOW_read_in_statement915);
					s=read(cfg, block);
					state._fsp--;

					}
					break;
				case 5 :
					// ILOCGenerator.g:238:10: s= conditional[cfg, block]
					{
					pushFollow(FOLLOW_conditional_in_statement929);
					s=conditional(cfg, block);
					state._fsp--;

					}
					break;
				case 6 :
					// ILOCGenerator.g:239:10: s= loop[cfg, block]
					{
					pushFollow(FOLLOW_loop_in_statement943);
					s=loop(cfg, block);
					state._fsp--;

					}
					break;
				case 7 :
					// ILOCGenerator.g:240:10: s= delete[cfg, block]
					{
					pushFollow(FOLLOW_delete_in_statement957);
					s=delete(cfg, block);
					state._fsp--;

					}
					break;
				case 8 :
					// ILOCGenerator.g:241:10: s= return_stmt[cfg, block]
					{
					pushFollow(FOLLOW_return_stmt_in_statement971);
					s=return_stmt(cfg, block);
					state._fsp--;

					}
					break;
				case 9 :
					// ILOCGenerator.g:242:10: s= invocation_stmt[cfg, block]
					{
					pushFollow(FOLLOW_invocation_stmt_in_statement985);
					s=invocation_stmt(cfg, block);
					state._fsp--;

					}
					break;

			}

			 resultBlock = s; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "statement"



	// $ANTLR start "block"
	// ILOCGenerator.g:247:1: block[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( BLOCK s= statement_list[cfg, block] ) ;
	public final BasicBlock block(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		try {
			// ILOCGenerator.g:249:4: ( ^( BLOCK s= statement_list[cfg, block] ) )
			// ILOCGenerator.g:249:7: ^( BLOCK s= statement_list[cfg, block] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block1027); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block1031);
			s=statement_list(cfg, block);
			state._fsp--;

			match(input, Token.UP, null); 


			         resultBlock = s;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "block"



	// $ANTLR start "statement_list"
	// ILOCGenerator.g:255:1: statement_list[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( STMTS (s= statement[cfg, currentBlock] )* ) ;
	public final BasicBlock statement_list(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		 BasicBlock currentBlock = block; 
		try {
			// ILOCGenerator.g:258:4: ( ^( STMTS (s= statement[cfg, currentBlock] )* ) )
			// ILOCGenerator.g:258:7: ^( STMTS (s= statement[cfg, currentBlock] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list1073); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:258:15: (s= statement[cfg, currentBlock] )*
				loop13:
				while (true) {
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= ASSIGN && LA13_0 <= BLOCK)||LA13_0==DELETE||LA13_0==IF||LA13_0==INVOKE||LA13_0==PRINT||LA13_0==READ||LA13_0==RETURN||LA13_0==WHILE) ) {
						alt13=1;
					}

					switch (alt13) {
					case 1 :
						// ILOCGenerator.g:258:16: s= statement[cfg, currentBlock]
						{
						pushFollow(FOLLOW_statement_in_statement_list1078);
						s=statement(cfg, currentBlock);
						state._fsp--;

						         
						         currentBlock = s;
						      
						}
						break;

					default :
						break loop13;
					}
				}

				match(input, Token.UP, null); 
			}

			 resultBlock = currentBlock; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "statement_list"



	// $ANTLR start "assignment"
	// ILOCGenerator.g:264:1: assignment[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] ) ;
	public final BasicBlock assignment(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope e =null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:266:4: ( ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] ) )
			// ILOCGenerator.g:266:7: ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] )
			{
			ast=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1119); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment1123);
			e=expression(cfg, block);
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment1128);
			l=lvalue(cfg, block, false);
			state._fsp--;

			match(input, Token.UP, null); 


			         if ((l!=null?((ILOCGenerator.lvalue_return)l).wasGlobal:false)) {
			            IInstruction.STOREGLOBAL storeglobal = new IInstruction.STOREGLOBAL();
			            storeglobal.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			            storeglobal.globalName = (l!=null?((ILOCGenerator.lvalue_return)l).globalName:null);
			            block.addInstruction(storeglobal);
			         }
			         else if ((l!=null?((ILOCGenerator.lvalue_return)l).wasField:false)) {
			            IInstruction.STOREAIFIELD storeaifield = new IInstruction.STOREAIFIELD();
			            storeaifield.structType = (l!=null?((ILOCGenerator.lvalue_return)l).structType:null);
			            storeaifield.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			            storeaifield.dest = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
			            storeaifield.fieldName = (l!=null?((ILOCGenerator.lvalue_return)l).fieldName:null);
			            block.addInstruction(storeaifield);
			         }
			         else {
			            IInstruction.MOV mov = new IInstruction.MOV();
			            mov.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			            mov.dest = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
			            block.addInstruction(mov);
			         }
			         resultBlock = block;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "assignment"



	// $ANTLR start "print"
	// ILOCGenerator.g:292:1: print[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) ;
	public final BasicBlock print(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope e =null;

		  
		try {
			// ILOCGenerator.g:295:4: ( ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) )
			// ILOCGenerator.g:295:7: ^(ast= PRINT e= expression[cfg, block] ( ENDL )? )
			{
			ast=(CommonTree)match(input,PRINT,FOLLOW_PRINT_in_print1173); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print1177);
			e=expression(cfg, block);
			state._fsp--;

			// ILOCGenerator.g:295:44: ( ENDL )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ENDL) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// ILOCGenerator.g:295:45: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print1181); 
					  
					}
					break;

			}

			match(input, Token.UP, null); 


			         resultBlock = block;
			         IInstruction.PRINTLN println = new IInstruction.PRINTLN();
			         println.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			         block.addInstruction(println);
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "print"



	// $ANTLR start "read"
	// ILOCGenerator.g:304:1: read[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= READ l= lvalue[cfg, block, false] ) ;
	public final BasicBlock read(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:306:4: ( ^(ast= READ l= lvalue[cfg, block, false] ) )
			// ILOCGenerator.g:306:7: ^(ast= READ l= lvalue[cfg, block, false] )
			{
			ast=(CommonTree)match(input,READ,FOLLOW_READ_in_read1221); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read1225);
			l=lvalue(cfg, block, false);
			state._fsp--;

			match(input, Token.UP, null); 


			         if ((l!=null?((ILOCGenerator.lvalue_return)l).wasGlobal:false)) {
			           IInstruction.COMPUTEGLOBALADDRESS computeglobaladdress = new IInstruction.COMPUTEGLOBALADDRESS();
			           computeglobaladdress.dest = Register.newRegister();
			           computeglobaladdress.globalName = (l!=null?((ILOCGenerator.lvalue_return)l).globalName:null);
			           block.addInstruction(computeglobaladdress);
			           IInstruction.READ read = new IInstruction.READ();
			           read.dest = computeglobaladdress.dest;
			           block.addInstruction(read);
			         }
			         else if ((l!=null?((ILOCGenerator.lvalue_return)l).wasField:false)) {
			            IInstruction.ADDISTRUCT addistruct = new IInstruction.ADDISTRUCT();
			            addistruct.source = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
			            addistruct.fieldName = (l!=null?((ILOCGenerator.lvalue_return)l).fieldName:null);
			            addistruct.dest = Register.newRegister();
			            addistruct.structType = (l!=null?((ILOCGenerator.lvalue_return)l).structType:null);
			            block.addInstruction(addistruct);
			            IInstruction.READ read = new IInstruction.READ();
			            read.dest = addistruct.dest;
			            block.addInstruction(read);
			         }
			         else {
			            IInstruction.ADDILOCAL addilocal = new IInstruction.ADDILOCAL();
			            addilocal.localName = (l!=null?((ILOCGenerator.lvalue_return)l).localName:null);
			            addilocal.dest = Register.newRegister();
			            block.addInstruction(addilocal);
			            IInstruction.READ read = new IInstruction.READ();
			            read.dest = addilocal.dest;
			            block.addInstruction(read);
			            IInstruction.LOADAILOCAL loadailocal = new IInstruction.LOADAILOCAL();
			            loadailocal.localName = (l!=null?((ILOCGenerator.lvalue_return)l).localName:null);
			            loadailocal.dest = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
			            block.addInstruction(loadailocal);
			         }
			         resultBlock = block;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "read"



	// $ANTLR start "conditional"
	// ILOCGenerator.g:345:1: conditional[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) ;
	public final BasicBlock conditional(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope g =null;
		BasicBlock t =null;
		BasicBlock e =null;


		      BasicBlock guardBlock = new BasicBlock();
		      guardBlock.label = getNextLabel();
		      BasicBlock thenBlock = new BasicBlock();
		      thenBlock.label = getNextLabel();
		      BasicBlock elseBlock = new BasicBlock();
		      elseBlock.label = getNextLabel();
		      BasicBlock afterBlock = new BasicBlock();
		      afterBlock.label = getNextLabel();
		      boolean hasElseBlock = false;
		   
		try {
			// ILOCGenerator.g:359:4: ( ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) )
			// ILOCGenerator.g:359:7: ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? )
			{
			ast=(CommonTree)match(input,IF,FOLLOW_IF_in_conditional1274); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional1278);
			g=expression(cfg, guardBlock);
			state._fsp--;

			pushFollow(FOLLOW_block_in_conditional1283);
			t=block(cfg, thenBlock);
			state._fsp--;

			// ILOCGenerator.g:359:70: (e= block[cfg, elseBlock] )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BLOCK) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// ILOCGenerator.g:359:71: e= block[cfg, elseBlock]
					{
					pushFollow(FOLLOW_block_in_conditional1289);
					e=block(cfg, elseBlock);
					state._fsp--;


					            hasElseBlock = true;
					         
					}
					break;

			}

			match(input, Token.UP, null); 


			         block.addNext(guardBlock);
			         guardBlock.addNext(thenBlock);
			         
			         IInstruction.COMPI compi = new IInstruction.COMPI();
			         compi.source = (g!=null?((ILOCGenerator.expression_return)g).resultRegister:null);
			         compi.immediate = 1;
			         guardBlock.addInstruction(compi);

			         IInstruction.CBREQ cbreq = new IInstruction.CBREQ();
			         cbreq.labelA = thenBlock.label;
			         cbreq.labelB = hasElseBlock ? elseBlock.label : afterBlock.label;
			         guardBlock.addInstruction(cbreq);
			         if (t != null) {
			            t.addNext(afterBlock);
			            
			            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			            jumpi.label = afterBlock.label;
			            t.addInstruction(jumpi);
			         }
			         if (hasElseBlock) {
			            guardBlock.addNext(elseBlock);   
			            if (e != null) {
			               e.addNext(afterBlock);

			               IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			               jumpi.label = afterBlock.label;
			               e.addInstruction(jumpi);
			            }
			         }
			         resultBlock = afterBlock;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "conditional"



	// $ANTLR start "loop"
	// ILOCGenerator.g:398:1: loop[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) ;
	public final BasicBlock loop(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope e =null;
		BasicBlock b =null;


		      BasicBlock guardBlock = new BasicBlock();
		      guardBlock.label = getNextLabel();
		      BasicBlock afterBlock = new BasicBlock();
		      afterBlock.label = getNextLabel();
		      BasicBlock bodyBlock = new BasicBlock();
		      bodyBlock.label = getNextLabel();
		   
		try {
			// ILOCGenerator.g:409:4: ( ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) )
			// ILOCGenerator.g:409:7: ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] )
			{
			ast=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_loop1364); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop1368);
			e=expression(cfg, guardBlock);
			state._fsp--;

			pushFollow(FOLLOW_block_in_loop1373);
			b=block(cfg, bodyBlock);
			state._fsp--;

			pushFollow(FOLLOW_expression_in_loop1376);
			expression(cfg, new BasicBlock());
			state._fsp--;

			match(input, Token.UP, null); 


			         block.addNext(guardBlock);
			         guardBlock.addNext(bodyBlock);
			         guardBlock.addNext(afterBlock);

			         IInstruction.COMPI compi = new IInstruction.COMPI();
			         compi.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			         compi.immediate = 1;
			         guardBlock.addInstruction(compi);

			         IInstruction.CBREQ cbreq = new IInstruction.CBREQ();
			         cbreq.labelA = bodyBlock.label;
			         cbreq.labelB = afterBlock.label;
			         guardBlock.addInstruction(cbreq);
			         if (b != null) {
			            b.addNext(guardBlock);

			            IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			            jumpi.label = guardBlock.label;
			            b.addInstruction(jumpi);
			         }
			         resultBlock = afterBlock;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "loop"



	// $ANTLR start "delete"
	// ILOCGenerator.g:435:1: delete[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= DELETE e= expression[cfg, block] ) ;
	public final BasicBlock delete(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope e =null;

		try {
			// ILOCGenerator.g:437:4: ( ^(ast= DELETE e= expression[cfg, block] ) )
			// ILOCGenerator.g:437:7: ^(ast= DELETE e= expression[cfg, block] )
			{
			ast=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete1413); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete1417);
			e=expression(cfg, block);
			state._fsp--;

			match(input, Token.UP, null); 


			         IInstruction.DEL del = new IInstruction.DEL();
			         del.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			         block.addInstruction(del);
			         resultBlock = block;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "delete"



	// $ANTLR start "return_stmt"
	// ILOCGenerator.g:446:1: return_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= RETURN (e= expression[cfg, block] )? ) ;
	public final BasicBlock return_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope e =null;


		      boolean hasExpression = false;
		   
		try {
			// ILOCGenerator.g:452:4: ( ^(ast= RETURN (e= expression[cfg, block] )? ) )
			// ILOCGenerator.g:452:7: ^(ast= RETURN (e= expression[cfg, block] )? )
			{
			ast=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_stmt1466); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:452:20: (e= expression[cfg, block] )?
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==AND||(LA16_0 >= DIVIDE && LA16_0 <= DOT)||(LA16_0 >= EQ && LA16_0 <= FALSE)||(LA16_0 >= GE && LA16_0 <= ID)||(LA16_0 >= INTEGER && LA16_0 <= INVOKE)||LA16_0==LE||(LA16_0 >= LT && LA16_0 <= OR)||LA16_0==PLUS||(LA16_0 >= TIMES && LA16_0 <= TRUE)) ) {
					alt16=1;
				}
				switch (alt16) {
					case 1 :
						// ILOCGenerator.g:452:21: e= expression[cfg, block]
						{
						pushFollow(FOLLOW_expression_in_return_stmt1471);
						e=expression(cfg, block);
						state._fsp--;

						 hasExpression = true; 
						}
						break;

				}

				match(input, Token.UP, null); 
			}


			         if (hasExpression) {
			            IInstruction.STORERET storeret = new IInstruction.STORERET();
			            storeret.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
			            block.addInstruction(storeret);            
			         }
			         block.addNext(cfg.exitBlock);
			         IInstruction.JUMPI jumpi = new IInstruction.JUMPI();
			         jumpi.label = cfg.exitBlock.label;
			         block.addInstruction(jumpi);
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "return_stmt"



	// $ANTLR start "invocation_stmt"
	// ILOCGenerator.g:466:1: invocation_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) ;
	public final BasicBlock invocation_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree id=null;
		TreeRuleReturnScope e =null;

		 
		      int argIdx = 0; 
		      List<IInstruction> instructions = new ArrayList<IInstruction>();
		   
		try {
			// ILOCGenerator.g:473:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) )
			// ILOCGenerator.g:473:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_stmt1522); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_stmt1526); 
			 
			           
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_stmt1551); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:477:17: (e= expression[cfg, block] )*
				loop17:
				while (true) {
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)) ) {
						alt17=1;
					}

					switch (alt17) {
					case 1 :
						// ILOCGenerator.g:477:18: e= expression[cfg, block]
						{
						pushFollow(FOLLOW_expression_in_invocation_stmt1556);
						e=expression(cfg, block);
						state._fsp--;

						  
						            IInstruction.STOREOUTARGUMENT storeoutargument = new IInstruction.STOREOUTARGUMENT();
						            storeoutargument.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
						            storeoutargument.argIdx = argIdx;
						            instructions.add(storeoutargument);
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


			         for (IInstruction instruction : instructions) {
			            block.addInstruction(instruction);
			         }
			         
			         IInstruction.CALL call = new IInstruction.CALL();
			         call.label = (id!=null?id.getText():null);
			         block.addInstruction(call);
			         resultBlock = block;
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultBlock;
	}
	// $ANTLR end "invocation_stmt"


	public static class lvalue_return extends TreeRuleReturnScope {
		public Register resultRegister = null;
		public boolean wasGlobal = false;
		public boolean wasField = false;
		public String fieldName = null;
		public String globalName = null;
		public String localName = null;
		public MiniType.StructType structType = null;
	};


	// $ANTLR start "lvalue"
	// ILOCGenerator.g:497:1: lvalue[CFG cfg, BasicBlock block, boolean nested] returns [\n Register resultRegister = null, \n boolean wasGlobal = false, \n boolean wasField = false,\n String fieldName = null,\n String globalName = null,\n String localName = null,\n MiniType.StructType structType = null\n ] : (id= ID | ^(ast= DOT l= lvalue[cfg, block, true] id= ID ) );
	public final ILOCGenerator.lvalue_return lvalue(CFG cfg, BasicBlock block, boolean nested) throws RecognitionException {
		ILOCGenerator.lvalue_return retval = new ILOCGenerator.lvalue_return();
		retval.start = input.LT(1);

		CommonTree id=null;
		CommonTree ast=null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:508:4: (id= ID | ^(ast= DOT l= lvalue[cfg, block, true] id= ID ) )
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
					// ILOCGenerator.g:508:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1614); 

					         if (cfg.locals.get((id!=null?id.getText():null)) != null) {
					            retval.resultRegister = cfg.locals.get((id!=null?id.getText():null));
					            retval.localName = (id!=null?id.getText():null);
					            MiniType localType = cfg.localTypes.get((id!=null?id.getText():null));
					            if (localType instanceof MiniType.StructType) {
					               retval.structType = (MiniType.StructType)localType;
					            }
					         }
					         else {
					            if (nested == true) {
					               IInstruction.LOADGLOBAL instruction = new IInstruction.LOADGLOBAL();
					               instruction.globalName = (id!=null?id.getText():null);
					               instruction.dest = Register.newRegister();
					               block.addInstruction(instruction);
					               retval.resultRegister = instruction.dest;
					            }
					            retval.wasGlobal = true;
					            retval.globalName = (id!=null?id.getText():null);
					            MiniType globalType = globalTypes.get((id!=null?id.getText():null));
					            if (globalType instanceof MiniType.StructType) {
					               retval.structType = (MiniType.StructType)globalType;
					            }
					         }
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:534:7: ^(ast= DOT l= lvalue[cfg, block, true] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_lvalue1633); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_lvalue_in_lvalue1637);
					l=lvalue(cfg, block, true);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1642); 
					match(input, Token.UP, null); 

					         
					         if (nested) {
					            IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
					            loadaifield.source = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
					            loadaifield.fieldName = (id!=null?id.getText():null);
					            loadaifield.dest = Register.newRegister();
					            loadaifield.structType = (l!=null?((ILOCGenerator.lvalue_return)l).structType:null);
					            block.addInstruction(loadaifield);
					            retval.resultRegister = loadaifield.dest;            
					            MiniType fieldType = (l!=null?((ILOCGenerator.lvalue_return)l).structType:null).fields.get((id!=null?id.getText():null));
					            if (fieldType instanceof MiniType.StructType) {
					               retval.structType = (MiniType.StructType)fieldType;
					            }
					         }
					         else {
					            retval.resultRegister = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
					            retval.structType = (l!=null?((ILOCGenerator.lvalue_return)l).structType:null);
					         }
					         retval.fieldName = (id!=null?id.getText():null);
					         retval.wasField = true;

					      
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
		return retval;
	}
	// $ANTLR end "lvalue"


	public static class expression_return extends TreeRuleReturnScope {
		public Register resultRegister = null;
		public MiniType.StructType structType = null;
	};


	// $ANTLR start "expression"
	// ILOCGenerator.g:559:1: expression[CFG cfg, BasicBlock block] returns [\n Register resultRegister = null,\n MiniType.StructType structType = null\n ] : ( ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) | ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL );
	public final ILOCGenerator.expression_return expression(CFG cfg, BasicBlock block) throws RecognitionException {
		ILOCGenerator.expression_return retval = new ILOCGenerator.expression_return();
		retval.start = input.LT(1);

		CommonTree ast=null;
		CommonTree id=null;
		CommonTree i=null;
		TreeRuleReturnScope lft =null;
		TreeRuleReturnScope rht =null;
		TreeRuleReturnScope e =null;


		      int argIdx = 0; 
		      List<IInstruction> instructions = new ArrayList<IInstruction>();
		   
		try {
			// ILOCGenerator.g:570:4: ( ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) | ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL )
			int alt23=13;
			switch ( input.LA(1) ) {
			case AND:
			case OR:
				{
				alt23=1;
				}
				break;
			case EQ:
			case GE:
			case GT:
			case LE:
			case LT:
			case NE:
				{
				alt23=2;
				}
				break;
			case DIVIDE:
			case MINUS:
			case PLUS:
			case TIMES:
				{
				alt23=3;
				}
				break;
			case NOT:
				{
				alt23=4;
				}
				break;
			case NEG:
				{
				alt23=5;
				}
				break;
			case DOT:
				{
				alt23=6;
				}
				break;
			case INVOKE:
				{
				alt23=7;
				}
				break;
			case ID:
				{
				alt23=8;
				}
				break;
			case INTEGER:
				{
				alt23=9;
				}
				break;
			case TRUE:
				{
				alt23=10;
				}
				break;
			case FALSE:
				{
				alt23=11;
				}
				break;
			case NEW:
				{
				alt23=12;
				}
				break;
			case NULL:
				{
				alt23=13;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// ILOCGenerator.g:570:7: ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:570:9: (ast= AND |ast= OR )
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
							// ILOCGenerator.g:570:10: ast= AND
							{
							ast=(CommonTree)match(input,AND,FOLLOW_AND_in_expression1694); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:570:20: ast= OR
							{
							ast=(CommonTree)match(input,OR,FOLLOW_OR_in_expression1700); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1714);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1719);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         Register leftReg = (lft!=null?((ILOCGenerator.expression_return)lft).resultRegister:null);
					         Register rightReg = (rht!=null?((ILOCGenerator.expression_return)rht).resultRegister:null);
					         Register result = Register.newRegister();
					         
					         if ((ast!=null?ast.getText():null).equals("&&")) {
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
					         retval.resultRegister = result;
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:594:7: ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:594:9: (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE )
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
							// ILOCGenerator.g:594:10: ast= EQ
							{
							ast=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expression1745); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:594:19: ast= LT
							{
							ast=(CommonTree)match(input,LT,FOLLOW_LT_in_expression1751); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:594:28: ast= GT
							{
							ast=(CommonTree)match(input,GT,FOLLOW_GT_in_expression1757); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:594:37: ast= NE
							{
							ast=(CommonTree)match(input,NE,FOLLOW_NE_in_expression1763); 
							}
							break;
						case 5 :
							// ILOCGenerator.g:594:46: ast= LE
							{
							ast=(CommonTree)match(input,LE,FOLLOW_LE_in_expression1769); 
							}
							break;
						case 6 :
							// ILOCGenerator.g:594:55: ast= GE
							{
							ast=(CommonTree)match(input,GE,FOLLOW_GE_in_expression1775); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1789);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1794);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         retval.resultRegister = Register.newRegister();

					         IInstruction.COMP comp = new IInstruction.COMP();
					         comp.sourceA = (lft!=null?((ILOCGenerator.expression_return)lft).resultRegister:null);
					         comp.sourceB = (rht!=null?((ILOCGenerator.expression_return)rht).resultRegister:null);
					         block.addInstruction(comp);

					         IInstruction.LOADI loadi = new IInstruction.LOADI();
					         loadi.immediate = 0;
					         loadi.dest = retval.resultRegister;
					         block.addInstruction(loadi);

					         IInstruction.LOADI loadi1 = new IInstruction.LOADI();
					         loadi1.immediate = 1;
					         loadi1.dest = Register.newRegister();
					         block.addInstruction(loadi1);         
					         switch ((ast!=null?ast.getText():null)) {
					            case "==":
					               IInstruction.MOVEQ moveq = new IInstruction.MOVEQ();
					               moveq.source = loadi1.dest;
					               moveq.dest = retval.resultRegister;
					               block.addInstruction(moveq);
					               break;
					            case "<":
					               IInstruction.MOVLT movlt = new IInstruction.MOVLT();
					               movlt.source = loadi1.dest;
					               movlt.dest = retval.resultRegister;
					               block.addInstruction(movlt);
					               break;
					            case ">":
					               IInstruction.MOVGT movgt = new IInstruction.MOVGT();
					               movgt.source = loadi1.dest;
					               movgt.dest = retval.resultRegister;
					               block.addInstruction(movgt);
					               break;
					            case "!=":
					               IInstruction.MOVNE movne = new IInstruction.MOVNE();
					               movne.source = loadi1.dest;
					               movne.dest = retval.resultRegister;
					               block.addInstruction(movne);
					               break;
					            case "<=":
					               IInstruction.MOVLE movle = new IInstruction.MOVLE();
					               movle.source = loadi1.dest;
					               movle.dest = retval.resultRegister;
					               block.addInstruction(movle);
					               break;
					            case ">=":
					               IInstruction.MOVGE movge = new IInstruction.MOVGE();
					               movge.source = loadi1.dest;
					               movge.dest = retval.resultRegister;
					               block.addInstruction(movge);
					               break;
					         }
					      
					}
					break;
				case 3 :
					// ILOCGenerator.g:653:7: ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:653:9: (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE )
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
							// ILOCGenerator.g:653:10: ast= PLUS
							{
							ast=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expression1820); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:653:21: ast= MINUS
							{
							ast=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression1826); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:653:33: ast= TIMES
							{
							ast=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expression1832); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:653:45: ast= DIVIDE
							{
							ast=(CommonTree)match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1838); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1852);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1857);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         Register leftReg = (lft!=null?((ILOCGenerator.expression_return)lft).resultRegister:null);
					         Register rightReg = (rht!=null?((ILOCGenerator.expression_return)rht).resultRegister:null);
					         Register result = Register.newRegister();
					         if ((ast!=null?ast.getText():null).equals("+")) {
					            IInstruction.ADD instruction = new IInstruction.ADD();
					            instruction.sourceA = leftReg;
					            instruction.sourceB = rightReg;
					            instruction.dest = result;
					            block.addInstruction(instruction);
					         }
					         else if ((ast!=null?ast.getText():null).equals("-")) {
					            IInstruction.SUB instruction = new IInstruction.SUB();
					            instruction.sourceA = leftReg;
					            instruction.sourceB = rightReg;
					            instruction.dest = result;
					            block.addInstruction(instruction);
					         }
					         else if ((ast!=null?ast.getText():null).equals("*")) {
					            IInstruction.MULT instruction = new IInstruction.MULT();
					            instruction.sourceA = leftReg;
					            instruction.sourceB = rightReg;
					            instruction.dest = result;
					            block.addInstruction(instruction);
					         }
					         else if ((ast!=null?ast.getText():null).equals("/")) {
					            IInstruction.DIV instruction = new IInstruction.DIV();
					            instruction.sourceA = leftReg;
					            instruction.sourceB = rightReg;
					            instruction.dest = result;
					            block.addInstruction(instruction);
					         }
					         retval.resultRegister = result;
					      
					}
					break;
				case 4 :
					// ILOCGenerator.g:689:7: ^(ast= NOT e= expression[cfg, block] )
					{
					ast=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expression1878); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1882);
					e=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         IInstruction.XORI xori = new IInstruction.XORI();
					         xori.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
					         xori.immediate = 1;
					         xori.dest = Register.newRegister();
					         block.addInstruction(xori);
					         retval.resultRegister = xori.dest;
					      
					}
					break;
				case 5 :
					// ILOCGenerator.g:698:7: ^(ast= NEG e= expression[cfg, block] )
					{
					ast=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expression1903); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1907);
					e=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         IInstruction.LOADI loadi = new IInstruction.LOADI();
					         loadi.immediate = -1;
					         loadi.dest = Register.newRegister();
					         block.addInstruction(loadi);
					         IInstruction.MULT mult = new IInstruction.MULT();
					         mult.sourceA = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
					         mult.sourceB = loadi.dest;
					         mult.dest = loadi.dest;
					         block.addInstruction(mult);
					         retval.resultRegister = mult.dest;
					      
					}
					break;
				case 6 :
					// ILOCGenerator.g:711:7: ^(ast= DOT e= expression[cfg, block] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_expression1928); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1932);
					e=expression(cfg, block);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1937); 
					match(input, Token.UP, null); 


					         IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
					         loadaifield.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
					         loadaifield.fieldName = (id!=null?id.getText():null);
					         loadaifield.dest = Register.newRegister();
					         loadaifield.structType = (MiniType.StructType)(e!=null?((ILOCGenerator.expression_return)e).structType:null);
					         block.addInstruction(loadaifield);
					         retval.resultRegister = loadaifield.dest;

					         MiniType fieldType = (e!=null?((ILOCGenerator.expression_return)e).structType:null).fields.get((id!=null?id.getText():null));
					         if (fieldType instanceof MiniType.StructType) {
					            retval.structType = (MiniType.StructType)fieldType;
					         }
					      
					}
					break;
				case 7 :
					// ILOCGenerator.g:726:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
					{
					match(input,INVOKE,FOLLOW_INVOKE_in_expression1955); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1959); 
					 
					            
					         
					match(input,ARGS,FOLLOW_ARGS_in_expression1979); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:730:14: (e= expression[cfg, block] )*
						loop22:
						while (true) {
							int alt22=2;
							int LA22_0 = input.LA(1);
							if ( (LA22_0==AND||(LA22_0 >= DIVIDE && LA22_0 <= DOT)||(LA22_0 >= EQ && LA22_0 <= FALSE)||(LA22_0 >= GE && LA22_0 <= ID)||(LA22_0 >= INTEGER && LA22_0 <= INVOKE)||LA22_0==LE||(LA22_0 >= LT && LA22_0 <= OR)||LA22_0==PLUS||(LA22_0 >= TIMES && LA22_0 <= TRUE)) ) {
								alt22=1;
							}

							switch (alt22) {
							case 1 :
								// ILOCGenerator.g:730:15: e= expression[cfg, block]
								{
								pushFollow(FOLLOW_expression_in_expression1984);
								e=expression(cfg, block);
								state._fsp--;

								  
								            IInstruction.STOREOUTARGUMENT storeoutargument = new IInstruction.STOREOUTARGUMENT();
								            storeoutargument.source = (e!=null?((ILOCGenerator.expression_return)e).resultRegister:null);
								            storeoutargument.argIdx = argIdx;
								            instructions.add(storeoutargument);
								            argIdx++;
								         
								}
								break;

							default :
								break loop22;
							}
						}

						match(input, Token.UP, null); 
					}

					match(input, Token.UP, null); 


					         for (IInstruction instruction : instructions) {
					            block.addInstruction(instruction);
					         }
					         IInstruction.CALL call = new IInstruction.CALL();
					         call.label = (id!=null?id.getText():null);
					         block.addInstruction(call);

					         if (functionReturnTypes.get((id!=null?id.getText():null)) instanceof MiniType.StructType) {
					            retval.structType = (MiniType.StructType)functionReturnTypes.get((id!=null?id.getText():null));   
					         } 
					         IInstruction.LOADRET loadret = new IInstruction.LOADRET();
					         loadret.dest = Register.newRegister();
					         block.addInstruction(loadret);
					         retval.resultRegister = loadret.dest;  
					      
					}
					break;
				case 8 :
					// ILOCGenerator.g:754:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2019); 
					              
					         if (cfg.locals.get((id!=null?id.getText():null)) != null) {
					            retval.resultRegister = cfg.locals.get((id!=null?id.getText():null));
					            if (cfg.localTypes.get((id!=null?id.getText():null)) instanceof MiniType.StructType) {
					               retval.structType = (MiniType.StructType)cfg.localTypes.get((id!=null?id.getText():null));
					            }            
					         }
					         else {
					            IInstruction.LOADGLOBAL instruction = new IInstruction.LOADGLOBAL();
					            instruction.globalName = (id!=null?id.getText():null);
					            instruction.dest = Register.newRegister();
					            block.addInstruction(instruction);
					            retval.resultRegister = instruction.dest;            
					            if (globalTypes.get((id!=null?id.getText():null)) instanceof MiniType.StructType) {
					               retval.structType = (MiniType.StructType)globalTypes.get((id!=null?id.getText():null));
					            }
					         }
					      
					}
					break;
				case 9 :
					// ILOCGenerator.g:773:7: i= INTEGER
					{
					i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression2037); 

					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = Integer.parseInt((i!=null?i.getText():null));
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         retval.resultRegister = instruction.dest;
					      
					}
					break;
				case 10 :
					// ILOCGenerator.g:781:7: ast= TRUE
					{
					ast=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression2055); 
					 
					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 1;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         retval.resultRegister = instruction.dest;  
					      
					}
					break;
				case 11 :
					// ILOCGenerator.g:789:7: ast= FALSE
					{
					ast=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression2073); 
					  
					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 0;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         retval.resultRegister = instruction.dest;
					      
					}
					break;
				case 12 :
					// ILOCGenerator.g:797:7: ^(ast= NEW id= ID )
					{
					ast=(CommonTree)match(input,NEW,FOLLOW_NEW_in_expression2092); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2096); 
					match(input, Token.UP, null); 


					         IInstruction.NEW instruction = new IInstruction.NEW();
					         instruction.struct = (MiniType.StructType)structTypes.get((id!=null?id.getText():null));;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         retval.resultRegister = instruction.dest;
					         retval.structType = instruction.struct;
					      
					}
					break;
				case 13 :
					// ILOCGenerator.g:806:7: ast= NULL
					{
					ast=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2115); 

					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 0;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         retval.resultRegister = instruction.dest;
					      
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
		return retval;
	}
	// $ANTLR end "expression"

	// Delegated rules



	public static final BitSet FOLLOW_PROGRAM_in_translate53 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_types_in_translate57 = new BitSet(new long[]{0x0000000000402008L});
	public static final BitSet FOLLOW_declarations_in_translate61 = new BitSet(new long[]{0x0000000000400008L});
	public static final BitSet FOLLOW_functions_in_translate66 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TYPES_in_types103 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_decl_in_types108 = new BitSet(new long[]{0x0010000000000008L});
	public static final BitSet FOLLOW_STRUCT_in_type_decl156 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type_decl170 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_nested_decl_in_type_decl199 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_field_decl_in_nested_decl237 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_DECL_in_field_decl268 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_field_decl271 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_field_decl275 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_field_decl280 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INT_in_type312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type322 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRUCT_in_type333 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_type337 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DECLS_in_declarations373 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_decl_list_in_declarations378 = new BitSet(new long[]{0x0000000000001008L});
	public static final BitSet FOLLOW_DECLLIST_in_decl_list416 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_decl_list419 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_decl_list423 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_decl_list438 = new BitSet(new long[]{0x0000000002000008L});
	public static final BitSet FOLLOW_FUNCS_in_functions503 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_function_in_functions508 = new BitSet(new long[]{0x0000000000200008L});
	public static final BitSet FOLLOW_FUN_in_function560 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_function574 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_parameters_in_function606 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_return_type_in_function633 = new BitSet(new long[]{0x0008000000002000L});
	public static final BitSet FOLLOW_declarations_in_function646 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function660 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters718 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_decl_in_parameters723 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_param_decl769 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_param_decl772 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_param_decl776 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_param_decl781 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETTYPE_in_return_type807 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_rtype_in_return_type812 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_type_in_rtype834 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_rtype844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement887 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement901 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement915 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement929 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_stmt_in_statement971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_stmt_in_statement985 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block1027 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block1031 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_STMTS_in_statement_list1073 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list1078 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment1119 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment1123 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment1128 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRINT_in_print1173 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print1177 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print1181 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read1221 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read1225 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional1274 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional1278 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional1283 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional1289 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop1364 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop1368 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop1373 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop1376 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete1413 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete1417 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_return_stmt1466 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_return_stmt1471 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_stmt1522 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_stmt1526 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_stmt1551 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_stmt1556 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ID_in_lvalue1614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lvalue1633 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_lvalue1637 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue1642 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_AND_in_expression1694 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_OR_in_expression1700 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1714 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1719 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression1745 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LT_in_expression1751 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GT_in_expression1757 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NE_in_expression1763 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LE_in_expression1769 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GE_in_expression1775 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1789 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1794 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression1820 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_MINUS_in_expression1826 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TIMES_in_expression1832 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1838 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1852 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1857 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1878 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1882 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1903 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1907 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1928 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1932 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1937 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_expression1955 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1959 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_expression1979 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1984 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ID_in_expression2019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression2037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression2055 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression2073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression2092 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression2096 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULL_in_expression2115 = new BitSet(new long[]{0x0000000000000002L});
}
