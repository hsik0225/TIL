package com.hyunsiks.sort;

import java.util.Arrays;

public class Shell {
    public static void sort(Comparable[] a) {
        int n = a.length;

        // Knuth 간격 (3^k-1)/2 : 1, 4, 13. 40, ... 중에서 4와 1민 사용
        int h = 4;
        while (h >= 1) {

            // h-정렬 수행
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && isleess(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
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

    public static void main(String[] args) {
        Comparable[] a = new Comparable[12];
        a[0] = 65;
        a[1] = 95;
        a[2] = 90;
        a[3] = 80;
        a[4] = 55;
        a[5] = 70;
        a[6] = 35;
        a[7] = 50;
        a[8] = 10;
        a[9] = 25;
        a[10] = 40;
        a[11] = 30;

        sort(a);

        System.out.println(Arrays.toString(a));
    }
}
