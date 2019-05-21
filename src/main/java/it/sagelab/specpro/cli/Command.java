package it.sagelab.specpro.cli;

import it.sagelab.specpro.models.ltl.LTLSpec;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.PrintStream;

public abstract class Command {

    protected PrintStream outStream = System.out;
    protected LTLSpec spec;

    public void setOutStream(PrintStream outStream) {
        this.outStream = outStream;
    }

    public void setLTLSpec(LTLSpec spec) { this.spec = spec; }

    public LTLSpec getLTLSpec() { return spec; }

    public abstract String getName();

    public abstract String getDescription();

    public abstract Options createOptionMenu();

    public abstract void run(CommandLine commandLine) throws IOException;

}
