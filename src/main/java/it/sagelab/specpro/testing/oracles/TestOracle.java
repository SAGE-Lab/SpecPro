package it.sagelab.specpro.testing.oracles;

import it.sagelab.specpro.models.ltl.assign.Trace;

public abstract class TestOracle {

    public enum Value {FALSE, PROBABLY_FALSE, INCONCLUSIVE, PROBABLY_TRUE, TRUE};

    public abstract void reset();

    public abstract Value evaluatePartial(Trace trace);

    public abstract Value evaluateComplete(Trace trace);

}
