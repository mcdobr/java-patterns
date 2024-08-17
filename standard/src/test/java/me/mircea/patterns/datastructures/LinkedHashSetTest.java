package me.mircea.patterns.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedHashSetTest {
    @Test
    void shouldRemoveDuplicatesAndKeepOrder() {
        int[] arr = {2, 3, 2, 5, 6, 7, 7, 7, 8, 7, 9};

        List<Integer> list = Arrays.stream(arr)
                .boxed()
                .toList();

        HashSet<Integer> integers = new LinkedHashSet<>(list);

        assertThat(integers).containsExactlyInAnyOrder(2, 3, 5, 6, 7, 8, 9);
    }
}
