// $ANTLR 3.3 Nov 30, 2010 12:50:56 ILOCGenerator.g 2015-04-20 20:55:31

   import java.util.List;
   import java.util.LinkedList;
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.HashSet;
   import javax.json.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class ILOCGenerator extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STRUCT", "INT", "BOOL", "FUN", "VOID", "PRINT", "ENDL", "READ", "IF", "ELSE", "WHILE", "DELETE", "RETURN", "TRUE", "FALSE", "NEW", "NULL", "PROGRAM", "TYPES", "TYPE", "DECLS", "FUNCS", "DECL", "DECLLIST", "PARAMS", "RETTYPE", "BLOCK", "STMTS", "INVOKE", "ARGS", "NEG", "LBRACE", "RBRACE", "SEMI", "COMMA", "LPAREN", "RPAREN", "ASSIGN", "DOT", "AND", "OR", "EQ", "LT", "GT", "NE", "LE", "GE", "PLUS", "MINUS", "TIMES", "DIVIDE", "NOT", "ID", "INTEGER", "WS", "COMMENT"
    };
    public static final int EOF=-1;
    public static final int STRUCT=4;
    public static final int INT=5;
    public static final int BOOL=6;
    public static final int FUN=7;
    public static final int VOID=8;
    public static final int PRINT=9;
    public static final int ENDL=10;
    public static final int READ=11;
    public static final int IF=12;
    public static final int ELSE=13;
    public static final int WHILE=14;
    public static final int DELETE=15;
    public static final int RETURN=16;
    public static final int TRUE=17;
    public static final int FALSE=18;
    public static final int NEW=19;
    public static final int NULL=20;
    public static final int PROGRAM=21;
    public static final int TYPES=22;
    public static final int TYPE=23;
    public static final int DECLS=24;
    public static final int FUNCS=25;
    public static final int DECL=26;
    public static final int DECLLIST=27;
    public static final int PARAMS=28;
    public static final int RETTYPE=29;
    public static final int BLOCK=30;
    public static final int STMTS=31;
    public static final int INVOKE=32;
    public static final int ARGS=33;
    public static final int NEG=34;
    public static final int LBRACE=35;
    public static final int RBRACE=36;
    public static final int SEMI=37;
    public static final int COMMA=38;
    public static final int LPAREN=39;
    public static final int RPAREN=40;
    public static final int ASSIGN=41;
    public static final int DOT=42;
    public static final int AND=43;
    public static final int OR=44;
    public static final int EQ=45;
    public static final int LT=46;
    public static final int GT=47;
    public static final int NE=48;
    public static final int LE=49;
    public static final int GE=50;
    public static final int PLUS=51;
    public static final int MINUS=52;
    public static final int TIMES=53;
    public static final int DIVIDE=54;
    public static final int NOT=55;
    public static final int ID=56;
    public static final int INTEGER=57;
    public static final int WS=58;
    public static final int COMMENT=59;

    // delegates
    // delegators


        public ILOCGenerator(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public ILOCGenerator(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ILOCGenerator.tokenNames; }
    public String getGrammarFileName() { return "ILOCGenerator.g"; }


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



    // $ANTLR start "translate"
    // ILOCGenerator.g:79:1: translate : ^( PROGRAM t= types d= declarations[null] f= functions ) ;
    public final void translate() throws RecognitionException {
        try {
            // ILOCGenerator.g:80:4: ( ^( PROGRAM t= types d= declarations[null] f= functions ) )
            // ILOCGenerator.g:80:7: ^( PROGRAM t= types d= declarations[null] f= functions )
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
        }
        return ;
    }
    // $ANTLR end "translate"


    // $ANTLR start "types"
    // ILOCGenerator.g:86:1: types : ( ^( TYPES (t= type_decl )* ) | );
    public final void types() throws RecognitionException {
          
        try {
            // ILOCGenerator.g:88:4: ( ^( TYPES (t= type_decl )* ) | )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==TYPES) ) {
                alt2=1;
            }
            else if ( (LA2_0==UP||(LA2_0>=DECLS && LA2_0<=FUNCS)||LA2_0==STMTS) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ILOCGenerator.g:88:7: ^( TYPES (t= type_decl )* )
                    {
                    match(input,TYPES,FOLLOW_TYPES_in_types103); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // ILOCGenerator.g:88:15: (t= type_decl )*
                        loop1:
                        do {
                            int alt1=2;
                            int LA1_0 = input.LA(1);

                            if ( (LA1_0==STRUCT) ) {
                                alt1=1;
                            }


                            switch (alt1) {
                        	case 1 :
                        	    // ILOCGenerator.g:88:16: t= type_decl
                        	    {
                        	    pushFollow(FOLLOW_type_decl_in_types108);
                        	    type_decl();

                        	    state._fsp--;

                        	      

                        	    }
                        	    break;

                        	default :
                        	    break loop1;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                      

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:90:7: 
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
        }
        return ;
    }
    // $ANTLR end "types"


    // $ANTLR start "type_decl"
    // ILOCGenerator.g:93:1: type_decl : ^(ast= STRUCT id= ID n= nested_decl[structType] ) ;
    public final void type_decl() throws RecognitionException {
        CommonTree ast=null;
        CommonTree id=null;

         MiniType.StructType structType = new MiniType.StructType(); 
        try {
            // ILOCGenerator.g:95:4: ( ^(ast= STRUCT id= ID n= nested_decl[structType] ) )
            // ILOCGenerator.g:95:7: ^(ast= STRUCT id= ID n= nested_decl[structType] )
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
        }
        return ;
    }
    // $ANTLR end "type_decl"


    // $ANTLR start "nested_decl"
    // ILOCGenerator.g:107:1: nested_decl[MiniType.StructType structType] : (f= field_decl[structType] )+ ;
    public final void nested_decl(MiniType.StructType structType) throws RecognitionException {
        MiniType f = null;


          
        try {
            // ILOCGenerator.g:109:4: ( (f= field_decl[structType] )+ )
            // ILOCGenerator.g:109:7: (f= field_decl[structType] )+
            {
            // ILOCGenerator.g:109:7: (f= field_decl[structType] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==DECL) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ILOCGenerator.g:109:8: f= field_decl[structType]
            	    {
            	    pushFollow(FOLLOW_field_decl_in_nested_decl237);
            	    f=field_decl(structType);

            	    state._fsp--;

            	      

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nested_decl"


    // $ANTLR start "field_decl"
    // ILOCGenerator.g:112:1: field_decl[MiniType.StructType structType] returns [MiniType miniType = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
    public final MiniType field_decl(MiniType.StructType structType) throws RecognitionException {
        MiniType miniType =  null;

        CommonTree id=null;
        MiniType t = null;


        try {
            // ILOCGenerator.g:114:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
            // ILOCGenerator.g:114:7: ^( DECL ^( TYPE t= type ) id= ID )
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
        }
        return miniType;
    }
    // $ANTLR end "field_decl"


    // $ANTLR start "type"
    // ILOCGenerator.g:122:1: type returns [MiniType miniType = null] : ( INT | BOOL | ^( STRUCT id= ID ) );
    public final MiniType type() throws RecognitionException {
        MiniType miniType =  null;

        CommonTree id=null;

        try {
            // ILOCGenerator.g:124:4: ( INT | BOOL | ^( STRUCT id= ID ) )
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
                    // ILOCGenerator.g:124:7: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type312); 
                     miniType = MiniType.INT; 

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:125:7: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type322); 
                     miniType = MiniType.BOOL; 

                    }
                    break;
                case 3 :
                    // ILOCGenerator.g:126:7: ^( STRUCT id= ID )
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
        }
        return miniType;
    }
    // $ANTLR end "type"


    // $ANTLR start "declarations"
    // ILOCGenerator.g:132:1: declarations[CFG cfg] : ( ^( DECLS (d= decl_list[cfg] )* ) | );
    public final void declarations(CFG cfg) throws RecognitionException {
        try {
            // ILOCGenerator.g:134:4: ( ^( DECLS (d= decl_list[cfg] )* ) | )
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
                    // ILOCGenerator.g:134:7: ^( DECLS (d= decl_list[cfg] )* )
                    {
                    match(input,DECLS,FOLLOW_DECLS_in_declarations373); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // ILOCGenerator.g:134:15: (d= decl_list[cfg] )*
                        loop5:
                        do {
                            int alt5=2;
                            int LA5_0 = input.LA(1);

                            if ( (LA5_0==DECLLIST) ) {
                                alt5=1;
                            }


                            switch (alt5) {
                        	case 1 :
                        	    // ILOCGenerator.g:134:16: d= decl_list[cfg]
                        	    {
                        	    pushFollow(FOLLOW_decl_list_in_declarations378);
                        	    decl_list(cfg);

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop5;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                      

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:136:7: 
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
        }
        return ;
    }
    // $ANTLR end "declarations"


    // $ANTLR start "decl_list"
    // ILOCGenerator.g:139:1: decl_list[CFG cfg] : ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) ;
    public final void decl_list(CFG cfg) throws RecognitionException {
        CommonTree id=null;
        MiniType t = null;


        try {
            // ILOCGenerator.g:140:4: ( ^( DECLLIST ^( TYPE t= type ) (id= ID )+ ) )
            // ILOCGenerator.g:140:7: ^( DECLLIST ^( TYPE t= type ) (id= ID )+ )
            {
            match(input,DECLLIST,FOLLOW_DECLLIST_in_decl_list416); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_decl_list419); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_decl_list423);
            t=type();

            state._fsp--;


            match(input, Token.UP, null); 
            // ILOCGenerator.g:141:10: (id= ID )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ILOCGenerator.g:141:11: id= ID
            	    {
            	    id=(CommonTree)match(input,ID,FOLLOW_ID_in_decl_list438); 

            	                   if (cfg != null) {
            	                      cfg.locals.put((id!=null?id.getText():null), Register.newRegister());
            	                   }
            	                

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "decl_list"


    // $ANTLR start "functions"
    // ILOCGenerator.g:151:1: functions returns [] : ( ^( FUNCS (f= function )* ) | );
    public final void functions() throws RecognitionException {
          
        try {
            // ILOCGenerator.g:154:4: ( ^( FUNCS (f= function )* ) | )
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
                    // ILOCGenerator.g:154:7: ^( FUNCS (f= function )* )
                    {
                    match(input,FUNCS,FOLLOW_FUNCS_in_functions503); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // ILOCGenerator.g:154:15: (f= function )*
                        loop8:
                        do {
                            int alt8=2;
                            int LA8_0 = input.LA(1);

                            if ( (LA8_0==FUN) ) {
                                alt8=1;
                            }


                            switch (alt8) {
                        	case 1 :
                        	    // ILOCGenerator.g:154:16: f= function
                        	    {
                        	    pushFollow(FOLLOW_function_in_functions508);
                        	    function();

                        	    state._fsp--;

                        	      

                        	    }
                        	    break;

                        	default :
                        	    break loop8;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }
                     
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
                                // System.out.println(cfg.entryBlock.label);
                                // System.out.println(cfg.locals);
                                // System.out.println(cfg.params);
                             } 
                          

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:171:7: 
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
        }
        return ;
    }
    // $ANTLR end "functions"


    // $ANTLR start "function"
    // ILOCGenerator.g:174:1: function : ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) ;
    public final void function() throws RecognitionException {
        CommonTree ast=null;
        CommonTree id=null;
        BasicBlock p = null;

        BasicBlock s = null;


         
              CFG cfg = new CFG();
              Register.resetRegisters();
              BasicBlock exitBlock = new BasicBlock();
              exitBlock.label = getNextLabel();
              cfg.exitBlock = exitBlock;
           
        try {
            // ILOCGenerator.g:183:4: ( ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] ) )
            // ILOCGenerator.g:183:7: ^(ast= FUN id= ID p= parameters[cfg] r= return_type d= declarations[cfg] s= statement_list[cfg, cfg.entryBlock] )
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
        }
        return ;
    }
    // $ANTLR end "function"


    // $ANTLR start "parameters"
    // ILOCGenerator.g:198:1: parameters[CFG cfg] returns [BasicBlock entryBlock = null] : ^( PARAMS (p= param_decl[] )* ) ;
    public final BasicBlock parameters(CFG cfg) throws RecognitionException {
        BasicBlock entryBlock =  null;

        String p = null;


         int paramNum = 0; 
        try {
            // ILOCGenerator.g:201:4: ( ^( PARAMS (p= param_decl[] )* ) )
            // ILOCGenerator.g:201:7: ^( PARAMS (p= param_decl[] )* )
            {
            match(input,PARAMS,FOLLOW_PARAMS_in_parameters693); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // ILOCGenerator.g:201:16: (p= param_decl[] )*
                loop10:
                do {
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==DECL) ) {
                        alt10=1;
                    }


                    switch (alt10) {
                	case 1 :
                	    // ILOCGenerator.g:201:17: p= param_decl[]
                	    {
                	    pushFollow(FOLLOW_param_decl_in_parameters698);
                	    p=param_decl();

                	    state._fsp--;

                	     
                	             cfg.params.put(p, paramNum++);
                	             cfg.locals.put(p, Register.newRegister());         
                	             //TODO: Generate code for setting up params.
                	          

                	    }
                	    break;

                	default :
                	    break loop10;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
             
                     entryBlock = new BasicBlock(); 
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return entryBlock;
    }
    // $ANTLR end "parameters"


    // $ANTLR start "param_decl"
    // ILOCGenerator.g:212:1: param_decl[] returns [String paramId = null] : ^( DECL ^( TYPE t= type ) id= ID ) ;
    public final String param_decl() throws RecognitionException {
        String paramId =  null;

        CommonTree id=null;
        MiniType t = null;


        try {
            // ILOCGenerator.g:214:4: ( ^( DECL ^( TYPE t= type ) id= ID ) )
            // ILOCGenerator.g:214:7: ^( DECL ^( TYPE t= type ) id= ID )
            {
            match(input,DECL,FOLLOW_DECL_in_param_decl744); 

            match(input, Token.DOWN, null); 
            match(input,TYPE,FOLLOW_TYPE_in_param_decl747); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_param_decl751);
            t=type();

            state._fsp--;


            match(input, Token.UP, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_param_decl756); 

            match(input, Token.UP, null); 

                     paramId = (id!=null?id.getText():null);
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return paramId;
    }
    // $ANTLR end "param_decl"


    // $ANTLR start "return_type"
    // ILOCGenerator.g:220:1: return_type : ^( RETTYPE (r= rtype ) ) ;
    public final void return_type() throws RecognitionException {
        try {
            // ILOCGenerator.g:221:4: ( ^( RETTYPE (r= rtype ) ) )
            // ILOCGenerator.g:221:7: ^( RETTYPE (r= rtype ) )
            {
            match(input,RETTYPE,FOLLOW_RETTYPE_in_return_type782); 

            match(input, Token.DOWN, null); 
            // ILOCGenerator.g:221:17: (r= rtype )
            // ILOCGenerator.g:221:18: r= rtype
            {
            pushFollow(FOLLOW_rtype_in_return_type787);
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
        }
        return ;
    }
    // $ANTLR end "return_type"


    // $ANTLR start "rtype"
    // ILOCGenerator.g:224:1: rtype : (t= type | VOID );
    public final void rtype() throws RecognitionException {
        MiniType t = null;


        try {
            // ILOCGenerator.g:225:4: (t= type | VOID )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=STRUCT && LA11_0<=BOOL)) ) {
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
                    // ILOCGenerator.g:225:7: t= type
                    {
                    pushFollow(FOLLOW_type_in_rtype809);
                    t=type();

                    state._fsp--;

                      

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:226:7: VOID
                    {
                    match(input,VOID,FOLLOW_VOID_in_rtype819); 
                      

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rtype"


    // $ANTLR start "statement"
    // ILOCGenerator.g:229:1: statement[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : (s= block[cfg, block] | s= assignment[cfg, block] | s= print[cfg, block] | s= read[cfg, block] | s= conditional[cfg, block] | s= loop[cfg, block] | s= delete[cfg, block] | s= return_stmt[cfg, block] | s= invocation_stmt[cfg, block] ) ;
    public final BasicBlock statement(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        BasicBlock s = null;


        try {
            // ILOCGenerator.g:231:4: ( (s= block[cfg, block] | s= assignment[cfg, block] | s= print[cfg, block] | s= read[cfg, block] | s= conditional[cfg, block] | s= loop[cfg, block] | s= delete[cfg, block] | s= return_stmt[cfg, block] | s= invocation_stmt[cfg, block] ) )
            // ILOCGenerator.g:231:7: (s= block[cfg, block] | s= assignment[cfg, block] | s= print[cfg, block] | s= read[cfg, block] | s= conditional[cfg, block] | s= loop[cfg, block] | s= delete[cfg, block] | s= return_stmt[cfg, block] | s= invocation_stmt[cfg, block] )
            {
            // ILOCGenerator.g:231:7: (s= block[cfg, block] | s= assignment[cfg, block] | s= print[cfg, block] | s= read[cfg, block] | s= conditional[cfg, block] | s= loop[cfg, block] | s= delete[cfg, block] | s= return_stmt[cfg, block] | s= invocation_stmt[cfg, block] )
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
                    // ILOCGenerator.g:231:8: s= block[cfg, block]
                    {
                    pushFollow(FOLLOW_block_in_statement848);
                    s=block(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:232:10: s= assignment[cfg, block]
                    {
                    pushFollow(FOLLOW_assignment_in_statement862);
                    s=assignment(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ILOCGenerator.g:233:10: s= print[cfg, block]
                    {
                    pushFollow(FOLLOW_print_in_statement876);
                    s=print(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ILOCGenerator.g:234:10: s= read[cfg, block]
                    {
                    pushFollow(FOLLOW_read_in_statement890);
                    s=read(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // ILOCGenerator.g:235:10: s= conditional[cfg, block]
                    {
                    pushFollow(FOLLOW_conditional_in_statement904);
                    s=conditional(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // ILOCGenerator.g:236:10: s= loop[cfg, block]
                    {
                    pushFollow(FOLLOW_loop_in_statement918);
                    s=loop(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // ILOCGenerator.g:237:10: s= delete[cfg, block]
                    {
                    pushFollow(FOLLOW_delete_in_statement932);
                    s=delete(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // ILOCGenerator.g:238:10: s= return_stmt[cfg, block]
                    {
                    pushFollow(FOLLOW_return_stmt_in_statement946);
                    s=return_stmt(cfg, block);

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // ILOCGenerator.g:239:10: s= invocation_stmt[cfg, block]
                    {
                    pushFollow(FOLLOW_invocation_stmt_in_statement960);
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
        }
        return resultBlock;
    }
    // $ANTLR end "statement"


    // $ANTLR start "block"
    // ILOCGenerator.g:244:1: block[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( BLOCK s= statement_list[cfg, block] ) ;
    public final BasicBlock block(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        BasicBlock s = null;


        try {
            // ILOCGenerator.g:246:4: ( ^( BLOCK s= statement_list[cfg, block] ) )
            // ILOCGenerator.g:246:7: ^( BLOCK s= statement_list[cfg, block] )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block1002); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_statement_list_in_block1006);
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
        }
        return resultBlock;
    }
    // $ANTLR end "block"


    // $ANTLR start "statement_list"
    // ILOCGenerator.g:252:1: statement_list[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( STMTS (s= statement[cfg, currentBlock] )* ) ;
    public final BasicBlock statement_list(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        BasicBlock s = null;


         BasicBlock currentBlock = block; 
        try {
            // ILOCGenerator.g:255:4: ( ^( STMTS (s= statement[cfg, currentBlock] )* ) )
            // ILOCGenerator.g:255:7: ^( STMTS (s= statement[cfg, currentBlock] )* )
            {
            match(input,STMTS,FOLLOW_STMTS_in_statement_list1048); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // ILOCGenerator.g:255:15: (s= statement[cfg, currentBlock] )*
                loop13:
                do {
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==PRINT||(LA13_0>=READ && LA13_0<=IF)||(LA13_0>=WHILE && LA13_0<=RETURN)||LA13_0==BLOCK||LA13_0==INVOKE||LA13_0==ASSIGN) ) {
                        alt13=1;
                    }


                    switch (alt13) {
                	case 1 :
                	    // ILOCGenerator.g:255:16: s= statement[cfg, currentBlock]
                	    {
                	    pushFollow(FOLLOW_statement_in_statement_list1053);
                	    s=statement(cfg, currentBlock);

                	    state._fsp--;

                	             
                	             currentBlock = s;
                	          

                	    }
                	    break;

                	default :
                	    break loop13;
                    }
                } while (true);


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
        }
        return resultBlock;
    }
    // $ANTLR end "statement_list"


    // $ANTLR start "assignment"
    // ILOCGenerator.g:261:1: assignment[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block] ) ;
    public final BasicBlock assignment(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;

        try {
            // ILOCGenerator.g:263:4: ( ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block] ) )
            // ILOCGenerator.g:263:7: ^(ast= ASSIGN e= expression[cfg, block] l= lvalue[cfg, block] )
            {
            ast=(CommonTree)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1094); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_assignment1098);
            expression(cfg, block);

            state._fsp--;

            pushFollow(FOLLOW_lvalue_in_assignment1103);
            lvalue(cfg, block);

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
        }
        return resultBlock;
    }
    // $ANTLR end "assignment"


    // $ANTLR start "print"
    // ILOCGenerator.g:269:1: print[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) ;
    public final BasicBlock print(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;

          
        try {
            // ILOCGenerator.g:272:4: ( ^(ast= PRINT e= expression[cfg, block] ( ENDL )? ) )
            // ILOCGenerator.g:272:7: ^(ast= PRINT e= expression[cfg, block] ( ENDL )? )
            {
            ast=(CommonTree)match(input,PRINT,FOLLOW_PRINT_in_print1148); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_print1152);
            expression(cfg, block);

            state._fsp--;

            // ILOCGenerator.g:272:44: ( ENDL )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ENDL) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ILOCGenerator.g:272:45: ENDL
                    {
                    match(input,ENDL,FOLLOW_ENDL_in_print1156); 
                      

                    }
                    break;

            }


            match(input, Token.UP, null); 

                     resultBlock = block;
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return resultBlock;
    }
    // $ANTLR end "print"


    // $ANTLR start "read"
    // ILOCGenerator.g:278:1: read[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= READ l= lvalue[cfg, block] ) ;
    public final BasicBlock read(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;

        try {
            // ILOCGenerator.g:280:4: ( ^(ast= READ l= lvalue[cfg, block] ) )
            // ILOCGenerator.g:280:7: ^(ast= READ l= lvalue[cfg, block] )
            {
            ast=(CommonTree)match(input,READ,FOLLOW_READ_in_read1196); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_lvalue_in_read1200);
            lvalue(cfg, block);

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
        }
        return resultBlock;
    }
    // $ANTLR end "read"


    // $ANTLR start "conditional"
    // ILOCGenerator.g:286:1: conditional[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) ;
    public final BasicBlock conditional(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;
        BasicBlock t = null;

        BasicBlock e = null;



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
            // ILOCGenerator.g:300:4: ( ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? ) )
            // ILOCGenerator.g:300:7: ^(ast= IF g= expression[cfg, guardBlock] t= block[cfg, thenBlock] (e= block[cfg, elseBlock] )? )
            {
            ast=(CommonTree)match(input,IF,FOLLOW_IF_in_conditional1249); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_conditional1253);
            expression(cfg, guardBlock);

            state._fsp--;

            pushFollow(FOLLOW_block_in_conditional1258);
            t=block(cfg, thenBlock);

            state._fsp--;

            // ILOCGenerator.g:300:70: (e= block[cfg, elseBlock] )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==BLOCK) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ILOCGenerator.g:300:71: e= block[cfg, elseBlock]
                    {
                    pushFollow(FOLLOW_block_in_conditional1264);
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
        }
        return resultBlock;
    }
    // $ANTLR end "conditional"


    // $ANTLR start "loop"
    // ILOCGenerator.g:322:1: loop[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) ;
    public final BasicBlock loop(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;
        BasicBlock b = null;



              BasicBlock guardBlock = new BasicBlock();
              guardBlock.label = getNextLabel();
              BasicBlock afterBlock = new BasicBlock();
              afterBlock.label = getNextLabel();
              BasicBlock bodyBlock = new BasicBlock();
              bodyBlock.label = getNextLabel();
           
        try {
            // ILOCGenerator.g:333:4: ( ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] ) )
            // ILOCGenerator.g:333:7: ^(ast= WHILE e= expression[cfg, guardBlock] b= block[cfg, bodyBlock] expression[cfg, new BasicBlock()] )
            {
            ast=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_loop1339); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_loop1343);
            expression(cfg, guardBlock);

            state._fsp--;

            pushFollow(FOLLOW_block_in_loop1348);
            b=block(cfg, bodyBlock);

            state._fsp--;

            pushFollow(FOLLOW_expression_in_loop1351);
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
        }
        return resultBlock;
    }
    // $ANTLR end "loop"


    // $ANTLR start "delete"
    // ILOCGenerator.g:345:1: delete[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= DELETE e= expression[cfg, block] ) ;
    public final BasicBlock delete(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;

        try {
            // ILOCGenerator.g:347:4: ( ^(ast= DELETE e= expression[cfg, block] ) )
            // ILOCGenerator.g:347:7: ^(ast= DELETE e= expression[cfg, block] )
            {
            ast=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete1388); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_delete1392);
            expression(cfg, block);

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
        }
        return resultBlock;
    }
    // $ANTLR end "delete"


    // $ANTLR start "return_stmt"
    // ILOCGenerator.g:353:1: return_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^(ast= RETURN (e= expression[cfg, block] )? ) ;
    public final BasicBlock return_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree ast=null;

        try {
            // ILOCGenerator.g:355:4: ( ^(ast= RETURN (e= expression[cfg, block] )? ) )
            // ILOCGenerator.g:355:7: ^(ast= RETURN (e= expression[cfg, block] )? )
            {
            ast=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_stmt1429); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // ILOCGenerator.g:355:20: (e= expression[cfg, block] )?
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=TRUE && LA16_0<=NULL)||LA16_0==INVOKE||LA16_0==NEG||(LA16_0>=DOT && LA16_0<=INTEGER)) ) {
                    alt16=1;
                }
                switch (alt16) {
                    case 1 :
                        // ILOCGenerator.g:355:21: e= expression[cfg, block]
                        {
                        pushFollow(FOLLOW_expression_in_return_stmt1434);
                        expression(cfg, block);

                        state._fsp--;


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

                     block.next.add(cfg.exitBlock);
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return resultBlock;
    }
    // $ANTLR end "return_stmt"


    // $ANTLR start "invocation_stmt"
    // ILOCGenerator.g:361:1: invocation_stmt[CFG cfg, BasicBlock block] returns [BasicBlock resultBlock = null] : ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) ;
    public final BasicBlock invocation_stmt(CFG cfg, BasicBlock block) throws RecognitionException {
        BasicBlock resultBlock =  null;

        CommonTree id=null;

         int argIdx = 0; 
        try {
            // ILOCGenerator.g:364:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) )
            // ILOCGenerator.g:364:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
            {
            match(input,INVOKE,FOLLOW_INVOKE_in_invocation_stmt1479); 

            match(input, Token.DOWN, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_stmt1483); 
             
                       
                     
            match(input,ARGS,FOLLOW_ARGS_in_invocation_stmt1508); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // ILOCGenerator.g:368:17: (e= expression[cfg, block] )*
                loop17:
                do {
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=TRUE && LA17_0<=NULL)||LA17_0==INVOKE||LA17_0==NEG||(LA17_0>=DOT && LA17_0<=INTEGER)) ) {
                        alt17=1;
                    }


                    switch (alt17) {
                	case 1 :
                	    // ILOCGenerator.g:368:18: e= expression[cfg, block]
                	    {
                	    pushFollow(FOLLOW_expression_in_invocation_stmt1513);
                	    expression(cfg, block);

                	    state._fsp--;

                	      
                	                   
                	                

                	    }
                	    break;

                	default :
                	    break loop17;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            match(input, Token.UP, null); 

                     resultBlock = block;
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return resultBlock;
    }
    // $ANTLR end "invocation_stmt"


    // $ANTLR start "lvalue"
    // ILOCGenerator.g:377:1: lvalue[CFG cfg, BasicBlock block] returns [] : (id= ID | ^(ast= DOT l= lvalue[cfg, block] id= ID ) );
    public final void lvalue(CFG cfg, BasicBlock block) throws RecognitionException {
        CommonTree id=null;
        CommonTree ast=null;

        try {
            // ILOCGenerator.g:379:4: (id= ID | ^(ast= DOT l= lvalue[cfg, block] id= ID ) )
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
                    // ILOCGenerator.g:379:7: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1567); 

                             
                          

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:383:7: ^(ast= DOT l= lvalue[cfg, block] id= ID )
                    {
                    ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_lvalue1586); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_lvalue_in_lvalue1590);
                    lvalue(cfg, block);

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_lvalue1595); 

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
        }
        return ;
    }
    // $ANTLR end "lvalue"


    // $ANTLR start "expression"
    // ILOCGenerator.g:389:1: expression[CFG cfg, BasicBlock block] returns [] : ( ^( (ast= AND | ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ | ast= LT | ast= GT | ast= NE | ast= LE | ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS | ast= MINUS | ast= TIMES | ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) | e= invocation_exp[cfg, block] | id= ID | i= INTEGER | ast= TRUE | ast= FALSE | ^(ast= NEW id= ID ) | ast= NULL );
    public final void expression(CFG cfg, BasicBlock block) throws RecognitionException {
        CommonTree ast=null;
        CommonTree id=null;
        CommonTree i=null;

        try {
            // ILOCGenerator.g:391:4: ( ^( (ast= AND | ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= EQ | ast= LT | ast= GT | ast= NE | ast= LE | ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^( (ast= PLUS | ast= MINUS | ast= TIMES | ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] ) | ^(ast= NOT e= expression[cfg, block] ) | ^(ast= NEG e= expression[cfg, block] ) | ^(ast= DOT e= expression[cfg, block] id= ID ) | e= invocation_exp[cfg, block] | id= ID | i= INTEGER | ast= TRUE | ast= FALSE | ^(ast= NEW id= ID ) | ast= NULL )
            int alt22=13;
            switch ( input.LA(1) ) {
            case AND:
            case OR:
                {
                alt22=1;
                }
                break;
            case EQ:
            case LT:
            case GT:
            case NE:
            case LE:
            case GE:
                {
                alt22=2;
                }
                break;
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDE:
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
                    // ILOCGenerator.g:391:7: ^( (ast= AND | ast= OR ) lft= expression[cfg, block] rht= expression[cfg, block] )
                    {
                    // ILOCGenerator.g:391:9: (ast= AND | ast= OR )
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
                            // ILOCGenerator.g:391:10: ast= AND
                            {
                            ast=(CommonTree)match(input,AND,FOLLOW_AND_in_expression1632); 

                            }
                            break;
                        case 2 :
                            // ILOCGenerator.g:391:20: ast= OR
                            {
                            ast=(CommonTree)match(input,OR,FOLLOW_OR_in_expression1638); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1652);
                    expression(cfg, block);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1657);
                    expression(cfg, block);

                    state._fsp--;


                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 2 :
                    // ILOCGenerator.g:397:7: ^( (ast= EQ | ast= LT | ast= GT | ast= NE | ast= LE | ast= GE ) lft= expression[cfg, block] rht= expression[cfg, block] )
                    {
                    // ILOCGenerator.g:397:9: (ast= EQ | ast= LT | ast= GT | ast= NE | ast= LE | ast= GE )
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
                            // ILOCGenerator.g:397:10: ast= EQ
                            {
                            ast=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expression1683); 

                            }
                            break;
                        case 2 :
                            // ILOCGenerator.g:397:19: ast= LT
                            {
                            ast=(CommonTree)match(input,LT,FOLLOW_LT_in_expression1689); 

                            }
                            break;
                        case 3 :
                            // ILOCGenerator.g:397:28: ast= GT
                            {
                            ast=(CommonTree)match(input,GT,FOLLOW_GT_in_expression1695); 

                            }
                            break;
                        case 4 :
                            // ILOCGenerator.g:397:37: ast= NE
                            {
                            ast=(CommonTree)match(input,NE,FOLLOW_NE_in_expression1701); 

                            }
                            break;
                        case 5 :
                            // ILOCGenerator.g:397:46: ast= LE
                            {
                            ast=(CommonTree)match(input,LE,FOLLOW_LE_in_expression1707); 

                            }
                            break;
                        case 6 :
                            // ILOCGenerator.g:397:55: ast= GE
                            {
                            ast=(CommonTree)match(input,GE,FOLLOW_GE_in_expression1713); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1727);
                    expression(cfg, block);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1732);
                    expression(cfg, block);

                    state._fsp--;


                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 3 :
                    // ILOCGenerator.g:403:7: ^( (ast= PLUS | ast= MINUS | ast= TIMES | ast= DIVIDE ) lft= expression[cfg, block] rht= expression[cfg, block] )
                    {
                    // ILOCGenerator.g:403:9: (ast= PLUS | ast= MINUS | ast= TIMES | ast= DIVIDE )
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
                            // ILOCGenerator.g:403:10: ast= PLUS
                            {
                            ast=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expression1758); 

                            }
                            break;
                        case 2 :
                            // ILOCGenerator.g:403:21: ast= MINUS
                            {
                            ast=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression1764); 

                            }
                            break;
                        case 3 :
                            // ILOCGenerator.g:403:33: ast= TIMES
                            {
                            ast=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expression1770); 

                            }
                            break;
                        case 4 :
                            // ILOCGenerator.g:403:45: ast= DIVIDE
                            {
                            ast=(CommonTree)match(input,DIVIDE,FOLLOW_DIVIDE_in_expression1776); 

                            }
                            break;

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1790);
                    expression(cfg, block);

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1795);
                    expression(cfg, block);

                    state._fsp--;


                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 4 :
                    // ILOCGenerator.g:408:7: ^(ast= NOT e= expression[cfg, block] )
                    {
                    ast=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expression1816); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1820);
                    expression(cfg, block);

                    state._fsp--;


                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 5 :
                    // ILOCGenerator.g:412:7: ^(ast= NEG e= expression[cfg, block] )
                    {
                    ast=(CommonTree)match(input,NEG,FOLLOW_NEG_in_expression1841); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1845);
                    expression(cfg, block);

                    state._fsp--;


                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 6 :
                    // ILOCGenerator.g:416:7: ^(ast= DOT e= expression[cfg, block] id= ID )
                    {
                    ast=(CommonTree)match(input,DOT,FOLLOW_DOT_in_expression1866); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1870);
                    expression(cfg, block);

                    state._fsp--;

                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1875); 

                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 7 :
                    // ILOCGenerator.g:420:7: e= invocation_exp[cfg, block]
                    {
                    pushFollow(FOLLOW_invocation_exp_in_expression1894);
                    invocation_exp(cfg, block);

                    state._fsp--;


                             
                          

                    }
                    break;
                case 8 :
                    // ILOCGenerator.g:424:7: id= ID
                    {
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1914); 
                         
                                     
                          

                    }
                    break;
                case 9 :
                    // ILOCGenerator.g:428:7: i= INTEGER
                    {
                    i=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_expression1932); 

                             
                          

                    }
                    break;
                case 10 :
                    // ILOCGenerator.g:432:7: ast= TRUE
                    {
                    ast=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_expression1950); 
                     
                             
                          

                    }
                    break;
                case 11 :
                    // ILOCGenerator.g:436:7: ast= FALSE
                    {
                    ast=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_expression1968); 

                             
                          

                    }
                    break;
                case 12 :
                    // ILOCGenerator.g:440:7: ^(ast= NEW id= ID )
                    {
                    ast=(CommonTree)match(input,NEW,FOLLOW_NEW_in_expression1987); 

                    match(input, Token.DOWN, null); 
                    id=(CommonTree)match(input,ID,FOLLOW_ID_in_expression1991); 

                    match(input, Token.UP, null); 

                             
                          

                    }
                    break;
                case 13 :
                    // ILOCGenerator.g:444:7: ast= NULL
                    {
                    ast=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2010); 

                             
                          

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "invocation_exp"
    // ILOCGenerator.g:450:1: invocation_exp[CFG cfg, BasicBlock block] returns [] : ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) ;
    public final void invocation_exp(CFG cfg, BasicBlock block) throws RecognitionException {
        CommonTree id=null;

         int argIdx = 0; 
        try {
            // ILOCGenerator.g:453:4: ( ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) ) )
            // ILOCGenerator.g:453:7: ^( INVOKE id= ID ^( ARGS (e= expression[cfg, block] )* ) )
            {
            match(input,INVOKE,FOLLOW_INVOKE_in_invocation_exp2051); 

            match(input, Token.DOWN, null); 
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_invocation_exp2055); 
             
                        
                     
            match(input,ARGS,FOLLOW_ARGS_in_invocation_exp2075); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // ILOCGenerator.g:457:14: (e= expression[cfg, block] )*
                loop23:
                do {
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0>=TRUE && LA23_0<=NULL)||LA23_0==INVOKE||LA23_0==NEG||(LA23_0>=DOT && LA23_0<=INTEGER)) ) {
                        alt23=1;
                    }


                    switch (alt23) {
                	case 1 :
                	    // ILOCGenerator.g:457:15: e= expression[cfg, block]
                	    {
                	    pushFollow(FOLLOW_expression_in_invocation_exp2080);
                	    expression(cfg, block);

                	    state._fsp--;

                	      
                	                
                	             

                	    }
                	    break;

                	default :
                	    break loop23;
                    }
                } while (true);


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
        }
        return ;
    }
    // $ANTLR end "invocation_exp"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROGRAM_in_translate53 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_in_translate57 = new BitSet(new long[]{0x0000000003000008L});
    public static final BitSet FOLLOW_declarations_in_translate61 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_functions_in_translate66 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPES_in_types103 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_decl_in_types108 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_STRUCT_in_type_decl156 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type_decl170 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_nested_decl_in_type_decl199 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_field_decl_in_nested_decl237 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_DECL_in_field_decl268 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_field_decl271 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_field_decl275 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_field_decl280 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_type312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type333 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type337 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECLS_in_declarations373 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_list_in_declarations378 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_DECLLIST_in_decl_list416 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_decl_list419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_decl_list423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_decl_list438 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_FUNCS_in_functions503 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_function_in_functions508 = new BitSet(new long[]{0x0000000000000088L});
    public static final BitSet FOLLOW_FUN_in_function560 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_function574 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_parameters_in_function597 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_return_type_in_function624 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_declarations_in_function637 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_statement_list_in_function651 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARAMS_in_parameters693 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_decl_in_parameters698 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_DECL_in_param_decl744 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_param_decl747 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_param_decl751 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_param_decl756 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETTYPE_in_return_type782 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rtype_in_return_type787 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_type_in_rtype809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_rtype819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_statement876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_in_statement890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_statement904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_statement918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_in_statement932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_statement946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invocation_stmt_in_statement960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block1002 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_list_in_block1006 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STMTS_in_statement_list1048 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_statement_list1053 = new BitSet(new long[]{0x000002014001DA08L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment1094 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_assignment1098 = new BitSet(new long[]{0x0100040000000000L});
    public static final BitSet FOLLOW_lvalue_in_assignment1103 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRINT_in_print1148 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_print1152 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ENDL_in_print1156 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_READ_in_read1196 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_in_read1200 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_conditional1249 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_conditional1253 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_conditional1258 = new BitSet(new long[]{0x0000000040000008L});
    public static final BitSet FOLLOW_block_in_conditional1264 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_loop1339 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_loop1343 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_loop1348 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_loop1351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DELETE_in_delete1388 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_delete1392 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_return_stmt1429 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_return_stmt1434 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_invocation_stmt1479 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_invocation_stmt1483 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ARGS_in_invocation_stmt1508 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_invocation_stmt1513 = new BitSet(new long[]{0x03FFFC05001E0008L});
    public static final BitSet FOLLOW_ID_in_lvalue1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue1586 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_in_lvalue1590 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue1595 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression1632 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_OR_in_expression1638 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1652 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression1657 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expression1683 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LT_in_expression1689 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GT_in_expression1695 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NE_in_expression1701 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LE_in_expression1707 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GE_in_expression1713 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1727 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression1732 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression1758 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_MINUS_in_expression1764 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TIMES_in_expression1770 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_DIVIDE_in_expression1776 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1790 = new BitSet(new long[]{0x03FFFC05001E0000L});
    public static final BitSet FOLLOW_expression_in_expression1795 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expression1816 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1820 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEG_in_expression1841 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1845 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expression1866 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1870 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_expression1875 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_invocation_exp_in_expression1894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expression1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_expression1932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expression1950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_expression1968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_expression1987 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expression1991 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_expression2010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INVOKE_in_invocation_exp2051 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_invocation_exp2055 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ARGS_in_invocation_exp2075 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_invocation_exp2080 = new BitSet(new long[]{0x03FFFC05001E0008L});

}