package it.sagelab.specpro.testing.oracles;

import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.Trace;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidPrefixOracle extends TestOracle {

    final private BAExplorer explorer;
    private Set<Vertex> currentStates;
    private int currentTraceLength;
    private Set<List<Edge>> lastEvaluation = Collections.emptySet();

    public ValidPrefixOracle(BuchiAutomaton automaton){
        super(automaton);
        this.explorer = new BAExplorer();
        this.explorer.setMaxPaths(1);
        reset();
    }

    @Override
    public void reset() {
        if(automaton != null) {
            this.currentStates = automaton.initStates();
        }
        this.currentTraceLength = 0;
    }

    @Override
    public Value evaluateComplete(Trace trace) {
        lastEvaluation = explorer.findInducedPaths(automaton, trace).toSet();
        return lastEvaluation.size() > 0 ? Value.TRUE : Value.FALSE;
    }

    @Override
    public Value evaluatePartial(Trace trace) {
        int n = currentTraceLength;
        currentTraceLength = trace.size();
        for(int i = n; i < trace.size(); ++i) {
            Assignment a = trace.get(i);
            currentStates = currentStates.stream().flatMap(v -> automaton.compatibleEdges(v, a).stream()).map(Edge::getTarget).collect(Collectors.toSet());
            if(currentStates.isEmpty()) {
                return Value.FALSE;
            }
        }

        return Value.INCONCLUSIVE;
    }

    public Set<Vertex> getCurrentStates() {
        return currentStates;
    }

    public Set<List<Edge>> getLastEvaluation() {
        return lastEvaluation;
    }

    public BAExplorer getExplorer() {
        return explorer;
    }
}
