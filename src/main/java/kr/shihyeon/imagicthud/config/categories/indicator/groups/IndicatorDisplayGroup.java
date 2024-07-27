package kr.shihyeon.imagicthud.config.categories.indicator.groups;

import dev.isxander.yacl3.config.v2.api.SerialEntry;

public class IndicatorDisplayGroup {

    @SerialEntry public boolean attackingAt = false;
    @SerialEntry public boolean lookingAt = true;
    @SerialEntry public boolean damagedOnly = false;
    @SerialEntry public int duration = 10;
    @SerialEntry public int reach = 20;
}
