package com.hyunsiks.hash;

import java.util.Random;

public class RandomProbing<K, V> {

    private int maxSize = 13;
    private int currentSize = 0;

    // 해시 테이블
    private K[] array = (K[]) new Object[maxSize];

    // key 관련 데이터 저장
    private V[] dataArray = (V[]) new Object[maxSize];

    // 해시코드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    // 삽입 연산
    private void put(K key, V data) {
        int initialPos = hash(key);
        int i = initialPos;

        Random random = new Random();
        random.setSeed(10);

        do {
            // 삽입 위치 발견
            if (array[i] == null) {
                array[i] = key;
                dataArray[i] = data;
                currentSize++;
                break;
            }

            if (array[i].equals(key)) {
                dataArray[i] = data;
                break;
            }

            i = (initialPos + random.nextInt(1000)) % maxSize;
        } while (currentSize < maxSize);

        System.out.println("해시 테이블");

        for (int k = 0; k < maxSize; k++) {
            System.out.printf("%8d", k);
        }

        System.out.println();

        for (int k = 0; k < maxSize; k++) {
            System.out.printf("%8s", array[k]);
        }

        System.out.println("\n");
    }

    // 탐색 연산
    public V get(K key) {
        Random random = new Random();

        // 삽입 때와 같은 시드 사용
        random.setSeed(10);

        // 초기 위치
        int initialPos = hash(key);
        int i = initialPos;
        while (array[i] != null) {
            if (array[i].equals(key)) {
                System.out.printf("%d의 data = %s\n", key, dataArray[i]);
                return dataArray[i];
            }
            i = (initialPos + random.nextInt(1000)) % maxSize;
        }

        // 탐색 실패
        return null;
    }

    public static void main(String[] args) {
        RandomProbing<Integer, String> randomProbing = new RandomProbing<>();
        randomProbing.put(25, "orange");
        randomProbing.put(37, "watermelon");
        randomProbing.put(18, "apple");
        randomProbing.put(55, "banana");
        randomProbing.put(22, "kiwi");
        randomProbing.put(35, "tomato");
        randomProbing.put(50, "melon");
        randomProbing.put(63, "pear");

        randomProbing.get(50);
        randomProbing.get(63);
    }
}
