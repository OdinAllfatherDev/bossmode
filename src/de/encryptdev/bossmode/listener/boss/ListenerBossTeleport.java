package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

/**
 * Created by EncryptDev
 */
public class ListenerBossTeleport implements Listener {

    @EventHandler
    public void on(EntityTeleportEvent event) {
        if(BossUtil.isBoss((LivingEntity)  event.getEntity())) {
            if(event.getEntity() instanceof Enderman) {
                event.setCancelled(true);
                event.getEntity().teleport(event.getFrom());
            }
        }
    }

}
