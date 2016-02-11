package it.sagelab.specpro.atg.generators;

import it.sagelab.specpro.atg.paths.BAExplorer;
import it.sagelab.specpro.atg.paths.LazySchapedAcceptanceCondition;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;
import java.util.stream.Collectors;


public class BATestGenerator {

    private final List<BuchiAutomata> buchiAutomataList;
    private List<Assignment> test;

    public BATestGenerator(List<BuchiAutomata> buchiAutomataList) {
        this.buchiAutomataList = buchiAutomataList;
    }

    public List<Assignment> generate(List<Assignment> test) {
        this.test = test;
        List<Trie<Edge>> inducedPaths = new ArrayList<>();
        List<List<Edge>> prefixes = new ArrayList<>();

        BAExplorer explorer = new BAExplorer();
        explorer.setLength(test.size());
        explorer.addAcceptanceCondition(new LazySchapedAcceptanceCondition());

        for(BuchiAutomata ba: buchiAutomataList) {
            Trie<Edge> edgeInducedPaths = explorer.findInducedPaths(ba, test);
            if(edgeInducedPaths.size() == 0) {
                System.out.println("Impossible to find an induced path");
                return null;
            }
            inducedPaths.add(edgeInducedPaths);
            prefixes.add(new ArrayList<>());
        }

        Deque<Assignment> composedTest = findCompatibleEdges(inducedPaths, prefixes, test.size());
        if(composedTest != null) {
            ArrayList<Assignment> assignments = new ArrayList<>();
            System.out.println("******************************************************************************");
            System.out.println("New Path found: ");

            while (!composedTest.isEmpty()) {
                Assignment a = composedTest.pop();
                System.out.println(a);
                assignments.add(a);
            }
            System.out.println("******************************************************************************");

            return assignments;
        } else {
            return null;
        }
    }

    private Deque<Assignment> findCompatibleEdges(List<Trie<Edge>> inducedPaths, List<List<Edge>> prefixes, int n) {
        if(n == 0) {
            return new ArrayDeque<>();
        }

        ArrayList<Set<Edge>> successors = new ArrayList<Set<Edge>>();

        for(int i = 0; i < inducedPaths.size(); ++i) {
            successors.add(inducedPaths.get(i).getSuccessors(prefixes.get(i)));
        }

        for(List<Edge> sequence: new SequenceBuilder<>(successors)) {
            Assignment mergedAssignment = merge(sequence, test.size() - n);
            if(mergedAssignment != null) {
                for(int i = 0; i < sequence.size(); ++i) {
                    prefixes.get(i).add(sequence.get(i));
                }
                Deque<Assignment> res = findCompatibleEdges(inducedPaths, prefixes, n - 1);
                if(res != null) {
                    res.push(mergedAssignment);
                    return res;
                } else {
                    for(int i = 0; i < prefixes.size(); ++i) {
                        prefixes.get(i).remove(prefixes.get(i).size() - 1);
                    }
                }

            }

        }

        return null;
    }

    private Assignment merge(List<Edge> edges, int n) {
//        System.out.println("Merge " + n + " : " + edges);
        Assignment assignment = test.get(n);
        List<Set<Assignment>> assignments = new ArrayList<>();
        for(Edge e: edges) {
            assignments.add(e.getAssigments());
        }
        return merge(assignments, assignment);
    }

    private Assignment merge(List<Set<Assignment>> assignments, Assignment ass) {
        Assignment mergedAss = new Assignment(ass);
        assignments = filter(assignments, mergedAss);
//        System.out.println("Filtered successful? " + (assignments == null ? "No" : "Yes"));
        if(assignments == null)
            return null;
        if(assignments.size() == 0)
            return mergedAss;
        if(assignments.size() == 1) {
            return mergedAss.combine(assignments.get(0).iterator().next());
        }

        Set<Assignment> firstSet = assignments.get(0);
//        System.out.println("examining set " + firstSet);
        for(Assignment a: firstSet) {
//            System.out.println("trying assignment " + a);
            Assignment mergedAss2 = merge(assignments.subList(1, assignments.size()), a);
            if(mergedAss2 != null)
                return mergedAss.combine(mergedAss2);
        }

        return null;
    }

    private List<Set<Assignment>> filter(List<Set<Assignment>> assignments, Assignment ass) {
//        System.out.println("filter with ass = " + ass);
//        assignments.forEach(a -> System.out.print(a.size() + " "));
//        System.out.println();
        if(assignments.size() == 0)
            return assignments;
        assignments = assignments.parallelStream()
                                 .map(item -> item.stream().filter(a -> a.isCompatible(ass)).collect(Collectors.toSet()))
                                 .sorted(Comparator.comparingInt(Set::size))
                                 .collect(Collectors.toList());
//        assignments.forEach(a -> System.out.print(a.size() + " "));
//        System.out.println();
//        System.out.println("*********");

        if(assignments.get(0).size() == 0)
            return null;

        int i = 0;
        try {
            while (i < assignments.size() && assignments.get(i).size() == 1) {
                ass.add(assignments.get(i).iterator().next());
                ++i;
            }

            if (i > 0 && ass != null) {
                assignments = assignments.subList(i, assignments.size());
                return filter(assignments, ass);
            } else {
                return assignments;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
