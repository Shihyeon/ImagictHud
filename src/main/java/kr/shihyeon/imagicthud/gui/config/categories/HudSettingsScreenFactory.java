package kr.shihyeon.imagicthud.gui.config.categories;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.enums.TextAlignMode;
import kr.shihyeon.imagicthud.config.enums.LocalDateTimeMode;
import kr.shihyeon.imagicthud.util.FactoryTranslationHelper;
import net.minecraft.text.Text;

import java.awt.*;

public class HudSettingsScreenFactory {

    public static ConfigCategory createHudCategory(ImagictHudConfig defaults, ImagictHudConfig config) {

        ConfigCategory.Builder hudCategory = ConfigCategory.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setCategory("hud")))
                .tooltip(Text.translatable(FactoryTranslationHelper.setCategory("hud", true)));

        OptionGroup hudDisplayGroup = createHudDisplayGroup(defaults, config);
        OptionGroup hudHeadGroup = createHudHeadGroup(defaults, config);
        OptionGroup hudLabelGroup = createHudLabelGroup(defaults, config);
        OptionGroup hudTextGroup = createHudTextGroup(defaults, config);
        OptionGroup hudLayoutGroup = createHudLayoutGroup(defaults, config);

        hudCategory.group(hudDisplayGroup);
        hudCategory.group(hudHeadGroup);
        hudCategory.group(hudLabelGroup);
        hudCategory.group(hudTextGroup);
        hudCategory.group(hudLayoutGroup);

        return hudCategory.build();
    }

    private static OptionGroup createHudDisplayGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder hudDisplayGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("hud", "display")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("hud", "display", true))));

        Option<Boolean> enableHeadOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_head")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_head", true))))
                .binding(
                        defaults.hud.display.enableHead,
                        () -> config.hud.display.enableHead,
                        newValue -> config.hud.display.enableHead = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableTopCustomLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_top_custom_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_top_custom_label", true))))
                .binding(
                        defaults.hud.display.enableTopCustomLabel,
                        () -> config.hud.display.enableTopCustomLabel,
                        newValue -> config.hud.display.enableTopCustomLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableLocalDateTimeLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_local_date_time_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_local_date_time_label", true))))
                .binding(
                        defaults.hud.display.enableLocalDateTimeLabel,
                        () -> config.hud.display.enableLocalDateTimeLabel,
                        newValue -> config.hud.display.enableLocalDateTimeLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableNicknameLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_nickname_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_nickname_label", true))))
                .binding(
                        defaults.hud.display.enableNicknameLabel,
                        () -> config.hud.display.enableNicknameLabel,
                        newValue -> config.hud.display.enableNicknameLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableCoordinatesLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_coordinates_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_coordinates_label", true))))
                .binding(
                        defaults.hud.display.enableCoordinatesLabel,
                        () -> config.hud.display.enableCoordinatesLabel,
                        newValue -> config.hud.display.enableCoordinatesLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableBiomeLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_biome_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_biome_label", true))))
                .binding(
                        defaults.hud.display.enableBiomeLabel,
                        () -> config.hud.display.enableBiomeLabel,
                        newValue -> config.hud.display.enableBiomeLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Boolean> enableBottomCustomLabelOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_bottom_custom_label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "display", "enable_bottom_custom_label", true))))
                .binding(
                        defaults.hud.display.enableBottomCustomLabel,
                        () -> config.hud.display.enableBottomCustomLabel,
                        newValue -> config.hud.display.enableBottomCustomLabel = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();

        hudDisplayGroup.option(enableHeadOption);
        hudDisplayGroup.option(enableTopCustomLabelOption);
        hudDisplayGroup.option(enableLocalDateTimeLabelOption);
        hudDisplayGroup.option(enableNicknameLabelOption);
        hudDisplayGroup.option(enableCoordinatesLabelOption);
        hudDisplayGroup.option(enableBiomeLabelOption);
        hudDisplayGroup.option(enableBottomCustomLabelOption);

        return hudDisplayGroup.build();
    }

    private static OptionGroup createHudHeadGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder hudHeadGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("hud", "head")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("hud", "head", true))));

        Option<HeadRenderMode> headRenderModeOption = Option.<HeadRenderMode>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "head", "render_mode")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "head", "render_mode", true))))
                .binding(
                        defaults.hud.head.headRenderMode,
                        () -> config.hud.head.headRenderMode,
                        newValue -> config.hud.head.headRenderMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(HeadRenderMode.class)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setEnumOptionFormatKey("hud", "head", "render_mode") + value.name().toLowerCase(), value)))
                .build();

        hudHeadGroup.option(headRenderModeOption);

        return hudHeadGroup.build();
    }

    private static OptionGroup createHudLabelGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder hudLabelGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("hud", "label")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("hud", "label", true))));

        Option<Boolean> enableLabelFrameOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "enable_frame")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "enable_frame", true))))
                .binding(
                        defaults.hud.label.enableLabelFrame,
                        () -> config.hud.label.enableLabelFrame,
                        newValue -> config.hud.label.enableLabelFrame = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Color> labelFrameColorOption = Option.<Color>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "frame_color")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "frame_color", true))))
                .binding(
                        defaults.hud.label.labelFrameColor,
                        () -> config.hud.label.labelFrameColor,
                        newValue -> config.hud.label.labelFrameColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Color> labelBackgroundColorOption = Option.<Color>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "background_color")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "background_color", true))))
                .binding(
                        defaults.hud.label.labelBackgroundColor,
                        () -> config.hud.label.labelBackgroundColor,
                        newValue -> config.hud.label.labelBackgroundColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Integer> labelBackgoundOpacityOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "background_opacity")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "label", "background_opacity", true))))
                .binding(
                        defaults.hud.label.labelBackgoundOpacity,
                        () -> config.hud.label.labelBackgoundOpacity,
                        newValue -> config.hud.label.labelBackgoundOpacity = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 100)
                        .step(10)
                        .formatValue(value -> Text.literal(String.format("%d%%", value)))
                )
                .build();
        hudLabelGroup.option(enableLabelFrameOption);
        hudLabelGroup.option(labelFrameColorOption);
        hudLabelGroup.option(labelBackgroundColorOption);
        hudLabelGroup.option(labelBackgoundOpacityOption);

        return hudLabelGroup.build();
    }

    private static OptionGroup createHudTextGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder hudTextGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("hud", "text")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("hud", "text", true))));

        Option<Color> textColorOption = Option.<Color>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "color")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "color", true))))
                .binding(
                        defaults.hud.text.textColor,
                        () -> config.hud.text.textColor,
                        newValue -> config.hud.text.textColor = newValue
                )
                .controller(ColorControllerBuilder::create)
                .build();
        Option<Boolean> enableTextShadowsOption = Option.<Boolean>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "enable_shadows")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "enable_shadows", true))))
                .binding(
                        defaults.hud.text.enableTextShadows,
                        () -> config.hud.text.enableTextShadows,
                        newValue -> config.hud.text.enableTextShadows = newValue
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
        Option<Integer> textOpacityOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "opacity")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "opacity", true))))
                .binding(
                        defaults.hud.text.textOpacity,
                        () -> config.hud.text.textOpacity,
                        newValue -> config.hud.text.textOpacity = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 100)
                        .step(10)
                        .formatValue(value -> Text.literal(String.format("%d%%", value)))
                )
                .build();
        Option<TextAlignMode> textAlignModeOption = Option.<TextAlignMode>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "align_mode")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "align_mode", true))))
                .binding(
                        defaults.hud.text.textAlignMode,
                        () -> config.hud.text.textAlignMode,
                        newValue -> config.hud.text.textAlignMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(TextAlignMode.class)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setEnumOptionFormatKey("hud", "text", "align_mode") + value.name().toLowerCase(), value)))
                .build();
        Option<LocalDateTimeMode> localDateTimeModeOption = Option.<LocalDateTimeMode>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "local_date_time_mode")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "local_date_time_mode", true))))
                .binding(
                        defaults.hud.text.localDateTimeMode,
                        () -> config.hud.text.localDateTimeMode,
                        newValue -> config.hud.text.localDateTimeMode = newValue
                )
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(LocalDateTimeMode.class)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setEnumOptionFormatKey("hud", "text", "local_date_time_mode") + value.name().toLowerCase(), value)))
                .build();
        Option<String> topCustomTextOption = Option.<String>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "top_custom_text")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "top_custom_text", true))))
                .binding(
                        defaults.hud.text.topCustomText,
                        () -> config.hud.text.topCustomText,
                        newValue -> config.hud.text.topCustomText = newValue
                )
                .controller(StringControllerBuilder::create)
                .build();
        Option<String> bottomCustomTextOption = Option.<String>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "bottom_custom_text")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "text", "bottom_custom_text", true))))
                .binding(
                        defaults.hud.text.bottomCustomText,
                        () -> config.hud.text.bottomCustomText,
                        newValue -> config.hud.text.bottomCustomText = newValue
                )
                .controller(StringControllerBuilder::create)
                .build();

        hudTextGroup.option(textColorOption);
        hudTextGroup.option(enableTextShadowsOption);
        hudTextGroup.option(textOpacityOption);
        hudTextGroup.option(textAlignModeOption);
        hudTextGroup.option(localDateTimeModeOption);
        hudTextGroup.option(topCustomTextOption);
        hudTextGroup.option(bottomCustomTextOption);

        return hudTextGroup.build();
    }

    private static OptionGroup createHudLayoutGroup(ImagictHudConfig defaults, ImagictHudConfig config) {

        OptionGroup.Builder hudLayoutGroup = OptionGroup.createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setGroup("hud", "layout")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setGroup("hud", "layout", true))));

        Option<Integer> labelWidthOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "label_width")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "label_width", true))))
                .binding(
                        defaults.hud.layout.labelWidth,
                        () -> config.hud.layout.labelWidth,
                        newValue -> config.hud.layout.labelWidth = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(50, 150)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> labelLineSpacingOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "label_line_spacing")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "label_line_spacing", true))))
                .binding(
                        defaults.hud.layout.labelLineSpacing,
                        () -> config.hud.layout.labelLineSpacing,
                        newValue -> config.hud.layout.labelLineSpacing = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(-5, 5)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Float> hudScaleOption = Option.<Float>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "scale")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "scale", true))))
                .binding(
                        defaults.hud.layout.hudScale,
                        () -> config.hud.layout.hudScale,
                        newValue -> config.hud.layout.hudScale = newValue
                )
                .controller(option -> FloatSliderControllerBuilder.create(option)
                        .range(.5f, 2.f)
                        .step(.1f)
                        .formatValue(value -> Text.literal(String.format("%.1fx", value)))
                )
                .build();
        Option<Integer> positionXOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "position_x")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "position_x", true))))
                .binding(
                        defaults.hud.layout.positionX,
                        () -> config.hud.layout.positionX,
                        newValue -> config.hud.layout.positionX = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> positionYOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "position_y")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "position_y", true))))
                .binding(
                        defaults.hud.layout.positionY,
                        () -> config.hud.layout.positionY,
                        newValue -> config.hud.layout.positionY = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                        .formatValue(value -> Text.translatable(FactoryTranslationHelper.setOptionFormatKey("int_pixels"), value))
                )
                .build();
        Option<Integer> offsetOption = Option.<Integer>createBuilder()
                .name(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "offset")))
                .description(OptionDescription.of(Text.translatable(FactoryTranslationHelper.setOption("hud", "layout", "offset", true))))
                .binding(
                        defaults.hud.layout.offset,
                        () -> config.hud.layout.offset,
                        newValue -> config.hud.layout.offset = newValue
                )
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 50)
                        .step(1)
                )
                .build();
        hudLayoutGroup.option(labelWidthOption);
        hudLayoutGroup.option(labelLineSpacingOption);
        hudLayoutGroup.option(hudScaleOption);
        hudLayoutGroup.option(positionXOption);
        hudLayoutGroup.option(positionYOption);
        hudLayoutGroup.option(offsetOption);

        return hudLayoutGroup.build();
    }
}
