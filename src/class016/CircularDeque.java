package class016;

import java.util.Deque;
import java.util.LinkedList;

// 设计循环双端队列
// 测试链接 : https://leetcode.cn/problems/design-circular-deque/
public class CircularDeque {

  // 提交时把类名、构造方法改成 : MyCircularDeque
  // 自己用数组实现，常数操作快，但是leetcode数据量太小了，看不出优势
  class MyCircularDeque {

    private int[] deque;
    // 双端队列就不要用开区间了，不要写，统一为左闭右闭：[L,R]
    private int l, r;
    private int size, limit;

    public MyCircularDeque(int k) {
      deque = new int[k];
      // l和r在双端队列数组实现中，初始化什么都行
      // 因为当数组为空时，l=r都需要初始化为0
      l = 0;
      r = -1;
      size = 0;
      limit = k;
    }

    public boolean insertFront(int value) {
      if (isFull()) {
        return false;
      } else {
        if (isEmpty()) {
          // 队列为空，同时初始化l和r=0
          // 所以构造器中的l和r的初始化什么都行
          l = r = 0;
          deque[0] = value;
        } else {
          // 队头加一个数：l-1
          l = l == 0 ? (limit - 1) : (l - 1);
          deque[l] = value;
        }
        size++;
        return true;
      }
    }

    public boolean insertLast(int value) {
      if (isFull()) {
        return false;
      } else {
        if (isEmpty()) {
          // 队列为空，同时初始化l和r=0
          // 所以构造器中的l和r的初始化什么都行
          l = r = 0;
          deque[0] = value;
        } else {
          // 队尾加一个数：r+1
          r = r == limit - 1 ? 0 : (r + 1);
          deque[r] = value;
        }
        size++;
        return true;
      }
    }

    public boolean deleteFront() {
      if (isEmpty()) {
        return false;
      } else {
        // 队头删一个数：l+1
        l = (l == limit - 1) ? 0 : (l + 1);
        size--;
        return true;
      }
    }

    public boolean deleteLast() {
      if (isEmpty()) {
        return false;
      } else {
        // 队尾删一个数：r-1
        r = r == 0 ? (limit - 1) : (r - 1);
        size--;
        return true;
      }
    }

    public int getFront() {
      if (isEmpty()) {
        return -1;
      } else {
        return deque[l];
      }
    }

    public int getRear() {
      if (isEmpty()) {
        return -1;
      } else {
        return deque[r];
      }
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == limit;
    }

  }

}
