/**
 * 题 10：并发 — 多线程生产者-消费者
 *
 * 实现一个阻塞队列 BlockingQueue<T>：
 *   - void put(T item)  放入元素，队列满时阻塞等待
 *   - T take()          取出元素，队列空时阻塞等待
 *   - int size()        返回当前元素个数
 *
 * 用 wait() / notifyAll() 实现（不要用 java.util.concurrent）。
 *
 * 写 main 测试：
 *   - 3 个生产者线程，各生产 5 个随机数
 *   - 2 个消费者线程，消费到队列为空且所有生产者完成时退出
 *   - 队列容量为 5
 *
 * 考察：synchronized、wait/notify、线程创建、并发编程基础
 */
import java.util.*;

public class Ex10_BlockingQueue {
    // TODO: 实现 BlockingQueue<T>

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        // 3 producers
        for (int i = 0; i < 3; i++) {
            final int pid = i;
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    int val = new Random().nextInt(100);
                    queue.put(val);
                    System.out.printf("[P%d] produced %d (size=%d)%n", pid, val, queue.size());
                    try { Thread.sleep(50); } catch (InterruptedException e) {}
                }
            }).start();
        }

        // 2 consumers
        for (int i = 0; i < 2; i++) {
            final int cid = i;
            new Thread(() -> {
                while (true) {
                    Integer val = queue.take();
                    System.out.printf("  [C%d] consumed %d (size=%d)%n", cid, val, queue.size());
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
            }).start();
        }
    }
}

class BlockingQueue<T> {
    // TODO: synchronized + wait/notifyAll 实现阻塞 put/take
    public BlockingQueue(int capacity) {}
    public synchronized void put(T item) {}
    public synchronized T take() { return null; }
    public synchronized int size() { return 0; }
}
