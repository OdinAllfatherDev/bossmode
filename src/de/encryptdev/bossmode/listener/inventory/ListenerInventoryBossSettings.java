package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.IBoss;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryBossSettings implements Listener {

    private BossManager bossManager;

    public ListenerInventoryBossSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if(CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§eBoss Settings")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§2Health":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.HEALTH));
                    break;
                case "§4Damage":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DAMAGE));
                    break;
                case "§bEquipment":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    break;
                case "§aBiome":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(1));
                    break;
                case "§6Drops":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.DROPS));
                    break;
                case "§4Advanced Settings":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().advancedSettings(bossManager.getBossEditor(player)));
                    break;
                case "§eWalk Speed":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPEED));
                    break;
                case "§eFinish":
                    IBoss boss = bossManager.getBossEditor(player).build();
                    bossManager.createBoss(boss,
                            bossManager.getEditBoss().contains(player));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("createBoss"));
                    bossManager.getBossEditor(player).setFinish(true);
                    player.closeInventory();
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            bossManager.getEditors().remove(player);
                        }
                    }.runTaskLater(BossMode.getInstance(), 40);
                    break;
                case "§eNearby Radius":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.NEARBY_RADIUS));
                    break;
                case "§eMount":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().mountTypes());
                    break;
                case "§eSpawn Radius":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWN_RADIUS));
                    break;
                case "§ePotion Effects":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                    break;
                case "§eName":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setNameClosedInventory"));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("useColorCodes"));
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_NAME);
                    break;
                case "§aSpecial Attack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().specialAttacks());
                    break;
                case "§bSpawn Location":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("spawnLocationClosedInventory"));
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_LOCATION);
                    break;
                case "§aChance to spawn":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.CHANCE_TO_SPAWN));
                    break;
                case "§eSpawn Amount":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWN_AMOUNT));
                    break;
                case "§aEXP":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DROPPED_XP));
                    break;
                case "§2Natural Spawn":
                    List<String> lore = itemStack.getItemMeta().getLore();
                    String lore0 = lore.get(0);
                    ItemStack newItem = null;
                    if (lore0.contains("OFF")) {
                        newItem = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 10,
                                Arrays.asList("§eNatural Drops: §2ON"));
                        inventory.setItem(42, ItemCreator.getItem(Material.GRASS, "§aBiome", 1, (byte) 0,
                                Arrays.asList("§eSet the biome who the boss spawn")));
                        inventory.setItem(43, ItemCreator.getItem(Material.GHAST_TEAR, "§aChance to spawn", 1, (byte) 0,
                                Arrays.asList("§eSet the chance to spawn", "§eMin: 1", "§eMax: 100")));
                        inventory.setItem(44, ItemCreator.getItem(Material.ARROW, "§eSpawn Amount", 1, (byte) 0,
                                Arrays.asList("§eSet the maximal spawn amount")));
                    } else {
                        newItem = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 8,
                                Arrays.asList("§eNatural Drops: §4OFF"));
                        inventory.setItem(42, ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        inventory.setItem(43, ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        inventory.setItem(44, ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        bossManager.getBossEditor(player).setBiome(null);
                    }

                    inventory.setItem(event.getSlot(), newItem);
                    break;

            }

        }
    }
}
