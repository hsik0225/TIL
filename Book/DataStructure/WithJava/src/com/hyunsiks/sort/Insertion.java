package com.hyunsiks.sort;

public class Insertion {
    public static void sort(Comparable[] a) {
        int n = a.length;

        // i 는 현재 원소의 인덱스
        for (int i = 1; i < n; i++) {

            // 현재 원소를 정렬된 앞부분에 삽입
            // 정렬될 때까지 1칸씩 움직이며 정렬한다
            for (int j = i; j > 0; j--) {
                if (isless(a[j], a[j-1]))
                    swap(a, j, j-1);
                else
                    break;
            }
        }
    }

    private static void swap(Comparable[] a, int j, int i) {
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    private static boolean isless(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
