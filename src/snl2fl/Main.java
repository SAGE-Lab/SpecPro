package snl2fl;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.JSONException;

import snl2fl.fl.patterns.Pattern;
import snl2fl.ltl.LTLContext;
import snl2fl.ltl.LTLTranslator;
import snl2fl.ltl.nusmv.NuSMVTranslator;
import snl2fl.ltl.panda.PANDATranslator;
import snl2fl.ltl.aalta.AALTATranslator;

import snl2fl.req.expressions.VariableExpression;
import snl2fl.req.parser.RequirementsBuilder;
import snl2fl.req.parser.RequirementsGrammarLexer;
import snl2fl.req.parser.RequirementsGrammarParser;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.qualitative.QualitativeRequirement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class Main.
 * <img src="../docs-images/ltl-cd.png">
 * @author Simone Vuotto
 * @author Massimo Narizzano
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
           printCommandLine();
        }
        

        RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        RequirementsBuilder builder = new RequirementsBuilder();
        walker.walk(builder, parser.file());
        List<Requirement> requirements = builder.getRequirementList();
        Map<String, VariableExpression> symbolTable = builder.getSymbolTable();
	/*    	
    	
       	System.out.println("Requirements");
        for(Requirement r : requirements)
            System.out.println(r);
        System.out.println("Symbol Table");
        for(String var : symbolTable.keySet())
            System.out.println(var);
        */
        ArrayList<QualitativeRequirement> qualitativeRequirements = new ArrayList<>();
        for(Requirement r : requirements) {
            if (r instanceof QualitativeRequirement)
                qualitativeRequirements.add((QualitativeRequirement)r);
            else
                System.out.println("Requirement "+requirements.indexOf(r)+" is not a qualitative requirement");
        }
        try {
            LTLContext context = new LTLContext(symbolTable, LTLTranslator.computeRangeMap(qualitativeRequirements),
                                                Pattern.loadPatterns(Pattern.PATTERNS_FILE));
            LTLTranslator ltltranslator = new LTLTranslator(qualitativeRequirements, context);
        
            if (args.length == 2) {
            	System.out.println("Translating into NuSMV syntax");
            	NuSMVTranslator nuSMVTranslator = new NuSMVTranslator(ltltranslator);
        		PrintStream ps = new PrintStream(new FileOutputStream(args[1]));
    			nuSMVTranslator.translate(ps,"invar");
        		ps.close();
			} else if (args[2].equals("-panda")){
				System.out.println("Translating into PANDA syntax");
				PANDATranslator pandaTranslator = new PANDATranslator(ltltranslator);
        		PrintStream ps = new PrintStream(new FileOutputStream(args[1]));

        		pandaTranslator.translate(ps);

        		ps.close();

            } else if (args[2].equals("-noinvar")){
            	System.out.println("Translating into NuSMV syntax");
            	NuSMVTranslator nuSMVTranslator = new NuSMVTranslator(ltltranslator);
        		PrintStream ps = new PrintStream(new FileOutputStream(args[1]));
        		
    			nuSMVTranslator.translate(ps,"noinvar");            	
    			ps.close();
            } else if (args[2].equals("-aalta")){
				System.out.println("Translating into AALTA syntax");
				AALTATranslator aaltaTranslator = new AALTATranslator(ltltranslator);
        		PrintStream ps_direct = new PrintStream(new FileOutputStream(args[1] + ".direct"));
        		aaltaTranslator.translate(ps_direct,"direct");
        		PrintStream ps_negated = new PrintStream(new FileOutputStream(args[1] + ".negated"));
        		aaltaTranslator.translate(ps_negated,"negated");

        		ps_negated.close();
        		ps_direct.close();
            } else {
            	
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    
    
    private static void printCommandLine(){
    	System.out.println("Usage: RequirementsParser <filePath> <outfile> <options>");
        System.out.println("<options> : -panda   (write the out.panda in PANDA input format(default is nusmv))");
        System.out.println("            -noinvar (write the out.nusmv without INVAR(default is with invar)");
        System.exit(0);
    }
    
}
