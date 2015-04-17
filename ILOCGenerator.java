// $ANTLR 3.5.2 ILOCGenerator.g 2015-04-17 11:55:28

   import java.util.ArrayList;
   import java.util.HashMap;
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



	// $ANTLR start "translate"
	// ILOCGenerator.g:61:1: translate : ^( PROGRAM t= types d= declarations[] f= functions ) ;
	public final void translate() throws RecognitionException {
		try {
			// ILOCGenerator.g:62:4: ( ^( PROGRAM t= types d= declarations[] f= functions ) )
			// ILOCGenerator.g:62:7: ^( PROGRAM t= types d= declarations[] f= functions )
			{
			match(input,PROGRAM,FOLLOW_PROGRAM_in_translate53); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				pushFollow(FOLLOW_types_in_translate57);
				types();
				state._fsp--;

				pushFollow(FOLLOW_declarations_in_translate61);
				declarations();
				state._fsp--;

				pushFollow(FOLLOW_functions_in_translate66);
				functions();
				state._fsp--;

				match(input, Token.UP, null); 
			}


			            for (MiniType type : structTypes.values()) {
			               System.out.println(type);
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
	// ILOCGenerator.g:70:1: types : ( ^( TYPES (t= type_decl )* ) |);
	public final void types() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:72:4: ( ^( TYPES (t= type_decl )* ) |)
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
					// ILOCGenerator.g:72:7: ^( TYPES (t= type_decl )* )
					{
					match(input,TYPES,FOLLOW_TYPES_in_types103); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:72:15: (t= type_decl )*
						loop1:
						while (true) {
							int alt1=2;
							int LA1_0 = input.LA(1);
							if ( (LA1_0==STRUCT) ) {
								alt1=1;
							}

							switch (alt1) {
							case 1 :
								// ILOCGenerator.g:72:16: t= type_decl
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
					// ILOCGenerator.g:74:7: 
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
	// ILOCGenerator.g:77:1: type_decl : ^(ast= STRUCT id= ID n= nested_decl[structType] ) ;
	public final void type_decl() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;

		 MiniType.StructType structType = new MiniType.StructType(); 
		try {
			// ILOCGenerator.g:79:4: ( ^(ast= STRUCT id= ID n= nested_decl[structType] ) )
			// ILOCGenerator.g:79:7: ^(ast= STRUCT id= ID n= nested_decl[structType] )
			{
			ast=(CommonTree)match(input,STRUCT,FOLLOW_STRUCT_in_type_decl156); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_type_decl170); 
			 
			               structType.name = "Struct " + (id!=null?id.getText():null);
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
	// ILOCGenerator.g:91:1: nested_decl[MiniType.StructType structType] : (f= field_decl[structType] )+ ;
	public final void nested_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType f =null;

		  
		try {
			// ILOCGenerator.g:93:4: ( (f= field_decl[structType] )+ )
			// ILOCGenerator.g:93:7: (f= field_decl[structType] )+
			{
			// ILOCGenerator.g:93:7: (f= field_decl[structType] )+
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
					// ILOCGenerator.g:93:8: f= field_decl[structType]
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
	// ILOCGenerator.g:96:1: field_decl[MiniType.StructType structType] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final MiniType field_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:98:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:98:7: ^( DECL ^( TYPE t= type ) id= ID )
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
	// ILOCGenerator.g:106:1: type returns [MiniType miniType = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final MiniType type() throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;

		try {
			// ILOCGenerator.g:108:4: ( INT | BOOL | ^( STRUCT id= ID ) )
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
					// ILOCGenerator.g:108:7: INT
					{
					match(input,INT,FOLLOW_INT_in_type312); 
					 miniType = MiniType.INT; 
					}
					break;
				case 2 :
					// ILOCGenerator.g:109:7: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type322); 
					 miniType = MiniType.BOOL; 
					}
					break;
				case 3 :
					// ILOCGenerator.g:110:7: ^( STRUCT id= ID )
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
	// ILOCGenerator.g:116:1: declarations[] : ( ^( DECLS (d= decl_list[] )* ) |);
	public final void declarations() throws RecognitionException {
		try {
			// ILOCGenerator.g:118:4: ( ^( DECLS (d= decl_list[] )* ) |)
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
					// ILOCGenerator.g:118:7: ^( DECLS (d= decl_list[] )* )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations373); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:118:15: (d= decl_list[] )*
						loop5:
						while (true) {
							int alt5=2;
							int LA5_0 = input.LA(1);
							if ( (LA5_0==DECLLIST) ) {
								alt5=1;
							}

							switch (alt5) {
							case 1 :
								// ILOCGenerator.g:118:16: d= decl_list[]
								{
								pushFollow(FOLLOW_decl_list_in_declarations378);
								decl_list();
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
					// ILOCGenerator.g:120:7: 
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
	// ILOCGenerator.g:123:1: decl_list[] : ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) ;
	public final void decl_list() throws RecognitionException {
		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:124:4: ( ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) )
			// ILOCGenerator.g:124:7: ^( DECLLIST ^( TYPE t= type ) (id= ID )+ )
			{
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list416); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list419); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list423);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			// ILOCGenerator.g:125:10: (id= ID )+
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
					// ILOCGenerator.g:125:11: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_decl_list438); 

					               
					            
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
	// ILOCGenerator.g:133:1: functions returns [] : ( ^( FUNCS (f= function )* ) |);
	public final void functions() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:136:4: ( ^( FUNCS (f= function )* ) |)
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
					// ILOCGenerator.g:136:7: ^( FUNCS (f= function )* )
					{
					match(input,FUNCS,FOLLOW_FUNCS_in_functions503); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:136:15: (f= function )*
						loop8:
						while (true) {
							int alt8=2;
							int LA8_0 = input.LA(1);
							if ( (LA8_0==FUN) ) {
								alt8=1;
							}

							switch (alt8) {
							case 1 :
								// ILOCGenerator.g:136:16: f= function
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

					  
					}
					break;
				case 2 :
					// ILOCGenerator.g:138:7: 
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
	// ILOCGenerator.g:141:1: function : ^(ast= FUN id= ID p= parameters[] r= return_type d= declarations[] s= statement_list[] ) ;
	public final void function() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;

		 
		      
		   
		try {
			// ILOCGenerator.g:146:4: ( ^(ast= FUN id= ID p= parameters[] r= return_type d= declarations[] s= statement_list[] ) )
			// ILOCGenerator.g:146:7: ^(ast= FUN id= ID p= parameters[] r= return_type d= declarations[] s= statement_list[] )
			{
			ast=(CommonTree)match(input,FUN,FOLLOW_FUN_in_function560); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function574); 

			               
			            
			pushFollow(FOLLOW_parameters_in_function602);
			parameters();
			state._fsp--;

			pushFollow(FOLLOW_return_type_in_function617);
			return_type();
			state._fsp--;


			               
			            
			pushFollow(FOLLOW_declarations_in_function645);
			declarations();
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function659);
			statement_list();
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
	// $ANTLR end "function"



	// $ANTLR start "parameters"
	// ILOCGenerator.g:163:1: parameters[] returns [] : ^( PARAMS (p= param_decl[] )* ) ;
	public final void parameters() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:166:4: ( ^( PARAMS (p= param_decl[] )* ) )
			// ILOCGenerator.g:166:7: ^( PARAMS (p= param_decl[] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters701); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:166:16: (p= param_decl[] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==DECL) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// ILOCGenerator.g:166:17: p= param_decl[]
						{
						pushFollow(FOLLOW_param_decl_in_parameters706);
						param_decl();
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
	}
	// $ANTLR end "parameters"



	// $ANTLR start "param_decl"
	// ILOCGenerator.g:170:1: param_decl[] returns [] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final void param_decl() throws RecognitionException {
		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:172:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:172:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_param_decl745); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_param_decl748); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_param_decl752);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_param_decl757); 
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
	// $ANTLR end "param_decl"



	// $ANTLR start "return_type"
	// ILOCGenerator.g:178:1: return_type returns [] : ^( RETTYPE (r= rtype ) ) ;
	public final void return_type() throws RecognitionException {
		try {
			// ILOCGenerator.g:180:4: ( ^( RETTYPE (r= rtype ) ) )
			// ILOCGenerator.g:180:7: ^( RETTYPE (r= rtype ) )
			{
			match(input,RETTYPE,FOLLOW_RETTYPE_in_return_type790); 
			match(input, Token.DOWN, null); 
			// ILOCGenerator.g:180:17: (r= rtype )
			// ILOCGenerator.g:180:18: r= rtype
			{
			pushFollow(FOLLOW_rtype_in_return_type795);
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
	// ILOCGenerator.g:183:1: rtype returns [] : (t= type | VOID );
	public final void rtype() throws RecognitionException {
		MiniType t =null;

		try {
			// ILOCGenerator.g:185:4: (t= type | VOID )
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
					// ILOCGenerator.g:185:7: t= type
					{
					pushFollow(FOLLOW_type_in_rtype824);
					t=type();
					state._fsp--;

					  
					}
					break;
				case 2 :
					// ILOCGenerator.g:186:7: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_rtype834); 
					  
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
	// ILOCGenerator.g:189:1: statement[] returns [] : (s= block[] |s= assignment[] |s= print[] |s= read[] |s= conditional[] |s= loop[] |s= delete[] |s= return_stmt[] |s= invocation_stmt[] ) ;
	public final void statement() throws RecognitionException {
		boolean s =false;

		try {
			// ILOCGenerator.g:191:4: ( (s= block[] |s= assignment[] |s= print[] |s= read[] |s= conditional[] |s= loop[] |s= delete[] |s= return_stmt[] |s= invocation_stmt[] ) )
			// ILOCGenerator.g:191:7: (s= block[] |s= assignment[] |s= print[] |s= read[] |s= conditional[] |s= loop[] |s= delete[] |s= return_stmt[] |s= invocation_stmt[] )
			{
			// ILOCGenerator.g:191:7: (s= block[] |s= assignment[] |s= print[] |s= read[] |s= conditional[] |s= loop[] |s= delete[] |s= return_stmt[] |s= invocation_stmt[] )
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
					// ILOCGenerator.g:191:8: s= block[]
					{
					pushFollow(FOLLOW_block_in_statement863);
					block();
					state._fsp--;

					}
					break;
				case 2 :
					// ILOCGenerator.g:192:10: s= assignment[]
					{
					pushFollow(FOLLOW_assignment_in_statement877);
					s=assignment();
					state._fsp--;

					}
					break;
				case 3 :
					// ILOCGenerator.g:193:10: s= print[]
					{
					pushFollow(FOLLOW_print_in_statement891);
					s=print();
					state._fsp--;

					}
					break;
				case 4 :
					// ILOCGenerator.g:194:10: s= read[]
					{
					pushFollow(FOLLOW_read_in_statement905);
					s=read();
					state._fsp--;

					}
					break;
				case 5 :
					// ILOCGenerator.g:195:10: s= conditional[]
					{
					pushFollow(FOLLOW_conditional_in_statement919);
					s=conditional();
					state._fsp--;

					}
					break;
				case 6 :
					// ILOCGenerator.g:196:10: s= loop[]
					{
					pushFollow(FOLLOW_loop_in_statement933);
					s=loop();
					state._fsp--;

					}
					break;
				case 7 :
					// ILOCGenerator.g:197:10: s= delete[]
					{
					pushFollow(FOLLOW_delete_in_statement947);
					s=delete();
					state._fsp--;

					}
					break;
				case 8 :
					// ILOCGenerator.g:198:10: s= return_stmt[]
					{
					pushFollow(FOLLOW_return_stmt_in_statement961);
					s=return_stmt();
					state._fsp--;

					}
					break;
				case 9 :
					// ILOCGenerator.g:199:10: s= invocation_stmt[]
					{
					pushFollow(FOLLOW_invocation_stmt_in_statement975);
					s=invocation_stmt();
					state._fsp--;

					}
					break;

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
	// $ANTLR end "statement"



	// $ANTLR start "block"
	// ILOCGenerator.g:204:1: block[] returns [] : ^( BLOCK s= statement_list[] ) ;
	public final void block() throws RecognitionException {
		try {
			// ILOCGenerator.g:206:4: ( ^( BLOCK s= statement_list[] ) )
			// ILOCGenerator.g:206:7: ^( BLOCK s= statement_list[] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block1017); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block1021);
			statement_list();
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
	// $ANTLR end "block"



	// $ANTLR start "statement_list"
	// ILOCGenerator.g:212:1: statement_list[] returns [] : ^( STMTS (s= statement[] )* ) ;
	public final void statement_list() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:215:4: ( ^( STMTS (s= statement[] )* ) )
			// ILOCGenerator.g:215:7: ^( STMTS (s= statement[] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list1063); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:215:15: (s= statement[] )*
				loop13:
				while (true) {
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= ASSIGN && LA13_0 <= BLOCK)||LA13_0==DELETE||LA13_0==IF||LA13_0==INVOKE||LA13_0==PRINT||LA13_0==READ||LA13_0==RETURN||LA13_0==WHILE) ) {
						alt13=1;
					}

					switch (alt13) {
					case 1 :
						// ILOCGenerator.g:215:16: s= statement[]
						{
						pushFollow(FOLLOW_statement_in_statement_list1068);
						statement();
						state._fsp--;

						         
						         
						      
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
	}
	// $ANTLR end "statement_list"



	// $ANTLR start "assignment"
	// ILOCGenerator.g:221:1: assignment[] returns [boolean hasReturn = false] : ^(ast= ASSIGN e= expression[] l= lvalue[] ) ;
	public final boolean assignment() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:223:4: ( ^(ast= ASSIGN e= expression[] l= lvalue[] ) )
			// ILOCGenerator.g:223:7: ^(ast= ASSIGN e= expression[] l= lvalue[] )
			{
			ast=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1109); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment1113);
			expression();
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment1118);
			lvalue();
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
		return hasReturn;
	}
	// $ANTLR end "assignment"



	// $ANTLR start "print"
	// ILOCGenerator.g:229:1: print[] returns [boolean hasReturn = false] : ^(ast= PRINT e= expression[] ( ENDL )? ) ;
	public final boolean print() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		  
		try {
			// ILOCGenerator.g:232:4: ( ^(ast= PRINT e= expression[] ( ENDL )? ) )
			// ILOCGenerator.g:232:7: ^(ast= PRINT e= expression[] ( ENDL )? )
			{
			ast=(CommonTree)match(input,PRINT,FOLLOW_PRINT_in_print1163); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print1167);
			expression();
			state._fsp--;

			// ILOCGenerator.g:232:34: ( ENDL )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ENDL) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// ILOCGenerator.g:232:35: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print1171); 
					  
					}
					break;

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
		return hasReturn;
	}
	// $ANTLR end "print"



	// $ANTLR start "read"
	// ILOCGenerator.g:238:1: read[] returns [boolean hasReturn = false] : ^(ast= READ l= lvalue[] ) ;
	public final boolean read() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:240:4: ( ^(ast= READ l= lvalue[] ) )
			// ILOCGenerator.g:240:7: ^(ast= READ l= lvalue[] )
			{
			ast=(CommonTree)match(input,READ,FOLLOW_READ_in_read1211); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read1215);
			lvalue();
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
		return hasReturn;
	}
	// $ANTLR end "read"



	// $ANTLR start "conditional"
	// ILOCGenerator.g:246:1: conditional[] returns [boolean hasReturn = false] : ^(ast= IF g= expression[] t= block[] (e= block[] )? ) ;
	public final boolean conditional() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:248:4: ( ^(ast= IF g= expression[] t= block[] (e= block[] )? ) )
			// ILOCGenerator.g:248:7: ^(ast= IF g= expression[] t= block[] (e= block[] )? )
			{
			ast=(CommonTree)match(input,IF,FOLLOW_IF_in_conditional1252); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional1256);
			expression();
			state._fsp--;

			pushFollow(FOLLOW_block_in_conditional1261);
			block();
			state._fsp--;

			// ILOCGenerator.g:248:41: (e= block[] )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BLOCK) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// ILOCGenerator.g:248:42: e= block[]
					{
					pushFollow(FOLLOW_block_in_conditional1267);
					block();
					state._fsp--;

					}
					break;

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
		return hasReturn;
	}
	// $ANTLR end "conditional"



	// $ANTLR start "loop"
	// ILOCGenerator.g:254:1: loop[] returns [boolean hasReturn = false] : ^(ast= WHILE e= expression[] b= block[] expression[] ) ;
	public final boolean loop() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:256:4: ( ^(ast= WHILE e= expression[] b= block[] expression[] ) )
			// ILOCGenerator.g:256:7: ^(ast= WHILE e= expression[] b= block[] expression[] )
			{
			ast=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_loop1306); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop1310);
			expression();
			state._fsp--;

			pushFollow(FOLLOW_block_in_loop1315);
			block();
			state._fsp--;

			pushFollow(FOLLOW_expression_in_loop1318);
			expression();
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
		return hasReturn;
	}
	// $ANTLR end "loop"



	// $ANTLR start "delete"
	// ILOCGenerator.g:262:1: delete[] returns [boolean hasReturn = false] : ^(ast= DELETE e= expression[] ) ;
	public final boolean delete() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:264:4: ( ^(ast= DELETE e= expression[] ) )
			// ILOCGenerator.g:264:7: ^(ast= DELETE e= expression[] )
			{
			ast=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete1355); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete1359);
			expression();
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
		return hasReturn;
	}
	// $ANTLR end "delete"



	// $ANTLR start "return_stmt"
	// ILOCGenerator.g:270:1: return_stmt[] returns [boolean hasReturn = true] : ^(ast= RETURN (e= expression[] )? ) ;
	public final boolean return_stmt() throws RecognitionException {
		boolean hasReturn =  true;


		CommonTree ast=null;

		try {
			// ILOCGenerator.g:272:4: ( ^(ast= RETURN (e= expression[] )? ) )
			// ILOCGenerator.g:272:7: ^(ast= RETURN (e= expression[] )? )
			{
			ast=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_stmt1396); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:272:20: (e= expression[] )?
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==AND||(LA16_0 >= DIVIDE && LA16_0 <= DOT)||(LA16_0 >= EQ && LA16_0 <= FALSE)||(LA16_0 >= GE && LA16_0 <= ID)||(LA16_0 >= INTEGER && LA16_0 <= INVOKE)||LA16_0==LE||(LA16_0 >= LT && LA16_0 <= OR)||LA16_0==PLUS||(LA16_0 >= TIMES && LA16_0 <= TRUE)) ) {
					alt16=1;
				}
				switch (alt16) {
					case 1 :
						// ILOCGenerator.g:272:21: e= expression[]
						{
						pushFollow(FOLLOW_expression_in_return_stmt1401);
						expression();
						state._fsp--;

						}
						break;

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
	// $ANTLR end "return_stmt"



	// $ANTLR start "invocation_stmt"
	// ILOCGenerator.g:278:1: invocation_stmt[] returns [boolean hasReturn = false] : ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) ) ;
	public final boolean invocation_stmt() throws RecognitionException {
		boolean hasReturn =  false;


		CommonTree id=null;

		 int argIdx = 0; 
		try {
			// ILOCGenerator.g:281:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) ) )
			// ILOCGenerator.g:281:7: ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_stmt1446); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_stmt1450); 
			 
			           
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_stmt1475); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:285:17: (e= expression[] )*
				loop17:
				while (true) {
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)) ) {
						alt17=1;
					}

					switch (alt17) {
					case 1 :
						// ILOCGenerator.g:285:18: e= expression[]
						{
						pushFollow(FOLLOW_expression_in_invocation_stmt1480);
						expression();
						state._fsp--;

						  
						               
						            
						}
						break;

					default :
						break loop17;
					}
				}

				match(input, Token.UP, null); 
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
		return hasReturn;
	}
	// $ANTLR end "invocation_stmt"



	// $ANTLR start "lvalue"
	// ILOCGenerator.g:293:1: lvalue[] returns [] : (id= ID | ^(ast= DOT l= lvalue[] id= ID ) );
	public final void lvalue() throws RecognitionException {
		CommonTree id=null;
		CommonTree ast=null;

		try {
			// ILOCGenerator.g:295:4: (id= ID | ^(ast= DOT l= lvalue[] id= ID ) )
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
					// ILOCGenerator.g:295:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1534); 

					         
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:299:7: ^(ast= DOT l= lvalue[] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_lvalue1553); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_lvalue_in_lvalue1557);
					lvalue();
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1562); 
					match(input, Token.UP, null); 


					         
					      
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
	// $ANTLR end "lvalue"



	// $ANTLR start "expression"
	// ILOCGenerator.g:305:1: expression[] returns [] : ( ^( (ast= AND |ast= OR ) lft= expression[] rht= expression[] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[] rht= expression[] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[] rht= expression[] ) | ^(ast= NOT e= expression[] ) | ^(ast= NEG e= expression[] ) | ^(ast= DOT e= expression[] id= ID ) |e= invocation_exp[] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL );
	public final void expression() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;
		CommonTree i=null;

		try {
			// ILOCGenerator.g:307:4: ( ^( (ast= AND |ast= OR ) lft= expression[] rht= expression[] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[] rht= expression[] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[] rht= expression[] ) | ^(ast= NOT e= expression[] ) | ^(ast= NEG e= expression[] ) | ^(ast= DOT e= expression[] id= ID ) |e= invocation_exp[] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL )
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
					// ILOCGenerator.g:307:7: ^( (ast= AND |ast= OR ) lft= expression[] rht= expression[] )
					{
					// ILOCGenerator.g:307:9: (ast= AND |ast= OR )
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
							// ILOCGenerator.g:307:10: ast= AND
							{
							ast=(CommonTree)match(input,AND,FOLLOW_AND_in_expression1599); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:307:20: ast= OR
							{
							ast=(CommonTree)match(input,OR,FOLLOW_OR_in_expression1605); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1619);
					expression();
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1624);
					expression();
					state._fsp--;

					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:313:7: ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[] rht= expression[] )
					{
					// ILOCGenerator.g:313:9: (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE )
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
							// ILOCGenerator.g:313:10: ast= EQ
							{
							ast=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expression1650); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:313:19: ast= LT
							{
							ast=(CommonTree)match(input,LT,FOLLOW_LT_in_expression1656); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:313:28: ast= GT
							{
							ast=(CommonTree)match(input,GT,FOLLOW_GT_in_expression1662); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:313:37: ast= NE
							{
							ast=(CommonTree)match(input,NE,FOLLOW_NE_in_expression1668); 
							}
							break;
						case 5 :
							// ILOCGenerator.g:313:46: ast= LE
							{
							ast=(CommonTree)match(input,LE,FOLLOW_LE_in_expression1674); 
							}
							break;
						case 6 :
							// ILOCGenerator.g:313:55: ast= GE
							{
							ast=(CommonTree)match(input,GE,FOLLOW_GE_in_expression1680); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1694);
					expression();
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1699);
					expression();
					state._fsp--;

					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 3 :
					// ILOCGenerator.g:319:7: ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[] rht= expression[] )
					{
					// ILOCGenerator.g:319:9: (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE )
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
							// ILOCGenerator.g:319:10: ast= PLUS
							{
							ast=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expression1725); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:319:21: ast= MINUS
							{
							ast=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression1731); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:319:33: ast= TIMES
							{
							ast=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expression1737); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:319:45: ast= DIVIDE
							{
							ast=(CommonTree)match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1743); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1757);
					expression();
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1762);
					expression();
					state._fsp--;

					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 4 :
					// ILOCGenerator.g:324:7: ^(ast= NOT e= expression[] )
					{
					ast=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expression1783); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1787);
					expression();
					state._fsp--;

					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 5 :
					// ILOCGenerator.g:328:7: ^(ast= NEG e= expression[] )
					{
					ast=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expression1808); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1812);
					expression();
					state._fsp--;

					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 6 :
					// ILOCGenerator.g:332:7: ^(ast= DOT e= expression[] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_expression1833); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1837);
					expression();
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1842); 
					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 7 :
					// ILOCGenerator.g:336:7: e= invocation_exp[]
					{
					pushFollow(FOLLOW_invocation_exp_in_expression1861);
					invocation_exp();
					state._fsp--;


					         
					      
					}
					break;
				case 8 :
					// ILOCGenerator.g:340:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1881); 
					     
					                 
					      
					}
					break;
				case 9 :
					// ILOCGenerator.g:344:7: i= INTEGER
					{
					i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1899); 

					         
					      
					}
					break;
				case 10 :
					// ILOCGenerator.g:348:7: ast= TRUE
					{
					ast=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression1917); 
					 
					         
					      
					}
					break;
				case 11 :
					// ILOCGenerator.g:352:7: ast= FALSE
					{
					ast=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression1935); 

					         
					      
					}
					break;
				case 12 :
					// ILOCGenerator.g:356:7: ^(ast= NEW id= ID )
					{
					ast=(CommonTree)match(input,NEW,FOLLOW_NEW_in_expression1954); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1958); 
					match(input, Token.UP, null); 


					         
					      
					}
					break;
				case 13 :
					// ILOCGenerator.g:360:7: ast= NULL
					{
					ast=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression1977); 

					         
					      
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
	// $ANTLR end "expression"



	// $ANTLR start "invocation_exp"
	// ILOCGenerator.g:366:1: invocation_exp[] returns [] : ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) ) ;
	public final void invocation_exp() throws RecognitionException {
		CommonTree id=null;

		 int argIdx = 0; 
		try {
			// ILOCGenerator.g:369:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) ) )
			// ILOCGenerator.g:369:7: ^( INVOKE id= ID ^( ARGS (e= expression[] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_exp2018); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_exp2022); 
			 
			            
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_exp2042); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:373:14: (e= expression[] )*
				loop23:
				while (true) {
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==AND||(LA23_0 >= DIVIDE && LA23_0 <= DOT)||(LA23_0 >= EQ && LA23_0 <= FALSE)||(LA23_0 >= GE && LA23_0 <= ID)||(LA23_0 >= INTEGER && LA23_0 <= INVOKE)||LA23_0==LE||(LA23_0 >= LT && LA23_0 <= OR)||LA23_0==PLUS||(LA23_0 >= TIMES && LA23_0 <= TRUE)) ) {
						alt23=1;
					}

					switch (alt23) {
					case 1 :
						// ILOCGenerator.g:373:15: e= expression[]
						{
						pushFollow(FOLLOW_expression_in_invocation_exp2047);
						expression();
						state._fsp--;

						  
						            
						         
						}
						break;

					default :
						break loop23;
					}
				}

				match(input, Token.UP, null); 
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
	// $ANTLR end "invocation_exp"

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
	public static final BitSet FOLLOW_parameters_in_function602 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_return_type_in_function617 = new BitSet(new long[]{0x0008000000002000L});
	public static final BitSet FOLLOW_declarations_in_function645 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function659 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters701 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_decl_in_parameters706 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_param_decl745 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_param_decl748 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_param_decl752 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_param_decl757 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETTYPE_in_return_type790 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_rtype_in_return_type795 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_type_in_rtype824 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_rtype834 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement877 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement891 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement947 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_stmt_in_statement961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_stmt_in_statement975 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block1017 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block1021 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_STMTS_in_statement_list1063 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list1068 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment1109 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment1113 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment1118 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRINT_in_print1163 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print1167 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print1171 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read1211 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read1215 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional1252 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional1256 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional1261 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional1267 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop1306 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop1310 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop1315 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop1318 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete1355 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete1359 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_return_stmt1396 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_return_stmt1401 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_stmt1446 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_stmt1450 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_stmt1475 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_stmt1480 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ID_in_lvalue1534 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lvalue1553 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_lvalue1557 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue1562 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_AND_in_expression1599 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_OR_in_expression1605 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1619 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1624 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression1650 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LT_in_expression1656 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GT_in_expression1662 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NE_in_expression1668 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LE_in_expression1674 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GE_in_expression1680 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1694 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1699 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression1725 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_MINUS_in_expression1731 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TIMES_in_expression1737 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1743 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1757 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1762 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1783 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1787 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1808 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1812 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1833 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1837 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1842 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_invocation_exp_in_expression1861 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expression1881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression1899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression1917 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression1935 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression1954 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1958 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULL_in_expression1977 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_exp2018 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_exp2022 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_exp2042 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_exp2047 = new BitSet(new long[]{0x006005FEB3998018L});
}
