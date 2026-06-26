/**
 * 题 6：文件读写
 *
 * 写一个程序，读入一个文本文件，把每一行反转后写入另一个文件。
 * 用 Files.readAllLines + Files.write（或 BufferedReader/BufferedWriter）。
 *
 * 输入文件：input.txt（每行一句话）
 * 输出文件：output.txt（每行字符顺序反转，行序不变）
 *
 * 考察：文件 I/O、nio.file.Files、IOException
 */
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Ex06_FileReverse {
    public static void reverseLines(String inputPath, String outputPath) throws IOException {
        // TODO
    }

    public static void main(String[] args) {
        // 先创建测试文件
        try {
            Files.write(Path.of("input.txt"),
                Arrays.asList("Hello World", "Java is fun", "Keep coding"));
            reverseLines("input.txt", "output.txt");
            Files.lines(Path.of("output.txt")).forEach(System.out::println);
            // 期望: dlroW olleH, nuf si avaJ, gnidoc peeK
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
