package com.hyunsiks.hash;

public class QuadProbing<K, V> {

    private int maxSize = 13;

    private int currentSize = 0;

    // 해시 테이블
    private K[] array = (K[]) new Object[maxSize];

    private V[] dataArray = (V[]) new Object[maxSize];

    // 삽입 연산
    private void put(K key, V data) {
        int initialPos = hash(key);
        int i = initialPos;
        int j = 1;

        do {
            // 삽입 위치 발견
            if (array[i] == null) {

                // key를 해시테이블에 저장
                array[i] = key;

                // key 관련 데이터 저장, 항목 수 1 증가
                dataArray[i] = data;

                currentSize++;

                break;
            }

            // 이미 키 존재
            if (array[i].equals(key)) {
                dataArray[i] = data;
            }

            i = (initialPos + j * j++) % maxSize;
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
        int initialPos = hash(key);
        int i = initialPos;
        int j = 1;

        // array[i]가 empty가 아니면
        while (array[i] != null) {

            // 탐색 성공
            if (array[i].equals(key)) {
                System.out.printf("%s의 data = %s\n", key, dataArray[i]);
                return dataArray[i];
            }

            i = (initialPos + j * j++) % maxSize;
        }

        // 탐색 실패
        return null;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    public static void main(String[] args) {
        QuadProbing<Integer, String> quadProbing = new QuadProbing<>();
        quadProbing.put(25, "orange");
        quadProbing.put(37, "watermelon");
        quadProbing.put(18, "apple");
        quadProbing.put(55, "banana");
        quadProbing.put(22, "kiwi");
        quadProbing.put(35, "tomato");
        quadProbing.put(50, "melon");
        quadProbing.put(63, "pear");

        quadProbing.get(50);
        quadProbing.get(63);
    }
}
