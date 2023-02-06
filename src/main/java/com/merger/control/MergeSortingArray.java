package com.merger.control;

public class MergeSortingArray {
    public void mergeSortDigits(int[] source, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int[] lHalf = new int[mid];
        int[] rHalf = new int[size - mid];

        for (int idx = 0; idx < mid; idx++) {
            lHalf[idx] = source[idx];
        }
        for (int idx = mid; idx < size; idx++) {
            rHalf[idx - mid] = source[idx];
        }
        mergeSortDigits(lHalf, mid);
        mergeSortDigits(rHalf, size - mid);

        mergeDigits(source, lHalf, rHalf,
                mid, size - mid);
    }

    private void mergeDigits(int[] source, int[] leftHalf, int[] rightHalf,
                             int lSize, int rSize) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < lSize && j < rSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                source[k++] = leftHalf[i++];
            } else {
                source[k++] = rightHalf[j++];
            }
        }

        while (i < lSize) {
            source[k++] = leftHalf[i++];
        }
        while (j < rSize) {
            source[k++] = rightHalf[j++];
        }
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
