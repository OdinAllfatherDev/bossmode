package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.mount.BossMount;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryMount extends InventoryListenerAdapter {

    private BossManager bossManager;
    
    public ListenerInventoryMount(BossManager bossManager) {
        this.bossManager = bossManager;
    }
    
    @Override
    public boolean listener(Player player, Inventory inventory, ItemStack itemStack, int slot) {
        if (inventory.getName().equalsIgnoreCase("§eSet the mount type")) {
            String itemName = itemStack.getItemMeta().getDisplayName();

            if (itemName.equalsIgnoreCase("§eBack")) {
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                return true;
            }

            for(BossMount.BossMountType mountType : BossMount.BossMountType.values()) {
                String name = "§e" + BossUtil.makeEnumNameNormal(mountType);
                if(itemName.equalsIgnoreCase(name)) {
                    bossManager.createMountEditor(player, mountType);
                    player.openInventory(BossMode.getInstance().getInventoryStorage().mountSettings());
                    return true;
                }
            }

        }
        return true;
    }

}
