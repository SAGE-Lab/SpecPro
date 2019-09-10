package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Trace;
import it.sagelab.specpro.testing.GoalsContainer;
import it.sagelab.specpro.testing.oracles.SimpleTestOracle;
import it.sagelab.specpro.testing.oracles.TestOracle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomWalkTestGenerator extends TestGenerator {

    private final SimpleTestOracle oracle;
    private Edge currentGoal;
    private Set<Edge> traversedEdges;
    private Vertex currentState;
    private GoalsContainer<Edge> criteria;

    public RandomWalkTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);
        oracle = new SimpleTestOracle(automaton);
        traversedEdges = new HashSet<>();
    }

    @Override
    public TestOracle getOracle() {
        return oracle;
    }

    @Override
    public void reset() {
        criteria = GoalsContainer.newTransitionCoverageGoal(automaton);
    }

    @Override
    public boolean hasNext() {
        return currentGoal != null || criteria.hasNextGoal();
    }

    @Override
    public Trace next() {
        if(currentGoal == null) {
            currentGoal = criteria.getNextGoal();
            currentState = SetUtils.pickRandom(automaton.initStates());
        }

        Edge currentEdge = SetUtils.pickRandom(automaton.outgoingEdgesOf(currentState));
        return new Trace(getInput(currentEdge));
    }

    @Override
    public boolean isCurrentTraceComplete(Trace trace, TestOracle.Value value) {
        Set<Edge> compatibleEdges = getCompatibleEdges(currentState, trace.last());
        if(compatibleEdges.contains(currentGoal)) {
            traversedEdges.add(currentGoal);
            return true;
        } else {
            Edge e = SetUtils.pickRandom(compatibleEdges);
            traversedEdges.add(e);
            currentState = e.getTarget();
            return false;
        }
    }

    @Override
    public void traceCompleted(Trace trace) {
        traversedEdges.forEach(e -> criteria.setSatisfiedGoal(e));
        traversedEdges.clear();
        currentGoal = null;
    }
}
