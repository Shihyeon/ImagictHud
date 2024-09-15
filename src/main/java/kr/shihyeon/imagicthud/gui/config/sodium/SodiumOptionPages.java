package kr.shihyeon.imagicthud.gui.config.sodium;

import com.google.common.collect.ImmutableList;
import kr.shihyeon.imagicthud.gui.config.sodium.categoties.HudConfigOptionPage;
import kr.shihyeon.imagicthud.gui.config.sodium.categoties.IndicatorConfigOptionPage;
import kr.shihyeon.imagicthud.util.ConfigTranslationHelper;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class SodiumOptionPages {

    private static final SodiumOptionsStorage STORAGE = new SodiumOptionsStorage();

    public static OptionPage renderHudPage() {

        List<OptionGroup> groups = new ArrayList<>();

        groups.add(HudConfigOptionPage.createGeneralGroup(STORAGE));
        groups.add(HudConfigOptionPage.createDisplayGroup(STORAGE));
        groups.add(HudConfigOptionPage.createHeadGroup(STORAGE));
        groups.add(HudConfigOptionPage.createLabelGroup(STORAGE));
        groups.add(HudConfigOptionPage.createTextGroup(STORAGE));
        groups.add(HudConfigOptionPage.createLayoutGroup(STORAGE));

        return new OptionPage(Component.translatable(ConfigTranslationHelper.setCategory("hud")), ImmutableList.copyOf(groups));
    }

    public static OptionPage renderIndicatorPage() {

        List<OptionGroup> groups = new ArrayList<>();

        groups.add(IndicatorConfigOptionPage.createGeneralGroup(STORAGE));
        groups.add(IndicatorConfigOptionPage.createDisplayGroup(STORAGE));
        groups.add(IndicatorConfigOptionPage.createEntitiesGroup(STORAGE));
        groups.add(IndicatorConfigOptionPage.createTextGroup(STORAGE));
        groups.add(IndicatorConfigOptionPage.createLayoutGroup(STORAGE));

        return new OptionPage(Component.translatable(ConfigTranslationHelper.setCategory("indicator")), ImmutableList.copyOf(groups));
    }
}
