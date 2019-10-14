package it.sagelab.specpro.testing.generators;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class GDFSStarTestGenerator extends GDFSTestGenerator {

    protected HashMap<Vertex, Integer> distanceToAcceptance;

    public GDFSStarTestGenerator(BuchiAutomaton automaton, Set<Atom> inputVariables) {
        super(automaton, inputVariables);

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

    @Override
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
}
