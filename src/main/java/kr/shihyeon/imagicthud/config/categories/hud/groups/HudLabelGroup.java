package kr.shihyeon.imagicthud.config.categories.hud.groups;

import com.google.gson.annotations.Expose;

import java.awt.*;

public class HudLabelGroup {

    @Expose public boolean enableLabelFrame = true;
    @Expose public Color labelFrameColor = new Color(0x000000);
    @Expose public Color labelBackgroundColor = new Color(0x444444);
    @Expose public int labelBackgroundOpacity = 70;
}
