package de.encryptdev.bossmode.boss.event;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by EncryptDev
 */
public class BossDeathEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private IBoss boss;
    private Location location;

    public BossDeathEvent(IBoss boss, Location location) {
        this.boss = boss;
        this.location = location;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    public IBoss getBoss() {
        return boss;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
