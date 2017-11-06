package snl2fl;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.cli.*;
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

import java.io.FileNotFoundException;
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

    public static void main(String[] args) {

        CommandLineParser commandLineParser = new DefaultParser();

        // create the Options
        Options options = new Options();
        options.addOption("h", "help", false, "print the help message");

        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "aalta", false, "translate for Aalta model checker"));
        group.addOption(new Option("p", "panda", false, "translate for Panda model checker"));
        group.addOption(new Option("n", "nusmv", false, "translate for NuSMV model checker (default)"));
        options.addOptionGroup(group);

        options.addOption(null, "noinvar", false, "translate without INVAR statemens (only for NuSMV)");
        options.addOption(null, "negated", false, "translate with negated notation (only for Aalta)");

        try {
            CommandLine commandLine = commandLineParser.parse( options, args );
            String[] files = commandLine.getArgs();

            if(commandLine.hasOption("h") || files.length != 2) {
                new HelpFormatter().printHelp("snl2fl [OPTIONS] <infile> <outfile>", options);
                System.exit(0);
            }

            LTLTranslator ltltranslator = buildLTLTranslator(files[0]);
            PrintStream ps = new PrintStream(new FileOutputStream(files[1]));

            if(commandLine.hasOption("a")) {
                System.out.println("Translating into AALTA syntax");
                AALTATranslator aaltaTranslator = new AALTATranslator(ltltranslator);
                aaltaTranslator.setNegated(commandLine.hasOption("negated"));
                aaltaTranslator.translate(ps);
            }
            else if(commandLine.hasOption("p")) {
                System.out.println("Translating into PANDA syntax");
                PANDATranslator pandaTranslator = new PANDATranslator(ltltranslator);
                pandaTranslator.translate(ps);
            }
            else {
                System.out.println("Translating into NuSMV syntax");
                NuSMVTranslator nuSMVTranslator = new NuSMVTranslator(ltltranslator);
                nuSMVTranslator.setNoinvar(commandLine.hasOption("noinvar"));
                nuSMVTranslator.translate(ps);
            }

            ps.close();

        } catch (ParseException | IOException | JSONException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static LTLTranslator buildLTLTranslator(String infile) throws IOException, JSONException {
        RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(new ANTLRFileStream(infile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        RequirementsBuilder builder = new RequirementsBuilder();
        walker.walk(builder, parser.file());
        List<Requirement> requirements = builder.getRequirementList();
        Map<String, VariableExpression> symbolTable = builder.getSymbolTable();

        ArrayList<QualitativeRequirement> qualitativeRequirements = new ArrayList<>();
        for(Requirement r : requirements) {
            if (r instanceof QualitativeRequirement)
                qualitativeRequirements.add((QualitativeRequirement)r);
            else
                System.out.println("Requirement "+requirements.indexOf(r)+" is not a qualitative requirement, it is skipped.");
        }

        System.out.println("Processing " + qualitativeRequirements.size() + "requirements...");

        LTLContext context = new LTLContext(symbolTable, LTLTranslator.computeRangeMap(qualitativeRequirements),
                Pattern.loadPatterns(Pattern.PATTERNS_FILE));

        return new LTLTranslator(qualitativeRequirements, context);
    }

}
