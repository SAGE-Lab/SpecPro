package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BAInputFilter {

    public static BuchiAutomaton filter(BuchiAutomaton automaton, Set<Atom> inputs) {
        BuchiAutomaton inputAutomaton = new BuchiAutomaton();
        for(Vertex v: automaton.vertexSet())
            inputAutomaton.addVertex(v);

        for(Edge e: automaton.edgeSet()) {
            Edge e1 = newEdge(e, inputs);
            inputAutomaton.addEdge(e1.getSource(), e1.getTarget(), e1);
        }

        return inputAutomaton;
    }

    private static Edge newEdge(Edge e, Set<Atom> inputs) {
        Edge e1 = new Edge(e.getSource(), e.getTarget(), new HashSet<>());
        for(Assignment assignment: e.getAssigments()) {
            Assignment a = new Assignment();
            for(Map.Entry<Atom, Boolean> entry: assignment.getAssignments().entrySet()) {
                if(inputs.contains(entry.getKey())) {
                    a.add(entry.getKey(), entry.getValue());
                }
            }
            addAssignment(e1, a);
        }
        return e1;
    }

    private static void addAssignment(Edge e, Assignment a) {

        Set<Assignment> toRemove = new HashSet<>();
        for(Assignment a1: e.getAssigments()) {
            if(a1.contains(a))
                return;
            if(a.contains(a1))
                toRemove.add(a);
        }

        e.getAssigments().removeAll(toRemove);
        e.getAssigments().add(a);
    }


}
