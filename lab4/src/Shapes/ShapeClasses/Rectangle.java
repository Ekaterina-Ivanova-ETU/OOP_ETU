package Shapes.ShapeClasses;

public class Rectangle implements Shape {
    public final double length;
    public final double height;

    public Rectangle(double length, double height) {
        if (length <= 0 || height <= 0)
            throw new IllegalArgumentException("Incorrect value(s) for the side(s) of the rectangle.");

        this.length = length;
        this.height = height;
    }

    @Override
    public double calcArea() { return length * height; }

    @Override
    public double calcPerimeter() { return 2 * (length + height); }

    @Override
    public String toString() {
        return "Rectangle: length = " + length + ", height = " + height +
                ", area = " + calcArea() +
                ", perimeter = " + calcPerimeter() + "\n";
    }
}
