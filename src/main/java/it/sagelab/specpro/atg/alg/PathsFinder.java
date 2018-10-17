package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;

public abstract class PathsFinder {

    private PathsBuilder pathsBuilder;
    protected BuchiAutomata graph;
    protected Vertex[] initStates;
    protected ArrayList<Edge> path;


    public PathsFinder(BuchiAutomata graph) {
       this(graph, new PathsBuilder());
    }

    public PathsFinder(BuchiAutomata graph, PathsBuilder pathsBuilder) {
        this.graph = graph;
        this.pathsBuilder = pathsBuilder;
        initStates = graph.getInitStates();
        path = new ArrayList<>();
    }

    public abstract void find();

    public Trie<Assignment> getPaths() {
        return pathsBuilder.getTrie();
    }

    protected void addPath() {
        for(Edge e : path)
            pathsBuilder.addEdge(e);
        pathsBuilder.generateAllConditionsPaths();
    }
}
