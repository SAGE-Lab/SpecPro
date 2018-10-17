package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.reasoners.translators.spot.LTL2BA;
import it.sagelab.specpro.reasoners.translators.spot.SpotTranslator;
import org.jgrapht.Graph;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RequirementsTestGenerator {

    private ArrayList<Trie<Assignment>> singlePaths = new ArrayList<>();
    private Trie<Assignment> completePaths = new Trie<>();
    private Trie<Assignment> visitedPaths = new Trie<>();
    protected ArrayList<BuchiAutomata> graphs = new ArrayList<>();
    private ArrayList<Iterator<List<Assignment>>> iterators = new ArrayList<>();
    private PathsBuilder pathsBuilder = new PathsBuilder();


    public RequirementsTestGenerator(String filePath) throws IOException {
        parseRequirements(filePath);
        generatePaths(4);
    }

    public RequirementsTestGenerator() {

    }

    public Trie generate() {
        filter();

        for(int i = 0; i < singlePaths.size(); ++i) {
            System.out.println("Evaluating req " + (i + 1) + "/" + singlePaths.size());
            Trie<Assignment> trie = singlePaths.get(i);
            List<List<Assignment>> pathsToRemove = new ArrayList<>();
            int n = 1;
            for(List<Assignment> currentPath: trie) {
                System.out.println("\tpath " + (n++) + "/" + trie.numberOfSequences());
                if(visitedPaths.contains(currentPath))
                    continue;
                HashSet<List<Assignment>> visited = new HashSet<>();
                visited.add(currentPath);
                List<Assignment> prod = findValidMul(currentPath, i, 0, visited);

                if(prod != null) {
                    completePaths.insert(prod);

                    for(List<Assignment> visitedPath: visited)
                        visitedPaths.insert(visitedPath);
                } else {
                    System.out.println("Path not valid: " + currentPath);
                    pathsToRemove.add(currentPath);
                }
            }

            for(List<Assignment> path: pathsToRemove) {
                trie.remove(path);
            }
            iterators.set(i, trie.iterator());

        }

        return completePaths;
    }

    /***********************************************************
     *
     * Private Methods
     *
     ***********************************************************/

    private void filter() {
        for(int i = 0; i < singlePaths.size(); ++i) {
            System.out.println("Filtering paths " + (i + 1) + "/" + singlePaths.size());
            Trie<Assignment> trie = singlePaths.get(i);
            Trie<Assignment> filteredPaths = new Trie<>();
            System.out.println("Initial paths: " + trie.numberOfSequences());
            for(List<Assignment> currentPath: trie) {
                Trie<Assignment> temp = new Trie<>();
                temp.insert(currentPath);

                boolean isValid = true;
                for (Trie<Assignment> t : singlePaths) {
                    if (t == trie)
                        continue;
                    Trie<Assignment> compatiblePaths = temp.andJoin(t, (a1, a2) -> {
                        if (a1 == null || a2 == null)
                            return null;
                        if (a1.isCompatible(a2))
                            return a2;
                        else
                            return null;
                    });
                    if (compatiblePaths.numberOfSequences() == 0) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    filteredPaths.insert(currentPath);
                } else {
                    System.out.println("Path filtered out: " + currentPath);
                }
            }
            System.out.println("Filtered paths: " + filteredPaths.numberOfSequences());
            if(filteredPaths.numberOfSequences() > 0) {
                singlePaths.set(i, filteredPaths);
                iterators.set(i, singlePaths.get(i).iterator());
            } else {
                singlePaths.remove(i);
                iterators.remove(i);
                --i;
            }
        }
    }

    private List<Assignment> findValidMul(List<Assignment> currentPath, int nReq, int index, Set<List<Assignment>> visited) {
        //System.out.println("\t\t mul index " + index);
        if(index == nReq)
            return findValidMul(currentPath, nReq, index + 1, visited);
        if(index >= singlePaths.size())
            return currentPath;
        Trie<Assignment> trie = singlePaths.get(index);
//        Trie<Assignment> pathTrie = new Trie<>();
//        pathTrie.insert(currentPath);
//
//
//        System.out.println("Current path: " + currentPath);
//        for(List<Assignment> p : trie) {
//            System.out.println(p);
//        }
//
//        Trie<Assignment> prodTrie = pathTrie.andJoin(trie, (a1, a2) -> {
//            if(a1 == null || a2 == null)
//                return null;
//            System.out.println("Combining " + a1 + " and " + a2);
//            return a1.combine(a2);
//        });
//
//        System.out.println("ProdTrie size: " + prodTrie.numberOfSequences());
//        if(currentPath.size() > 1)
//            System.exit(0);
//
//        for(List<Assignment> path: prodTrie) {
//            List<Assignment> newPath = findValidMul(path, nReq, index + 1, visited);
//            if(newPath != null) {
//                Trie<Assignment> t = new Trie<>();
//                t.insert(newPath);
//
//                t = t.andJoin(prodTrie, (a1, a2) -> {
//                    if(a1 == null || a2 == null || !a1.contains(a2))
//                        return null;
//                    else
//                        return a2;
//                });
//
//                for(List<Assignment> p: t)
//                    visited.add(p);
//
//                return newPath;
//            }
//        }

        Iterator<List<Assignment>> iterator = iterators.get(index);
        for(int i = 0; i < trie.numberOfSequences(); ++i) {
            if(!iterator.hasNext()) {
                iterator = trie.iterator();
                iterators.set(index, iterator);
            }

            if(!iterator.hasNext()) {
                throw new RuntimeException("Trie # " + i + " is empty!");
            }

            List<Assignment> prodPath = iterator.next();
            List<Assignment> newPath = prod(currentPath, prodPath);
            if(newPath != null) {
                newPath = findValidMul(newPath, nReq, index + 1, visited);

                if(newPath != null) {
                    visited.add(prodPath);
                    return newPath;
                }
            }

        }

        return null;
    }

    private List<Assignment> prod(List<Assignment> l1, List<Assignment> l2) {
        if(l1.size() != l2.size())
            return null;
        Iterator<Assignment> i1 = l1.iterator();
        Iterator<Assignment> i2 = l2.iterator();

        ArrayList<Assignment> result = new ArrayList<>();

        while(i1.hasNext()) {
            Assignment a1 = i1.next();
            Assignment a2 = i2.next();
            Assignment a = a1.combine(a2);
            if(a == null)
                return null;
            result.add(a);
        }

        return result;
    }

    private void parseRequirements(String filePath) throws IOException {
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
            IterativeDeepeningDFS pf = new IterativeDeepeningDFS(g, 1, length, pathsBuilder);
            //IterativeDeepeningDFS pf = new TransionCoveregePathsFinder(g, 1, length, pathsBuilder);
            pf.find();
            Trie<Assignment> trie = pf.getPaths();
            singlePaths.add(trie);
            iterators.add(trie.iterator());
            System.out.println("Automata # " + singlePaths.size() + " #sequences: " + trie.numberOfSequences());
            sum += trie.numberOfSequences();
        }

        System.out.println("Sequences to evaluate: " + sum);
    }


}
