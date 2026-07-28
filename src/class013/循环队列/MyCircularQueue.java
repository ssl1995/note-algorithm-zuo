package class013.循环队列;

//https://leetcode.cn/problems/design-circular-queue/
public class MyCircularQueue {

  public int[] queue;

  /**
   * 左闭右开：[l,r)
   * size：已有的数个数，循环队列引入这个变量
   * limit：队列总长度
   */
  public int l, r, size, limit;

  // 同时在队列里的数字个数，不要超过k
  public MyCircularQueue(int k) {
    queue = new int[k];
    l = 0;
    // 左闭右开,r=0
    r = 0;
    size = 0;
    limit = k;
  }

  // 如果队列满了，什么也不做，返回false
  // 如果队列没满，加入value，返回true
  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    } else {
      queue[r] = value;
      // r++, 结束了，跳回0
      r = r == limit - 1 ? 0 : (r + 1);
      size++;
      return true;
    }
  }

  // 如果队列空了，什么也不做，返回false
  // 如果队列没空，弹出头部的数字，返回true
  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    } else {
      // l++, 结束了，跳回0
      l = l == limit - 1 ? 0 : (l + 1);
      size--;
      return true;
    }
  }

  // 返回队列头部的数字（不弹出），如果没有数返回-1
  public int Front() {
    if (isEmpty()) {
      return -1;
    } else {
      return queue[l];
    }
  }

  // 返回队列尾部的数字（不弹出），如果没有数返回-1
  public int Rear() {
    if (isEmpty()) {
      return -1;
    } else {
      int last = r == 0 ? (limit - 1) : (r - 1);
      return queue[last];
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == limit;
  }
}
