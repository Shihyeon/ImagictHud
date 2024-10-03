package kr.shihyeon.imagicthud.fabric.client;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.util.EntityTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ImagictHudClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ImagictHudClient.init();

        registerEvents();
    }

    private void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(EntityTracker::tick);

        ClientEntityEvents.ENTITY_UNLOAD.register(
                (entity, world) -> EntityTracker.removeFromUUIDS(entity)
        );
    }
}
