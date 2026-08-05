package class036;

// 求完全二叉树的节点个数
// 测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code09_CountCompleteTreeNodes {

  // 不提交这个类
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  }

  // 提交如下的方法
  public static int countNodes(TreeNode head) {
    if (head == null) {
      return 0;
    }
    int treeHeight = mostLeft(head, 1);
    return f(head, 1, treeHeight);
  }

  // cur : 当前来到的节点
  // level :  当前来到的节点在第几层
  // h : 整棵树的高度，不是cur这棵子树的高度
  // 求 : cur这棵子树上有多少节点
  public static int f(TreeNode cur, int level, int h) {
    if (level == h) {
      return 1;
    }
    // 1、当前节点的右子树，扎到了整棵树的高度，当前节点的左子树是满二叉树
    if (mostLeft(cur.right, level + 1) == h) {
      // 满二叉树节点数：2^高度-1
      int leftFullCount = (1 << (h - level)) - 1;
      // 当前节点数=1
      int curCount = 1;
      // 当前节点左子树节点数
      int rightCount = f(cur.right, level + 1, h);
      return leftFullCount + curCount + rightCount;
    } else {
      // 2、当前节点的右子树，没扎到了整棵树的高度，当前节点的右子树是满二叉树
      // 满二叉树节点数：2^高度-1
      int rightFullCount = (1 << (h - level - 1)) - 1;
      // 当前节点数=1
      int curCount = 1;
      // 当前节点左子树节点数
      int leftCount = f(cur.left, level + 1, h);
      return rightFullCount + curCount + leftCount;
    }
  }

  // 当前节点是cur，并且它在level层
  // 返回从cur开始不停往左，能扎到几层
  public static int mostLeft(TreeNode cur, int level) {
    while (cur != null) {
      cur = cur.left;
      level++;
    }
    return level - 1;
  }

}
