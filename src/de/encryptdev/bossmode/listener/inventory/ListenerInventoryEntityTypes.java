package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossEditor;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.EntityTypeVersion;
import de.encryptdev.bossmode.util.CheckNull;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryEntityTypes implements Listener {

    private BossManager bossManager;

    public ListenerInventoryEntityTypes(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().startsWith("§4Entity Type #")) {
            event.setCancelled(true);
            int side = Integer.parseInt(inventory.getName().split("#")[1]);

            String itemName = itemStack.getItemMeta().getDisplayName();

            BossEditor be = bossManager.getBossEditor(player);

            if (side == 1) {
                if (itemName.equalsIgnoreCase("§eFinish")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    return;
                } else if (itemName.equalsIgnoreCase("§eNext")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(2));
                    inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                } else {
                    String str = itemName.substring(2, itemName.length());
                    Class<?> clazz = EntityTypeVersion.valueOf(str).getNmsClass();

                    inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));

                    boolean b = be.addNearEntityClass(clazz.getName());
                    if (b) {
                        inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    } else {
                        be.removeNearEntityClass(clazz.getName());
                        inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    }
                }
            } else if (side == 2) {
                if (itemName.equalsIgnoreCase("§eFinish")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    return;
                }
                if (itemName.equalsIgnoreCase("§eBack")) {
                    player.openInventory(BossMode.getInstance().getInventoryStorage().entityTypesInventory(1));
                    inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                } else {
                    String str = itemName.substring(2, itemName.length());
                    Class<?> clazz = EntityTypeVersion.valueOf(str).getNmsClass();

                    boolean b = be.addNearEntityClass(clazz.getName());
                    if (b) {
                        inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    } else {
                        be.removeNearEntityClass(clazz.getName());
                        inventory.setItem(39, ItemCreator.getItem(Material.GOLD_NUGGET, "§eEntities", be.nearEntitySize()));
                    }
                }
            }
        }
    }

}
