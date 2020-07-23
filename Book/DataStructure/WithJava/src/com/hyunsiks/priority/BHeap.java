package com.hyunsiks.priority;

public class BHeap<Key extends Comparable<Key>, Value> {

    // a[0]은 사용 안함
    private Entry[] array;

    // 힙의 크기
    private int size;

    public BHeap(Entry[] array, int size) {
        this.array = array;
        this.size = size;
    }

    public int size() {
        return this.size;
    }

    // 키 비교
    private boolean greater(int i, int j) {
        return array[i].getKey().compareTo(array[j].getKey()) > 0;
    }

    // a[i]와 a[j]를 교환
    private void swap(int i, int j) {
        Entry temp = array[i];
        array [i] = array[j];
        array[j ] = temp;
    }

    // 초기 임의의 순서로 키가 저장되어 있는 배열의 항목들을 최소힙으로 만든다
    public void createHeap() {
        for (int i = size/2; i > 0; i--) {
            downheap(i);
        }
    }


    // i는 현재 노드의 인덱스
    private void downheap(int i) {

        // i의 왼쪽 자식노드가 힙에 있으면
        while (2*i <= size) {

            // k는 왼쪽 자식노드의 인덱스
            int k = 2*i;

            // k가 승자의 인덱스가 됨
            if (k < size && greater(k, k+1))
                k++;

            // 현재 노드가 자식 승자와 같거나 작으면 루프를 중단
            if (!greater(i, k))
                break;


            // 현재 노드가 자식 승자보다 크면 현재 노드와 자식 승자와 교환
            swap(i, k);

            // 자식 승자가 현재 노드가 되어 다시 반복
            i = k;
        }
    }

    public void insert(Key key, Value value) {

        // 새로운 Entry 생성
        Entry temp  = new Entry(key, value);

        // 새로운 키(항목)를 배열 마지막 항목 다음에 저장
        array[++size] = temp;

        // 위로 올라가며 힙 속성 회복
        upheap(size);
    }


    // j는 현재 노드의 인덱스
    private void upheap(int j) {

        // 현재 노드가 루트가 아니고 동시에 부모가 크면
        while (j > 1 && greater(j/2, j)) {
            swap(j/2, j);
            j = j/2;
        }
    }

    public Entry deleteMin() {

        // a[1]의 최소값을 min으로 저장하여 리턴
        Entry min = array[1];

        // 힙의 마지막 항목과 루트를 교환하고 힙 크기 1 감소
        swap(1, size--);

        // 마지막 항목을 null 처리
        array[size+1] = null;

        // 힙속성을 회복
        downheap(1);

        return min;
    }

    private String print() {
        StringBuilder sb  = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            sb.append("[").append(array[i].getKey()).append(" ").append(array[i].getValue()).append("] ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Entry[] array = new Entry[13];

        // array[0]는 사용안함
        array[1] = new Entry(90, "watermelon");
        array[2] = new Entry(80, "pear");
        array[3] = new Entry(70, "melon");
        array[4] = new Entry(50, "lime");
        array[5] = new Entry(60, "mango");
        array[6] = new Entry(20, "cherry");
        array[7] = new Entry(30, "grape");
        array[8] = new Entry(35, "orange");
        array[9] = new Entry(10, "apricot");
        array[10] = new Entry(15, "banana");
        array[11] = new Entry(45, "lemon");
        array[12] = new Entry(40, "kiwi");

        BHeap bHeap = new BHeap(array, 12);
        System.out.println("힙 만들기 전 : \n" + bHeap.print());

        // 힙 만들기
        bHeap.createHeap();
        System.out.println("최소힙 : \n" + bHeap.print());

        bHeap.deleteMin();
        System.out.println("min 삭제 후 : \n" + bHeap.print());

        bHeap.insert(5, "apple");
        System.out.println("5 삽입 후 : \n" + bHeap.print());
    }
}
