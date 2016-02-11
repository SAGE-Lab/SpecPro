package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.util.List;

public class BARunnerTestGenerator extends RequirementsTestGenerator {

    public BARunnerTestGenerator(String filePath) throws IOException {
        super(filePath);
    }

    @Override
    protected void generate(Trie<Assignment> completePaths) {
        PathEvaluator pathValidator = new PathEvaluator(graphs, pathsBuilder);
        int nReq = 1;
        for(Trie<Assignment> trie :  singlePaths) {
            System.out.println("Evaluating requiremnet " + nReq + " / " + singlePaths.size());
            ++nReq;
            int nPath = 1;
            for(List<Assignment> path: trie) {
                System.out.println("\tEvaluating path " + nPath + " / " + trie.size());
                ++nPath;
                List<Assignment> newPath = pathValidator.evaluate(path);
                if(newPath != null) {
                    System.out.println("**********************************************************");
                    System.out.println("New EdgeSequence found!");
                    newPath.forEach(System.out::println);
                    System.out.println("**********************************************************");
                    completePaths.insert(newPath);
                }
            }
        }
    }



}
