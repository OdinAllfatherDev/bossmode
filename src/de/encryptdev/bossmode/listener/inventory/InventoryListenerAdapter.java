package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
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

        AdapterCallback callback = listener(player, event.getInventory(), event.getCurrentItem(), event.getSlot());
        if(event.getInventory() == callback.getInventory())
            event.setCancelled(callback.isCancel());
    }

    @EventHandler
    public void on(InventoryMoveItemEvent event) {
        if(event.getDestination().getType() == InventoryType.PLAYER)
            event.setCancelled(false);
    }

    public abstract AdapterCallback listener(Player player, Inventory inventory, ItemStack itemStack, int slot);

    public class AdapterCallback {

        private Inventory inventory;
        private boolean cancel;

        public AdapterCallback(Inventory inventory, boolean cancel) {
            this.inventory = inventory;
            this.cancel = cancel;
        }

        public Inventory getInventory() {
            return inventory;
        }

        public boolean isCancel() {
            return cancel;
        }
    }

}
