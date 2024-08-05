package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static List<String> getLabelTexts(MinecraftClient client, ImagictHudConfig config, PlayerListEntry playerListEntry) {
        List<String> textLines = new ArrayList<>();
        String nickname = "";
        if (client.player != null) {
            nickname = PlayerUtil.getName(client, playerListEntry);
        }

        if (config.hud.display.enableLocalDateTimeLabel) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter;
            switch (config.hud.text.localDateTimeMode) {
                case DATE_AND_TIME -> formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
                case DATE -> formatter = DateTimeFormatter.ofPattern("MM/dd");
                case TIME -> formatter = DateTimeFormatter.ofPattern("HH:mm");
                case null -> formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
            }
            String currentLocalDateTime = currentDateTime.format(formatter);
            textLines.add(currentLocalDateTime);
        }

        if (config.hud.display.enableNicknameLabel) {
            textLines.add(nickname);
        }

        if (config.hud.display.enableCoordinatesLabel) {
            final int playerPosX = MathHelper.floor(client.player.getX());
            final int playerPosY = MathHelper.floor(client.player.getBoundingBox().minY);
            final int playerPosZ = MathHelper.floor(client.player.getZ());
            textLines.add(playerPosX + ", " + playerPosY + ", " + playerPosZ);
        }

        if (config.hud.display.enableBiomeLabel) {
            BlockPos playerPos = client.player.getBlockPos();
            if (client.world != null) {
                var biomeEntry = client.world.getBiome(playerPos);
                var biomeKey = biomeEntry.getKey().orElseThrow(() -> new IllegalStateException("Biome key not found"));

                String biomeTranslationKey = "biome.minecraft." + biomeKey.getValue().getPath();
                String biomeName = Text.translatable(biomeTranslationKey).getString();

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
