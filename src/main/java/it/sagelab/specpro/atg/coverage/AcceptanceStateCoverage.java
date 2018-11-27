package it.sagelab.specpro.atg.coverage;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

public class AcceptanceStateCoverage extends BACoverage {

    private final HashSet<Vertex> visitedStates;
    private Set<Vertex> acceptanceStates;
    private List<Edge> lastAcceptedPath;

    public AcceptanceStateCoverage(BuchiAutomaton buchiAutomaton) {
        super(buchiAutomaton);
        visitedStates = new HashSet<>();
        acceptanceStates = buchiAutomaton.vertexSet().stream().filter(v -> v.isAcceptingState()).collect(toSet());
    }

    public AcceptanceStateCoverage() {
        super();
        visitedStates = new HashSet<>();
    }

    @Override
    public void reset(BuchiAutomaton buchiAutomaton) {
        super.reset(buchiAutomaton);
        visitedStates.clear();
        acceptanceStates = buchiAutomaton.vertexSet().stream().filter(v -> v.isAcceptingState()).collect(toSet());
    }

    @Override
    public boolean covered() {
        return acceptanceStates.size() == visitedStates.size();
    }

    @Override
    public boolean covered(List<Edge> path) {
        return path == lastAcceptedPath;
    }

    @Override
    public void accept(List<Edge> path, List<Assignment> test) {

        int betaIndex = path.size();
        for(int i = 0; i < test.size(); ++i) {
            if(test.get(i).isStartBeta()) {
                betaIndex = i;
                break;
            }
        }

        for(int i = betaIndex; i < path.size(); ++i) {
            if(path.get(i).getSource().isAcceptingState())
                visitedStates.add(path.get(i).getSource());
        }

        lastAcceptedPath = path;
    }

    @Override
    public boolean evaluate(List<Edge> path) {
        Vertex lastVertex = path.get(path.size() - 1).getTarget();

        int betaIndex = path.size();
        for(int i = 0; i < path.size(); ++i) {
            if(path.get(i).getSource() == lastVertex) {
                betaIndex = i;
                break;
            }
        }

        for(int i = betaIndex; i < path.size(); ++i) {
            if(path.get(i).getSource().isAcceptingState())
                return true;
        }

        return false;
    }

    @Override
    public boolean evaluateTest(List<Edge> path, List<Assignment> test) {
        for(int i = 0; i < path.size(); ++i) {
            Vertex v = path.get(i).getSource();
            if(v.isAcceptingState() && !visitedStates.contains(v))
                return true;
        }

        return false;
    }

    @Override
    public double coverage() {
        return ((double) visitedStates.size()) / ((double) acceptanceStates.size());
    }
}
