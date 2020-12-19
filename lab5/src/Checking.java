import Shapes.*;
import WorkWithFiles.StoreFile;
import WorkWithFiles.StoreShapes;

import java.io.IOException;
import java.util.*;


public class Checking {
    public static void main(String[] args) throws IOException {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(1.5));
        shapes.add(new Circle(7));
        shapes.add(new Rectangle(2, 8));
        shapes.add(new Rectangle(20, 2.5));
        shapes.add(new Square(2.5));
        shapes.add(new Square(9.9));
        shapes.add(new Triangle(3, 4, 5));
        shapes.add(new Triangle(4, 6, 7));

        StoreFile<Shape> storeShapes = new StoreShapes("shapes.json");

        storeShapes.WriteToFile(shapes);

        List<Shape> listShapesFromFile = storeShapes.ReadFromFile();

        System.out.print("Shapes read from the file:\n");
        listShapesFromFile.forEach(System.out::print);
    }
}