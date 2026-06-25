/**
 * 题 8：综合项目 — 命令行 Todo List
 *
 * 做一个交互式 Todo 管理程序，支持以下命令：
 *   add <任务描述>     — 添加任务
 *   list               — 列出所有任务（编号 + 状态 + 描述）
 *   done <编号>        — 标记任务为完成
 *   delete <编号>      — 删除任务
 *   exit               — 退出
 *
 * 数据存到 todo.json，每次启动自动加载，每次修改自动保存。
 * JSON 用简单的字符串拼接即可（不需要第三方库）。
 *
 * 考察：Scanner 输入、循环、List、文件读写、JSON 序列化基础、综合能力
 *
 * 提示：
 * - 用 TodoItem 类（id, desc, done）
 * - 用 ArrayList<TodoItem> 存所有任务
 * - 保存格式：[{"id":1,"desc":"buy milk","done":false}, ...]
 */
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Ex08_TodoList {
    static final String FILE = "todo.json";
    static List<TodoItem> items = new ArrayList<>();
    static int nextId = 1;

    static class TodoItem {
        int id;
        String desc;
        boolean done;
        TodoItem(int id, String desc, boolean done) {
            this.id = id; this.desc = desc; this.done = done;
        }
        @Override
        public String toString() {
            return String.format("[%s] %d. %s", done ? "✓" : " ", id, desc);
        }
    }

    // TODO: load() — 从 todo.json 读取
    // TODO: save() — 写入 todo.json
    // TODO: 主循环处理命令

    public static void main(String[] args) {
        // TODO: 加载已有数据
        // TODO: Scanner 循环读取命令，分发到各处理方法
        System.out.println("Todo List — 命令: add / list / done / delete / exit");
    }
}
