package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.collections.SetUtils;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.testing.SUT;
import it.sagelab.specpro.testing.TestingEnvironment;
import it.sagelab.specpro.testing.oracles.TestOracle;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Trace;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TestsGenerator defines the interface needed to generate test cases with a given Buchi Automaton within a {@link TestingEnvironment}.
 * It also provides some utils functions that are in common with many tests generation algorithms.
 */
public abstract class TestGenerator {

    protected BuchiAutomaton automaton;
    protected final Set<Atom> inputVariables;

    public TestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        this.automaton = automaton;
        this.inputVariables = inputVariables;
    }

    /**
     * Returns the oracle used by this algorithm to validate the traces produced by the {@link SUT}.
     * @return A TestOracle.
     */
    public abstract TestOracle getOracle();

    /**
     * Resets the generator to it's initial state
     */
    public abstract void reset();

    /**
     * Returns true if the algorithm has more tests to generate.
     * @return True is there are more tests to execute, False otherwise.
     */
    public abstract boolean hasNext();

    /**
     * Generates the next test to execute, or part of it.
     * @return A trace containing the input assignments to execute on the {@link SUT}.
     */
    public abstract Trace next();

    /**
     * Checks the current status of the executed test and return True if it is considered complete.
     * @param trace The current trace (inputs and outputs) played on the {@link SUT}.
     * @param value The current value attributed to the trace by the {@link TestOracle}.
     * @return True if the current test is complete, False otherwise.
     */
    public abstract boolean isCurrentTraceComplete(Trace trace, TestOracle.Value value);

    /**
     * This method is called when a trace is complete, either because {@link #isCurrentTraceComplete}
     * returns true or because the {@link TestingEnvironment} decided to interrupt the test.
     * @param trace The completed trace.
     */
    public abstract void traceCompleted(Trace trace);

    protected List<Edge> shortestPathTo(Vertex v) {
        return shortestPathFromTo(automaton.initStates().iterator().next(), v);
    }

    protected List<Edge> shortestPathTo(Edge e) {
        return shortestPathFromTo(automaton.initStates().iterator().next(), e);
    }

    protected List<Edge> shortestPathFromTo(Vertex v1, Vertex v2) {
        List<Edge> run = new ArrayList<>();
        DijkstraShortestPath<Vertex, Edge> sp = new DijkstraShortestPath<>(automaton);

        GraphPath<Vertex, Edge> prefix = sp.getPath(v1, v2);
        if(prefix != null) {
            run.addAll(prefix.getEdgeList());
            return run;
        }
        else {
            return null;
        }
    }

    protected List<Edge> shortestPathFromTo(Vertex v1, Edge e) {
        List<Edge> run = new ArrayList<>();
        DijkstraShortestPath<Vertex, Edge> sp = new DijkstraShortestPath<>(automaton);

        GraphPath<Vertex, Edge> prefix = sp.getPath(v1, e.getSource());

        if(prefix != null) {
            run.addAll(prefix.getEdgeList());
            run.add(e);
            return run;
        } else {
            return null;
        }

    }

    protected Assignment getInput(Assignment a) {
        return a.filter(inputVariables, x -> false);
    }

    protected  Assignment getInput(Edge e) {
        return getInput(SetUtils.pickRandom(e.getAssigments()));
    }

    protected Set<Edge> getCompatibleEdges(Vertex v, Assignment assignment) {
        return automaton.outgoingEdgesOf(v).stream()
                .filter(e -> e.getAssigments().stream().filter(a -> a.isCompatible(assignment)).findAny().isPresent())
                .collect(Collectors.toSet());
    }

}
