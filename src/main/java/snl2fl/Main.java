package snl2fl;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
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

import java.io.*;
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

            CharStream inStream = new ANTLRFileStream(files[0]);
            OutputStream outStream = new FileOutputStream(files[1]);

            Snl2FlParser snl2FlParser = new Snl2FlParser();
            Snl2FlTranslator translator;

            if(commandLine.hasOption("a")) {
                System.out.println("Translating into AALTA syntax");
                translator = new AALTATranslator().setNegated(commandLine.hasOption("negated"));
            }
            else if(commandLine.hasOption("p")) {
                System.out.println("Translating into PANDA syntax");
                translator = new PANDATranslator();
            }
            else {
                System.out.println("Translating into NuSMV syntax");
                translator = new NuSMVTranslator().setNoinvar(commandLine.hasOption("noinvar"));
            }

            snl2FlParser.parse(inStream).translate(translator, outStream);

            outStream.close();

        } catch (ParseException | IOException | Snl2FlException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
