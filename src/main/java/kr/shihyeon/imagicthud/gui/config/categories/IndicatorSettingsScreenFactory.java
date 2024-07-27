package kr.shihyeon.imagicthud.gui.config.categories;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.util.FactoryTranslationHelper;
import net.minecraft.text.Text;

public class IndicatorSettingsScreenFactory {

    public static ConfigCategory createIndicatorCategory(ImagictHudConfig defaults, ImagictHudConfig config) {

        ConfigCategory.Builder indicatorCategory = ConfigCategory.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setCategory("indicator")))
                .tooltip(Text.translatable(FactoryTranslationHelper.setCategory("indicator", true)));

        OptionGroup indicatorDisplayGroup = createIndicatorDisplayGroup(defaults, config);
        OptionGroup indicatorEntitiesGroup = createIndicatorEntitiesGroup(defaults, config);

        indicatorCategory.group(indicatorDisplayGroup);
        indicatorCategory.group(indicatorEntitiesGroup);

        return indicatorCategory.build();
    }

    private static OptionGroup createIndicatorDisplayGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder indicatorDisplayGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("indicator", "display")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("indicator", "display", true))));

        Option<Boolean> attackingAtOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "attacking_at")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "attacking_at", true))))
                .binding(
                        defaults.indicator.display.attackingAt,
                        () -> config.indicator.display.attackingAt,
                        newValue -> config.indicator.display.attackingAt = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> lookingAtOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "looking_at")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "looking_at", true))))
                .binding(
                        defaults.indicator.display.lookingAt,
                        () -> config.indicator.display.lookingAt,
                        newValue -> config.indicator.display.lookingAt = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> damagedOnlyOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "damaged_only")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "damaged_only", true))))
                .binding(
                        defaults.indicator.display.damagedOnly,
                        () -> config.indicator.display.damagedOnly,
                        newValue -> config.indicator.display.damagedOnly = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Integer> durationOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "duration")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "duration", true))))
                .binding(
                        defaults.indicator.display.duration,
                        () -> config.indicator.display.duration,
                        newValue -> config.indicator.display.duration = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 120)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_seconds"), value))
                )
                .build();
        Option<Integer> reachOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "reach")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "display", "reach", true))))
                .binding(
                        defaults.indicator.display.reach,
                        () -> config.indicator.display.reach,
                        newValue -> config.indicator.display.reach = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(3, 50)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_meter"), value))
                )
                .build();
        indicatorDisplayGroup.option(attackingAtOption);
        indicatorDisplayGroup.option(lookingAtOption);
        indicatorDisplayGroup.option(damagedOnlyOption);
        indicatorDisplayGroup.option(durationOption);
        indicatorDisplayGroup.option(reachOption);

        return indicatorDisplayGroup.build();
    }

    private static OptionGroup createIndicatorEntitiesGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder indicatorEntitiesGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("indicator", "entities")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("indicator", "entities", true))));

        Option<Boolean> playerEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "player_entities")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "player_entities", true))))
                .binding(
                        defaults.indicator.entities.playerEntities,
                        () -> config.indicator.entities.playerEntities,
                        newValue -> config.indicator.entities.playerEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> selfPlayerEntityOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "self_player_entity")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "self_player_entity", true))))
                .binding(
                        defaults.indicator.entities.selfPlayerEntity,
                        () -> config.indicator.entities.selfPlayerEntity,
                        newValue -> config.indicator.entities.selfPlayerEntity = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> passiveEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "passive_entities")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "passive_entities", true))))
                .binding(
                        defaults.indicator.entities.passiveEntities,
                        () -> config.indicator.entities.passiveEntities,
                        newValue -> config.indicator.entities.passiveEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> hostileEntitiesOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "hostile_entities")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("indicator", "entities", "hostile_entities", true))))
                .binding(
                        defaults.indicator.entities.hostileEntities,
                        () -> config.indicator.entities.hostileEntities,
                        newValue -> config.indicator.entities.hostileEntities = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        indicatorEntitiesGroup.option(playerEntitiesOption);
        indicatorEntitiesGroup.option(selfPlayerEntityOption);
        indicatorEntitiesGroup.option(passiveEntitiesOption);
        indicatorEntitiesGroup.option(hostileEntitiesOption);

        return indicatorEntitiesGroup.build();
    }
}
