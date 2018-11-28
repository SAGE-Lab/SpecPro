package it.sagelab.specpro.collections;

import java.util.*;

public class ListUtils {


    public static<E> List<E>[] randomSplit(List<E> list, int n) {
        Collections.shuffle(list);
        return split(list, n);
    }

    public static<E> List<E>[] split(List<E> list, int n) {
        int m = (int) Math.round((double)list.size() / n);
        List<E>[] lists = new List[n];
        for(int i = 0; i < n; ++i) {
            int end = i == n - 1 ? list.size() : (i + 1) * m;
            List<E> l = list.subList(i * m, + end);
            lists[i] = l;
        }

        return lists;
    }

    public static<E> Set<E> toSet(List<E> list) {
        return new HashSet<>(list);
    }

}
