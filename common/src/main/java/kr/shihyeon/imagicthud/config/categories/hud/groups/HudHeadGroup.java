package kr.shihyeon.imagicthud.config.categories.hud.groups;

import com.google.gson.annotations.Expose;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.HeadRenderMode;

public class HudHeadGroup {

    @Expose public HeadRenderMode headRenderMode = HeadRenderMode.BOLD;
}
