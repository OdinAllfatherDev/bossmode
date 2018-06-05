package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.lang.LanguageCode;
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
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.HEALTH,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.HEALTH, bossManager.getBossEditor(player).getMaxHealth()) ? InventoryStorage.CounterType.HEALTH.getDefaultValue() :
                                    bossManager.getBossEditor(player).getMaxHealth()));
                    break;
                case "§4Damage":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DAMAGE,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.DAMAGE, bossManager.getBossEditor(player).getDamage()) ? InventoryStorage.CounterType.DAMAGE.getDefaultValue() :
                                    bossManager.getBossEditor(player).getDamage()));
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
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPEED,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPEED, bossManager.getBossEditor(player).getSpeed()) ? InventoryStorage.CounterType.SPEED.getDefaultValue() :
                                    bossManager.getBossEditor(player).getSpeed()));
                    break;
                case "§eFinish":
                    IBoss boss = bossManager.getBossEditor(player).build();
                    bossManager.createBoss(boss,
                            bossManager.getEditBoss().contains(player));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.CREATE_BOSS));
                    bossManager.getBossEditor(player).setFinish(true);
                    bossManager.getPlayerBossEditor().remove(player);
                    bossManager.getEditors().remove(player);
                    bossManager.getPlayerSpawnerEditor().remove(player);
                    bossManager.getEditBoss().remove(player);
                    player.closeInventory();
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            bossManager.getEditors().remove(player);
                        }
                    }.runTaskLater(BossMode.getInstance(), 40);
                    break;
                case "§eNearby Radius":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.NEARBY_RADIUS,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.NEARBY_RADIUS, bossManager.getBossEditor(player).getNearbyRad()) ? InventoryStorage.CounterType.NEARBY_RADIUS.getDefaultValue() :
                                    bossManager.getBossEditor(player).getNearbyRad()));
                    break;
                case "§eMount":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().mountTypes());
                    break;
                case "§eSpawn Radius":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWN_RADIUS,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWN_RADIUS, bossManager.getBossEditor(player).getSpawnRadius()) ? InventoryStorage.CounterType.SPAWN_RADIUS.getDefaultValue() :
                                    bossManager.getBossEditor(player).getSpawnRadius()));
                    break;
                case "§ePotion Effects":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                    break;
                case "§eName":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.SET_NAME_CLOSED_INVENTORY));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.USE_COLOR_CODES));
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_NAME);
                    break;
                case "§aSpecial Attack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().specialAttacks());
                    break;
                case "§bSpawn Location":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.SPAWN_LOCATION_CLOSED_INVENTORY));
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWN_LOCATION);
                    break;
                case "§aChance to spawn":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.CHANCE_TO_SPAWN,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.CHANCE_TO_SPAWN, bossManager.getBossEditor(player).getChanceToSpawn()) ? InventoryStorage.CounterType.CHANCE_TO_SPAWN.getDefaultValue() :
                                    bossManager.getBossEditor(player).getChanceToSpawn()));
                    break;
                case "§eSpawn Amount":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWN_AMOUNT,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWN_AMOUNT, bossManager.getBossEditor(player).getSpawnAmount()) ? InventoryStorage.CounterType.SPAWN_AMOUNT.getDefaultValue() :
                                    bossManager.getBossEditor(player).getSpawnAmount()));
                    break;
                case "§aEXP":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.DROPPED_XP,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.DROPPED_XP, bossManager.getBossEditor(player).getDroppedXp()) ? InventoryStorage.CounterType.DROPPED_XP.getDefaultValue() :
                                    bossManager.getBossEditor(player).getDroppedXp()));
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
