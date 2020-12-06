package Shapes.ShapeClasses;

public class Circle implements Shape {
    public final double radius;

    public Circle(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Incorrect radius of the circle.");

        this.radius = radius;
    }

    @Override
    public double calcArea() { return Math.PI * Math.pow(radius, 2); }

    @Override
    public double calcPerimeter() { return 2 * Math.PI * radius; }

    @Override
    public String toString() {
        return "Circle: radius = " + radius +
                ", area = " + calcArea() +
                ", perimeter = " + calcPerimeter() + "\n";
    }
}
