package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.reasoners.translators.spot.LTL2BA;
import it.sagelab.specpro.reasoners.translators.spot.SpotTranslator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class RequirementsTestGenerator {

    protected ArrayList<Trie<Assignment>> singlePaths = new ArrayList<>();
    protected ArrayList<BuchiAutomata> graphs = new ArrayList<>();
    protected PathsBuilder pathsBuilder = new PathsBuilder();


    public RequirementsTestGenerator(String filePath) throws IOException {
        parseRequirements(filePath);
    }

    public RequirementsTestGenerator() {  }


    public Trie generate(int length, boolean allCombinations) {
        Trie<Assignment> completePaths = new Trie<>();
        pathsBuilder.setAllCombinations(allCombinations);
        singlePaths = new ArrayList<>();
        generatePaths(length);

        generate(completePaths);

        return completePaths;
    }

    protected abstract void generate(Trie<Assignment> completePaths);


    /***********************************************************
     *
     * Private Methods
     *
     ***********************************************************/



    protected void parseRequirements(String filePath) throws IOException {
        Snl2FlParser parser = new Snl2FlParser();
        parser.parseFile(filePath);

        LTL2BA ltl2ba = new LTL2BA();

        List<Requirement> requirements = new ArrayList<>(parser.getRequirements());

        for(Requirement r: requirements) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
                parser.getRequirements().clear();
                parser.getRequirements().add(r);
                parser.translate(new SpotTranslator(), ps);

                String ltlFormula = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                System.out.println(ltlFormula);

                graphs.add(ltl2ba.translate(ltlFormula));
            }

        }
    }

    protected void generatePaths(int length) {
        int sum = 0;
        for(BuchiAutomata g : graphs) {
            pathsBuilder.reset();
            //IterativeDeepeningDFS pf = new IterativeDeepeningDFS(g, 1, length, pathsBuilder);
            IterativeDeepeningDFS pf = new TransionCoveregePathsFinder(g, length, length, pathsBuilder);
            pf.find();
            Trie<Assignment> trie = pf.getPaths();
            singlePaths.add(trie);
            System.out.println("Automata # " + singlePaths.size() + " #sequences: " + trie.numberOfSequences());
            sum += trie.numberOfSequences();
        }

        System.out.println("Sequences to evaluate: " + sum);
    }


}
