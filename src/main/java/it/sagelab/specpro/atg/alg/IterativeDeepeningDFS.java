package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import org.jgrapht.Graph;

import java.util.Set;

public class IterativeDeepeningDFS extends PathsFinder {

    final int minIterations;
    final int maxIterations;


    public IterativeDeepeningDFS(BuchiAutomata g, int minIterations, int maxIterations) {
        super(g);
        this.minIterations = minIterations;
        this.maxIterations = maxIterations;

        if(this.minIterations > this.maxIterations)
            throw new IllegalArgumentException("minIterations can't be greater then maxIterations.");

        if(this.minIterations <= 0)
            throw new IllegalArgumentException("minIterations must be greather then 0");
    }

    public IterativeDeepeningDFS(BuchiAutomata g, int maxIterations) {
        this(g, 1, maxIterations);
    }

    public IterativeDeepeningDFS(BuchiAutomata g, int minIterations, int maxIterations, PathsBuilder builder) {
        super(g, builder);
        this.minIterations = minIterations;
        this.maxIterations = maxIterations;

        if(this.minIterations > this.maxIterations)
            throw new IllegalArgumentException("minIterations can't be greater then maxIterations.");

        if(this.minIterations <= 0)
            throw new IllegalArgumentException("minIterations must be greather then 0");
    }

    public IterativeDeepeningDFS(BuchiAutomata g, int maxIterations, PathsBuilder builder) {
        this(g, 1, maxIterations, builder);
    }

    public void find() {
        for(int i = minIterations; i <= maxIterations; ++i) {
            path.clear();
            for(Vertex v : initStates) {
                dfs(v, i + 1);
            }
        }
    }

    protected void dfs(Vertex v, int n) {

        if(n < 0) {
            throw new IllegalArgumentException("n can't be lower then 0");
        }

        if(n == 0) {
            if(v.isAcceptingState()) {
                addPath();
            }
            return;
        }

        Set<Edge> edges = graph.outgoingEdgesOf(v);

        for(Edge e: edges) {
            path.add(e);
            dfs(e.getTarget(), n - 1);
            path.remove(path.size() - 1);
        }

    }

}
