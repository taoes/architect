package com.zhoutao123.struct.tree;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class TreeUtils {

  public static TreeNode generatorTree(int[] arr) {
    TreeNode root = new TreeNode(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      insert(root, arr[i]);
    }
    return root;
  }

  /** 递归的方式实现前序遍历 */
  public static void perOrderTraversal(TreeNode node) {
    if (node == null) {
      return;
    }

    System.out.print(node.getData() + "，");
    perOrderTraversal(node.getLeft());
    perOrderTraversal(node.getRight());
  }

  /** 递归的方式实现中序遍历 */
  public static void inOrderTraversal(TreeNode node) {
    if (node == null) {
      return;
    }

    inOrderTraversal(node.getLeft());
    System.out.print(node.getData() + "，");
    inOrderTraversal(node.getRight());
  }

  /** 递归的方式实现后序遍历 */
  public static void postOrderTraversal(TreeNode node) {
    if (node == null) {
      return;
    }

    postOrderTraversal(node.getLeft());
    postOrderTraversal(node.getRight());
    System.out.print(node.getData() + "，");
  }

  /**
   * 实现层次遍历
   *
   * <p>实现原理是通过一个队列实现的</>
   */
  public static void layoutTraversal(TreeNode node) {
    Queue<TreeNode> queue = new LinkedBlockingDeque<>();
    queue.add(node);
    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();
      System.out.print(head.getData() + ",");
      if (head.getLeft() != null) {
        queue.offer(head.getLeft());
      }

      if (head.getRight() != null) {
        queue.offer(head.getRight());
      }
    }
  }

  /**
   * 从树中查找最大的元素, 通过层遍历的方式查找最大元素
   *
   * @apiNote 时间复杂度: O(n)
   */
  public static int findMax(TreeNode node) {
    Queue<TreeNode> queue = new LinkedBlockingDeque<>();
    queue.add(node);
    int max = Integer.MIN_VALUE;
    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();
      if (max < head.getData()) {
        max = head.getData();
      }
      if (head.getLeft() != null) {
        queue.offer(head.getLeft());
      }

      if (head.getRight() != null) {
        queue.offer(head.getRight());
      }
    }
    return max;
  }

  /**
   * 从树中查找某个元素
   *
   * @apiNote 时间复杂度: O(n) 空间复杂度: O(n)
   */
  public static boolean container(TreeNode node, int data) {
    if (node == null) {
      return false;
    }
    if (node.getData() == data) {
      return true;
    }

    // 判断左子树是否包含此数据,如果左子树没有包含，那么判断右子树是够包含
    if (!container(node.getLeft(), data)) {
      return container(node.getRight(), data);
    }
    return true;
  }

  /**
   * 递归法计算树中节点的个数
   *
   * @apiNote 非递归法可以使用遍历树的方式实现
   */
  public static int nodeCount(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return nodeCount(node.getLeft()) + 1 + nodeCount(node.getRight());
  }

  private static void insert(TreeNode root, int data) {
    if (root == null) {
      return;
    }
    int rootData = root.getData();
    if (data > rootData) {
      if (root.getRight() == null) {
        root.setRight(new TreeNode(data));
      } else {
        insert(root.getRight(), data);
      }
    } else {
      if (root.getLeft() == null) {
        root.setLeft(new TreeNode(data));
      } else {
        insert(root.getLeft(), data);
      }
    }
  }

  public static void main(String[] args) {
    ScriptEngineManager manager = new ScriptEngineManager();
    List<ScriptEngineFactory> factoryList = manager.getEngineFactories();
    for (ScriptEngineFactory factory : factoryList) {
      System.out.println(factory.getEngineName() + " --> " + factory.getLanguageName());
    }
  }
}
