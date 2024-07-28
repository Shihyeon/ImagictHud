package kr.shihyeon.imagicthud.config.categories.indicator;

import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.config.categories.ConfigCategory;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.IndicatorDisplayGroup;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.IndicatorEntitiesGroup;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.IndicatorGeneralGroup;

public class IndicatorCategory extends ConfigCategory {

    @Expose public IndicatorGeneralGroup general = new IndicatorGeneralGroup();
    @Expose public IndicatorDisplayGroup display = new IndicatorDisplayGroup();
    @Expose public IndicatorEntitiesGroup entities = new IndicatorEntitiesGroup();
}
