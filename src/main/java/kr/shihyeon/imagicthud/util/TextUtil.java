package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static List<String> getLabelTexts(Minecraft client, ImagictHudConfig config, PlayerInfo playerListEntry) {
        List<String> textLines = new ArrayList<>();
        String nickname = "";
        if (client.player != null) {
            nickname = PlayerUtil.getName(client, playerListEntry);
        }

        if (config.hud.display.enableLocalDateTimeLabel) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
            switch (config.hud.text.localDateTimeMode) {
                case DATE_AND_TIME -> formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
                case DATE -> formatter = DateTimeFormatter.ofPattern("MM/dd");
                case TIME -> formatter = DateTimeFormatter.ofPattern("HH:mm");
            }
            String currentLocalDateTime = currentDateTime.format(formatter);
            textLines.add(currentLocalDateTime);
        }

        if (config.hud.display.enableNicknameLabel) {
            textLines.add(nickname);
        }

        if (config.hud.display.enableCoordinatesLabel) {
            final int playerPosX = Mth.floor(client.player.getX());
            final int playerPosY = Mth.floor(client.player.getBoundingBox().minY);
            final int playerPosZ = Mth.floor(client.player.getZ());
            textLines.add(playerPosX + ", " + playerPosY + ", " + playerPosZ);
        }

        if (config.hud.display.enableBiomeLabel) {
            BlockPos playerPos = client.player.getOnPos();
            if (client.level != null) {
                Holder<Biome> biomeEntry = client.level.getBiome(playerPos);
                ResourceKey<Biome> biomeKey = biomeEntry.unwrapKey().orElseThrow(() -> new IllegalStateException("Biome key not found"));

                String biomeTranslationKey = "biome.minecraft." + biomeKey.location().getPath();
                String biomeName = Component.translatable(biomeTranslationKey).getString();

                textLines.add(biomeName);
            } else {
                textLines.add("Unknown Biome");
            }
        }

        return textLines;
    }

    public static String getLongestString(List<String> textLines) {
        return textLines
                .stream()
                .reduce("",
                        (longestText, text) -> longestText.length() < text.length() ? text : longestText
                );
    }
}
