package com.zhoutao123.struct.tree;

/**
 * 树的遍历
 *
 * @apiNote 前序遍历，中序遍历，后序遍历以及层次遍历
 */
public class TreeTraversal {

  public static void main(String[] args) {
    int[] arr = {10, 3, 4, 12, 32, 1, 5, 21, 0, 45};
    TreeNode node = TreeUtils.generatorTree(arr);
    System.out.print("前序遍历");
    TreeUtils.perOrderTraversal(node);

    System.out.print("\n中序遍历");
    TreeUtils.inOrderTraversal(node);

    System.out.print("\n后序遍历");
    TreeUtils.postOrderTraversal(node);

    System.out.println("\n层次遍历");
    TreeUtils.layoutTraversal(node);
  }
}
