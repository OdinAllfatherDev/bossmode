package de.encryptdev.bossmode.boss;

import de.encryptdev.bossmode.boss.util.BossSettings;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * This class represent the APIBoss class
 * <p>
 * Created by EncryptDev
 */
public class Boss extends APIBoss {

    public Boss(BossSettings bossSettings, int livingId, String name, Location spawnLocation, EntityType type) {
        super(bossSettings, livingId, name, spawnLocation, type);
    }

}
