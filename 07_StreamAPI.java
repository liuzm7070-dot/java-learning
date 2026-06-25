/**
 * 题 7：Stream API + Lambda
 *
 * 给定一个 List<Person>（name, age, salary），用 Stream API 完成：
 *   1. 找出年龄 > 25 的人
 *   2. 把他们的名字转成大写
 *   3. 按薪资降序排列
 *   4. 收集成一个 List<String> 返回
 *
 * 一行链式调用完成。
 *
 * 考察：Stream、filter、map、sorted、collect、record/class、lambda
 */
import java.util.*;
import java.util.stream.*;

public class Ex07_StreamFilter {
    // Person: name, age, salary
    record Person(String name, int age, int salary) {}

    public static List<String> highEarners(List<Person> people) {
        // TODO: 一行 Stream 链式调用
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("alice", 30, 80000),
            new Person("bob",   22, 50000),
            new Person("charlie", 28, 120000),
            new Person("diana", 35, 95000)
        );
        System.out.println(highEarners(people));
        // 期望: [CHARLIE, DIANA, ALICE]
    }
}
