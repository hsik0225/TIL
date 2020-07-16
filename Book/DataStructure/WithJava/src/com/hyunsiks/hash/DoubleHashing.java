package com.hyunsiks.hash;

public class DoubleHashing<K, V> {

    private int maxSize = 13;

    private int currentSize = 0;

    private K[] array = (K[]) new Object[maxSize];

    private V[] dataArray = (V[]) new Object[maxSize];

    // 해시코드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    private void put(K key, V data) {
        int initialPos = hash(key);
        int i = initialPos;
        int j = 1;

        // 두 번째 해시 함수
        int d = (7 - Integer.parseInt(key.toString())%7);

        do {
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

            i = (initialPos + j * d) % maxSize;
            j++;
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

    public V get(K key) {
        int initialPos = hash(key);
        int i = initialPos;
        int j = 1;

        // 두 번째 해시 함수
        int d = (7 - Integer.parseInt(key.toString())%7);

        while (array[i] != null) {
            if (array[i].equals(key)) {
                System.out.printf("%d의 data = %s\n", key, dataArray[i]);
                return dataArray[i];
            }

            i = (initialPos + j * j++) % maxSize;
            j++;
        }

        return null;
    }

    public static void main(String[] args) {
        DoubleHashing<Integer, String> doubleHashing = new DoubleHashing<>();
        doubleHashing.put(25, "orange");
        doubleHashing.put(37, "watermelon");
        doubleHashing.put(18, "apple");
        doubleHashing.put(55, "banana");
        doubleHashing.put(22, "kiwi");
        doubleHashing.put(35, "tomato");
        doubleHashing.put(50, "melon");
        doubleHashing.put(63, "pear");

        doubleHashing.get(50);
        doubleHashing.get(63);
    }
}
