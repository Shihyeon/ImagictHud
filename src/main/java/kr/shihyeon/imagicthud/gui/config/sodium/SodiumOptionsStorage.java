package kr.shihyeon.imagicthud.gui.config.sodium;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class SodiumOptionsStorage implements OptionStorage<ImagictHudConfig> {

    private final ImagictHudConfig CONFIG;

    public SodiumOptionsStorage() {
        CONFIG = ImagictHudClient.CONFIG;
    }

    @Override
    public ImagictHudConfig getData() {
        return this.CONFIG;
    }

    @Override
    public void save() {
        this.CONFIG.save();
    }
}
