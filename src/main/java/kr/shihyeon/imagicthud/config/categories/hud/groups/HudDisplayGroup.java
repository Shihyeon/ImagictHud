package kr.shihyeon.imagicthud.config.categories.hud.groups;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

public class HudDisplayGroup {

    @SerialEntry public boolean enableHead = true;
    @SerialEntry public boolean enableTopCustomLabel = false;
    @SerialEntry public boolean enableLocalDateTimeLabel = false;
    @SerialEntry public boolean enableNicknameLabel = true;
    @SerialEntry public boolean enableCoordinatesLabel = true;
    @SerialEntry public boolean enableBiomeLabel = true;
    @SerialEntry public boolean enableBottomCustomLabel = false;
}
