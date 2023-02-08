package com.merger.control;

public class MergeSorting {
    public int[] mergeSortDigits(int[] source, int minSize, int maxSize) {
        if (minSize == maxSize) {
            return new int[]{source[minSize]};
        }
        int mid = minSize + (maxSize - minSize) / 2;
        int[] lHalf = mergeSortDigits(source, minSize, mid);
        int[] rHalf = mergeSortDigits(source, mid + 1, maxSize);

        return mergeDigits(lHalf, rHalf);
    }

    private int[] mergeDigits(int[] lHalf, int[] rHalf) {
        int lSize = lHalf.length;
        int rSize = rHalf.length;
        int[] result = new int[lSize + rSize];

        int i = 0;
        int j = 0;
        int k = 0;

        while (j < lSize && k < rSize) {
            if (lHalf[j] <= rHalf[k]) {
                result[i++] = lHalf[j++];
            } else {
                result[i++] = rHalf[k++];
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

    public String[] mergeSortStrings(String[] source, int minSize, int maxSize) {
        if(minSize == maxSize) {
            return new String[]{source[minSize]};
        }
        int mid = minSize + (maxSize - minSize) / 2;
        String[] lHalf = mergeSortStrings(source, minSize, mid);
        String[] rHalf = mergeSortStrings(source, mid + 1, maxSize);

        return mergeString(lHalf, rHalf);
    }

    private String[] mergeString(String[] lHalf, String[] rHalf) {
        int lSize = lHalf.length;
        int rSize = rHalf.length;
        String[] result = new String[lSize + rSize];

        int i = 0;
        int j = 0;
        int k = 0;

        while (j < lSize && k < rSize) {
            if (isAlphabeticallySmaller(lHalf[j], rHalf[k])) {
                result[i++] = lHalf[j++];
            } else {
                result[i++] = rHalf[k++];
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
