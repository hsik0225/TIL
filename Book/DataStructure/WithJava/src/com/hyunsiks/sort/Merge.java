package com.hyunsiks.sort;

public class Merge {
    private static void merge(Comparable[] a, Comparable[] b, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {

            // 앞부분이 먼저 소진된 경우
            if (i > mid)
                b[k] = a[j++];

            // 뒷부분이 먼저 소진된 경우
            else if (j > high)
                b[k] = a[i++];

            // a[j] 가 승자
            else if (isless(a[j], a[i]))
                b[k] = a[j++];

            else
                b[k] = a[i++];
        }

        // 보조배열 b를 배열 a에 복사
        for (int k = low; k <= high; k++)
            a[k] = b[k];
    }

    private static void sort(Comparable[] a, Comparable[] b, int low, int high) {
        if (high <= low)
            return;

        int mid = low + (high - low) / 2;

        // 앞부분 재귀호출
        sort(a, b, low, mid);

        // 뒷부분 재귀호출
        sort(a, b, mid + 1, high);

        // 합병 수행
        merge(a, b, low, mid, high);
    }

    public static void sort(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        sort(a, b, 0, a.length-1);
    }

    private static boolean isless(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
