package com.merger.control;

public class MergeSortingArray {
    public void mergeSort(int[] source, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[size - mid];

        for (int idx = 0; idx < mid; idx++) {
            leftHalf[idx] = source[idx];
        }
        for (int idx = mid; idx < size; idx++) {
            rightHalf[idx - mid] = source[idx];
        }
        mergeSort(leftHalf, mid);
        mergeSort(rightHalf, size - mid);

        merge(source, leftHalf, rightHalf,
                mid, size - mid);
    }

    public void merge(int[] result, int[] leftHalf, int[] rightHalf,
                      int sizeLeft, int sizeRight) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < sizeLeft && j < sizeRight) {
            if (leftHalf[i] <= rightHalf[j]) {
                result[k++] = leftHalf[i++];
            } else {
                result[k++] = rightHalf[j++];
            }
        }

        while (i < sizeLeft) {
            result[k++] = leftHalf[i++];
        }
        while (j < sizeRight) {
            result[k++] = rightHalf[j++];
        }
    }
}
