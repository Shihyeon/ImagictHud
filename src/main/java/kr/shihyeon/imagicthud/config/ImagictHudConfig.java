package kr.shihyeon.imagicthud.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.config.categories.general.GeneralCategory;
import kr.shihyeon.imagicthud.config.categories.hud.HudCategory;
import kr.shihyeon.imagicthud.config.categories.indicator.IndicatorCategory;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

public class ImagictHudConfig {

    public static final Path CONFIG_PATH = YACLPlatform.getConfigDir().resolve(ImagictHud.MODID + ".json5");

    public static ConfigClassHandler<ImagictHudConfig> HANDLER = ConfigClassHandler.createBuilder(ImagictHudConfig.class)
            .id(Identifier.of(ImagictHud.MODID, ImagictHud.MODID + "_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(CONFIG_PATH)
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry public GeneralCategory general = new GeneralCategory();
    @SerialEntry public HudCategory hud = new HudCategory();
    @SerialEntry public IndicatorCategory indicator = new IndicatorCategory();
}
