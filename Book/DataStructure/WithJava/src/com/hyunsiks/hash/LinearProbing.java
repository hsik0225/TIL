package com.hyunsiks.hash;

public class LinearProbing<K, V> {

    // 테이블 크기
    private int size = 13;

    // 해시 테이블
    private K[] array = (K[]) new Object[size];

    // key 관련 데이터 저장
    private V[] dataArray = (V[]) new Object[size];

    // 해시코드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    // 삽입 연산
    private void put(K key, V data) {

        // 초기 위치
        int initialPos = hash(key);

        int i = initialPos;

        int j = 1;

        do {
            // 삽입 위치 발견
            if (dataArray[i] == null) {

                // key를 해시테이블에 저장
                array[i] = key;

                // key 관련 데이터를 동일한 인덱스 하에 저장
                dataArray[i] = data;
                break;
            }

            // 이미 key 존재
            if (array[i].equals(key)) {

                // 데이터만 갱신
                dataArray[i] = data;
                break;
            }

            // i = 다음 위치
            i = (initialPos + j++)%size;
        } while (i != initialPos); // 현재 i가 초기 위치와 같게 되면 루프 종료

        System.out.println("해시 테이블");

        for (int k = 0; k < size; k++) {
            System.out.printf("%8d", k);
        }

        System.out.println();

        for (int k = 0; k < size; k++) {
            System.out.printf("%8s", array[k]);
        }

        System.out.println("\n");
    }

    // 탐색 연산
    public V get(K key) {
        int initialPos = hash(key);
        int i = initialPos;
        int j = 1;

        // array[i] 가 empty 가 아니면
        while (array[i] != null) {
            if (array[i].equals(key)) {
                System.out.printf("%d의 data = %s\n", key, dataArray[i]);
                return dataArray[i];
            }

            i = (initialPos + j++) % size;
        }

        // 탐색 실패
        return null;
    }

    public static void main(String[] args) {
        LinearProbing<Integer, String> linearProbing = new LinearProbing<>();
        linearProbing.put(25, "orange");
        linearProbing.put(37, "watermelon");
        linearProbing.put(18, "apple");
        linearProbing.put(55, "banana");
        linearProbing.put(22, "kiwi");
        linearProbing.put(35, "tomato");
        linearProbing.put(50, "melon");
        linearProbing.put(63, "pear");

        linearProbing.get(50);
        linearProbing.get(63);
    }
}
