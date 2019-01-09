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
        String[] command = {"ltl2tgba", "-B", formula, "-d"};
        //String[] command = {"./ltl2gba_launcher.pl", formula};
        try {
            process = rt.exec(command);
            int exitValue = process.waitFor();
            if(exitValue != 0)
                throw new RuntimeException("ltl2gba returned with value " + exitValue);
            if(process.getErrorStream().available() > 0) {
                String error = IOUtils.toString(process.getErrorStream());
                if (error.length() > 0)
                    System.err.println(error);
            }
            if(process.getInputStream().available() > 0) {
                String input = IOUtils.toString(process.getInputStream());
                input = input.replace("label=\"\\n[Büchi]\"", "");
                //System.out.println(input);
                importer.importGraph(g, new StringReader(input));
            } else {
                return null;
            }

        } catch (IOException | InterruptedException | ImportException e) {
            if(process != null)
                process.destroyForcibly();
            e.printStackTrace();
            g = null;
        }

        return g;
    }

}
