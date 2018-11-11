package it.sagelab.specpro.atg.generators;

import it.sagelab.specpro.atg.paths.BAExplorer;
import it.sagelab.specpro.atg.paths.LazySchapedAcceptanceCondition;
import it.sagelab.specpro.atg.paths.TransitionCoverageAcceptanceCondition;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequirementsTestGenerator {

    private ArrayList<BuchiAutomata> buchiAutomata = new ArrayList<>();
    private ArrayList<Requirement> requirements = new ArrayList<>();
    private Set<VariableExpression> inputVars;

    public RequirementsTestGenerator(String filePath) throws IOException {
        Snl2FlParser parser = new Snl2FlParser();
        parser.parseFile(filePath);
        parseRequirements(parser);
    }

    public RequirementsTestGenerator(Snl2FlParser parser) throws IOException {
        parseRequirements(parser);
    }


    public Trie<Assignment> generate(int length) {
        Trie<Assignment> result = new Trie<>();
        int count = 0;
        for(BuchiAutomata ba: buchiAutomata) {
            System.out.println("Generating paths for req # " + (++count) + "/" + buchiAutomata.size());
            ArrayList<BuchiAutomata> automataList = new ArrayList<>(buchiAutomata);
            automataList.remove(ba);
            BATestGenerator baTestGenerator = new BATestGenerator(automataList);
            Trie<Edge> edgeTrie = generatePaths(ba, length);
            for(List<Edge> path: edgeTrie) {
                System.out.println("Testing path: " + path);
                for(List<Assignment> assPath: new SequenceBuilder<Assignment>(path)) {
                    List<Assignment> testingPath = new ArrayList<>();
                    boolean containsInput = false;
                    for(Assignment a: assPath) {
                        Assignment ass = new Assignment(a);
                        for(VariableExpression ve: inputVars) {
                            Atom atom = ass.getAtom(ve.getLabel());
                            if(atom != null && a.getValue(atom)) {
                                containsInput = true;
                            }
                            else {
                                ass.add(new Atom(ve.getLabel()), false);
                            }
                        }
                        testingPath.add(ass);
                    }

                    if(containsInput) {
                        System.out.println("Testing assign path: " + testingPath);
                        List<Assignment> test = baTestGenerator.generate(testingPath);
                        if (test != null) {
                            System.out.println("Wow, test found!");
                            result.insert(test);
                        }
                    }
                }
            }
        }

        return result;
    }


    /***********************************************************
     *
     * Private Methods
     *
     ***********************************************************/


    private Trie<Edge> generatePaths(BuchiAutomata ba, int length) {

        BAExplorer explorer = new BAExplorer();
        explorer.setLength(length);
        explorer.addAcceptanceCondition(new LazySchapedAcceptanceCondition());
        //explorer.addAcceptanceCondition(new TransitionCoverageAcceptanceCondition());

        return explorer.generatePaths(ba);
    }

    private void parseRequirements(Snl2FlParser parser) throws IOException {

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

                buchiAutomata.add(ltl2ba.translate(ltlFormula));
            }

        }

        inputVars = parser.getBuilder().getContext().getInputVariables();
    }
}
