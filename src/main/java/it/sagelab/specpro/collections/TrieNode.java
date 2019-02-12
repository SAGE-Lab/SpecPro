package it.sagelab.specpro.collections;

import java.util.HashMap;
import java.util.Map;

class TrieNode <K> {
    K key;
    Map<K, TrieNode<K>> children;
    boolean isLeaf;

    public TrieNode(K key, boolean isLeaf) {
        this.key = key;
        this.isLeaf = isLeaf;
        children = new HashMap<>();
    }

    public TrieNode(K key) {
        this(key, false);
    }

    public TrieNode<K> add(K element, boolean isLeaf) {
        TrieNode<K> tn = children.get(element);
        if(tn == null) {
            tn = new TrieNode<>(element, isLeaf);
            children.put(element, tn);
        }
        tn.isLeaf |= isLeaf;
        return tn;
    }

    public TrieNode<K> get(K key) {
        return children.get(key);
    }

    public boolean contains(K key) {
        return children.containsKey(key);
    }
}
