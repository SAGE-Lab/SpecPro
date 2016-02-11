package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;
import it.sagelab.specpro.reasoners.translators.spot.LTL2BA;
import it.sagelab.specpro.reasoners.translators.spot.SpotTranslator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public abstract class RequirementsTestGenerator {

    protected ArrayList<Trie<Assignment>> singlePaths = new ArrayList<>();
    protected ArrayList<BuchiAutomata> graphs = new ArrayList<>();
    protected PathsBuilder pathsBuilder = new PathsBuilder();
    protected ArrayList<Requirement> requirements = new ArrayList<>();


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

        requirements = new ArrayList<>(parser.getRequirements());

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
        int i = 0;
        int sum = 0;
        for(Requirement r: requirements) {
            Set<VariableExpression> inputVars = r.getVariables().stream().filter(v -> v.isInput()).collect(Collectors.toSet());
            if(inputVars.size() > 0) {
                System.out.println("Req: " + r.getText());
                BuchiAutomata g = graphs.get(i);
                pathsBuilder.reset();
                IterativeDeepeningDFS pf = new IterativeDeepeningDFS(g, length, length, pathsBuilder);

                //IterativeDeepeningDFS pf = new TransionCoveregePathsFinder(g, length, length, pathsBuilder);
                pf.find();
                Trie<Assignment> trie = pf.getPaths();

                Trie<Assignment> inputPaths = new Trie<>();

                for(List<Assignment> path: trie) {
                    boolean containsInput = false;
                    for(Assignment a: path) {
                        for(VariableExpression ve: inputVars) {
                            Atom atom = a.getAtom(ve.getLabel());
                            if(atom != null && a.getValue(atom)) {
                                containsInput = true;
                            }
                            else {
                                a.add(new Atom(ve.getLabel()), false);
                            }
                        }
                    }

                    if(containsInput) {
                        inputPaths.insert(path);
                    }

                }

                singlePaths.add(inputPaths);
                System.out.println("Automata # " + singlePaths.size() + " #sequences: " + trie.size());
                sum += trie.size();

            }
            ++i;
        }
        System.out.println("Sequences to evaluate: " + sum);
    }


}
