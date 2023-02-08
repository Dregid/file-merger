package com.merger.control;

import com.merger.handler.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FileHandlerTest {
    @Mock
    private FileHandler fileHandler;

    @Test
    public void shouldReturnArrayDigitsFromFile() {
        String[] files = {"in1.txt", "in2.txt", "in3.txt"};
        int[] desiredArray = new int[]{2,6,12,13,9,15,33,35,8,11,21,23};

        Mockito.when(fileHandler.readToArrayDigits(files))
                .thenReturn(desiredArray);
        int[] result = fileHandler.readToArrayDigits(files);

        Assertions.assertArrayEquals(result, desiredArray);
    }

    @Test
    public void shouldReturnArraysStrings() {
        String[] file = {"in4.txt"};
        String[] desiredArray = {"I", "Love", "Java", "&", "Spring", "!"};

        Mockito.when(fileHandler.readToArrayStrings(file))
                .thenReturn(desiredArray);
        String[] result = fileHandler.readToArrayStrings(file);

        Assertions.assertArrayEquals(result, desiredArray);
    }
}
