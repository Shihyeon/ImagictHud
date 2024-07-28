package kr.shihyeon.imagicthud;

import kr.shihyeon.imagicthud.util.ModLogger;
import net.fabricmc.api.ModInitializer;

public class ImagictHud implements ModInitializer {

    public static final String MODID = "imagicthud";

    @Override
    public void onInitialize() {
        print("ImagictHud initialized!");
    }

    public void print(String message) {
        ModLogger.log("[ImagictHud] " + message);
    }
}
