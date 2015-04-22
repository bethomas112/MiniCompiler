// $ANTLR 3.5.2 ILOCGenerator.g 2015-04-21 22:39:50

   import java.util.List;
   import java.util.LinkedList;
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.HashSet;
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

	      private List<IInstruction> instructions;
	      public BasicBlock() {
	         prev = new ArrayList<>();
	         next = new ArrayList<>();
	         instructions = new ArrayList<>();
	      }

	      public List<IInstruction> getILOC() { 
	         return instructions; 
	      }

	      public void addInstruction(IInstruction instruction) {
	         instructions.add(instruction);
	      }

	      public String toString() {
	         StringBuilder sb = new StringBuilder();
	         sb.append(label + ":\n");
	         for (IInstruction instruction : instructions) {
	            sb.append("\t" + instruction.getText() + "\n");
	         }
	         return sb.toString();
	      }
	   }

	   private static int labelCount = 0;
	   private static String getNextLabel() {
	      return "L" + labelCount++;
	   }

	   private HashMap<String, MiniType> structTypes = new HashMap<>();
	   private List<CFG> cfgs = new ArrayList<>();   



	// $ANTLR start "translate"
	// ILOCGenerator.g:95:1: translate : ^( PROGRAM t= types d= declarations[null] f= functions ) ;
	public final void translate() throws RecognitionException {
		try {
			// ILOCGenerator.g:96:4: ( ^( PROGRAM t= types d= declarations[null] f= functions ) )
			// ILOCGenerator.g:96:7: ^( PROGRAM t= types d= declarations[null] f= functions )
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
	// ILOCGenerator.g:102:1: types : ( ^( TYPES (t= type_decl )* ) |);
	public final void types() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:104:4: ( ^( TYPES (t= type_decl )* ) |)
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
					// ILOCGenerator.g:104:7: ^( TYPES (t= type_decl )* )
					{
					match(input,TYPES,FOLLOW_TYPES_in_types103); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:104:15: (t= type_decl )*
						loop1:
						while (true) {
							int alt1=2;
							int LA1_0 = input.LA(1);
							if ( (LA1_0==STRUCT) ) {
								alt1=1;
							}

							switch (alt1) {
							case 1 :
								// ILOCGenerator.g:104:16: t= type_decl
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
					// ILOCGenerator.g:106:7: 
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
	// ILOCGenerator.g:109:1: type_decl : ^(ast= STRUCT id= ID n= nested_decl[structType] ) ;
	public final void type_decl() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;

		 MiniType.StructType structType = new MiniType.StructType(); 
		try {
			// ILOCGenerator.g:111:4: ( ^(ast= STRUCT id= ID n= nested_decl[structType] ) )
			// ILOCGenerator.g:111:7: ^(ast= STRUCT id= ID n= nested_decl[structType] )
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
	// ILOCGenerator.g:123:1: nested_decl[MiniType.StructType structType] : (f= field_decl[structType] )+ ;
	public final void nested_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType f =null;

		  
		try {
			// ILOCGenerator.g:125:4: ( (f= field_decl[structType] )+ )
			// ILOCGenerator.g:125:7: (f= field_decl[structType] )+
			{
			// ILOCGenerator.g:125:7: (f= field_decl[structType] )+
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
					// ILOCGenerator.g:125:8: f= field_decl[structType]
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
	// ILOCGenerator.g:128:1: field_decl[MiniType.StructType structType] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final MiniType field_decl(MiniType.StructType structType) throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:130:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:130:7: ^( DECL ^( TYPE t= type ) id= ID )
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
	// ILOCGenerator.g:138:1: type returns [MiniType miniType = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
	public final MiniType type() throws RecognitionException {
		MiniType miniType =  null;


		CommonTree id=null;

		try {
			// ILOCGenerator.g:140:4: ( INT | BOOL | ^( STRUCT id= ID ) )
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
					// ILOCGenerator.g:140:7: INT
					{
					match(input,INT,FOLLOW_INT_in_type312); 
					 miniType = MiniType.INT; 
					}
					break;
				case 2 :
					// ILOCGenerator.g:141:7: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type322); 
					 miniType = MiniType.BOOL; 
					}
					break;
				case 3 :
					// ILOCGenerator.g:142:7: ^( STRUCT id= ID )
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
	// ILOCGenerator.g:148:1: declarations[CFG cfg] : ( ^( DECLS (d= decl_list[cfg] )* ) |);
	public final void declarations(CFG cfg) throws RecognitionException {
		try {
			// ILOCGenerator.g:150:4: ( ^( DECLS (d= decl_list[cfg] )* ) |)
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
					// ILOCGenerator.g:150:7: ^( DECLS (d= decl_list[cfg] )* )
					{
					match(input,DECLS,FOLLOW_DECLS_in_declarations373); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:150:15: (d= decl_list[cfg] )*
						loop5:
						while (true) {
							int alt5=2;
							int LA5_0 = input.LA(1);
							if ( (LA5_0==DECLLIST) ) {
								alt5=1;
							}

							switch (alt5) {
							case 1 :
								// ILOCGenerator.g:150:16: d= decl_list[cfg]
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
					// ILOCGenerator.g:152:7: 
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
	// ILOCGenerator.g:155:1: decl_list[CFG cfg] : ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) ;
	public final void decl_list(CFG cfg) throws RecognitionException {
		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:156:4: ( ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) )
			// ILOCGenerator.g:156:7: ^( DECLLIST ^( TYPE t= type ) (id= ID )+ )
			{
			match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list416); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_decl_list419); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_decl_list423);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			// ILOCGenerator.g:157:10: (id= ID )+
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
					// ILOCGenerator.g:157:11: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_decl_list438); 

					               if (cfg != null) {
					                  cfg.locals.put((id!=null?id.getText():null), Register.newRegister());
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
	// ILOCGenerator.g:167:1: functions returns [] : ( ^( FUNCS (f= function )* ) |);
	public final void functions() throws RecognitionException {
		  
		try {
			// ILOCGenerator.g:170:4: ( ^( FUNCS (f= function )* ) |)
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
					// ILOCGenerator.g:170:7: ^( FUNCS (f= function )* )
					{
					match(input,FUNCS,FOLLOW_FUNCS_in_functions503); 
					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); 
						// ILOCGenerator.g:170:15: (f= function )*
						loop8:
						while (true) {
							int alt8=2;
							int LA8_0 = input.LA(1);
							if ( (LA8_0==FUN) ) {
								alt8=1;
							}

							switch (alt8) {
							case 1 :
								// ILOCGenerator.g:170:16: f= function
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

					 
					         for (CFG cfg : cfgs) {
					            List<BasicBlock> blocks = cfg.bfsBlocks();
					            for (BasicBlock block : blocks) {
					               StringBuilder sb = new StringBuilder();
					               // sb.append(block.label + " -> ");
					               // for (BasicBlock nextBlock : block.next) {
					               //    sb.append(nextBlock.label + ", ");
					               // }
					               sb.append(block);
					               System.out.print(sb.toString());
					            }
					         } 
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:185:7: 
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
	// ILOCGenerator.g:188:1: function : ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) ;
	public final void function() throws RecognitionException {
		CommonTree ast=null;
		CommonTree id=null;
		BasicBlock p =null;
		BasicBlock s =null;

		 
		      CFG cfg = new CFG();
		      Register.resetRegisters();
		      BasicBlock exitBlock = new BasicBlock();
		      exitBlock.label = getNextLabel();
		      cfg.exitBlock = exitBlock;
		      exitBlock.addInstruction(new IInstruction.RET());
		   
		try {
			// ILOCGenerator.g:198:4: ( ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) )
			// ILOCGenerator.g:198:7: ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] )
			{
			ast=(CommonTree)match(input,FUN,FOLLOW_FUN_in_function560); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_function574); 
			pushFollow(FOLLOW_parameters_in_function597);
			p=parameters(cfg);
			state._fsp--;


			            cfg.entryBlock = p;
			            cfg.entryBlock.label = (id!=null?id.getText():null);
			         
			pushFollow(FOLLOW_return_type_in_function624);
			return_type();
			state._fsp--;

			pushFollow(FOLLOW_declarations_in_function637);
			declarations(cfg);
			state._fsp--;

			pushFollow(FOLLOW_statement_list_in_function651);
			s=statement_list(cfg, cfg.entryBlock);
			state._fsp--;

			match(input, Token.UP, null); 


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
	// ILOCGenerator.g:213:1: parameters[CFG cfg] returns [BasicBlock entryBlock = null] : ^( PARAMS (p= param_decl[] )* ) ;
	public final BasicBlock parameters(CFG cfg) throws RecognitionException {
		BasicBlock entryBlock =  null;


		String p =null;

		 
		      int paramNum = 0;
		      BasicBlock block = new BasicBlock();
		   
		try {
			// ILOCGenerator.g:220:4: ( ^( PARAMS (p= param_decl[] )* ) )
			// ILOCGenerator.g:220:7: ^( PARAMS (p= param_decl[] )* )
			{
			match(input,PARAMS,FOLLOW_PARAMS_in_parameters697); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:220:16: (p= param_decl[] )*
				loop10:
				while (true) {
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==DECL) ) {
						alt10=1;
					}

					switch (alt10) {
					case 1 :
						// ILOCGenerator.g:220:17: p= param_decl[]
						{
						pushFollow(FOLLOW_param_decl_in_parameters702);
						p=param_decl();
						state._fsp--;

						 
						         Register paramReg = Register.newRegister();
						         cfg.params.put(p, paramNum);
						         cfg.locals.put(p, paramReg);         
						         //TODO: Generate code for setting up params.
						         IInstruction.LOADINARGUMENT loadinargument = new IInstruction.LOADINARGUMENT();
						         loadinargument.variable = p;
						         loadinargument.argIdx = paramNum;
						         loadinargument.dest = paramReg;
						         block.instructions.add(loadinargument);
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
	// ILOCGenerator.g:238:1: param_decl[] returns [String paramId = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
	public final String param_decl() throws RecognitionException {
		String paramId =  null;


		CommonTree id=null;
		MiniType t =null;

		try {
			// ILOCGenerator.g:240:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
			// ILOCGenerator.g:240:7: ^( DECL ^( TYPE t= type ) id= ID )
			{
			match(input,DECL,FOLLOW_DECL_in_param_decl748); 
			match(input, Token.DOWN, null); 
			match(input,TYPE,FOLLOW_TYPE_in_param_decl751); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_type_in_param_decl755);
			t=type();
			state._fsp--;

			match(input, Token.UP, null); 

			id=(CommonTree)match(input,ID,FOLLOW_ID_in_param_decl760); 
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
	// ILOCGenerator.g:246:1: return_type : ^( RETTYPE (r= rtype ) ) ;
	public final void return_type() throws RecognitionException {
		try {
			// ILOCGenerator.g:247:4: ( ^( RETTYPE (r= rtype ) ) )
			// ILOCGenerator.g:247:7: ^( RETTYPE (r= rtype ) )
			{
			match(input,RETTYPE,FOLLOW_RETTYPE_in_return_type786); 
			match(input, Token.DOWN, null); 
			// ILOCGenerator.g:247:17: (r= rtype )
			// ILOCGenerator.g:247:18: r= rtype
			{
			pushFollow(FOLLOW_rtype_in_return_type791);
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
	// ILOCGenerator.g:250:1: rtype : (t= type | VOID );
	public final void rtype() throws RecognitionException {
		MiniType t =null;

		try {
			// ILOCGenerator.g:251:4: (t= type | VOID )
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
					// ILOCGenerator.g:251:7: t= type
					{
					pushFollow(FOLLOW_type_in_rtype813);
					t=type();
					state._fsp--;

					  
					}
					break;
				case 2 :
					// ILOCGenerator.g:252:7: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_rtype823); 
					  
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
	// ILOCGenerator.g:255:1: statement[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] ) ;
	public final BasicBlock statement(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		try {
			// ILOCGenerator.g:257:4: ( (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] ) )
			// ILOCGenerator.g:257:7: (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] )
			{
			// ILOCGenerator.g:257:7: (s= block[cfg, block] |s= assignment[cfg, block] |s= print[cfg, block] |s= read[cfg, block] |s= conditional[cfg, block] |s= loop[cfg, block] |s= delete[cfg, block] |s= return_stmt[cfg, block] |s= invocation_stmt[cfg, block] )
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
					// ILOCGenerator.g:257:8: s= block[cfg, block]
					{
					pushFollow(FOLLOW_block_in_statement852);
					s=block(cfg, block);
					state._fsp--;

					}
					break;
				case 2 :
					// ILOCGenerator.g:258:10: s= assignment[cfg, block]
					{
					pushFollow(FOLLOW_assignment_in_statement866);
					s=assignment(cfg, block);
					state._fsp--;

					}
					break;
				case 3 :
					// ILOCGenerator.g:259:10: s= print[cfg, block]
					{
					pushFollow(FOLLOW_print_in_statement880);
					s=print(cfg, block);
					state._fsp--;

					}
					break;
				case 4 :
					// ILOCGenerator.g:260:10: s= read[cfg, block]
					{
					pushFollow(FOLLOW_read_in_statement894);
					s=read(cfg, block);
					state._fsp--;

					}
					break;
				case 5 :
					// ILOCGenerator.g:261:10: s= conditional[cfg, block]
					{
					pushFollow(FOLLOW_conditional_in_statement908);
					s=conditional(cfg, block);
					state._fsp--;

					}
					break;
				case 6 :
					// ILOCGenerator.g:262:10: s= loop[cfg, block]
					{
					pushFollow(FOLLOW_loop_in_statement922);
					s=loop(cfg, block);
					state._fsp--;

					}
					break;
				case 7 :
					// ILOCGenerator.g:263:10: s= delete[cfg, block]
					{
					pushFollow(FOLLOW_delete_in_statement936);
					s=delete(cfg, block);
					state._fsp--;

					}
					break;
				case 8 :
					// ILOCGenerator.g:264:10: s= return_stmt[cfg, block]
					{
					pushFollow(FOLLOW_return_stmt_in_statement950);
					s=return_stmt(cfg, block);
					state._fsp--;

					}
					break;
				case 9 :
					// ILOCGenerator.g:265:10: s= invocation_stmt[cfg, block]
					{
					pushFollow(FOLLOW_invocation_stmt_in_statement964);
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
	// ILOCGenerator.g:270:1: block[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( BLOCK s= statement_list[cfg, block] ) ;
	public final BasicBlock block(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		try {
			// ILOCGenerator.g:272:4: ( ^( BLOCK s= statement_list[cfg, block] ) )
			// ILOCGenerator.g:272:7: ^( BLOCK s= statement_list[cfg, block] )
			{
			match(input,BLOCK,FOLLOW_BLOCK_in_block1006); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_statement_list_in_block1010);
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
	// ILOCGenerator.g:278:1: statement_list[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( STMTS (s= statement[cfg, currentBlock] )* ) ;
	public final BasicBlock statement_list(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		BasicBlock s =null;

		 BasicBlock currentBlock = block; 
		try {
			// ILOCGenerator.g:281:4: ( ^( STMTS (s= statement[cfg, currentBlock] )* ) )
			// ILOCGenerator.g:281:7: ^( STMTS (s= statement[cfg, currentBlock] )* )
			{
			match(input,STMTS,FOLLOW_STMTS_in_statement_list1052); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:281:15: (s= statement[cfg, currentBlock] )*
				loop13:
				while (true) {
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( ((LA13_0 >= ASSIGN && LA13_0 <= BLOCK)||LA13_0==DELETE||LA13_0==IF||LA13_0==INVOKE||LA13_0==PRINT||LA13_0==READ||LA13_0==RETURN||LA13_0==WHILE) ) {
						alt13=1;
					}

					switch (alt13) {
					case 1 :
						// ILOCGenerator.g:281:16: s= statement[cfg, currentBlock]
						{
						pushFollow(FOLLOW_statement_in_statement_list1057);
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
	// ILOCGenerator.g:287:1: assignment[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] ) ;
	public final BasicBlock assignment(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register e =null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:289:4: ( ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] ) )
			// ILOCGenerator.g:289:7: ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block, false] )
			{
			ast=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1098); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_assignment1102);
			e=expression(cfg, block);
			state._fsp--;

			pushFollow(FOLLOW_lvalue_in_assignment1107);
			l=lvalue(cfg, block, false);
			state._fsp--;

			match(input, Token.UP, null); 


			         if ((l!=null?((ILOCGenerator.lvalue_return)l).wasGlobal:false)) {
			            IInstruction.STOREGLOBAL storeglobal = new IInstruction.STOREGLOBAL();
			            storeglobal.source = e;
			            storeglobal.globalName = (l!=null?((ILOCGenerator.lvalue_return)l).globalName:null);
			            block.addInstruction(storeglobal);
			         }
			         else if ((l!=null?((ILOCGenerator.lvalue_return)l).wasField:false)) {
			            IInstruction.STOREAIFIELD storeaifield = new IInstruction.STOREAIFIELD();
			            storeaifield.source = e;
			            storeaifield.dest = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
			            storeaifield.fieldName = (l!=null?((ILOCGenerator.lvalue_return)l).fieldName:null);
			            block.addInstruction(storeaifield);
			         }
			         else {
			            IInstruction.MOV mov = new IInstruction.MOV();
			            mov.source = e;
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
	// ILOCGenerator.g:314:1: print[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) ;
	public final BasicBlock print(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register e =null;

		  
		try {
			// ILOCGenerator.g:317:4: ( ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) )
			// ILOCGenerator.g:317:7: ^(ast= PRINT e= expression[cfg, block] ( ENDL )? )
			{
			ast=(CommonTree)match(input,PRINT,FOLLOW_PRINT_in_print1152); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_print1156);
			e=expression(cfg, block);
			state._fsp--;

			// ILOCGenerator.g:317:44: ( ENDL )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ENDL) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// ILOCGenerator.g:317:45: ENDL
					{
					match(input,ENDL,FOLLOW_ENDL_in_print1160); 
					  
					}
					break;

			}

			match(input, Token.UP, null); 


			         resultBlock = block;
			         IInstruction.PRINTLN println = new IInstruction.PRINTLN();
			         println.source = e;
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
	// ILOCGenerator.g:326:1: read[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= READ l= lvalue[cfg, block, false] ) ;
	public final BasicBlock read(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:328:4: ( ^(ast= READ l= lvalue[cfg, block, false] ) )
			// ILOCGenerator.g:328:7: ^(ast= READ l= lvalue[cfg, block, false] )
			{
			ast=(CommonTree)match(input,READ,FOLLOW_READ_in_read1200); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_lvalue_in_read1204);
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
	// ILOCGenerator.g:366:1: conditional[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) ;
	public final BasicBlock conditional(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register g =null;
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
			// ILOCGenerator.g:380:4: ( ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) )
			// ILOCGenerator.g:380:7: ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? )
			{
			ast=(CommonTree)match(input,IF,FOLLOW_IF_in_conditional1253); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_conditional1257);
			g=expression(cfg, guardBlock);
			state._fsp--;

			pushFollow(FOLLOW_block_in_conditional1262);
			t=block(cfg, thenBlock);
			state._fsp--;

			// ILOCGenerator.g:380:70: (e= block[cfg, elseBlock] )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BLOCK) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// ILOCGenerator.g:380:71: e= block[cfg, elseBlock]
					{
					pushFollow(FOLLOW_block_in_conditional1268);
					e=block(cfg, elseBlock);
					state._fsp--;


					            hasElseBlock = true;
					         
					}
					break;

			}

			match(input, Token.UP, null); 


			         block.next.add(guardBlock);
			         guardBlock.next.add(thenBlock);
			         
			         if (t != null) {
			            t.next.add(afterBlock);
			         }
			         if (hasElseBlock) {
			            guardBlock.next.add(elseBlock);   
			            if (e != null) {
			               e.next.add(afterBlock);
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
	// ILOCGenerator.g:402:1: loop[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) ;
	public final BasicBlock loop(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register e =null;
		BasicBlock b =null;


		      BasicBlock guardBlock = new BasicBlock();
		      guardBlock.label = getNextLabel();
		      BasicBlock afterBlock = new BasicBlock();
		      afterBlock.label = getNextLabel();
		      BasicBlock bodyBlock = new BasicBlock();
		      bodyBlock.label = getNextLabel();
		   
		try {
			// ILOCGenerator.g:413:4: ( ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) )
			// ILOCGenerator.g:413:7: ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] )
			{
			ast=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_loop1343); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_loop1347);
			e=expression(cfg, guardBlock);
			state._fsp--;

			pushFollow(FOLLOW_block_in_loop1352);
			b=block(cfg, bodyBlock);
			state._fsp--;

			pushFollow(FOLLOW_expression_in_loop1355);
			expression(cfg, new BasicBlock());
			state._fsp--;

			match(input, Token.UP, null); 


			         block.next.add(guardBlock);
			         guardBlock.next.add(bodyBlock);
			         guardBlock.next.add(afterBlock);
			         if (b != null) {
			            b.next.add(guardBlock);
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
	// ILOCGenerator.g:425:1: delete[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= DELETE e= expression[cfg, block] ) ;
	public final BasicBlock delete(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register e =null;

		try {
			// ILOCGenerator.g:427:4: ( ^(ast= DELETE e= expression[cfg, block] ) )
			// ILOCGenerator.g:427:7: ^(ast= DELETE e= expression[cfg, block] )
			{
			ast=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete1392); 
			match(input, Token.DOWN, null); 
			pushFollow(FOLLOW_expression_in_delete1396);
			e=expression(cfg, block);
			state._fsp--;

			match(input, Token.UP, null); 


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
	// ILOCGenerator.g:433:1: return_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= RETURN (e= expression[cfg, block] )? ) ;
	public final BasicBlock return_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree ast=null;
		Register e =null;

		try {
			// ILOCGenerator.g:435:4: ( ^(ast= RETURN (e= expression[cfg, block] )? ) )
			// ILOCGenerator.g:435:7: ^(ast= RETURN (e= expression[cfg, block] )? )
			{
			ast=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_stmt1433); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:435:20: (e= expression[cfg, block] )?
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==AND||(LA16_0 >= DIVIDE && LA16_0 <= DOT)||(LA16_0 >= EQ && LA16_0 <= FALSE)||(LA16_0 >= GE && LA16_0 <= ID)||(LA16_0 >= INTEGER && LA16_0 <= INVOKE)||LA16_0==LE||(LA16_0 >= LT && LA16_0 <= OR)||LA16_0==PLUS||(LA16_0 >= TIMES && LA16_0 <= TRUE)) ) {
					alt16=1;
				}
				switch (alt16) {
					case 1 :
						// ILOCGenerator.g:435:21: e= expression[cfg, block]
						{
						pushFollow(FOLLOW_expression_in_return_stmt1438);
						e=expression(cfg, block);
						state._fsp--;

						}
						break;

				}

				match(input, Token.UP, null); 
			}


			         IInstruction.STORERET storeret = new IInstruction.STORERET();
			         storeret.source = e;
			         block.addInstruction(storeret);
			         block.next.add(cfg.exitBlock);
			      
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
	// ILOCGenerator.g:444:1: invocation_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) ;
	public final BasicBlock invocation_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
		BasicBlock resultBlock =  null;


		CommonTree id=null;
		Register e =null;

		 int argIdx = 0; 
		try {
			// ILOCGenerator.g:447:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) )
			// ILOCGenerator.g:447:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_stmt1483); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_stmt1487); 
			 
			           
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_stmt1512); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:451:17: (e= expression[cfg, block] )*
				loop17:
				while (true) {
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==AND||(LA17_0 >= DIVIDE && LA17_0 <= DOT)||(LA17_0 >= EQ && LA17_0 <= FALSE)||(LA17_0 >= GE && LA17_0 <= ID)||(LA17_0 >= INTEGER && LA17_0 <= INVOKE)||LA17_0==LE||(LA17_0 >= LT && LA17_0 <= OR)||LA17_0==PLUS||(LA17_0 >= TIMES && LA17_0 <= TRUE)) ) {
						alt17=1;
					}

					switch (alt17) {
					case 1 :
						// ILOCGenerator.g:451:18: e= expression[cfg, block]
						{
						pushFollow(FOLLOW_expression_in_invocation_stmt1517);
						e=expression(cfg, block);
						state._fsp--;

						  
						            IInstruction.STOREOUTARGUMENT storeoutarument = new IInstruction.STOREOUTARGUMENT();
						            storeoutarument.source = e;
						            storeoutarument.argIdx = argIdx;
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
	};


	// $ANTLR start "lvalue"
	// ILOCGenerator.g:466:1: lvalue[CFG cfg, BasicBlock block, boolean nested] returns [\n Register resultRegister = null, \n boolean wasGlobal = false, \n boolean wasField = false,\n String fieldName = null,\n String globalName = null,\n String localName = null\n ] : (id= ID | ^(ast= DOT l= lvalue[cfg, block, true] id= ID ) );
	public final ILOCGenerator.lvalue_return lvalue(CFG cfg, BasicBlock block, boolean nested) throws RecognitionException {
		ILOCGenerator.lvalue_return retval = new ILOCGenerator.lvalue_return();
		retval.start = input.LT(1);

		CommonTree id=null;
		CommonTree ast=null;
		TreeRuleReturnScope l =null;

		try {
			// ILOCGenerator.g:476:4: (id= ID | ^(ast= DOT l= lvalue[cfg, block, true] id= ID ) )
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
					// ILOCGenerator.g:476:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1575); 

					         if (cfg.locals.get((id!=null?id.getText():null)) != null) {
					            retval.resultRegister = cfg.locals.get((id!=null?id.getText():null));
					            retval.localName = (id!=null?id.getText():null);
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
					         }
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:494:7: ^(ast= DOT l= lvalue[cfg, block, true] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_lvalue1594); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_lvalue_in_lvalue1598);
					l=lvalue(cfg, block, true);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1603); 
					match(input, Token.UP, null); 

					         
					         if (nested) {
					            IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
					            loadaifield.source = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
					            loadaifield.fieldName = (id!=null?id.getText():null);
					            loadaifield.dest = Register.newRegister();
					            block.addInstruction(loadaifield);
					            retval.resultRegister = loadaifield.dest;
					         }
					         else {
					            retval.resultRegister = (l!=null?((ILOCGenerator.lvalue_return)l).resultRegister:null);
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



	// $ANTLR start "expression"
	// ILOCGenerator.g:512:1: expression[CFG cfg, BasicBlock block] returns [Register resultRegister = null] : ( ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) |e= invocation_exp[cfg, block] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL );
	public final Register expression(CFG cfg, BasicBlock block) throws RecognitionException {
		Register resultRegister =  null;


		CommonTree ast=null;
		CommonTree id=null;
		CommonTree i=null;
		Register lft =null;
		Register rht =null;
		Register e =null;

		try {
			// ILOCGenerator.g:514:4: ( ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) |e= invocation_exp[cfg, block] |id= ID |i= INTEGER |ast= TRUE |ast= FALSE | ^(ast= NEW id= ID ) |ast= NULL )
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
					// ILOCGenerator.g:514:7: ^( (ast= AND |ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:514:9: (ast= AND |ast= OR )
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
							// ILOCGenerator.g:514:10: ast= AND
							{
							ast=(CommonTree)match(input,AND,FOLLOW_AND_in_expression1640); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:514:20: ast= OR
							{
							ast=(CommonTree)match(input,OR,FOLLOW_OR_in_expression1646); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1660);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1665);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         Register leftReg = lft;
					         Register rightReg = rht;
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
					         resultRegister = result;
					      
					}
					break;
				case 2 :
					// ILOCGenerator.g:538:7: ^( (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:538:9: (ast= EQ |ast= LT |ast= GT |ast= NE |ast= LE |ast= GE )
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
							// ILOCGenerator.g:538:10: ast= EQ
							{
							ast=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expression1691); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:538:19: ast= LT
							{
							ast=(CommonTree)match(input,LT,FOLLOW_LT_in_expression1697); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:538:28: ast= GT
							{
							ast=(CommonTree)match(input,GT,FOLLOW_GT_in_expression1703); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:538:37: ast= NE
							{
							ast=(CommonTree)match(input,NE,FOLLOW_NE_in_expression1709); 
							}
							break;
						case 5 :
							// ILOCGenerator.g:538:46: ast= LE
							{
							ast=(CommonTree)match(input,LE,FOLLOW_LE_in_expression1715); 
							}
							break;
						case 6 :
							// ILOCGenerator.g:538:55: ast= GE
							{
							ast=(CommonTree)match(input,GE,FOLLOW_GE_in_expression1721); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1735);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1740);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         resultRegister = Register.newRegister();

					         IInstruction.COMP comp = new IInstruction.COMP();
					         comp.sourceA = lft;
					         comp.sourceB = rht;
					         block.addInstruction(comp);

					         IInstruction.LOADI loadi = new IInstruction.LOADI();
					         loadi.immediate = 0;
					         loadi.dest = resultRegister;
					         block.addInstruction(loadi);
					         switch ((ast!=null?ast.getText():null)) {
					            case "==":
					               IInstruction.MOVEQ moveq = new IInstruction.MOVEQ();
					               moveq.immediate = 1;
					               moveq.dest = resultRegister;
					               block.addInstruction(moveq);
					               break;
					            case "<":
					               IInstruction.MOVLT movlt = new IInstruction.MOVLT();
					               movlt.immediate = 1;
					               movlt.dest = resultRegister;
					               block.addInstruction(movlt);
					               break;
					            case ">":
					               IInstruction.MOVGT movgt = new IInstruction.MOVGT();
					               movgt.immediate = 1;
					               movgt.dest = resultRegister;
					               block.addInstruction(movgt);
					               break;
					            case "!=":
					               IInstruction.MOVNE movne = new IInstruction.MOVNE();
					               movne.immediate = 1;
					               movne.dest = resultRegister;
					               block.addInstruction(movne);
					               break;
					            case "<=":
					               IInstruction.MOVLE movle = new IInstruction.MOVLE();
					               movle.immediate = 1;
					               movle.dest = resultRegister;
					               block.addInstruction(movle);
					               break;
					            case ">=":
					               IInstruction.MOVGE movge = new IInstruction.MOVGE();
					               movge.immediate = 1;
					               movge.dest = resultRegister;
					               block.addInstruction(movge);
					               break;
					         }
					      
					}
					break;
				case 3 :
					// ILOCGenerator.g:592:7: ^( (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] )
					{
					// ILOCGenerator.g:592:9: (ast= PLUS |ast= MINUS |ast= TIMES |ast= DIVIDE )
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
							// ILOCGenerator.g:592:10: ast= PLUS
							{
							ast=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expression1766); 
							}
							break;
						case 2 :
							// ILOCGenerator.g:592:21: ast= MINUS
							{
							ast=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression1772); 
							}
							break;
						case 3 :
							// ILOCGenerator.g:592:33: ast= TIMES
							{
							ast=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expression1778); 
							}
							break;
						case 4 :
							// ILOCGenerator.g:592:45: ast= DIVIDE
							{
							ast=(CommonTree)match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1784); 
							}
							break;

					}

					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1798);
					lft=expression(cfg, block);
					state._fsp--;

					pushFollow(FOLLOW_expression_in_expression1803);
					rht=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         Register leftReg = lft;
					         Register rightReg = rht;
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
					         resultRegister = result;
					      
					}
					break;
				case 4 :
					// ILOCGenerator.g:628:7: ^(ast= NOT e= expression[cfg, block] )
					{
					ast=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expression1824); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1828);
					e=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         IInstruction.XORI xori = new IInstruction.XORI();
					         xori.source = e;
					         xori.immediate = 1;
					         xori.dest = Register.newRegister();
					         block.addInstruction(xori);
					         resultRegister = xori.dest;
					      
					}
					break;
				case 5 :
					// ILOCGenerator.g:637:7: ^(ast= NEG e= expression[cfg, block] )
					{
					ast=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expression1849); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1853);
					e=expression(cfg, block);
					state._fsp--;

					match(input, Token.UP, null); 


					         IInstruction.LOADI loadi = new IInstruction.LOADI();
					         loadi.immediate = -1;
					         loadi.dest = Register.newRegister();
					         block.addInstruction(loadi);
					         IInstruction.MULT mult = new IInstruction.MULT();
					         mult.sourceA = e;
					         mult.sourceB = loadi.dest;
					         mult.dest = loadi.dest;
					         block.addInstruction(mult);
					         resultRegister = mult.dest;
					      
					}
					break;
				case 6 :
					// ILOCGenerator.g:650:7: ^(ast= DOT e= expression[cfg, block] id= ID )
					{
					ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_expression1874); 
					match(input, Token.DOWN, null); 
					pushFollow(FOLLOW_expression_in_expression1878);
					e=expression(cfg, block);
					state._fsp--;

					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1883); 
					match(input, Token.UP, null); 


					         IInstruction.LOADAIFIELD loadaifield = new IInstruction.LOADAIFIELD();
					         loadaifield.source = e;
					         loadaifield.fieldName = (id!=null?id.getText():null);
					         loadaifield.dest = Register.newRegister();
					         block.addInstruction(loadaifield);
					         resultRegister = loadaifield.dest;
					      
					}
					break;
				case 7 :
					// ILOCGenerator.g:659:7: e= invocation_exp[cfg, block]
					{
					pushFollow(FOLLOW_invocation_exp_in_expression1902);
					e=invocation_exp(cfg, block);
					state._fsp--;


					         IInstruction.LOADRET loadret = new IInstruction.LOADRET();
					         loadret.dest = Register.newRegister();
					         block.addInstruction(loadret);
					         resultRegister = loadret.dest;
					      
					}
					break;
				case 8 :
					// ILOCGenerator.g:666:7: id= ID
					{
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1922); 
					              
					         if (cfg.locals.get((id!=null?id.getText():null)) != null) {
					            resultRegister = cfg.locals.get((id!=null?id.getText():null));
					         }
					         else {
					            IInstruction.LOADGLOBAL instruction = new IInstruction.LOADGLOBAL();
					            instruction.globalName = (id!=null?id.getText():null);
					            instruction.dest = Register.newRegister();
					            block.addInstruction(instruction);
					            resultRegister = instruction.dest;
					         }
					      
					}
					break;
				case 9 :
					// ILOCGenerator.g:679:7: i= INTEGER
					{
					i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1940); 

					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = Integer.parseInt((i!=null?i.getText():null));
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         resultRegister = instruction.dest;
					      
					}
					break;
				case 10 :
					// ILOCGenerator.g:687:7: ast= TRUE
					{
					ast=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression1958); 
					 
					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 1;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         resultRegister = instruction.dest;  
					      
					}
					break;
				case 11 :
					// ILOCGenerator.g:695:7: ast= FALSE
					{
					ast=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression1976); 
					  
					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 0;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         resultRegister = instruction.dest;
					      
					}
					break;
				case 12 :
					// ILOCGenerator.g:703:7: ^(ast= NEW id= ID )
					{
					ast=(CommonTree)match(input,NEW,FOLLOW_NEW_in_expression1995); 
					match(input, Token.DOWN, null); 
					id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1999); 
					match(input, Token.UP, null); 


					         IInstruction.NEW instruction = new IInstruction.NEW();
					         instruction.struct = (MiniType.StructType)structTypes.get((id!=null?id.getText():null));;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         resultRegister = instruction.dest;
					      
					}
					break;
				case 13 :
					// ILOCGenerator.g:711:7: ast= NULL
					{
					ast=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2018); 

					         IInstruction.LOADI instruction = new IInstruction.LOADI();
					         instruction.immediate = 0;
					         instruction.dest = Register.newRegister();
					         block.addInstruction(instruction);
					         resultRegister = instruction.dest;
					      
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
		return resultRegister;
	}
	// $ANTLR end "expression"



	// $ANTLR start "invocation_exp"
	// ILOCGenerator.g:721:1: invocation_exp[CFG cfg, BasicBlock block] returns [Register resultRegister = null] : ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) ;
	public final Register invocation_exp(CFG cfg, BasicBlock block) throws RecognitionException {
		Register resultRegister =  null;


		CommonTree id=null;
		Register e =null;

		 int argIdx = 0; 
		try {
			// ILOCGenerator.g:724:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) )
			// ILOCGenerator.g:724:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
			{
			match(input,INVOKE,FOLLOW_INVOKE_in_invocation_exp2059); 
			match(input, Token.DOWN, null); 
			id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_exp2063); 
			 
			            
			         
			match(input,ARGS,FOLLOW_ARGS_in_invocation_exp2083); 
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); 
				// ILOCGenerator.g:728:14: (e= expression[cfg, block] )*
				loop23:
				while (true) {
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==AND||(LA23_0 >= DIVIDE && LA23_0 <= DOT)||(LA23_0 >= EQ && LA23_0 <= FALSE)||(LA23_0 >= GE && LA23_0 <= ID)||(LA23_0 >= INTEGER && LA23_0 <= INVOKE)||LA23_0==LE||(LA23_0 >= LT && LA23_0 <= OR)||LA23_0==PLUS||(LA23_0 >= TIMES && LA23_0 <= TRUE)) ) {
						alt23=1;
					}

					switch (alt23) {
					case 1 :
						// ILOCGenerator.g:728:15: e= expression[cfg, block]
						{
						pushFollow(FOLLOW_expression_in_invocation_exp2088);
						e=expression(cfg, block);
						state._fsp--;

						  
						            IInstruction.STOREOUTARGUMENT storeoutarument = new IInstruction.STOREOUTARGUMENT();
						            storeoutarument.source = e;
						            storeoutarument.argIdx = argIdx;
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


			         IInstruction.CALL call = new IInstruction.CALL();
			         call.label = (id!=null?id.getText():null);
			         block.addInstruction(call);
			      
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return resultRegister;
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
	public static final BitSet FOLLOW_parameters_in_function597 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_return_type_in_function624 = new BitSet(new long[]{0x0008000000002000L});
	public static final BitSet FOLLOW_declarations_in_function637 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_statement_list_in_function651 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARAMS_in_parameters697 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_param_decl_in_parameters702 = new BitSet(new long[]{0x0000000000000808L});
	public static final BitSet FOLLOW_DECL_in_param_decl748 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TYPE_in_param_decl751 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_in_param_decl755 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ID_in_param_decl760 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETTYPE_in_return_type786 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_rtype_in_return_type791 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_type_in_rtype813 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_rtype823 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement852 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_in_statement880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_read_in_statement894 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditional_in_statement908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_loop_in_statement922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_stmt_in_statement950 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_invocation_stmt_in_statement964 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BLOCK_in_block1006 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_list_in_block1010 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_STMTS_in_statement_list1052 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_statement_in_statement_list1057 = new BitSet(new long[]{0x04014800240040C8L});
	public static final BitSet FOLLOW_ASSIGN_in_assignment1098 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_assignment1102 = new BitSet(new long[]{0x0000000002010000L});
	public static final BitSet FOLLOW_lvalue_in_assignment1107 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRINT_in_print1152 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_print1156 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_ENDL_in_print1160 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_READ_in_read1200 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_read1204 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_conditional1253 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_conditional1257 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_conditional1262 = new BitSet(new long[]{0x0000000000000088L});
	public static final BitSet FOLLOW_block_in_conditional1268 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHILE_in_loop1343 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_loop1347 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_block_in_loop1352 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_loop1355 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete1392 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_delete1396 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RETURN_in_return_stmt1433 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_return_stmt1438 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_stmt1483 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_stmt1487 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_stmt1512 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_stmt1517 = new BitSet(new long[]{0x006005FEB3998018L});
	public static final BitSet FOLLOW_ID_in_lvalue1575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_lvalue1594 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_lvalue_in_lvalue1598 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_lvalue1603 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_AND_in_expression1640 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_OR_in_expression1646 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1660 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1665 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_expression1691 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LT_in_expression1697 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GT_in_expression1703 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_NE_in_expression1709 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LE_in_expression1715 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GE_in_expression1721 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1735 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1740 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PLUS_in_expression1766 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_MINUS_in_expression1772 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TIMES_in_expression1778 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_DIVIDE_in_expression1784 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1798 = new BitSet(new long[]{0x006005FEB3998010L});
	public static final BitSet FOLLOW_expression_in_expression1803 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expression1824 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1828 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEG_in_expression1849 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1853 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DOT_in_expression1874 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_expression1878 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_ID_in_expression1883 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_invocation_exp_in_expression1902 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_expression1922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_expression1940 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_expression1958 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_expression1976 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEW_in_expression1995 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_expression1999 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULL_in_expression2018 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INVOKE_in_invocation_exp2059 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ID_in_invocation_exp2063 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ARGS_in_invocation_exp2083 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expression_in_invocation_exp2088 = new BitSet(new long[]{0x006005FEB3998018L});
}
