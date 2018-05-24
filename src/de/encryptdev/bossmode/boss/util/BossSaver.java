package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.Boss;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.mount.Mount;
import de.encryptdev.bossmode.boss.mount.MountType;
import de.encryptdev.bossmode.boss.special.*;
import de.encryptdev.bossmode.util.BMFileManager;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class BossSaver {

    private BossManager bossManager;

    public BossSaver(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    public void saveBoss(IBoss boss, boolean edit) {
        BMFileManager fileManager = new BMFileManager("boss_" + boss.getBossID(), "bosses");
        fileManager.set("name", boss.getBossName());
        fileManager.set("world_name", boss.getWorld().getName());
        fileManager.set("living_id", boss.getLivingID());
        fileManager.set("max_health", boss.getBossSettings().getMaxHealth());
        fileManager.set("boss_id", boss.getBossID());
        fileManager.set("spawn_location", boss.getSpawnLocation());
        fileManager.set("boss_type", boss.getEntityType().toString());
        fileManager.set("nearby_rad", boss.getBossSettings().getNearbyRad());
        fileManager.set("speed", boss.getBossSettings().getSpeed());
        fileManager.set("chance_to_spawn", boss.getBossSettings().getChanceToSpawn());
        fileManager.set("spawn_amount", boss.getBossSettings().getSpawnAmount());
        fileManager.set("spawn_radius", boss.getBossSettings().getSpawnRadius());
        fileManager.set("chance_to_spawn_by_entity_spawn", boss.getBossSettings().getChanceToSpawnBySpawnEntity());
        fileManager.set("has_spawner", boss.hasSpawner());
        fileManager.set("biome", boss.getBossSettings().getBiome() == null ? null : boss.getBossSettings().getBiome().name());

        if (boss.getBossSettings().getEquipment() != null) {
            fileManager.set("equipment.armor.helmet", boss.getBossSettings().getEquipment().getHelmet());
            fileManager.set("equipment.armor.chestplate", boss.getBossSettings().getEquipment().getChestplate());
            fileManager.set("equipment.armor.leggings", boss.getBossSettings().getEquipment().getLeggings());
            fileManager.set("equipment.armor.boots", boss.getBossSettings().getEquipment().getBoots());
        }

        fileManager.set("equipment.hand.main", boss.getBossSettings().getEquipment().getMainHand());
        fileManager.set("equipment.hand.main_drop", boss.getBossSettings().getDropChanceWeaponMainHand());
        fileManager.set("equipment.hand.off", boss.getBossSettings().getEquipment().getOffHand());
        fileManager.set("equipment.hand.off_drop", boss.getBossSettings().getDropChanceWeaponSecondHand());
        fileManager.set("natural_drops", boss.getBossSettings().getNaturalDrops());
        fileManager.set("damage", boss.getBossSettings().getDamage());

        for (SpecialAttack sa : boss.getSpecialAttacks()) {
            if (sa instanceof TeleportSpecialAttack) {
                fileManager.set("specialattacks.teleportSpecialAttack", wrapString(sa.datas()));
            }
            fileManager.set("specialattacks." + sa.getName(), wrapString(sa.datas()));
        }

        fileManager.set("potioneffects", boss.getBossSettings().getPotionEffects());
        fileManager.set("dropped_xp", boss.getBossSettings().getDroppedXp());

        fileManager.set("near_attack", boss.getBossSettings().getNearAttackEntities());
        fileManager.set("look_at_player", boss.getBossSettings().isLookAtPlayer());
        if (boss.getMount() == null) {
            fileManager.set("mount.type", null);
            fileManager.set("mount.health", 20.0F);
        } else {
            fileManager.set("mount.type", boss.getMount().getType().toString());
            fileManager.set("mount.health", boss.getMount().getHealth());
        }

        if (!edit) {
            BossMode.getInstance().getConfig().set("bossId", BossUtil.getBossIds() + 1);
            BossMode.getInstance().saveConfig();
            this.bossManager.getBosses().add(boss);
        }

        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] New boss saved (" + boss.toString() + ")");
    }

    public boolean delete(IBoss iBoss) {
        BMFileManager fileManager = new BMFileManager("boss_" + iBoss.getBossID(), "bosses");
        boolean succesful = fileManager.deleteFile();
        if (succesful) {
            List<IBoss> copy = bossManager.getAllSpawnedBosses();

            for (IBoss living : copy)
                if (living.getBossID() == iBoss.getBossID()) {
                    living.death();
                    bossManager.getAllSpawnedBosses().remove(living);
                }

            bossManager.getBosses().remove(iBoss);

            int bossId = BossMode.getInstance().getConfig().getInt("bossId");
            BossMode.getInstance().getConfig().set("bossId", bossId - 1);
            BossMode.getInstance().saveConfig();
            return true;
        } else {
            return false;
        }

    }

    public void loadBossFromList() {
        File dir = new File(BossMode.getInstance().getDataFolder().getAbsolutePath() + "/bosses/");
        if (!dir.exists())
            dir.mkdir();
        if (dir.listFiles().length <= 0) {
            BossMode.getLog().log(Level.INFO, "[BossMode-LOG] 0 boss files loaded");
            return;
        }
        for (File files : dir.listFiles()) {
            BMFileManager fileManager = new BMFileManager(files.getName().replace(".yml", ""), "bosses");
            int bossId = fileManager.get("boss_id", int.class);
            String worldName = fileManager.get("world_name", String.class);
            double maxHealth = fileManager.get("max_health", double.class);
            ItemStack helmet = fileManager.get("equipment.armor.helmet", ItemStack.class);
            ItemStack chestplate = fileManager.get("equipment.armor.chestplate", ItemStack.class);
            ItemStack leggings = fileManager.get("equipment.armor.leggings", ItemStack.class);
            ItemStack boots = fileManager.get("equipment.armor.boots", ItemStack.class);
            ItemStack weaponMainHand = fileManager.get("equipment.hand.main", ItemStack.class);
            ItemStack weaponSecondHand = fileManager.get("equipment.hand.off", ItemStack.class);

            double weaponMainHandDropChance = fileManager.get("equipment.hand.main_drop", double.class);
            double weaponSecondHandDropChance = fileManager.get("equipment.hand.off_drop", double.class);
            double damage = fileManager.get("damage", double.class);
            double chanceToSpawn = fileManager.get("chance_to_spawn", double.class);
            double speed = fileManager.get("speed", double.class);
            double chanceToSpawnByEntitySpawn = fileManager.get("chance_to_spawn_by_entity_spawn", double.class);
            double spawnRadius = fileManager.get("spawn_radius", double.class);
            double nearbyRad = fileManager.get("nearby_rad", double.class);

            int spawnAmount = fileManager.get("spawn_amount", int.class);
            int livingId = fileManager.get("living_id", int.class);

            int droppexXp = 1;
            if (fileManager.get("dropped_xp", int.class) == null)
                fileManager.set("dropped_xp", droppexXp);
            else
                droppexXp = fileManager.get("dropped_xp", int.class);

            List<String> nearEntities = new ArrayList<>();
            if (fileManager.get("near_attack", List.class) == null)
                fileManager.set("near_attack", new ArrayList<>());
            else
                nearEntities = fileManager.get("near_attack", List.class);

            boolean lookAtPlayer = true;
            if (fileManager.get("look_at_player") == null)
                fileManager.set("look_at_player", true);
            else
                lookAtPlayer = fileManager.get("look_at_player", boolean.class);

            boolean hasSpawner = fileManager.get("has_spawner", boolean.class);

            List<ItemStack> naturalDrops = fileManager.get("natural_drops", List.class);

            String biomeName = fileManager.get("biome", String.class);

            Biome biome = null;

            if (biomeName != null)
                biome = Biome.valueOf(fileManager.get("biome", String.class));

            List<SpecialAttack> specialAttacks = new ArrayList<>();

            ConfigurationSection section = fileManager.getConfigurationSection("specialattacks");
            if (section != null) {
                for (String str : section.getKeys(false)) {

                    String[] unwrapped = unwrapString(fileManager.get("specialattacks." + str, String.class));

                    if (str.equalsIgnoreCase("teleportSpecialAttack")) {
                        specialAttacks.add(new TeleportSpecialAttack());
                    } else if (str.equalsIgnoreCase("armySpecialAttack")) {
                        specialAttacks.add(new ArmySpecialAttack(Integer.valueOf(unwrapped[0]), Double.valueOf(unwrapped[1])));
                    } else if (str.equalsIgnoreCase("stompSpecialAttack")) {
                        specialAttacks.add(new StompSpecialAttack(Double.valueOf(unwrapped[0])));
                    } else if (str.equalsIgnoreCase("messageSpecialAttack")) {

                        List<String> list = new ArrayList<>();

                        for (String str0 : unwrapped)
                            list.add(str0);

                        specialAttacks.add(new MessageSpecialAttack(list));
                    }
                }
            }
            List<PotionEffect> potionEffects = fileManager.get("potioneffects", List.class);

            String type = fileManager.get("mount.type", String.class);
            double health = fileManager.get("mount.health", double.class);

            BossEquipment bossEquipment = new BossEquipment(helmet, chestplate, leggings, boots, weaponMainHand, weaponSecondHand);

            BossSettings settings = new BossSettings(bossId, maxHealth, bossEquipment,
                    (float) weaponMainHandDropChance, (float) weaponSecondHandDropChance,
                    naturalDrops, (float) damage, (float) chanceToSpawn, speed, spawnAmount, (float) chanceToSpawnByEntitySpawn,
                    nearbyRad, spawnRadius, droppexXp, potionEffects, biome, specialAttacks, nearEntities, lookAtPlayer);

            Boss boss = new Boss(settings, livingId, fileManager.get("name", String.class),
                    fileManager.get("spawn_location", Location.class), EntityType.valueOf(fileManager.get("boss_type", String.class)));
            boss.setHasSpawner(hasSpawner);
            boss.setWorldName(worldName);

            if (type != null) {
                Mount mount = new Mount(MountType.valueOf(type), health);
                boss.setMount(mount);
            }

            this.bossManager.getBosses().add(boss);

        }
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] " + dir.listFiles().length + " boss file loaded");
    }

    private String[] unwrapString(String string) {
        return string.split(":");
    }

    private String wrapString(String... datas) {
        if (datas.length == 0)
            return "";
        if (datas.length == 1)
            return datas[0];

        String wrapped = "";

        for (String str : datas) {
            wrapped = wrapped + str + ":";
        }
        return wrapped;
    }

}
