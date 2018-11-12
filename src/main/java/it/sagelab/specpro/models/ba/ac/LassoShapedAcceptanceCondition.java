package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

public class LassoShapedAcceptanceCondition implements AcceptanceCondition {

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

}
