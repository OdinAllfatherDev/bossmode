package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryBiome extends InventoryListenerAdapter {
    
    private BossManager bossManager;
    
    public ListenerInventoryBiome(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if(inventory.getName().equalsIgnoreCase("§eBiome 1")) {
            String itemName = itemStack.getItemMeta().getDisplayName();
            if(itemName.equalsIgnoreCase("§eNext")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(2));
            } else if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                bossManager.getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }


        } else if(inventory.getName().equalsIgnoreCase("§eBiome 2")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            if(itemName.equalsIgnoreCase("§eNext")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(3));
            } else if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(1));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                bossManager.getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }

        } else if(inventory.getName().equalsIgnoreCase("§eBiome 3")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(2));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                bossManager.getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }
        }
        return true;
    }
}
