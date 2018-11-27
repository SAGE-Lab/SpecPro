package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public abstract class BACoverage {

    protected BuchiAutomaton buchiAutomaton;

    public BACoverage(BuchiAutomaton buchiAutomaton) {
        this.buchiAutomaton = buchiAutomaton;
    }

    public BACoverage() {
        buchiAutomaton = null;
    }

    public void reset(BuchiAutomaton buchiAutomaton) {
        this.buchiAutomaton = buchiAutomaton;
    }

    public abstract boolean covered();

    public abstract boolean covered(List<Edge> path);

    public abstract void accept(List<Edge> path, List<Assignment> test);

    public abstract boolean evaluate(List<Edge> path);

    public abstract boolean evaluateTest(List<Edge> path, List<Assignment> test);

    public abstract double coverage();

}
