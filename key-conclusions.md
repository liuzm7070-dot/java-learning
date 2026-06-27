# Java 核心结论速记

> 本次对话中逐步搞清的 Java 底层逻辑

---

## 1. 接口不是类，是「合同」

| 概念 | 关键字 | 能否 new | 有构造方法 | 能多继承 |
|------|--------|----------|-----------|---------|
| 类 | `class` | ✅ | ✅ | 只能 1 个 |
| 抽象类 | `abstract class` | ❌ | ✅ | 只能 1 个 |
| 接口 | `interface` | ❌ | ❌ | ✅ 无数个 |

```java
interface Shape { double area(); }          // 只定义合同，不实现
class Circle implements Shape { ... }       // 履行合同
class Dog extends Animal implements Pet, Runnable { }  // 多接口突破单继承限制
```

> 接口的唯二用途：① 定义方法合同 ② 让一个类拥有多种「身份」（打破单继承限制）

---

## 2. 操作数据靠外部工具类，不挂对象上

这是 **Java 的核心设计哲学**：

```java
// 操作全是外部 static 方法
Arrays.toString(arr);       // 打印数组
Arrays.sort(arr);           // 排序
Collections.sort(list);     // 集合排序
Math.abs(x);                // 数学运算

// 同一个操作，在 Python/C++ 里可能挂在对象上
// Python:  arr.sort()
// C++:     sort(begin, end)   自由函数 + 迭代器
```

> Java 禁止运算符重载、拒绝自由函数，所有操作收进 `Arrays` / `Collections` / `Math` 等工具类。

---

## 3. 多态：编译看类型，运行看对象

```java
Shape[] shapes = { new Circle(5), new Rectangle(4, 6) };

// shapes[i] 的类型声明是 Shape（接口名）
// 但堆上存的是真正的 Circle / Rectangle 对象
shapes[0].area();  // → 运行期找到 Circle.area() 执行
shapes[1].area();  // → 运行期找到 Rectangle.area() 执行
```

| 阶段 | 检查什么 |
|------|---------|
| 编译期 | `Shape` 接口有没有 `area()` 的签名 |
| 运行期 | 堆上实际对象的类里找 `area()` 的实现 |

---

## 4. C++ vs Java 设计风格

| | C++ | Java |
|---|---|---|
| 操作在类外 | 自由函数 + 迭代器 | `static` 工具方法 |
| 操作在类内 | 成员函数 + 运算符重载 | 只有成员函数，不重载运算符 |
| 数组打印 | 迭代 | `Arrays.toString()` 强制 |
| 虚函数 | `virtual` 显式声明 | **默认全是虚函数** |
| 多继承 | `class A : B, C` | 单继承 + 多接口 |
| 对象分配 | 栈或堆自由选择 | 全部堆，`new` 必须 |
| 释放 | `delete` | GC 自动 |
| 哲学 | 灵活，多套路 | 统一，一条路 |

---

## 5. 命名：一条规则

| 是什么 | 大小写 | 例子 |
|--------|--------|------|
| 类 / 接口 / 枚举 | **大写开头** | `BankAccount`, `Shape`, `Day` |
| 变量 / 方法 / 参数 | **小写开头** | `name`, `deposit()`, `amount` |
| 常量 (`static final`) | **全大写** | `MAX_SIZE` |
| 包名 | **全小写** | `com.example.banking` |

```java
String name = "Alice";  // String 是类（大写）, name 是变量（小写）
```

---

## 6. 文件与类：铁律

```java
// 文件名: Ex02_BankAccount.java
public class Ex02_BankAccount { }   // 必须与文件名逐字一致
class BankAccount { }               // 非 public 类可以共存任意多个
```

| 规则 | 说明 |
|------|------|
| 一个文件只能一个 `public class` | 类名 = 文件名（包括大小写） |
| 可以有多个非 public 类 | 包级可见，同文件随意 |

---

## 7. toString()：Java 的 __str__

```java
@Override
public String toString() {
    return "你想打印什么";
}
// 之后所有用到字符串的地方自动调用
System.out.println(obj);    // ✅
"前缀" + obj;               // ✅
String.format("%s", obj);   // ✅
```

> 等价于 Python 的 `__str__`。现阶段背下来抄就行，写完整套题自然懂了。

---

## 8. Git 三件套

```bash
git status                           # 看改了啥
git add -A && git commit -m "说明"    # 暂存 + 提交
git push                             # 推到 GitHub
git pull                             # 拉别人/别处的更新
```

> `clone` = 下载整个仓库（含完整历史和源网址）
