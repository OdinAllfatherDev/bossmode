package de.encryptdev.bossmode.boss.mount;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

/**
 * Created by EncryptDev
 */
public class Mount {

    public static final String META_DATA = "mount_iboss";

    private MountType type;
    private double health;
    private BukkitTask task;
    private LivingEntity livingEntity;

    public Mount(MountType type, double health) {
        this.type = type;
        this.health = health;
    }

    public void spawn(IBoss boss, Location location, LivingEntity entity) {
        livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, type.getEntityType());
        if (BossUtil.is1_8()) {
            livingEntity.setMaxHealth(health);
        } else {
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        }
        livingEntity.setHealth(health);
        livingEntity.addPassenger(entity);
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(BossMode.getInstance(), () -> {
            if (livingEntity != null)
                if (livingEntity.getPassengers().isEmpty())
                    livingEntity.addPassenger(entity);
        }, 0, 10);
        livingEntity.setMetadata(META_DATA, new FixedMetadataValue(BossMode.getInstance(), boss));
    }

    public void die() {
        this.livingEntity.remove();
        this.task.cancel();
        livingEntity.removeMetadata(META_DATA, BossMode.getInstance());
    }

    public double getHealth() {
        return health;
    }

    public MountType getType() {
        return type;
    }

}
