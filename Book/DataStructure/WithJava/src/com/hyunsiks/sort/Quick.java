package com.hyunsiks.sort;

import java.util.Arrays;

public class Quick {
    public static void sort(Comparable[] a)  {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low)
            return;

        int j = partition(a, low, high);

        // 피벗보다 작은 부분을 재귀호출
        sort(a, low, j - 1);

        // 피벗보다 큰 부분을 재귀호출
        sort(a, j + 1, high);
    }

    private static int partition(Comparable[] a, int pivot, int high) {
        int i = pivot;
        int j = high + 1;
        Comparable p = a[pivot];
        while (true) {

            // 피벗보다 작으면
            while (isless(a[++i], p))
                if (i == high)
                    break;

            // 피벗보다 크면
            while (isless(p, a[--j]))
                if (j == pivot)
                    break;

            // i와 j가 교차되면 루프 나가기
            if (i >= j)
                break;

            swap(a, i, j);
        }

        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));

        // 피벗과 a[j] 교환
        swap(a, pivot, j);

        // a[j] 피벗이 영원히 자리 잡은 곳
        return j;
    }

    private static boolean isless(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Comparable[] a = new Comparable[12];
        a[0] = 10;
        a[1] = 15;
        a[2] = 20;
        a[3] = 25;
        a[4] = 30;
        a[5] = 35;
        a[6] = 40;
        a[7] = 45;
        a[8] = 50;
        a[9] = 55;
        a[10] = 60;
        a[11] = 65;

        sort(a);

        System.out.println(Arrays.toString(a));
    }
}
