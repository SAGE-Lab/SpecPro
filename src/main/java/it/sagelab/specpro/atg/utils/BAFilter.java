package it.sagelab.specpro.atg.utils;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class BAFilter {

    private Predicate<Atom> assignValue;

    public BAFilter(Predicate<Atom> assignValue) {
        this.assignValue = assignValue;
    }

    public BAFilter() {
        this((a) -> false);
    }

    public void setAssignValue(Predicate<Atom> assignValue) {
        this.assignValue = assignValue;
    }

    public BuchiAutomaton inputFilter(BuchiAutomaton automaton, Set<Atom> inputs) {
        BuchiAutomaton inputAutomaton = new BuchiAutomaton();
        for(Vertex v: automaton.vertexSet())
            inputAutomaton.addVertex(v);

        for(Edge e: automaton.edgeSet()) {
            Edge e1 = newEdge(e, inputs);
            inputAutomaton.addEdge(e1.getSource(), e1.getTarget(), e1);
        }

        return inputAutomaton;
    }


    private Edge newEdge(Edge e, Set<Atom> inputs) {
        Edge e1 = new Edge(e.getSource(), e.getTarget(), new HashSet<>(), e.getId());
        for(Assignment assignment: e.getAssigments()) {
            addAssignment(e1, assignment.filter(inputs));
        }

        for(Assignment a: e1) {
            fill(a, inputs);
        }

        return e1;
    }

    private void addAssignment(Edge e, Assignment a) {

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

    private void fill(Assignment a, Set<Atom> inputs) {
        for(Atom i: inputs) {
            if(!a.contains(i)) {
                a.add(i, assignValue.test(i));
            }
        }
    }

}
