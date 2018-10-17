package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;
import java.util.stream.Collectors;

public class PathValidator {

    private class Option {
        Edge edge;
        Assignment assignment;

        public Option(Edge edge, Assignment assignment) {
            this.edge = edge;
            this.assignment = assignment;
        }
    }

    protected ArrayList<BuchiAutomata> graphs;
    protected PathsBuilder pathsBuilder;

    public PathValidator(ArrayList<BuchiAutomata> graphs, PathsBuilder pathsBuilder) {
        this.graphs = graphs;
        this.pathsBuilder = pathsBuilder;
    }


//    public List<Assignment> validate(List<Assignment> path) {
//        Vertex[] initStates = new Vertex[graphs.size()];
//        for(int i = 0; i < graphs.size(); ++i)
//            initStates[i] = graphs.get(i).getInitStates()[0];
//    }

    private List<Assignment> validate(List<Assignment> path, Vertex[] currentState, int index) {
        if(index >= path.size())
            return path;

        Assignment s = path.get(index);
        ArrayList<List<Option>> allEdges = new ArrayList<>();
        for(int i = 0; i < currentState.length; ++i) {
            List<Option> options = getCompatibleEdges(graphs.get(i), currentState[i], s);
            if(options.size() == 0)
                return null;
            allEdges.add(options);
        }

        allEdges.sort(Comparator.comparingInt(List::size));

        int i = 0;
        while(i < allEdges.size() && allEdges.get(i).size() == 1) {
            s = s.combine(allEdges.get(i).get(0).assignment);
        }

        return null;
    }

    private List<Option> getCompatibleEdges(BuchiAutomata ba, Vertex v, Assignment assignment) {
        Set<Edge> edges = ba.outgoingEdgesOf(v);
        List<Option> validAssignments = new ArrayList<>();

        for(Edge e: edges) {
            List<Assignment> edgeAssignments = pathsBuilder.getEdgeAssignments(e);

            edgeAssignments = edgeAssignments.stream().filter(a -> assignment.isCompatible(a)).collect(Collectors.toList());
            for(Assignment a: edgeAssignments)
                validAssignments.add(new Option(e, a));
        }

        return validAssignments;
    }


}
