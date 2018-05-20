package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryAdvancedSettings extends InventoryListenerAdapter {
    
    private BossManager bossManager;
    
    public ListenerInventoryAdvancedSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }
    
    @Override
    public boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§4Advanced Settings")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eAttack Entity":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(1));
                    break;
                case "§eLook at player":
                    String lore = itemStack.getItemMeta().getLore().get(0);
                    if (lore.contains("OFF")) {
                        inventory.setItem(slot, ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§2ON", " ", "§eIf this is on, then the boss look always to the player")));
                        bossManager.getBossEditor(player).setLookAtPlayer(true);
                    } else if (lore.contains("ON")) {
                        inventory.setItem(slot, ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                                Arrays.asList("§4OFF", " ", "§eIf this is on, then the boss look always to the player")));
                        bossManager.getBossEditor(player).setLookAtPlayer(false);
                    }
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    break;
            }

        }
        return true;
    }

}
