package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventorySpawnerSettings implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(CheckNull.checkNull(event))
            return;

        if(event.getInventory().getName().equalsIgnoreCase("§eSpawner Settings")) {
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch(itemName) {
                case "§eDelay":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_DELAY);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDelayClosedInventory"));
                    break;
                case "§eMin Spawn Delay":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_MIN_DELAY);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setMinSpawnDelayClosedInventory"));
                    break;
                case "§eMax Spawn Delay":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_MAX_DELAY);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setMaxSpawnDelayClosedInventory"));
                    break;
                case "§eRequired Player Range":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_REQUIRED_PLAYERS_RANGE);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setRequiredPlayerRangeClosedInventory"));
                    break;
                case "§eSpawn Count":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_SPAWN_AMOUNT);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnCountClosedInventory"));
                    break;
                case "§eSpawn Range":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER_SPAWN_RANGE);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnRangeClosedInventory"));
                    break;
                case "§eFinish":
                    ItemStack itemStack = BossMode.getInstance().getBossManager().createSpawner(BossMode.getInstance().getBossManager().getSpawnerEditor(player));
                    player.getInventory().addItem(itemStack);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("buildSpawner"));
                    BossMode.getInstance().getBossManager().getPlayerSpawnerEditor().remove(player);
                    player.closeInventory();
                    break;
            }

        }

    }

}
