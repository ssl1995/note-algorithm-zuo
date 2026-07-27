package class006;

// 峰值元素是指其值严格大于左右相邻值的元素
// 给你一个整数数组 nums，已知任何两个相邻的值都不相等
// 找到峰值元素并返回其索引
// 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
// 你可以假设 nums[-1] = nums[n] = 无穷小
// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
public class Code04_FindPeakElement {

  /**
   * LC162 寻找峰值
   * 峰值元素是指其值严格大于左右相邻值的元素。
   * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
   * 你可以假设 nums[-1] = nums[n] = -∞ 。
   *  对于所有有效的 i 都有 nums[i] != nums[i + 1]
   * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
   * 输入：nums = [1,2,1,3,5,6,4]
   * 输出：1(2) 或 5(6)
   */
  class Solution {

    /**
     * 核心思想：二分不要求数组有序，只要求"二段性"——
     * 每次看 mid 的坡向，就能断定峰值必在哪一侧，把另一侧排除掉
     */
    public static int findPeakElement(int[] arr) {
      int n = arr.length;
      // 只有1个数，它左右都是-∞，自己就是峰值
      if (arr.length == 1) {
        return 0;
      }
      // 特判两端：arr[0]右边是-∞，只要大于arr[1]就是峰值
      if (arr[0] > arr[1]) {
        return 0;
      }
      // 同理，arr[n-1]只要大于arr[n-2]就是峰值
      if (arr[n - 1] > arr[n - 2]) {
        return n - 1;
      }
      // 两端都不是峰值后，峰值必在 [1, n-2] 内部（闭区间二分写法）
      int l = 1, r = n - 2, m = 0, ans = -1;
      while (l <= r) {
        m = (l + r) / 2;
        if (arr[m - 1] > arr[m]) {
          // m 处于下坡（左边比它高）：沿坡向左走，必有峰值
          // 右半 [m, r] 不可能有"最左的拐点"，排除
          r = m - 1;
        } else if (arr[m] < arr[m + 1]) {
          // m 处于上坡（右边比它高）：沿坡向右走，必有峰值
          // 左半 [l, m] 排除
          l = m + 1;
        } else {
          // arr[m-1] < arr[m] 且 arr[m] > arr[m+1]，m 就是峰值
          ans = m;
          break;
        }
      }
      return ans;
    }


  }

}
