package kr.shihyeon.imagicthud.gui.config.categories;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.util.FactoryTranslationHelper;
import net.minecraft.text.Text;

public class GeneralSettingsScreenFactory {

    public static ConfigCategory createGeneralCategory(ImagictHudConfig defaults, ImagictHudConfig config) {

            ConfigCategory.Builder generalCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(FactoryTranslationHelper.setCategory("general")))
                    .tooltip(Text.translatable(FactoryTranslationHelper.setCategory("general", true)));

            OptionGroup generalHudGroup = createGeneralHudGroup(defaults, config);
            OptionGroup generalIndicatorGroup = createGeneralIndicatorGroup(defaults, config);

            generalCategory.group(generalHudGroup);
            generalCategory.group(generalIndicatorGroup);

        return generalCategory.build();
    }

    private static OptionGroup createGeneralHudGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder generalHudGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("general", "hud")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("general", "hud", true))));

        Option<Boolean> enableHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(FactoryTranslationHelper.setOption("general", "hud", "enable_hud")))
                    .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("general", "hud", "enable_hud", true))))
                    .binding(
                            defaults.general.hud.enableHud,
                            () -> config.general.hud.enableHud,
                            newValue -> config.general.hud.enableHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();

        generalHudGroup.option(enableHudOption);

        return generalHudGroup.build();
    }

    private static OptionGroup createGeneralIndicatorGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder generalIndicatorGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("general", "indicator")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("general", "indicator", true))));

        Option<Boolean> enableIndicatorOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(FactoryTranslationHelper.setOption("general", "indicator", "enable_indicator")))
                    .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("general", "indicator", "enable_indicator", true))))
                    .binding(
                            defaults.general.indicator.enableIndicator,
                            () -> config.general.indicator.enableIndicator,
                            newValue -> config.general.indicator.enableIndicator = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();

        generalIndicatorGroup.option(enableIndicatorOption);

        return generalIndicatorGroup.build();
    }
}
