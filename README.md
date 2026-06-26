# Java 快速入门 — 10 道练习

## 学习路线

| 题目 | 文件 | 主题 | 对应知识点 |
|------|------|------|-----------|
| 1 | `Ex01_RunningSum.java` | 前缀和 | 数组、静态方法、for 循环 |
| 2 | `Ex02_BankAccount.java` | 银行账户 | 类、构造方法、方法、toString |
| 3 | `Ex03_ShapeInterface.java` | 形状接口 | interface、implements、多态 |
| 4 | `Ex04_WordCount.java` | 单词统计 | HashMap、ArrayList、排序 |
| 5 | `Ex05_SafeDivide.java` | 安全除法 | try-catch、异常类型 |
| 6 | `Ex06_FileReverse.java` | 文件行反转 | Files API、I/O 流 |
| 7 | `Ex07_StreamFilter.java` | Stream 筛选 | filter/map/sorted/collect |
| 8 | `Ex08_TodoList.java` | 命令行 Todo | 综合实战（Scanner、JSON、CRUD） |
| 9 | `Ex09_LRUCache.java` | LRU 缓存 | HashMap + 双向链表 |
| 10 | `Ex10_BlockingQueue.java` | 阻塞队列 | Thread、synchronized、wait/notify |

## 用法

```bash
# 全部编译（已有占位代码，可编译通过）
javac *.java

# 逐题练习（编译 + 运行）
javac Ex01_RunningSum.java && java Ex01_RunningSum
javac Ex02_BankAccount.java   && java Ex02_BankAccount
# ... 以此类推

# 查看参考解答
cd solutions && javac Solutions.java && java Solutions
```

## 建议学习节奏

- **Day 1**：题 1-3（基础语法 + OOP）
- **Day 2**：题 4-7（集合 + 异常 + I/O + Stream）
- **Day 3**：题 8（综合项目，手动实现）
- **Day 4**：题 9-10（算法 + 并发）

## 参考文件

- `naming-rules.md` — 大小写规则速记
- `style-guide.md` — 15 条 Java 代码风格规则
- `class-rules.md` — 类规则速成（C++ 对照）
- `solutions/Solutions.java` — 全部参考解答
