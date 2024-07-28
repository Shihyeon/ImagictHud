package kr.shihyeon.imagicthud.gui.config.yacl3;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.gui.config.yacl3.categories.HudConfigScreenFactory;
import kr.shihyeon.imagicthud.gui.config.yacl3.categories.IndicatorConfigScreenFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Arrays;

public class YaclConfigScreenFactoryManager {

    private static final ImagictHudConfig CONFIG = ImagictHudConfig.INSTANCE;

    public static Screen createScreen(Screen parent) {

        ConfigCategory hudCategory = HudConfigScreenFactory.createHudCategory(CONFIG);
        ConfigCategory indicatorCategory = IndicatorConfigScreenFactory.createIndicatorCategory(CONFIG);

        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable(ImagictHud.MODID + ".config.title"))
                .categories(Arrays.asList(hudCategory, indicatorCategory))
                .save(CONFIG::save)
                .build()
                .generateScreen(parent);
    }
}
