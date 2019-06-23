package it.sagelab.specpro.collections;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Implementation of a Trie data structure, not limited to character sequences.
 * This class can handle sequences of arbitrary elements K and retrieve them with an iterator.
 *
 * @param <K> The type of key used in the Trie
 * @author Simone Vuotto
 */
public class Trie <K> implements Iterable<List<K>> {

    TrieNode<K> root;
    private int nSequences = 0;

    public Trie() {
        root = new TrieNode<>(null, false);
    }

    /**
     * Insert a sequence of elements K in the trie.
     * @param path the list of elements to insert
     */
    public void insert(List<K> path) {

        TrieNode<K> t = root;

        for(K element: path) {
            t = t.add(element, false);
        }

        if(!t.isLeaf)
            ++nSequences;

        t.isLeaf = true;
    }

    /**
     * Insert a sequence of elements K in the trie, using the comparator to compare the keys.
     * @param path the list of elements to insert
     * @param comparator the comparator to use to compare the keys
     */
    public void insert(List<K> path, Comparator<K> comparator) {
        TrieNode<K> t = root;

        for(K element: path) {
            t = t.add(element, false, comparator);
        }

        if(!t.isLeaf)
            ++nSequences;

        t.isLeaf = true;
    }

    /**
     * Insert a sequence of elements K in the trie.
     * @param path the array of elements to insert
     */
    public void insert(K[] path) {

        TrieNode<K> t = root;

        for(K element: path) {
            t = t.add(element, false);
        }

        if(!t.isLeaf)
            ++nSequences;

        t.isLeaf = true;
    }

    /**
     * Remove all partial utils that are contained in longer one. For example, if "a-a-b" and "a-a" are inserted, the
     * second one will be discarted.
     */
    public void removePartialPaths() {
        Stack<TrieNode<K>> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TrieNode<K> t = stack.pop();
            if(t.children.size() > 0) {
                if(t.isLeaf) {
                    t.isLeaf = false;
                    --nSequences;
                }
                stack.addAll(t.children.values());
            }
        }
    }

    /**
     * Check if the trie contains the sequence of elements specified in input.
     * This method only checks if the elements exist, not if the sequence is complete.
     * @see #containsSequence(List) to check if the sequence is complete
     * @param path the sequence to check
     */
    public boolean contains(List<K> path) {
        return find(path) != null;
    }

    /**
     * Returns all successors of the sequence given in input.
     *
     * @param path the sequence to check
     * @return the set of successors, or null if the sequence is not part of the Trie
     */
    public Set<K> getSuccessors(List<K> path) {
        TrieNode<K> t = find(path);
        if(t == null)
            return null;
        return t.children.keySet();
    }

    /**
     * Check if the trie contains the sequence specified in input.
     * This method checks that the sequence of elements exists and that the last one is marked as leaf,
     * i.e. it represent a full sequence of keys.
     * @see #contains(List)
     * @param path the sequence to check
     */
    public boolean containsSequence(List<K> path) {
        TrieNode<K> t = find(path);
        return t!= null && t.isLeaf;
    }


    public void remove(List<K> path) {
        TrieNode<K> t = find(path);
        if(t != null && t.isLeaf) {
            --nSequences;
            t.isLeaf = false;
            prune(root);
        }
    }

    /**
     *
     * @return the number of sequences in the Trie
     */
    public int size() {
        return nSequences;
    }


    @Override
    public Iterator<List<K>> iterator() {
        return new TrieIterator<>(this);
    }

    public Set<List<K>> toSet() {
        Set<List<K>> s = new HashSet<>();
        for(List<K> list: this)
            s.add(list);
        return s;
    }


    /**
     * Generates a new trie that is a combination of the current one and {@code t}, passed in input.
     * Two nodes are joined only if the new key produced by {@code joinFuncion} is not null.
     * If two nodes are joined, so will be their children, if any.
     * A node is considered a leaf only if both the joining nodes are marked as leaf.
     * At the end of the process, nodes with no children and marked as not leaf are removed.
     * @param t the trie to be joined
     * @param joinFunction the user specified function to merge two keys in a new one.
     *                     If the function returns null, no node is created from the given keys.
     * @return a new Trie object
     */
    public Trie<K> andJoin(Trie<K> t, BiFunction<K, K, K> joinFunction) {
        Trie<K> trie = new Trie<>();
        trie.nSequences = andJoin(trie.root, this.root, t.root, joinFunction);
        // After join some nodes may be redundant, they are removed with pruning
        prune(trie.root);
        return trie;
    }

    /**
     ********************************
     * Private Methods
     ********************************
     */

    private TrieNode<K> find(List<K> path) {
        TrieNode<K> t = root;

        for(K element: path) {
            t = t.get(element);
            if(t == null)
                return null;
        }

        return t;
    }

    private int andJoin(TrieNode<K> root, TrieNode<K> n1, TrieNode<K> n2, BiFunction<K, K, K> joinFunction) {

        if(n1 == null || n2 == null) {
            return 0;
        }

        int nSequences = 0;

        for(Map.Entry<K, TrieNode<K>> e1: n1.children.entrySet()) {
            for(Map.Entry<K, TrieNode<K>> e2: n2.children.entrySet()) {
                K newKey = joinFunction.apply(e1.getKey(), e2.getKey());
                if(newKey != null) {
                    TrieNode<K> newNode = root.add(newKey, e1.getValue().isLeaf && e2.getValue().isLeaf);
                    if(newNode.isLeaf)
                        ++nSequences;
                    nSequences += andJoin(newNode, e1.getValue(), e2.getValue(), joinFunction);
                }
            }
        }

        return nSequences;
    }

    private void prune(TrieNode<K> root) {
        ArrayList<K> nodesToRemove = new ArrayList<>();
        for(Map.Entry<K, TrieNode<K>> entry: root.children.entrySet()) {
            prune(entry.getValue());
            if(entry.getValue().children.isEmpty() && !entry.getValue().isLeaf)
                nodesToRemove.add(entry.getKey());
        }

        for(K key: nodesToRemove)
            root.children.remove(key);
    }

}
