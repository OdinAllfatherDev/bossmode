package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by EncryptDev
 */
public class ListenerBossSpawner implements Listener {

    private BossManager bossManager;
    
    public ListenerBossSpawner(BossManager bossManager) {
        this.bossManager = bossManager;
    }
    
    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().startsWith("§eSpawner: ")) {
                    if (event.getBlock().getState() instanceof CreatureSpawner) {
                        BossMode.getInstance().getNbtSpawnerUtil().modifySpawner(player.getInventory().getItemInMainHand(), event.getBlock().getLocation());
                        event.getBlock().setMetadata("boss_id", new FixedMetadataValue(BossMode.getInstance(),
                                bossManager.getBoss(Integer.valueOf(player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).split(":")[1].trim()))));
                    }
                }
            }
        }
    }

}
