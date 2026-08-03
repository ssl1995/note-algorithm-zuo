package class031;

// 已知n是非负数
// 返回大于等于n的最小的2某次方
// 如果int范围内不存在这样的数，返回整数最小值
public class Code03_Near2power {

  public static int near2power(int n) {
    if (n <= 0) {
      return 1;
    }
		// 1、>=n 转成 严格>n-1的最小2的幂
		// 如果n不是2的幂，那么n--没用；但如果n是2的幂，答案就是自己
    n--;
		// 2、最高位的1向右铺满
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
		// 3、返回n+1，就是>=n的最小2的幂次
    return n + 1;
  }

  public static void main(String[] args) {
    int number = 100;
    System.out.println(near2power(number));
  }

}
