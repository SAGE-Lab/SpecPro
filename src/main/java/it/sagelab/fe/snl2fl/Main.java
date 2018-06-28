package it.sagelab.fe.snl2fl;

import org.apache.commons.cli.*;

import it.sagelab.reasoners.translators.nusmv.NuSMVTranslator;
import it.sagelab.reasoners.translators.panda.PANDATranslator;
import it.sagelab.reasoners.translators.aalta.AALTATranslator;

import java.io.*;

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
                new HelpFormatter().printHelp("it.sagelab.it.sagelab.fe.snl2fl [OPTIONS] <infile> <outfile>", options);
                System.exit(0);
            }

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

            OutputStream outStream = new FileOutputStream(files[1]);
            snl2FlParser.parseFile(files[0]).translate(translator, outStream);

            outStream.close();

        } catch (ParseException | IOException | Snl2FlException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
