package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.lang.LanguageCode;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryFirst implements Listener {

    private BossManager bossManager;

    public ListenerInventoryFirst(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§eNEW BOSS | CHANGE BOSS")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§5§lNew boss":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().changeEntityType());
                    bossManager.getEditors().add(player);
                    break;
                case "§eSpawner":
                    player.closeInventory();

                    if (bossManager.getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.EMPTY_BOSS_LIST));
                        return;
                    }

                    List<IBoss> allBosses = bossManager.getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses.size(); i++)
                        player.sendMessage(allBosses.get(i).getBossName() + " §a| ID: §4" + allBosses.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.WRITE_ID_NOW));
                    bossManager.getEditors().add(player);
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER);
                    break;
                case "§5§lEdit boss":
                    player.closeInventory();

                    if (bossManager.getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.EMPTY_BOSS_LIST));
                        return;
                    }

                    List<IBoss> allBosses0 = bossManager.getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses0.size(); i++)
                        player.sendMessage(allBosses0.get(i).getBossName() + " §a| ID: §4" + allBosses0.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.WRITE_ID_NOW));
                    bossManager.getEditBoss().add(player);
                    bossManager.getEditors().add(player);
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_EDIT_BOSS);
                    break;
            }

        }
    }

}
