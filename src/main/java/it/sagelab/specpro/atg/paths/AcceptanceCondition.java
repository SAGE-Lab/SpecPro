package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.models.ba.Edge;

public interface AcceptanceCondition {

    boolean accept(Edge[] path);

    void accepted(Edge[] path);
}
