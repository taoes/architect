package com.zhoutao123.struct;

/**
 * 链表的倒数第N个节点
 *
 * @apiNote 双指针
 */
public class LinkedNodeFromEnd {
    public static void main(String[] args) {
        final Node node = Node.generator(0, 5);
        int k = 3;
        int p2Index = 0;
        Node p1 = node;
        Node p2 = node;
        while (p2 != null) {
            if (p2Index < k) {
                p2Index++;
                p2 = p2.getNext();
            } else {
                p2 = p2.getNext();
                p1 = p1.getNext();
            }
        }
        System.out.println(p1.getData());
    }
}
