package kr.shihyeon.imagicthud.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import kr.shihyeon.imagicthud.ImagictHud;
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
    @SerialEntry public boolean enableLabelCenteredText = true;
    // ----- Hud: Layout ----- //
    @SerialEntry public int labelWidth = 100;
    @SerialEntry public int lebelTextLineSpacing = 0;
    @SerialEntry public float scale = 1.0f;
    @SerialEntry public int xPosition = 0;
    @SerialEntry public int yPosition = 0;
    @SerialEntry public int offset = 10;

    /*
     * Screen
     */
    public static Screen createScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {

            /*
             * Category: Hud
             */
            var hudCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(defaults.setCategory("hud")));

            /*
             * Category: Hud
             * Group: Component
             */
            var hudComponentGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(defaults.setGroup("hud", "component")))
                    .description(OptionDescription.of(Text.translatable(defaults.setGroup("hud", "component", true))));

            var enableHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_hud", true))))
                    .binding(
                            defaults.enableHud,
                            () -> config.enableHud,
                            newValue -> config.enableHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableHeadHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_head_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_head_hud", true))))
                    .binding(
                            defaults.enableHeadHud,
                            () -> config.enableHeadHud,
                            newValue -> config.enableHeadHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableTopCustomHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_top_custom_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_top_custom_hud", true))))
                    .binding(
                            defaults.enableTopCustomHud,
                            () -> config.enableTopCustomHud,
                            newValue -> config.enableTopCustomHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableLocalDateTimeHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_local_date_time_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_local_date_time_hud", true))))
                    .binding(
                            defaults.enableLocalDateTimeHud,
                            () -> config.enableLocalDateTimeHud,
                            newValue -> config.enableLocalDateTimeHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableNicknameHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_nickname_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_nickname_hud", true))))
                    .binding(
                            defaults.enableNicknameHud,
                            () -> config.enableNicknameHud,
                            newValue -> config.enableNicknameHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableCoordinatesHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_coordinates_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_coordinates_hud", true))))
                    .binding(
                            defaults.enableCoordinatesHud,
                            () -> config.enableCoordinatesHud,
                            newValue -> config.enableCoordinatesHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableBiomeHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_biome_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_biome_hud", true))))
                    .binding(
                            defaults.enableBiomeHud,
                            () -> config.enableBiomeHud,
                            newValue -> config.enableBiomeHud = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var enableBottomCustomHudOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "component", "enable_bottom_custom_hud")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "component", "enable_bottom_custom_hud", true))))
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

            /*
             * Category: Hud
             * Group: Label
             */
            var hudLabelGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(defaults.setGroup("hud", "label")))
                    .description(OptionDescription.of(Text.translatable(defaults.setGroup("hud", "label", true))));

            var enableLabelFrameOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label", "enable_label_frame")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label", "enable_label_frame", true))))
                    .binding(
                            defaults.enableLabelFrame,
                            () -> config.enableLabelFrame,
                            newValue -> config.enableLabelFrame = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var labelFrameColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label", "label_frame_color")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label", "label_frame_color", true))))
                    .binding(
                            defaults.labelFrameColor,
                            () -> config.labelFrameColor,
                            newValue -> config.labelFrameColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var labelBackgroundColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label", "label_background_color")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label", "label_background_color", true))))
                    .binding(
                            defaults.labelBackgroundColor,
                            () -> config.labelBackgroundColor,
                            newValue -> config.labelBackgroundColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var labelBackgoundOpacityOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label", "label_background_opacity")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label", "label_background_opacity", true))))
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

            /*
             * Category: Hud
             * Group: CustomLabel
             */
            var hudCustomLabelGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(defaults.setGroup("hud", "custom_label")))
                    .description(OptionDescription.of(Text.translatable(defaults.setGroup("hud", "custom_label", true))));

            var topCustomLabelTextOption = Option.<String>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "custom_label", "top_custom_label_text")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "custom_label", "top_custom_label_text", true))))
                    .binding(
                            defaults.topCustomLabelText,
                            () -> config.topCustomLabelText,
                            newValue -> config.topCustomLabelText = newValue
                    )
                    .controller(StringControllerBuilder::create)
                    .build();
            var bottomCustomLabelTextOption = Option.<String>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "custom_label", "bottom_custom_label_text")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "custom_label", "bottom_custom_label_text", true))))
                    .binding(
                            defaults.bottomCustomLabelText,
                            () -> config.bottomCustomLabelText,
                            newValue -> config.bottomCustomLabelText = newValue
                    )
                    .controller(StringControllerBuilder::create)
                    .build();
            hudCustomLabelGroup.option(topCustomLabelTextOption);
            hudCustomLabelGroup.option(bottomCustomLabelTextOption);

            /*
             * Category: Hud
             * Group: LabelText
             */
            var hudLabelTextGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(defaults.setGroup("hud", "label_text")))
                    .description(OptionDescription.of(Text.translatable(defaults.setGroup("hud", "label_text", true))));

            var labelTextColorOption = Option.<Color>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label_text", "label_text_color")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label_text", "label_text_color", true))))
                    .binding(
                            defaults.labelTextColor,
                            () -> config.labelTextColor,
                            newValue -> config.labelTextColor = newValue
                    )
                    .controller(ColorControllerBuilder::create)
                    .build();
            var enableLabelTextShadowsOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label_text", "enable_label_text_shadows")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label_text", "enable_label_text_shadows", true))))
                    .binding(
                            defaults.enableLabelTextShadows,
                            () -> config.enableLabelTextShadows,
                            newValue -> config.enableLabelTextShadows = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            var labelTextOpacityOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label_text", "label_text_opacity")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label_text", "label_text_opacity", true))))
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
            var enableLabelCenteredTextOption = Option.<Boolean>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "label_text", "label_centered_text")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "label_text", "label_centered_text", true))))
                    .binding(
                            defaults.enableLabelCenteredText,
                            () -> config.enableLabelCenteredText,
                            newValue -> config.enableLabelCenteredText = newValue
                    )
                    .controller(TickBoxControllerBuilder::create)
                    .build();
            hudLabelTextGroup.option(labelTextColorOption);
            hudLabelTextGroup.option(enableLabelTextShadowsOption);
            hudLabelTextGroup.option(labelTextOpacityOption);
            hudLabelTextGroup.option(enableLabelCenteredTextOption);

            /*
             * Category: Hud
             * Group: Layout
             */
            var hudLayoutGroup = OptionGroup.createBuilder()
                    .name(Text.translatable(defaults.setGroup("hud", "layout")))
                    .description(OptionDescription.of(Text.translatable(defaults.setGroup("hud", "layout", true))));

            var labelWidthOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "label_width")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "label_width", true))))
                    .binding(
                            defaults.labelWidth,
                            () -> config.labelWidth,
                            newValue -> config.labelWidth = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(50, 150)
                            .step(1)
                            .formatValue(value -> Text.translatable(config.setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var lebelTextLineSpacingOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "label_text_line_spacing")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "label_text_line_spacing", true))))
                    .binding(
                            defaults.lebelTextLineSpacing,
                            () -> config.lebelTextLineSpacing,
                            newValue -> config.lebelTextLineSpacing = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(-5, 5)
                            .step(1)
                            .formatValue(value -> Text.translatable(config.setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var scaleOption = Option.<Float>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "scale")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "scale", true))))
                    .binding(
                            defaults.scale,
                            () -> config.scale,
                            newValue -> config.scale = newValue
                    )
                    .controller(option -> FloatSliderControllerBuilder.create(option)
                            .range(.5f, 2.f)
                            .step(.1f)
                            .formatValue(value -> Text.literal(String.format("%.1fx", value)))
                    )
                    .build();
            var xPositionOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "x_position")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "x_position", true))))
                    .binding(
                            defaults.xPosition,
                            () -> config.xPosition,
                            newValue -> config.xPosition = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(config.setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var yPositionOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "y_position")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "y_position", true))))
                    .binding(
                            defaults.yPosition,
                            () -> config.yPosition,
                            newValue -> config.yPosition = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(config.setOptionFormatKey("int_pixels"), value))
                    )
                    .build();
            var offsetOption = Option.<Integer>createBuilder()
                    .name(Text.translatable(defaults.setOption("hud", "layout", "offset")))
                    .description(OptionDescription.of(Text.translatable(defaults.setOption("hud", "layout", "offset", true))))
                    .binding(
                            defaults.offset,
                            () -> config.offset,
                            newValue -> config.offset = newValue
                    )
                    .controller(option -> IntegerSliderControllerBuilder.create(option)
                            .range(0, 50)
                            .step(1)
                            .formatValue(value -> Text.translatable(config.setOptionFormatKey("int_rate"), value))
                    )
                    .build();
            hudLayoutGroup.option(labelWidthOption);
            hudLayoutGroup.option(lebelTextLineSpacingOption);
            hudLayoutGroup.option(scaleOption);
            hudLayoutGroup.option(xPositionOption);
            hudLayoutGroup.option(yPositionOption);
            hudLayoutGroup.option(offsetOption);

            hudCategory.group(hudComponentGroup.build());
            hudCategory.group(hudLabelGroup.build());
            hudCategory.group(hudCustomLabelGroup.build());
            hudCategory.group(hudLabelTextGroup.build());
            hudCategory.group(hudLayoutGroup.build());

            /*
             * Category: Hotbar
             */
            var indicatorCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(defaults.setCategory("indicator")));



            /*
             * Category: Hotbar
             */
            var hotbarCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable(defaults.setCategory("hotbar")));



            /*
             * Builder: HudCategory, ...
             */
            return builder
                    .title(Text.translatable("imagicthud.config.title"))
                    .categories(Arrays.asList(
                            hudCategory.build()//,
                            //hotbarCategory.build()
                    ));

        }).generateScreen(parent);
    }

    // Translate Key
    private String setCategory(String category) {
        return setCategory(category, false);
    }

    private String setCategory(String category, boolean useDescription) {
        String categoryDesc;
        if (useDescription) {
            categoryDesc = category + ".description";
        } else {
            categoryDesc = category + ".title";
        }
        return "imagicthud.config." + categoryDesc;
    }

    private String setGroup(String category, String group) {
        return setGroup(category, group, false);
    }

    private String setGroup(String category, String group, boolean useDescription) {
        String groupDesc;
        if (useDescription) {
            groupDesc = group + ".description";
        } else {
            groupDesc = group + ".title";
        }
        return "imagicthud.config." + category + "." + groupDesc;
    }

    private String setOption(String category, String group, String option) {
        return setOption(category, group, option, false);
    }

    private String setOption(String category, String group, String option, boolean useDescription) {
        String optionDesc;
        if (useDescription) {
            optionDesc = option + ".description";
        } else {
            optionDesc = option + ".title";
        }
        return "imagicthud.config." + category + "." + group + "." + optionDesc;
    }

    private String setOptionFormatKey(String typeAndUnit) {
        return "imagicthud.config.format_key." + typeAndUnit;
    }
}
