package com.hyunsiks.list.circular;

public class CircularNode<E> {

    public CircularNode<E> nextNode;

    public E item;

    public CircularNode(CircularNode<E> nextNode, E item) {
        this.nextNode = nextNode;
        this.item = item;
    }

    public void setNextNode(CircularNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    public void setItem(E item) {
        this.item = item;
    }
}
