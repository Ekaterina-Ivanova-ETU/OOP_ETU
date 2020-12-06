import Shapes.*;

import java.util.*;

public class Checking {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<Shape>();

        shapes.add(new Circle(1.5));
        shapes.add(new Circle(7));
        shapes.add(new Rectangle(2, 8));
        shapes.add(new Rectangle(20, 2.5));
        shapes.add(new Square(2.5));
        shapes.add(new Square(9.9));
        shapes.add(new Triangle(3, 4, 5));
        shapes.add(new Triangle(4, 6, 7));

        System.out.println("Total area of all shapes = " +
                shapes.stream().mapToDouble(Shape::calcArea).sum() + "\n");
        System.out.println("The shape with the largest area:\n" +
                shapes.stream().max(Comparator.comparing(Shape::calcArea)).get());
        System.out.println("The shape with the smallest area:\n" +
                shapes.stream().min(Comparator.comparing(Shape::calcArea)).get());
        System.out.println("The shape with the largest perimeter:\n" +
                shapes.stream().max(Comparator.comparing(Shape::calcPerimeter)).get());
        System.out.println("The shape with the smallest perimeter:\n" +
                shapes.stream().min(Comparator.comparing(Shape::calcPerimeter)).get());
    }
}
