package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MealyCoverage {

    private KISSParser parser;
    private Set<List<Edge>> validSequences;

    private Set<Vertex> visitedStates;
    private Set<Edge> visitedEdges;
    private Set<List<Assignment>> unsuccessfulTests;
    private Vertex currentState;
    private int nEvaluatedTests;
    private int nSuccessfulTests;

    public MealyCoverage(String inputFile) throws IOException {
        parser = new KISSParser();
        parser.parse(inputFile);
        parser.saveToFile(inputFile.replace(".kiss", ".dot"));
        visitedStates = new HashSet<>();
        visitedEdges = new HashSet<>();
        nEvaluatedTests = 0;
        nSuccessfulTests = 0;
        unsuccessfulTests = new HashSet<>();
        currentState = parser.resetState;
    }

    public void evaluateTest(List<Assignment> test, int nRepetitionsOfBeta) {
        List<Assignment> newTest = new ArrayList<>(test);
        int betaIndex = 0;
        for(int i = 0; i < test.size(); ++i) {
            if(test.get(i).isStartBeta())
                betaIndex = i;
        }

        ++nEvaluatedTests;
        validSequences = new HashSet<>();

        for(int i = 1; i < nRepetitionsOfBeta; ++i) {
            newTest.addAll(test.subList(betaIndex, test.size() -1));

        }

        ArrayList<Edge> traversedEdges = new ArrayList<>();
        //System.out.println("Evaluating test: " + newTest);
        visit(parser.resetState, newTest, traversedEdges);
        //System.out.println("Valid utils: " + validSequences.size());

        //System.out.println("Lengths: " + test.size() + "\t" + newTest.size());





        if(validSequences.size() > 0) {
            ++nSuccessfulTests;
        }
        else {
            unsuccessfulTests.add(newTest);
        }
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
        double precision = 100.0 * nSuccessfulTests / nEvaluatedTests;
        System.out.println("Evaluated Tests: " + nEvaluatedTests);
        System.out.println("State Coverage: " + stateCoverage);
        System.out.println("Transition Coverage: " + edgeCoverage);
        System.out.println("Precision: " + precision);
        System.out.println("$$$\t" + nEvaluatedTests + "\t" + stateCoverage + "\t" + edgeCoverage + "\t" + precision);
    }

    public void printDebugData() {
        Set<Vertex> nonVisitedStates = parser.graph.vertexSet().stream().filter(v -> !visitedStates.contains(v)).collect(Collectors.toSet());
        System.out.println("Non visited states: " + nonVisitedStates);
        System.out.println("Unsuccessful tests:");
        for(List<Assignment> test: unsuccessfulTests) {
            System.out.println(test);
        }
    }

    public boolean execAction(Scanner scanner) {
        System.out.println("Current state: " + currentState);

        System.out.println("Choose assignment: ");
        String line = scanner.nextLine().trim();
        if(line.length() == 0)
            return false;
        String[] ass = line.split(",");
        Assignment assignment = new Assignment();
        for(String a: ass) {
            a = a.trim();
            boolean negated = a.startsWith("!");
            if(negated)
                a = a.substring(1);
            assignment.add(new Atom(a), !negated);
        }
        System.out.println("Assignment: " + assignment);
        validSequences = new HashSet<>();
        visit(currentState, Arrays.asList(assignment), new ArrayList<>());
        List<Edge> validEdges = validSequences.stream().map(l -> l.get(0)).collect(Collectors.toList());
        System.out.println("Valid Edges: " + validEdges);
        if(validEdges.size() == 0) {
            System.out.println("Impossible to keep going...");
            return false;
        }
        if(validEdges.size() == 1) {
            currentState = validEdges.get(0).getTarget();
        } else {
            System.out.print("Choose an edge to follow [0 .." + (validEdges.size() - 1) + "]: ");
            int index = scanner.nextInt();
            currentState = validEdges.get(index).getTarget();
        }
        return true;
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
            Assignment edgeAssignment = e.getAssigments().iterator().next();

            for(Atom i: parser.inputs) {
                if(currentAssignment.contains(i) && edgeAssignment.contains(i)) {
                    validInput &= edgeAssignment.contains(i) && edgeAssignment.getValue(i) == currentAssignment.getValue(i);
                }
            }

            for(Atom o: parser.outputs) {
                if(currentAssignment.contains(o)) {
                    validOutput &= edgeAssignment.contains(o) && edgeAssignment.getValue(o) == currentAssignment.getValue(o);
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
