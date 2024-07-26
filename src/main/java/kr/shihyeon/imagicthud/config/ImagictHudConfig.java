package kr.shihyeon.imagicthud.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.config.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.enums.LabelTextAlignMode;
import kr.shihyeon.imagicthud.config.enums.LocalDateTimeMode;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.nio.file.Path;
import java.util.Arrays;

public class ImagictHudConfig {

    public static final Path CONFIG_PATH = YACLPlatform.getConfigDir().resolve(ImagictHud.MODID + ".json5");

    public static ConfigClassHandler<ImagictHudConfig> HANDLER = ConfigClassHandler.createBuilder(ImagictHudConfig.class)
            .id(Identifier.of(ImagictHud.MODID, ImagictHud.MODID + "_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(CONFIG_PATH)
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build())
            .build();

    // ----- Hud: Component ----- //
    @SerialEntry public boolean enableHud = true;
    @SerialEntry public boolean enableHeadHud = true;
    @SerialEntry public boolean enableTopCustomHud = false;
    @SerialEntry public boolean enableLocalDateTimeHud = false;
    @SerialEntry public boolean enableNicknameHud = true;
    @SerialEntry public boolean enableCoordinatesHud = true;
    @SerialEntry public boolean enableBiomeHud = true;
    @SerialEntry public boolean enableBottomCustomHud = false;
    // ----- Hud: Head ----- //
    @SerialEntry public HeadRenderMode headRenderMode = HeadRenderMode.BOLD;
    // ----- Hud: Label ----- //
    @SerialEntry public boolean enableLabelFrame = true;
    @SerialEntry public Color labelFrameColor = new Color(0x000000);
    @SerialEntry public Color labelBackgroundColor = new Color(0x444444);
    @SerialEntry public int labelBackgoundOpacity = 70;
    // ----- Hud: CustomLabel ----- //
    @SerialEntry public String topCustomLabelText = "Custom Text";
    @SerialEntry public String bottomCustomLabelText = "Custom Text";
    // ----- Hud: LabelText ----- //
    @SerialEntry public Color labelTextColor = new Color(0xFFFFFF);
    @SerialEntry public boolean enableLabelTextShadows = false;
    @SerialEntry public int labelTextOpacity = 90;
    @SerialEntry public LabelTextAlignMode labelTextAlignMode = LabelTextAlignMode.CENTER;
    @SerialEntry public LocalDateTimeMode localDateTimeMode = LocalDateTimeMode.DATE_AND_TIME;
    // ----- Hud: Layout ----- //
    @SerialEntry public int labelWidth = 100;
    @SerialEntry public int labelTextLineSpacing = 0;
    @SerialEntry public float hudScale = 1.0f;
    @SerialEntry public int xPosition = 0;
    @SerialEntry public int yPosition = 0;
    @SerialEntry public int offset = 10;

    // ----- Indicator: Component ----- //
    @SerialEntry public boolean enableIndicator = true;
    // ----- Indicator: Display ----- //
    @SerialEntry public boolean attackingAt = false;
    @SerialEntry public boolean lookingAt = true;
    @SerialEntry public boolean damagedOnly = false;
    @SerialEntry public int duration = 10;
    @SerialEntry public int reach = 3;
    // ----- Indicator: Entities ----- //
    @SerialEntry public boolean playerEntities = true;
    @SerialEntry public boolean selfPlayerEntity = false;
    @SerialEntry public boolean passiveEntities = true;
    @SerialEntry public boolean hostileEntities = true;

    /**
     * Screen
     */
    public static Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {

            /**
             * Category: Hud
             */
            var hudCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(setCategory("hud")));

            /**
             * Category: Hud
             * Group: Component
             */
            var hudComponentGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "component")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "component", true))));

            var enableHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_hud", true))))
                    .binding(
                            defaults.enableHud,
                            () -> config.enableHud,
                            newValue -> config.enableHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableHeadHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_head_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_head_hud", true))))
                    .binding(
                            defaults.enableHeadHud,
                            () -> config.enableHeadHud,
                            newValue -> config.enableHeadHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableTopCustomHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_top_custom_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_top_custom_hud", true))))
                    .binding(
                            defaults.enableTopCustomHud,
                            () -> config.enableTopCustomHud,
                            newValue -> config.enableTopCustomHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableLocalDateTimeHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_local_date_time_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_local_date_time_hud", true))))
                    .binding(
                            defaults.enableLocalDateTimeHud,
                            () -> config.enableLocalDateTimeHud,
                            newValue -> config.enableLocalDateTimeHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableNicknameHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_nickname_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_nickname_hud", true))))
                    .binding(
                            defaults.enableNicknameHud,
                            () -> config.enableNicknameHud,
                            newValue -> config.enableNicknameHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableCoordinatesHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_coordinates_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_coordinates_hud", true))))
                    .binding(
                            defaults.enableCoordinatesHud,
                            () -> config.enableCoordinatesHud,
                            newValue -> config.enableCoordinatesHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableBiomeHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_biome_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_biome_hud", true))))
                    .binding(
                            defaults.enableBiomeHud,
                            () -> config.enableBiomeHud,
                            newValue -> config.enableBiomeHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableBottomCustomHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "component", "enable_bottom_custom_hud")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "component", "enable_bottom_custom_hud", true))))
                    .binding(
                            defaults.enableBottomCustomHud,
                            () -> config.enableBottomCustomHud,
                            newValue -> config.enableBottomCustomHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            hudComponentGroup.option(enableHudOption);
            hudComponentGroup.option(enableHeadHudOption);
            hudComponentGroup.option(enableTopCustomHudOption);
            hudComponentGroup.option(enableLocalDateTimeHudOption);
            hudComponentGroup.option(enableNicknameHudOption);
            hudComponentGroup.option(enableCoordinatesHudOption);
            hudComponentGroup.option(enableBiomeHudOption);
            hudComponentGroup.option(enableBottomCustomHudOption);

            /**
             * Category: Hud
             * Group: Head
             */
            var hudHeadGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "head")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "head", true))));

            var headRenderModeOption = Option.<HeadRenderMode>createBuilder()
                    .name(Text.translatable(setOption("hud", "head", "render_mode")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "head", "render_mode", true))))
                    .binding(
                            defaults.headRenderMode,
                            () -> config.headRenderMode,
                            newValue -> config.headRenderMode = newValue
                    )
                    .controller(option -> EnumControllerBuilder.create(option)
                            .enumClass(HeadRenderMode.class)
                            .formatValue(value -> Text.translatable(setEnumOptionFormatKey("hud", "head", "render_mode") + value.name().toLowerCase(), value)))
                    .build();
            hudHeadGroup.option(headRenderModeOption);

            /**
             * Category: Hud
             * Group: Label
             */
            var hudLabelGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "label")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "label", true))));

            var enableLabelFrameOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "label", "enable_frame")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label", "enable_frame", true))))
                    .binding(
                            defaults.enableLabelFrame,
                            () -> config.enableLabelFrame,
                            newValue -> config.enableLabelFrame = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var labelFrameColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(setOption("hud", "label", "frame_color")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label", "frame_color", true))))
                    .binding(
                            defaults.labelFrameColor,
                            () -> config.labelFrameColor,
                            newValue -> config.labelFrameColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var labelBackgroundColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(setOption("hud", "label", "background_color")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label", "background_color", true))))
                    .binding(
                            defaults.labelBackgroundColor,
                            () -> config.labelBackgroundColor,
                            newValue -> config.labelBackgroundColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var labelBackgoundOpacityOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "label", "background_opacity")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label", "background_opacity", true))))
                    .binding(
                            defaults.labelBackgoundOpacity,
                            () -> config.labelBackgoundOpacity,
                            newValue -> config.labelBackgoundOpacity = newValue
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

            /**
             * Category: Hud
             * Group: CustomLabel
             */
            var hudCustomLabelGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "custom_label")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "custom_label", true))));

            var topCustomLabelTextOption = Option.<String>createBuilder()
                    .name(Text.translatable(setOption("hud", "custom_label", "top_text")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "custom_label", "top_text", true))))
                    .binding(
                            defaults.topCustomLabelText,
                            () -> config.topCustomLabelText,
                            newValue -> config.topCustomLabelText = newValue
                    )
                    .controller(StringControllerBuilder::create)
                    .build();
            var bottomCustomLabelTextOption = Option.<String>createBuilder()
                    .name(Text.translatable(setOption("hud", "custom_label", "bottom_text")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "custom_label", "bottom_text", true))))
                    .binding(
                            defaults.bottomCustomLabelText,
                            () -> config.bottomCustomLabelText,
                            newValue -> config.bottomCustomLabelText = newValue
                    )
                    .controller(StringControllerBuilder::create)
                    .build();
            hudCustomLabelGroup.option(topCustomLabelTextOption);
            hudCustomLabelGroup.option(bottomCustomLabelTextOption);

            /**
             * Category: Hud
             * Group: LabelText
             */
            var hudLabelTextGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "label_text")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "label_text", true))));

            var labelTextColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(setOption("hud", "label_text", "color")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label_text", "color", true))))
                    .binding(
                            defaults.labelTextColor,
                            () -> config.labelTextColor,
                            newValue -> config.labelTextColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var enableLabelTextShadowsOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("hud", "label_text", "enable_shadows")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label_text", "enable_shadows", true))))
                    .binding(
                            defaults.enableLabelTextShadows,
                            () -> config.enableLabelTextShadows,
                            newValue -> config.enableLabelTextShadows = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var labelTextOpacityOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "label_text", "opacity")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label_text", "opacity", true))))
                    .binding(
                            defaults.labelTextOpacity,
                            () -> config.labelTextOpacity,
                            newValue -> config.labelTextOpacity = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 100)
                            .step(10)
                            .formatValue(value -> Text.literal(String.format("%d%%", value)))
                    )
                    .build();
            var labelTextAlignModeOption = Option.<LabelTextAlignMode>createBuilder()
                    .name(Text.translatable(setOption("hud", "label_text", "align_mode")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label_text", "align_mode", true))))
                    .binding(
                            defaults.labelTextAlignMode,
                            () -> config.labelTextAlignMode,
                            newValue -> config.labelTextAlignMode = newValue
                    )
                    .controller(option -> EnumControllerBuilder.create(option)
                            .enumClass(LabelTextAlignMode.class)
                            .formatValue(value -> Text.translatable(setEnumOptionFormatKey("hud", "label_text", "align_mode") + value.name().toLowerCase(), value)))
                    .build();
            var localDateTimeModeOption = Option.<LocalDateTimeMode>createBuilder()
                    .name(Text.translatable(setOption("hud", "label_text", "local_date_time_mode")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "label_text", "local_date_time_mode", true))))
                    .binding(
                            defaults.localDateTimeMode,
                            () -> config.localDateTimeMode,
                            newValue -> config.localDateTimeMode = newValue
                    )
                    .controller(option -> EnumControllerBuilder.create(option)
                            .enumClass(LocalDateTimeMode.class)
                            .formatValue(value -> Text.translatable(setEnumOptionFormatKey("hud", "label_text", "local_date_time_mode") + value.name().toLowerCase(), value)))
                    .build();
            hudLabelTextGroup.option(labelTextColorOption);
            hudLabelTextGroup.option(enableLabelTextShadowsOption);
            hudLabelTextGroup.option(labelTextOpacityOption);
            hudLabelTextGroup.option(labelTextAlignModeOption);
            hudLabelTextGroup.option(localDateTimeModeOption);

            /**
             * Category: Hud
             * Group: Layout
             */
            var hudLayoutGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("hud", "layout")))
                    .description(OptionDescription.of(Text.translatable(setGroup("hud", "layout", true))));

            var labelWidthOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "label_width")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "label_width", true))))
                    .binding(
                            defaults.labelWidth,
                            () -> config.labelWidth,
                            newValue -> config.labelWidth = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(50, 150)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var labelTextLineSpacingOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "label_text_line_spacing")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "label_text_line_spacing", true))))
                    .binding(
                            defaults.labelTextLineSpacing,
                            () -> config.labelTextLineSpacing,
                            newValue -> config.labelTextLineSpacing = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(-5, 5)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var hudScaleOption = Option.<Float>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "scale")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "scale", true))))
                    .binding(
                            defaults.hudScale,
                            () -> config.hudScale,
                            newValue -> config.hudScale = newValue
                    )
                    .controller(option -> FloatSliderControllerBuilder.create(option)
                            .range(.5f, 2.f)
                            .step(.1f)
                            .formatValue(value -> Text.literal(String.format("%.1fx", value)))
                    )
                    .build();
            var xPositionOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "x_position")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "x_position", true))))
                    .binding(
                            defaults.xPosition,
                            () -> config.xPosition,
                            newValue -> config.xPosition = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var yPositionOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "y_position")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "y_position", true))))
                    .binding(
                            defaults.yPosition,
                            () -> config.yPosition,
                            newValue -> config.yPosition = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var offsetOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("hud", "layout", "offset")))
                    .description(OptionDescription.of(Text.translatable(setOption("hud", "layout", "offset", true))))
                    .binding(
                            defaults.offset,
                            () -> config.offset,
                            newValue -> config.offset = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_rate"), value))
                    )
                    .build();
            hudLayoutGroup.option(labelWidthOption);
            hudLayoutGroup.option(labelTextLineSpacingOption);
            hudLayoutGroup.option(hudScaleOption);
            hudLayoutGroup.option(xPositionOption);
            hudLayoutGroup.option(yPositionOption);
            hudLayoutGroup.option(offsetOption);

            hudCategory.group(hudComponentGroup.build());
            hudCategory.group(hudHeadGroup.build());
            hudCategory.group(hudLabelGroup.build());
            hudCategory.group(hudCustomLabelGroup.build());
            hudCategory.group(hudLabelTextGroup.build());
            hudCategory.group(hudLayoutGroup.build());

            /**
             * Category: Indicator
             */
            var indicatorCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(setCategory("indicator")));

            /**
             * Category: Indicator
             * Group: Component
             */
            var indicatorComponentGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("indicator", "component")))
                    .description(OptionDescription.of(Text.translatable(setGroup("indicator", "component", true))));

            var enableIndicatorOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "component", "enable_indicator")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "component", "enable_indicator", true))))
                    .binding(
                            defaults.enableIndicator,
                            () -> config.enableIndicator,
                            newValue -> config.enableIndicator = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            indicatorComponentGroup.option(enableIndicatorOption);

            /**
             * Category: Indicator
             * Group: Display
             */
            var indicatorDisplayGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("indicator", "display")))
                    .description(OptionDescription.of(Text.translatable(setGroup("indicator", "display", true))));

            var attackingAtOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "display", "attacking_at")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "display", "attacking_at", true))))
                    .binding(
                            defaults.attackingAt,
                            () -> config.attackingAt,
                            newValue -> config.attackingAt = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var lookingAtOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "display", "looking_at")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "display", "looking_at", true))))
                    .binding(
                            defaults.lookingAt,
                            () -> config.lookingAt,
                            newValue -> config.lookingAt = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var damagedOnlyOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "display", "damaged_only")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "display", "damaged_only", true))))
                    .binding(
                            defaults.damagedOnly,
                            () -> config.damagedOnly,
                            newValue -> config.damagedOnly = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var durationOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("indicator", "display", "duration")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "display", "duration", true))))
                    .binding(
                            defaults.duration,
                            () -> config.duration,
                            newValue -> config.duration = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 120)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_seconds"), value))
                    )
                    .build();
            var reachOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(setOption("indicator", "display", "reach")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "display", "reach", true))))
                    .binding(
                            defaults.reach,
                            () -> config.reach,
                            newValue -> config.reach = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(3, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(setOptionFormatKey("int_meter"), value))
                    )
                    .build();
            indicatorDisplayGroup.option(attackingAtOption);
            indicatorDisplayGroup.option(lookingAtOption);
            indicatorDisplayGroup.option(damagedOnlyOption);
            indicatorDisplayGroup.option(durationOption);
            indicatorDisplayGroup.option(reachOption);

            /**
             * Category: Indicator
             * Group: Entities
             */
            var indicatorEntitiesGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(setGroup("indicator", "entities")))
                    .description(OptionDescription.of(Text.translatable(setGroup("indicator", "entities", true))));

            var playerEntitiesOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "entities", "player_entities")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "entities", "player_entities", true))))
                    .binding(
                            defaults.playerEntities,
                            () -> config.playerEntities,
                            newValue -> config.playerEntities = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var selfPlayerEntityOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "entities", "self_player_entity")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "entities", "self_player_entity", true))))
                    .binding(
                            defaults.selfPlayerEntity,
                            () -> config.selfPlayerEntity,
                            newValue -> config.selfPlayerEntity = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var passiveEntitiesOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "entities", "passive_entities")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "entities", "passive_entities", true))))
                    .binding(
                            defaults.passiveEntities,
                            () -> config.passiveEntities,
                            newValue -> config.passiveEntities = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var hostileEntitiesOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(setOption("indicator", "entities", "hostile_entities")))
                    .description(OptionDescription.of(Text.translatable(setOption("indicator", "entities", "hostile_entities", true))))
                    .binding(
                            defaults.hostileEntities,
                            () -> config.hostileEntities,
                            newValue -> config.hostileEntities = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            indicatorEntitiesGroup.option(playerEntitiesOption);
            indicatorEntitiesGroup.option(selfPlayerEntityOption);
            indicatorEntitiesGroup.option(passiveEntitiesOption);
            indicatorEntitiesGroup.option(hostileEntitiesOption);

            indicatorCategory.group(indicatorComponentGroup.build());
            indicatorCategory.group(indicatorDisplayGroup.build());
            indicatorCategory.group(indicatorEntitiesGroup.build());

            /**
             * Category: Hotbar
             */
            var hotbarCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(setCategory("hotbar")));



            /**
             * Builder: HudCategory, ...
             */
            return builder
                    .title(Text.translatable("yacl3." + ImagictHud.MODID + ".config.title"))
                    .categories(Arrays.asList(
                            hudCategory.build(),
                            indicatorCategory.build()
                            //hotbarCategory.build()
                    ));

        }).generateScreen(parent);
    }

    // Translate Key
    private static String setCategory(String category) {
        return setCategory(category, false);
    }

    private static String setCategory(String category, boolean useDescription) {
        String categoryDesc;
        if (useDescription) {
            categoryDesc = category + ".description";
        } else {
            categoryDesc = category + ".title";
        }
        return "yacl3." + ImagictHud.MODID + ".config." + categoryDesc;
    }

    private static String setGroup(String category, String group) {
        return setGroup(category, group, false);
    }

    private static String setGroup(String category, String group, boolean useDescription) {
        String groupDesc;
        if (useDescription) {
            groupDesc = group + ".description";
        } else {
            groupDesc = group + ".title";
        }
        return "yacl3." + ImagictHud.MODID + ".config." + category + "." + groupDesc;
    }

    private static String setOption(String category, String group, String option) {
        return setOption(category, group, option, false, -1);
    }

    private static String setOption(String category, String group, String option, boolean useDescription) {
        return setOption(category, group, option, useDescription, -1);
    }

    private static String setOption(String category, String group, String option, boolean useDescription, int line) {
        String optionDesc;
        if (useDescription) {
            if (line == -1) {
                optionDesc = option + ".description";
            } else {
                optionDesc = option + ".description." + line;
            }
        } else {
            optionDesc = option + ".title";
        }
        return "yacl3." + ImagictHud.MODID + ".config." + category + "." + group + "." + optionDesc;
    }

    private static String setEnumOptionFormatKey(String category, String group, String option) {
        return "yacl3." + ImagictHud.MODID + ".config." + category + "." + group + "." + option + ".";
    }

    private static String setOptionFormatKey(String typeAndUnit) {
        return "yacl3." + ImagictHud.MODID + ".config.format_key." + typeAndUnit;
    }
}
