package class015;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

  /**
   * 最小栈
   * 解法：差值编码法（O(1) 额外空间，面试加分解）
   * 时间复杂度：所有操作 O(1)
   * 空间复杂度：O(1) 额外空间——只有 1 个栈 + 1 个 min 变量，没有辅助栈
   * （栈本身存 n 个元素属于必需的数据存储，不计入额外空间）
   * 【记忆口诀】存差不存值，负差记新底；弹负还旧min，正差加min还
   */
  private final Deque<Long> stack;// 必用数据结构：存"入栈值 - 入栈前的最小值"
  // min设计+-操作，用long防止越界
  private long min;// O(1)的额外空间

  public MinStack() {
    stack = new ArrayDeque<>();
  }

  public void push(int val) {
    // 1、第一个元素：差值约定为 0，min 就是它自己
    if (stack.isEmpty()) {
      stack.push(0L);
      min = val;
      return;
    }
    // 2、存"入栈值与入栈前最小值的差"（min 是 long，自动提升为 long 运算，不溢出）
    long diff = val - min;
    stack.push(diff);
    // 3、差值为负 => 本次入栈刷新了最小值，更新 min
    if (diff < 0) {
      min = val;
    }
  }

  // 题目要求只在栈非空时执行pop、top、getMin
  public void pop() {
    Long diff = stack.pop();
    // 负差：弹掉的正是当前最小值，用 min - diff 还原上一个最小值
    // （diff = 新min - 旧min => 旧min = 当前min - diff）
    if (diff < 0) {
      min = min - diff;
    }
    // 非负差：min 本来就不是它刷新的，不用动
  }

  public int top() {
    long diff = stack.peek();
    // 负差：栈顶真实值就是当前 min；非负差：真实值 = min + 差
    if (diff < 0) {
      return (int) min;
    }
    return (int) (min + diff);
  }

  public int getMin() {
    return (int) min;
  }
}
