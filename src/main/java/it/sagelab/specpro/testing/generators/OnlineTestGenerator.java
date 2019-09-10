package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.testing.GoalsContainer;
import it.sagelab.specpro.testing.oracles.SimpleTestOracle;
import it.sagelab.specpro.testing.oracles.TestOracle;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Trace;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OnlineTestGenerator extends TestGenerator {

    private final SimpleTestOracle oracle;
    private Edge currentGoal;
    private GoalsContainer<Edge> criteria;
    private List<Edge> currentRun;
    private Set<Edge> traversedEdges;
    private Assignment currentInput;
    private int currentIndex;

    public OnlineTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);
        this.oracle = new SimpleTestOracle(automaton);
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
       if(currentRun != null && currentIndex < currentRun.size() - 1) {
           return true;
       }

        if(criteria.hasNextGoal()) {
            currentGoal = criteria.getNextGoal();
            currentRun = shortestPathTo(currentGoal);
            traversedEdges = new HashSet<>();
            currentIndex = -1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Trace next() {
        Trace t = new Trace();
        currentInput = getInput(currentRun.get(++currentIndex).getRandAssignment());
        t.add(currentInput);
        return t;
    }

    @Override
    public boolean isCurrentTraceComplete(Trace trace, TestOracle.Value value) {

        Vertex previousState = currentRun.get(currentIndex).getSource();
        Set<Edge> comatibleEdges = getCompatibleEdges(previousState, trace.last());
        Edge edge = currentRun.get(currentIndex);

        if(comatibleEdges.contains(edge)) {
            traversedEdges.add(edge);
        } else {
            currentRun = null;
            for(Vertex v: oracle.getCurrentStates()) {
                currentRun = shortestPathFromTo(v, currentGoal);
                if(currentRun != null) {
                    traversedEdges.add(automaton.getEdge(previousState, v));
                    break;
                }
            }
            currentIndex = -1;
        }

        return currentRun != null && currentIndex >= currentRun.size() - 1;
    }

    @Override
    public void traceCompleted(Trace trace) {
        currentRun = null;
        traversedEdges.forEach(e -> criteria.setSatisfiedGoal(e));
        traversedEdges.clear();
    }
}
