package com.merger;

import com.merger.control.Command;

public class MergeSorting {
    public int[] mergeSortDigits(int[] source, int minSize, int maxSize, Command command) {
        if (minSize == maxSize) {
            return new int[]{source[minSize]};
        }
        int mid = minSize + (maxSize - minSize) / 2;
        int[] lHalf = mergeSortDigits(source, minSize, mid, command);
        int[] rHalf = mergeSortDigits(source, mid + 1, maxSize, command);

        return mergeDigits(lHalf, rHalf, command);
    }

    private int[] mergeDigits(int[] lHalf, int[] rHalf, Command command) {
        int lSize = lHalf.length;
        int rSize = rHalf.length;
        int[] result = new int[lSize + rSize];

        int i = 0;
        int j = 0;
        int k = 0;

        if (command == Command.ASCENDING) {
            while (j < lSize && k < rSize) {
                if (lHalf[j] <= rHalf[k]) {
                    result[i++] = lHalf[j++];
                } else {
                    result[i++] = rHalf[k++];
                }
            }
        } else if (command == Command.DESCENDING) {
            while (j < lSize && k < rSize) {
                if (lHalf[j] >= rHalf[k]) {
                    result[i++] = lHalf[j++];
                } else {
                    result[i++] = rHalf[k++];
                }
            }
        }

        while (j < lSize) {
            result[i++] = lHalf[j++];
        }
        while (k < rSize) {
            result[i++] = rHalf[k++];
        }
        return result;
    }

    public String[] mergeSortStrings(String[] source, int minSize, int maxSize, Command command) {
        if(minSize == maxSize) {
            return new String[]{source[minSize]};
        }
        int mid = minSize + (maxSize - minSize) / 2;
        String[] lHalf = mergeSortStrings(source, minSize, mid, command);
        String[] rHalf = mergeSortStrings(source, mid + 1, maxSize, command);

        return mergeString(lHalf, rHalf, command);
    }

    private String[] mergeString(String[] lHalf, String[] rHalf, Command command) {
        int lSize = lHalf.length;
        int rSize = rHalf.length;
        String[] result = new String[lSize + rSize];

        int i = 0;
        int j = 0;
        int k = 0;

        if (command == Command.ASCENDING) {
            while (j < lSize && k < rSize) {
                if (isAlphabeticallySmaller(lHalf[j], rHalf[k])) {
                    result[i++] = lHalf[j++];
                } else {
                    result[i++] = rHalf[k++];
                }
            }
        } else if (command == Command.DESCENDING) {
            while (j < lSize && k < rSize) {
                if (isAlphabeticallySmaller(rHalf[k], lHalf[j])) {
                    result[i++] = lHalf[j++];
                } else {
                    result[i++] = rHalf[k++];
                }
            }
        }

        while (j < lSize) {
            result[i++] = lHalf[j++];
        }
        while (k < rSize) {
            result[i++] = rHalf[k++];
        }
        return result;
    }

    private boolean isAlphabeticallySmaller(String str, String compared) {
        str = str.toUpperCase();
        compared = compared.toUpperCase();

        return str.compareTo(compared) < 0;
    }
}
