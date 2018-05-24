package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventorySpawnerSettings extends InventoryListenerAdapter {

    private BossManager bossManager;

    public ListenerInventorySpawnerSettings(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public AdapterCallback listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§eSpawner Settings")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eDelay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_DELAY));
                    break;
                case "§eMin Spawn Delay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_MIN_DELAY));
                    break;
                case "§eMax Spawn Delay":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_MAX_DELAY));
                    break;
                case "§eRequired Player Range":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_REQUIRED_PLAYERS_RANGE));
                    break;
                case "§eSpawn Count":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_SPAWN_COUNT));
                    break;
                case "§eSpawn Range":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPAWNER_SPAWN_RANGE));
                    break;
                case "§eFinish":
                    ItemStack itemStack0 = bossManager.createSpawner(bossManager.getSpawnerEditor(player));
                    player.getInventory().addItem(itemStack0);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("buildSpawner"));
                    bossManager.getPlayerSpawnerEditor().remove(player);
                    player.closeInventory();
                    break;
            }

        }
        return new AdapterCallback(inventory, true);
    }

}
