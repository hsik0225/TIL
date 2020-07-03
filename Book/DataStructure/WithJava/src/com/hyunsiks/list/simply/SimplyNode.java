package com.hyunsiks.list.simply;

class SimplyNode<E> {

    public SimplyNode<E> nextSimplyNode;

    public E item;

    public SimplyNode(SimplyNode<E> nextSimplyNode, E item) {
        this.nextSimplyNode = nextSimplyNode;
        this.item = item;
    }

    public SimplyNode<E> getNextSimplyNode() {
        return nextSimplyNode;
    }

    public void setNextSimplyNode(SimplyNode<E> nextSimplyNode) {
        this.nextSimplyNode = nextSimplyNode;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }
}
