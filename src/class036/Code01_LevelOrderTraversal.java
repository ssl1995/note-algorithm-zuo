package class036;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class Code01_LevelOrderTraversal {

  // 不提交这个类
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  }

  // 提交时把方法名改为levelOrder，此方法为普通bfs，此题不推荐
  public static List<List<Integer>> levelOrder1(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root != null) {
      Queue<TreeNode> queue = new LinkedList<>();
      HashMap<TreeNode, Integer> levels = new HashMap<>();
      queue.add(root);
      levels.put(root, 0);
      while (!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        int level = levels.get(cur);
        if (ans.size() == level) {
          ans.add(new ArrayList<>());
        }
        ans.get(level).add(cur.val);
        if (cur.left != null) {
          queue.add(cur.left);
          levels.put(cur.left, level + 1);
        }
        if (cur.right != null) {
          queue.add(cur.right);
          levels.put(cur.right, level + 1);
        }
      }
    }
    return ans;
  }

  // 如果测试数据量变大了就修改这个值
  public static int MAXN = 2001;
  // 假设queue不会越界
  public static TreeNode[] queue = new TreeNode[MAXN];

  public static int l, r;

  // 提交时把方法名改为levelOrder，此方法为每次处理一层的优化bfs，此题推荐
  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> ans = new ArrayList<>();
    // 手写一个队列
    l = 0;
    r = 0;
    queue[r++] = root;

    while (l != r) {
      int size = r - l;
      List<Integer> temp = new ArrayList<>();
      while (size-- > 0) {
        TreeNode pop = queue[l++];// 手写队列出队也是++
        temp.add(pop.val);

        if (pop.left != null) {
          queue[r++] = pop.left;
        }
        if (pop.right != null) {
          queue[r++] = pop.right;
        }
      }
      ans.add(temp);
    }

    return ans;
  }

}
