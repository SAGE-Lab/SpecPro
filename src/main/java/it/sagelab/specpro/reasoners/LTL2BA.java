package it.sagelab.specpro.reasoners;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.DotBuilder;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import org.apache.commons.io.IOUtils;
import org.jgrapht.io.DOTImporter;
import org.jgrapht.io.ImportException;

import java.io.IOException;
import java.io.StringReader;

public class LTL2BA {

    DotBuilder db;

    DOTImporter<Vertex, Edge> importer;

    public LTL2BA() {
        db = new DotBuilder();
        importer = new DOTImporter<>(db, db, db);
    }

    public BuchiAutomaton translate(String formula) {
        BuchiAutomaton g = new BuchiAutomaton(db);
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String input = null;
        try {
            ProcessBuilder builder = new ProcessBuilder("ltl2tgba", "-B", formula, "--low","-d");
            builder.redirectErrorStream(true);
            builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
            process = builder.start();

            input = IOUtils.toString(process.getInputStream());

            int exitValue = process.waitFor();
            if(exitValue != 0) {
                System.err.println(input);
                throw new RuntimeException("ltl2gba returned with value " + exitValue);
            }
            else {
                input = input.replace("label=\"\\n[Büchi]\"", "");
                importer.importGraph(g, new StringReader(input));
            }

        } catch (IOException | InterruptedException | ImportException e) {
            if(process != null)
                process.destroyForcibly();
            e.printStackTrace();
        }

        return g;
    }

}
