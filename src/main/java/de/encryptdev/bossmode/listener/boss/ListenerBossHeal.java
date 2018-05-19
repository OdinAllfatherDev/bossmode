package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossSettings;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

/**
 * Created by EncryptDev
 */
public class ListenerBossHeal implements Listener {

    @EventHandler
    public void on(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            if (event.getEntity().hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                int id = event.getEntity().getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                IBoss iBoss = BossMode.getInstance().getBossManager().getSpawnedBoss(id);
                iBoss.heal(event.getAmount());
            }
        }
    }

}
