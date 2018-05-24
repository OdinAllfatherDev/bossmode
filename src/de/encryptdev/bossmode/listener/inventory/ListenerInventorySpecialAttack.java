package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.special.TeleportSpecialAttack;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventorySpecialAttack extends InventoryListenerAdapter {

    private BossManager bossManager;

    public ListenerInventorySpecialAttack(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @Override
    public AdapterCallback listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§5Special Attacks")) {
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
        return new AdapterCallback(inventory, true);
    }

}
