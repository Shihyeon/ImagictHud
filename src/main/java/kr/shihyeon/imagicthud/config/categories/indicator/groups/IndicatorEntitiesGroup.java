package kr.shihyeon.imagicthud.config.categories.indicator.groups;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

public class IndicatorEntitiesGroup {

    @SerialEntry public boolean playerEntities = true;
    @SerialEntry public boolean selfPlayerEntity = false;
    @SerialEntry public boolean passiveEntities = true;
    @SerialEntry public boolean hostileEntities = true;
}
