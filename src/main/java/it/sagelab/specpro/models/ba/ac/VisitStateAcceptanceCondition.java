package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

public class VisitStateAcceptanceCondition implements AcceptanceCondition {

    private Vertex state;
    private int startIndex = -1;


    public Vertex getState() {
        return state;
    }

    public void setState(Vertex state) {
        this.state = state;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public boolean accept(Edge[] path) {
        if(path.length == 0) {
            return false;
        }
        if(startIndex < 0) {
            return path[path.length - 1].getTarget().equals(state);
        } else {
            boolean visisted = false;
            for(int i = startIndex; i < path.length; ++i) {
                visisted |= path[i].getTarget().equals(state);
            }
            return visisted;
        }
    }
}
