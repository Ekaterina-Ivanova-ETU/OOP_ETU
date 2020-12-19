package Shapes;

public class Triangle implements Shape {
    public final double sideA;
    public final double sideB;
    public final double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0 || sideA >= sideB + sideC || sideB >= sideA + sideC || sideC >= sideA + sideB)
            throw new IllegalArgumentException("Wrong sides of the triangle.");

        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    @Override
    public double calcArea() {
        double p = calcPerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public double calcPerimeter() { return sideA + sideB + sideC; }

    @Override
    public String toString() {
        return "Triangle (" + sideA + ", " + sideB + ", " + sideC + ")";
    }
}
