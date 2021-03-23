package com.zhoutao123.struct;


import lombok.Data;

@Data
public class Node {

    private int data;

    private Node pre;

    private Node next;

    public Node(int data) {
        this.data = data;
    }


    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static Node generator(int start, int count) {
        return generator(start, count, 1);
    }

    public static Node generator(int start, int count, int step) {
        final Node node = new Node(start);
        Node tmp = node;
        for (int i = start + 1; i < count + start; i += step) {
            Node newNode = new Node(i);
            tmp.setNext(newNode);
            tmp = newNode;
        }
        return node;
    }

    public void print() {
        Node tmp = this;
        while (tmp != null) {
            System.out.print(tmp.getData() + ",");
            tmp = tmp.getNext();
        }
        System.out.println();
    }
}
