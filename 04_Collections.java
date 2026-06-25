/**
 * 题 4：集合框架 — ArrayList + HashMap
 *
 * 统计一篇英文文章中每个单词的出现次数（忽略大小写、去掉标点）。
 * 写方法：
 *   public static Map<String, Integer> wordCount(String text)
 * 返回按出现次数降序排列的结果（用 LinkedHashMap 保持顺序）。
 *
 * 考察：HashMap、ArrayList、String 操作、排序
 */
import java.util.*;

public class Ex04_WordCount {
    public static Map<String, Integer> wordCount(String text) {
        // TODO
    }

    public static void main(String[] args) {
        String text = "Hello world, hello Java. Java is great; world says hello!";
        Map<String, Integer> result = wordCount(text);
        for (var entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        // 期望输出顺序: hello: 3, world: 2, java: 2, ...
    }
}
