package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import net.minecraft.network.chat.Component;

public class ConfigTranslationHelper {

    public static String setCategory(String category) {
        return setCategory(category, false);
    }

    public static String setCategory(String category, boolean useDescription) {
        String categoryDesc = useDescription ? category + ".description" : category + ".title";
        return ImagictHudClient.MODID + ".config." + categoryDesc;
    }

    public static String setGroup(String category, String group) {
        return setGroup(category, group, false);
    }

    public static String setGroup(String category, String group, boolean useDescription) {
        String groupDesc = useDescription ? group + ".description" : group + ".title";
        return ImagictHudClient.MODID + ".config." + category + "." + groupDesc;
    }

    public static String setOption(String category, String group, String option) {
        return setOption(category, group, option, false, -1);
    }

    public static String setOption(String category, String group, String option, boolean useDescription) {
        return setOption(category, group, option, useDescription, -1);
    }

    public static String setOption(String category, String group, String option, boolean useDescription, int line) {
        String optionDesc = useDescription ? (line == -1 ? option + ".description" : option + ".description." + line) : option + ".title";
        return ImagictHudClient.MODID + ".config." + category + "." + group + "." + optionDesc;
    }

    public static String setEnumOptionFormatKey(String category, String group, String option) {
        return ImagictHudClient.MODID + ".config." + category + "." + group + "." + option + ".";
    }

    public static String setOptionFormatKey(String typeAndUnit) {
        return ImagictHudClient.MODID + ".config.format_key." + typeAndUnit;
    }

    public static Component translatableEnum(String baseKey, Enum<?> value) {
        String translationKey = baseKey + value.name().toLowerCase();
        return Component.translatable(translationKey);
    }
}
