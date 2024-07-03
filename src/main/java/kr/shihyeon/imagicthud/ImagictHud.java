package kr.shihyeon.imagicthud;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImagictHud implements ModInitializer {

    public static final String MODID = "imagicthud";
    public static final Logger logger = LogManager.getLogger(ImagictHud.MODID);

    @Override
    public void onInitialize() {
        print("ImagictHud initialized");
    }

    public void print(String text) {
        logger.info("[ImagictHud] " + text);
    }
}
