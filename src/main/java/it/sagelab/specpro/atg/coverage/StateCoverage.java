package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;

public class StateCoverage extends BACoverage {

    private final HashSet<Vertex> visitedStates;
    private List<Edge> lastAcceptedPath;

    public StateCoverage(BuchiAutomaton buchiAutomaton) {
        super(buchiAutomaton);
        visitedStates = new HashSet<>();
        visitedStates.addAll(buchiAutomaton.initStates());
    }

    public StateCoverage() {
        super();
        visitedStates = new HashSet<>();
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        super.reset(buchiAutomaton);
        visitedStates.clear();
        visitedStates.addAll(buchiAutomaton.initStates());
    }

    @Override
    public boolean covered() {
        return buchiAutomaton.vertexSet().size() == visitedStates.size();
    }

    @Override
    public boolean covered(List<Edge> path) {
        return lastAcceptedPath == path;
    }

    @Override
    public void accept(List<Edge> path, List<Assignment> test) {
        for(Edge edge: path) {
            visitedStates.add(edge.getSource());
        }
        lastAcceptedPath = path;
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        for(Edge edge: path) {
            if(!visitedStates.contains(edge.getSource())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double coverage() {
        return ((double) visitedStates.size()) / ((double) buchiAutomaton.vertexSet().size());
    }
}
