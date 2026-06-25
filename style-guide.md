# Java 代码风格 — 从 C++/Python 迁移者必读

## 一、命名规范（最高频错误）

| 元素 | 规则 | 正确 | 错误 |
|------|------|------|------|
| 类名 | **PascalCase**（大驼峰） | `BankAccount` | `bankAccount`, `bank_account` |
| 方法/变量 | **camelCase**（小驼峰） | `getBalance()`, `preSum` | `GetBalance()`, `pre_sum` |
| 常量 | **UPPER_SNAKE** | `MAX_SIZE`, `DEFAULT_PORT` | `maxSize`, `max_size` |
| 包名 | **全小写** | `com.example.utils` | `com.example.Utils` |

```java
// ❌ C++/Python 风格混用
public class bank_account {
    int max_value = 100;
    public void PrintInfo() {}
}

// ✅ Java 风格
public class BankAccount {
    static final int MAX_VALUE = 100;
    int maxValue = 100;
    public void printInfo() {}
}
```

## 二、不要滥用 var

```java
// ❌ 看不出类型
var x = getSomething();      // 返回什么？读代码的人不知道
var n = list.size();         // int 还是 long？

// ✅ 类型明确时可以用
var account = new BankAccount("Alice", 1000);  // 右边已经写明了
var map = new HashMap<String, List<Integer>>(); // 避免重复复杂泛型

// ✅ 简单类型直接写，别用 var
int count = 10;
String name = "hello";
double price = 99.9;
```

> 原则：右边有 `new` 且类型明确 → 可以用 var；否则写清楚。

## 三、import 不要整包导入

```java
// ❌ 导入整个包
import java.util.*;

// ✅ 只导入用的类
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
```

IDEA 自动用 `*` 的阈值默认是 5（同包导入超过 5 个会合并成 `*`），可以调大或用快捷键 `Ctrl+Alt+O` 整理。

## 四、大括号不另起一行

```java
// ❌ C# 风格
public void foo()
{
    if (x > 0)
    {
        // ...
    }
}

// ✅ Java 标准（K&R 风格，大括号跟在语句后）
public void foo() {
    if (x > 0) {
        // ...
    }
}
```

## 五、字段用 private + getter

C++ 有 `public:`/`private:` 分段，Java 是**逐个标记**：

```java
// ❌ 直接暴露
public class Person {
    public int age;        // 破坏封装
    public String name;
}

// ✅ 字段私有 + 访问方法
public class Person {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getName() { return name; }
}
```

> 写算法题时 `public` 字段没问题；做项目必须封装。

## 六、方法名要动词开头

```java
// ❌ 名词当方法名
public int[] runningSum(int[] nums) {}   // 可以，但不够好
public static Shape largestShape() {}    // 名词

// ✅ 方法名说清楚做什么
public int[] computeRunningSum(int[] nums) {}
public static Shape findLargestShape() {}
// 或者经典命名
// getXxx() → 返回属性
// isXxx()  → 返回 boolean
// computeXxx() / calculateXxx() → 计算
// toXxx()  → 转换
```

## 七、条件判断和循环不要省大括号

```java
// ❌ 这条在 C++ 里常见，Java 禁止
if (x > 0)
    doSomething();      // 单行也不能省大括号

// ✅ 必须包起来
if (x > 0) {
    doSomething();
}
```

## 八、String 比较用 equals，不用 ==

```java
// ❌ Python/C++ 习惯
String name = "Alice";
if (name == "Alice") { ... }     // 比较的是引用地址！

// ✅ Java 专用
if (name.equals("Alice")) { ... }
if ("Alice".equals(name)) { ... }  // 更安全：避免 name 为 null 时报错
```

> `==` 比较栈上的值（引用地址），`equals` 比较堆上的内容。

## 九、接口和实现的区分

```java
// ❌ 直接用具体类型
ArrayList<String> list = new ArrayList<>();
HashMap<String, Integer> map = new HashMap<>();

// ✅ 用接口类型声明，实现类只在 new 的时候出现
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
```

> 好处：以后换实现（`ArrayList` → `LinkedList`）只改一处。

## 十、for 循环习惯性写法

```java
// ❌ 用 x 做循环变量
for (int x = 0; x < n; x++) {}

// ✅ Java 惯用 i/j/k
for (int i = 0; i < n; i++) {}
```

## 十一、空行不是随意加

```java
// ✅ 一个方法内按逻辑分块，最多空一行
public void process() {
    // 输入验证
    if (input == null) return;

    // 核心逻辑
    int result = compute(input);

    // 输出
    System.out.println(result);
}
```

## 十二、不要写无用的代码

```java
// ❌ 没用的一行
int[] res = new int[nums.length];
return res;

// ❌ 多余的中间变量
int length = nums.length;              // nums.length 本身就是 O(1)
for (int i = 0; i < length; i++) {}   // 直接写 nums.length 即可
```

## 十三、工具类 vs 普通类

```java
// 全是 static 方法的类 → 工具类，私有化构造方法
public class MathUtils {
    private MathUtils() {}  // 禁止实例化

    public static int add(int a, int b) { return a + b; }
}
```

## 十四、JavaDoc 注释

```java
/**
 * 计算数组的前缀和。
 *
 * @param nums 输入整数数组
 * @return 前缀和数组，result[i] = nums[0] + ... + nums[i]
 * @throws IllegalArgumentException 如果数组为空
 */
public static int[] computeRunningSum(int[] nums) {
    // ...
}
```

## 十五、检查清单

写完一个类后自查：

- [ ] 类名 PascalCase？
- [ ] 方法名 camelCase + 动词开头？
- [ ] 字段是 private？
- [ ] import 没用 `*`？
- [ ] String 比较用 `equals` 而非 `==`？
- [ ] 声明用接口类型（`List`）而非实现类（`ArrayList`）？
- [ ] 大括号跟在语句后，不另起一行？
- [ ] if/for 哪怕单行也包大括号？
- [ ] 循环变量用 `i` 而非 `x`？
- [ ] 没有多余的中间变量？
