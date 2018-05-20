package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryFirst extends InventoryListenerAdapter {

    private BossManager bossManager;

    public ListenerInventoryFirst(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§eNEW BOSS | CHANGE BOSS")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§5§lNew boss":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().changeEntityType());
                    bossManager.getEditors().add(player);
                    break;
                case "§eSpawner":
                    player.closeInventory();

                    if (bossManager.getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("emptyBossList"));
                        return true;
                    }

                    List<IBoss> allBosses = bossManager.getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses.size(); i++)
                        player.sendMessage(allBosses.get(i).getBossName() + " §a| ID: §4" + allBosses.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("writeIdNow"));
                    bossManager.getEditors().add(player);
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_SPAWNER);
                    break;
                case "§5§lEdit boss":
                    player.closeInventory();

                    if (bossManager.getBosses().isEmpty()) {
                        player.sendMessage(BossMode.getInstance().getTranslatedMessage("emptyBossList"));
                        return true;
                    }

                    List<IBoss> allBosses0 = bossManager.getBosses();

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                    for (int i = 0; i < allBosses0.size(); i++)
                        player.sendMessage(allBosses0.get(i).getBossName() + " §a| ID: §4" + allBosses0.get(i).getBossID());

                    player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("writeIdNow"));
                    bossManager.getEditBoss().add(player);
                    bossManager.getEditors().add(player);
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_EDIT_BOSS);
                    break;
            }

        }
        return true;
    }

}
