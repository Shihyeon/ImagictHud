package kr.shihyeon.imagicthud.config.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {

    private final Map<Class<?>, Enum<?>> enumDefaults = new HashMap<>();

    public <T extends Enum<T>> void registerEnum(Class<T> enumClass, T defaultValue) {
        enumDefaults.put(enumClass, defaultValue);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (!Enum.class.isAssignableFrom(rawType)) {
            return null;
        }
        Enum<?> defaultValue = enumDefaults.get(rawType);
        if (defaultValue == null) {
            return null;
        }
        Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) rawType;
        return (TypeAdapter<T>) new EnumTypeAdapter(enumClass, defaultValue).nullSafe();
    }
}
