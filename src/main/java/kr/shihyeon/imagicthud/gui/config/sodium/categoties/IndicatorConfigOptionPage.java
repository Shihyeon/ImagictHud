package kr.shihyeon.imagicthud.gui.config.sodium.categoties;

import kr.shihyeon.imagicthud.gui.config.sodium.SodiumOptionsStorage;
import kr.shihyeon.imagicthud.util.ConfigTranslationHelper;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;

public class IndicatorConfigOptionPage {

    public static OptionGroup createGeneralGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableIndicatorOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "general", "enable_indicator", true)))
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
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "attacking_at", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.attackingAt = value,
                        option -> option.indicator.display.attackingAt
                )
                .build();
        OptionImpl<?, Boolean> lookingAtOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "looking_at", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.lookingAt = value,
                        option -> option.indicator.display.lookingAt
                )
                .build();
        OptionImpl<?, Boolean> damagedOnlyOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "damaged_only", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.display.damagedOnly = value,
                        option -> option.indicator.display.damagedOnly
                )
                .build();
        OptionImpl<?, Integer> durationOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "duration", true)))
                .setControl(option -> new SliderControl(option, 0, 120, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_seconds"))))
                .setBinding(
                        (option, value) -> option.indicator.display.duration = value,
                        option -> option.indicator.display.duration
                )
                .build();
        OptionImpl<?, Integer> reachOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "display", "reach", true)))
                .setControl(option -> new SliderControl(option, 3, 50, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_meter"))))
                .setBinding(
                        (option, value) -> option.indicator.display.reach = value,
                        option -> option.indicator.display.reach
                )
                .build();

        group.add(attackingAtOption);
        group.add(lookingAtOption);
        group.add(damagedOnlyOption);
        group.add(durationOption);
        group.add(reachOption);

        return group.build();
    }

    public static OptionGroup createEntitiesGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> playerEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "player_entities", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.playerEntities = value,
                        option -> option.indicator.entities.playerEntities
                )
                .build();
        OptionImpl<?, Boolean> selfPlayerEntityOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "self_player_entity", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.selfPlayerEntity = value,
                        option -> option.indicator.entities.selfPlayerEntity
                )
                .build();
        OptionImpl<?, Boolean> passiveEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "passive_entities", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.indicator.entities.passiveEntities = value,
                        option -> option.indicator.entities.passiveEntities
                )
                .build();
        OptionImpl<?, Boolean> hostileEntitiesOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities")))
                .setTooltip(Text.translatable(ConfigTranslationHelper.setOption("indicator", "entities", "hostile_entities", true)))
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
}
