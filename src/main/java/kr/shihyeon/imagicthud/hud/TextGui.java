package kr.shihyeon.imagicthud.hud;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.util.PlayerUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.math.BlockPos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TextGui {
    public static void renderText(DrawContext context, MinecraftClient client, String text, int x, int y, int color, boolean shadow) {
        if (client != null && client.textRenderer != null) {
            context.drawText(client.textRenderer, text, x, y, color, shadow);
        }
    }

    public static List<String> getStrings(MinecraftClient client, ImagictHudConfig config, PlayerListEntry playerListEntry) {
        List<String> textLines = new ArrayList<>();
        String nickname = "";
        if (client.player != null) {
            nickname = PlayerUtil.getName(client, playerListEntry);
        }

        if (config.enableBottomCustomHud) {
            String topCustomLabelText = config.topCustomLabelText;
            textLines.add(topCustomLabelText);
        }

        if (config.enableLocalDateTimeHud) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
            String currentLocalDateTime = currentDateTime.format(formatter);
            textLines.add(currentLocalDateTime);
        }

        if (config.enableNicknameHud) {
            textLines.add(nickname);
        }

        if (config.enableCoordinatesHud) {
            assert client.player != null;
            long playerPosX = (long) client.player.getX();
            long playerPosY = (long) client.player.getY();
            long playerPosZ = (long) client.player.getZ();
            textLines.add(playerPosX + ", " + playerPosY + ", " + playerPosZ);
        }

        if (config.enableBiomeHud) {
            assert client.player != null;
            BlockPos playerPos = client.player.getBlockPos();
            if (client.world != null) {
                var biomeEntry = client.world.getBiome(playerPos);
                var biomeKey = biomeEntry.getKey().orElseThrow(() -> new IllegalStateException("Biome key not found"));

                String biomeTranslationKey = "biome.minecraft." + biomeKey.getValue().getPath();
                String biomeName = net.minecraft.text.Text.translatable(biomeTranslationKey).getString();

                textLines.add(biomeName);
            } else {
                textLines.add("Unknown Biome");
            }
        }

        if (config.enableTopCustomHud) {
            String bottomCustomLabelText = config.bottomCustomLabelText;
            textLines.add(bottomCustomLabelText);
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
