package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class MealyCoverage {

    private KISSParser parser;
    private Set<List<Edge>> validSequences;

    private Set<Vertex> visitedStates;
    private Set<Edge> visitedEdges;
    private Set<List<Assignment>> unsuccessfulTests;
    private Set<TestCase> unsuccessfulTestCases;
    private Vertex currentState;
    private int nEvaluatedTests;
    private int nSuccessfulTests;

    public MealyCoverage(String inputFile) throws IOException {
        parser = new KISSParser();
        parser.parse(inputFile);
        //parser.saveToFile(inputFile.replace(".kiss", ".dot"));
        visitedStates = new HashSet<>();
        visitedEdges = new HashSet<>();
        nEvaluatedTests = 0;
        nSuccessfulTests = 0;
        unsuccessfulTests = new HashSet<>();
        unsuccessfulTestCases = new HashSet<>();
        currentState = parser.resetState;
    }

    public void evaluateTestCase(TestCase testCase) {
        ++nEvaluatedTests;
        ArrayList<Edge> traversedEdges = new ArrayList<>();
        ArrayList<Assignment> output = new ArrayList<>();

        validSequences = new HashSet<>();
        visit2(parser.resetState, testCase, traversedEdges, output);

        if(validSequences.size() > 0) {
            ++nSuccessfulTests;
        }
        else {
            unsuccessfulTestCases.add(testCase);
        }
        for(List<Edge> sequence: validSequences) {
            for(Edge e: sequence) {
                visitedEdges.add(e);
                visitedStates.add(e.getSource());
                visitedStates.add(e.getTarget());
            }
        }
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


    public void printMeasures(PrintStream stream) {
        double stateCoverage = 100.0 * visitedStates.size() / parser.graph.vertexSet().size();
        double edgeCoverage = 100.0 * visitedEdges.size() / parser.graph.edgeSet().size();
        double precision = 100.0 * nSuccessfulTests / nEvaluatedTests;
//        stream.println("Evaluated Tests: " + nEvaluatedTests);
//        stream.println("State Coverage: " + stateCoverage);
//        stream.println("Transition Coverage: " + edgeCoverage);
//        stream.println("Precision: " + precision);
        stream.print(String.format("%.2f, %.2f, %.2f, ", stateCoverage, edgeCoverage, precision));
    }

    public void printDebugData() {
        Set<Vertex> nonVisitedStates = parser.graph.vertexSet().stream().filter(v -> !visitedStates.contains(v)).collect(Collectors.toSet());
        System.out.println("Non visited states: " + nonVisitedStates);
        System.out.println("Unsuccessful tests:");
        for(List<Assignment> test: unsuccessfulTests) {
            System.out.println(test);
        }

        for(TestCase t: unsuccessfulTestCases) {
            System.out.println("Input: " + t.getInput());
            for(List<Assignment> output: t.getOutputs()) {
                System.out.println("\t" + output);
            }
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

        //Set<Edge> edgeSet = parser.graph.edgesOf(currentState);
        Set<Edge> edgeSet = parser.graph.edgesOf(currentState).stream()
                .filter(e -> checkInput(e, currentAssignment))
                .filter(e -> checkOutput(e, currentAssignment)).collect(Collectors.toSet());

        for(Edge e: edgeSet) {
                traversedEdges.add(e);
                visit(e.getTarget(), test, traversedEdges);
                traversedEdges.remove(traversedEdges.size() - 1);
            }
    }

    private void visit2(Vertex currentState, TestCase test, List<Edge> traversedEdges, List<Assignment> output) {
        if(traversedEdges.size() == test.getInput().size()) {
            validSequences.add(new ArrayList<>(traversedEdges));
            return;
        }

        Assignment currentInput = test.getInput().get(traversedEdges.size());
        Set<Edge> edgeSet = parser.graph.edgesOf(currentState).stream().filter(e -> checkInput(e, currentInput)).collect(Collectors.toSet());

        Set<Assignment> succ = test.getOutputs().getSuccessors(output);

        for(Edge e: edgeSet) {
            for(Assignment a: succ) {
                if(checkOutput(e, a)) {
                    traversedEdges.add(e);
                    output.add(a);
                    visit2(e.getTarget(), test, traversedEdges, output);
                    traversedEdges.remove(traversedEdges.size() - 1);
                    output.remove(output.size() - 1);
                }
            }
        }

    }

    private boolean checkInput(Edge e, Assignment input) {
        boolean validInput = true;
        for(Assignment a: e.getAssigments()) {
            for (Atom i : parser.inputs) {
                if (input.contains(i) && a.contains(i)) {
                    validInput &= a.getValue(i) == input.getValue(i);
                }
            }
        }
        return validInput;
    }

    private boolean checkOutput(Edge e, Assignment output) {
        boolean validOutput = true;
        for(Assignment a: e.getAssigments()) {
            for (Atom o : parser.outputs) {
                if (output.contains(o)) {
                    validOutput &= a.contains(o) && a.getValue(o) == output.getValue(o);
                }
            }
        }
        return validOutput;
    }

}
