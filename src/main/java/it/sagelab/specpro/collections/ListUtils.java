package it.sagelab.specpro.collections;

import java.util.Collections;
import java.util.List;

public class ListUtils {


    public static<E> List<E>[] randomSplit(List<E> list, int n) {
        Collections.shuffle(list);
        int m = (int) Math.round((double)list.size() / n);
        List<E>[] lists = new List[n];
        for(int i = 0; i < n; ++i) {
            int end = i == n - 1 ? list.size() : (i + 1) * m;
            List<E> l = list.subList(i * m, + end);
            lists[i] = l;
        }

        return lists;
    }
}
