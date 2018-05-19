package de.encryptdev.bossmode.util;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Created by EncryptDev
 */
public class CheckNull {

    /**
     * This method check if the clicked item is null
     *
     * @param event
     * @return
     */
    public static boolean checkNull(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta() || event.getSlotType().equals(InventoryType.SlotType.OUTSIDE))
            return true;
        return false;
    }

}
