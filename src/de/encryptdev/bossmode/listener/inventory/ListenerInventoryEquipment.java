package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryEquipment implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().equalsIgnoreCase("§bEquipment")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

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
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    break;
            }

        }
    }

}
