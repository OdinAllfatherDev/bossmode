package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryChangeEntityType implements Listener {

    private BossManager bossManager;

    public ListenerInventoryChangeEntityType(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§eSet the entity type")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();
            String normalName = itemName.substring(4, itemName.length());

            switch (normalName) {
                case "Zombie":
                    bossManager.createBossEditor(player, EntityType.ZOMBIE);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Skeleton":
                    bossManager.createBossEditor(player, EntityType.SKELETON);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Enderman":
                    bossManager.createBossEditor(player, EntityType.ENDERMAN);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Creeper":
                    bossManager.createBossEditor(player, EntityType.CREEPER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Wither Skeleton":
                    bossManager.createBossEditor(player, EntityType.WITHER_SKELETON);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                case "Pig Zombie":
                    bossManager.createBossEditor(player, EntityType.PIG_ZOMBIE);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Spider":
                    bossManager.createBossEditor(player, EntityType.SPIDER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Cave Spider":
                    bossManager.createBossEditor(player, EntityType.CAVE_SPIDER);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
                case "Giant":
                    bossManager.createBossEditor(player, EntityType.GIANT);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(false));
                    break;
            }

        }
    }
}
