package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryAdvancedSettings implements Listener {

    private BossManager bossManager;

    public ListenerInventoryAdvancedSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§4Advanced Settings")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eAttack Entity":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(1));
                    break;
                case "§eLook at player":
                    String lore = itemStack.getItemMeta().getLore().get(0);
                    if (lore.contains("OFF")) {
                        inventory.setItem(event.getSlot(), ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§2ON", " ", "§eIf this is on, then the boss look always to the player")));
                        bossManager.getBossEditor(player).setLookAtPlayer(true);
                    } else if (lore.contains("ON")) {
                        inventory.setItem(event.getSlot(), ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§4OFF", " ", "§eIf this is on, then the boss look always to the player")));
                        bossManager.getBossEditor(player).setLookAtPlayer(false);
                    }
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    break;
            }

        }
    }

}
