package it.sagelab.specpro.atg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.List;

public class TestCase {

    private final List<Assignment> input;
    private final Trie<Assignment> outputs;

    public TestCase(List<Assignment> input) {
        this.input = input;
        this.outputs = new Trie<>();
    }

    public void addOutput(List<Assignment> output) {
        outputs.insert(output);
    }

    public List<Assignment> getInput() {
        return input;
    }

    public Trie<Assignment> getOutputs() {
        return outputs;
    }


}
