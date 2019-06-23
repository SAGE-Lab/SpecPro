package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;

public class EndsWithAcceptanceStateCondition implements AcceptanceCondition {

    @Override
    public boolean accept(Edge[] path) {
        return path[path.length - 1].getTarget().isAcceptingState();
    }
}
