package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.coverage.*;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import org.jgrapht.Graph;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        KISSParser parser = new KISSParser();
        parser.parse("elevator-2floors.kiss");
        parser.saveToFile("elevator.dot");

        AutomaticTestGenerator atg = new AutomaticTestGenerator();
        atg.setMinLength(2);
        //atg.setCoverageCriterion(new AcceptanceStateCoverage());
        atg.setCoverageCriterion(new StateCoverage());
        //atg.setCoverageCriterion(new CombinedCoverage(new AcceptanceStateCoverage(), new ConditionCoverage()));
        atg.addFormula("F (p & X (p & X (p & X p)) & q & X (q & X (q & X q))) <-> G F acc");
        Map<BuchiAutomaton, Set<TestSequence>> tests = atg.generate();

        MealyCoverage mc = new MealyCoverage("output.kiss");

        for(Set<TestSequence> ts: tests.values()) {
            for(TestSequence test : ts) {
                mc.evaluateTest(test.getAssignmentList(),1);
            }
        }

        mc.printMeasures();

    }

}
