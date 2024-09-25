package kr.shihyeon.imagicthud.config.categories.indicator.groups;

import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorBarMode;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorMode;

public class IndicatorDisplayGroup {

    @Expose public boolean attackingAt = true;
    @Expose public boolean lookingAt = true;
    @Expose public boolean damagedOnly = false;
    @Expose public IndicatorMode indicatorMode = IndicatorMode.BAR_AND_NAME_A;
    @Expose public IndicatorBarMode indicatorBarMode = IndicatorBarMode.BAR_AND_NUMBER;
    @Expose public int duration = 10;
    @Expose public int reach = 20;
}
