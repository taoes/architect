package com.zhoutao123.struct;

/**
 * 合并有序链表
 */
public class MergeLinkedList {

    public static void main(String[] args) {
        Node node1 = Node.generator(0, 14,3);
        Node node2 = Node.generator(3, 33,4);
        node1.print();
        node2.print();
        Node merge = merge(node1, node2);
        merge.print();

    }

    public static Node merge(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        int v1 = node1.getData();
        int v2 = node2.getData();

        if (v1 <= v2) {
            Node merge = merge(node1.getNext(), node2);
            node1.setNext(merge);
            return node1;
        } else {
            Node merge = merge(node1, node2.getNext());
            node2.setNext(merge);
            return node2;
        }


    }
}

