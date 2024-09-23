package kr.shihyeon.imagicthud.neoforge.client;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.gui.screen.config.yacl3.YaclConfigScreenFactoryManager;
import kr.shihyeon.imagicthud.util.EntityTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(ImagictHudClient.MODID)
public class ImagictHudClientNeoForge {

    public static List<KeyMapping> KEYLIST = new ArrayList<>();

    public ImagictHudClientNeoForge(IEventBus bus, ModContainer container) {
		ImagictHudClient.init();
		bus.addListener(this::registerKeys);
		registerEvents();
		container.registerExtensionPoint(IConfigScreenFactory.class, (client, parent) -> YaclConfigScreenFactoryManager.createScreen(parent));
	}

	public void registerKeys(RegisterKeyMappingsEvent event) {
		KEYLIST.forEach(event::register);
		KEYLIST.clear();
	}

	private void registerEvents() {
		NeoForge.EVENT_BUS.addListener(this::onClientTick);
		NeoForge.EVENT_BUS.addListener(this::onEntityUnload);
    }

	@SubscribeEvent
    private void onClientTick(ClientTickEvent.Post event) {
		Minecraft client = Minecraft.getInstance();
        EntityTracker.tick(client);
    }

	@SubscribeEvent
	private void onEntityUnload(EntityJoinLevelEvent event) {
        if (event.getEntity() != null && event.getLevel().isClientSide) {
            EntityTracker.removeFromUUIDS(event.getEntity());
        }
    }
}
