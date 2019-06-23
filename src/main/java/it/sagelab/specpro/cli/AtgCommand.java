package it.sagelab.specpro.cli;

import it.sagelab.specpro.atg.*;
import it.sagelab.specpro.atg.cache.MaxLengthCacheStrategy;
import it.sagelab.specpro.atg.cache.NoCacheStrategy;
import it.sagelab.specpro.atg.cache.RandomDeleteCacheStrategy;
import it.sagelab.specpro.atg.cache.ResetCacheStrategy;
import it.sagelab.specpro.atg.coverage.*;
import it.sagelab.specpro.atg.utils.AccCounterOutputSelector;
import it.sagelab.specpro.atg.utils.IdentityOutputSelector;
import it.sagelab.specpro.atg.utils.RandomOutputSelector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.Set;

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

        options.addOption(null, "minLength", true, "Min length of generated utils");
        options.addOption(null, "maxLength", true, "Max length of generated utils");
        options.addOption("c", "coverage", true, "Choose the coverage criteria. " +
                "Possible values are 'state', 'transition', 'acceptance', 'acc+state' or 'acc+transition'.");
        /*
        options.addOption(null, "conjunction", false,
                "Generates only one buchi automata with the conjunction of all the specifications");
        options.addOption(null, "cross-coverage", false, "Compute the coverage of the conjunction BA with the tests generated");
        */
        options.addOption(null, "cache", true,"Set the cache strategy. " +
                "Possible values are 'reset', 'max-length', 'random-del' or 'no'.");
        options.addOption(null, "cache-maxLength", true, "Max path legnth stored in the cache (only for max-length strategy).");

        options.addOption(null, "expand-trans", false, "Expand implicit transitions in BAs before starting test case generation");

        options.addOption(null, "alg", true, "std | sp");
        options.addOption(null, "outputSelection", true, "random | id | acceptance");
        options.addOption(null, "mealy-coverage", true, "Compute the coverage of the given mealy machine in kiss format");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        LTLTestGenerator testGenerator = null;

        if(!commandLine.hasOption("alg") || commandLine.getOptionValue("alg").equals("std")) {
            CoverageBesedTestGenerator coverageBesedTestGenerator = new CoverageBesedTestGenerator();
            testGenerator = coverageBesedTestGenerator;

            if (commandLine.hasOption("minLength")) {
                int minLength = Integer.parseInt(commandLine.getOptionValue("minLength"));
                coverageBesedTestGenerator.setMinLength(minLength);
            }

            if (commandLine.hasOption("maxLength")) {
                int maxLength = Integer.parseInt(commandLine.getOptionValue("maxLength"));
                coverageBesedTestGenerator.setMaxLength(maxLength);
            }

            if (commandLine.hasOption("coverage")) {
                String value = commandLine.getOptionValue("coverage");

                switch (value) {
                    case "state":
                        coverageBesedTestGenerator.setCoverageCriterion(new StateCoverage());
                        break;
                    case "transition":
                        coverageBesedTestGenerator.setCoverageCriterion(new TransitionCoverage());
                        break;
                    case "condition":
                        coverageBesedTestGenerator.setCoverageCriterion(new ConditionCoverage());
                        break;
                    case "acceptance":
                        coverageBesedTestGenerator.setCoverageCriterion(new AcceptanceStateCoverage());
                        break;
                    case "acc+state":
                        coverageBesedTestGenerator.setCoverageCriterion(new CombinedCoverage(new StateCoverage(), new AcceptanceStateCoverage()));
                        break;
                    case "acc+transition":
                        coverageBesedTestGenerator.setCoverageCriterion(new CombinedCoverage(new TransitionCoverage(), new AcceptanceStateCoverage()));
                        break;
                    case "acc+condition":
                        coverageBesedTestGenerator.setCoverageCriterion(new CombinedCoverage(new ConditionCoverage(), new AcceptanceStateCoverage()));
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
                        coverageBesedTestGenerator.getBaProductHandler().setCacheStrategy(new ResetCacheStrategy());
                        break;
                    case "max-length":
                        int maxLength = 10;
                        if (commandLine.hasOption("cache-maxLength")) {
                            maxLength = Integer.parseInt(commandLine.getOptionValue("cache-maxLength"));
                        }
                        coverageBesedTestGenerator.getBaProductHandler().setCacheStrategy(new MaxLengthCacheStrategy(maxLength));
                        break;
                    case "random-del":
                        long maxEntries = 1_000_000;
                        if (commandLine.hasOption("cache-maxEntries")) {
                            maxEntries = Long.parseLong("cache-maxLength");
                        }
                        coverageBesedTestGenerator.getBaProductHandler().setCacheStrategy(new RandomDeleteCacheStrategy(maxEntries));
                        break;
                    case "no":
                        coverageBesedTestGenerator.getBaProductHandler().setCacheStrategy(new NoCacheStrategy());
                        break;
                    default:
                        System.err.println("Value for option cache not valid. See 'SpecPro atg --help'.");
                        System.exit(-1);
                }
            }
        } else {
            SPTestGenerator spTestGenerator = new SPTestGenerator();
            testGenerator = spTestGenerator;

            if(commandLine.hasOption("outputSelection")) {
                String value = commandLine.getOptionValue("outputSelection");
                switch (value) {
                    case "random":
                        spTestGenerator.setOutputSelector(new RandomOutputSelector());
                        break;
                    case "id":
                        spTestGenerator.setOutputSelector(new IdentityOutputSelector());
                        break;
                    case "acceptance":
                        spTestGenerator.setOutputSelector(new AccCounterOutputSelector(spec.getOutputVariables()));
                }
            }
        }

        if(commandLine.hasOption("expand-trans")) {
            testGenerator.expandTransitions();
        }

        Set<TestSequence> tests = testGenerator.generate(spec);

        outStream.println("Different tests generated: " + tests.size());
        tests.forEach(l -> outStream.println(l.getAssignmentList()));

        outStream.println();

        if(commandLine.hasOption("mealy-coverage")) {
            String value = commandLine.getOptionValue("mealy-coverage");
            MealyCoverage mealyCoverage = new MealyCoverage(value);

            for(TestSequence t: tests) {
                mealyCoverage.evaluateTest(t.getAssignmentList(), 1);
            }

            mealyCoverage.printMeasures(outStream);
        }

    }
}
