package com.merger.handler;

public class ArrayHandler {
    public static int[] mergeArraysDigits(int[][] arrays, int totalDigits) {
        int[] arrayDigits = new int[totalDigits];
        int idx = 0;

        for (int[] array : arrays) {
            for (int digit : array) {
                arrayDigits[idx++] = digit;
            }
        }
        return arrayDigits;
    }

    public static String[] mergeArraysStrings(String[][] arrays, int totalWords) {
        String[] arrayWords = new String[totalWords];
        int idx = 0;

        for (String[] array : arrays) {
            for (String word : array) {
                arrayWords[idx++] = word;
            }
        }
        return arrayWords;
    }

    public static int[] clearAscArrayFromErrors(int[] array) {
        int errorAmount = 0;
        for (int idx = array.length - 1; idx > 0; idx--) {
            if (999999999 == array[idx]) {
                errorAmount++;
            } else {
                break;
            }
        }
        int[] result = new int[array.length - errorAmount];
        System.arraycopy(array, 0, result, 0, result.length);

        return result;
    }

    public static int[] clearDescArrayFromErrors(int[] array) {
        int errorAmount = 0;
        for (int number : array) {
            if (999999999 == number) {
                errorAmount++;
            } else {
                break;
            }
        }
        int[] result = new int[array.length - errorAmount];
        System.arraycopy(array, errorAmount, result, 0, result.length);

        return result;
    }

    public static String[] clearAscArrayFromErrors(String[] array) {
        int errorAmount = 0;
        for (int idx = array.length - 1; idx > 0; idx--) {
            if ("⌂".equals(array[idx])) {
                errorAmount++;
            } else {
                break;
            }
        }
        String[] result = new String[array.length - errorAmount];
        System.arraycopy(array, 0, result, 0, result.length);

        return result;
    }

    public static String[] clearDescArrayFromErrors(String[] array) {
        int errorAmount = 0;
        for (String word : array) {
            if ("⌂".equals(word)) {
                errorAmount++;
            } else {
                break;
            }
        }
        String[] result = new String[array.length - errorAmount];
        System.arraycopy(array, errorAmount, result, 0, result.length);

        return result;
    }
}
