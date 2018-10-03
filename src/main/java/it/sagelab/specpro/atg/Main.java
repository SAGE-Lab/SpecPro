package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.alg.IterativeDeepeningDFS;
import it.sagelab.specpro.atg.alg.TransionCoveregePathsFinder;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.reasoners.translators.spot.LTL2BA;
import it.sagelab.specpro.reasoners.translators.spot.SpotTranslator;
import org.jgrapht.Graph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "car.req";
        Snl2FlParser parser = new Snl2FlParser();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
            parser.parseFile(filePath);
            parser.translate(new SpotTranslator(), ps);
        }
        String ltlFormula = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        //System.out.println(ltlFormula);

        LTL2BA ltl2ba = new LTL2BA();
        Graph<Vertex, Edge> graph = ltl2ba.translate(ltlFormula);

        //TransionCoveregePathsFinder pf = new TransionCoveregePathsFinder(graph);
        IterativeDeepeningDFS pf = new IterativeDeepeningDFS(graph, 10);
        pf.find();

        int maxLen = 0;
        for(List<Assignment> p : pf.getPaths()) {
            if(p.size() > maxLen)
                maxLen = p.size();
            System.out.println(p);
        }

        System.out.println("Max len: " + maxLen);
        System.out.println("Number of Sequences: " + pf.getPaths().numberOfSequences());


    }

}
