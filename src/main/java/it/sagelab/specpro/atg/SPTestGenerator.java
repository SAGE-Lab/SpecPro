package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.utils.AccCounterOutputSelector;
import it.sagelab.specpro.atg.utils.BAInputFilter;
import it.sagelab.specpro.atg.utils.OutputSelector;
import it.sagelab.specpro.atg.utils.RandomOutputSelector;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.GraphPath;;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.io.IOException;
import java.util.*;

public class SPTestGenerator extends LTLTestGenerator {

    Trie<Assignment> inputSequences = new Trie<>();
    OutputSelector outputSelector = new RandomOutputSelector();;

    public void setOutputSelector(OutputSelector selector) {
        this.outputSelector = selector;
    }

    @Override
    public Set<TestSequence> generate(LTLSpec spec) throws IOException {
        parseRequirements(spec, true);
        HashSet<TestSequence> tests = new HashSet<>();
        HashSet<Edge> visitedEdges = new HashSet<>();

        BuchiAutomaton automaton = BAInputFilter.filter(buchiAutomata.get(0), spec.getInputVariables());

        Set<Vertex> initStates = automaton.initStates();
        Set<Vertex> acceptanceStates = automaton.acceptanceStates();

        HashMap<Vertex, Vertex> nearestAccState = new HashMap<>();
        for(Vertex v: automaton.vertexSet()) {
            Vertex nearest = findNearest(automaton, v, acceptanceStates);
            nearestAccState.put(v, nearest);
        }

//        HashMap<Vertex, List<GraphPath<Vertex, Edge>>> simpleCycles = new HashMap<>();
//        AllDirectedPaths<Vertex, Edge> allDirectedPaths = new AllDirectedPaths<>(automaton);
//
//        for(Vertex v: acceptanceStates) {
//            simpleCycles.put(v, allDirectedPaths.getAllPaths(v, v, true, null));
//        }

        for(Vertex initState: initStates) {

            BreadthFirstIterator<Vertex, Edge> iterator = new BreadthFirstIterator<Vertex,Edge>(automaton, initState);
            while(iterator.hasNext()) {
                Vertex v = iterator.next();

                for (Edge e : automaton.outgoingEdgesOf(v)) {
                    if (visitedEdges.contains(e)) {
                        continue;
                    }

                    Vertex acceptanceState = nearestAccState.get(e.getTarget());
                    {
                        TestSequence test = buildTest(automaton, v, e, initState, acceptanceState);
//                        GraphPath<Vertex, Edge> cycle = ListUtils.randomSelect(simpleCycles.get(acceptanceState));
//
//                        if(cycle.getLength() > 0) {
//                            int startBeta = test.getAssignmentList().size();
//                            test.add(cycle);
//                            test.getAssignmentList().get(startBeta).setStartBeta(true);
//                        }
                        addSequence(test);

                        for(Edge edge: test.getPath())
                            visitedEdges.add(edge);
                    }

                }

            }
        }

        System.out.println("Remove shorter utils...");
        inputSequences.removePartialPaths();
        System.out.println("Input Sequences: " + inputSequences.size());



        for(List<Assignment> assignments: inputSequences) {
            tests.add(outputSelector.selectOutput(buchiAutomata.get(0), assignments));
        }

        return tests;
    }

    private Vertex findNearest(BuchiAutomaton automaton, Vertex v, Set<Vertex> acceptanceStates) {
        BreadthFirstIterator<Vertex, Edge> iterator = new BreadthFirstIterator<Vertex,Edge>(automaton, v);
        while(iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if(acceptanceStates.contains(vertex)) {
                return vertex;
            }
        }

        return null;
    }

    public TestSequence buildTest(BuchiAutomaton g, Vertex v, Edge e, Vertex initState, Vertex acceptanceState) {
        TestSequence test = new TestSequence();
        DijkstraShortestPath<Vertex, Edge> sp = new DijkstraShortestPath<>(g);

        if(v != initState) {
            GraphPath<Vertex, Edge> prefix = sp.getPath(initState, v);
            test.add(prefix);
        }
        test.add(e);

        GraphPath<Vertex, Edge> spToAcceptance = sp.getPath(e.getTarget(), acceptanceState);
        test.add(spToAcceptance);

        return test;
    }

    public void addSequence(TestSequence test) {
        List<Assignment> assignmentList = new ArrayList(test.getAssignmentList());
        trim(assignmentList);

        inputSequences.insert(assignmentList, (a1, a2) -> {
            if(a2.isCompatible(a1)) {
                for(Map.Entry<Atom, Boolean> entry: a1.getAssignments().entrySet()) {
                    a2.add(entry.getKey(), entry.getValue());
                }
                assignmentList.set(assignmentList.indexOf(a1), a2);
                return 0;
            }
            return -1;
        });
    }

    private void trim(List<Assignment> assignments) {
        while(assignments.size() > 0) {
            if(assignments.get(assignments.size() - 1).getAssignments().isEmpty()) {
                assignments.remove(assignments.size() - 1);
            } else {
                return;
            }
        }
    }

    private boolean isInduced(Edge e, Assignment assignment) {
        for(Assignment a: e.getAssigments()) {
            if(a.isCompatible(assignment))
                return true;
        }
        return false;
    }

}
