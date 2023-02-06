package com.merger.control;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortingArrayTest {
    private final MergeSortingArray mergeSorter = new MergeSortingArray();

    @Test
    public void shouldReturnSortedArrayDigits() {
        int[] actual = {3, 5, 2, 16, 18, 10, 15, 25};
        int[] expected = {2, 3, 5, 10, 15, 16, 18, 25};

        mergeSorter.mergeSortDigits(actual, actual.length);
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldReturnSorterArrayStrings() {
        String[] actual = {"I", "love", "Java", "&", "Spring", "!"};
        String[] expected = {"!", "&", "I", "Java", "love", "Spring"};

        actual = mergeSorter.mergeSortStrings(actual, 0, actual.length - 1);
        Assertions.assertArrayEquals(actual, expected);
    }
}
