package com.hyunsiks.priority;

public class Huffman {

    // a[0] 은 사용안함
    private Entry[] a;

    // 힙의 크기
    private int size;

    public Huffman(Entry[] a, int size) {
        this.a = a;
        this.size = size;
    }

    private boolean greater(int i, int j) {
        return a[i].getKey() > a[j].getKey();
    }

    // 허프만 트리 생성
    public Entry createTree() {

        // 힙에 1개의 노드만 남을 때까지
        while (size > 1) {
            Entry e1 = deleteMin();
            Entry e2 = deleteMin();

            Entry temp = new Entry(e1.getKey() + e2.getKey(), e1.getValue() + e2.getValue(), e1, e2, " ");

            insert(temp);
        }

        // 1개 남은 노드(루트노드)를 힙에서 제거하며 리
        return deleteMin();
    }

    private void insert(Entry temp) {
        a[++size] = temp;
        upheap(size);
    }

    private void upheap(int j) {

        // 현재 노드가 루트가 아니고 동시에 부모가 크면
        while (j > 1 && greater(j/2, j)) {
            swap(j/2, j);
            j = j/2;
        }
    }

    private Entry deleteMin() {

        // a[1]의 최소값을 min으로 저장하여 리턴
        Entry min = a[1];

        // 힙의 마지막 항목과 교환하고 힙 크기 1 감소
        swap(1, size--);

        // 마지막 항목을 null 로 처리
        a[size + 1] = null;

        // 힙속성을 회복
        downheap(1);

        return min;
    }

    private void downheap(int i) {

        // i의 왼쪽 자식노드가 힙에 있으면
        while (2 * i <= size) {

            // k는 왼쪽 자식노드의 인덱스
            int k = 2 * i;

            // k 가 승자의 인덱스가 됨
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

    private void swap(int i, int j) {
        Entry temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            sb.append("[ " + a[i].getKey() + " " + a[i].getValue() + " ] ");
        }

        return sb.toString();
    }

    private String printCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            sb.append(a[i].getValue() + " : " + a[i].getCode());
        }

        return sb.toString();
    }

    static class Entry {

        // 빈도 수
        private int frequency;

        // 이파리 노드의 문자 또는 내부노드의 합성된 문자열
        private String word;

        // 왼쪽 자식
        Entry left;

        // 오른쪽 자식
        Entry right;

        // 허프만 코드
        private String code;

        public Entry(int frequency, String word, Entry left, Entry right, String code) {
            this.frequency = frequency;
            this.word = word;
            this.left = left;
            this.right = right;
            this.code = code;
        }

        public int getKey() {
            return frequency;
        }

        public String getValue() {
            return word;
        }

        public Entry getLeft() {
            return left;
        }

        public Entry getRight() {
            return right;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    private void createHeap() {
        for (int i = size/2; i > 0; i--) {
            downheap(i);
        }
    }

    private void preorder(Entry entry) {
        if (entry.left != null) {
            entry.left.setCode(entry.code + "0");
            preorder(entry.left);
        }

        if (entry.right != null) {
            entry.right.setCode(entry.code + "1");
            preorder(entry.right);
        }

        if (entry.left == null && entry.right == null)
            System.out.print("[ " + entry.getValue() + " : " + entry.getCode() + " ] ");
    }

    public static void main(String[] args) {
        Entry[] entries = new Entry[7];
        entries[1] = new Entry(60, "a" ,null, null, null);
        entries[2] = new Entry(20, "b" ,null, null, null);
        entries[3] = new Entry(30, "c" ,null, null, null);
        entries[4] = new Entry(35, "d" ,null, null, null);
        entries[5] = new Entry(40, "e" ,null, null, null);
        entries[6] = new Entry(90, "f" ,null, null, null);

        Huffman huffman = new Huffman(entries, 6);
        System.out.println("최소힙 만들기 전 : \n" + huffman.print());

        huffman.createHeap();
        System.out.println("\n최소힙 : \n" + huffman.print());

        Entry root = huffman.createTree();
        System.out.println("\n허프만 코드 : " + huffman.printCode());
        huffman.preorder(root);
    }
}
