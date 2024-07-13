package kr.shihyeon.imagicthud.hud;

import kr.shihyeon.imagicthud.ImagictHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class ResourceGui {
    protected static final Set<Identifier> blendedHeadTextures = new HashSet<>();

    public static Identifier getBlendedLocation(Identifier TextureLocation) {
        return Identifier.of(ImagictHud.MODID, TextureLocation.getPath());
    }

    public static void renderLabelFrame(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x+1, y, x+width-1, y+1, color);
        context.fill(x, y+1, x+1, y+height-1, color);
        context.fill(x+width, y+1, x+width-1, y+height-1, color);
        context.fill(x+1, y+height, x+width-1, y+height-1, color);
    }

    public static void renderLabelBackground(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x+1, y, x+width-1, y+1, color);
        context.fill(x, y+1, x+width, y+height-1, color);
        context.fill(x+1, y+height, x+width-1, y+height-1, color);
    }

    public static void renderHead(DrawContext context, PlayerListEntry playerListEntry, int x, int y) {
        Identifier skinLocation = playerListEntry.getSkinTextures().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        if (blendedHeadTextures.contains(skinLocation)) {
            context.drawTexture(getBlendedLocation(skinLocation), x, y, initPosX, initPosY, 0, 0, regionSize, regionSize, regionSize, regionSize);
        } else {
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, u, v, regionSize, regionSize, textureSize, textureSize);
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, uh, v, regionSize, regionSize, textureSize, textureSize);
        }
    }

    public static void renderBoldHead(DrawContext context, PlayerListEntry playerListEntry, int x, int y) {
        Identifier skinLocation = playerListEntry.getSkinTextures().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        int initHeadPosX = initPosX * 14/16;
        int initHeadPosY = initPosY * 14/16;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        if (blendedHeadTextures.contains(skinLocation)) {
            context.drawTexture(getBlendedLocation(skinLocation), x, y, initPosX, initPosY, 0, 0, regionSize, regionSize, regionSize, regionSize);
        } else {
            context.drawTexture(skinLocation, x + offset/2, y + offset/2, initHeadPosX, initHeadPosY, u, v, regionSize, regionSize, textureSize, textureSize);
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, uh, v, regionSize, regionSize, textureSize, textureSize);
        }
    }
}
