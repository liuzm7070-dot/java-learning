# Java 大小写规则 — 一条就够了

## 规则：看它是什么，不看在哪儿

| 是什么 | 大小写 | 例子 |
|--------|--------|------|
| **类名** | 大写开头 (PascalCase) | `BankAccount`, `String`, `ArrayList` |
| **接口名** | 大写开头 | `Runnable`, `Shape`, `Comparable` |
| **枚举名** | 大写开头 | `Day`, `Color` |
| **变量** | 小写开头 (camelCase) | `name`, `balance`, `amount` |
| **方法** | 小写开头 | `deposit()`, `withdraw()`, `toString()` |
| **参数** | 小写开头 | `int amount`, `String name` |
| **包名** | 全小写 | `com.example.banking` |
| **常量** (static final) | 全大写 + 下划线 | `MAX_SIZE`, `DEFAULT_NAME` |

## 代码里一眼辨认

```java
public class BankAccount {       // 大写 → 类
    private String name;         // 小写 → 字段
    private int balance;         // 小写 → 字段

    public void deposit(int amount) {  // 小写 deposit(方法), 小写 amount(参数)
        int newBalance = this.balance + amount;  // 小写 → 局部变量
    }
}
```

> `String name` — `String` 大写（它是类），`name` 小写（它是变量）。同一行各自遵守各自规则。

## 典型错误对照

```java
// ❌
BankAccount(String Name, int Save)            // 参数写成大写
public void transfer(BankAccount Reciver, int Nums)  // 参数大写 + 拼写错误

// ✅
BankAccount(String name, int initialBalance)  // 参数小写
public void transfer(BankAccount receiver, int amount) // 参数小写
```

## 一句话

> 是类/接口 → 大写开头；不是 → 小写开头。常量例外，全大写。
