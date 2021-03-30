package com.zhoutao123.struct.tree;

/**
 * 树的遍历
 *
 * @apiNote 前序遍历，中序遍历，后序遍历以及层次遍历
 */
public class TreeSearch {

  public static void main(String[] args) {
    int[] arr = {10, 3, 4, 12, 32, 1, 5, 21, 0, 45};
    TreeNode node = TreeUtils.generatorTree(arr);
    int max = TreeUtils.findMax(node);
    System.out.printf("最大元素:%s\n", max);

    System.out.printf("从树中查找 21=>:%s\n", TreeUtils.container(node, 21));
    System.out.printf("从树中查找 45=>:%s\n", TreeUtils.container(node, 45));
    System.out.printf("从树中查找 1=>:%s\n", TreeUtils.container(node, 1));
    System.out.printf("从树中查找 7=>:%s\n", TreeUtils.container(node, 7));

    System.out.printf("节点的数量 7=>:%s\n", TreeUtils.nodeCount(node));
  }
}
