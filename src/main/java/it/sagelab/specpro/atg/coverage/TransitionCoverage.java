package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;

public class TransitionCoverage extends BACoverage {

    private final HashSet<Edge> visitedEdges;
    private List<Edge> lastAcceptedPath;

    public TransitionCoverage(BuchiAutomaton buchiAutomaton) {
        super(buchiAutomaton);
        visitedEdges = new HashSet<>();
        for(Vertex v: buchiAutomaton.initStates())
            visitedEdges.addAll(buchiAutomaton.outgoingEdgesOf(v));
    }

    public TransitionCoverage() {
        super();
        visitedEdges = new HashSet<>();
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        super.reset(buchiAutomaton);
        visitedEdges.clear();
        for(Vertex v: buchiAutomaton.initStates())
            visitedEdges.addAll(buchiAutomaton.outgoingEdgesOf(v));
    }

    @Override
    public boolean covered() {
        return buchiAutomaton.edgeSet().size() == visitedEdges.size();
    }

    @Override
    public boolean covered(List<Edge> path) {
        return lastAcceptedPath == path;
    }

    @Override
    public void accept(List<Edge> path, List<Assignment> test) {
        for(Edge e: path)
            visitedEdges.add(e);
        lastAcceptedPath = path;
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        for(Edge e: path) {
            if (!visitedEdges.contains(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean evaluateTest(List<Edge> path, List<Assignment> test) {
        return true;
    }

    @Override
    public double coverage() {
        return ((double) visitedEdges.size()) / ((double) buchiAutomaton.edgeSet().size());
    }

}
