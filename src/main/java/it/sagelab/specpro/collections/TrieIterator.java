package it.sagelab.specpro.collections;

import java.util.*;

public class TrieIterator <K> implements Iterator<List<K>> {

    Trie<K> trie;
    LinkedList<K> list;
    Deque<Iterator<Map.Entry<K, TrieNode<K>>>> stack;

    public TrieIterator(Trie<K> trie) {
        stack = new ArrayDeque<>();
        stack.push(trie.root.children.entrySet().iterator());
        list = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().hasNext()) {
            stack.pop();
            if(!list.isEmpty())
                list.removeLast();
        }
        return !stack.isEmpty();
    }

    @Override
    public List<K> next() {
        boolean pathFound = false;
        while(!pathFound) {
            Map.Entry<K, TrieNode<K>> entry = stack.peek().next();
            list.add(entry.getKey());
            stack.push(entry.getValue().children.entrySet().iterator());
            if(entry.getValue().isLeaf)
                pathFound = true;
        }

        return new ArrayList<>(list);
    }
}
