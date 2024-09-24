package kr.shihyeon.imagicthud.neoforge.platform;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.neoforge.client.ImagictHudClientNeoForge;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;
import net.minecraft.client.KeyMapping;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.LoadingModList;

import java.nio.file.Path;

public class NeoForgeHelpers implements IPlatformHelpers {

    @Override
    public boolean isModLoaded(String modId) {
        return LoadingModList.get().getModFileById(modId) != null;
    }

    @Override
    public String getVersion() {
        return LoadingModList.get().getModFileById(ImagictHudClient.MODID).versionString();
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public KeyMapping registerKeyBinding(KeyMapping keyMapping) {
        ImagictHudClientNeoForge.KEYLIST.add(keyMapping);
        return keyMapping;
    }
}
