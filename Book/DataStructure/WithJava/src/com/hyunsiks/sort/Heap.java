package com.hyunsiks.sort;

public class Heap {
    public static void sort(Comparable[] a) {

        // a[0] 은 사용안함
       int heapSize = a.length-1;

       /// 힙 만들기
        for (int i = heapSize/2; i > 0; i--)
            downheap(a, i, heapSize);

        // 힙 정렬
        while (heapSize > 1) {

            // a[1]과 힙의 마지막 항목과 교환
            swap(a, 1, heapSize--);

            // 위배된 힙 속성 고치기
            downheap(a, 1, heapSize);
        }
    }

    private static void downheap(Comparable[] a, int p, int heapSize) {
        while (2*p <= heapSize) {

            // s는 왼쪽 자식의 인덱스
            int s = 2*p;

            // 오른쪽 자식이 큰 경우
            if (s < heapSize && isleess(a[s], a[s+1]))
                s++;

            // 힙속성 만족하는 경우
            if (!isleess(a[p], a[s]))
                break;

            // 힙속성 만족 안하면 부모(p)와 자식 승자(s) 교환
            swap(a, p, s);

            // 이제 자식 승자의 자리에 부모가 있게 됨
            p = s;
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
