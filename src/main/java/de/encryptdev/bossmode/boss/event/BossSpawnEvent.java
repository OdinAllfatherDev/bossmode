package de.encryptdev.bossmode.boss.event;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by EncryptDev
 */
public class BossSpawnEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private IBoss boss;
    private Location spawnLocation;

    public BossSpawnEvent(IBoss boss, Location spawnLocation) {
        this.boss = boss;
        this.spawnLocation = spawnLocation;
    }

    public IBoss getBoss() {
        return boss;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
