package kr.shihyeon.imagicthud.config;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.LocalDateTimeMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.TextAlignMode;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorMode;
import kr.shihyeon.imagicthud.util.ModLogger;
import kr.shihyeon.imagicthud.config.categories.hud.HudCategory;
import kr.shihyeon.imagicthud.config.categories.indicator.IndicatorCategory;
import kr.shihyeon.imagicthud.config.gson.ColorDeserializer;
import kr.shihyeon.imagicthud.config.gson.ColorSerializer;
import kr.shihyeon.imagicthud.config.gson.EnumTypeAdapterFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Path;

public class ImagictHudConfig {

    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(ImagictHud.MODID + ".json5");

    private static final EnumTypeAdapterFactory ENUM_FACTORY = new EnumTypeAdapterFactory();
    static {
        // Hud Category
        ENUM_FACTORY.registerEnum(HeadRenderMode.class, HeadRenderMode.BOLD);
        ENUM_FACTORY.registerEnum(TextAlignMode.class, TextAlignMode.CENTER);
        ENUM_FACTORY.registerEnum(LocalDateTimeMode.class, LocalDateTimeMode.DATE_AND_TIME);

        // Indicator Category
        ENUM_FACTORY.registerEnum(IndicatorMode.class, IndicatorMode.BAR_AND_NUMBER);
    }

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(ENUM_FACTORY)
            .registerTypeAdapter(Color.class, new ColorSerializer())
            .registerTypeAdapter(Color.class, new ColorDeserializer())
            .registerTypeAdapter(Identifier.class, new Identifier.Serializer())
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.PRIVATE)
            .create();

    @Expose public final HudCategory hud = new HudCategory();
    @Expose public final IndicatorCategory indicator = new IndicatorCategory();

    private final File file;

    private static final Object lock = new Object();

    public static final ImagictHudConfig INSTANCE = new ImagictHudConfig(CONFIG_PATH.toFile());

    private ImagictHudConfig(File file) {
        this.file = file;
        load();
    }

    public void load() {
        synchronized (lock) {
            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    ImagictHudConfig config = GSON.fromJson(reader, ImagictHudConfig.class);
                    if (config != null) {
                        this.hud.updateFields(config.hud);
                        this.indicator.updateFields(config.indicator);
                    }
                } catch (Exception exception) {
                    ModLogger.error("Could not parse config, falling back to defaults!", exception);
                }
            } else {
                write();
            }
        }
    }

    public void save() {
        synchronized (lock) {
            write();
        }
    }

    private void write() {
        File dir = file.getParentFile();

        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Could not create parent directories");
        }

        if (!dir.isDirectory()) {
            throw new RuntimeException("The parent file is not a directory");
        }

        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(this, writer);
        } catch (IOException exception) {
            throw new RuntimeException("Could not save configuration file", exception);
        }
    }
}
