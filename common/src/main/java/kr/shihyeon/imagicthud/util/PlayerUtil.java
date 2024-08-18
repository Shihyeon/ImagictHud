package kr.shihyeon.imagicthud.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;

public class PlayerUtil {

    public static String getName(Minecraft client, PlayerInfo playerListEntry) {
        String nickname;
        if (playerListEntry != null) {
            if (playerListEntry.getTabListDisplayName() != null) {
                nickname = playerListEntry.getTabListDisplayName().getString();
            } else {
                nickname = client.player.getName().getString();
            }
        } else {
            nickname = null;
        }
        return nickname;
    }
}
