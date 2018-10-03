package it.sagelab.specpro.reasoners.translators.spot;

import it.sagelab.specpro.models.ba.DotBuilder;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import org.apache.commons.io.IOUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
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

    public Graph<Vertex, Edge> translate(String formula) {
        Graph<Vertex, Edge> g = new DirectedPseudograph<>(db);
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String[] command = {"ltl2tgba", "-B", formula, "-d"};
        try {
            process = rt.exec(command);
            process.waitFor();
            String error = IOUtils.toString(process.getErrorStream());
            if(error.length() > 0)
                System.err.println(error);
            String input = IOUtils.toString(process.getInputStream());
            input = input.replace("label=\"\\n[Büchi]\"", "");
            //System.out.println(input);
            importer.importGraph(g, new StringReader(input));

        } catch (IOException | InterruptedException | ImportException e) {
            e.printStackTrace();
            g = null;
        }

        return g;
    }

}
