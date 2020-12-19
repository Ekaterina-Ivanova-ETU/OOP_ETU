package WorkWithFiles;

import Shapes.Shape;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;
import java.io.*;
import java.io.IOException;


public class StoreShapes implements StoreFile<Shape> {
    private final String filePath;

    public StoreShapes(String filePath) {
        this.filePath = filePath;
    }

    public void WriteToFile(List<Shape> shapes) throws IOException {
        try (Writer writer = new FileWriter(filePath, false)) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Type type = new TypeToken<List<Shape>>() {}.getType();
            gsonBuilder.registerTypeAdapter(Shape.class, new JSONObjectAdapter());
            gsonBuilder.create().toJson(shapes, type, writer);
            writer.flush();
        } catch (IOException e) {
            throw new IOException("Error writing data to file.");
        }
    }

    public List<Shape> ReadFromFile() throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Type type = new TypeToken<List<Shape>>() {}.getType();
            gsonBuilder.registerTypeAdapter(Shape.class, new JSONObjectAdapter());
            List<Shape> shapes = gsonBuilder.create().fromJson(reader, type);
            return shapes != null ? shapes : Collections.emptyList();
        } catch (IOException e) {
            throw new IOException("Error reading data from a file.");
        }
    }
}
