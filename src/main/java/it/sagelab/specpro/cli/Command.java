package it.sagelab.specpro.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.PrintStream;

public abstract class Command {

    protected PrintStream outStream = System.out;
    protected String inputFile;

    public void setOutStream(PrintStream outStream) {
        this.outStream = outStream;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract Options createOptionMenu();

    public abstract void run(CommandLine commandLine) throws IOException;

}
