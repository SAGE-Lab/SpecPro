package it.sagelab.specpro.testing;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.Trace;

import java.util.Set;

/**
 * SUT provides a standard interface to interact with the System Under Test.
 *
 * It is responsible to execute a sequence of inputs and to return the corresponding outputs.
 * A SUT should also provide a method to reset the system to it's initial state.
 */
public abstract class SUT {


    private final Set<Atom> inputs;
    private final Set<Atom> outputs;

    protected SUT(Set<Atom> inputs, Set<Atom> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public Set<Atom> getInputs() {
        return inputs;
    }

    public Set<Atom> getOutputs() {
        return outputs;
    }

    /**
     * Reset the system to it's initial state
     */
    abstract public void reset();

    /**
     * Exec an input action on the system and return the corresponding output.
     * The input assignment should contains a value for all the atoms in {@link #getInputs()} and the output should
     * contains a value for all the atoms in {@link #getOutputs()}.
     * @param input The input action
     * @return The produced outupt
     */
    abstract public Assignment exec(Assignment input) throws InvalidInputException;

    /**
     * Exec a sequence of inputs on the system and return the corresponding output.
     * The input trace should contains a value for all the atoms in {@link #getInputs()} at each step, and the output
     * trace should contains a value for all the atoms in {@link #getOutputs()}.
     * @param input A sequence of input actions
     * @return The produced output
     */
    public Trace exec(Trace input) {
        Trace output = new Trace();
        for(Assignment i: input) {
            output.add(exec(i));
        }
        return output;
    }

}
