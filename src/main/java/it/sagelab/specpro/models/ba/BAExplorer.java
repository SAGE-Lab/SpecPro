package it.sagelab.specpro.models.ba;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ba.ac.AcceptanceCondition;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BAExplorer {

    private Trie<Edge> paths;
    private Edge[] path;
    private int length;
    private BuchiAutomaton ba;
    private final Set<AcceptanceCondition> conditions;

    public BAExplorer()  {
        conditions = new HashSet<>();

        path = null;
        length = 0;
    }

    public void addAcceptanceCondition(AcceptanceCondition condition) {
        conditions.add(condition);
    }

    public void removeAcceptanceCondition(AcceptanceCondition condition) {
        conditions.remove(condition);
    }

    public Set<AcceptanceCondition> getAcceptanceConditions() {
        return conditions;
    }

    public void setLength(int length) {
        this.length = length;
        path = new Edge[length];
    }

    public Trie<Edge> generatePaths(BuchiAutomaton ba) {
        paths = new Trie<>();
        this.ba = ba;

        for(Vertex v : ba.initStates()) {
            for(Edge e: ba.outgoingEdgesOf(v)) {
                dfs(e.getTarget(), length);
            }
        }

        return paths;
    }

    public Trie<Edge> findInducedPaths(BuchiAutomaton ba, List<Assignment> test) {
        paths = new Trie<>();
        this.ba = ba;

        for(Vertex v : ba.initStates()) {
            for(Edge e: ba.outgoingEdgesOf(v)) {
                inducedDfs(e.getTarget(), test, length);
            }
        }

        return paths;
    }



    /***************************************************
     *
     * Private Methods
     *
     ***************************************************/

    private void acceptPath() {
        for(AcceptanceCondition ac: conditions) {
            if(!ac.accept(path))
                return;
        }
        paths.insert(path);
    }

    private void dfs(Vertex v, int n) {

        if(n < 0) {
            throw new IllegalArgumentException("n can't be lower then 0");
        }

        if(n == 0) {
            acceptPath();
            return;
        }

        Set<Edge> edges = ba.outgoingEdgesOf(v);

        for(Edge e: edges) {
            path[path.length  - n] = e;
            dfs(e.getTarget(), n - 1);
        }

    }

    private void inducedDfs(Vertex v, List<Assignment> test, int n) {
        if(n < 0) {
            throw new IllegalArgumentException("n can't be lower then 0");
        }

        if(n == 0) {
            acceptPath();
            return;
        }

        Set<Edge> edges = ba.outgoingEdgesOf(v);

        for(Edge e: edges) {
            if(isInduced(e, test.get(test.size() - n))) {
                path[path.length - n] = e;
                dfs(e.getTarget(), n - 1);
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
