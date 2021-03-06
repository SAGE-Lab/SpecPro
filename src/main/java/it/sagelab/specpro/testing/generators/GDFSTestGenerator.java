package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.Trace;
import it.sagelab.specpro.testing.oracles.FLTLOracle;
import it.sagelab.specpro.testing.oracles.TestOracle;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class GDFSTestGenerator extends TestGenerator {


    protected final Map<Edge, Integer> visitsCounter;
    protected final Map<Edge, Assignment> inputMap;
    protected final HashMap<Vertex, Integer> distanceToAcceptance;
    protected final FLTLOracle oracle;

    protected int minLength = 1;
    protected int minCounterValue = 1;

    protected boolean finishedTest = true;

    Vertex currentState;
    ArrayList<Edge> traversedEdges = new ArrayList<>();
    Edge currentEdge;

    public GDFSTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);
        visitsCounter = new HashMap<>();
        inputMap = new HashMap<>();

        oracle = new FLTLOracle(automaton);

        distanceToAcceptance = new HashMap<>();
        LinkedList<Vertex> toVisit = new LinkedList<>();
        for(Vertex acc : automaton.acceptanceStates()) {
            distanceToAcceptance.put(acc, 0);
            toVisit.addAll(automaton.incomingEdgesOf(acc).stream().map(e -> e.getSource()).filter(v -> !distanceToAcceptance.keySet().contains(v)).collect(toList()));
        }

        while(!toVisit.isEmpty()) {
            Vertex vertex = toVisit.pop();
            int distance = automaton.outgoingEdgesOf(vertex).stream().map(e -> e.getTarget()).mapToInt(v -> distanceToAcceptance.getOrDefault(v, Integer.MAX_VALUE)).min().getAsInt();
            distanceToAcceptance.put(vertex, distance + 1);
            toVisit.addAll(automaton.incomingEdgesOf(vertex).stream().map(e -> e.getSource()).filter(v -> !distanceToAcceptance.keySet().contains(v)).collect(toList()));
        }
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMinCounterValue(int minCounterValue) {
        this.minCounterValue = minCounterValue;
    }

    @Override
    public TestOracle getOracle() {
        return oracle;
    }

    @Override
    public void reset() {
        visitsCounter.clear();
    }

    @Override
    public boolean hasNext() {
        if(!finishedTest) {
            return true;
        }
        finishedTest = false;
        currentState = automaton.initStates().iterator().next();
        Optional<Integer> minValue = visitsCounter.values().stream().min(Integer::compareTo);
        return !minValue.isPresent() || (minValue.get() < minCounterValue);
    }

    @Override
    public Trace next() {
        Trace t = new Trace();
        for(Edge e: automaton.outgoingEdgesOf(currentState)) {
            visitsCounter.putIfAbsent(e, 0);
        }
        currentEdge = nextEdge(currentState);

        Assignment input = getInput(currentEdge);

        for(Edge e: automaton.compatibleEdges(currentState, input)) {
            if(getInput(e).equals(input)) {
                visitsCounter.compute(e, (edge, val) -> val + 1);
            }
        }

        visitsCounter.compute(currentEdge , (edge, val) -> val + 1);

        t.add(input);
        return t;
    }

    @Override
    public boolean isCurrentTraceComplete(Trace trace, TestOracle.Value value) {
        Set<Edge> compatibleEdges = automaton.compatibleEdges(currentState, trace.last());
        if(compatibleEdges.isEmpty()) {
           compatibleEdges = oracle.getCurrentStates().stream().flatMap(v -> automaton.compatibleEdges(v, trace.last()).stream()).collect(toSet());
        }

        Edge e = null;
        if(compatibleEdges.contains(currentEdge)) {
            e = currentEdge;
        } else {
            e = selectEdgeWithBestScore(compatibleEdges);
        }

        traversedEdges.add(e);
        currentState = e.getTarget();


        return trace.size() > minLength && currentState.isAcceptingState();
    }

    @Override
    public void traceCompleted(Trace trace) {
        finishedTest = true;
    }

    protected Edge nextEdge(Vertex v) {
        return selectEdgeWithBestScore(automaton.outgoingEdgesOf(v));
    }

    protected Edge selectEdgeWithBestScore(Set<Edge> edgeSet) {
        List<Edge> edges = edgeSet.stream().sorted((e1, e2) -> {
            int score = 4 * Integer.compare(visitsCounter.getOrDefault(e1, Integer.MAX_VALUE),  visitsCounter.getOrDefault(e2, Integer.MAX_VALUE));
            score += 2 * Integer.compare(distanceToAcceptance.get(e1.getTarget()), distanceToAcceptance.get(e2.getTarget()));
            score += -Integer.compare(automaton.outDegreeOf(e1.getTarget()), automaton.outDegreeOf(e2.getTarget()));
            if(score != 0) {
                return score;
            } else {
                return Integer.compare(e1.getId(), e2.getId());
            }

        }).collect(toList());

        if(edges.isEmpty()) {
            return null;
        }

        return edges.get(0);
    }

    @Override
    protected Assignment getInput(Edge e) {
        return inputMap.computeIfAbsent(e, super::getInput);
    }

}
