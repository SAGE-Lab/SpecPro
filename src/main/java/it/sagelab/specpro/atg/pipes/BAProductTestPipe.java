package it.sagelab.specpro.atg.pipes;

import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;
import java.util.stream.Collectors;

public class BAProductTestPipe implements TestPipe {

    private final List<BuchiAutomaton> buchiAutomataList;
    private List<Assignment> test;

    public BAProductTestPipe(List<BuchiAutomaton> buchiAutomataList) {
        this.buchiAutomataList = buchiAutomataList;
    }

    @Override
    public List<Assignment> process(List<Assignment> test) {
        if(test == null) {
            return null;
        }
        if(buchiAutomataList == null || buchiAutomataList.size() == 0) {
            return test;
        }

        this.test = test;

        List<Trie<Edge>> inducedPaths = new ArrayList<>();
        List<List<Edge>> prefixes = new ArrayList<>();

        BAExplorer explorer = new BAExplorer();
        explorer.setLength(test.size());
        explorer.addAcceptanceCondition(new LassoShapedAcceptanceCondition());

        for(BuchiAutomaton ba: buchiAutomataList) {
            Trie<Edge> edgeInducedPaths = explorer.findInducedPaths(ba, test);
            if(edgeInducedPaths.size() == 0) {
                return null;
            }
            inducedPaths.add(edgeInducedPaths);
            prefixes.add(new ArrayList<>());
        }

        Deque<Assignment> composedTest = findCompatibleEdges(inducedPaths, prefixes, test.size());

        if(composedTest != null) {
            ArrayList<Assignment> assignments = new ArrayList<>();

            while (!composedTest.isEmpty()) {
                assignments.add(composedTest.pop());
            }

            return assignments;
        }

        return null;
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
        if(assignments == null)
            return null;
        if(assignments.size() == 0)
            return mergedAss;
        if(assignments.size() == 1) {
            return mergedAss.combine(assignments.get(0).iterator().next());
        }

        Set<Assignment> firstSet = assignments.get(0);
        for(Assignment a: firstSet) {
            Assignment mergedAss2 = merge(assignments.subList(1, assignments.size()), a);
            if(mergedAss2 != null)
                return mergedAss.combine(mergedAss2);
        }

        return null;
    }

    private List<Set<Assignment>> filter(List<Set<Assignment>> assignments, Assignment ass) {
        if(assignments.size() == 0)
            return assignments;
        assignments = assignments.parallelStream()
                .map(item -> item.stream().filter(a -> a.isCompatible(ass)).collect(Collectors.toSet()))
                .sorted(Comparator.comparingInt(Set::size))
                .collect(Collectors.toList());

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
