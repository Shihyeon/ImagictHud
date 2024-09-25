package kr.shihyeon.imagicthud.config.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorSerializer implements JsonSerializer<Color> {
    @Override
    public JsonElement serialize(Color color, Type typeOfSrc, JsonSerializationContext context) {
        String hexColor = String.format("#%06X", (0xFFFFFF & color.getRGB()));
        return new JsonPrimitive(hexColor);
    }
}
