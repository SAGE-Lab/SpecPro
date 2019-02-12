package it.sagelab.specpro.models.ba;

import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.*;
import java.util.stream.Collectors;

public class BuchiAutomaton extends DirectedPseudograph<Vertex, Edge> {

    public BuchiAutomaton(EdgeFactory<Vertex, Edge> ef) {
        super(ef);
    }

    public Vertex[] getInitStates() {
        return vertexSet().stream().filter(v -> v.getId().equals("I")).toArray(n-> new Vertex[n]);
    }

    public Set<Vertex> initStates() {
        return vertexSet().stream().filter(v -> v.getId().equals("I")).collect(Collectors.toSet());

    }

    public Iterator<List<Edge>> iterator(int length) {
        return new BuchiAutomatonIterator(this, new LassoShapedAcceptanceCondition(), length);
    }

    public void expandImplicitTransitions() {
        new HashSet<>(edgeSet()).stream().filter(e -> e.getAssigments().size() > 1).peek(this::expand).count();
    }

    private void expand(Edge e) {
        Set<Assignment> assignments = getCombinedAssignments(new LinkedList<>(e.getAssigments()));

        for(Assignment a: assignments) {
            Edge edge = new Edge(e.getSource(), e.getTarget(), "", null);
            Set<Assignment> assignmentSet = new HashSet<>();
            assignmentSet.add(a);
            edge.setAssignmentSet(assignmentSet);
            addEdge(e.getSource(), e.getTarget(), edge);
        }

        removeEdge(e);
    }

    private Set<Assignment> getCombinedAssignments(Queue<Assignment> assignments) {
        if(assignments.size() <= 1) {
            return new HashSet<>(assignments);
        }
        Assignment currentAssignment = assignments.poll();
        Assignment allNegatedAssignment = new Assignment(currentAssignment);
        for(Assignment ass: assignments) {
            allNegatedAssignment = allNegatedAssignment.combine(ass.negate());
            if(allNegatedAssignment == null)
                break;
        }
        HashSet<Assignment> combinedAssignments = new HashSet<>();
        if(allNegatedAssignment != null) {
            combinedAssignments.add(allNegatedAssignment);
        }

        Set<Assignment> subCombined = getCombinedAssignments(assignments);

        for(Assignment a: subCombined) {
            Assignment ass = a.combine(currentAssignment);
            if(ass != null) {
                combinedAssignments.add(ass);
            }
            Assignment negatedAss = a.combine(currentAssignment.negate());
            if(negatedAss != null) {
                combinedAssignments.add(negatedAss);
            }

        }

        return combinedAssignments;
    }
}
