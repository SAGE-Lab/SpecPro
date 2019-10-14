package it.sagelab.specpro.models.fsm;

import it.sagelab.specpro.models.ltl.Atom;
import org.jgrapht.graph.DirectedPseudograph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MealyMachine extends DirectedPseudograph<String, Edge> {

    private String resetState;
    private List<Atom> inputs;
    private List<Atom> outputs;


    public MealyMachine() {
        super(Edge.class);
    }

    public void setResetState(String resetState) {
        this.resetState = resetState;
    }

    public String getResetState() {
        return resetState;
    }

    public List<Atom> getInputs() {
        return inputs;
    }

    public void setInputs(List<Atom> inputs) {
        this.inputs = inputs;
    }

    public List<Atom> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Atom> outputs) {
        this.outputs = outputs;
    }

    /**
     * Save the mealy machine in KISS format
     * @param fileName
     */
    public void saveKissFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(".inputs");
        for(Atom a: inputs) {
            fileWriter.write(" ");
            fileWriter.write(a.getName());
        }
        fileWriter.write("\n");
        fileWriter.write(".outputs");
        for(Atom a: outputs) {
            fileWriter.write(" ");
            fileWriter.write(a.getName());

        }
        fileWriter.write("\n");
        fileWriter.write(".i "  + inputs.size() + "\n");
        fileWriter.write(".o "  + outputs.size() + "\n");
        fileWriter.write(".p " + edgeSet().size() + "\n");
        fileWriter.write(".s " + vertexSet().size() + "\n");
        fileWriter.write(".r " + resetState + "\n");
        fileWriter.write(toString());
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Edge e: edgeSet()) {
            for (Atom i : inputs) {
                if(e.getInputs().contains(i)) {
                    builder.append(e.getInputs().getValue(i) ? "1" : "0");
                } else {
                    builder.append("-");
                }
            }
            builder.append(" ").append(e.getSource()).append(" ").append(e.getTarget()).append(" ");

            for (Atom o : outputs) {
                if(e.getOutputs().contains(o)) {
                    builder.append(e.getOutputs().getValue(o) ? "1" : "0");
                } else {
                    builder.append("-");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }


}
