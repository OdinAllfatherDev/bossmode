package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.special.ArmySpecialAttack;
import de.encryptdev.bossmode.boss.special.MessageSpecialAttack;
import de.encryptdev.bossmode.boss.special.StompSpecialAttack;
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

    private final HashMap<Player, List<String>> specialAttackMesasges = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (BossMode.getInstance().getBossManager().getChatCommand().containsKey(player)) {
            event.setCancelled(true);
            String cc = BossMode.getInstance().getBossManager().getChatCommand().get(player);

            if (cc.equals(BossManager.CHAT_COMMAND_NAME)) {

                if (event.getMessage().length() > 16) {
                    player.sendMessage(BossMode.PREFIX + "The max charachters for the name is 16");
                    return;
                }
                if (event.getMessage().length() <= 0) {
                    player.sendMessage(BossMode.PREFIX + "The min charachters for the name is 1");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setName(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setName"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));

                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_DAMAGE)) {

                double d = Double.parseDouble(event.getMessage().replace(",", "."));

                if (d == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setDamage(d);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDamage"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_HEALTH)) {


                double d = parseDouble(event.getMessage().replace(",", "."));

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setMaxHealth(d);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setMaxHealth"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_DROP_CHANCE_MAIN_HAND)) {


                float f = parseFloat(event.getMessage().replace(",", "."));
                if (f == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setDropChanceMainHand(f);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDropChanceMainHand"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_DROP_CHANCE_OFF_HAND)) {

                float f = parseFloat(event.getMessage().replace(",", "."));

                if (f == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setDropChanceOffHand(f);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setDropChanceOffHand"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().equipment());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWN_LOCATION) && event.getMessage().equalsIgnoreCase("#finish")) {
                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setSpawnLocation(player.getLocation());
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnLocation"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_CHANCE_TO_SPAWN)) {
                float f = parseFloat(event.getMessage().replace(",", "."));

                if (f == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                if (f > 100)
                    f = 100;
                if (f < 1)
                    f = 1;

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setChanceToSpawn(f);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpawnChance"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance()
                        .getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_SPEED)) {
                double d = parseDouble(event.getMessage().replace(",", "."));

                if (d == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                if (d <= 0) {
                    player.sendMessage(BossMode.PREFIX + "Value can not lower as 0");
                    return;
                }
                BossMode.getInstance().getBossManager().getBossEditor(player).setSpeed(d);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setSpeed"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWN_AMOUNT)) {
                int i = parseInt(event.getMessage().replace(",", "."));

                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if (i < 1) {
                    player.sendMessage(BossMode.PREFIX + "Value can not lower as 1");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player).setSpawnAmount(i);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("setMaxSpawnAmount"));
                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_EDIT_BOSS)) {
                int i = parseInt(event.getMessage().replace(",", "."));

                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(i);
                if (iBoss == null) {
                    player.sendMessage(BossMode.PREFIX + "The boss with the id '" + i + "' doesn't exist");
                    return;
                }

                player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(iBoss.getBossSettings().isNaturalSpawn()));
                BossMode.getInstance().getBossManager().createBossEditor(player, iBoss);
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE)) {
                String message = event.getMessage();
                if (message.equalsIgnoreCase("#finish") && specialAttackMesasges.containsKey(player)) {

                    List<String> messages = specialAttackMesasges.get(player);
                    BossMode.getInstance().getBossManager().getBossEditor(player)
                            .addSpecialAttack(new MessageSpecialAttack(messages));
                    specialAttackMesasges.remove(player);
                    player.openInventory(BossMode.getInstance().getInventoryStorage()
                            .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    BossMode.getInstance().getBossManager().getChatCommand().remove(player);
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

            } else if (cc.equals(BossManager.CHAT_COMMAND_DROP_CHANCE_RANDOM_SPAWN)) {
                float f = parseFloat(event.getMessage());

                if (f == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                if (f > 100)
                    f = 100;
                if (f < 1)
                    f = 1;

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setChanceToSpawnByEntitySpawn(f);
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);


            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWN_RADIUS)) {
                double d = parseDouble(event.getMessage());

                if (d == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                if (d > 100)
                    d = 100;
                if (d < 1)
                    d = 1;

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setSpawnRadius(d);
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_NEARBY_RADIUS)) {
                double d = parseDouble(event.getMessage());

                if (d == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                if (d > 100)
                    d = 100;
                if (d < 1)
                    d = 1;

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .setNearbyRad(d);
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_SPECIAL_ATTACK_STRENGTH)) {
                double d = parseDouble(event.getMessage());

                if (d == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .addSpecialAttack(new StompSpecialAttack(d));
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPECIAL_ATTACK_ARMY)) {
                int i = parseInt(event.getMessage());
                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                BossMode.getInstance().getBossManager().getBossEditor(player)
                        .addSpecialAttack(new ArmySpecialAttack(i, 90));
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER)) {
                int i = parseInt(event.getMessage().replace(",", "."));

                if (i == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(i);
                if (iBoss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist"));
                    return;
                }

                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().createSpawnerEditor(player, iBoss);
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_DELAY)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setDelay(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_MAX_DELAY)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setMaxSpawnDelay(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_SPAWN_RANGE)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setSpawnRange(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_MIN_DELAY)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setMinSpawnDelay(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_REQUIRED_PLAYERS_RANGE)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setRequiredPlayerRange(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_SPAWN_AMOUNT)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setSpawnCount(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWNER_REQUIRED_PLAYERS_RANGE)) {
                int r = parseInt(event.getMessage());

                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getSpawnerEditor(player).setSpawnRange(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage().spawnerInventory());
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);

            } else if (cc.equals(BossManager.CHAT_COMMAND_DROPPED_XP)) {
                int r = parseInt(event.getMessage());
                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player).setDroppedXp(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
            } else if (cc.equals(BossManager.CHAT_COMMAND_SPAWN_CHANCE_RANDOM_SPAWN)) {
                int r = parseInt(event.getMessage());
                if (r > 100)
                    r = 100;
                if (r < 0)
                    r = 0;
                if (r == -1) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    event.setCancelled(true);
                    return;
                }

                BossMode.getInstance().getBossManager().getBossEditor(player).setChanceToSpawnByEntitySpawn(r);
                player.openInventory(BossMode.getInstance().getInventoryStorage()
                        .bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                BossMode.getInstance().getBossManager().getChatCommand().remove(player);
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
