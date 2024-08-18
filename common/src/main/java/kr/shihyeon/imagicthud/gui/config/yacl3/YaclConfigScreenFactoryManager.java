package kr.shihyeon.imagicthud.gui.config.yacl3;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.gui.config.yacl3.categories.HudConfigScreenFactory;
import kr.shihyeon.imagicthud.gui.config.yacl3.categories.IndicatorConfigScreenFactory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class YaclConfigScreenFactoryManager {

    private static final ImagictHudConfig CONFIG = ImagictHudClient.CONFIG;

    public static Screen createScreen(Screen parent) {

        List<ConfigCategory> categories = new ArrayList<>();

        ConfigCategory hudCategory = HudConfigScreenFactory.createHudCategory(CONFIG);
        ConfigCategory indicatorCategory = IndicatorConfigScreenFactory.createIndicatorCategory(CONFIG);

        categories.add(hudCategory);
        categories.add(indicatorCategory);

        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable(ImagictHud.MODID + ".config.title"))
                .categories(categories)
                .save(CONFIG::save)
                .build()
                .generateScreen(parent);
    }
}
