package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

import java.util.HashSet;

public class TransionCoveregePathsFinder extends IterativeDeepeningDFS {

    private final HashSet<Edge> visitedEdges;

    public TransionCoveregePathsFinder(BuchiAutomata graph) {
        super(graph, graph.vertexSet().size());
        visitedEdges = new HashSet<>();
    }

    public TransionCoveregePathsFinder(BuchiAutomata graph, int minIterations, int maxIterations, PathsBuilder pathsBuilder) {
        super(graph, minIterations, maxIterations, pathsBuilder);
        visitedEdges = new HashSet<>();
    }

    @Override
    public void find() {
        for(int i = minIterations; i <= maxIterations; ++i) {
            path.clear();
            for(Vertex v : initStates) {
                dfs(v, i + 1);
                if(visitedEdges.size() == graph.edgeSet().size())
                    return;
            }
        }
    }

    @Override
    protected void addPath() {
        boolean newEdge = false;
        for(Edge e: path) {
            if(!visitedEdges.contains(e)) {
                visitedEdges.add(e);
                newEdge = true;
            }
        }
        if (newEdge) {
            super.addPath();
        }
    }
}
