package kr.shihyeon.imagicthud.config.categories.hud.groups;

import com.google.gson.annotations.Expose;

public class HudDisplayGroup {

    @Expose public boolean enableHead = true;
    @Expose public boolean enableLocalDateTimeLabel = false;
    @Expose public boolean enableNicknameLabel = true;
    @Expose public boolean enableCoordinatesLabel = true;
    @Expose public boolean enableBiomeLabel = true;
}
