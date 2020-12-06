package Shapes;

import Shapes.ShapeClasses.*;
import java.util.*;

public class ShapeAccumulator {
    private ArrayList<Shape> shapes;

    public ShapeAccumulator() {
        shapes = new ArrayList<>();
    }

    public <T extends Shape> void add(T shape) {
        shapes.add(shape);
    }

    public void addAll(Collection<? extends Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public Shape getMaxAreaShape() {
        return shapes.stream().max(Comparator.comparingDouble(Shape::calcArea)).get();
    }

    public Shape getMinAreaShape() {
        return shapes.stream().min(Comparator.comparingDouble(Shape::calcArea)).get();
    }

    public Shape getMaxPerimeterShape() {
        return shapes.stream().max(Comparator.comparingDouble(Shape::calcPerimeter)).get();
    }

    public Shape getMinPerimeterShape() {
        return shapes.stream().min(Comparator.comparingDouble(Shape::calcPerimeter)).get();
    }

    public double getTotalArea() {
        return shapes.stream().mapToDouble(Shape::calcArea).sum();
    }

    public double getTotalPerimeter() {
        return shapes.stream().mapToDouble(Shape::calcPerimeter).sum();
    }
}
