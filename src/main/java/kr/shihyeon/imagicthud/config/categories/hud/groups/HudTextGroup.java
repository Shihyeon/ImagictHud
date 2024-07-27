package kr.shihyeon.imagicthud.config.categories.hud.groups;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import kr.shihyeon.imagicthud.config.enums.TextAlignMode;
import kr.shihyeon.imagicthud.config.enums.LocalDateTimeMode;

import java.awt.*;

public class HudTextGroup {

    @SerialEntry public Color textColor = new Color(0xFFFFFF);
    @SerialEntry public boolean enableTextShadows = false;
    @SerialEntry public int textOpacity = 90;
    @SerialEntry public TextAlignMode textAlignMode = TextAlignMode.CENTER;
    @SerialEntry public LocalDateTimeMode localDateTimeMode = LocalDateTimeMode.DATE_AND_TIME;
    @SerialEntry public String topCustomText = "Custom Text";
    @SerialEntry public String bottomCustomText = "Custom Text";
}
