package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public class CombinedCoverage extends BACoverage {

    private final BACoverage cond1, cond2;

    public CombinedCoverage(BACoverage cond1, BACoverage cond2) {
        super();
        this.cond1 = cond1;
        this.cond2 = cond2;
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        this.cond1.reset(buchiAutomaton);
        this.cond2.reset(buchiAutomaton);
    }

    @Override
    public boolean covered() {
        return cond1.covered() && cond2.covered();
    }

    @Override
    public boolean covered(List<Edge> path) {
        return cond1.covered(path) && cond2.covered(path);
    }

    @Override
    public void accept(List<Edge> path, List<Assignment> test) {
        cond1.accept(path, test);
        cond2.accept(path, test);
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        return cond1.evaluate(path) || cond2.evaluate(path);
    }

    @Override
    public boolean evaluateTest(List<Edge> path, List<Assignment> test) {
        return cond1.evaluateTest(path, test) || cond2.evaluateTest(path, test);
    }

    @Override
    public double coverage() {
        return (cond1.coverage() + cond2.coverage()) / 2;
    }
}
