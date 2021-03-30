package com.zhoutao123.leetcode.simple;

import com.zhoutao123.leetcode.TreeNode;

import java.util.Deque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Q101-对称树
 *
 * <p>执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 *
 * <p>内存消耗： 36.2 MB , 在所有 Java 提交中击败了 95.48% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/symmetric-tree/
 */
public class Q101_SymmetricTree {

  public static void main(String[] args) {
    Q101_SymmetricTree instance = new Q101_SymmetricTree();
    TreeNode root = new TreeNode(1);

    TreeNode left = new TreeNode(2);
    left.left = new TreeNode(4);
    left.right = new TreeNode(3);

    TreeNode right = new TreeNode(2);
    right.left = new TreeNode(4);
    right.right = new TreeNode(3);

    root.left = left;
    root.right = right;

    System.out.println(instance.isSymmetric(root));
    System.out.println(instance.isSymmetricWithQueue(root));
  }

  /** 递归实现对称数 */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return nodeEqual(root.left, root.right);
  }

  private boolean nodeEqual(TreeNode l, TreeNode r) {
    if (l == null && r == null) {
      return true;
    } else if (l == null || r == null) {
      return false;
    }
    boolean valueEqual = Objects.equals(l.val, r.val);
    return valueEqual && nodeEqual(l.left, r.right) && nodeEqual(l.right, r.left);
  }

  /** 非递归的方式实现 */
  public boolean isSymmetricWithQueue(TreeNode root) {
    TreeNode NULL = new TreeNode();
    Queue<TreeNode> queue = new LinkedBlockingDeque<>();
    Deque<TreeNode> deque = new LinkedBlockingDeque<>();
    queue.offer(root);
    queue.offer(NULL);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      // 说明一层结束了
      if (poll == NULL) {
        if (!queue.isEmpty()) {
          queue.offer(NULL);
        }
        // 检查双向队列中的
        while (true) {
          if (deque.size() == 1) {
            deque.clear();
            break;
          }
          TreeNode fVal = deque.pollFirst();
          TreeNode lVal = deque.pollLast();
          // 说明队列的值取完了
          if (fVal == null && lVal == null) {
            break;
          } else if (fVal == null || lVal == null) {
            return false;
          }

          if (!Objects.equals(lVal.val, fVal.val)) {
            return false;
          }
        }
        continue;
      }
      deque.offerFirst(poll);
      if (poll.left != null) {
        queue.offer(poll.left);
      }

      if (poll.right != null) {
        queue.offer(poll.right);
      }
    }
    return true;
  }
}
