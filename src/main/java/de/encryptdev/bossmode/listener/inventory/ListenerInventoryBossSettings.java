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
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryBossSettings implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;
        if (event.getInventory().getName().equalsIgnoreCase("§eBoss Settings")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch (itemName) {
                case "§2Health":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setHealthClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_HEALTH);
                    break;
                case "§4Damage":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDamageClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_DAMAGE);
                    break;
                case "§bEquipment":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                    break;
                case "§aBiome":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().getBiomeInventory(1));
                    break;
                case "§bSpawn Chance - Random Spawn":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSPawnChanceRandomSpawnClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_CHANCE_RANDOM_SPAWN);
                    break;
                case "§6Drops":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().openPutContentInventory(InventoryStorage.PutType.DROPS));
                    break;
                case "§4Advanced Settings":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().advancedSettings(BossMode.getInstance().getBossManager().getBossEditor(player)));
                    break;
                case "§eWalk Speed":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setWalkSpeedClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPEED);
                    break;
                case "§eFinish":
                    IBoss boss = BossMode.getInstance().getBossManager().getBossEditor(player).build();
                    BossMode.getInstance().getBossManager().createBoss(boss,
                            BossMode.getInstance().getBossManager().getEditBoss().contains(player));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("createBoss"));
                    BossMode.getInstance().getBossManager().getBossEditor(player).setFinish(true);
                    player.closeInventory();
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            BossMode.getInstance().getBossManager().getEditors().remove(player);
                        }
                    }.runTaskLater(BossMode.getInstance(), 40);
                    break;
                case "§eNearby Radius":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setNearbyRadiusClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_NEARBY_RADIUS);
                    break;
                case "§eSpawn Radius":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnRadiusClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_RADIUS);
                    break;
                case "§ePotion Effects":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                    break;
                case "§eName":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setNameClosedInventory"));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("useColorCodes"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_NAME);
                    break;
                case "§aDrop Chance - Main Hand":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDropChanceMainHandClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_DROP_CHANCE_MAIN_HAND);
                    break;
                case "§aSpecial Attack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().specialAttacks());
                    break;
                case "§bSpawn Location":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("spawnLocationClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_LOCATION);
                    break;
                case "§aChance to spawn":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("chanceToSpawnClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_CHANCE_TO_SPAWN);
                    break;
                case "§eSpawn Amount":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setMaxSpawnAmountClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_AMOUNT);
                    break;
                case "§aEXP":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDroppedEXPClosedInventory"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_DROPPED_XP);
                    break;
                case "§2Natural Spawn":
                    List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                    String lore0 = lore.get(0);
                    ItemStack newItem = null;
                    if (lore0.contains("OFF")) {
                        newItem = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 10,
                                Arrays.asList("§eNatural Drops: §2ON"));
                        event.getInventory().setItem(42, ItemCreator.getItem(Material.GRASS, "§aBiome", 1, (byte) 0,
                                Arrays.asList("§eSet the biome who the boss spawn")));
                        event.getInventory().setItem(43, ItemCreator.getItem(Material.GHAST_TEAR, "§aChance to spawn", 1, (byte) 0,
                                Arrays.asList("§eSet the chance to spawn", "§eMin: 1", "§eMax: 100")));
                        event.getInventory().setItem(44, ItemCreator.getItem(Material.ARROW, "§eSpawn Amount", 1, (byte) 0,
                                Arrays.asList("§eSet the maximal spawn amount")));
                    } else {
                        newItem = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 8,
                                Arrays.asList("§eNatural Drops: §4OFF"));
                        event.getInventory().setItem(42,  ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        event.getInventory().setItem(43,  ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        event.getInventory().setItem(44,  ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15));
                        BossMode.getInstance().getBossManager().getBossEditor(player).setBiome(null);
                    }

                    event.getInventory().setItem(event.getSlot(), newItem);
                    break;

            }

        }

    }

}
