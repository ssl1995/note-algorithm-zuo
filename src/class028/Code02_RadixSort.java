package class028;

import java.util.Arrays;

// 基数排序
// 测试链接 : https://leetcode.cn/problems/sort-an-array/
public class Code02_RadixSort {

  // 可以设置进制，不一定10进制，随你设置
  public static int BASE = 10;

  public static int MAXN = 50001;

  public static int[] help = new int[MAXN];

  public static int[] cnts = new int[BASE];

  public static int[] sortArray(int[] arr) {
    if (arr.length == 0) {
      return new int[]{};
    }
    // 基数排序，基于非负数进行排序，如果遇到负数，需要特殊处理
    // 比如，找到最小值，然后每个数-min，排序后，再+min
    // 如果会溢出，那么要改用long类型数组来排序
    int n = arr.length;
    // 找到数组中的最小值
    int min = arr[0];
    for (int i = 1; i < n; i++) {
      min = Math.min(min, arr[i]);
    }
    // 记录数组中的最大值=每次排序的轮数
    int max = 0;
    for (int i = 0; i < n; i++) {
      // 数组中存在非负数，每个数-min，再排序
      if (min < 0) {
        arr[i] -= min;
      }

      max = Math.max(max, arr[i]);
    }

    // 根据最大值在BASE进制下的位数，决定基数排序做多少轮
    radixSort(arr, n, bits(max));

    // 数组中所有数都减去了最小值，所以最后不要忘了还原
    if (min < 0) {
      for (int i = 0; i < n; i++) {
        arr[i] += min;
      }
    }

    return arr;
  }

  // 返回number在BASE进制下有几位
  public static int bits(int number) {
    int ans = 0;
    while (number > 0) {
      ans++;
      number /= BASE;
    }
    return ans;
  }

  // 基数排序核心代码
  // arr内要保证没有负数
  // n是arr的长度
  // bits是arr中最大值在BASE进制下有几位
  public static void radixSort(int[] arr, int n, int bits) {
    // 理解的时候可以假设BASE = 10
    for (int offset = 1; bits > 0; offset *= BASE, bits--) {
      Arrays.fill(cnts, 0);

      // 数字提取某一位的技巧
      // 比如num=21,十位2=(21/10)%10
      for (int i = 0; i < n; i++) {
        cnts[(arr[i] / offset) % BASE]++;
      }

      // 处理成前缀次数累加的形式
      // 比如cnts[1]=20,表示<=1的数有20个
      for (int i = 1; i < BASE; i++) {
        cnts[i] = cnts[i] + cnts[i - 1];
      }

      // 前缀数量分区的技巧，必须从后往前遍历
      // 比如cnts[1]=20，第一次遍历到的1放到数组下标
      for (int i = n - 1; i >= 0; i--) {
        help[--cnts[(arr[i] / offset) % BASE]] = arr[i];
      }

      System.arraycopy(help, 0, arr, 0, n);
    }
  }

}
