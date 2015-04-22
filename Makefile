FILES=Mini.java MiniType.java IInstruction.java Register.java

Mini.class : antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.mini antlr.generated.json antlr.generated.type antlr.generated.iloc
	touch antlr.generated

antlr.generated.mini : Mini.g
	java org.antlr.Tool Mini.g
	touch antlr.generated.mini

antlr.generated.json : ToJSON.g
	java org.antlr.Tool ToJSON.g
	touch antlr.generated.json

antlr.generated.type : TypeChecker.g
	java org.antlr.Tool TypeChecker.g
	touch antlr.generated.type

antlr.generated.iloc : ILOCGenerator.g
	java org.antlr.Tool ILOCGenerator.g
	touch antlr.generated.iloc

clean:
	\rm *generated* MiniParser.java MiniLexer.java ToJSON.java Mini.tokens ToJSON.tokens TypeChecker.java ILOCGenerator.java *.class TypeChecker.tokens ILOCGenerator.tokens 
