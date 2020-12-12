package Shapes;

public class Square implements Shape {
    public final double side;

    public Square(double side) {
        if (side <= 0)
            throw new IllegalArgumentException("Incorrect value for the side(s) of the square.");

        this.side = side;
    }

    @Override
    public double calcArea() { return side * side; }

    @Override
    public double calcPerimeter() { return 4 * side; }

    @Override
    public String toString() {
        return "Square (" + side + " x " + side + ")";
    }
}
