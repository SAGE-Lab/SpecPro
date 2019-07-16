package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.utils.BAFilter;
import it.sagelab.specpro.atg.utils.OutputGenerator;
import it.sagelab.specpro.atg.utils.OutputSelector;
import it.sagelab.specpro.atg.utils.RandomOutputSelector;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.GraphPath;;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class SPTestGenerator extends LTLTestGenerator {

    private static final Logger logger = LogManager.getLogger(CoverageBesedTestGenerator.class);

    OutputSelector outputSelector = new RandomOutputSelector();
    OutputGenerator outputGenerator = new OutputGenerator();

    private boolean saturateInput = false;

    public void setSaturateInput(boolean saturateInput) {
        this.saturateInput = saturateInput;
    }

    public OutputGenerator getOutputGenerator() {
        return outputGenerator;
    }

    public void setOutputSelector(OutputSelector selector) {
        this.outputSelector = selector;
    }


    public Trie<Assignment> selectInputs(LTLSpec spec) {
        Trie<Assignment> inputSequences = new Trie<>();
        HashSet<Edge> visitedEdges = new HashSet<>();

        BuchiAutomaton automaton = BAFilter.inputFilter(buchiAutomata.get(0), spec.getInputVariables());

        Set<Vertex> initStates = automaton.initStates();
        Set<Vertex> acceptanceStates = automaton.acceptanceStates();

        HashMap<Vertex, Vertex> nearestAccState = new HashMap<>();
        for(Vertex v: automaton.vertexSet()) {
            Vertex nearest = findNearest(automaton, v, acceptanceStates);
            nearestAccState.put(v, nearest);
        }

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
                        List<Assignment> assignmentList = new ArrayList(test.getAssignmentList());
                        // BAFilter.trim(assignmentList);
                        if(saturateInput) {
                            saturateInput(assignmentList, spec);
                        }

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

                        for(Edge edge: test.getPath())
                            visitedEdges.add(edge);
                    }

                }

            }
        }

        logger.info("Input sequences: " + inputSequences.size());
        logger.info("Remove shorter utils...");
        inputSequences.removePartialPaths();
        logger.info("Input sequences: " + inputSequences.size());

        return inputSequences;
    }

    @Override
    public Set<TestSequence> generate(LTLSpec spec) throws IOException {
        parseRequirements(spec, true);
        HashSet<TestSequence> tests = new HashSet<>();

//        for(List<Assignment> assignments: inputSequences) {
//            tests.add(outputSelector.selectOutput(buchiAutomata.get(0), assignments));
//        }

        return tests;
    }

    @Override
    public Set<TestCase> generateTestCases(LTLSpec spec, PrintStream outStream) throws IOException {
        parseRequirements(spec, true);
        outStream.println("BA Generated!");
        BuchiAutomaton automaton = buchiAutomata.get(0);

        Trie<Assignment> inputSequences = selectInputs(spec);
        outStream.println("# inputs:" + inputSequences.size());


        HashSet<TestCase> testCases = new HashSet<>();

        for(List<Assignment> input: inputSequences) {
            TestCase t = outputGenerator.generate(automaton, spec, input);
            testCases.add(t);
            outStream.println("-input: " + t.getInput());
            outStream.println(t.getOutputs().size());
            t.getOutputs().forEach(o -> outStream.println(o));
        }

        return testCases;
    }

    private void saturateInput(List<Assignment> input, LTLSpec spec) {
        for(Assignment a: input) {
            for(Atom i: spec.getInputVariables()) {
                if(!a.contains(i)) {
                    a.add(i, false);
                }
            }
        }
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

}
