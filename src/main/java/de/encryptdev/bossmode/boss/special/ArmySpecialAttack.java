package de.encryptdev.bossmode.boss.special;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.path.BossPathfinderEdited;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by EncryptDev
 */
public class ArmySpecialAttack extends SpecialAttack {

    private IBoss iBoss;
    private int amount;
    private double chance;
    private Random random;

    public ArmySpecialAttack(int amount, double chance) {
        super("armySpecialAttack");
        this.amount = amount;
        this.chance = chance;
        this.random = new Random();
    }

    @Override
    public void execute(Player... params) {
        int rand = random.nextInt(100);
        if (rand <= chance)
            for (int i = 0; i < amount; i++) {
                Entity entity = iBoss.getBossEntity().getWorld().spawnEntity(iBoss.getBossEntity().getLocation(),
                        iBoss.getEntityType());
                new BossPathfinderEdited((LivingEntity) entity, iBoss.getBossSettings().isLookAtPlayer(), iBoss.getBossSettings().getNearAttackEntities());
            }
    }

    @Override
    public String[] datas() {
        return new String[]{String.valueOf(amount), String.valueOf(chance)};
    }

    @Override
    public void setIBoss(IBoss iBoss) {
        this.iBoss = iBoss;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("amount", amount);
        map.put("chance", chance);
        return map;
    }
}
