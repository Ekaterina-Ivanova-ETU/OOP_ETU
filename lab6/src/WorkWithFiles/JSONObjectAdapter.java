package WorkWithFiles;

import Shapes.Shape;

import com.google.gson.*;
import java.lang.reflect.Type;

public class JSONObjectAdapter implements JsonSerializer<Shape>, JsonDeserializer<Shape> {
    private static final String CLASSNAME = "classname";
    private static final String DATA = "data";

    @Override
    public JsonElement serialize(Shape object, Type type, JsonSerializationContext JsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, object.getClass().getName());
        jsonObject.add(DATA, JsonSerializationContext.serialize(object));
        return jsonObject;
    }

    @Override
    public Shape deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = jsonPrimitive.getAsString();
        Class objectClass = getObjectClass(className);
        return context.deserialize(jsonObject.get(DATA), objectClass);
    }

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
