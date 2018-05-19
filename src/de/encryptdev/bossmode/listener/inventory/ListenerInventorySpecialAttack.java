package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.special.TeleportSpecialAttack;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by EncryptDev
 */
public class ListenerInventorySpecialAttack implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(CheckNull.checkNull(event))
            return;

        if(event.getInventory().getName().equalsIgnoreCase("§5Special Attacks")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch(itemName) {
                case "§bMessages":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("messagesSpecialAttack"));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("useColorCodes"));
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE);
                    break;
                case "§5Teleport":
                    BossMode.getInstance().getBossManager()
                            .getBossEditor(player).addSpecialAttack(new TeleportSpecialAttack());
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    break;
                case "§eStomp":
                    player.closeInventory();
                    BossMode.getInstance().getBossManager().getChatCommand().put(player, BossManager.CHAT_COMMAND_SPECIAL_ATTACK_STRENGTH);
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("strengthSpecialAttack"));
                    break;
                case "§eArmy":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("amountOfEntitySpecialAttack"));
                    BossMode.getInstance().getBossManager().getChatCommand()
                            .put(player, BossManager.CHAT_COMMAND_SPECIAL_ATTACK_ARMY);
                    break;
            }
        }
    }

}
