package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
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
public class ListenerPutInventory implements Listener {

    private BossManager bossManager;
    
    public ListenerPutInventory(BossManager bossManager) {
        this.bossManager = bossManager;
    }
    
    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if(CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_HELMET.getInvName())) {
            event.setCancelled(true);
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack helmet = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setHelmet(helmet);
                    } else {
                        bossManager.getBossEditor(player).setHelmet(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_CHESTPLATE.getInvName())) {
            event.setCancelled(true);
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack chestplate = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setChestplate(chestplate);
                    } else {
                        bossManager.getBossEditor(player).setChestplate(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_LEGGINGS.getInvName())) {
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    event.setCancelled(true);
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack leggings = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setLeggings(leggings);
                    } else {
                        bossManager.getBossEditor(player).setLeggings(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_BOOTS.getInvName())) {
            event.setCancelled(true);
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack boots = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setBoots(boots);
                    } else {
                        bossManager.getBossEditor(player).setBoots(null);
                    }

                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());


                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.DROPS.getInvName())) {
            event.setCancelled(true);
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack[] items = inventory.getContents();
                        for (ItemStack item : items) {
                            if (item == null)
                                continue;
                            if (item.hasItemMeta())
                                if (item.getItemMeta().getDisplayName().equals("§6§lSet"))
                                    continue;

                            bossManager.getBossEditor(player).setNaturalDrops(item);
                        }
                    } else {
                        bossManager.getBossEditor(player).setNaturalDrops(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.OFF_HAND.getInvName())) {
            event.setCancelled(true);
            if (itemStack.hasItemMeta())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack offHand = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setOffHand(offHand);
                        player.closeInventory();
                        player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DROP_CHANCE_OFF_HAND));
                    } else {
                        bossManager.getBossEditor(player).setOffHand(null);
                    }

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.MAIN_HAND.getInvName())) {
            event.setCancelled(true);
            if (itemStack.getItemMeta().hasDisplayName())
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack mainHand = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setMainHand(mainHand);
                        player.closeInventory();
                        player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DROP_CHANCE_MAIN_HAND));
                    } else {
                        bossManager.getBossEditor(player).setMainHand(null);
                    }

                }
        }
    }

    private boolean checkEmptyInventory(Inventory inventory) {
        return inventory.getContents().length <= 1;
    }

    private ItemStack getSingleItem(Inventory inventory) {
        for (ItemStack stack : inventory.getContents())
            if (stack != null)
                if (stack.getType() != Material.STAINED_GLASS_PANE && stack.getType() != Material.DIAMOND)
                    return stack;

        return null;
    }


}
