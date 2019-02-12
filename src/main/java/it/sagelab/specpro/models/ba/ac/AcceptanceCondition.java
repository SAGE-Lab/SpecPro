package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;

public interface AcceptanceCondition {

    boolean accept(Edge[] path);

}
