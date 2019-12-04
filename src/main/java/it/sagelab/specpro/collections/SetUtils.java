package it.sagelab.specpro.collections;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class SetUtils {

    private static Random random = new Random();

    public static <E> E pickRandom(Set<E> edgeSet) {

        if(edgeSet.isEmpty()) {
            return null;
        }

        int n = random.nextInt(edgeSet.size());
        Iterator<E> itr = edgeSet.iterator();

        while(--n >= 0) {
            itr.next();
        }

        return itr.next();
    }
}
