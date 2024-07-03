package kr.shihyeon.imagicthud.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;

public class PlayerUtil {

    public static String getName(MinecraftClient client, PlayerListEntry playerListEntry) {
        //PlayerListEntry playerListEntry = client.player.networkHandler.getPlayerListEntry(client.player.getUuid());
        String nickname;
        if (playerListEntry != null) {
            if (playerListEntry.getDisplayName() != null) {
                nickname = playerListEntry.getDisplayName().getString();
            } else {
                nickname = client.player.getName().getString();
            }
        } else {
            nickname = null;
        }
        return nickname;
    }
}
