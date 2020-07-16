package com.hyunsiks.hash;

public class Chaining<K, V> {
    private int maxSize = 13;
    private Node[] nodeArray = new Node[maxSize];

    public static class Node {
        private Object key;
        private Object data;
        private Node next;

        public Node(Object key, Object data, Node ref) {
            this.key = key;
            this.data = data;
            this.next = ref;
        }

        public Object getKey() {
            return key;
        }

        public Object getData() {
            return data;
        }
    }

    // 해시코드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % maxSize;
    }

    public void put(K key, V data) {
        int i = hash(key);
        for (Node node = nodeArray[i]; node != null; node = node.next) {

            // 이미 key 존재
            if (key.equals(node.key)) {

                // data 만 갱신
                node.data = data;
                return;
            }
        }


        // 연결리스트의 첫 노드로 삽입
        nodeArray[i] = new Node(key, data, nodeArray[i]);
    }

    public V get(K key) {
        int i = hash(key);

        // 링크드리스트 탐색
        for (Node node = nodeArray[i]; node != null; node = node.next) {
            if (key.equals(node.key))
                return (V) node.data;
        }

        // 탐색 실패
        return null;
    }
}
