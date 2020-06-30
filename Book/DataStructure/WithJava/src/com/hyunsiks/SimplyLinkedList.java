package com.hyunsiks;

public class SimplyLinkedList {

    class Node<E> {
        private Node<E> nextNode;

        private E item;

        public Node(Node<E> nextNode, E item) {
            this.nextNode = nextNode;
            this.item = item;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }
    }
}
