package com.hyunsiks.sort;

public class Selection {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (isleess(a[j], a[min]))
                    min = j;
            }
            swap(a, i, min);
        }
    }

    // 원소 교환
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 키 비교
    private static boolean isleess(Comparable i, Comparable j) {
        return (i.compareTo(j) < 0);
    }
}
