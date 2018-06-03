package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossEditor;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by EncryptDev
 */
public class ListenerClosedInventory implements Listener {

    private BossManager bossManager;

    public ListenerClosedInventory(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (bossManager.getBossEditor(player) != null) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    BossEditor editor = bossManager.getBossEditor(player);
                    if (!editor.isFinish() && !bossManager.getChatCommand().containsKey(player)) {
                        if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                            bossManager.getPlayerBossEditor().remove(player);
                            bossManager.getEditors().remove(player);
                            bossManager.getEditBoss().remove(player);
                            player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                        }
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 10);
        } else if (bossManager.getPlayerSpawnerEditor().get(player) != null) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (!bossManager.getChatCommand().containsKey(player)) {
                        if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                            bossManager.getPlayerSpawnerEditor().remove(player);
                            bossManager.getEditors().remove(player);
                            player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                        }
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 10);
        }
        if (event.getInventory().getName().equalsIgnoreCase("§eSet the entity type")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                        bossManager.getEditors().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 10);
            return;
        }

    }
}
