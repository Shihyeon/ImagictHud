package kr.shihyeon.imagicthud.gui.screen.config.sodium.categoties;

import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorBarMode;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorMode;
import kr.shihyeon.imagicthud.gui.screen.config.sodium.SodiumOptionsStorage;
import kr.shihyeon.imagicthud.util.ConfigTranslationHelper;
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.caffeinemc.mods.sodium.client.gui.options.control.CyclingControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.SliderControl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.Component;

import java.util.stream.Stream;

public class IndicatorConfigOptionPage {

    public static OptionGroup createGeneralGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableIndicatorOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.general.enableIndicator = value,
                        option -> option.indicator.general.enableIndicator
                )
                .build();

        group.add(enableIndicatorOption);

        return group.build();
    }

    public static OptionGroup createDisplayGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> attackingAtOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.attackingAt = value,
                        option -> option.indicator.display.attackingAt
                )
                .build();
        OptionImpl<?, Boolean> lookingAtOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.lookingAt = value,
                        option -> option.indicator.display.lookingAt
                )
                .build();
        OptionImpl<?, Boolean> damagedOnlyOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.damagedOnly = value,
                        option -> option.indicator.display.damagedOnly
                )
                .build();
        OptionImpl<?, IndicatorMode> indicatorModeOption = OptionImpl.createBuilder(IndicatorMode.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "indicator_mode")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "indicator_mode", true)))
                .setControl(option -> new CyclingControl<>(
                        option,
                        IndicatorMode.class,
                        Stream.of(IndicatorMode.values())
                              .map(value -> ControlValueFormatter.translateVariable(
                                      ConfigTranslationHelper.setEnumOptionFormatKey("indicator", "display", "indicator_mode")
                                              + value.name().toLowerCase())
                                      .format(0))
                              .toArray(Component[]::new))
                )
                .setBinding(
                        (option, value) -> option.indicator.display.indicatorMode = value,
                        option -> option.indicator.display.indicatorMode
                )
                .build();
        OptionImpl<?, IndicatorBarMode> indicatorBarModeOption = OptionImpl.createBuilder(IndicatorBarMode.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "indicator_bar_mode")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "indicator_bar_mode", true)))
                .setControl(option -> new CyclingControl<>(
                        option,
                        IndicatorBarMode.class,
                        Stream.of(IndicatorBarMode.values())
                              .map(value -> ControlValueFormatter.translateVariable(
                                      ConfigTranslationHelper.setEnumOptionFormatKey("indicator", "display", "indicator_bar_mode")
                                              + value.name().toLowerCase())
                                      .format(0))
                              .toArray(Component[]::new))
                )
                .setBinding(
                        (option, value) -> option.indicator.display.indicatorBarMode = value,
                        option -> option.indicator.display.indicatorBarMode
                )
                .build();
        OptionImpl<?, Integer> durationOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration", true)))
                .setControl(option -> new SliderControl(option, 0, 120, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_seconds"))))
                .setBinding(
                        (option, value) -> option.indicator.display.duration = value,
                        option -> option.indicator.display.duration
                )
                .build();
        OptionImpl<?, Integer> reachOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach", true)))
                .setControl(option -> new SliderControl(option, 3, 50, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_meter"))))
                .setBinding(
                        (option, value) -> option.indicator.display.reach = value,
                        option -> option.indicator.display.reach
                )
                .build();

        group.add(attackingAtOption);
        group.add(lookingAtOption);
        group.add(damagedOnlyOption);
        group.add(indicatorModeOption);
        group.add(indicatorBarModeOption);
        group.add(durationOption);
        group.add(reachOption);

        return group.build();
    }

    public static OptionGroup createEntitiesGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> playerEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.playerEntities = value,
                        option -> option.indicator.entities.playerEntities
                )
                .build();
        OptionImpl<?, Boolean> selfPlayerEntityOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.selfPlayerEntity = value,
                        option -> option.indicator.entities.selfPlayerEntity
                )
                .build();
        OptionImpl<?, Boolean> passiveEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.passiveEntities = value,
                        option -> option.indicator.entities.passiveEntities
                )
                .build();
        OptionImpl<?, Boolean> hostileEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.hostileEntities = value,
                        option -> option.indicator.entities.hostileEntities
                )
                .build();

        group.add(playerEntitiesOption);
        group.add(selfPlayerEntityOption);
        group.add(passiveEntitiesOption);
        group.add(hostileEntitiesOption);

        return group.build();
    }

    public static OptionGroup createTextGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableTextShadowsOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "text", "enable_shadows")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "text", "enable_shadows", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.text.enableTextShadows = value,
                        option -> option.indicator.text.enableTextShadows
                )
                .build();

        group.add(enableTextShadowsOption);

        return group.build();
    }

    public static OptionGroup createLayoutGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Integer> positionYOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "layout", "position_y")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "layout", "position_y", true)))
                .setControl(option -> new SliderControl(option, -15, 15, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"))))
                .setBinding(
                        (option, value) -> option.indicator.layout.positionY = value,
                        option -> option.indicator.layout.positionY
                )
                .build();
        OptionImpl<?, Integer> nameScaleOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("indicator", "layout", "name_scale")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("indicator", "layout", "name_scale", true)))
                .setControl(option -> new SliderControl(option, 0, 4, 1, ControlValueFormatter.guiScale()))
                .setBinding(
                        (option, value) -> option.indicator.layout.nameScale = value,
                        option -> option.indicator.layout.nameScale
                )
                .build();

        group.add(positionYOption);
        group.add(nameScaleOption);

        return group.build();
    }
}
