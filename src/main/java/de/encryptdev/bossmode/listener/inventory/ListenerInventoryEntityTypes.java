package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossEditor;
import de.encryptdev.bossmode.boss.util.EntityTypeVersion;
import de.encryptdev.bossmode.util.CheckNull;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryEntityTypes implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().startsWith("§4Entity Type #")) {
            event.setCancelled(true);
            int side = Integer.parseInt(event.getInventory().getName().split("#")[1]);

            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            BossEditor be = BossMode.getInstance().getBossManager().getBossEditor(player);

            if (side == 1) {
                if (itemName.equalsIgnoreCase("§eFinish")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    return;
                } else if (itemName.equalsIgnoreCase("§eNext")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(2));
                    event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                } else {
                    String str = itemName.substring(2, itemName.length());
                    System.out.println(str);
                    Class<?> clazz = EntityTypeVersion.valueOf(str).getNmsClass();

                    event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));

                    boolean b = be.addNearEntityClass(clazz.getName());
                    if (b) {
                        event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    } else {
                        be.removeNearEntityClass(clazz.getName());
                        event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    }
                }
            } else if (side == 2) {
                if (itemName.equalsIgnoreCase("§eFinish")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    return;
                }
                if (itemName.equalsIgnoreCase("§eBack")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(1));
                    event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                } else {
                    String str = itemName.substring(2, itemName.length());
                    Class<?> clazz = EntityTypeVersion.valueOf(str).getNmsClass();

                    boolean b = be.addNearEntityClass(clazz.getName());
                    if (b) {
                        event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    } else {
                        be.removeNearEntityClass(clazz.getName());
                        event.getInventory().setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    }
                }
            }
        }

    }

}
