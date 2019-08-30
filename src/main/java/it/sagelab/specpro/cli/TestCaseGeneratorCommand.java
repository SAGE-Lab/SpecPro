package it.sagelab.specpro.cli;

import it.sagelab.specpro.atg.MealyCoverage;
import it.sagelab.specpro.atg.IOTestGenerator;
import it.sagelab.specpro.atg.TestCase;
import it.sagelab.specpro.models.ba.ac.EndsWithAcceptanceStateCondition;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.reasoners.LTL2BA;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.Set;


public class TestCaseGeneratorCommand extends Command {
    @Override
    public String getName() {
        return "test-cases";
    }

    @Override
    public String getDescription() {
        return "Generate tests cases of the given ltl specification (experimental) [Spot is required]";
    }

    @Override
    public Options createOptionMenu() {
        Options options = new Options();
        options.addOption(null, "end-acceptance", false, "Option to get only outputs ending with an acceptance state");
        options.addOption(null, "lasso-shaped", false, "Option to get only lasso shaped outputs");
        options.addOption(null, "saturate-input", false, "Set all unspecified input variables to false");
        options.addOption(null, "mealy-coverage", true, "Compute the coverage of the given mealy machine in kiss format");
        options.addOption(null, "owl", false, "Use owl");
        options.addOption("k", true, "Set max number of considered runs per input");

        return options;
    }

    @Override
    public void run(CommandLine commandLine) throws IOException {
        IOTestGenerator atg = new IOTestGenerator();

        if(commandLine.hasOption("owl")){
            LTL2BA.USE_OWL = true;
        }

        if(commandLine.hasOption("saturate-input")) {
            atg.setSaturateInput(true);
        }

        if(commandLine.hasOption("end-acceptance")) {
            atg.getOutputGenerator().getExplorer().addAcceptanceCondition(new EndsWithAcceptanceStateCondition());
        }

        if(commandLine.hasOption("lasso-shaped")) {
            atg.getOutputGenerator().getExplorer().addAcceptanceCondition(new LassoShapedAcceptanceCondition());
        }

        if(commandLine.hasOption("k")) {
            atg.getOutputGenerator().getExplorer().setMaxPaths(Integer.parseInt(commandLine.getOptionValue("k")));
        }

        Set<TestCase> testCases = atg.generateTestCases(spec, outStream);

        if(commandLine.hasOption("mealy-coverage")) {
            String value = commandLine.getOptionValue("mealy-coverage");
            MealyCoverage mealyCoverage = new MealyCoverage(value);

            for(TestCase t: testCases) {
                mealyCoverage.evaluateTestCase(t);
            }

            mealyCoverage.printMeasures(outStream);
            mealyCoverage.printDebugData();
        }

    }
}
