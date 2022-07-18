package com.kenzie.countdown;

import java.util.List;

public class RecursiveMagicIndex {

    /**
     * This recursive method accepts a sorted list of distinct values
     * and returns a magic index if one exists. A magic index is a
     * an index in a list that has the same value as the index.
     * @param list - this is the sorted list to search through for a magic index
     * @return - this is the magic index, (-1) will be returned if one cannot be found
     */
    public static int magicIndex(List<Integer> list) {
       return binarySearch(list, 0, list.size() - 1);
    }

    private static int binarySearch(List<Integer> list, int start, int end) {

        if (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) == mid) {
                return mid;
            }

            if (list.get(mid) < mid) {
                return binarySearch(list, mid + 1, end);
            } else {
                return binarySearch(list, start, mid - 1);
            }
        }
        return -1;
    }
}
