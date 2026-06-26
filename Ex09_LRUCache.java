/**
 * 题 9：算法 — LRU Cache
 *
 * 实现一个 LRU（最近最少使用）缓存：
 *   - LRUCache(int capacity)  构造方法
 *   - int get(int key)        获取值，不存在返回 -1；访问算「使用」
 *   - void put(int key, int value)  插入/更新；容量满时淘汰最久未使用的
 *
 * 要求：get 和 put 都是 O(1) 时间复杂度。
 *
 * 提示：HashMap + 手写双向链表（不要用 LinkedHashMap 偷懒）。
 *
 * 考察：HashMap、双向链表、数据结构设计、算法
 */
import java.util.*;

public class Ex09_LRUCache {
    // TODO: 定义 Node 内部类（key, value, prev, next）
    // TODO: 实现 LRUCache

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 期望: 1
        cache.put(3, 3);                  // 淘汰 key=2
        System.out.println(cache.get(2)); // 期望: -1
        cache.put(4, 4);                  // 淘汰 key=1
        System.out.println(cache.get(1)); // 期望: -1
        System.out.println(cache.get(3)); // 期望: 3
        System.out.println(cache.get(4)); // 期望: 4
    }
}

class LRUCache {
    // TODO: HashMap + 双向链表，实现 get/put O(1)
    public LRUCache(int capacity) {}
    public int get(int key) { return -1; }
    public void put(int key, int value) {}
}
