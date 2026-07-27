package class006;

import java.util.Arrays;

// 有序数组中找<=num的最右位置
public class Code03_FindRight {

  // 为了验证
  public static void main(String[] args) {
    int N = 100;
    int V = 1000;
    int testTime = 500000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int n = (int) (Math.random() * N);
      int[] arr = randomArray(n, V);
      Arrays.sort(arr);
      int num = (int) (Math.random() * V);
//      if (right(arr, num) != findRight(arr, num)) {
//        System.out.println("出错了!");
//      }
      if (findRight(arr, num) != findRight1(arr, num)) {
        System.out.println("出错了!");
      }
    }
    System.out.println("测试结束");
  }

  // 为了验证
  public static int[] randomArray(int n, int v) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (int) (Math.random() * v) + 1;
    }
    return arr;
  }

  // 为了验证
  // 保证arr有序，才能用这个方法
  public static int right(int[] arr, int num) {
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] <= num) {
        return i;
      }
    }
    return -1;
  }

  // 保证arr有序，才能用这个方法
  // 有序数组中找<=num的最右位置，没找到返回-1
  public static int findRight(int[] arr, int num) {
    int left = 0, right = arr.length - 1, mid = 0;
    int ans = -1;
    while (left <= right) {
      mid = left + ((right - left) >> 1);
      if (arr[mid] <= num) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }

  // 有序数组中找<=num的最右位置，没找到返回-1
  public static int findRight1(int[] arr, int num) {
    int left = 0;
    int right = arr.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] > num) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // 当所有元素都>t时，left=0
    // [2,3,4]找<=1的最右位置
    return left == 0 ? -1 : left - 1;
  }

}
