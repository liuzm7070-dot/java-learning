# Java 快速入门 — 10 道练习

## 学习路线

| 题目 | 文件 | 主题 | 对应知识点 |
|------|------|------|-----------|
| 1 | `01_Basics.java` | 前缀和 | 数组、静态方法、for 循环 |
| 2 | `02_OOP.java` | 银行账户 | 类、构造方法、方法、toString |
| 3 | `03_Interface.java` | 形状接口 | interface、implements、多态 |
| 4 | `04_Collections.java` | 单词统计 | HashMap、ArrayList、排序 |
| 5 | `05_Exception.java` | 安全除法 | try-catch、异常类型 |
| 6 | `06_FileIO.java` | 文件行反转 | Files API、I/O 流 |
| 7 | `07_StreamAPI.java` | Stream 筛选 | filter/map/sorted/collect |
| 8 | `08_Project_TodoList.java` | 命令行 Todo | 综合实战（Scanner、JSON、CRUD） |
| 9 | `09_Algorithm_LRU.java` | LRU 缓存 | HashMap + 双向链表 |
| 10 | `10_Concurrency.java` | 阻塞队列 | Thread、synchronized、wait/notify |

## 用法

```bash
# 逐题练习
javac Ex01_RunningSum.java && java Ex01_RunningSum
javac Ex02_BankAccount.java   && java Ex02_BankAccount
# ... 以此类推

# 查看参考解答（题 1-10 全部运行）
javac Solutions.java && java Solutions
```

## 建议学习节奏

- **Day 1**：题 1-3（基础语法 + OOP）
- **Day 2**：题 4-7（集合 + 异常 + I/O + Stream）
- **Day 3**：题 8（综合项目，手动实现）
- **Day 4**：题 9-10（算法 + 并发）

## 关键记忆点

1. **没有 delete，没有析构** — GC 全权管理
2. **默认就是虚函数** — 非 static 方法自动动态绑定
3. **方法签名强类型** — 返回值、参数类型一个不能省
4. **synchronized 块** — Java 的互斥锁语法
5. **Stream API** — 就是 Python 的 map/filter/lambda
