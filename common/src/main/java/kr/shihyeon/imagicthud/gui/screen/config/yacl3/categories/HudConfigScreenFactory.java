package kr.shihyeon.imagicthud.gui.screen.config.yacl3.categories;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.TextAlignMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.LocalDateTimeMode;
import kr.shihyeon.imagicthud.util.ConfigTranslationHelper;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class HudConfigScreenFactory {

    public static ConfigCategory createHudCategory(ImagictHudConfig config) {

        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setCategory("hud")))
                .tooltip(Component.translatable(ConfigTranslationHelper.setCategory("hud", true)));

        OptionGroup generalGroup = createGeneralGroup(config);
        OptionGroup displayGroup = createDisplayGroup(config);
        OptionGroup headGroup = createHeadGroup(config);
        OptionGroup labelGroup = createLabelGroup(config);
        OptionGroup textGroup = createTextGroup(config);
        OptionGroup layoutGroup = createLayoutGroup(config);

        category.group(generalGroup);
        category.group(displayGroup);
        category.group(headGroup);
        category.group(labelGroup);
        category.group(textGroup);
        category.group(layoutGroup);

        return category.build();
    }

    private static OptionGroup createGeneralGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "general")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "general", true))));

        Option<Boolean> enableHudOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "general", "enable_hud")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "general", "enable_hud", true))))
                .binding(
                        config.hud.general.enableHud,
                        () -> config.hud.general.enableHud,
                        newValue -> config.hud.general.enableHud = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();

        group.option(enableHudOption);

        return group.build();
    }

    private static OptionGroup createDisplayGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "display")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "display", true))));

        Option<Boolean> enableHeadOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_head")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_head", true))))
                .binding(
                        config.hud.display.enableHead,
                        () -> config.hud.display.enableHead,
                        newValue -> config.hud.display.enableHead = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableLocalDateTimeLabelOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_local_date_time_label")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_local_date_time_label", true))))
                .binding(
                        config.hud.display.enableLocalDateTimeLabel,
                        () -> config.hud.display.enableLocalDateTimeLabel,
                        newValue -> config.hud.display.enableLocalDateTimeLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableNicknameLabelOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_nickname_label")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_nickname_label", true))))
                .binding(
                        config.hud.display.enableNicknameLabel,
                        () -> config.hud.display.enableNicknameLabel,
                        newValue -> config.hud.display.enableNicknameLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableCoordinatesLabelOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_coordinates_label")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_coordinates_label", true))))
                .binding(
                        config.hud.display.enableCoordinatesLabel,
                        () -> config.hud.display.enableCoordinatesLabel,
                        newValue -> config.hud.display.enableCoordinatesLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableBiomeLabelOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_biome_label")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "display", "enable_biome_label", true))))
                .binding(
                        config.hud.display.enableBiomeLabel,
                        () -> config.hud.display.enableBiomeLabel,
                        newValue -> config.hud.display.enableBiomeLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();

        group.option(enableHeadOption);
        group.option(enableLocalDateTimeLabelOption);
        group.option(enableNicknameLabelOption);
        group.option(enableCoordinatesLabelOption);
        group.option(enableBiomeLabelOption);

        return group.build();
    }

    private static OptionGroup createHeadGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "head")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "head", true))));

        Option<HeadRenderMode> headRenderModeOption = Option.<HeadRenderMode>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "head", "render_mode")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "head", "render_mode", true))))
                .binding(
                        config.hud.head.headRenderMode,
                        () -> config.hud.head.headRenderMode,
                        newValue -> config.hud.head.headRenderMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(HeadRenderMode.class)
                        .formatValue(value -> ConfigTranslationHelper.translatableEnum(ConfigTranslationHelper.setEnumOptionFormatKey("hud", "head", "render_mode"), value)))
                .build();

        group.option(headRenderModeOption);

        return group.build();
    }

    private static OptionGroup createLabelGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "label")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "label", true))));

        Option<Boolean> enableLabelFrameOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "enable_frame")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "enable_frame", true))))
                .binding(
                        config.hud.label.enableLabelFrame,
                        () -> config.hud.label.enableLabelFrame,
                        newValue -> config.hud.label.enableLabelFrame = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Color> labelFrameColorOption = Option.<Color>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "frame_color")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "frame_color", true))))
                .binding(
                        config.hud.label.labelFrameColor,
                        () -> config.hud.label.labelFrameColor,
                        newValue -> config.hud.label.labelFrameColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Color> labelBackgroundColorOption = Option.<Color>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_color")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_color", true))))
                .binding(
                        config.hud.label.labelBackgroundColor,
                        () -> config.hud.label.labelBackgroundColor,
                        newValue -> config.hud.label.labelBackgroundColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Integer> labelBackgoundOpacityOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_opacity")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "label", "background_opacity", true))))
                .binding(
                        config.hud.label.labelBackgroundOpacity,
                        () -> config.hud.label.labelBackgroundOpacity,
                        newValue -> config.hud.label.labelBackgroundOpacity = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 100)
                        .step(10)
                        .formatValue(value -> Component.literal(String.format("%d%%", value)))
                )
                .build();
        group.option(enableLabelFrameOption);
        group.option(labelFrameColorOption);
        group.option(labelBackgroundColorOption);
        group.option(labelBackgoundOpacityOption);

        return group.build();
    }

    private static OptionGroup createTextGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "text")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "text", true))));

        Option<Color> textColorOption = Option.<Color>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "color")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "color", true))))
                .binding(
                        config.hud.text.textColor,
                        () -> config.hud.text.textColor,
                        newValue -> config.hud.text.textColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Boolean> enableTextShadowsOption = Option.<Boolean>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "enable_shadows")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "enable_shadows", true))))
                .binding(
                        config.hud.text.enableTextShadows,
                        () -> config.hud.text.enableTextShadows,
                        newValue -> config.hud.text.enableTextShadows = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Integer> textOpacityOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "opacity")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "opacity", true))))
                .binding(
                        config.hud.text.textOpacity,
                        () -> config.hud.text.textOpacity,
                        newValue -> config.hud.text.textOpacity = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 100)
                        .step(10)
                        .formatValue(value -> Component.literal(String.format("%d%%", value)))
                )
                .build();
        Option<TextAlignMode> textAlignModeOption = Option.<TextAlignMode>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "align_mode")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "align_mode", true))))
                .binding(
                        config.hud.text.textAlignMode,
                        () -> config.hud.text.textAlignMode,
                        newValue -> config.hud.text.textAlignMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(TextAlignMode.class)
                        .formatValue(value -> ConfigTranslationHelper.translatableEnum(ConfigTranslationHelper.setEnumOptionFormatKey("hud", "text", "align_mode"), value)))
                .build();
        Option<LocalDateTimeMode> localDateTimeModeOption = Option.<LocalDateTimeMode>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "local_date_time_mode")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "text", "local_date_time_mode", true))))
                .binding(
                        config.hud.text.localDateTimeMode,
                        () -> config.hud.text.localDateTimeMode,
                        newValue -> config.hud.text.localDateTimeMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(LocalDateTimeMode.class)
                        .formatValue(value -> ConfigTranslationHelper.translatableEnum(ConfigTranslationHelper.setEnumOptionFormatKey("hud", "text", "local_date_time_mode"), value)))
                .build();

        group.option(textColorOption);
        group.option(enableTextShadowsOption);
        group.option(textOpacityOption);
        group.option(textAlignModeOption);
        group.option(localDateTimeModeOption);

        return group.build();
    }

    private static OptionGroup createLayoutGroup(ImagictHudConfig config) {

        OptionGroup.Builder group = OptionGroup.createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setGroup("hud", "layout")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setGroup("hud", "layout", true))));

        Option<Integer> labelWidthOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_width")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_width", true))))
                .binding(
                        config.hud.layout.labelWidth,
                        () -> config.hud.layout.labelWidth,
                        newValue -> config.hud.layout.labelWidth = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(50, 150)
                        .step(1)
                        .formatValue(value -> Component.translatable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> labelLineSpacingOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_line_spacing")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "label_line_spacing", true))))
                .binding(
                        config.hud.layout.labelLineSpacing,
                        () -> config.hud.layout.labelLineSpacing,
                        newValue -> config.hud.layout.labelLineSpacing = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(-5, 5)
                        .step(1)
                        .formatValue(value -> Component.translatable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> hudScaleOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "scale")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "scale", true))))
                .binding(
                        config.hud.layout.hudScale,
                        () -> config.hud.layout.hudScale,
                        newValue -> config.hud.layout.hudScale = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 8)
                        .step(1)
                        .formatValue(value -> Component.literal(String.format("%dx", value)))
                )
                .build();
        Option<Integer> positionXOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_x")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_x", true))))
                .binding(
                        config.hud.layout.positionX,
                        () -> config.hud.layout.positionX,
                        newValue -> config.hud.layout.positionX = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                        .formatValue(value -> Component.translatable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> positionYOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_y")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "position_y", true))))
                .binding(
                        config.hud.layout.positionY,
                        () -> config.hud.layout.positionY,
                        newValue -> config.hud.layout.positionY = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                        .formatValue(value -> Component.translatable(ConfigTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> offsetOption = Option.<Integer>createBuilder()
                .name(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "offset")))
                .description(OptionDescription.of(Component.translatable(ConfigTranslationHelper.setOption("hud", "layout", "offset", true))))
                .binding(
                        config.hud.layout.offset,
                        () -> config.hud.layout.offset,
                        newValue -> config.hud.layout.offset = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                )
                .build();
        group.option(labelWidthOption);
        group.option(labelLineSpacingOption);
        group.option(hudScaleOption);
        group.option(positionXOption);
        group.option(positionYOption);
        group.option(offsetOption);

        return group.build();
    }
}
