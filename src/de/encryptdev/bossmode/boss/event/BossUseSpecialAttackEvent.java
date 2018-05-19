package de.encryptdev.bossmode.boss.event;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by EncryptDev
 */
public class BossUseSpecialAttackEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private IBoss boss;
    private SpecialAttack specialAttack;

    public BossUseSpecialAttackEvent(IBoss boss, SpecialAttack specialAttack) {
        this.boss = boss;
        this.specialAttack = specialAttack;
    }

    public IBoss getBoss() {
        return boss;
    }

    public SpecialAttack getSpecialAttack() {
        return specialAttack;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
