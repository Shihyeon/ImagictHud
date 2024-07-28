package kr.shihyeon.imagicthud.config.categories;

import com.google.gson.annotations.Expose;

import java.awt.*;
import java.lang.reflect.Field;

public abstract class ConfigCategory {

    public void updateFields(ConfigCategory newCategory) {
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Expose.class)) {
                    field.setAccessible(true);
                    Object newValue = field.get(newCategory);
                    if (newValue != null) {
                        field.set(this, newValue);
                    } else {
                        field.set(this, getDefaultValue(field.getType()));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object getDefaultValue(Class<?> type) {
        if (type == boolean.class) return false;
        if (type == int.class) return 0;
        if (type == float.class) return 1.0f;
        if (type == String.class) return "";
        if (type == Color.class) return 0xffffffff;

        return null;
    }
}
