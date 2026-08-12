package class045;

import java.util.Arrays;
import java.util.HashSet;

// 数组中两个数的最大异或值
// 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0<=i<=j<=n
// 1 <= nums.length <= 2 * 10^5
// 0 <= nums[i] <= 2^31 - 1
// 测试链接 : https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
public class Code02_TwoNumbersMaximumXor {

  // 前缀树的做法
  // 好想
  public static int findMaximumXOR1(int[] nums) {
    build(nums);
    int ans = 0;
    for (int num : nums) {
      ans = Math.max(ans, maxXor(num));
    }
    clear();
    return ans;
  }

  // 准备这么多静态空间就够了，实验出来的
//   如果测试数据升级了规模，就改大这个值
	public static int MAXN = 3000001;
//  public static int MAXN = 12;

  public static int[][] tree = new int[MAXN][2];

  // 前缀树目前使用了多少空间
  public static int cnt;

  // 数字只需要从哪一位开始考虑
  public static int high;

  public static void build(int[] nums) {
    cnt = 1;
    // 找个最大值
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }
    // 计算数组最大值的二进制状态，有多少个前缀的0
    // 可以忽略这些前置的0，从left位开始考虑
    high = 31 - Integer.numberOfLeadingZeros(max);
    for (int num : nums) {
      insert(num);
    }
  }

  public static void insert(int num) {
    int cur = 1;
    for (int i = high, path; i >= 0; i--) {
      path = (num >> i) & 1;
      if (tree[cur][path] == 0) {
        tree[cur][path] = ++cnt;
      }
      cur = tree[cur][path];
    }
  }

  // 查询 num 与树中所有数字异或的最大值
  // 贪心策略：从高位到低位，每一位都尽量走【相反 bit】的路，让该位异或出 1
  //
  // 举例：nums = {12, 2} 建好的树如下（high = 3，只看第3~0位）
  //            1 (根)
  //           / \
  //         0/   \1
  //         6     2
  //        0|     |1
  //         7     3
  //        1|    0|
  //         8     4
  //        0|    0|
  //         9     5
  //       (2)   (12)
  // 以 num = 12（二进制 1100）为例，逐位走树：
  //   i=3: status=1, want=0, tree[1][0]=6 存在 -> 走节点6, 该位异或得1, ans=1000
  //   i=2: status=1, want=0, tree[6][0]=7 存在 -> 走节点7, 该位异或得1, ans=1100
  //   i=1: status=0, want=1, tree[7][1]=8 存在 -> 走节点8, 该位异或得1, ans=1110
  //   i=0: status=0, want=1, tree[8][1]=0 不存在! 只能走 tree[8][0]=9
  //        -> 该位异或得0, ans=1110
  // 最终 ans = 1110 = 14，即 12 ^ 2，就是最大异或值
  public static int maxXor(int num) {
    // 最终异或的结果(尽量大)
    int ans = 0;
    // 前缀树目前来到的节点编号
    int cur = 1;
    for (int i = high, status, want; i >= 0; i--) {
      // status : num第i位的状态
      // 例如 num=12(1100)，i=2 时 status = (12>>2)&1 = 1
      status = (num >> i) & 1;
      // want : num第i位希望遇到的状态（相反的bit，这样才能异或出1）
      want = status ^ 1;
      if (tree[cur][want] == 0) { // 询问前缀树，能不能达成
        // 不能达成（相反bit的路不存在），只能妥协走相同bit的路
        // 例如 num=12 走到 i=0 时：tree[8][1]=0，没有 bit1 的路，want 改回 0
        want ^= 1;
      }
      // want变成真的往下走的路
      // status ^ want：该位实际异或的结果（走了相反路就是1，妥协了就是0）
      // << i 后 |= 进 ans：把这一位的结果拼到总答案的第 i 位上
      ans |= (status ^ want) << i;
      // 沿选定的路走到子节点，继续处理下一位
      cur = tree[cur][want];
    }
    return ans;
  }

  public static void clear() {
    for (int i = 1; i <= cnt; i++) {
      tree[i][0] = tree[i][1] = 0;
    }
  }

  // 用哈希表的做法
  // 难想
  public int findMaximumXOR2(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(num, max);
    }
    int ans = 0;
    HashSet<Integer> set = new HashSet<>();
    for (int i = 31 - Integer.numberOfLeadingZeros(max); i >= 0; i--) {
      // ans : 31....i+1 已经达成的目标
      int better = ans | (1 << i);
      set.clear();
      for (int num : nums) {
        // num : 31.....i 这些状态保留，剩下全成0
        num = (num >> i) << i;
        set.add(num);
        // num ^ 某状态 是否能 达成better目标，就在set中找 某状态 : better ^ num
        if (set.contains(better ^ num)) {
          ans = better;
          break;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums={3,10,5,25,2,8};
    // 最大运算结果是 5 XOR 25 = 28.
    System.out.println(findMaximumXOR1(nums));
  }

}