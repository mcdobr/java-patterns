package me.mircea.webapp.patterns.functional;

import java.util.Collection;
import java.util.Iterator;

public class IterativeOperations {
    private IterativeOperations() {
    }

    public static int sumWithStreams(Collection<Integer> collection) {
        return collection.stream()
                .reduce(0, Integer::sum);
    }

    public static int sumWithClassicLoop(Collection<Integer> collection) {
        int sum = 0;
        Integer[] array = collection.toArray(new Integer[0]);
        for (int i = 0; i < collection.size(); ++i) {
            sum += array[i];
        }
        return sum;
    }

    public static int sumWithForEachLoop(Collection<Integer> collection) {
        int sum = 0;
        for (Integer integer : collection) {
            sum += integer;
        }
        return sum;
    }

    public static int sumWithIterators(Collection<Integer> collection) {
        int sum = 0;
        final Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }
}
