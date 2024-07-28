package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityTracker {
    private static final ConcurrentHashMap<UUID, Integer> UUIDS = new ConcurrentHashMap<>();

    private static ImagictHudConfig config;

    static {
        config = ImagictHudClient.CONFIG;
        if (config == null) {
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
        if (config != null && config.indicator != null) {
            attackingAt = config.indicator.display.attackingAt;
            lookingAt = config.indicator.display.lookingAt;
            damagedOnly = config.indicator.display.damagedOnly;
            duration = config.indicator.display.duration;
            reach = config.indicator.display.reach;
            playerEntities = config.indicator.entities.playerEntities;
            selfPlayerEntity = config.indicator.entities.selfPlayerEntity;
            passiveEntities = config.indicator.entities.passiveEntities;
            hostileEntities = config.indicator.entities.hostileEntities;
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

    public static void tick(MinecraftClient client){
        if (client.player == null || client.world == null) return;

        if (config.indicator.general.enableIndicator) {
            for (Entity entity: client.world.getEntities()) {
                if (entity instanceof LivingEntity livingEntity) {
                    drawBar(client.player, livingEntity);
                }
            }
        }

        trimEntities(client.world);

        init();
    }

    private static void init() {
        boolean configChanged = false;

        if (config.indicator.display.attackingAt != attackingAt) {
            attackingAt = config.indicator.display.attackingAt;
            configChanged = true;
        }
        if (config.indicator.display.lookingAt != lookingAt) {
            lookingAt = config.indicator.display.lookingAt;
            configChanged = true;
        }
        if (config.indicator.display.damagedOnly != damagedOnly) {
            damagedOnly = config.indicator.display.damagedOnly;
            configChanged = true;
        }
        if (config.indicator.display.duration != duration) {
            duration = config.indicator.display.duration;
            configChanged = true;
        }
        if (config.indicator.display.reach != reach) {
            reach = config.indicator.display.reach;
            configChanged = true;
        }
        if (config.indicator.entities.playerEntities != playerEntities) {
            playerEntities = config.indicator.entities.playerEntities;
            configChanged = true;
        }
        if (config.indicator.entities.selfPlayerEntity != selfPlayerEntity) {
            selfPlayerEntity = config.indicator.entities.selfPlayerEntity;
            configChanged = true;
        }
        if (config.indicator.entities.passiveEntities != passiveEntities) {
            passiveEntities = config.indicator.entities.passiveEntities;
            configChanged = true;
        }
        if (config.indicator.entities.hostileEntities != hostileEntities) {
            hostileEntities = config.indicator.entities.hostileEntities;
            configChanged = true;
        }

        if (configChanged) {
            clearUUIDS();
        }
    }

    public static void clearUUIDS() {
        UUIDS.clear();
    }

    private static void drawBar(ClientPlayerEntity player, LivingEntity livingEntity) {
        if (isSelf(livingEntity, player)) {
            addToUUIDS(livingEntity, 86_400 * 20);
            return;
        }

        if (!isEntityTypeAllowed(livingEntity, player)) {
            return;
        }

        boolean isExistingEntity = UUIDS.containsKey(livingEntity.getUuid());
        boolean isDamaged = livingEntity.getHealth() != livingEntity.getMaxHealth();
        boolean isLookingAt = config.indicator.display.lookingAt && isTargeted(livingEntity);

        boolean shouldAddToUUIDS = false;

        if (config.indicator.display.attackingAt) {
            if (isExistingEntity) {
                if (config.indicator.display.damagedOnly) {
                    if (isDamaged) {
                        shouldAddToUUIDS = isLookingAt || !config.indicator.display.lookingAt;
                    }
                } else {
                    shouldAddToUUIDS = isLookingAt || !config.indicator.display.lookingAt;
                }
            }
        } else {
            if (config.indicator.display.damagedOnly) {
                if (isDamaged) {
                    shouldAddToUUIDS = isLookingAt || !config.indicator.display.lookingAt;
                }
            } else {
                shouldAddToUUIDS = isLookingAt || !config.indicator.display.lookingAt;
            }
        }

        if (shouldAddToUUIDS) {
            addToUUIDS(livingEntity, config.indicator.display.duration * 20);
        }
    }

    public static void onDamage(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getAttacker() instanceof PlayerEntity) {
            assert MinecraftClient.getInstance().world != null;
            if (config.indicator.display.attackingAt
                    && livingEntity instanceof LivingEntity
                    && EntityTracker.isEntityTypeAllowed(livingEntity, MinecraftClient.getInstance().player)) {
                if (!addToUUIDS(livingEntity, 0)) {
                    //UUIDS.replace(livingEntity.getUuid(), ImagictHudClient.CONFIG.duration * 20);
                    addToUUIDS(livingEntity, config.indicator.display.duration * 20);
                }
            }
        }
    }

    private static void trimEntities(ClientWorld world) {
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
        UUIDS.entrySet().removeIf(entry -> isInvalid(getEntityFromUUID(entry.getKey(), world))|| !config.indicator.general.enableIndicator);

        if (UUIDS.size() >= 1536) {
            UUIDS.clear();
        }
    }

    public static void removeFromUUIDS(Entity entity){
        UUIDS.remove(entity.getUuid());
    }
    public static void removeFromUUIDS(UUID uuid){
        UUIDS.remove(uuid);
    }

    private static boolean addToUUIDS(LivingEntity livingEntity, int duration) {
        if (!UUIDS.containsKey(livingEntity.getUuid())) {
            UUIDS.put(livingEntity.getUuid(), duration);
            return true;
        } else {
            // update duration
            UUIDS.replace(livingEntity.getUuid(), duration);
            return false;
        }
    }

    public static boolean isInUUIDS(LivingEntity livingEntity){
        return UUIDS.containsKey(livingEntity.getUuid());
    }

    private static boolean isEntityTypeAllowed(LivingEntity livingEntity, PlayerEntity self){
        if (!config.indicator.entities.passiveEntities && livingEntity instanceof PassiveEntity) {
            return false;
        }
        if (!config.indicator.entities.hostileEntities && livingEntity instanceof HostileEntity) {
            return false;
        }
        if (!config.indicator.entities.playerEntities && livingEntity instanceof PlayerEntity) {
            return false;
        }
        if (livingEntity == self) {
            return false;
        }
        return !(livingEntity instanceof ArmorStandEntity);
    }

    private static boolean isSelf(LivingEntity livingEntity, PlayerEntity self) {
        return config.indicator.entities.selfPlayerEntity && livingEntity == self;
    }

    private static boolean isTargeted(LivingEntity livingEntity) {
        Entity camera = MinecraftClient.getInstance().cameraEntity;
        double d = config.indicator.display.reach;
        double e = MathHelper.square(d);
        Vec3d vec3d = camera.getCameraPosVec(0);
        HitResult hitResult = camera.raycast(d, 0, false);
        double f = hitResult.getPos().squaredDistanceTo(vec3d);
        if (hitResult.getType() != HitResult.Type.MISS) {
            e = f;
            d = Math.sqrt(e);
        }
        Vec3d vec3d2 = camera.getRotationVec(0);
        Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
        Box box = camera.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
        assert MinecraftClient.getInstance().cameraEntity != null;
        EntityHitResult entityHitResult = ProjectileUtil.raycast(MinecraftClient.getInstance().cameraEntity, vec3d, vec3d3, box, entity -> !entity.isSpectator() && entity.canHit(), e);

        if (entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity livingEntity1) {
            return livingEntity1 == livingEntity;
        }
        return false;
    }

    public static boolean isInvalid(Entity entity){
        return (entity == null
                || !entity.isAlive()
                || !entity.isLiving()
                || entity.isRegionUnloaded()
                || !(entity instanceof LivingEntity)
                || MinecraftClient.getInstance().player == null
                || MinecraftClient.getInstance().player.getVehicle() == entity
                || entity.isInvisibleTo(MinecraftClient.getInstance().player));
    }

    private static Entity getEntityFromUUID(UUID uuid, ClientWorld world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getUuid().equals(uuid)) {
                return entity;
            }
        }
        return null;
    }
}