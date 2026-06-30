# Java 数据结构互转速查

## 一、String ↔ 其他

```
String ──→ int         Integer.parseInt("123")
String ──→ double      Double.parseDouble("3.14")
String ──→ char[]      s.toCharArray()
String ──→ List        Arrays.asList(s.split(" "))
String ──→ Set         new HashSet<>(Arrays.asList(s.split(" ")))

int ──→ String         String.valueOf(100)  或  Integer.toString(100)
double ──→ String      String.valueOf(3.14)
char[] ──→ String      new String(chars)
StringBuilder ──→ String   sb.toString()
```

```java
// 基础类型 ↔ String
int n = Integer.parseInt("123");
String s = String.valueOf(100);
```

---

## 二、数组 ↔ 集合

```
数组 ──→ List          Arrays.asList(arr)              // 固定大小
数组 ──→ List(可变)    new ArrayList<>(Arrays.asList(arr))
数组 ──→ Set           new HashSet<>(Arrays.asList(arr))

List ──→ 数组          list.toArray(new String[0])
List ──→ int[]         list.stream().mapToInt(i->i).toArray()

Set ──→ 数组           set.toArray(new String[0])
```

```java
// 数组 → List
String[] arr = {"a", "b", "c"};
List<String> list = Arrays.asList(arr);              // 固定大小，不能 add/remove
List<String> list2 = new ArrayList<>(Arrays.asList(arr)); // 可变

// List → 数组
String[] back = list.toArray(new String[0]);

// 去重：数组 → Set
Set<String> set = new HashSet<>(Arrays.asList(arr));

// Set → 数组
String[] deduped = set.toArray(new String[0]);
```

---

## 三、List ↔ Set

```
List ──→ Set           new HashSet<>(list)           // 去重，丢顺序
Set ──→ List           new ArrayList<>(set)          // 恢复有序（但不是原序）
```

```java
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>(list);          // list → set
List<String> back = new ArrayList<>(set);      // set → list
```

---

## 四、Map 与 List/Set 互转

```
Map ──→ List<Key>      new ArrayList<>(map.keySet())
Map ──→ List<Value>    new ArrayList<>(map.values())
Map ──→ List<Entry>    new ArrayList<>(map.entrySet())
Map ──→ Set<Key>       map.keySet()                    // 本来就是 Set
Map ──→ Set<Entry>     map.entrySet()                  // 本来就是 Set

List<Entry> ──→ Map(有序)   遍历塞入 LinkedHashMap
```

```java
Map<String, Integer> map = new HashMap<>();

// Map → 各种视图
Set<String> keys = map.keySet();                       // 视图（直接反映变化）
List<String> keyList = new ArrayList<>(map.keySet());  // 独立副本
List<Integer> values = new ArrayList<>(map.values());
List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

// Entry 列表 → LinkedHashMap（保持排序结果）
Map<String, Integer> sorted = new LinkedHashMap<>();
for (var e : entries) {
    sorted.put(e.getKey(), e.getValue());
}
```

---

## 五、String ↔ StringBuilder ↔ char[]

```java
String s = "hello";

// String → StringBuilder
StringBuilder sb = new StringBuilder(s);

// StringBuilder → String
String back = sb.toString();

// String → char[]
char[] chars = s.toCharArray();

// char[] → String
String s2 = new String(chars);

// char[] 部分 → String
String s3 = new String(chars, 0, 3);    // 前 3 个字符
```

---

## 六、int[] ↔ Integer[] 互转

```java
// int[] → Integer[]
int[] nums = {1, 2, 3};
Integer[] boxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);

// Integer[] → int[]
Integer[] objs = {1, 2, 3};
int[] unboxed = Arrays.stream(objs).mapToInt(Integer::intValue).toArray();
```

---

## 七、List<Integer> ↔ int[]

```java
// List<Integer> → int[]
List<Integer> list = Arrays.asList(1, 2, 3);
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

// int[] → List<Integer>
int[] nums = {1, 2, 3};
List<Integer> list2 = Arrays.stream(nums).boxed().collect(Collectors.toList());
```

---

## 八、打印各种数据类型

```java
// 数组 → 必须 Arrays.toString
System.out.println(Arrays.toString(arr));           // [1, 2, 3]
System.out.println(Arrays.deepToString(matrix));    // [[1,2],[3,4]]

// 集合 → 直接打印
System.out.println(list);   // [a, b, c]
System.out.println(map);    // {hello=3, java=2}
System.out.println(set);    // [a, b, c]

// Map Entry → 打印
map.forEach((k, v) -> System.out.println(k + ": " + v));
```

---

## 九、构造方法接受什么

| 目标 | 接受 | 例子 |
|------|------|------|
| `new ArrayList<>(...)` | 任意 Collection 或空 | `new ArrayList<>(set)` |
| `new HashSet<>(...)` | 任意 Collection 或空 | `new HashSet<>(list)` |
| `new LinkedHashMap<>()` | 空 | `new LinkedHashMap<>()` |
| `new HashMap<>(...)` | 另一个 Map | `new HashMap<>(existingMap)` |
| `new String(...)` | char[] 或 String | `new String(chars)` |
| `new StringBuilder(...)` | String | `new StringBuilder("hello")` |

---

## 十、一张速查表

| 从 | 到 | 方法 |
|----|----|------|
| `String` | `int` | `Integer.parseInt(s)` |
| `int` | `String` | `String.valueOf(n)` |
| `String` | `char[]` | `s.toCharArray()` |
| `char[]` | `String` | `new String(chars)` |
| `String[]` | `List<String>` | `Arrays.asList(arr)` |
| `String[]` | `Set<String>` | `new HashSet<>(Arrays.asList(arr))` |
| `List<T>` | `T[]` | `list.toArray(new T[0])` |
| `List<T>` | `Set<T>` | `new HashSet<>(list)` |
| `Set<T>` | `List<T>` | `new ArrayList<>(set)` |
| `Map<K,V>` | `List<K>` | `new ArrayList<>(map.keySet())` |
| `Map<K,V>` | `List<V>` | `new ArrayList<>(map.values())` |
| `Map<K,V>` | `List<Entry>` | `new ArrayList<>(map.entrySet())` |
| `List<Entry>` | `LinkedHashMap` | 遍历塞入 |
| `int[]` | `Integer[]` | `Arrays.stream(arr).boxed()...` |
| `Integer[]` | `int[]` | `Arrays.stream(arr).mapToInt()...` |
| `StringBuilder` | `String` | `sb.toString()` |
| `String` | `StringBuilder` | `new StringBuilder(s)` |
