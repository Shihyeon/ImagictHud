package kr.shihyeon.imagicthud.config.categories.indicator.groups;

import com.google.gson.annotations.Expose;

public class IndicatorEntitiesGroup {

    @Expose public boolean playerEntities = true;
    @Expose public boolean selfPlayerEntity = false;
    @Expose public boolean passiveEntities = true;
    @Expose public boolean hostileEntities = true;
}
