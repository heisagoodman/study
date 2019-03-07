package com.law.niuke;

/**
 * 描述：
 *
 * @author law
 * @create 2019-02-24 下午7:10
 */
public class QueueAl<T> {
    private Node head;
    private Node tail;

    public QueueAl() {
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static class Node<T> {
        T t;
        Node next;
    }

    public void add(T t) {
        Node node = new Node();
        node.t = t;
        if (head == null) {
            head = node;
        }
        if (tail == null) {
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public T peek() {
        T t = (T) head.t;
        head = head.next;
        return t;
    }
}
