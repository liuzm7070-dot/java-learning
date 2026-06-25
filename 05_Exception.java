/**
 * 题 5：异常处理
 *
 * 写一个方法 public static int safeDivide(String a, String b)
 * 从字符串解析两个整数并做除法。
 * 需要处理的情况：
 *   1. NumberFormatException（输入不是数字）
 *   2. ArithmeticException（除数为 0）
 * 两种情况都返回 -1 并打印有意义的错误信息。
 *
 * 考察：try-catch、异常链、Integer.parseInt
 */
public class Ex05_SafeDivide {
    public static int safeDivide(String a, String b) {
        // TODO
    }

    public static void main(String[] args) {
        System.out.println(safeDivide("10", "2"));   // 期望: 5
        System.out.println(safeDivide("10", "0"));   // 期望: -1 + 错误信息
        System.out.println(safeDivide("10", "abc")); // 期望: -1 + 错误信息
    }
}
