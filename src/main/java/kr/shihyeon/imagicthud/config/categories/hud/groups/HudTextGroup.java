package kr.shihyeon.imagicthud.config.categories.hud.groups;

import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.TextAlignMode;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.LocalDateTimeMode;

import java.awt.*;

public class HudTextGroup {

    @Expose public Color textColor = new Color(0xFFFFFF);
    @Expose public boolean enableTextShadows = false;
    @Expose public int textOpacity = 90;
    @Expose public TextAlignMode textAlignMode = TextAlignMode.CENTER;
    @Expose public LocalDateTimeMode localDateTimeMode = LocalDateTimeMode.DATE_AND_TIME;
}
