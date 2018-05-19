package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by EncryptDev
 */
public class ListenerClosedInventory implements Listener {

    @EventHandler
    public void on(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getInventory().getName().equalsIgnoreCase("§eBoss Settings") ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_HELMET.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_CHESTPLATE.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_LEGGINGS.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.ARMOR_BOOTS.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.DROPS.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.OFF_HAND.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase(InventoryStorage.PutType.MAIN_HAND.getInvName()) ||
                event.getInventory().getName().equalsIgnoreCase("§eBiome 1") ||
                event.getInventory().getName().equalsIgnoreCase("§eBiome 2") ||
                event.getInventory().getName().equalsIgnoreCase("§eBiome 3") ||
                event.getInventory().getName().equalsIgnoreCase("§5Special Attacks") ||
                event.getInventory().getName().equalsIgnoreCase("§ePotion Effects") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eRegeneration") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eSwiftness") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eFire Resistance") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eHealing") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eStrength") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eSlowness") ||
                event.getInventory().getName().equalsIgnoreCase("§eSettings | §eInvisibility") ||
                event.getInventory().getName().equalsIgnoreCase("§4Advanced Settings") ||
                event.getInventory().getName().equalsIgnoreCase("§4Entity Type #1") ||
                event.getInventory().getName().equalsIgnoreCase("§4Entity Type #2") ||
                event.getInventory().getName().equalsIgnoreCase("§bEquipment")) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    if (!BossMode.getInstance().getBossManager().getChatCommand().containsKey(player)
                            && !BossMode.getInstance().getBossManager().getBossEditor(player).isFinish()
                            && player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                        BossMode.getInstance().getBossManager().getEditors().remove(player);
                        BossMode.getInstance().getBossManager().getPlayerBossEditor().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    } else if (!BossMode.getInstance().getBossManager().getChatCommand().containsKey(player)
                            && BossMode.getInstance().getBossManager().getBossEditor(player).isFinish()) {
                        BossMode.getInstance().getBossManager().getEditors().remove(player);
                        BossMode.getInstance().getBossManager().getPlayerBossEditor().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("finishEditorMode"));
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 5);

        } else if (event.getInventory().getName().equalsIgnoreCase("§eSet the entity type")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting")) {
                        BossMode.getInstance().getBossManager().getEditors().remove(player);
                        BossMode.getInstance().getBossManager().getPlayerBossEditor().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }

                }
            }.runTaskLater(BossMode.getInstance(), 5);
        } else if(event.getInventory().getName().equalsIgnoreCase("§eSpawner Settings")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if(player.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("container.crafting") &&
                            !BossMode.getInstance().getBossManager().getChatCommand().containsKey(player)) {
                        BossMode.getInstance().getBossManager().getPlayerSpawnerEditor().remove(player);
                        BossMode.getInstance().getBossManager().getEditors().remove(player);
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("leftEditorModeWithoutSave"));
                    }
                }
            }.runTaskLater(BossMode.getInstance(), 5);
        }

    }

}
