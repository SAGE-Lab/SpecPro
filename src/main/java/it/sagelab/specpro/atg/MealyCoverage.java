package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MealyCoverage {

    private KISSParser parser;
    private Set<List<Edge>> validSequences;

    private Set<Vertex> visitedStates;
    private Set<Edge> visitedEdges;

    public MealyCoverage(String inputFile) throws FileNotFoundException {
        parser = new KISSParser();
        parser.parse(inputFile);
        visitedStates = new HashSet<>();
        visitedEdges = new HashSet<>();
    }

    public void evaluateTest(List<Assignment> test) {
        ArrayList<Edge> traversedEdges = new ArrayList<>();
        validSequences = new HashSet<>();
        System.out.println("Evaluating test: " + test);
        visit(parser.resetState, test, traversedEdges);
        System.out.println("Valid paths: " + validSequences.size());
        for(List<Edge> sequence: validSequences) {
            for(Edge e: sequence) {
                visitedEdges.add(e);
                visitedStates.add(e.getSource());
                visitedStates.add(e.getTarget());
            }
        }
    }

    public void printMeasures() {
        double stateCoverage = 100.0 * visitedStates.size() / parser.graph.vertexSet().size();
        double edgeCoverage = 100.0 * visitedEdges.size() / parser.graph.edgeSet().size();
        System.out.println("State Coverage: " + stateCoverage);
        System.out.println("Edge Coverage: " + edgeCoverage);
    }

    private void visit(Vertex currentState, List<Assignment> test, ArrayList<Edge> traversedEdges) {
        if(traversedEdges.size() == test.size()) {
            validSequences.add(new ArrayList<>(traversedEdges));
            return;
        }
        Assignment currentAssignment = test.get(traversedEdges.size());

        Set<Edge> edgeSet = parser.graph.edgesOf(currentState);
        for(Edge e: edgeSet) {
            boolean validInput = true, validOutput = true;
            Assignment a = e.getAssigments().iterator().next();

            for(Atom i: parser.inputs) {
                if(currentAssignment.contains(i)) {
                    validInput &= a.contains(i) && a.getValue(i) == currentAssignment.getValue(i);
                }
            }

            for(Atom o: parser.outputs) {
                if(currentAssignment.contains(o)) {
                    validOutput &= a.contains(o) && a.getValue(o) == currentAssignment.getValue(o);
                }
            }

            if(validInput && validOutput) {
                traversedEdges.add(e);
                visit(e.getTarget(), test, traversedEdges);
                traversedEdges.remove(traversedEdges.size() - 1);
            }

        }

    }

}
