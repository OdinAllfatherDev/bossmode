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
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerPutInventory implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_HELMET.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack helmet = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setHelmet(helmet);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setHelmet(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    event.setCancelled(true);
                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_CHESTPLATE.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack chestplate = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setChestplate(chestplate);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setChestplate(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    event.setCancelled(true);
                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_LEGGINGS.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack leggings = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setLeggings(leggings);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setLeggings(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    event.setCancelled(true);
                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_BOOTS.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack boots = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setBoots(boots);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setBoots(null);
                    }

                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    event.setCancelled(true);

                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.DROPS.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack[] items = event.getInventory().getContents();
                        for (ItemStack item : items) {
                            if (item == null)
                                continue;
                            if (item.hasItemMeta())
                                if (item.getItemMeta().getDisplayName().equals("§6§lSet"))
                                    continue;

                            BossMode.getInstance().getBossManager().getBossEditor(player).setNaturalDrops(item);
                        }
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setNaturalDrops(null);
                    }
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    event.setCancelled(true);
                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.OFF_HAND.getInvName())) {
            if (event.getCurrentItem().hasItemMeta())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack offHand = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setOffHand(offHand);
                        player.closeInventory();
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDropChanceOffHandItemClosedInventory"));
                        BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_DROP_CHANCE_OFF_HAND);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setOffHand(null);
                    }
                    event.setCancelled(true);
                }
        } else if (event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.MAIN_HAND.getInvName())) {
            if (event.getCurrentItem().getItemMeta().hasDisplayName())
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSet")) {
                    if (!checkEmptyInventory(event.getInventory())) {
                        ItemStack mainHand = getSingleItem(event.getInventory());
                        BossMode.getInstance().getBossManager().getBossEditor(player).setMainHand(mainHand);
                        player.closeInventory();
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDropChanceMainHandClosedInventory"));
                        BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_DROP_CHANCE_MAIN_HAND);
                    } else {
                        BossMode.getInstance().getBossManager().getBossEditor(player).setMainHand(null);
                    }
                    event.setCancelled(true);
                }
        }

    }

    private boolean checkEmptyInventory(Inventory inventory) {
        return inventory.getContents().length <= 1;
    }

    private ItemStack getSingleItem(Inventory inventory) {
        for (ItemStack stack : inventory.getContents())
            if (stack != null)
                if (stack.getType() != Material.DIAMOND)
                    return stack;

        return null;
    }


}
