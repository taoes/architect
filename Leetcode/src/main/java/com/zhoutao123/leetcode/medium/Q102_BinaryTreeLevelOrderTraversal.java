package com.zhoutao123.leetcode.medium;

import com.zhoutao123.leetcode.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Q102-二叉树的层次遍历
 *
 * <p>执行用时： 7 ms , 在所有 Java 提交中击败了 10.26% 的用户
 *
 * <p>内存消耗： 38.8 MB , 在所有 Java 提交中击败了 22.64% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class Q102_BinaryTreeLevelOrderTraversal {

  public static void main(String[] args) {
    Q102_BinaryTreeLevelOrderTraversal instance = new Q102_BinaryTreeLevelOrderTraversal();
    TreeNode root = new TreeNode(3);

    TreeNode left = new TreeNode(9);
    //    left.left = new TreeNode();
    //    left.right = new TreeNode(3);

    TreeNode right = new TreeNode(20);
    right.left = new TreeNode(15);
    right.right = new TreeNode(7);

    root.left = left;
    root.right = right;
    List<List<Integer>> lists = new Q102_BinaryTreeLevelOrderTraversal().levelOrder(root);
    System.out.println();
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    TreeNode NULL = new TreeNode();
    Queue<TreeNode> queue = new LinkedBlockingDeque<>();
    List<List<Integer>> deque = new ArrayList<>();
    List<Integer> integers = new ArrayList<>();
    queue.offer(root);
    queue.offer(NULL);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      // 说明一层结束了
      if (poll == NULL) {
        if (!queue.isEmpty()) {
          queue.offer(NULL);
        }
        deque.add(integers);
        integers = new ArrayList<>();
        continue;
      }
      integers.add(poll.val);
      if (poll.left != null) {
        queue.offer(poll.left);
      }

      if (poll.right != null) {
        queue.offer(poll.right);
      }
    }
    return deque;
  }
}
