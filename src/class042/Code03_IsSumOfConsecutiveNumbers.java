package class042;

// 判断一个数字是否是若干数量(数量>1)的连续正整数的和
public class Code03_IsSumOfConsecutiveNumbers {

	public static boolean is1(int num) {
		for (int start = 1, sum; start <= num; start++) {
			sum = start;
			for (int j = start + 1; j <= num; j++) {
				if (sum + j > num) {
					break;
				}
				if (sum + j == num) {
					return true;
				}
				sum += j;
			}
		}
		return false;
	}

	public static boolean is2(int num) {
		// 是2的某次方=false
//		return (num & (num - 1)) == 0 ? false : true;
		return (num & (num - 1)) != 0;
	}

	public static void main(String[] args) {
		for (int num = 1; num < 200; num++) {
			// 暴力法，找规律-> 2的某次方不能表示成连续正整数的和
			System.out.println(num + " : " + (is1(num) ? "T" : "F"));
		}
	}
}
