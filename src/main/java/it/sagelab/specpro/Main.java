package it.sagelab.specpro;

import it.sagelab.specpro.cli.*;
import it.sagelab.specpro.fe.AbstractLTLFrontEnd;
import it.sagelab.specpro.fe.LTLFrontEnd;
import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.IntStream;

public class Main {

    private final static String VERSION_BORDER = "+-----------------------------------------------------------------------+";

    public static Map<String, Command> getCommands() {
        final Map<String, Command> commands = new HashMap<>();
        if(commands.isEmpty()) {
            Command translate = new TranslateCommand();
            Command consistency = new ConsistencyCommand();
            Command muc = new MucCommand();
            Command extractWords = new ExtractWordsCommand();
            Command testCaseGeneratorCommand = new TestCaseGeneratorCommand();

            commands.put(translate.getName(), translate);
            commands.put(consistency.getName(), consistency);
            commands.put(muc.getName(), muc);
            commands.put(extractWords.getName(), extractWords);
            commands.put(testCaseGeneratorCommand.getName(), testCaseGeneratorCommand);
        }

        return commands;
    }

    public static Options getOptions() {
        final Options options = new Options();

        if(options.getOptions().size() == 0) {
            options.addOption("h", "help", false, "Print the help message and exit");
            options.addOption(null, "version",false,"Print version information and exit");
            options.addOption("i", "input", true, "Input file [required]");
            options.addOption("o", "output", true, "Output file");
            options.addOption("f", "frontend", true, "Select input format to use. Possible values are: ltl, psp (default).");
            options.addOption("v", "verbose", false, "Print additional information");
        }

        return options;
    }

    public static void printHelp(CommandLine commandLine, String[] args) {

        Map<String, Command> commands = getCommands();

        if(args.length > 0 && commands.containsKey(args[0])) {
            Command command = commands.get(args[0]);
            System.out.println(command);
            new HelpFormatter().printHelp("SpecPro " + command.getName() + " [-h] [-o <outfile>] -i <infile> [OPTIONS]", command.createOptionMenu());
        }
        else {
            new HelpFormatter().printHelp("SpecPro [COMMAND] [-h] [-o <outfile>] -i <infile> [OPTIONS]", getOptions());
            System.out.println("Available commands: ");
            for(Command command: commands.values()) {
                System.out.print("  " + command.getName());
                for(int i = command.getName().length(); i < 15; ++i)
                    System.out.print(" ");
                System.out.println(command.getDescription());
                System.out.println();
            }
        }

    }

    public static void printVersionRow(String text) {
        int nSpaces = VERSION_BORDER.length() - text.length() - 2;
        System.out.print("|");
        IntStream.range(0, nSpaces / 2).forEach(i -> System.out.print(" "));
        System.out.print(text);
        IntStream.range(0, nSpaces / 2).forEach(i -> System.out.print(" "));
        if(nSpaces % 2 != 0) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static void printVersion() throws IOException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("version.properties");

        String version = "";

        if(input != null) {
            Properties versionProp = new Properties();
            versionProp.load(input);

            version = "v" + versionProp.getProperty("version");
            if("True".equals(versionProp.getProperty("snapshot"))) {
                version += "-SNAPSHOT";
            }
            version += " (build #" + versionProp.getProperty("build") + ")";
        } else {
            version = "version unknown";
        }


        System.out.println(VERSION_BORDER);
        printVersionRow("   _____                     ____                  ");
        printVersionRow("  / ___/ ____   ___   _____ / __ \\ _____ ____      ");
        printVersionRow("  \\__ \\ / __ \\ / _ \\ / ___// /_/ // ___// __ \\     ");
        printVersionRow(" ___/ // /_/ //  __// /__ / ____// /   / /_/ /     ");
        printVersionRow("/____// .___/ \\___/ \\___//_/    /_/    \\____/      ");
        printVersionRow("     /_/                                           ");
        printVersionRow("");
        printVersionRow(version);

        printVersionRow("Copyright (c) 2018 University of Genoa, University of Sassari.");
        printVersionRow("Mantainer: Simone Vuotto <svuotto@uniss.it>.");
        printVersionRow("Software released under GNU LGPLv3 license.");
        printVersionRow("This is free software: you are free to change and redistribute it.");
        printVersionRow("There is NO WARRANTY, to the extent permitted by law.");
        printVersionRow("");
        System.out.println(VERSION_BORDER);
        System.out.println();
    }

    public static void main(String[] args) {

        ExtendedParser commandLineParser = new ExtendedParser();
        Options options = getOptions();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args, false);
            String [] args2 = commandLineParser.getNotParsedArgs();
            String inputFile, outputFile;
            PrintStream outStream;
            AbstractLTLFrontEnd frontEnd;

            if (commandLine.hasOption("h")) {
                printHelp(commandLine, args2);
                System.exit(0);
            }

            if (commandLine.hasOption("version")) {
                printVersion();
                System.exit(0);
            }

            if(!commandLine.hasOption("i")) {
                throw new IOException("Input file not specified!");
            }
            inputFile = commandLine.getOptionValue("i");

            if (commandLine.hasOption("o")) {
                outputFile = commandLine.getOptionValue("o");
                outStream = new PrintStream(outputFile);
            } else {
                outStream = System.out;
            }

            if(commandLine.hasOption("fe")) {
                switch (commandLine.getOptionValue("fe")) {
                    case "ltl":
                        frontEnd = new LTLFrontEnd();
                        break;
                    case "psp":
                        frontEnd = new PSPFrontEnd();
                        break;
                    default:
                        throw  new IllegalArgumentException("Option " + commandLine.getOptionValue("fe") + " not recognized");
                }
            } else {
                frontEnd = new PSPFrontEnd();
            }

            if(commandLine.hasOption("v")) {
                Configurator.setRootLevel(Level.INFO);
            } else {
                Configurator.setRootLevel(Level.WARN);
            }


            if(args2.length == 0) {
                System.err.println("No command selected. See 'SpecPro --help'");
                System.exit(-1);
            }
            Map<String, Command> commands = getCommands();
            if(!commands.containsKey(args2[0])) {
                System.err.println("'" + args2[0] + "' is not a valid command. See 'SpecPro --help'");
                System.exit(-1);
            }


            Command command = commands.get(args2[0]);

            CommandLine commandLine2 = commandLineParser.parse(command.createOptionMenu(), args2);

            LTLSpec spec = frontEnd.parseFile(inputFile);

            command.setLTLSpec(spec);
            command.setOutStream(outStream);
            command.run(commandLine2);

        } catch (ParseException | IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println();
            System.exit(-1);
        }

    }
}
