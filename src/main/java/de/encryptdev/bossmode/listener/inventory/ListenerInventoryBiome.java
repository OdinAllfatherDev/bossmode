package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryBiome implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(CheckNull.checkNull(event))
            return;

        if(event.getInventory().getName().equalsIgnoreCase("§eBiome 1")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
            if(itemName.equalsIgnoreCase("§eNext")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(2));
            } else if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                BossMode.getInstance().getBossManager().getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }


        } else if(event.getInventory().getName().equalsIgnoreCase("§eBiome 2")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            if(itemName.equalsIgnoreCase("§eNext")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(3));
            } else if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(1));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                BossMode.getInstance().getBossManager().getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }

        } else if(event.getInventory().getName().equalsIgnoreCase("§eBiome 3")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            if(itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(2));
            }

            if(itemName.contains("§a§l")) {
                Biome biome = Biome.valueOf(itemName.replace("§a§l", ""));
                BossMode.getInstance().getBossManager().getBossEditor(player).setBiome(biome);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(true));
            }
        }

    }

}
