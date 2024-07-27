package kr.shihyeon.imagicthud.config.categories.hud.groups;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.awt.*;

public class HudLabelGroup {

    @SerialEntry public boolean enableLabelFrame = true;
    @SerialEntry public Color labelFrameColor = new Color(0x000000);
    @SerialEntry public Color labelBackgroundColor = new Color(0x444444);
    @SerialEntry public int labelBackgoundOpacity = 70;
}
