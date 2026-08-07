package class038;

import java.util.HashSet;

// 字符串的全部子序列
// 子序列本身是可以有重复的，只是这个题目要求去重
// 测试链接 : https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
public class Code01_Subsequences {

  public static String[] generatePermutation1(String str) {
    char[] s = str.toCharArray();
    HashSet<String> set = new HashSet<>();
    f1(s, 0, new StringBuilder(), set);

    int m = set.size();
    String[] ans = new String[m];
    int i = 0;
    for (String cur : set) {
      ans[i++] = cur;
    }
    return ans;
  }

  /**
   * s[i...]，之前决定的路径path，set收集结果时去重
   * f1(0, ""): append 'a'
   * ├─ f1(1, "a"): append 'b'
   * │  ├─ f1(2, "ab") → 收集 "ab"
   * │  ├─ delete → path 回到 "a"      ← 分支1的撤销
   * │  └─ f1(2, "a")  → 收集 "a"      ← 分支2无修改，直接递归
   * │     （返回时 path 仍是 "a"，与进入 f1(1) 时一致 ✓）
   * ├─ delete → path 回到 ""          ← 第0层分支1的撤销
   * └─ f1(1, ""): append 'b'
   * ├─ f1(2, "b") → 收集 "b"
   * ├─ delete → path 回到 ""
   * └─ f1(2, "")  → 收集 ""
   */
  public static void f1(char[] s, int i, StringBuilder path, HashSet<String> set) {
    if (i == s.length) {
      set.add(path.toString());
    } else {
      // 分支1：要 s[i] —— 做了修改，所以递归回来要撤销
      // 做选择
      path.append(s[i]);
      f1(s, i + 1, path, set);
      // 撤销选择（恢复现场）
      path.deleteCharAt(path.length() - 1);

      // 分支2：不要 s[i] —— 什么都没改，自然无需撤销
      // 带路径回溯原则：被调用时path是什么样，返回时必须还是什么样
      f1(s, i + 1, path, set);
    }
  }

  public static String[] generatePermutation2(String str) {
    char[] s = str.toCharArray();
    HashSet<String> set = new HashSet<>();
    f2(s, 0, new char[s.length], 0, set);
    int m = set.size();
    String[] ans = new String[m];
    int i = 0;
    for (String cur : set) {
      ans[i++] = cur;
    }
    return ans;
  }

  public static void f2(char[] s, int i, char[] path, int size, HashSet<String> set) {
    if (i == s.length) {
      set.add(String.valueOf(path, 0, size));
    } else {
      // 数组范围size天然支持恢复现场
      path[size] = s[i];
      f2(s, i + 1, path, size + 1, set);
      f2(s, i + 1, path, size, set);
    }
  }

}
