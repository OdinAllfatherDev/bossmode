package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryMountType extends InventoryListenerAdapter {

    private BossManager bossManager;

    public ListenerInventoryMountType(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public AdapterCallback listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§eMountTypes")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                return new AdapterCallback(inventory, true);
            } else if(itemName.equalsIgnoreCase("§4Reset")) {
                bossManager.getBossEditor(player).setMount(null, 20.0);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                return new AdapterCallback(inventory, true);
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
        return new AdapterCallback(inventory, true);
    }
}
