package com.merger;

import com.merger.control.FileHandler;
import com.merger.control.MergeSortingArray;

public class MergerApplication {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        MergeSortingArray mergeSortingArray = new MergeSortingArray();

        String[] files = {"in1.txt", "in2.txt"};
        int[] arrays = fileHandler.toArrayDigits(files);

        mergeSortingArray.mergeSortDigits(arrays, arrays.length);

        for (int digit : arrays) {
            System.out.println(digit);
        }
    }
}
