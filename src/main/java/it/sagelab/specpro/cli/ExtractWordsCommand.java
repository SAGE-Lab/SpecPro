package it.sagelab.specpro.cli;

import it.sagelab.specpro.atg.*;
import it.sagelab.specpro.atg.cache.MaxLengthCacheStrategy;
import it.sagelab.specpro.atg.cache.NoCacheStrategy;
import it.sagelab.specpro.atg.cache.RandomDeleteCacheStrategy;
import it.sagelab.specpro.atg.cache.ResetCacheStrategy;
import it.sagelab.specpro.atg.coverage.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.Set;

public class ExtractWordsCommand extends Command {

    @Override
    public String getName() {
        return "extract-words";
    }

    @Override
    public String getDescription() {
        return "Extracts a set of words from the Buchi Automaton of the given specification, using " +
                "the given coverage criteria as a stopping condition (it requires Spot)";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();

        options.addOption(null, "minLength", true, "Min length of the generated words");
        options.addOption(null, "maxLength", true, "Max length of the generated words");
        options.addOption("c", "coverage", true, "Choose the coverage criteria. " +
                "Possible values are 'state', 'transition', 'acceptance', 'acc+state' or 'acc+transition'.");
        options.addOption(null, "cache", true,"Set the cache strategy. " +
                "Possible values are 'reset', 'max-length', 'random-del' or 'no'.");
        options.addOption(null, "cache-maxLength", true, "Max path length stored in the cache (only for max-length strategy).");
        options.addOption(null, "expand-trans", false, "Expand implicit transitions in BAs before starting test case generation");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        CoverageBesedWordsGenerator testGenerator  = new CoverageBesedWordsGenerator();

        if (commandLine.hasOption("minLength")) {
            int minLength = Integer.parseInt(commandLine.getOptionValue("minLength"));
            testGenerator.setMinLength(minLength);
        }

        if (commandLine.hasOption("maxLength")) {
            int maxLength = Integer.parseInt(commandLine.getOptionValue("maxLength"));
            testGenerator.setMaxLength(maxLength);
        }

        if (commandLine.hasOption("coverage")) {
            String value = commandLine.getOptionValue("coverage");

            switch (value) {
                case "state":
                    testGenerator.setCoverageCriterion(new StateCoverage());
                    break;
                case "transition":
                    testGenerator.setCoverageCriterion(new TransitionCoverage());
                    break;
                case "condition":
                    testGenerator.setCoverageCriterion(new ConditionCoverage());
                    break;
                case "acceptance":
                    testGenerator.setCoverageCriterion(new AcceptanceStateCoverage());
                    break;
                case "acc+state":
                    testGenerator.setCoverageCriterion(new CombinedCoverage(new StateCoverage(), new AcceptanceStateCoverage()));
                    break;
                case "acc+transition":
                    testGenerator.setCoverageCriterion(new CombinedCoverage(new TransitionCoverage(), new AcceptanceStateCoverage()));
                    break;
                case "acc+condition":
                    testGenerator.setCoverageCriterion(new CombinedCoverage(new ConditionCoverage(), new AcceptanceStateCoverage()));
                    break;
                default:
                    System.err.println("Value for option coverage not valid. See 'SpecPro atg --help'.");
                    System.exit(-1);
            }

        }

        if (commandLine.hasOption("cache")) {
            String value = commandLine.getOptionValue("cache");

            switch (value) {
                case "reset":
                    testGenerator.getBaProductHandler().setCacheStrategy(new ResetCacheStrategy());
                    break;
                case "max-length":
                    int maxLength = 10;
                    if (commandLine.hasOption("cache-maxLength")) {
                        maxLength = Integer.parseInt(commandLine.getOptionValue("cache-maxLength"));
                    }
                    testGenerator.getBaProductHandler().setCacheStrategy(new MaxLengthCacheStrategy(maxLength));
                    break;
                case "random-del":
                    long maxEntries = 1_000_000;
                    if (commandLine.hasOption("cache-maxEntries")) {
                        maxEntries = Long.parseLong("cache-maxLength");
                    }
                    testGenerator.getBaProductHandler().setCacheStrategy(new RandomDeleteCacheStrategy(maxEntries));
                    break;
                case "no":
                    testGenerator.getBaProductHandler().setCacheStrategy(new NoCacheStrategy());
                    break;
                default:
                    System.err.println("Value for option cache not valid. See 'SpecPro atg --help'.");
                    System.exit(-1);
            }
        }


        if(commandLine.hasOption("expand-trans")) {
            testGenerator.expandTransitions();
        }

        Set<TestSequence> tests = testGenerator.generate(spec);

        outStream.println("Different tests generated: " + tests.size());
        tests.forEach(l -> outStream.println(l.getAssignmentList()));
    }
}
