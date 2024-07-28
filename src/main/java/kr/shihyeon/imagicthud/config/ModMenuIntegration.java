package kr.shihyeon.imagicthud.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import kr.shihyeon.imagicthud.gui.config.yacl3.YaclConfigScreenFactoryManager;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return YaclConfigScreenFactoryManager::createScreen;
    }
}
