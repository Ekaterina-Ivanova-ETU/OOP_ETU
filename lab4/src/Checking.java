import Shapes.ShapeClasses.*;
import Shapes.ShapeAccumulator;

import java.util.*;

public class Checking {
    public static void main(String[] args) {

        ShapeAccumulator shapeAccumulator = new ShapeAccumulator();

        ArrayDeque<Shape> shapes = new ArrayDeque<>();
        shapes.add(new Circle(7));
        shapes.add(new Rectangle(20, 2.6));
        shapes.add(new Square(9.9));
        shapes.add(new Triangle(4, 6, 7));

        LinkedList<Circle> circles = new LinkedList<>();
        circles.add(new Circle(3.3));
        circles.add(new Circle(2.67));

        shapeAccumulator.add(new Circle(1.5));
        shapeAccumulator.add(new Rectangle(2, 8));
        shapeAccumulator.add(new Square(2.51));
        shapeAccumulator.add(new Triangle(3, 4, 5));
        shapeAccumulator.addAll(shapes);
        shapeAccumulator.addAll(circles);


        System.out.println("The shape with the largest area:\n" + shapeAccumulator.getMaxAreaShape());
        System.out.println("The shape with the smallest area:\n" + shapeAccumulator.getMinAreaShape());
        System.out.println("The shape with the largest perimeter:\n" + shapeAccumulator.getMaxPerimeterShape());
        System.out.println("The shape with the smallest perimeter:\n" + shapeAccumulator.getMinPerimeterShape());
        System.out.println("Total area of all shapes = " + shapeAccumulator.getTotalArea() + "\n");
        System.out.println("Total perimeter of all shapes = " + shapeAccumulator.getTotalPerimeter());
    }
}
