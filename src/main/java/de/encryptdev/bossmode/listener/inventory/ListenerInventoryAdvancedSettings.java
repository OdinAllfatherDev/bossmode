package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.util.CheckNull;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryAdvancedSettings implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().equalsIgnoreCase("§4Advanced Settings")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eAttack Entity":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(1));
                    break;
                case "§eLook at player":
                    String lore = event.getCurrentItem().getItemMeta().getLore().get(0);
                    if (lore.contains("OFF")) {
                        event.getInventory().setItem(event.getSlot(), ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§2ON", " ", "§eIf this is on, then the boss look always to the player")));
                        BossMode.getInstance().getBossManager().getBossEditor(player).setLookAtPlayer(true);
                    } else if (lore.contains("ON")) {
                        event.getInventory().setItem(event.getSlot(), ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§4OFF", " ", "§eIf this is on, then the boss look always to the player")));
                        BossMode.getInstance().getBossManager().getBossEditor(player).setLookAtPlayer(false);
                    }
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    break;
            }

        }
    }

}
