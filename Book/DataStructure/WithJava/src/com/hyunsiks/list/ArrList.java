package com.hyunsiks.list;

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

        for (int i = size; i >= index; i--) {
            items[i] = items[i-1];
        }

        items[index] = item;

        size++;
    }

    public E delete(int index) {
        if (items.length == 0) {
            throw new NoSuchElementException();
        }

        E item = items[index];

        for (int i = index; i < size; i++) {
            items[i] = items[i+1];
        }

        size--;

        if (size > 0 && size == items.length / 4) {
            resize(items.length/2);
        }

        return item;
    }

    public void peekAll() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            String peekValue = String.valueOf(peek(i));
            result.append(peekValue).append(" ");
        }

        System.out.println(result.toString());
    }

    private void resize(int newSize) {
        System.out.println("Run Resize");

        Object[] t = new Object[newSize];

        for (int i = 0; i < size; i++) {
            t[i] = items[i];
        }

        this.items = (E[]) t;
    }

    public static void main(String[] args) {
        ArrList<String> arrList = new ArrList<>();
        arrList.insertLast("apple");
        arrList.peekAll();

        arrList.insertLast("orange");
        arrList.peekAll();

        arrList.insertLast("cherry");
        arrList.peekAll();

        arrList.insertLast("peer");
        arrList.peekAll();

        arrList.insert("grape", 1);
        arrList.peekAll();

        arrList.insert("lemon", 4);
        arrList.peekAll();

        arrList.insertLast("kiwi");
        arrList.peekAll();

        arrList.delete(4);
        arrList.peekAll();

        arrList.delete(0);
        arrList.peekAll();

        arrList.delete(0);
        arrList.peekAll();

        arrList.delete(3);
        arrList.peekAll();

        arrList.delete(0);
        arrList.peekAll();

        System.out.println("첫 번째 항목은 " + arrList.peek(1) + "입니다");
    }
}
