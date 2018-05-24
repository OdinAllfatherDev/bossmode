package de.encryptdev.bossmode.boss.mount;

import org.bukkit.entity.EntityType;

/**
 * Created by EncryptDev
 */
public enum MountType {

    PIG(EntityType.PIG), COW(EntityType.COW), BAT(EntityType.BAT);

    private EntityType entityType;

    MountType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
