package it.sagelab.specpro.consistency;

import it.sagelab.specpro.models.InputRequirement;

import java.util.List;

public abstract class InconsistencyFinder {

    protected ConsistencyChecker cc;

    public InconsistencyFinder(ConsistencyChecker cc) {
        this.cc = cc;
    }

    public abstract List<InputRequirement> run();
}
