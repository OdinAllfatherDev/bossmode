package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerPutInventory extends InventoryListenerAdapter {

    private BossManager bossManager;
    
    public ListenerPutInventory(BossManager bossManager) {
        this.bossManager = bossManager;
    }
    
    @Override
    public AdapterCallback listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_HELMET.getInvName())) {
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
                    if (!checkEmptyInventory(inventory)) {
                        ItemStack leggings = getSingleItem(inventory);
                        bossManager.getBossEditor(player).setLeggings(leggings);
                    } else {
                        bossManager.getBossEditor(player).setLeggings(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());

                }
        } else if (inventory.getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_BOOTS.getInvName())) {
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
        return new AdapterCallback(inventory, true);
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
