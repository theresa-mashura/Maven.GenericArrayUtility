package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E extends Comparable<E>> {

    private E[] inputArray;
    private E[] mergedArray;


    public ArrayUtility(E[] inputArray) {
        this.inputArray = inputArray;
        this.mergedArray = inputArray;

    }

    public E[] mergeArrays(E[] arrayToMerge) {
        int l = arrayToMerge.length + inputArray.length;
        E[] newArr = (E[]) new Object[l];

        for (int i = 0, j = 0; i < l; i++) {
            if (i < inputArray.length) {
                newArr[i] = inputArray[i];
            } else if (i >= inputArray.length) {
                newArr[i] = arrayToMerge[j];
                j++;
            }
        }
        this.mergedArray = newArr;
        return newArr;
    }

    public void sortArray() {
        E temp;
        for (int i = 0; i < mergedArray.length; i++) {
            for (int j = 1; j < mergedArray.length - 1; j++) {
                if(mergedArray[j-1].compareTo(mergedArray[j]) > 0) {
                    temp = mergedArray[j-1];
                    mergedArray[j-1] = mergedArray[j];
                    mergedArray[j] = temp;
                }
            }
        }
    }

    public int countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        mergeArrays(arrayToMerge);

        int count = 0;
        for (int i = 0; i < mergedArray.length; i++) {
            if (mergedArray[i].equals(valueToEvaluate)) {
                count++;
            }
        }

        return count;
    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        mergeArrays(arrayToMerge);
        this.sortArray();

        int count = 1;
        int mostCommonCount = 0;
        E mostCommonElement = mergedArray[0];

        for (int i = 1; i < mergedArray.length; i++) {
            if (mergedArray[i] == mergedArray [i-1]) {
                count++;
            } else {
                if (count > mostCommonCount) {
                    mostCommonCount = count;
                    mostCommonElement = mergedArray[i-1];
                }
                count = 1;
            }
        }
        return mostCommonElement;
    }

    public int getNumberOfOccurrences(E valueToEvaluate) {

        int count = 0;
        for (int i = 0; i < mergedArray.length; i++) {
            if (mergedArray[i].equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }

    public E[] removeValue(E valueToRemove) {
        int l = mergedArray.length - this.getNumberOfOccurrences(valueToRemove);
        E[] arr = (E[]) new Object[l];

        for (int i = 0, j = 0; i < mergedArray.length; i++) {
            if (!mergedArray[i].equals(valueToRemove)) {
                arr[j] = mergedArray[i];
                j++;
            }
        }

        this.mergedArray = arr;
        return mergedArray;
    }

}
