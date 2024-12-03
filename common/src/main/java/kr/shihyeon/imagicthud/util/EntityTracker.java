package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityTracker {
    private static final Minecraft client = Minecraft.getInstance();

    private static final ConcurrentHashMap<UUID, Integer> UUIDS = new ConcurrentHashMap<>();

    private static final ImagictHudConfig CONFIG;

    private static final int TICK_COUNT = 20;

    static {
        CONFIG = ImagictHudClient.getConfig();
        if (CONFIG == null) {
            throw new IllegalStateException("ImagictHudClient.CONFIG is not initialized");
        }
    }

    private static boolean attackingAt;
    private static boolean lookingAt;
    private static boolean damagedOnly;
    private static int duration;
    private static int reach;
    private static boolean playerEntities;
    private static boolean selfPlayerEntity;
    private static boolean passiveEntities;
    private static boolean hostileEntities;

    static {
        if (CONFIG != null && CONFIG.indicator != null) {
            attackingAt = CONFIG.indicator.display.attackingAt;
            lookingAt = CONFIG.indicator.display.lookingAt;
            damagedOnly = CONFIG.indicator.display.damagedOnly;
            duration = CONFIG.indicator.display.duration;
            reach = CONFIG.indicator.display.reach;
            playerEntities = CONFIG.indicator.entities.playerEntities;
            selfPlayerEntity = CONFIG.indicator.entities.selfPlayerEntity;
            passiveEntities = CONFIG.indicator.entities.passiveEntities;
            hostileEntities = CONFIG.indicator.entities.hostileEntities;
        } else {
            attackingAt = true;
            lookingAt = true;
            damagedOnly = false;
            duration = 10;
            reach = 20;
            playerEntities = true;
            selfPlayerEntity = false;
            passiveEntities = true;
            hostileEntities = true;
        }
    }

    public static void tick(Minecraft client) {
        if (client.player == null || client.level == null) return;

        if (CONFIG.indicator.general.enableIndicator) {
            for (Entity entity : client.level.entitiesForRendering()) {
                if (entity instanceof LivingEntity livingEntity) {
                    drawBar(client.player, livingEntity);
                }
            }
        }

        trimEntities(client.level);

        init();
    }

    private static void init() {
        boolean configChanged = false;

        if (CONFIG.indicator.display.attackingAt != attackingAt) {
            attackingAt = CONFIG.indicator.display.attackingAt;
            configChanged = true;
        }
        if (CONFIG.indicator.display.lookingAt != lookingAt) {
            lookingAt = CONFIG.indicator.display.lookingAt;
            configChanged = true;
        }
        if (CONFIG.indicator.display.damagedOnly != damagedOnly) {
            damagedOnly = CONFIG.indicator.display.damagedOnly;
            configChanged = true;
        }
        if (CONFIG.indicator.display.duration != duration) {
            duration = CONFIG.indicator.display.duration;
            configChanged = true;
        }
        if (CONFIG.indicator.display.reach != reach) {
            reach = CONFIG.indicator.display.reach;
            configChanged = true;
        }
        if (CONFIG.indicator.entities.playerEntities != playerEntities) {
            playerEntities = CONFIG.indicator.entities.playerEntities;
            configChanged = true;
        }
        if (CONFIG.indicator.entities.selfPlayerEntity != selfPlayerEntity) {
            selfPlayerEntity = CONFIG.indicator.entities.selfPlayerEntity;
            configChanged = true;
        }
        if (CONFIG.indicator.entities.passiveEntities != passiveEntities) {
            passiveEntities = CONFIG.indicator.entities.passiveEntities;
            configChanged = true;
        }
        if (CONFIG.indicator.entities.hostileEntities != hostileEntities) {
            hostileEntities = CONFIG.indicator.entities.hostileEntities;
            configChanged = true;
        }

        if (configChanged) {
            clearUUIDS();
        }
    }

    public static void clearUUIDS() {
        UUIDS.clear();
    }

    private static void drawBar(LocalPlayer player, LivingEntity livingEntity) {
        if (isSelf(livingEntity, player)) {
            addToUUIDS(livingEntity, 86_400 * TICK_COUNT);
            return;
        }

        if (!isEntityTypeAllowed(livingEntity, player)) {
            return;
        }

        boolean isExistingEntity = UUIDS.containsKey(livingEntity.getUUID());
        boolean isDamaged = livingEntity.getHealth() != livingEntity.getMaxHealth();
        boolean isLookingAt = CONFIG.indicator.display.lookingAt && isTargeted(livingEntity);

        boolean shouldAddToUUIDS = false;

        if (CONFIG.indicator.display.attackingAt) {
            if (isExistingEntity) {
                if (CONFIG.indicator.display.damagedOnly) {
                    if (isDamaged) {
                        shouldAddToUUIDS = isLookingAt || !CONFIG.indicator.display.lookingAt;
                    }
                } else {
                    shouldAddToUUIDS = isLookingAt || !CONFIG.indicator.display.lookingAt;
                }
            }
        } else {
            if (CONFIG.indicator.display.damagedOnly) {
                if (isDamaged) {
                    shouldAddToUUIDS = isLookingAt || !CONFIG.indicator.display.lookingAt;
                }
            } else {
                shouldAddToUUIDS = isLookingAt || !CONFIG.indicator.display.lookingAt;
            }
        }

        if (shouldAddToUUIDS) {
            addToUUIDS(livingEntity, CONFIG.indicator.display.duration * TICK_COUNT);
        }
    }

    public static void onDamage(DamageSource damageSource, LivingEntity livingEntity) {
        Player self = client.player;

        if (damageSource.getEntity() instanceof Player && damageSource.getEntity() == self) {
            assert client.level != null;

            if (CONFIG.indicator.display.attackingAt
                    && livingEntity instanceof LivingEntity
                    && EntityTracker.isEntityTypeAllowed(livingEntity, self)) {
                if (!addToUUIDS(livingEntity, 0)) {
                    addToUUIDS(livingEntity, CONFIG.indicator.display.duration * TICK_COUNT);
                }
            }
        }
    }

    private static void trimEntities(ClientLevel world) {
        // Check if there's a need to trim entries
        Iterator<Map.Entry<UUID, Integer>> iterator = UUIDS.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            entry.setValue(entry.getValue() - 1);
            if (entry.getValue() <= 0) {
                iterator.remove(); // Safe removal during iteration
            }
        }

        // Remove invalid entities
        UUIDS.entrySet().removeIf(entry -> isInvalid(getEntityFromUUID(entry.getKey(), world)) || !CONFIG.indicator.general.enableIndicator);

        if (UUIDS.size() >= 1536) {
            UUIDS.clear();
        }
    }

    public static void removeFromUUIDS(Entity entity) {
        UUIDS.remove(entity.getUUID());
    }

    public static void removeFromUUIDS(UUID uuid) {
        UUIDS.remove(uuid);
    }

    private static boolean addToUUIDS(LivingEntity livingEntity, int duration) {
        if (!UUIDS.containsKey(livingEntity.getUUID())) {
            UUIDS.put(livingEntity.getUUID(), duration);
            return true;
        } else {
            // update duration
            UUIDS.replace(livingEntity.getUUID(), duration);
            return false;
        }
    }

    public static boolean isInUUIDS(LivingEntity livingEntity) {
        return UUIDS.containsKey(livingEntity.getUUID());
    }

    private static boolean isEntityTypeAllowed(LivingEntity livingEntity, Player self) {
        if (!CONFIG.indicator.entities.passiveEntities && livingEntity instanceof AgeableMob) {
            return false;
        }
        if (!CONFIG.indicator.entities.hostileEntities && livingEntity instanceof Monster) {
            return false;
        }
        if (!CONFIG.indicator.entities.playerEntities && livingEntity instanceof Player) {
            return false;
        }
        if (livingEntity == self) {
            return false;
        }
        return !(livingEntity instanceof ArmorStand);
    }

    private static boolean isSelf(LivingEntity livingEntity, Player self) {
        return CONFIG.indicator.entities.selfPlayerEntity && livingEntity == self;
    }

    private static boolean isTargeted(LivingEntity livingEntity) {
        Entity camera = client.cameraEntity;
        double d = CONFIG.indicator.display.reach;
        double e = Mth.square(d);
        Vec3 vec3d = camera.getEyePosition(0);
        HitResult hitResult = camera.pick(d, 0, false);
        double f = hitResult.getLocation().distanceToSqr(vec3d);
        if (hitResult.getType() != HitResult.Type.MISS) {
            e = f;
            d = Math.sqrt(e);
        }
        Vec3 vec3d2 = camera.getViewVector(0);
        Vec3 vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
        AABB box = camera.getBoundingBox().expandTowards(vec3d2.scale(d)).inflate(1.0, 1.0, 1.0);
        assert client.cameraEntity != null;
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(client.cameraEntity, vec3d, vec3d3, box, entity -> !entity.isSpectator() && entity.isPickable(), e);

        if (entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity livingEntity1) {
            return livingEntity1 == livingEntity;
        }
        return false;
    }

    public static boolean isInvalid(Entity entity) {
        return (entity == null
                || !entity.isAlive()
                || !entity.showVehicleHealth()
                || entity.touchingUnloadedChunk()
                || !(entity instanceof LivingEntity)
                || client.player == null
                || client.player.getVehicle() == entity
                || entity.isInvisibleTo(client.player));
    }

    private static Entity getEntityFromUUID(UUID uuid, ClientLevel world) {
        for (Entity entity : world.entitiesForRendering()) {
            if (entity.getUUID().equals(uuid)) {
                return entity;
            }
        }
        return null;
    }
}