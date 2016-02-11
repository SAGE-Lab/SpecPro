package it.sagelab.specpro.collections;

import java.util.Iterator;
import java.util.List;

public class SequenceBuilder<K> implements Iterable<List<K>> {

    private final List<? extends Iterable<K>> sequence;

    public SequenceBuilder(List<? extends Iterable<K>> sequence) {
        this.sequence = sequence;
    }

    @Override
    public Iterator<List<K>> iterator() {
        return new SequenceBuilderIterator<>(sequence);
    }
}
