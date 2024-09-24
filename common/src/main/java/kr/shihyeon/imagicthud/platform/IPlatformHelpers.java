package kr.shihyeon.imagicthud.platform;

import net.minecraft.client.KeyMapping;

import java.nio.file.Path;
import java.util.ServiceLoader;

public interface IPlatformHelpers {

    IPlatformHelpers INSTANCE = ServiceLoader.load(IPlatformHelpers.class).findFirst().get();

    static IPlatformHelpers getInstance() {
        return INSTANCE;
    }

    boolean isModLoaded(String modId);

    String getVersion();

    Path getConfigDir();

    KeyMapping registerKeyBinding(KeyMapping keyMapping);
}
