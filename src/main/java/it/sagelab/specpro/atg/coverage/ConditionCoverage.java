package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConditionCoverage extends BACoverage {

    private final HashMap<Edge, Set<Assignment>> visistedConditions;


    public ConditionCoverage() {
        this.visistedConditions = new HashMap<>();
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        super.reset(buchiAutomaton);
        visistedConditions.clear();
    }

    @Override
    public boolean covered() {
        if(buchiAutomaton.edgeSet().size() != visistedConditions.keySet().size())
            return false;
        for(Edge e: buchiAutomaton.edgeSet()) {
            if(e.getAssigments().size() != visistedConditions.get(e).size())
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
            visistedConditions.putIfAbsent(path.get(i), new HashSet<>());
            for(Assignment a: path.get(i)) {
                if(test.get(i).contains(a)) {
                    visistedConditions.get(path.get(i)).add(a);
                }
            }
        }
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        for(Edge e: path) {
            visistedConditions.putIfAbsent(e, new HashSet<>());
            if(e.getAssigments().size() != visistedConditions.get(e).size())
                return true;
        }
        return false;
    }
}
