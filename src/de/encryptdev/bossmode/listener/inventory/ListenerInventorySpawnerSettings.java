package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.lang.LanguageCode;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventorySpawnerSettings implements Listener {

    private BossManager bossManager;

    public ListenerInventorySpawnerSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§eSpawner Settings")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eDelay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_DELAY,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_DELAY, bossManager.getSpawnerEditor(player).getDelay()) ? InventoryStorage.CounterType.SPAWNER_DELAY.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getDelay()));
                    break;
                case "§eMin Spawn Delay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_MIN_DELAY,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_MIN_DELAY, bossManager.getSpawnerEditor(player).getMinSpawnDelay()) ? InventoryStorage.CounterType.SPAWNER_MIN_DELAY.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getMinSpawnDelay()));
                    break;
                case "§eMax Spawn Delay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_MAX_DELAY,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_MAX_DELAY, bossManager.getSpawnerEditor(player).getMaxSpawnDelay()) ? InventoryStorage.CounterType.SPAWNER_MAX_DELAY.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getMaxSpawnDelay()));
                    break;
                case "§eRequired Player Range":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_REQUIRED_PLAYERS_RANGE,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_REQUIRED_PLAYERS_RANGE, bossManager.getSpawnerEditor(player).getRequiredPlayerRange()) ? InventoryStorage.CounterType.SPAWNER_REQUIRED_PLAYERS_RANGE.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getRequiredPlayerRange()));
                    break;
                case "§eSpawn Count":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_SPAWN_COUNT,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_SPAWN_COUNT, bossManager.getSpawnerEditor(player).getSpawnCount()) ? InventoryStorage.CounterType.SPAWNER_SPAWN_COUNT.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getSpawnCount()));
                    break;
                case "§eSpawn Range":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_SPAWN_RANGE,
                            BossUtil.checkDefaultValue(InventoryStorage.CounterType.SPAWNER_SPAWN_RANGE, bossManager.getSpawnerEditor(player).getSpawnRange()) ? InventoryStorage.CounterType.SPAWNER_SPAWN_RANGE.getDefaultValue() :
                                    bossManager.getSpawnerEditor(player).getSpawnRange()));
                    break;
                case "§eFinish":
                    ItemStack itemStack0 = bossManager.createSpawner(bossManager.getSpawnerEditor(player));
                    player.getInventory().addItem(itemStack0);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.BUILD_SPAWNER));
                    bossManager.getPlayerSpawnerEditor().remove(player);
                    bossManager.getEditors().remove(player);
                    player.closeInventory();
                    break;
            }

        }
    }

}
