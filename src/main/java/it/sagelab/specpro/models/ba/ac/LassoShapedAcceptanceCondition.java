package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

public class LassoShapedAcceptanceCondition implements AcceptanceCondition {

    @Override
    public boolean accept(Edge[] path) {
        if(path == null)
            return false;
        int n = path.length;
        Vertex[] visitedStates = new Vertex[n + 1];
        visitedStates[0] = path[0].getSource();
        for(int i = 0; i < n; ++i)
            visitedStates[i + 1] = path[i].getTarget();
        int index = 0;
        while(visitedStates[index] != visitedStates[n - 1])
            ++index;
        for(int i = index; i <= n; ++i) {
            if(visitedStates[i].isAcceptingState()) {
                return true;
            }
        }
        return false;
    }

}
