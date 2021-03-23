package com.zhoutao123.struct;

import java.util.Objects;

/**
 * 链表翻转
 */
public class Reserve {
    public static void main(String[] args) {
        Node node = new Node("0");
        Node tmp = node;
        for (int i = 1; i <= 100; i++) {
            tmp.next = new Node(String.valueOf(i));
            tmp = tmp.next;
        }

        node = reserveGroup(node, 5);


        tmp = node;
        while (tmp != null) {
            System.out.print(tmp.data + ",");
            tmp = tmp.next;
        }
        System.out.println();
    }

    private static Node reserveGroup(Node node, int k) {
        Node b = node;
        // 查找到指定位置
        for (int i = 0; i < k; i++) {
            if (b == null) return node;
            b = b.next;
        }
        Node newPre = reserve(node, b);
        node.next = reserveGroup(b, k);
        return newPre;
    }

    private static Node reserve(Node node, Node b) {
        Node pre, cur, nxt;
        pre = null;
        cur = node;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


    static class Node {

        public String data;

        public Node next;

        public Node(String node) {
            this.data = node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
}
