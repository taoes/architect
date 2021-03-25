package com.zhoutao123.struct;

import java.util.Random;

/**
 * 树的遍历
 */
public class TreeForeach {

    public static void main(String[] args) {
        TreeNode<Integer> node = generatorTree();
        System.out.println();

        // 前序遍历
        node.foreach(1);
        node.foreach(2);
        node.foreach(3);
    }


    /**
     * 生成搜索二叉树
     *
     * @return 二叉树
     */
    public static TreeNode<Integer> generatorTree() {

        TreeNode<Integer> root = new TreeNode<>(50);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            TreeNode<Integer> tmp = new TreeNode<>(random.nextInt(100));
            System.out.print(tmp.data + ",");
            append(root, tmp);
        }
        return root;
    }

    /**
     * 插入节点到树中
     */
    private static void append(TreeNode<Integer> root, TreeNode<Integer> tmp) {
        if (root == null) {
            root = tmp;
        }
        Integer data = root.data;
        if (tmp.data > data) {
            if (root.right == null) {
                root.right = tmp;
            } else {
                append(root.right, tmp);
            }
        } else {
            if (root.left == null) {
                root.left = tmp;
            } else {
                append(root.left, tmp);
            }
        }

    }

    static class TreeNode<T> {

        private T data;

        //左节点
        private TreeNode<T> left;

        //右节点
        private TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }

        public void foreach(int foreachStyle) {

            if (foreachStyle == 1) {
                System.out.print(this.data + ",");
            }

            if (left != null) {
                left.foreach(foreachStyle);
            }


            if (foreachStyle == 2) {
                System.out.print(this.data + ",");
            }

            if (right != null) {
                right.foreach(foreachStyle);
            }


            if (foreachStyle == 3) {
                System.out.print(this.data + ",");
            }
        }
    }


}
