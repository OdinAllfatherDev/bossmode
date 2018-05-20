package de.encryptdev.bossmode.boss.mount;

import org.bukkit.Material;

/**
 * Created by EncryptDev
 */
public class MountEditor {

    private BossMount.BossMountType mountType;
    private double maxHealth;
    private double jumpStrength;
    private Material armor;

    public MountEditor(BossMount.BossMountType mountType) {
        this.mountType = mountType;
    }

}
