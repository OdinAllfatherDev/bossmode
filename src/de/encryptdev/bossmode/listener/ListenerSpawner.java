package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

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
        if (event.getSpawner().hasMetadata("boss_id")) {
            if (event.getSpawner().getMetadata("boss_id").get(0).value() instanceof IBoss) {
                event.getEntity().remove();
                IBoss iBoss = (IBoss) event.getSpawner().getMetadata("boss_id").get(0).value();
                bossManager.spawnBoss(iBoss, event.getLocation());
            }

        }
    }

    @EventHandler
    public void on(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null || event.getItem() == null || !event.getItem().hasItemMeta())
            return;
        if (event.getItem().getItemMeta().getDisplayName().startsWith("§eEGG:")) {
            List<String> lore = event.getItem().getItemMeta().getLore();
            int id = Integer.parseInt(lore.get(0).split(":")[1].trim());
            IBoss iBoss = bossManager.getBoss(id);
            if (iBoss == null) {
                event.setCancelled(true);
                return;
            }

            bossManager.spawnBoss(iBoss, event.getClickedBlock() != null ? event.getClickedBlock().getLocation().add(0, 1.5, 0) :
                    event.getPlayer().getTargetBlock(null, 100).getLocation().add(0, 1.5, 0));

            new BukkitRunnable() {

                @Override
                public void run() {
                    for (Entity entities : event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getLocation(), 20, 20, 20)) {
                        if (entities instanceof LivingEntity) {
                            if (!BossUtil.isBoss((LivingEntity) entities) && entities.getType() == iBoss.getEntityType() && entities.getName().contains("§eEGG: ")) {
                                entities.remove();
                            }
                        }
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 5);

        }
    }

}
