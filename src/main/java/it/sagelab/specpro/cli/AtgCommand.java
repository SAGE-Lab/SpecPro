package it.sagelab.specpro.cli;

import it.sagelab.specpro.atg.AutomaticTestGenerator;
import it.sagelab.specpro.atg.cache.MaxLengthCacheStrategy;
import it.sagelab.specpro.atg.cache.NoCacheStrategy;
import it.sagelab.specpro.atg.cache.RandomDeleteCacheStrategy;
import it.sagelab.specpro.atg.cache.ResetCacheStrategy;
import it.sagelab.specpro.atg.coverage.*;
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
                "Possible values are 'state', 'transition', 'condition', 'acceptance', 'as-state', 'as-transition' or 'as-condition'.");
        options.addOption(null, "conjunction", false,
                "Generates only one buchi automata with the conjunction of all the specifications");
        options.addOption(null, "cross-coverage", false, "Compute the coverage of the conjunction BA with the tests generated");
        options.addOption(null, "cache", true,"Set the cache strategy. " +
                "Possible values are 'reset', 'max-length', 'random-del' or 'no'.");
        options.addOption(null, "cache-maxLength", true, "Max path legnth stored in the cache (only for max-length strategy).");
        options.addOption(null, "expand-trans", false, "Expand implicit transitions in BAs before starting test case generation");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        AutomaticTestGenerator atg = new AutomaticTestGenerator();

        if(commandLine.hasOption("minLength")) {
            int minLength = Integer.parseInt(commandLine.getOptionValue("minLength"));
            atg.setMinLength(minLength);
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
                case "acceptance":
                    atg.setCoverageCriterion(new AcceptanceStateCoverage());
                    break;
                case "as-state":
                    atg.setCoverageCriterion(new CombinedCoverage(new StateCoverage(), new AcceptanceStateCoverage()));
                    break;
                case "as-transition":
                    atg.setCoverageCriterion(new CombinedCoverage(new TransitionCoverage(), new AcceptanceStateCoverage()));
                    break;
                case "as-condition":
                    atg.setCoverageCriterion(new CombinedCoverage(new ConditionCoverage(), new AcceptanceStateCoverage()));
                    break;
                default:
                    System.err.println("Value for option coverage not valid. See 'SpecPro atg --help'.");
                    System.exit(-1);
            }

        }

        if(commandLine.hasOption("cache")) {
            String value = commandLine.getOptionValue("cache");

            switch(value) {
                case "reset":
                    atg.getBaProductHandler().setCacheStrategy(new ResetCacheStrategy());
                    break;
                case "max-length":
                    int maxLength = 10;
                    if(commandLine.hasOption("cache-maxLength")) {
                        maxLength = Integer.parseInt(commandLine.getOptionValue("cache-maxLength"));
                    }
                    atg.getBaProductHandler().setCacheStrategy(new MaxLengthCacheStrategy(maxLength));
                    break;
                case "random-del":
                    long maxEntries = 1_000_000;
                    if(commandLine.hasOption("cache-maxEntries")) {
                        maxEntries = Long.parseLong("cache-maxLength");
                    }
                    atg.getBaProductHandler().setCacheStrategy(new RandomDeleteCacheStrategy(maxEntries));
                    break;
                case "no":
                    atg.getBaProductHandler().setCacheStrategy(new NoCacheStrategy());
                    break;
                default:
                    System.err.println("Value for option cache not valid. See 'SpecPro atg --help'.");
                    System.exit(-1);
            }
        }

        atg.parseRequirements(spec, commandLine.hasOption("conjunction"));

        if(commandLine.hasOption("expand-trans")) {
            atg.expandTransitions();
        }

        atg.generate(outStream);

        if(commandLine.hasOption("cross-coverage")) {
            atg.computeCrossCoverageWithConjBA(spec);
        }
    }
}
