package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

public class LazySchapedAcceptanceCondition implements AcceptanceCondition {

    @Override
    public boolean accept(Edge[] path) {
        int n = path.length;
        Vertex[] visitedStates = new Vertex[n];
        for(int i = 0; i < n; ++i)
            visitedStates[i] = path[i].getTarget();
        int index = 0;
        while(visitedStates[index] != visitedStates[n - 1])
            ++index;
        for(int i = index + 1; i < n; ++i) {
            if(visitedStates[i].isAcceptingState()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void accepted(Edge[] path) {

    }
}
