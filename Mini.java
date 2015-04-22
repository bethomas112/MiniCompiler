import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;

import java.io.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Arrays;
import javax.json.JsonValue;
import javax.json.Json;

public class Mini
{
   public static void main(String[] args)
   {
      parseParameters(args);

      CommonTokenStream tokens = new CommonTokenStream(createLexer());
      MiniParser parser = new MiniParser(tokens);
      CommonTree tree = parse(parser);

      if (_displayAST && tree != null)
      {
         DOTTreeGenerator gen = new DOTTreeGenerator();
         StringTemplate st = gen.toDOT(tree);
         System.out.println(st);
      }
      else if (!parser.hasErrors())
      {
         typeCheck(tree, tokens);
         if (_dumpIL) {
            generateILOC(tree, tokens);
         }
      }
   }

   private static final String DISPLAYAST = "-displayAST";
   private static final String DUMPILOC = "-dumpIL";

   private static String _inputFile = null;
   private static boolean _displayAST = false;
   private static boolean _dumpIL = false;
   
   private static void parseParameters(String [] args)
   {
      for (int i = 0; i < args.length; i++)
      {
         if (args[i].equals(DISPLAYAST))
         {
            _displayAST = true;
         }
         else if (args[i].equals(DUMPILOC))
         {
            _dumpIL = true;
         }
         else if (args[i].charAt(0) == '-')
         {
            System.err.println("unexpected option: " + args[i]);
            System.exit(1);
         }
         else if (_inputFile != null)
        {
            System.err.println("too many files specified");
            System.exit(1);
         }
         else
         {
            _inputFile = args[i];
         }
      }
   }

   private static CommonTree parse(MiniParser parser)
   {
      try
      {
         MiniParser.program_return ret = parser.program();

         return (CommonTree)ret.getTree();
      }
      catch (org.antlr.runtime.RecognitionException e)
      {
         error(e.toString());
      }
      catch (Exception e)
      {
         System.exit(-1);
      }

      return null;
   }

   private static void typeCheck(CommonTree tree, CommonTokenStream tokens) {
      try
      {
         CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
         nodes.setTokenStream(tokens);
         TypeChecker tc = new TypeChecker(nodes);

         tc.translate();
      }
      catch (org.antlr.runtime.RecognitionException | TypeChecker.TypeException e)
      {
         error(e.getMessage());
      }
   }

   private static void generateILOC(CommonTree tree, CommonTokenStream tokens) {
      try
      {
         CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
         nodes.setTokenStream(tokens);
         ILOCGenerator igen = new ILOCGenerator(nodes);
         System.out.println(_inputFile);
         System.out.println(Arrays.toString(_inputFile.split("\\.")));
         igen.setOutputFile(new File(_inputFile.split("\\.")[0] + ".il"));
         igen.translate();
      }
      catch (org.antlr.runtime.RecognitionException e)
      {
         error(e.getMessage());
      }
   }

   private static JsonValue translate(CommonTree tree, CommonTokenStream tokens)
   {
      try
      {
         CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
         nodes.setTokenStream(tokens);
         ToJSON tparser = new ToJSON(nodes);

         return tparser.translate();
      }
      catch (org.antlr.runtime.RecognitionException e)
      {
         error(e.toString());
      }
      return Json.createObjectBuilder().build();
   }

   private static void error(String msg)
   {
      System.err.println(msg);
      System.exit(1);
   }

   private static MiniLexer createLexer()
   {
      try
      {
         ANTLRInputStream input;
         if (_inputFile == null)
         {
            input = new ANTLRInputStream(System.in);
         }
         else
         {
            input = new ANTLRInputStream(
               new BufferedInputStream(new FileInputStream(_inputFile)));
         }
         return new MiniLexer(input);
      }
      catch (java.io.IOException e)
      {
         System.err.println("file not found: " + _inputFile);
         System.exit(1);
         return null;
      }
   }
}
