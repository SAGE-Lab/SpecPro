package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.Path;

import java.util.*;

public class PathsBuilder {

    private final HashMap<String, Atom> atoms;
    private final HashMap<Edge, List<Assignment>> edgesCache;
    private Trie<Assignment> trie;
    private ArrayList<List<Assignment>> currentPathAssignments;
    private boolean allCombinations;


    public PathsBuilder() {
        atoms = new HashMap<>();
        edgesCache = new HashMap<>();
        trie = new Trie<>();
        currentPathAssignments = new ArrayList<>();
        allCombinations = false;
    }

    public boolean isAllCombinations() {
        return allCombinations;
    }

    public void setAllCombinations(boolean allCombinations) {
        this.allCombinations = allCombinations;
    }

    public void reset() {
        trie = new Trie<>();
    }

    public List<Assignment> getEdgeAssignments(Edge e) {
        edgesCache.computeIfAbsent(e, edge -> generateAssignments(edge.getLabel()));
        return edgesCache.get(e);
    }

    public void addEdge(Edge e) {
        edgesCache.computeIfAbsent(e, edge -> generateAssignments(edge.getLabel()));
        List<Assignment> assignments = edgesCache.get(e);
        currentPathAssignments.add(assignments);
    }

    public void generateAllConditionsPaths() {
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(new Path());
        //generateAllConditionsPaths(paths, 0);
        generateAllCombinationsPaths(paths, 0);


        for(Path p: paths) {
            trie.insert(p.get());
        }
        currentPathAssignments = new ArrayList<>();
    }

    public Trie<Assignment> getTrie() {
        return trie;
    }

    /***************************************************
     *
     * Private Methods
     *
     ***************************************************/

    private void generateAllCombinationsPaths(ArrayList<Path> paths, int index) {
        if(index >= currentPathAssignments.size())
            return;
        List<Assignment> assignments = currentPathAssignments.get(index);

        if(assignments != null && assignments.size() > 0) {
            ArrayList<Path> newPaths = new ArrayList<>();

            for (int i = 1; i < assignments.size(); ++i) {
                for (int j = 0; j < paths.size(); ++j) {
                    Path p = paths.get(j).clone();
                    p.append(assignments.get(i));
                    newPaths.add(p);
                }
            }

            for (int j = 0; j < paths.size(); ++j) {
                paths.get(j).append(assignments.get(0));
            }

            paths.addAll(newPaths);
        }
        generateAllCombinationsPaths(paths, index + 1);

    }

    private void generateAllConditionsPaths(ArrayList<Path> paths, int index) {
        if(index >= currentPathAssignments.size())
            return;
        List<Assignment> assignments = currentPathAssignments.get(index);
        Random rand = new Random(System.currentTimeMillis());
        while(assignments.size() > paths.size()) {
            Collections.shuffle(paths, rand);
            paths.add(paths.get(0).clone());
        }

        for(int i = 0; i < paths.size() && assignments.size() > 0; ++i) {
            if(assignments.size() > i) {
                paths.get(i).append(assignments.get(i));
            } else {
                paths.get(i).append(assignments.get(rand.nextInt(assignments.size())));
            }
        }

        generateAllConditionsPaths(paths, index + 1);
    }

    private List<Assignment> generateAssignments(String formula) {
        if(formula == null)
            return Collections.emptyList();
        ArrayList<Assignment> assignments = new ArrayList<>();
        Assignment ass = new Assignment();
        assignments.add(ass);
        int index = 0;
        while(index < formula.length()) {
            switch (formula.charAt(index)) {
                case ' ':
                case '(':
                case ')':
                case '&':
                    ++index;
                    break;
                case '!':
                    index = parseAtom(formula, index + 1, ass, false);
                    break;
                case '|':
                    ass = new Assignment();
                    assignments.add(ass);
                    ++index;
                    break;
                default:
                    if(formula.charAt(index) == '_' || Character.isLetterOrDigit(formula.charAt(index))) {
                        index = parseAtom(formula, index, ass, true);
                    } else {
                        throw new RuntimeException("Formula \"" + formula + "\" non recognized at index " + index);
                    }
            }
        }

        return assignments;
    }

    private int parseAtom(String formula, int index, Assignment a, boolean value) {
        StringBuilder builder = new StringBuilder();
        while(index < formula.length() &&
                (formula.charAt(index) == '_' ||
                        Character.isLetterOrDigit(formula.charAt(index)))) {
            builder.append(formula.charAt(index++));
        }
        String name = builder.toString();
        atoms.computeIfAbsent(name, n -> new Atom(n));
        Atom atom = atoms.get(name);
        if(!atom.getName().equals("1"))
            a.add(atom, value);
        return index;
    }


}
