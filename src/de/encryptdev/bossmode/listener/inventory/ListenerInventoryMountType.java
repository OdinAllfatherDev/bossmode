package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryMountType implements Listener {

    private BossManager bossManager;

    public ListenerInventoryMountType(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;
        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§eMountTypes")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            if (itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                return;
            } else if (itemName.equalsIgnoreCase("§4Reset")) {
                bossManager.getBossEditor(player).setMount(null, 20.0);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                return;
            }

            switch (itemName) {
                case "§aPig":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.MOUNT_HEALTH_PIG));
                    break;
                case "§aCow":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.MOUNT_HEALTH_COW));
                    break;
                case "§aBat":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.MOUNT_HEALTH_BAT));
                    break;
            }
        }
    }
}
