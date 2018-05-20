package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.special.MessageSpecialAttack;
import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerChat implements Listener {

    private HashMap<Player, List<String>> specialAttackMesasges;
    private BossManager bossManager;

    public ListenerChat(BossManager bossManager) {
        this.bossManager = bossManager;
        this.specialAttackMesasges = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (bossManager.getChatCommand().containsKey(player)) {
            event.setCancelled(true);
            String cc = bossManager.getChatCommand().get(player);

            if (cc.equals(BossManager.CHAT_COMMAND_SPAWN_LOCATION) && event.getMessage().equalsIgnoreCase("#finish")) {
                bossManager.getBossEditor(player)
                        .setSpawnLocation(player.getLocation());
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnLocation"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));
                bossManager.getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_EDIT_BOSS)) {
                int i = parseInt(event.getMessage().replace(",", "."));

                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                IBoss iBoss = bossManager.getBoss(i);
                if (iBoss == null) {
                    player.sendMessage(BossMode.PREFIX + "The boss with the id '" + i + "' doesn't exist");
                    return;
                }

                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(iBoss.getBossSettings().isNaturalSpawn()));
                bossManager.createBossEditor(player, iBoss);
                bossManager.getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE)) {
                String message = event.getMessage();
                if (message.equalsIgnoreCase("#finish") && specialAttackMesasges.containsKey(player)) {

                    List<String> messages = specialAttackMesasges.get(player);
                    bossManager.getBossEditor(player)
                            .addSpecialAttack(new MessageSpecialAttack(messages));
                    specialAttackMesasges.remove(player);
                    player.openInventory(BossMode.getInstance().getInventoryStorage()
                            .bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));
                    bossManager.getChatCommand().remove(player);
                    return;
                }

                if (!specialAttackMesasges.containsKey(player)) {
                    specialAttackMesasges.put(player, Arrays.asList(ChatColor.translateAlternateColorCodes('&', message)));
                } else {
                    List<String> ml = new LinkedList<>();
                    for (String str : specialAttackMesasges.get(player))
                        ml.add(ChatColor.translateAlternateColorCodes('&', str));
                    ml.add(ChatColor.translateAlternateColorCodes('&', message));
                    specialAttackMesasges.remove(player);
                    specialAttackMesasges.put(player, ml);
                }
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("addMessageSpecialAttack"));

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER)) {
                int i = parseInt(event.getMessage().replace(",", "."));

                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                IBoss iBoss = bossManager.getBoss(i);
                if (iBoss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist"));
                    return;
                }

                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                bossManager.createSpawnerEditor(player, iBoss);
                bossManager.getChatCommand().remove(player);
            } else if(cc.equalsIgnoreCase(BossManager.CHAT_COMMAND_NAME)) {
                String name = event.getMessage();
                if(name.length() > 16 || name.length() < 0) {
                    player.sendMessage(BossMode.PREFIX + "The name length must between 0 and 16 characters");
                    return;
                }

                bossManager.getBossEditor(player).setName(ChatColor.translateAlternateColorCodes('&', name));
                bossManager.getChatCommand().remove(player);
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(bossManager.getBossEditor(player).isNaturalSpawn()));

            }
        }
    }

    private int parseInt(String message) {
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private double parseDouble(String message) {
        try {
            return Double.parseDouble(message);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private float parseFloat(String message) {
        try {
            return Float.parseFloat(message);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

}
