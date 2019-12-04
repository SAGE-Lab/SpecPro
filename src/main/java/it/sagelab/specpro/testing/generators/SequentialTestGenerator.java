package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ba.ac.VisitStateAcceptanceCondition;
import it.sagelab.specpro.models.ba.ac.EndsWithAcceptanceStateCondition;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Trace;
import it.sagelab.specpro.testing.GoalsContainer;
import it.sagelab.specpro.testing.oracles.SimpleTestOracle;
import it.sagelab.specpro.testing.oracles.TestOracle;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.util.*;

public class SequentialTestGenerator extends TestGenerator {

    private final SimpleTestOracle oracle;
    private VisitStateAcceptanceCondition condition;

    //private final BuchiAutomaton inputAutomaton;
    private final HashMap<Vertex, Vertex> nearestAccState;
    private HashSet<Edge> visitedEdges;
    private List<Edge> nextRun;
    private Vertex acceptanceState;
    private GoalsContainer<Edge> criteria;

    public SequentialTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);
        this.oracle = new SimpleTestOracle(automaton);
        condition = new VisitStateAcceptanceCondition();
        //this.oracle.getExplorer().addAcceptanceCondition(condition);
        this.oracle.getExplorer().addAcceptanceCondition(new EndsWithAcceptanceStateCondition());

        //this.inputAutomaton = new BAFilter().inputFilter(this.automaton, inputVariables);

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
        criteria = GoalsContainer.newTransitionCoverageGoal(automaton);
        visitedEdges = new HashSet<>();
    }

    @Override
    public boolean hasNext() {

        if(nextRun == null && criteria.hasNextGoal()) {
            Edge e = criteria.getNextGoal();
            acceptanceState = nearestAccState.get(e.getTarget());
            if(acceptanceState != null) {
                condition.setState(acceptanceState);
                nextRun = shortestPathTo(e);
                nextRun.addAll(shortestPathFromTo(e.getTarget(), acceptanceState));
            } else {
                criteria.setSatisfiedGoal(e);
                return hasNext();
            }
        }

        return nextRun != null;
    }

    @Override
    public Trace next() {
        Trace t = new Trace();
        for(Edge e: nextRun) {
            t.add(getInput(e));
            criteria.setSatisfiedGoal(e);
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
        oracle.getLastEvaluation().forEach(t -> t.forEach(e -> criteria.setSatisfiedGoal(e)));
        nextRun = null;
    }


    private Vertex findNearest(BuchiAutomaton automaton, Vertex v, Set<Vertex> acceptanceStates) {
        BreadthFirstIterator<Vertex, Edge> iterator = new BreadthFirstIterator<Vertex,Edge>(automaton, v);
        while(iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if(acceptanceStates.contains(vertex)) {
                return vertex;
            }
        }
        return null;
    }


}
