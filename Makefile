FILES=Mini.java


Mini.class : antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.mini antlr.generated.json antlr.generated.type
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

clean:
	\rm *generated* MiniParser.java MiniLexer.java ToJSON.java Mini.tokens ToJSON.tokens *.class
