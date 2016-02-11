package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.IOException;
import java.util.*;

public class PathsMultiplierTestGenerator extends RequirementsTestGenerator {

    private ArrayList<Iterator<List<Assignment>>> iterators = new ArrayList<>();
    private Trie<Assignment> visitedPaths = new Trie<>();


    public PathsMultiplierTestGenerator(String filePath) throws IOException {
        parseRequirements(filePath);
    }

    public PathsMultiplierTestGenerator() { }

    @Override
    protected void generate(Trie<Assignment> completePaths) {

        for(Trie<Assignment> trie: singlePaths) {
            iterators.add(trie.iterator());
        }

        filter();

        for(int i = 0; i < singlePaths.size(); ++i) {
            System.out.println("Evaluating req " + (i + 1) + "/" + singlePaths.size());
            Trie<Assignment> trie = singlePaths.get(i);
            List<List<Assignment>> pathsToRemove = new ArrayList<>();
            int n = 1;
            for(List<Assignment> currentPath: trie) {
                System.out.println("\tpath " + (n++) + "/" + trie.size());
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
                    System.out.println("EdgeSequence not valid: " + currentPath);
                    pathsToRemove.add(currentPath);
                }
            }

            for(List<Assignment> path: pathsToRemove) {
                trie.remove(path);
            }
            iterators.set(i, trie.iterator());

        }
    }



    private void filter() {
        for(int i = 0; i < singlePaths.size(); ++i) {
            System.out.println("Filtering paths " + (i + 1) + "/" + singlePaths.size());
            Trie<Assignment> trie = singlePaths.get(i);
            Trie<Assignment> filteredPaths = new Trie<>();
            System.out.println("Initial paths: " + trie.size());
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
                    if (compatiblePaths.size() == 0) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    filteredPaths.insert(currentPath);
                } else {
                    System.out.println("EdgeSequence filtered out: " + currentPath);
                }
            }
            System.out.println("Filtered paths: " + filteredPaths.size());
            if(filteredPaths.size() > 0) {
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

        if(index == nReq)
            return findValidMul(currentPath, nReq, index + 1, visited);
        if(index >= singlePaths.size())
            return currentPath;
        Trie<Assignment> trie = singlePaths.get(index);

        Iterator<List<Assignment>> iterator = iterators.get(index);
        for(int i = 0; i < trie.size(); ++i) {
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

}
