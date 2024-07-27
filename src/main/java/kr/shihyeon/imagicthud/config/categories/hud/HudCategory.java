package kr.shihyeon.imagicthud.config.categories.hud;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import kr.shihyeon.imagicthud.config.categories.hud.groups.*;

public class HudCategory {

    @SerialEntry public HudDisplayGroup display = new HudDisplayGroup();
    @SerialEntry public HudHeadGroup head = new HudHeadGroup();
    @SerialEntry public HudLabelGroup label = new HudLabelGroup();
    @SerialEntry public HudTextGroup text = new HudTextGroup();
    @SerialEntry public HudLayoutGroup layout = new HudLayoutGroup();
}
