package de.encryptdev.bossmode.boss.event;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by EncryptDev
 */
public class BossDamageEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private IBoss boss;
    private double damage;
    private boolean fallDamage;

    public BossDamageEvent(IBoss boss, double damage, boolean fallDamage) {
        this.boss = boss;
        this.damage = damage;
        this.fallDamage = fallDamage;
    }

    public IBoss getBoss() {
        return boss;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isFallDamage() {
        return fallDamage;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
