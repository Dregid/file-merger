package com.merger.control;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortingArrayTest {
    private final MergeSortingArray mergeSorter = new MergeSortingArray();

    @Test
    public void shouldReturnSortedArray() {
        int[] actual = {3, 5, 2, 16, 18, 10, 15, 25};
        int[] expected = {2, 3, 5, 10, 15, 16, 18, 25};

        mergeSorter.mergeSort(actual, actual.length);
        Assertions.assertArrayEquals(actual, expected);
    }
}
