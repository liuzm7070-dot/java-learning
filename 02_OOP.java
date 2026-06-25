/**
 * 题 2：面向对象基础
 *
 * 设计一个 BankAccount 类：
 * - 字段：owner(String), balance(double)
 * - 构造方法：BankAccount(String owner, double initialBalance)
 * - 方法：deposit(double amount), withdraw(double amount) 返回 boolean（余额不足返回 false）
 * - 方法：transfer(BankAccount target, double amount) 返回 boolean
 * - 重写 toString()：返回 "owner: $balance"
 *
 * 考察：类定义、构造方法、方法、toString
 */
public class Ex02_BankAccount {
    // TODO: 定义 BankAccount 类

    public static void main(String[] args) {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob   = new BankAccount("Bob", 500);

        alice.transfer(bob, 200);
        System.out.println(alice); // 期望: Alice: $800.00
        System.out.println(bob);   // 期望: Bob: $700.00

        boolean ok = bob.withdraw(9999);
        System.out.println(ok);    // 期望: false
    }
}
