package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.special.TeleportSpecialAttack;
import de.encryptdev.bossmode.boss.util.BossManager;
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
public class ListenerInventorySpecialAttack implements Listener {

    private BossManager bossManager;

    public ListenerInventorySpecialAttack(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (CheckNull.checkNull(event))
            return;

        ItemStack itemStack = event.getCurrentItem();

        if (inventory.getName().equalsIgnoreCase("§5Special Attacks")) {
            event.setCancelled(true);
            String itemName = itemStack.getItemMeta().getDisplayName();

            switch (itemName) {
                case "§bMessages":
                    player.closeInventory();
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("messagesSpecialAttack"));
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("useColorCodes"));
                    bossManager.getChatCommand().put(player, BossManager.CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE);
                    break;
                case "§5Teleport":
                    BossMode.getInstance().getBossManager()
                            .getBossEditor(player).addSpecialAttack(new TeleportSpecialAttack());
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    break;
                case "§eStomp":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPECIAL_ATTACK_STOMP_STRENGTH));
                    break;
                case "§eArmy":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().counterInventory(InventoryStorage.CounterType.SPECIAL_ATTACK_ARMY_AMOUNT));
                    break;
            }
        }
    }

}
