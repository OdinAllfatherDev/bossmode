package de.encryptdev.bossmode.boss.ai;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.event.BossUseSpecialAttackEvent;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by EncryptDev
 */
public class BossAISpecialAttack {

    private IBoss iBoss;
    private List<SpecialAttack> specialAttacks;
    private Random random;
    private int ticks;

    public BossAISpecialAttack(IBoss iBoss, int ticks) {
        this.iBoss = iBoss;
        this.specialAttacks = iBoss.getSpecialAttacks();
        this.random = new Random();
        this.ticks = ticks;
    }

    public void execute(Player... player) {
        if (specialAttacks.size() > 1) {
            SpecialAttack specialAttack = specialAttacks.get(random.nextInt(specialAttacks.size() - 1));
            specialAttack.execute(player);
            Bukkit.getPluginManager().callEvent(new BossUseSpecialAttackEvent(iBoss, specialAttack));
        } else if (specialAttacks.size() == 1) {
            specialAttacks.get(0).execute(player);
            Bukkit.getPluginManager().callEvent(new BossUseSpecialAttackEvent(iBoss, specialAttacks.get(0)));
        }
    }

    public int getTicks() {
        return ticks;
    }

}
