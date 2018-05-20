package de.encryptdev.bossmode.listener.inventory;

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
public abstract class InventoryListenerAdapter implements Listener {


    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        boolean cancel = listener(player, event.getInventory(), event.getCurrentItem(), event.getSlot());
        event.setCancelled(cancel);
    }

    public abstract boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot);

}
