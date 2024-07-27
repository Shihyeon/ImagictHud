package kr.shihyeon.imagicthud.gui.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.gui.config.categories.GeneralSettingsScreenFactory;
import kr.shihyeon.imagicthud.gui.config.categories.HudSettingsScreenFactory;
import kr.shihyeon.imagicthud.gui.config.categories.IndicatorSettingsScreenFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Arrays;

public class ConfigSettingsScreenFactoryManager {

    private static ConfigClassHandler<ImagictHudConfig> HANDLER = ImagictHudConfig.HANDLER;

    public static Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {

            ConfigCategory generalCategory = GeneralSettingsScreenFactory.createGeneralCategory(defaults, config);
            ConfigCategory hudCategory = HudSettingsScreenFactory.createHudCategory(defaults, config);
            ConfigCategory indicatorCategory = IndicatorSettingsScreenFactory.createIndicatorCategory(defaults, config);

            return builder
                    .title(Text.translatable("yacl3." + ImagictHud.MODID + ".config.title"))
                    .categories(Arrays.asList(
                            generalCategory,
                            hudCategory,
                            indicatorCategory
                    ));
        }).generateScreen(parent);
    }
}
