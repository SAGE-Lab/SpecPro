package it.sagelab.specpro;

import it.sagelab.specpro.consistency.BinaryInconsistencyFinder;
import it.sagelab.specpro.consistency.ConsistencyChecker;
import it.sagelab.specpro.consistency.InconsistencyFinder;
import it.sagelab.specpro.consistency.LinearInconsistencyFinder;
import it.sagelab.specpro.fe.snl2fl.Snl2FlException;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.reasoners.Aalta;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.reasoners.Panda;
import it.sagelab.specpro.reasoners.translators.nusmv.NuSMVTranslator;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The Class Main.
 * <img src="../docs-images/ltl-cd.png">
 * @author Simone Vuotto
 * @author Massimo Narizzano
 */
public class Main {

    public static Options createOptionMenu() {
        // create Options for the consistency task
        Options options = new Options();
        options.addOption("h", "help", false, "print the help message");

        OptionGroup actionGroup = new OptionGroup();
        actionGroup.addOption(new Option("c", "consistency", false,
                "Performs Consistency Check with the selected model checker"));
        actionGroup.addOption(new Option("m", "muc", true,
                "Performs Minimaml Unsatisfiable Core of the given requirements set using the selected algorithm. " +
                "The possible values are 'linear' or 'binary'."));
        actionGroup.addOption(new Option("t", "translate", false,
                "Translate the input file in the corresponding consistency checking specification (dafault)"));
        options.addOptionGroup(actionGroup);


        OptionGroup mcGroup = new OptionGroup();
        mcGroup.addOption(new Option("a", "aalta", false, "translate for Aalta model checker. " +
                "In order to run consistency or muc tasks you have to set SPECPRO_AALTA environment variable."));
        mcGroup.addOption(new Option("p", "panda", false, "translate for Panda model checker"));
        mcGroup.addOption(new Option("n", "nusmv", false, "translate for NuSMV model checker (default). " +
                "In order to run consistency or muc tasks you have to set SPECPRO_NUSMV environment variable."));
        options.addOptionGroup(mcGroup);

        options.addOption(null, "noinvar", false, "translate without INVAR statemens (only for NuSMV)");
        options.addOption(null, "timeout", true, "The timeout for the model checker call in seconds");

        options.addRequiredOption("i", "input", true, "Input file [required]");
        options.addOption("o", "output", true, "Output file");

        return options;
    }

    public static ModelChecker selectModelChecker(CommandLine commandLine, long timeout) {
        if(commandLine.hasOption("a")) {
            return new Aalta(timeout);
        }
        else if(commandLine.hasOption("p")) {
            return new Panda(timeout);
        }
        else {
            NuSMVTranslator translator = new NuSMVTranslator().setNoinvar(commandLine.hasOption("noinvar"));
            NuSMV mc = new NuSMV(timeout);
            mc.setTranslator(translator);
            return mc;
        }
    }

    public static void main(String[] args) {

        CommandLineParser commandLineParser = new DefaultParser();
        Options options = createOptionMenu();

        try {
            CommandLine commandLine = commandLineParser.parse( options, args );
            String inputFile, outputFile;
            PrintStream outStream;
            int timeout = 60;

            if(commandLine.hasOption("h")) {
                new HelpFormatter().printHelp( "SpecPro [OPTIONS] -i <infile> [-o <outfile>]", options);
                System.exit(0);
            }

            inputFile = commandLine.getOptionValue("i");

            if(commandLine.hasOption("o")) {
                outputFile = commandLine.getOptionValue("o");
                outStream = new PrintStream(new FileOutputStream(outputFile));
            } else {
                outStream = System.out;
            }

            if(commandLine.hasOption("timeout")) {
                timeout = Integer.parseInt(commandLine.getOptionValue("timeout"));
            }

            Snl2FlParser snl2FlParser = new Snl2FlParser();
            ModelChecker mc = selectModelChecker(commandLine, timeout);

            snl2FlParser.parseFile(inputFile);

            if(commandLine.hasOption("m")) {
                String value = commandLine.getOptionValue("m");
                ConsistencyChecker consistencyChecker = new ConsistencyChecker(mc, snl2FlParser, "out.temp");
                InconsistencyFinder muc = null;
                if("linear".equals(value)) {
                    muc = new LinearInconsistencyFinder(consistencyChecker);
                } else if("binary".equals(value)) {
                    muc = new BinaryInconsistencyFinder(consistencyChecker);
                } else {
                   System.out.println("Value for option m not valid");
                   System.exit(-1);
                }
                List<Requirement> reqs = muc.run();
                if(reqs == null) {
                    outStream.println("Fail occured during model checking call.");
                    outStream.println(mc.getMessage());
                }
                else {
                    outStream.println("# MUC of " + reqs.size() + " elements found: ");
                    for (Requirement r : reqs) {
                        outStream.println(r.getText());
                    }
                }

            } else if(commandLine.hasOption("c")) {
                ConsistencyChecker consistencyChecker = new ConsistencyChecker(mc, snl2FlParser, "out.temp");
                ConsistencyChecker.Result result = consistencyChecker.run();
                outStream.println(result);
                if(result == ConsistencyChecker.Result.FAIL) {
                    System.out.println(mc.getMessage());
                }
            } else {
                // Translate
                snl2FlParser.translate(mc.getTranslator(), outStream);
            }

            Files.deleteIfExists(Paths.get("out.temp"));
            outStream.close();

        } catch (ParseException | IOException | Snl2FlException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println();
            new HelpFormatter().printHelp( "SpecPro [OPTIONS] -i <infile> [-o <outfile>]", options);
        }
    }

}
