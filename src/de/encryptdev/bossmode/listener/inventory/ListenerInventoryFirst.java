package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryFirst implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(CheckNull.checkNull(event))
            return;

        if(event.getInventory().getName().equalsIgnoreCase("§eNEW BOSS | CHANGE BOSS")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch(itemName) {
                case "§5§lNew boss":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().changeEntityType());
                    BossMode.getInstance().getBossManager().getEditors().add(player);
                    break;
                case "§eSpawner":
                    player.closeInventory();

                    if(BossMode.getInstance().getBossManager().getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("emptyBossList"));
                        return;
                    }

                    List<IBoss> allBosses = BossMode.getInstance().getBossManager().getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses.size(); i++)
                        player.sendMessage(allBosses.get(i).getBossName() + " §a| ID: §4" + allBosses.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("writeIdNow"));
                    BossMode.getInstance().getBossManager().getEditors().add(player);
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER);
                    break;
                case "§5§lEdit boss":
                    player.closeInventory();

                    if(BossMode.getInstance().getBossManager().getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("emptyBossList"));
                        return;
                    }

                    List<IBoss> allBosses0 = BossMode.getInstance().getBossManager().getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses0.size(); i++)
                        player.sendMessage(allBosses0.get(i).getBossName() + " §a| ID: §4" + allBosses0.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("writeIdNow"));
                    BossMode.getInstance().getBossManager().getEditBoss().add(player);
                    BossMode.getInstance().getBossManager().getEditors().add(player);
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_EDIT_BOSS);
                    break;
            }

        }

    }

}
