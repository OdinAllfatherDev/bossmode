package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossSettings;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerPlayerDamageByBoss implements Listener {

    private BossManager bossManager;

    public ListenerPlayerDamageByBoss(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof LivingEntity) {
            Player player = (Player) event.getEntity();
            if (BossUtil.isBoss((LivingEntity) event.getDamager())) {
                IBoss iBoss = BossUtil.getLivingBossByMetadata((LivingEntity) event.getDamager());

                if (BossUtil.is1_8()) {
                    if (iBoss.getBossEntity().hasMetadata(BossSettings.META_DATA_BOSS_POTION)) {
                        List<PotionEffect> peList = (List<PotionEffect>) iBoss.getBossEntity().getMetadata(BossSettings.META_DATA_BOSS_POTION).get(0).value();
                        if (!peList.isEmpty())
                            for (PotionEffect pe : peList)
                                player.addPotionEffect(new PotionEffect(pe.getType(), 400, pe.getAmplifier()));
                    }
                    float damage = iBoss.getBossSettings().getDamage();
                    ((Player) event.getEntity()).damage(damage);

                } else {
                    if (iBoss.getBossEntity().hasMetadata(BossSettings.META_DATA_BOSS_POTION)) {
                        List<PotionEffect> peList = (List<PotionEffect>) iBoss.getBossEntity().getMetadata(BossSettings.META_DATA_BOSS_POTION).get(0).value();
                        if (!peList.isEmpty())
                            for (PotionEffect pe : peList)
                                player.addPotionEffect(new PotionEffect(pe.getType(), 400, pe.getAmplifier()));
                    }
                }
            }
        }

    }
}
