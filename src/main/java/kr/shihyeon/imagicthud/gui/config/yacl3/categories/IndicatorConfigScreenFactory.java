package kr.shihyeon.imagicthud.gui.config.yacl3.categories;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.util.ConfigTranslationHelper;
import net.minecraft.text.Text;

public class IndicatorConfigScreenFactory {

    public static ConfigCategory createIndicatorCategory(ImagictHudConfig config) {

        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setCategory("indicator")))
                .tooltip(Text.translatable(ConfigTranslationHelper.setCategory("indicator", true)));

        OptionGroup indicatorGeneralGroup = createIndicatorGeneralGroup(config);
        OptionGroup indicatorDisplayGroup = createIndicatorDisplayGroup(config);
        OptionGroup indicatorEntitiesGroup = createIndicatorEntitiesGroup(config);

        category.group(indicatorGeneralGroup);
        category.group(indicatorDisplayGroup);
        category.group(indicatorEntitiesGroup);

        return category.build();
    }

    private static OptionGroup createIndicatorGeneralGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "general")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "general", true))));

        Option<Boolean> enableIndicatorOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator", true))))
                .binding(
                        config.indicator.general.enableIndicator,
                        () -> config.indicator.general.enableIndicator,
                        newValue -> config.indicator.general.enableIndicator = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();

        group.option(enableIndicatorOption);

        return group.build();
    }

    private static OptionGroup createIndicatorDisplayGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "display")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "display", true))));

        Option<Boolean> attackingAtOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at", true))))
                .binding(
                        config.indicator.display.attackingAt,
                        () -> config.indicator.display.attackingAt,
                        newValue -> config.indicator.display.attackingAt = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> lookingAtOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at", true))))
                .binding(
                        config.indicator.display.lookingAt,
                        () -> config.indicator.display.lookingAt,
                        newValue -> config.indicator.display.lookingAt = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> damagedOnlyOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only", true))))
                .binding(
                        config.indicator.display.damagedOnly,
                        () -> config.indicator.display.damagedOnly,
                        newValue -> config.indicator.display.damagedOnly = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Integer> durationOption = Option.<Integer>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration", true))))
                .binding(
                        config.indicator.display.duration,
                        () -> config.indicator.display.duration,
                        newValue -> config.indicator.display.duration = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 120)
                        .step(1)
                        .formatValue(value -> Text.translatable(ConfigTranslationHelper.setOptionFormatKey("int_seconds"), value))
                )
                .build();
        Option<Integer> reachOption = Option.<Integer>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach", true))))
                .binding(
                        config.indicator.display.reach,
                        () -> config.indicator.display.reach,
                        newValue -> config.indicator.display.reach = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(3, 50)
                        .step(1)
                        .formatValue(value -> Text.translatable(ConfigTranslationHelper.setOptionFormatKey("int_meter"), value))
                )
                .build();
        group.option(attackingAtOption);
        group.option(lookingAtOption);
        group.option(damagedOnlyOption);
        group.option(durationOption);
        group.option(reachOption);

        return group.build();
    }

    private static OptionGroup createIndicatorEntitiesGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "entities")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setGroup("indicator", "entities", true))));

        Option<Boolean> playerEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities", true))))
                .binding(
                        config.indicator.entities.playerEntities,
                        () -> config.indicator.entities.playerEntities,
                        newValue -> config.indicator.entities.playerEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> selfPlayerEntityOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity", true))))
                .binding(
                        config.indicator.entities.selfPlayerEntity,
                        () -> config.indicator.entities.selfPlayerEntity,
                        newValue -> config.indicator.entities.selfPlayerEntity = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> passiveEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities", true))))
                .binding(
                        config.indicator.entities.passiveEntities,
                        () -> config.indicator.entities.passiveEntities,
                        newValue -> config.indicator.entities.passiveEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> hostileEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities")))
                .description(OptionDescription.of(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities", true))))
                .binding(
                        config.indicator.entities.hostileEntities,
                        () -> config.indicator.entities.hostileEntities,
                        newValue -> config.indicator.entities.hostileEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        group.option(playerEntitiesOption);
        group.option(selfPlayerEntityOption);
        group.option(passiveEntitiesOption);
        group.option(hostileEntitiesOption);

        return group.build();
    }
}