package it.sagelab.specpro.atg;

import it.sagelab.specpro.collections.ListUtils;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SPStatesTestGenerator extends LTLTestGenerator {

    @Override
    public Set<TestSequence> generate(LTLSpec spec) throws IOException {
        parseRequirements(spec, true);
        HashSet<TestSequence> tests = new HashSet<>();
        HashSet<Vertex> visitedStates = new HashSet<>();

        BuchiAutomaton automaton = buchiAutomata.get(0);
        Set<Vertex> initStates = automaton.initStates();
        Set<Vertex> acceptanceStates = automaton.acceptanceStates();

        HashMap<Vertex, List<GraphPath<Vertex, Edge>>> simpleCycles = new HashMap<>();
        AllDirectedPaths<Vertex, Edge> allDirectedPaths = new AllDirectedPaths<>(automaton);

        for(Vertex v: acceptanceStates) {
            simpleCycles.put(v, allDirectedPaths.getAllPaths(v, v, true, null));
        }

        for(Vertex initState: initStates) {

            BreadthFirstIterator<Vertex, Edge> iterator = new BreadthFirstIterator<Vertex,Edge>(automaton, initState);
            while(iterator.hasNext()) {
                Vertex v = iterator.next();
                if (acceptanceStates.contains(v) || visitedStates.contains(v)) {
                    continue;
                }

                Vertex acceptanceState = findNearest(automaton, v, acceptanceStates);
                {
                    TestSequence test = buildTest(automaton, v, initState, acceptanceState);
                    GraphPath<Vertex, Edge> cycle = ListUtils.randomSelect(simpleCycles.get(acceptanceState));

                    if(cycle.getLength() > 0) {
                        int startBeta = test.getAssignmentList().size();
                        test.add(cycle);
                        test.getAssignmentList().get(startBeta).setStartBeta(true);
                    }
                    tests.add(test);

                    for(Edge edge: test.getPath())
                        visitedStates.add(edge.getTarget());
                }



            }
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

    public TestSequence buildTest(BuchiAutomaton g, Vertex v, Vertex initState, Vertex acceptanceState) {
        TestSequence test = new TestSequence();
        DijkstraShortestPath<Vertex, Edge> sp = new DijkstraShortestPath<>(g);

        if(v != initState) {
            GraphPath<Vertex, Edge> prefix = sp.getPath(initState, v);
            test.add(prefix);
        }


        GraphPath<Vertex, Edge> spToAcceptance = sp.getPath(v, acceptanceState);
        test.add(spToAcceptance);

        return test;
    }

}
