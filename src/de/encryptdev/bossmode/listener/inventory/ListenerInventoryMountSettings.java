package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryMountSettings extends InventoryListenerAdapter {

    private BossManager bossManager;

    public ListenerInventoryMountSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if(inventory.getName().equalsIgnoreCase("§eMount Settings")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch(itemName) {
                case "§eJump Strength":

                    break;
            }

        }
        return true;
    }
}
