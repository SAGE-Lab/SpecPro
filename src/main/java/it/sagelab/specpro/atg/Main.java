package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.alg.*;
import it.sagelab.specpro.collections.Trie;

import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void smallAutomataProduct(String filePath) throws IOException {

        RequirementsTestGenerator rtg = new RequirementsTestGenerator(filePath);
        //RequirementsTestGenerator rtg = new SplitReqTestGenerator(filePath, 1);
        Trie<Assignment> result = rtg.generate();


        int count = 0;
        int maxLength = 0;
        for(List<Assignment> p : result) {
            maxLength = p.size() > maxLength ? p.size() : maxLength;
           System.out.println(p);
            ++count;
        }
        System.out.println("Number of Sequences: " + count);
        System.out.println("Max length: " + maxLength);

    }

    public static void main(String[] args) throws IOException {

        String filePath = "test.req";
//        Snl2FlParser parser = new Snl2FlParser();
//
//        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
//            parser.parseFile(filePath);
//            parser.translate(new SpotTranslator(), ps);
//        }
//        String ltlFormula = new String(baos.toByteArray(), StandardCharsets.UTF_8);
//        //System.out.println(ltlFormula);
//
//        LTL2BA ltl2ba = new LTL2BA();
//        Graph<Vertex, Edge> graph = ltl2ba.translate(ltlFormula);
//
//        TransionCoveregePathsFinder pf = new TransionCoveregePathsFinder(graph);
//        // IterativeDeepeningDFS pf = new IterativeDeepeningDFS(graph, 10);
//        pf.find();
//
//        int maxLen = 0;
//        for(List<Assignment> p : pf.getPaths()) {
//            if(p.size() > maxLen)
//                maxLen = p.size();
//            System.out.println(p);
//        }
//
//        System.out.println("Max len: " + maxLen);
//        System.out.println("Number of Sequences: " + pf.getPaths().numberOfSequences());

        smallAutomataProduct(filePath);
    }

}
