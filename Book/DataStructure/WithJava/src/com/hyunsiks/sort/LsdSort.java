package com.hyunsiks.sort;

import java.util.Arrays;

public class LsdSort {
    private static void sort(int[] a) {
        int r = 10;
        int n = a.length;
        int[] t = new int[n];

        for (int k = 10; k <= 1000; k *= 10) {
            int[] startIndex = new int[r + 1];

            // 빈도수 계산
            for (int i = 0; i < n; i++) {

                // (a[i] % k) / (k / 10)은 a[i]의 각 자릿수를 추출
                startIndex[(a[i] % k) / (k / 10) + 1]++;
            }

            // 각 버킷 인덱스 값이 저장될 배열 t의 시작 인덱스 계산
            for (int i = 0; i < r; i++) {
                startIndex[i + 1] += startIndex[i];
            }

            // a[i]를 배열 t에 차례로 저장
            // startIndex 는 배열에 원소를 저장하는 시작위치를 의미
            // startIndex[1] = 3이면, 인덱스 3부터 1이 시작된다는 것을 의미
            for (int i = 0; i < n; i++) {
                t[startIndex[(a[i] % k) / (k / 10)]++] = a[i];
            }

            // 배열 t를 배열 a로 복사
            System.arraycopy(t, 0, a, 0, n);

            System.out.println("\n" + k / 10 + "의 자리 정렬 결과 : ");

            for (int i = 0; i < n; i++) {
                System.out.println(String.format("%03d", a[i]) + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] a = {251, 430, 301, 540, 551, 401, 2, 10, 124, 22, 204, 115};
        sort(a);
    }
}
