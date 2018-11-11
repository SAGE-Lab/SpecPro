package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.generators.RequirementsTestGenerator;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class AutomaticTestGenerator {

    private int min, max;
    private OutputStream outputStream;
    private Snl2FlParser parser;

    public AutomaticTestGenerator(OutputStream outputStream, Snl2FlParser parser, int min, int max) {
        this.outputStream = outputStream;
        this.parser = parser;
        this.min = min;
        this.max = max;
    }

    public void run() throws IOException {
        RequirementsTestGenerator rtg = new RequirementsTestGenerator(parser);

        PrintWriter printWriter = new PrintWriter(outputStream);

        for (int pathLenght = min; pathLenght < max; ++pathLenght) {
            Trie<Assignment> result = rtg.generate(pathLenght);

            for (List<Assignment> p : result) {
                printWriter.println(p);
            }
        }

        printWriter.close();
    }

}
