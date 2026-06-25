/**
 * 题 1：基础语法 + 数组操作
 *
 * 写一个方法 public static int[] runningSum(int[] nums)
 * 返回「前缀和」数组：result[i] = nums[0] + ... + nums[i]
 *
 * 示例：输入 [1,2,3,4] → 输出 [1,3,6,10]
 *
 * 考察：数组遍历、静态方法、返回数组
 */
public class Ex01_RunningSum {
    public static int[] runningSum(int[] nums) {
        // TODO
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        int[] result = runningSum(input);
        // 打印结果
        for (int n : result) {
            System.out.print(n + " ");
        }
        System.out.println(); // 期望: 1 3 6 10
    }
}
