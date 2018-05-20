package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
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

        Inventory inventory = event.getInventory();

        if (inventory.getName().equalsIgnoreCase("§eBoss Settings") ||
                inventory.getName().equalsIgnoreCase("§eBiome 1") ||
                inventory.getName().equalsIgnoreCase("§eBiome 2") ||
                inventory.getName().equalsIgnoreCase("§eBiome 3") ||
                inventory.getName().equalsIgnoreCase("§5Special Attacks") ||
                inventory.getName().equalsIgnoreCase("§ePotion Effects") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eRegeneration") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eSwiftness") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eFire Resistance") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eHealing") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eStrength") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eSlowness") ||
                inventory.getName().equalsIgnoreCase("§eSettings | §eInvisibility") ||
                inventory.getName().equalsIgnoreCase("§4Advanced Settings") ||
                inventory.getName().equalsIgnoreCase("§4Entity Type #1") ||
                inventory.getName().equalsIgnoreCase("§4Entity Type #2") ||
                inventory.getName().equalsIgnoreCase("§bEquipment") ||
                inventory.getName().equalsIgnoreCase("§eSet the mount type")) {

            checkQuit(player);

        } else if (inventory.getName().equalsIgnoreCase("§eSet the entity type")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                        bossManager.getEditors().remove(player);
                        bossManager.getPlayerBossEditor().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }

                }
            }.runTaskLater(BossMode.getInstance(), 5);
        } else if (inventory.getName().equalsIgnoreCase("§eSpawner Settings")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting") &&
                            !bossManager.getChatCommand().containsKey(player)) {
                        bossManager.getPlayerSpawnerEditor().remove(player);
                        bossManager.getEditors().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 5);
        } else if (inventory.getName().equalsIgnoreCase("§eMount Settings")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if(player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting") &&
                            !bossManager.getChatCommand().containsKey(player)) {
                        bossManager.getPlayerMountEditor().remove(player);
                        bossManager.getEditors().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 5);
        }
        for (InventoryStorage.CounterType counterType : InventoryStorage.CounterType.values())
            if (event.getInventory().getName().equalsIgnoreCase(counterType.getInventoryName())) {
                checkQuit(player);
            }

    }

    private void checkQuit(Player player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!bossManager.getChatCommand().containsKey(player)
                        && !bossManager.getBossEditor(player).isFinish()
                        && player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                    bossManager.getEditors().remove(player);
                    bossManager.getPlayerBossEditor().remove(player);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                } else if (!bossManager.getChatCommand().containsKey(player)
                        && bossManager.getBossEditor(player).isFinish()) {
                    bossManager.getEditors().remove(player);
                    bossManager.getPlayerBossEditor().remove(player);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("finishEditorMode"));
                }
            }
        }.runTaskLater(BossMode.getInstance(), 5);
    }
}
