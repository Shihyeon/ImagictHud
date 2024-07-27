package kr.shihyeon.imagicthud.config.categories.indicator;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.IndicatorDisplayGroup;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.IndicatorEntitiesGroup;

public class IndicatorCategory {

    @SerialEntry public IndicatorDisplayGroup display = new IndicatorDisplayGroup();
    @SerialEntry public IndicatorEntitiesGroup entities = new IndicatorEntitiesGroup();
}
