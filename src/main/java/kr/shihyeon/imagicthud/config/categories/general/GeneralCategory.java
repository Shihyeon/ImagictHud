package kr.shihyeon.imagicthud.config.categories.general;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import kr.shihyeon.imagicthud.config.categories.general.groups.GeneralHudGroup;
import kr.shihyeon.imagicthud.config.categories.general.groups.GeneralIndicatorGroup;

public class GeneralCategory {

    @SerialEntry public GeneralHudGroup hud = new GeneralHudGroup();
    @SerialEntry public GeneralIndicatorGroup indicator = new GeneralIndicatorGroup();
}
