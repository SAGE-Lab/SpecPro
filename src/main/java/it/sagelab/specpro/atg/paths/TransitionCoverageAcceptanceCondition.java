package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.models.ba.Edge;

import java.util.HashSet;

public class TransitionCoverageAcceptanceCondition implements AcceptanceCondition {

    private final HashSet<Edge> visitedEdges;

    public TransitionCoverageAcceptanceCondition() {
        visitedEdges = new HashSet<>();
    }

    @Override
    public boolean accept(Edge[] path) {
        for(Edge e: path) {
            if (!visitedEdges.contains(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void accepted(Edge[] path) {
        for(Edge e: path)
            visitedEdges.add(e);
    }
}
