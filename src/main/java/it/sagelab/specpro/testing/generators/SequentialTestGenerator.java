package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.atg.utils.BAFilter;
import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ba.ac.EndWithStateAcceptanceCondition;
import it.sagelab.specpro.models.ba.ac.EndsWithAcceptanceStateCondition;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Trace;
import it.sagelab.specpro.testing.oracles.SimpleTestOracle;
import it.sagelab.specpro.testing.oracles.TestOracle;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.*;

public class SequentialTestGenerator extends TestGenerator {

    private final SimpleTestOracle oracle;
    private EndWithStateAcceptanceCondition condition;

    private final BuchiAutomaton inputAutomaton;
    private final HashMap<Vertex, Vertex> nearestAccState;
    private HashSet<Edge> visitedEdges;
    private List<Edge> nextRun;
    private Vertex acceptanceState;

    public SequentialTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);
        this.oracle = new SimpleTestOracle(automaton);
        condition = new EndWithStateAcceptanceCondition();
        //this.oracle.getExplorer().addAcceptanceCondition(condition);
        this.oracle.getExplorer().addAcceptanceCondition(new EndsWithAcceptanceStateCondition());

        this.inputAutomaton = new BAFilter().inputFilter(this.automaton, inputVariables);

        Set<Vertex> acceptanceStates = automaton.acceptanceStates();
        nearestAccState = new HashMap<>();
        for(Vertex v: automaton.vertexSet()) {
            Vertex nearest = findNearest(automaton, v, acceptanceStates);
            nearestAccState.put(v, nearest);
        }

        reset();
    }

    @Override
    public TestOracle getOracle() {
        return oracle;
    }

    @Override
    public void reset() {

        visitedEdges = new HashSet<>();
    }

    @Override
    public boolean hasNext() {
        nextRun = null;
        BreadthFirstIterator<Vertex,Edge> iterator = new BreadthFirstIterator<>(inputAutomaton, inputAutomaton.initStates().iterator().next());
        while(iterator.hasNext() && nextRun == null) {
            Vertex v = iterator.next();

            for (Edge e : automaton.outgoingEdgesOf(v)) {
                if (visitedEdges.contains(e)) {
                    continue;
                }

                acceptanceState = nearestAccState.get(e.getTarget());
                condition.setState(acceptanceState);
                nextRun = shortestPathTo(e);
                nextRun.addAll(shortestPathFromTo(e.getTarget(), acceptanceState));
                break;
            }
        }

        return nextRun != null;
    }

    @Override
    public Trace next() {
        Trace t = new Trace();
        for(Edge e: nextRun) {
            t.add(getInput(e));
        }
        visitedEdges.addAll(nextRun);
        return t;
    }

    @Override
    public boolean isCurrentTraceComplete(Trace trace, TestOracle.Value value) {
        if(oracle.getCurrentStates().contains(acceptanceState)) {
            return true;
        }
        else {
            for(Vertex v: oracle.getCurrentStates()) {
                nextRun = shortestPathFromTo(v, acceptanceState);
                if(nextRun != null)
                    return false;
            }
            Vertex v = SetUtils.pickRandom(oracle.getCurrentStates());
            acceptanceState = findNearest(automaton, v, automaton.acceptanceStates());
            nextRun = shortestPathFromTo(v, acceptanceState);
            return false;
        }
    }

    @Override
    public void traceCompleted(Trace trace) {

    }


    private Vertex findNearest(BuchiAutomaton automaton, Vertex v, Set<Vertex> acceptanceStates) {
        BreadthFirstIterator<Vertex, Edge> iterator = new BreadthFirstIterator<Vertex,Edge>(inputAutomaton, v);
        while(iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if(acceptanceStates.contains(vertex)) {
                return vertex;
            }
        }
        return null;
    }


}
