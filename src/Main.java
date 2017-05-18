package snl2fl;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.JSONException;

import snl2fl.fl.patterns.Pattern;
import snl2fl.ltl.LTLContext;
import snl2fl.ltl.LTLTranslator;
import snl2fl.ltl.nusmv.NuSMVTranslator;
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

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("Usage: RequirementsParser <filePath> <outdir>");
            System.exit(0);
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
            NuSMVTranslator nuSMVTranslator = new NuSMVTranslator(ltltranslator);
            PrintStream ps = new PrintStream(new FileOutputStream(args[1] + "out.smv"));

            nuSMVTranslator.translate(ps);

            ps.close();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
