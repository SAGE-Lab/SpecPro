package it.sagelab.specpro.testing.oracles;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.assign.Trace;

public abstract class TestOracle {

    public enum Value {FALSE, PROBABLY_FALSE, INCONCLUSIVE, PROBABLY_TRUE, TRUE};

    protected BuchiAutomaton automaton;

    public TestOracle(BuchiAutomaton automaton) {
        this.automaton = automaton;
    }

    public abstract void reset();

    public void reset(BuchiAutomaton automaton) {
        this.automaton = automaton;
        this.reset();
    }

    public abstract Value evaluatePartial(Trace trace);

    public abstract Value evaluateComplete(Trace trace);

    public BuchiAutomaton getAutomaton() {
        return automaton;
    }
}
