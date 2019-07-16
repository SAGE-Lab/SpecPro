package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BAFilter {

    public static BuchiAutomaton inputFilter(BuchiAutomaton automaton, Set<Atom> inputs) {
        BuchiAutomaton inputAutomaton = new BuchiAutomaton();
        for(Vertex v: automaton.vertexSet())
            inputAutomaton.addVertex(v);

        for(Edge e: automaton.edgeSet()) {
            Edge e1 = newEdge(e, inputs);
            inputAutomaton.addEdge(e1.getSource(), e1.getTarget(), e1);
        }

        return inputAutomaton;
    }

    public static void trim(List<Assignment> assignments) {
        while(assignments.size() > 0) {
            if(assignments.get(assignments.size() - 1).getAssignments().isEmpty()) {
                assignments.remove(assignments.size() - 1);
            } else {
                return;
            }
        }
    }

    private static Edge newEdge(Edge e, Set<Atom> inputs) {
        Edge e1 = new Edge(e.getSource(), e.getTarget(), new HashSet<>());
        for(Assignment assignment: e.getAssigments()) {
            addAssignment(e1, assignment.filter(inputs));
        }
        return e1;
    }

    private static void addAssignment(Edge e, Assignment a) {

        Set<Assignment> toRemove = new HashSet<>();
        for(Assignment a1: e.getAssigments()) {
            if(a1.contains(a))
                return;
            if(a.contains(a1))
                toRemove.add(a1);
        }

        e.getAssigments().removeAll(toRemove);
        e.getAssigments().add(a);
    }

}
