package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

/**
 * Created by EncryptDev
 */
public class ListenerSpawner implements Listener {
    
    private BossManager bossManager;
    
    public ListenerSpawner(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(SpawnerSpawnEvent event) {
        if(event.getSpawner().hasMetadata("boss_id")) {
            if(event.getSpawner().getMetadata("boss_id").get(0).value() instanceof IBoss) {
                event.getEntity().remove();
                IBoss iBoss = (IBoss) event.getSpawner().getMetadata("boss_id").get(0).value();
                bossManager.spawnBoss(iBoss, event.getLocation());
            }

        }
    }

}
