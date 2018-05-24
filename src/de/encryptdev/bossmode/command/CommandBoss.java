package de.encryptdev.bossmode.command;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import de.encryptdev.bossmode.boss.util.BossEquipment;
import de.encryptdev.bossmode.boss.util.BossSettings;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class CommandBoss extends ACommand {

    @Override
    public void playerCommand(Player player, String[] args) {
        if (!player.hasPermission("bossmode.admin") || !player.hasPermission("bossmode.*") || !player.isOp())
            return;

        if (args.length == 0) {
            player.sendMessage("                           §4§lBOSSMODE       ");
            player.sendMessage("                               §6§lv" + BossMode.getInstance().getDescription().getVersion() + "-BETA");
            player.sendMessage("                       §5§lby EncryptDev   ");
            player.sendMessage(BossMode.PREFIX + "/boss - show all commands");
            player.sendMessage(BossMode.PREFIX + "/boss editor - change in the editor mode");
            player.sendMessage(BossMode.PREFIX + "/boss list - show all bosses");
            player.sendMessage(BossMode.PREFIX + "/boss kill <id> - kill a specific boss");
            player.sendMessage(BossMode.PREFIX + "/boss killall - kill all living bosses");
            player.sendMessage(BossMode.PREFIX + "/boss info <id> - get all information about the boss");
            player.sendMessage(BossMode.PREFIX + "/boss spawn <id> - spawn a specific boss");
            player.sendMessage(BossMode.PREFIX + "/boss spawner <id> - get a spawner from the boss, if the spawner exist");
            player.sendMessage(BossMode.PREFIX + "/boss delete <id> - delete the boss");
            player.sendMessage(BossMode.PREFIX + "I hope you have fun! ;)");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("editor")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player)) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("alreadyInEditorMode"));
                    return;
                }
                player.openInventory(BossMode.getInstance().getInventoryStorage().newOrEditBoss());
            } else if (args[0].equalsIgnoreCase("list")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                List<IBoss> allBosses = BossMode.getInstance().getBossManager().getBosses();

                player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                for (int i = 0; i < allBosses.size(); i++)
                    player.sendMessage(BossMode.PREFIX + "§aName: " + allBosses.get(i).getBossName() + " §a| ID: §4"
                            + allBosses.get(i).getBossID()
                            + " §a| Spawner: §4" + allBosses.get(i).hasSpawner());

                player.sendMessage("§7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            } else if (args[0].equalsIgnoreCase("killall")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int i = 0;

                List<IBoss> copy = BossMode.getInstance().getBossManager().getAllSpawnedBosses();

                for (IBoss iBoss : copy) {
                    int livingId = iBoss.getLivingID();
                    for (Entity entity : player.getWorld().getEntities())
                        if (entity.hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID))
                            if (entity.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt() == livingId) {
                                iBoss.death();
                                entity.remove();
                                i++;
                            }
                }

                player.sendMessage(BossMode.getInstance().getTranslatedMessage("killAllBosses").replace("%Amount%", String.valueOf(i)));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("spawn")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int id = -1;
                try {
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException nfe) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if(id == -1) {
                    player.sendMessage(BossMode.PREFIX + "ID is -1, please contact me");
                    return;
                }


                List<Player> all = new LinkedList<>();
                for (Player a : Bukkit.getOnlinePlayers()) {
                    all.add(a);
                }

                IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(id);
                if (iBoss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist").replace("%id%", String.valueOf(id)));
                    return;
                }

                BossMode.getInstance().getBossManager().spawnBoss(BossMode.getInstance().getBossManager().getBoss(id), null);

            } else if (args[0].equalsIgnoreCase("spawner")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int id = -1;
                try {
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException nfe) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if(id == -1) {
                    player.sendMessage(BossMode.PREFIX + "ID is -1, please contact me");
                    return;
                }

                IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(id);
                if (iBoss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist").replace("%id%", String.valueOf(id)));
                    return;
                }
                if (!iBoss.hasSpawner()) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("spawnerItemNull"));
                    return;
                }
                ItemStack spawner = BossMode.getInstance().getBossManager().getSpawner(iBoss.getBossID());

                player.getInventory().addItem(spawner);
                player.sendMessage(BossMode.getInstance().getTranslatedMessage("getSpawner"));

            } else if (args[0].equalsIgnoreCase("kill")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int id = -1;
                try {
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException nfe) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if(id == -1) {
                    player.sendMessage(BossMode.PREFIX + "ID is -1, please contact me");
                    return;
                }
                if (BossMode.getInstance().getBossManager().getBoss(id) == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist").replace("%id%", String.valueOf(id)));
                    return;
                }

                int i = 0;

                for (IBoss iBoss : BossMode.getInstance().getBossManager().getSpecificBoss(id)) {
                    int livingId = iBoss.getLivingID();
                    for (Entity entity : player.getWorld().getEntities())
                        if (entity.hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID))
                            if (entity.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt() == livingId) {
                                iBoss.death();
                                entity.remove();
                                i++;
                            }
                }

                player.sendMessage(BossMode.getInstance().getTranslatedMessage("killBoss").replace("%Amount%", String.valueOf(i)));

            } else if (args[0].equalsIgnoreCase("info")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int id = -1;
                try {
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException nfe) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if(id == -1) {
                    player.sendMessage(BossMode.PREFIX + "ID is -1, please contact me");
                    return;
                }

                IBoss boss = BossMode.getInstance().getBossManager().getBoss(id);

                if (boss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist").replace("%id%", String.valueOf(id)));
                    return;
                }

                player.sendMessage(BossMode.PREFIX + "Information about: " + boss.getBossName());
                player.sendMessage(BossMode.PREFIX + "Name: §a" + boss.getBossName());
                player.sendMessage(BossMode.PREFIX + "ID: §a" + boss.getBossID());
                player.sendMessage(BossMode.PREFIX + "LivingID: §a" + boss.getLivingID());
                player.sendMessage(BossMode.PREFIX + "Spawner: §a" + boss.hasSpawner());
                player.sendMessage(BossMode.PREFIX + "SpawnLocation: §a" + (boss.getSpawnLocation() == null ? "/" : "X: " + boss.getSpawnLocation().getX() + ", Y: " + boss.getSpawnLocation()
                        .getY() + ", Z: " + boss.getSpawnLocation().getZ()));
                player.sendMessage(BossMode.PREFIX + "NaturalSpawn: §a" + (boss.getBossSettings().getBiome() == null ? "false" : "true"));
                player.sendMessage(BossMode.PREFIX + "WalkSpeed: §a" + boss.getBossSettings().getSpeed());
                player.sendMessage(BossMode.PREFIX + "AttackDamage: §a" + boss.getBossSettings().getDamage());
                player.sendMessage(BossMode.PREFIX + "Health: §a" + boss.getBossSettings().getMaxHealth());
                player.sendMessage(BossMode.PREFIX + "SpawnAmount: §a" + boss.getBossSettings().getSpawnAmount());
                player.sendMessage(BossMode.PREFIX + "NearPlayerRange: §a" + boss.getBossSettings().getNearbyRad());
                player.sendMessage(BossMode.PREFIX + "PotionEffects: §a" + (boss.getBossSettings().getPotionEffects().isEmpty() ? "/" : ""));
                List<PotionEffect> potionEffects = boss.getBossSettings().getPotionEffects();
                if (!potionEffects.isEmpty())
                    for (PotionEffect pe : potionEffects)
                        player.sendMessage(BossMode.PREFIX + " -> §a" + pe.getType().getName() + " §7| Duration: §a" + pe.getDuration() + " §7| Amplifier: §a" + pe.getAmplifier());

                player.sendMessage(BossMode.PREFIX + "SpecialAttacks: §a" + (boss.getBossSettings().getSpecialAttacks().isEmpty() ? "/" : ""));
                List<SpecialAttack> specialAttacks = boss.getBossSettings().getSpecialAttacks();
                if (!specialAttacks.isEmpty())
                    for (SpecialAttack sa : specialAttacks)
                        player.sendMessage(BossMode.PREFIX + " -> §a" + sa.getName());

                player.sendMessage(BossMode.PREFIX + "NearAttackEntities: §a" + (boss.getBossSettings().getNearAttackEntities().isEmpty() ? "/" : ""));
                List<String> nearEntities = boss.getBossSettings().getNearAttackEntities();
                if (!nearEntities.isEmpty())
                    for (String str : nearEntities)
                        player.sendMessage(BossMode.PREFIX + " -> §a" + str.replace(".", ",").split(",")[4].replace("Entity", ""));

                BossEquipment equipment = boss.getBossSettings().getEquipment();

                player.sendMessage(BossMode.PREFIX + "MainHand: §a" + (equipment.getMainHand() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getMainHand().getType())));
                player.sendMessage(BossMode.PREFIX + "DropChance: §a" + boss.getBossSettings().getDropChanceWeaponMainHand());
                player.sendMessage(BossMode.PREFIX + "OffHand: §a" + (equipment.getOffHand() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getOffHand().getType())));
                player.sendMessage(BossMode.PREFIX + "DropChance: §a" + boss.getBossSettings().getDropChanceWeaponSecondHand());
                player.sendMessage(BossMode.PREFIX + "Helmet: §a" + (equipment.getHelmet() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getHelmet().getType())));
                player.sendMessage(BossMode.PREFIX + "Chestplate: §a" + (equipment.getChestplate() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getChestplate().getType())));
                player.sendMessage(BossMode.PREFIX + "Leggings: §a" + (equipment.getLeggings() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getLeggings().getType())));
                player.sendMessage(BossMode.PREFIX + "Boots: §a" + (equipment.getBoots() == null ? "/" : BossUtil.makeEnumNameNormal(equipment.getBoots().getType())));


            } else if (args[0].equalsIgnoreCase("delete")) {
                if (BossMode.getInstance().getBossManager().getEditors().contains(player))
                    BossMode.getInstance().getBossManager().getEditors().remove(player);
                int id = -1;
                try {
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException nfe) {
                    player.sendMessage(BossMode.PREFIX + "It must be a number");
                    return;
                }
                if(id == -1) {
                    player.sendMessage(BossMode.PREFIX + "ID is -1, please contact me");
                    return;
                }

                IBoss boss = BossMode.getInstance().getBossManager().getBoss(id);

                if (boss == null) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("bossNotExist").replace("%id%", String.valueOf(id)));
                    return;
                }

                boolean result = BossMode.getInstance().getBossManager().deleteBoss(boss);
                if (result) {
                    player.sendMessage(BossMode.getInstance().getTranslatedMessage("deleteBossSuccessful"));
                } else {
                    player.sendMessage(BossMode.PREFIX + "Boss file not deleted");
                }

            }
        }

    }

    @Override
    public void consoleCommand(ConsoleCommandSender ccs, String[] args) {

    }
}
