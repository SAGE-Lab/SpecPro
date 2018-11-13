package it.sagelab.specpro.cli;

import it.sagelab.specpro.atg.AutomaticTestGenerator;
import it.sagelab.specpro.atg.coverage.ConditionCoverage;
import it.sagelab.specpro.atg.coverage.StateCoverage;
import it.sagelab.specpro.atg.coverage.TransitionCoverage;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;

public class AtgCommand extends Command {

    @Override
    public String getName() {
        return "atg";
    }

    @Override
    public String getDescription() {
        return "Automatic Test Genearation of the given specification (it requires Spot)";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();

        options.addOption(null, "minLength", true, "Min length of generated paths");
        options.addOption(null, "maxLength", true, "Max length of generated paths");
        options.addOption("c", "coverage", true, "Choose the coverage criteria. " +
                "Possible values are 'state', 'transition' (default) or 'condition'.");
        options.addOption(null, "disable-input-check", false,"Disable the check on input variables");
        options.addOption(null, "conjunction", false,
                "Generates only one buchi automata with the conjunction of all the specifications");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        AutomaticTestGenerator atg = new AutomaticTestGenerator();

        if(commandLine.hasOption("minLength")) {
            int minLength = Integer.parseInt(commandLine.getOptionValue("minLength"));
            atg.setMaxLength(minLength);
        }

        if(commandLine.hasOption("maxLength")) {
            int maxLength = Integer.parseInt(commandLine.getOptionValue("maxLength"));
            atg.setMaxLength(maxLength);
        }

        if(commandLine.hasOption("coverage")) {
            String value = commandLine.getOptionValue("coverage");

            switch (value) {
                case "state":
                    atg.setCoverageCriterion(new StateCoverage());
                    break;
                case "transition":
                    atg.setCoverageCriterion(new TransitionCoverage());
                    break;
                case "condition":
                    atg.setCoverageCriterion(new ConditionCoverage());
                    break;
                default:
                    System.err.println("Value for option coverage not valid. See 'SpecPro atg --help'.");
                    System.exit(-1);
            }

        }

        if(commandLine.hasOption("disable-input-check")) {
            atg.setFilterInputVars(false);
        }

        atg.parseRequirements(inputFile, commandLine.hasOption("conjunction"));
        atg.generate(outStream);
    }
}
