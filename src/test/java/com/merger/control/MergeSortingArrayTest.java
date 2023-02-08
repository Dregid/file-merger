package com.merger.control;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MergeSortingArrayTest {
    @Mock
    private MergeSortingArray mergeSorter;

    @Test
    public void shouldReturnSortedArrayDigits() {
        int[] actual = {3, 5, 2, 16, 18, 10, 15, 25};
        int[] expected = {2, 3, 5, 10, 15, 16, 18, 25};

        Mockito.when(mergeSorter.mergeSortDigits(actual, 0, actual.length - 1))
                .thenReturn(expected);
        actual = mergeSorter.mergeSortDigits(actual, 0, actual.length - 1);

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldReturnSorterArrayStrings() {
        String[] actual = {"I", "Love", "Java", "&", "Spring", "!"};
        String[] expected = {"!", "&", "I", "Java", "Love", "Spring"};

        Mockito.when(mergeSorter.mergeSortStrings(actual, 0, actual.length - 1))
                .thenReturn(expected);
        actual = mergeSorter.mergeSortStrings(actual, 0, actual.length - 1);

        Assertions.assertArrayEquals(actual, expected);
    }
}
