package class004;

public class SelectBubbleInsert {

  // 数组中交换i和j位置的数
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // 选择排序
  public static void selectionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // [0,i][j,n-1],后面找minIndex与i交换
    for (int minIndex, i = 0; i < arr.length - 1; i++) {
      minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      swap(arr, i, minIndex);
    }
  }

  // 冒泡排序
  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // [0,j][i,n-1],每次都将[0,i]中的最大值放到最终位置
    for (int i = arr.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
        }
      }
    }
  }

  // 插入排序
  public static void insertionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // [0,j][i,n-1],新来的i插入到[0,j]合适的位置：1、j>=0；2、比左边的数小就停止
    for (int i = 1; i < arr.length; i++) {
      for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
        swap(arr, j, j + 1);
      }
    }
  }

}