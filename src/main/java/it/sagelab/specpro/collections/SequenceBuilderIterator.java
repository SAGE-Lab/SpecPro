package it.sagelab.specpro.collections;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SequenceBuilderIterator<K> implements Iterator<List<K>> {

    private final List<? extends Iterable<K>> sequence;

    private final List<Iterator<K>> iterators;
    private final ArrayList<K> list;

    public SequenceBuilderIterator(List<? extends Iterable<K>> sequence) {
        this.sequence = sequence;
        iterators = new ArrayList<>();
        list = new ArrayList<>();
        for(Iterable<K> i: sequence) {
            Iterator<K> itr = i.iterator();
            if(itr.hasNext()) {
                iterators.add(itr);
            } else {
                iterators.clear();
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        for(Iterator<K> itr: iterators) {
            if (itr.hasNext())
                return true;
        }
        return false;
    }

    @Override
    public List<K> next() {
        if(list.isEmpty()) {
            for(Iterator<K> itr: iterators) {
                list.add(itr.next());
            }
        } else {

            int n = iterators.size() - 1;
            while (!iterators.get(n).hasNext()) {
                iterators.set(n, sequence.get(n).iterator());
                --n;
            }

            for (int i = n; i < iterators.size(); ++i) {
                list.set(i, iterators.get(i).next());
            }
        }

        return new ArrayList<>(list);
    }
}
