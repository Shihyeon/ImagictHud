package kr.shihyeon.imagicthud.gui.screen.config.sodium.categoties;

import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.LocalDateTimeMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.TextAlignMode;
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

public class HudConfigOptionPage {

    public static OptionGroup createGeneralGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableHudOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "general", "enable_hud")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "general", "enable_hud", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.general.enableHud = value,
                        option -> option.hud.general.enableHud
                )
                .build();

        group.add(enableHudOption);

        return group.build();
    }

    public static OptionGroup createDisplayGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableHeadOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_head")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_head", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.display.enableHead = value,
                        option -> option.hud.display.enableHead
                )
                .build();
        OptionImpl<?, Boolean> enableLocalDateTimeLabelOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_local_date_time_label")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_local_date_time_label", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.display.enableLocalDateTimeLabel = value,
                        option -> option.hud.display.enableLocalDateTimeLabel
                )
                .build();
        OptionImpl<?, Boolean> enableNicknameLabelOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_nickname_label")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_nickname_label", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.display.enableNicknameLabel = value,
                        option -> option.hud.display.enableNicknameLabel
                )
                .build();
        OptionImpl<?, Boolean> enableCoordinatesLabelOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_coordinates_label")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_coordinates_label", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.display.enableCoordinatesLabel = value,
                        option -> option.hud.display.enableCoordinatesLabel
                )
                .build();
        OptionImpl<?, Boolean> enableBiomeLabelOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_biome_label")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_biome_label", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.display.enableBiomeLabel = value,
                        option -> option.hud.display.enableBiomeLabel
                )
                .build();

        group.add(enableHeadOption);
        group.add(enableLocalDateTimeLabelOption);
        group.add(enableNicknameLabelOption);
        group.add(enableCoordinatesLabelOption);
        group.add(enableBiomeLabelOption);

        return group.build();
    }

    public static OptionGroup createHeadGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, HeadRenderMode> headRenderModeOption = OptionImpl.createBuilder(HeadRenderMode.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "head", "render_mode")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "head", "render_mode", true)))
                .setControl(option -> new CyclingControl<>(
                        option,
                        HeadRenderMode.class,
                        Stream.of(HeadRenderMode.values())
                              .map(value -> ControlValueFormatter.translateVariable(
                                      ConfigTranslationHelper.setEnumOptionFormatKey("hud", "head", "render_mode")
                                              + value.name().toLowerCase())
                                      .format(0))
                              .toArray(Component[]::new))
                )
                .setBinding(
                        (option, value) -> option.hud.head.headRenderMode = value,
                        option -> option.hud.head.headRenderMode
                )
                .build();

        group.add(headRenderModeOption);

        return group.build();
    }

    public static OptionGroup createLabelGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Boolean> enableLabelFrameOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "enable_frame")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "enable_frame", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.label.enableLabelFrame = value,
                        option -> option.hud.label.enableLabelFrame
                )
                .build();
        // labelFrameColorOption
        // labelBackgroundColorOption
        OptionImpl<?, Integer> labelBackgroundOpacityOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_opacity")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_opacity", true)))
                .setControl(option -> new SliderControl(option, 0, 100, 10, ControlValueFormatter.percentage())) // mode: value -> Text.literal(value==32?"Vanilla":(value==256?"Keep All":value+" chunks"))
                .setBinding(
                        (option, value) -> option.hud.label.labelBackgroundOpacity = value,
                        option -> option.hud.label.labelBackgroundOpacity
                )
                .build();

        group.add(enableLabelFrameOption);
        group.add(labelBackgroundOpacityOption);

        return group.build();
    }

    public static OptionGroup createTextGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        // textColorOption
        OptionImpl<?, Boolean> enableTextShadowsOption = OptionImpl.createBuilder(boolean.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "enable_shadows")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "enable_shadows", true)))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (option, value) -> option.hud.text.enableTextShadows = value,
                        option -> option.hud.text.enableTextShadows
                )
                .build();
        OptionImpl<?, Integer> textOpacityOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "opacity")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "opacity", true)))
                .setControl(option -> new SliderControl(option, 0, 100, 10, ControlValueFormatter.percentage()))
                .setBinding(
                        (option, value) -> option.hud.text.textOpacity = value,
                        option -> option.hud.text.textOpacity
                )
                .build();
        OptionImpl<?, TextAlignMode> textAlignModeOption = OptionImpl.createBuilder(TextAlignMode.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "align_mode")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "align_mode", true)))
                .setControl(option -> new CyclingControl<>(
                        option,
                        TextAlignMode.class,
                        Stream.of(TextAlignMode.values())
                              .map(value -> ControlValueFormatter.translateVariable(
                                      ConfigTranslationHelper.setEnumOptionFormatKey("hud", "text", "align_mode")
                                              + value.name().toLowerCase())
                                      .format(0))
                              .toArray(Component[]::new))
                )
                .setBinding(
                        (option, value) -> option.hud.text.textAlignMode = value,
                        option -> option.hud.text.textAlignMode
                )
                .build();
        OptionImpl<?, LocalDateTimeMode> localDateTimeModeOption = OptionImpl.createBuilder(LocalDateTimeMode.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "local_date_time_mode")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "local_date_time_mode", true)))
                .setControl(option -> new CyclingControl<>(
                        option,
                        LocalDateTimeMode.class,
                        Stream.of(LocalDateTimeMode.values())
                              .map(value -> ControlValueFormatter.translateVariable(
                                      ConfigTranslationHelper.setEnumOptionFormatKey("hud", "text", "local_date_time_mode")
                                              + value.name().toLowerCase())
                                      .format(0))
                              .toArray(Component[]::new))
                )
                .setBinding(
                        (option, value) -> option.hud.text.localDateTimeMode = value,
                        option -> option.hud.text.localDateTimeMode
                )
                .build();

        group.add(enableTextShadowsOption);
        group.add(textOpacityOption);
        group.add(textAlignModeOption);
        group.add(localDateTimeModeOption);

        return group.build();
    }

    public static OptionGroup createLayoutGroup(SodiumOptionsStorage storage) {

        OptionGroup.Builder group = OptionGroup.createBuilder();

        OptionImpl<?, Integer> labelWidthOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_width")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_width", true)))
                .setControl(option -> new SliderControl(option, 50, 150, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"))))
                .setBinding(
                        (option, value) -> option.hud.layout.labelWidth = value,
                        option -> option.hud.layout.labelWidth
                )
                .build();
        OptionImpl<?, Integer> labelLineSpacingOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_line_spacing")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_line_spacing", true)))
                .setControl(option -> new SliderControl(option, -5, 5, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"))))
                .setBinding(
                        (option, value) -> option.hud.layout.labelLineSpacing = value,
                        option -> option.hud.layout.labelLineSpacing
                )
                .build();
        OptionImpl<?, Integer> hudScaleOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "scale")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "scale", true)))
                .setControl(option -> new SliderControl(option, 0, 8, 1, ControlValueFormatter.guiScale()))
                .setBinding(
                        (option, value) -> option.hud.layout.hudScale = value,
                        option -> option.hud.layout.hudScale
                )
                .build();
        OptionImpl<?, Integer> positionXOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_x")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_x", true)))
                .setControl(option -> new SliderControl(option, 0, 50, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"))))
                .setBinding(
                        (option, value) -> option.hud.layout.positionX = value,
                        option -> option.hud.layout.positionX
                )
                .build();
        OptionImpl<?, Integer> positionYOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_y")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_y", true)))
                .setControl(option -> new SliderControl(option, 0, 50, 1, ControlValueFormatter.translateVariable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"))))
                .setBinding(
                        (option, value) -> option.hud.layout.positionY = value,
                        option -> option.hud.layout.positionY
                )
                .build();
        OptionImpl<?, Integer> offsetOption = OptionImpl.createBuilder(int.class, storage)
                .setName(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "offset")))
                .setTooltip(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "offset", true)))
                .setControl(option -> new SliderControl(option, 0, 50, 1, ControlValueFormatter.number()))
                .setBinding(
                        (option, value) -> option.hud.layout.offset = value,
                        option -> option.hud.layout.offset
                )
                .build();

        group.add(labelWidthOption);
        group.add(labelLineSpacingOption);
        group.add(hudScaleOption);
        group.add(positionXOption);
        group.add(positionYOption);
        group.add(offsetOption);

        return group.build();
    }
}
