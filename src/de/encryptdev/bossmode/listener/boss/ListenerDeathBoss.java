package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossSettings;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * Created by EncryptDev
 */
public class ListenerDeathBoss implements Listener {

    @EventHandler
    public void on(EntityDeathEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity entity = event.getEntity();

            if (entity.hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                int id = entity.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                IBoss iBoss = BossMode.getInstance().getBossManager().getSpawnedBoss(id);
                if(iBoss != null) {
                    iBoss.death();
                    event.setDroppedExp(iBoss.getBossSettings().getDroppedXp());
                } else {
                    event.setDroppedExp(100);
                }
            }
        }

}

    @EventHandler
    public void on(EntityExplodeEvent event) {
        if(event.getEntity() instanceof LivingEntity) {
            if(event.getEntity() instanceof Creeper) {
                if (event.getEntity().hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                    int id = event.getEntity().getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                    IBoss iBoss = BossMode.getInstance().getBossManager().getSpawnedBoss(id);
                    iBoss.death();
                }
            }
        }
    }

}
