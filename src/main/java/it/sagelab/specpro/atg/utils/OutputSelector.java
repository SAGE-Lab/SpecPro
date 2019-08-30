package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.atg.TestSequence;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public interface OutputSelector {

    public TestSequence selectOutput(BuchiAutomaton automaton, List<Assignment> inputAssignment);
}
