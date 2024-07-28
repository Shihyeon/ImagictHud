package kr.shihyeon.imagicthud.config.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

    private final Class<T> enumClass;
    private final T defaultValue;

    public EnumTypeAdapter(Class<T> enumClass, T defaultValue) {
        this.enumClass = enumClass;
        this.defaultValue = defaultValue;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.name());
        }
    }

    @Override
    public T read(JsonReader in) throws IOException {
        try {
            return Enum.valueOf(enumClass, in.nextString());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return defaultValue;
        }
    }
}
