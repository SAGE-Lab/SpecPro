package it.sagelab.specpro.models.ba;

import it.sagelab.specpro.models.ba.ac.AcceptanceCondition;

import java.util.*;

public class BuchiAutomatonIterator implements Iterator<List<Edge>> {

    private final BuchiAutomaton buchiAutomaton;
    private final int length;
    private final AcceptanceCondition acceptanceCondition;
    private Edge[] path;
    private Deque<Iterator<Edge>> stack;

    public BuchiAutomatonIterator(BuchiAutomaton buchiAutomaton, AcceptanceCondition acceptanceCondition, int length) {
        this.buchiAutomaton = buchiAutomaton;
        this.acceptanceCondition = acceptanceCondition;
        this.length = length;
        path = new Edge[length];
        stack = new ArrayDeque<>();

        HashSet<Edge> initEdges = new HashSet<>();
        for(Vertex v : buchiAutomaton.initStates()) {
            for (Edge e : buchiAutomaton.outgoingEdgesOf(v)) {
                initEdges.addAll(buchiAutomaton.outgoingEdgesOf(e.getTarget()));
            }
        }

        stack.add(initEdges.iterator());
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()) {
            Iterator<Edge> itr = stack.peek();
            if (itr.hasNext()) {
                Edge e = itr.next();
                path[stack.size() - 1] = e;
                stack.push(buchiAutomaton.outgoingEdgesOf(e.getTarget()).iterator());
            } else {
                stack.pop();
            }

            if (stack.size() == length + 1) {
                stack.pop();
                if (acceptanceCondition.accept(path))
                    return true;
            }
        }

        return false;
    }

    @Override
    public List<Edge> next() {
        return Arrays.asList(path);
    }

}
