package it.sagelab.specpro.models.fsm;

import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {

    private final Assignment inputs;
    private final Assignment outputs;
    private final String source;
    private final String target;

    public Edge(String source, String target, Assignment input, Assignment output) {
        this.source = source;
        this.target = target;
        this.inputs = input;
        this.outputs = output;
    }

    public Assignment getInputs() {
        return inputs;
    }

    public Assignment getOutputs() {
        return outputs;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getTarget() {
        return target;
    }


}
