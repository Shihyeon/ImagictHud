package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
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

    public static boolean attackingAt = ImagictHudClient.CONFIG.attackingAt;
    public static boolean lookingAt = ImagictHudClient.CONFIG.lookingAt;
    public static boolean damagedOnly = ImagictHudClient.CONFIG.damagedOnly;
    public static int duration = ImagictHudClient.CONFIG.duration;
    public static int reach = ImagictHudClient.CONFIG.reach;
    public static boolean playerEntities = ImagictHudClient.CONFIG.playerEntities;
    public static boolean selfPlayerEntity = ImagictHudClient.CONFIG.selfPlayerEntity;
    public static boolean passiveEntities = ImagictHudClient.CONFIG.passiveEntities;
    public static boolean hostileEntities = ImagictHudClient.CONFIG.hostileEntities;

    public static void tick(MinecraftClient client){
        if (client.player == null || client.world == null) return;

        if (ImagictHudClient.CONFIG.enableIndicator) {
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

        if (ImagictHudClient.CONFIG.attackingAt != attackingAt) {
            attackingAt = ImagictHudClient.CONFIG.attackingAt;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.lookingAt != lookingAt) {
            lookingAt = ImagictHudClient.CONFIG.lookingAt;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.damagedOnly != damagedOnly) {
            damagedOnly = ImagictHudClient.CONFIG.damagedOnly;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.duration != duration) {
            duration = ImagictHudClient.CONFIG.duration;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.reach != reach) {
            reach = ImagictHudClient.CONFIG.reach;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.playerEntities != playerEntities) {
            playerEntities = ImagictHudClient.CONFIG.playerEntities;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.selfPlayerEntity != selfPlayerEntity) {
            selfPlayerEntity = ImagictHudClient.CONFIG.selfPlayerEntity;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.passiveEntities != passiveEntities) {
            passiveEntities = ImagictHudClient.CONFIG.passiveEntities;
            configChanged = true;
        }
        if (ImagictHudClient.CONFIG.hostileEntities != hostileEntities) {
            hostileEntities = ImagictHudClient.CONFIG.hostileEntities;
            configChanged = true;
        }

        if (configChanged) {
            UUIDS.clear();
        }
    }

    private static void drawBar(ClientPlayerEntity player, LivingEntity livingEntity) {
        if (isEntityTypeAllowed(livingEntity, player)) {
            if (ImagictHudClient.CONFIG.attackingAt) {
                if (UUIDS.containsKey(livingEntity.getUuid())) {
                    if (ImagictHudClient.CONFIG.damagedOnly) {
                        if (livingEntity.getHealth() != livingEntity.getMaxHealth()) {
                            if (ImagictHudClient.CONFIG.lookingAt) {
                                if (isTargeted(livingEntity)) {
                                    addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                                }
                            } else {
                                addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                            }
                        }
                    } else {
                        if (ImagictHudClient.CONFIG.lookingAt) {
                            if (isTargeted(livingEntity)) {
                                addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                            }
                        } else {
                            addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                        }
                    }
                }
            } else {
                if (ImagictHudClient.CONFIG.damagedOnly) {
                    if (livingEntity.getHealth() != livingEntity.getMaxHealth()) {
                        if (ImagictHudClient.CONFIG.lookingAt) {
                            if (isTargeted(livingEntity)) {
                                addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                            }
                        } else {
                            addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                        }
                    }
                } else {
                    if (ImagictHudClient.CONFIG.lookingAt) {
                        if (isTargeted(livingEntity)) {
                            addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                        }
                    } else {
                        addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
                    }
                }
            }
        }
        if (isSelf(livingEntity, player)) {
            addToUUIDS(livingEntity, 86_400 * 20);
        }
    }

    public static void onDamage(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getAttacker() instanceof PlayerEntity) {
            assert MinecraftClient.getInstance().world != null;
            if (ImagictHudClient.CONFIG.attackingAt
                    && livingEntity instanceof LivingEntity
                    && EntityTracker.isEntityTypeAllowed(livingEntity, MinecraftClient.getInstance().player)) {
                if (!addToUUIDS(livingEntity, 0)) {
                    //UUIDS.replace(livingEntity.getUuid(), ImagictHudClient.CONFIG.duration * 20);
                    addToUUIDS(livingEntity, ImagictHudClient.CONFIG.duration * 20);
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
        UUIDS.entrySet().removeIf(entry -> isInvalid(getEntityFromUUID(entry.getKey(), world))|| !ImagictHudClient.CONFIG.enableIndicator);

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
        if (!ImagictHudClient.CONFIG.passiveEntities && livingEntity instanceof PassiveEntity) {
            return false;
        }
        if (!ImagictHudClient.CONFIG.hostileEntities && livingEntity instanceof HostileEntity) {
            return false;
        }
        if (!ImagictHudClient.CONFIG.playerEntities && livingEntity instanceof PlayerEntity) {
            return false;
        }
        if (livingEntity == self) {
            return false;
        }
        return !(livingEntity instanceof ArmorStandEntity);
    }

    private static boolean isSelf(LivingEntity livingEntity, PlayerEntity self) {
        return ImagictHudClient.CONFIG.selfPlayerEntity && livingEntity == self;
    }

    private static boolean isTargeted(LivingEntity livingEntity) {
        Entity camera = MinecraftClient.getInstance().cameraEntity;
        double d = ImagictHudClient.CONFIG.reach;
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