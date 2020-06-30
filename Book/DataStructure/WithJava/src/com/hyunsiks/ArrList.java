package com.hyunsiks;

import java.util.NoSuchElementException;

public class ArrList<E> {

    private E[] items;

    private int size;

    public ArrList() {
        this.items = (E[]) new Object[1];
        size = 0;
    }

    public E peek(int index) {
        if (size == 0) {

            // UnderFlow 경우에 프로그램 정지
            throw new NoSuchElementException();
        }

        return items[index];
    }

    public void insertLast(E item) {
        if (items.length == size) {
            resize(2*items.length);
        }

        items[size++] = item;
    }

    public void insert(E item, int index) {
        if (items.length == size) {
            resize(2*items.length);
        }

        for (int i = index; i < size-1; i++) {
            items[i+1] = items[i];
        }

        items[index] = item;

        size++;
    }

    public E delete(int index) {
        if (items.length == 0) {
            throw new NoSuchElementException();
        }

        E item = items[index];

        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }

        size--;

        if (size > 0 && size == items.length / 4) {
            resize(items.length/2);
        }

        return item;
    }

    private void resize(int newSize) {

    }

    public static void main(String[] args) {

    }
}
