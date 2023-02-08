package com.merger;

import com.merger.control.FileHandler;

public class MergerApplication {
    public static void main(String[] args) {
        String[] files = new String[]{"in1.txt", "in2.txt", "in3.txt", "in4.txt"};
        FileHandler fileHandler = new FileHandler();
        int[] result = fileHandler.toArrayDigits(files);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
