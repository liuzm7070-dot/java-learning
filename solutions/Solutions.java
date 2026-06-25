/**
 * ============================================
 * 十道 Java 练习 — 参考解答
 * ============================================
 *
 * 每个解答自成一节。编译运行：
 *   javac Solutions.java && java Solutions
 * 然后选择题目编号查看运行结果。
 */

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

// ============================================================
// 题1：前缀和
// ============================================================
class Sol01 {
    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result[i] = sum;
        }
        return result;
    }

    static void demo() {
        int[] r = runningSum(new int[]{1, 2, 3, 4});
        System.out.println("题1: " + Arrays.toString(r)); // [1, 3, 6, 10]
    }
}

// ============================================================
// 题2：BankAccount
// ============================================================
class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount target, double amount) {
        if (!this.withdraw(amount)) return false;
        target.deposit(amount);
        return true;
    }

    @Override
    public String toString() {
        return owner + ": $" + String.format("%.2f", balance);
    }
}

class Sol02 {
    static void demo() {
        BankAccount alice = new BankAccount("Alice", 1000);
        BankAccount bob   = new BankAccount("Bob", 500);
        alice.transfer(bob, 200);
        System.out.println("题2:");
        System.out.println(alice); // Alice: $800.00
        System.out.println(bob);   // Bob: $700.00
        System.out.println(bob.withdraw(9999)); // false
    }
}

// ============================================================
// 题3：Shape 接口
// ============================================================
interface Shape {
    double area();
}

class Circle implements Shape {
    private double radius;
    Circle(double r) { this.radius = r; }
    public double area() { return Math.PI * radius * radius; }
}

class Rectangle implements Shape {
    private double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    public double area() { return w * h; }
}

class Sol03 {
    static Shape largestShape(Shape[] shapes) {
        Shape largest = shapes[0];
        for (Shape s : shapes) {
            if (s.area() > largest.area()) largest = s;
        }
        return largest;
    }

    static void demo() {
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Circle(3) };
        Shape largest = largestShape(shapes);
        System.out.println("题3: Largest area = " + String.format("%.2f", largest.area()));
    }
}

// ============================================================
// 题4：单词统计
// ============================================================
class Sol04 {
    static Map<String, Integer> wordCount(String text) {
        // 去标点 + 统一小写
        String[] words = text.toLowerCase().replaceAll("[^a-z0-9\\s]", "").split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (!w.isEmpty()) map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // 按次数降序排列
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<String, Integer> sorted = new LinkedHashMap<>();
        for (var e : list) sorted.put(e.getKey(), e.getValue());
        return sorted;
    }

    static void demo() {
        String text = "Hello world, hello Java. Java is great; world says hello!";
        System.out.println("题4:");
        for (var e : wordCount(text).entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue());
        }
    }
}

// ============================================================
// 题5：安全除法
// ============================================================
class Sol05 {
    static int safeDivide(String a, String b) {
        try {
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            return x / y;
        } catch (NumberFormatException e) {
            System.out.println("错误: 输入不是有效数字 — " + e.getMessage());
            return -1;
        } catch (ArithmeticException e) {
            System.out.println("错误: 除数不能为零");
            return -1;
        }
    }

    static void demo() {
        System.out.println("题5:");
        System.out.println("10/2 = " + safeDivide("10", "2"));
        System.out.println("10/0 = " + safeDivide("10", "0"));
        System.out.println("10/abc = " + safeDivide("10", "abc"));
    }
}

// ============================================================
// 题6：文件行反转
// ============================================================
class Sol06 {
    static void reverseLines(String inputPath, String outputPath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(inputPath));
        List<String> reversed = new ArrayList<>();
        for (String line : lines) {
            reversed.add(new StringBuilder(line).reverse().toString());
        }
        Files.write(Path.of(outputPath), reversed);
    }

    static void demo() {
        System.out.println("题6:");
        try {
            Files.write(Path.of("input.txt"),
                Arrays.asList("Hello World", "Java is fun", "Keep coding"));
            reverseLines("input.txt", "output.txt");
            Files.lines(Path.of("output.txt")).forEach(s -> System.out.println("  " + s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// ============================================================
// 题7：Stream API
// ============================================================
record Person(String name, int age, int salary) {}

class Sol07 {
    static List<String> highEarners(List<Person> people) {
        return people.stream()
            .filter(p -> p.age() > 25)
            .sorted((a, b) -> Integer.compare(b.salary(), a.salary()))
            .map(p -> p.name().toUpperCase())
            .collect(Collectors.toList());
    }

    static void demo() {
        List<Person> people = Arrays.asList(
            new Person("alice", 30, 80000),
            new Person("bob", 22, 50000),
            new Person("charlie", 28, 120000),
            new Person("diana", 35, 95000)
        );
        System.out.println("题7: " + highEarners(people));
    }
}

// ============================================================
// 题8：Todo List
// ============================================================
class Sol08 {
    static final String FILE = "todo.json";
    static List<TodoItem> items = new ArrayList<>();
    static int nextId = 1;

    static class TodoItem {
        int id; String desc; boolean done;
        TodoItem(int id, String desc, boolean done) {
            this.id = id; this.desc = desc; this.done = done;
        }
        String toJson() {
            return String.format("{\"id\":%d,\"desc\":\"%s\",\"done\":%b}",
                id, desc.replace("\"", "\\\""), done);
        }
        @Override
        public String toString() {
            return String.format("[%s] %d. %s", done ? "✓" : " ", id, desc);
        }
    }

    static void load() {
        try {
            String raw = Files.readString(Path.of(FILE)).trim();
            if (raw.isEmpty() || !raw.startsWith("[")) return;
            // 简易 JSON 数组解析（不依赖第三方库）
            raw = raw.substring(1, raw.lastIndexOf(']'));
            if (raw.trim().isEmpty()) return;
            for (String part : raw.split("\\},\\{")) {
                part = part.replace("{", "").replace("}", "");
                int id = 0; String desc = ""; boolean done = false;
                for (String kv : part.split(",")) {
                    String[] pair = kv.split(":", 2);
                    String key = pair[0].replace("\"", "").trim();
                    String val = pair[1].replace("\"", "").trim();
                    if (key.equals("id")) id = Integer.parseInt(val);
                    else if (key.equals("desc")) desc = val;
                    else if (key.equals("done")) done = Boolean.parseBoolean(val);
                }
                items.add(new TodoItem(id, desc, done));
                if (id >= nextId) nextId = id + 1;
            }
        } catch (IOException e) {
            // 文件不存在，忽略
        }
    }

    static void save() {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append("  ").append(items.get(i).toJson());
            if (i < items.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n");
        try {
            Files.writeString(Path.of(FILE), sb.toString());
        } catch (IOException e) {
            System.err.println("保存失败: " + e.getMessage());
        }
    }

    static void demo() {
        load();
        Scanner scanner = new Scanner(System.in);
        System.out.println("题8: Todo List — 命令: add / list / done / delete / exit");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+", 2);
            String cmd = parts[0].toLowerCase();

            try {
                switch (cmd) {
                    case "add" -> {
                        String desc = parts.length > 1 ? parts[1] : "";
                        if (desc.isEmpty()) { System.out.println("用法: add <描述>"); break; }
                        items.add(new TodoItem(nextId++, desc, false));
                        save();
                        System.out.println("已添加 #" + (nextId - 1));
                    }
                    case "list" -> {
                        if (items.isEmpty()) { System.out.println("(空)"); break; }
                        items.forEach(System.out::println);
                    }
                    case "done" -> {
                        if (parts.length < 2) { System.out.println("用法: done <编号>"); break; }
                        int id = Integer.parseInt(parts[1]);
                        for (TodoItem t : items) {
                            if (t.id == id) { t.done = true; save(); System.out.println("已标记 #" + id); }
                        }
                    }
                    case "delete" -> {
                        if (parts.length < 2) { System.out.println("用法: delete <编号>"); break; }
                        int id = Integer.parseInt(parts[1]);
                        items.removeIf(t -> t.id == id);
                        save();
                        System.out.println("已删除 #" + id);
                    }
                    case "exit" -> { System.out.println("再见!"); return; }
                    default -> System.out.println("未知命令");
                }
            } catch (NumberFormatException e) {
                System.out.println("编号必须是数字");
            }
        }
    }
}

// ============================================================
// 题9：LRU Cache
// ============================================================
class LRUCache {
    static class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0); // dummy head
    private final Node tail = new Node(0, 0); // dummy tail

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            if (map.size() > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
            }
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}

class Sol09 {
    static void demo() {
        System.out.println("题9: LRU Cache");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1) = " + cache.get(1)); // 1
        cache.put(3, 3);                                 // evicts 2
        System.out.println("get(2) = " + cache.get(2)); // -1
        cache.put(4, 4);                                 // evicts 1
        System.out.println("get(1) = " + cache.get(1)); // -1
        System.out.println("get(3) = " + cache.get(3)); // 3
        System.out.println("get(4) = " + cache.get(4)); // 4
    }
}

// ============================================================
// 题10：阻塞队列
// ============================================================
class BlockingQueue<T> {
    private final T[] queue;
    private int head, tail, count;

    @SuppressWarnings("unchecked")
    BlockingQueue(int capacity) {
        queue = (T[]) new Object[capacity];
    }

    synchronized void put(T item) {
        while (count == queue.length) {
            try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
        }
        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        count++;
        notifyAll();
    }

    synchronized T take() {
        while (count == 0) {
            try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); return null; }
        }
        T item = queue[head];
        head = (head + 1) % queue.length;
        count--;
        notifyAll();
        return item;
    }

    synchronized int size() {
        return count;
    }
}

class Sol10 {
    static volatile boolean producersDone = false;

    static void demo() throws InterruptedException {
        System.out.println("题10: 生产者-消费者 (3P / 2C, 队列容量5)");
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        Thread[] producers = new Thread[3];
        Thread[] consumers = new Thread[2];

        for (int i = 0; i < 3; i++) {
            final int pid = i;
            producers[i] = new Thread(() -> {
                Random rand = new Random();
                for (int j = 0; j < 5; j++) {
                    int val = rand.nextInt(100);
                    queue.put(val);
                    System.out.printf("[P%d] -> %2d  (size=%d)%n", pid, val, queue.size());
                    try { Thread.sleep(rand.nextInt(100)); } catch (InterruptedException e) {}
                }
            });
        }

        for (int i = 0; i < 2; i++) {
            final int cid = i;
            consumers[i] = new Thread(() -> {
                Random rand = new Random();
                while (!producersDone || queue.size() > 0) {
                    Integer val = queue.take();
                    if (val != null) {
                        System.out.printf("  [C%d] <- %2d  (size=%d)%n", cid, val, queue.size());
                    }
                    try { Thread.sleep(rand.nextInt(200)); } catch (InterruptedException e) {}
                }
            });
        }

        for (Thread p : producers) p.start();
        for (Thread c : consumers) c.start();
        for (Thread p : producers) p.join();
        producersDone = true;
        for (Thread c : consumers) c.join();
        System.out.println("题10: 全部完成");
    }
}

// ============================================================
// 主入口
// ============================================================
public class Solutions {
    public static void main(String[] args) throws Exception {
        Sol01.demo(); System.out.println();
        Sol02.demo(); System.out.println();
        Sol03.demo(); System.out.println();
        Sol04.demo(); System.out.println();
        Sol05.demo(); System.out.println();
        Sol06.demo(); System.out.println();
        Sol07.demo(); System.out.println();
        // 题8 是交互式的，想单独测就注释掉 Sol08.demo()
        // Sol08.demo();
        Sol09.demo(); System.out.println();
        Sol10.demo();
    }
}
