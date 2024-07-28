package kr.shihyeon.imagicthud.config.categories.hud;

import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.config.categories.ConfigCategory;
import kr.shihyeon.imagicthud.config.categories.hud.groups.*;

public class HudCategory extends ConfigCategory {

    @Expose public HudGeneralGroup general = new HudGeneralGroup();
    @Expose public HudDisplayGroup display = new HudDisplayGroup();
    @Expose public HudHeadGroup head = new HudHeadGroup();
    @Expose public HudLabelGroup label = new HudLabelGroup();
    @Expose public HudTextGroup text = new HudTextGroup();
    @Expose public HudLayoutGroup layout = new HudLayoutGroup();
}
