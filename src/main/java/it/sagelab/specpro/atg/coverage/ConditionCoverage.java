package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConditionCoverage extends BACoverage {

    private final HashMap<Edge, Set<Assignment>> visitedConditions;


    public ConditionCoverage() {
        this.visitedConditions = new HashMap<>();
    }

    public ConditionCoverage(BuchiAutomaton ba) {
        super(ba);
        this.visitedConditions = new HashMap<>();
        for(Vertex v: buchiAutomaton.initStates()) {
            for(Edge e: buchiAutomaton.outgoingEdgesOf(v)) {
                visitedConditions.put(e, e.getAssigments());
            }
        }
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        super.reset(buchiAutomaton);
        visitedConditions.clear();
        for(Vertex v: buchiAutomaton.initStates()) {
            for(Edge e: buchiAutomaton.outgoingEdgesOf(v)) {
                visitedConditions.put(e, e.getAssigments());
            }
        }
    }

    @Override
    public boolean covered() {
        if(buchiAutomaton.edgeSet().size() != visitedConditions.keySet().size())
            return false;
        for(Edge e: buchiAutomaton.edgeSet()) {
            if(e.getAssigments().size() != visitedConditions.get(e).size())
                return false;
        }
        return true;
    }

    @Override
    public boolean covered(List<Edge> path) {
        return !evaluate(path);
    }

    @Override
    public void accept(List<Edge> path, List<Assignment> test) {
        for(int i = 0; i < path.size(); ++i) {
            visitedConditions.putIfAbsent(path.get(i), new HashSet<>());
            for(Assignment a: path.get(i)) {
                if(test.get(i).isCompatible(a)) {
                    visitedConditions.get(path.get(i)).add(a);
                }
            }
        }
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        for(Edge e: path) {
            visitedConditions.putIfAbsent(e, new HashSet<>());
            if(e.getAssigments().size() != visitedConditions.get(e).size())
                return true;
        }
        return false;
    }

    @Override
    public boolean evaluateTest(List<Edge> path, List<Assignment> test) {
        for(int i = 0; i < path.size(); ++i) {
            if(!visitedConditions.get(path.get(i)).contains(test.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double coverage() {
        int coveredConditions = visitedConditions.values().stream().mapToInt(Set::size).sum();
        int totalConditions = buchiAutomaton.edgeSet().stream().map(e -> e.getAssigments()).mapToInt(Set::size).sum();
        return ((double) coveredConditions) / ((double) totalConditions);
    }

}
