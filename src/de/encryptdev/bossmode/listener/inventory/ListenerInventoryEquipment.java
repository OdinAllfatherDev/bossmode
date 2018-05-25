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
public class ListenerInventoryEquipment implements Listener {

    private BossManager bossManager;

    public ListenerInventoryEquipment(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§bEquipment")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§bArmor - Helmet":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.ARMOR_HELMET));
                    break;
                case "§bArmor - Chestplate":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.ARMOR_CHESTPLATE));
                    break;
                case "§bArmor - Leggings":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.ARMOR_LEGGINGS));
                    break;
                case "§bArmor - Boots":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.ARMOR_BOOTS));
                    break;
                case "§6Drops":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.DROPS));
                    break;
                case "§aMain Hand":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.MAIN_HAND));
                    break;
                case "§aOff Hand":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.OFF_HAND));
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    break;
            }

        }
    }

}
