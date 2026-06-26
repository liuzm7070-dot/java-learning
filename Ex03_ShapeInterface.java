/**
 * 题 3：接口 + 多态
 *
 * 定义一个接口 Shape，含 double area()
 * 实现两个类：
 *   Circle(double radius)
 *   Rectangle(double width, double height)
 *
 * 写一个方法 public static Shape largestShape(Shape[] shapes)
 * 返回面积最大的形状。
 *
 * 考察：interface、implements、多态、instanceof（可选）
 */
public class Ex03_ShapeInterface {
    // TODO: interface Shape
    // TODO: class Circle implements Shape
    // TODO: class Rectangle implements Shape

    public static Shape largestShape(Shape[] shapes) {
        // TODO: 返回面积最大的 Shape
        return shapes[0]; // 占位
    }

    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Circle(3)
        };
        Shape largest = largestShape(shapes);
        System.out.println("Largest area: " + largest.area());
        // Circle(5) 面积 ≈ 78.54，最大
    }
}

// 占位 — 请补充完整
interface Shape { double area(); }
class Circle implements Shape { Circle(double r) {} public double area() { return 0; } }
class Rectangle implements Shape { Rectangle(double w, double h) {} public double area() { return 0; } }
