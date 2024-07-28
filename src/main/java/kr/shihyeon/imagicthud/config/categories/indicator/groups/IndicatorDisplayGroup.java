package kr.shihyeon.imagicthud.config.categories.indicator.groups;

import com.google.gson.annotations.Expose;

public class IndicatorDisplayGroup {

    @Expose public boolean attackingAt = true;
    @Expose public boolean lookingAt = true;
    @Expose public boolean damagedOnly = false;
    @Expose public int duration = 10;
    @Expose public int reach = 20;
}
