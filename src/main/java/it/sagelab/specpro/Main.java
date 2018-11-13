package it.sagelab.specpro;

import it.sagelab.specpro.cli.*;
import it.sagelab.specpro.fe.snl2fl.Snl2FlException;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, Command> getCommands() {
        final Map<String, Command> commands = new HashMap<>();
        if(commands.isEmpty()) {
            Command translate = new TranslateCommand();
            Command consistency = new ConsistencyCommand();
            Command muc = new MucCommand();
            Command atg = new AtgCommand();

            commands.put(translate.getName(), translate);
            commands.put(consistency.getName(), consistency);
            commands.put(muc.getName(), muc);
            commands.put(atg.getName(), atg);
        }

        return commands;
    }

    public static Options getOptions() {
        final Options options = new Options();

        if(options.getOptions().size() == 0) {
            options.addOption("h", "help", false, "print the help message");
            options.addOption("i", "input", true, "Input file [required]");
            options.addOption("o", "output", true, "Output file");
        }

        return options;
    }

    public static void printHelp(CommandLine commandLine) {

        String [] args = commandLine.getArgs();
        Map<String, Command> commands = getCommands();

        if(args.length > 0 && commands.containsKey(args[0])) {
            Command command = commands.get(args[0]);
            System.out.println(command);
            new HelpFormatter().printHelp("SpecPro " + command.getName() + " [-h] [-o <outfile>] -i <infile> [OPTIONS]", command.createOptionMenu());
            System.exit(0);
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
            System.exit(0);
        }

    }

    public static void main(String[] args) {

        CommandLineParser commandLineParser = new DefaultParser();
        Options options = getOptions();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            String inputFile, outputFile;
            PrintStream outStream;

            if (commandLine.hasOption("h")) {
                printHelp(commandLine);
            }

            inputFile = commandLine.getOptionValue("i");

            if (commandLine.hasOption("o")) {
                outputFile = commandLine.getOptionValue("o");
                outStream = new PrintStream(outputFile);
            } else {
                outStream = System.out;
            }

            String [] args2 = commandLine.getArgs();
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

            command.setInputFile(inputFile);
            command.setOutStream(outStream);
            command.run(commandLine2);

        } catch (ParseException | IOException | Snl2FlException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println();
            System.exit(-1);
        }

    }
}
