# Java 类规则速成 — C++ / Python 对照

## 一、Java 类的「铁律」：一套文件规则

### 文件名 = 类名（严格一致，包括大小写）

```java
// 文件必须叫 BankAccount.java
public class BankAccount { }
//        ↑ 跟文件名逐字相同
```

**一个文件只能有一个 public class**，可以有多个非 public 的。

### 一个源文件里全是类，没有顶层函数

```java
// ❌ Python/C++ 思维
void helper() { }                    // 不行！函数必须在类里

// ✅ Java 必须
public class MathUtils {
    public static void helper() { }  // static 方法 = C++ 普通函数
}
```

## 二、构造方法：没有析构，this 是点号

```java
public class Person {
    private String name;
    private int age;

    // 构造方法 = 类名同名，无返回类型
    public Person(String name, int age) {
        this.name = name;    // this. 不是 this->
        this.age = age;
    }

    // 可以重载多个构造方法
    public Person(String name) {
        this(name, 0);       // this() 调用另一个构造方法
    }

    // ❌ 没有 ~Person()  ——  GC 自动回收
}

// 调用
Person p = new Person("Alice", 25);  // new 必须在堆上，没有栈对象
```

> **没有栈对象**：`Person p` 只是引用，`new Person()` 才创建对象。这点跟 C++ 根本不同。

## 三、访问修饰符：每个成员都要写

```java
public class Demo {
    public    int a;   // 任何地方可见
    private   int b;   // 只有本类可见
    protected int c;   // 本类 + 同包 + 子类可见
              int d;   // (default) 本类 + 同包可见  ← 没有关键字！
}
```

C++ 是 `public:` / `private:` **分段**，Java 是**逐成员标注**。漏写就是 `default`（包级可见），不是 private。

| 修饰符 | 本类 | 同包 | 子类 | 任何地方 |
|--------|------|------|------|----------|
| `private` | ✅ | ❌ | ❌ | ❌ |
| `default`(不写) | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

## 四、继承：单继承 + 多接口

```java
// C++:  class Dog : public Animal { };
// Java:
public class Dog extends Animal { }           // 只能继承一个类

// C++:  class Dog : public Animal, public Pet { };  ← 多继承
// Java: 用 interface 弥补
public class Dog extends Animal implements Pet, Runnable {
    // 实现多个接口    ↑
}
```

### super = 调用父类

```java
public class Dog extends Animal {
    public Dog(String name) {
        super(name);   // 调用父类构造方法，必须放第一行
    }

    @Override
    public void speak() {
        super.speak(); // 调用父类方法
        System.out.println("Woof!");
    }
}
```

## 五、所有非 static 方法默认是「虚函数」

```java
public class Animal {
    public void speak() { ... }    // 自动支持重写！
    //     ↑ 不需要 virtual 关键字
}

public class Dog extends Animal {
    @Override
    public void speak() { ... }    // @Override 是可选的，但强烈建议写
}
// 动态绑定自动发生，不需要指针/引用语法
Animal a = new Dog();
a.speak();  // 调用 Dog.speak()，自动走虚表
```

> `@Override` 注解让编译器帮你检查是否真的重写了，写错了方法签名立刻报错。

## 六、static = 属于类，不属于实例

```java
public class Counter {
    private static int count = 0;      // 类变量（全局唯一）
    private int id;                     // 实例变量（每个对象一份）

    public Counter() {
        id = ++count;
    }

    public static int getCount() {     // 类方法
        return count;
        // 不能访问 this.id ！ 静态方法里没有 this
    }
}

// 调用
Counter.getCount();            // static 方法：类名.方法
new Counter().getId();         // 实例方法：对象.方法
```

## 七、final = 不可改变（三种用法）

```java
// 1. final 变量 → 常量
final int MAX = 100;

// 2. final 方法 → 禁止子类重写
public final void criticalMethod() { }

// 3. final 类 → 禁止继承
public final class String { }   // String 就是 final 的
```

## 八、抽象类 vs 接口 → 一张表

| | abstract class | interface |
|---|---|---|
| 关键字 | `abstract class` | `interface` |
| 继承数量 | 只能 extend 1 个 | 可以 implement 多个 |
| 构造方法 | 有 | 没有 |
| 字段 | 可以有实例字段 | 只能有 `public static final` 常量 |
| 方法实现 | 可以有实现了的方法 | Java 8+ 可以用 `default` 写实现 |
| 何时用 | "是一种" + 有共享状态 | "能做什么" |

```java
// 接口：定义契约
public interface Flyable {
    void fly();                     // 默认是 public abstract
    default void land() {           // Java 8+: 默认实现
        System.out.println("Landing...");
    }
}

// 抽象类：有共享字段/方法体
public abstract class Vehicle {
    protected int speed;            // 可以有字段
    public abstract void move();    // 强制子类实现
    public void stop() {            // 提供默认实现
        speed = 0;
    }
}
```

## 九、内部类：类里套类

```java
public class Outer {
    private int x = 10;

    class Inner {              // 成员内部类
        void print() {
            System.out.println(x);  // 直接访问 Outer 的 private 成员
        }
    }

    static class StaticInner {  // 静态内部类 → 不能访问 Outer 的实例字段
        // ...
    }
}

// 用法
Outer.Inner i = new Outer().new Inner();
Outer.StaticInner si = new Outer.StaticInner();
```

> 写算法题/小项目基本用不到内部类，知道有这个东西就行。

## 十、C++ → Java 总结对照

| 概念 | C++ | Java |
|------|-----|------|
| 栈上对象 | `Person p;` | ❌ 没有，必须 `new` |
| 堆上对象 | `Person* p = new Person();` | `Person p = new Person();` |
| 释放 | `delete p;` | ❌ GC 自动 |
| 虚函数 | `virtual void f();` | 默认虚，`final` 禁止重写 |
| 纯虚函数 | `= 0;` | `abstract` |
| 多继承 | `class A : public B, public C` | `extends B implements C` |
| 访问分段 | `public:` / `private:` 分段 | 逐成员标注 |
| 友元 | `friend` | ❌ 没有 |
| 运算符重载 | 有 | ❌ 没有（只有 String 的 + 特殊） |
| 析构函数 | `~ClassName()` | ❌ GC 管理 |
| 头文件 | `.h` + `.cpp` | 只有 `.java` |
| 成员访问 | `p->x` / `p.x` | 全是 `p.x`，没有 -> |
| 命名空间 | `namespace X {}` | `package x.y;` |

## 十一、一个「规范」的类长这样子

```java
package com.example.banking;             // 1. 包声明

import java.util.Objects;                 // 2. import

/**
 * 银行账户类。                         // 3. JavaDoc
 */
public class BankAccount {               // 4. 类声明
    // ---- 静态常量 ----
    private static final double MIN_BALANCE = 0.0;

    // ---- 实例字段（全部 private）----
    private final String owner;           // final: 创建后不可改
    private double balance;

    // ---- 构造方法 ----
    public BankAccount(String owner, double initialBalance) {
        this.owner = Objects.requireNonNull(owner);   // 防御性 null 检查
        this.balance = Math.max(initialBalance, MIN_BALANCE);
    }

    // 第二个构造方法（提供默认值）
    public BankAccount(String owner) {
        this(owner, 0);                   // 委托给上面的构造方法
    }

    // ---- getter / setter ----
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
    // owner 没有 setter  —— 不可变字段

    // ---- 业务方法 ----
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("金额必须大于 0");
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    // ---- Object 方法重写 ----
    @Override
    public String toString() {
        return owner + ": $" + String.format("%.2f", balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return Double.compare(that.balance, balance) == 0
            && owner.equals(that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, balance);
    }
}
```

> 把上面这个模板看懂，Java 类的规则就掌握了 80%。
