package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryChangeEntityType implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().equalsIgnoreCase("§eSet the entity type")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
            String normalName = itemName.substring(4, itemName.length());

            switch (normalName) {
                case "Zombie":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.ZOMBIE);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Skeleton":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.SKELETON);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Enderman":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.ENDERMAN);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Creeper":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.CREEPER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Wither Skeleton":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.WITHER_SKELETON);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                case "Pig Zombie":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.PIG_ZOMBIE);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Spider":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.SPIDER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Cave Spider":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.CAVE_SPIDER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Giant":
                    BossMode.getInstance().getBossManager().createBossEditor(player, EntityType.GIANT);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
            }

        }

    }
}
