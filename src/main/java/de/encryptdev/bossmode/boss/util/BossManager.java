package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.Boss;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.spawn.NaturalSpawnManager;
import de.encryptdev.bossmode.boss.special.*;
import de.encryptdev.bossmode.util.BMFileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class BossManager {

    public static final String CHAT_COMMAND_NAME = "cc_name";
    public static final String CHAT_COMMAND_HEALTH = "cc_health";
    public static final String CHAT_COMMAND_DAMAGE = "cc_damage";
    public static final String CHAT_COMMAND_DROP_CHANCE_MAIN_HAND = "cc_drop_chance_main_hand";
    public static final String CHAT_COMMAND_DROP_CHANCE_OFF_HAND = "cc_drop_chance_off_hand";
    public static final String CHAT_COMMAND_SPAWN_LOCATION = "cc_spawn_location";
    public static final String CHAT_COMMAND_CHANCE_TO_SPAWN = "cc_chance_to_spawn";
    public static final String CHAT_COMMAND_SPEED = "cc_speed";
    public static final String CHAT_COMMAND_SPAWN_AMOUNT = "cc_spawn_amount";
    public static final String CHAT_COMMAND_EDIT_BOSS = "cc_edit_boss";
    public static final String CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE = "cc_special_attack_message";
    public static final String CHAT_COMMAND_DROP_CHANCE_RANDOM_SPAWN = "cc_drop_chance_random_spawn";
    public static final String CHAT_COMMAND_NEARBY_RADIUS = "cc_nearby_rad";
    public static final String CHAT_COMMAND_SPAWN_RADIUS = "cc_spawn_radius";
    public static final String CHAT_COMMAND_SPECIAL_ATTACK_STRENGTH = "cc_special_attack_strength";
    public static final String CHAT_COMMAND_SPAWNER = "cc_spawner";
    public static final String CHAT_COMMAND_SPECIAL_ATTACK_ARMY = "cc_special_attack_army";
    public static final String CHAT_COMMAND_SPAWNER_DELAY = "cc_spawner_delay";
    public static final String CHAT_COMMAND_SPAWNER_MIN_DELAY = "cc_spawner_min_delay";
    public static final String CHAT_COMMAND_SPAWNER_MAX_DELAY = "cc_spawner_max_delay";
    public static final String CHAT_COMMAND_SPAWNER_REQUIRED_PLAYERS_RANGE = "cc_spawner_required_players_range";
    public static final String CHAT_COMMAND_SPAWNER_SPAWN_AMOUNT = "cc_spawner_spawn_amount";
    public static final String CHAT_COMMAND_SPAWNER_SPAWN_RANGE = "cc_spawner_spawn_range";
    public static final String CHAT_COMMAND_DROPPED_XP = "cc_dropped_xp";
    public static final String CHAT_COMMAND_SPAWN_CHANCE_RANDOM_SPAWN = "cc_spawn_chance_random_spawn";

    private List<IBoss> bosses;
    private List<Player> editors;
    private List<Player> editBoss;
    private HashMap<Player, BossEditor> playerBossEditor;
    private HashMap<Player, SpawnerEditor> playerSpawnerEditor;
    private HashMap<Player, String> chatCommand;
    private NaturalSpawnManager naturalSpawnManager;

    public BossManager() {
        this.bosses = new LinkedList<>();
        this.editors = new LinkedList<>();
        this.playerBossEditor = new HashMap<>();
        this.playerSpawnerEditor = new HashMap<>();
        this.editBoss = new LinkedList<>();
        this.chatCommand = new HashMap<>();
        this.naturalSpawnManager = new NaturalSpawnManager();
    }

    public void createBossEditor(Player player, EntityType type) {
        BossEditor editor = new BossEditor(type);
        playerBossEditor.put(player, editor);
    }

    public void createSpawnerEditor(Player player, IBoss iBoss) {
        SpawnerEditor editor = new SpawnerEditor(iBoss);
        playerSpawnerEditor.put(player, editor);
    }

    public void createBossEditor(Player player, IBoss boss) {
        BossEditor editor = new BossEditor(boss);
        playerBossEditor.put(player, editor);
    }

    public SpawnerEditor getSpawnerEditor(Player player) {
        return playerSpawnerEditor.get(player);
    }

    public BossEditor getBossEditor(Player player) {
        return playerBossEditor.get(player);
    }

    public void createBoss(IBoss boss, boolean edit) {

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

        if (boss.getBossSettings().getArmor() != null) {
            fileManager.set("equipment.armor.helmet", boss.getBossSettings().getArmor()[0]);
            fileManager.set("equipment.armor.chestplate", boss.getBossSettings().getArmor()[1]);
            fileManager.set("equipment.armor.leggings", boss.getBossSettings().getArmor()[2]);
            fileManager.set("equipment.armor.boots", boss.getBossSettings().getArmor()[3]);
        }

        fileManager.set("equipment.hand.main", boss.getBossSettings().getWeaponMainHand());
        fileManager.set("equipment.hand.main_drop", boss.getBossSettings().getDropChanceWeaponMainHand());
        fileManager.set("equipment.hand.off", boss.getBossSettings().getWeaponSecondHand());
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

        if (!edit) {
            BossMode.getInstance().getConfig().set("bossId", BossUtil.getBossIds() + 1);
            BossMode.getInstance().saveConfig();
            this.bosses.add(boss);
        }

        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] New boss saved (" + boss.toString() + ")");

    }

    public ItemStack createSpawner(SpawnerEditor editor) {
        BMFileManager fileManager = new BMFileManager("spawner_list");

        ItemStack itemStack = editor.build();

        fileManager.set("spawner." + getBoss(Integer.valueOf(itemStack.getItemMeta().getLore().get(0).split(":")[1].trim())).getBossID() + ".item", itemStack);
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] New boss spawner saved (" + itemStack.getItemMeta().getLore().get(0).split(":")[1].trim() + ")");


        int id = Integer.parseInt(itemStack.getItemMeta().getLore().get(0).split(":")[1].trim());
        IBoss iBoss = getBoss(id);
        iBoss.setHasSpawner(true);
        BMFileManager bossFile = new BMFileManager("boss_" + id, "bosses");
        bossFile.set("has_spawner", true);

        return itemStack;
    }

    public ItemStack getSpawner(int id) {
        BMFileManager fileManager = new BMFileManager("spawner_list");
        return fileManager.get("spawner." + id + ".item", ItemStack.class);
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

    public boolean delete(IBoss iBoss) {
        File file = new File(BossMode.getInstance().getDataFolder().getAbsolutePath() + "/bosses/" + iBoss.getBossID());
        if(!file.exists())
            return false;

        List<IBoss> copy = naturalSpawnManager.getNaturalSpawned();

        for(IBoss living : copy)
            if(living.getBossID() == iBoss.getBossID()) {
                living.death();
                naturalSpawnManager.getNaturalSpawned().remove(living);
            }

        bosses.remove(iBoss);
        file.delete();

        int bossId = BossMode.getInstance().getConfig().getInt("bossId");
        BossMode.getInstance().getConfig().set("bossId", bossId - 1);
        BossMode.getInstance().saveConfig();

        return true;
    }

    private String[] unwrapString(String string) {
        return string.split(":");
    }

    public IBoss copyBoss(IBoss iBoss) {
        int livingId = BossUtil.getLivingBossIds() + 1;

        BossMode.getInstance().getConfig().set("livingBossId", livingId);
        BossMode.getInstance().saveConfig();

        return new Boss(iBoss.getBossSettings(), livingId, iBoss.getBossName(),
                iBoss.getSpawnLocation(), iBoss.getEntityType());
    }

    public IBoss getBoss(int bossId) {
        for (IBoss boss : bosses)
            if (boss.getBossID() == bossId)
                return boss;
        return null;
    }

    public IBoss getSpawnedBoss(int livingId) {
        for (IBoss spawned : naturalSpawnManager.getNaturalSpawned())
            if (spawned.getLivingID() == livingId)
                return spawned;
        return null;
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
            ItemStack[] armor = new ItemStack[4];
            ItemStack helmet = fileManager.get("equipment.armor.helmet", ItemStack.class);
            ItemStack chestplate = fileManager.get("equipment.armor.chestplate", ItemStack.class);
            ItemStack leggings = fileManager.get("equipment.armor.leggings", ItemStack.class);
            ItemStack boots = fileManager.get("equipment.armor.boots", ItemStack.class);
            armor[0] = helmet;
            armor[1] = chestplate;
            armor[2] = leggings;
            armor[3] = boots;

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

            BossSettings settings = new BossSettings(bossId, maxHealth, armor, weaponMainHand, weaponSecondHand,
                    (float) weaponMainHandDropChance, (float) weaponSecondHandDropChance,
                    naturalDrops, (float) damage, (float) chanceToSpawn, speed, spawnAmount, (float) chanceToSpawnByEntitySpawn,
                    nearbyRad, spawnRadius, droppexXp, potionEffects, biome, specialAttacks, nearEntities, lookAtPlayer);

            Boss boss = new Boss(settings, livingId, fileManager.get("name", String.class),
                    fileManager.get("spawn_location", Location.class), EntityType.valueOf(fileManager.get("boss_type", String.class)));
            boss.setHasSpawner(hasSpawner);
            boss.setWorldName(worldName);

            this.bosses.add(boss);

        }
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] " + dir.listFiles().length + " boss file loaded");
    }

    public void startNaturalSpawnSched() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(BossMode.getInstance(),
                () -> {

                    for (IBoss iBoss : getBosses())
                        if (iBoss.getBossSettings().isNaturalSpawn()) {
                            if (naturalSpawnManager.getNaturalMap().containsKey(iBoss)) {
                                int amount = naturalSpawnManager.getNaturalMap().get(iBoss);
                                if (amount < iBoss.getBossSettings().getSpawnAmount()) {
                                    amount++;
                                    naturalSpawnManager.getNaturalMap().replace(iBoss, amount);
                                    spawnBossNatural(iBoss);
                                    BossMode.getLog().log(Level.INFO,
                                            "[BossMode-LOG] Spawn a boss natural [bossId='" +
                                                    iBoss.getLivingID() + "']");
                                }
                            } else {
                                naturalSpawnManager.getNaturalMap().put(iBoss, 1);
                                spawnBossNatural(iBoss);
                                BossMode.getLog().log(Level.INFO,
                                        "[BossMode-LOG] Spawn a boss natural [bossId='"
                                                + iBoss.getLivingID() + "']");
                            }
                        }
                }, 0, 20 * 60);
    }

    public HashMap<Player, SpawnerEditor> getPlayerSpawnerEditor() {
        return playerSpawnerEditor;
    }

    public void spawnBossNatural(IBoss iBoss) {
        naturalSpawnManager.spawnBossNatural(iBoss.getBossID());
    }

    public NaturalSpawnManager getNaturalSpawnManager() {
        return naturalSpawnManager;
    }

    public List<Player> getEditBoss() {
        return editBoss;
    }

    public HashMap<Player, String> getChatCommand() {
        return chatCommand;
    }

    public HashMap<Player, BossEditor> getPlayerBossEditor() {
        return playerBossEditor;
    }

    public List<Player> getEditors() {
        return editors;
    }

    public List<IBoss> getBosses() {
        return bosses;
    }
}
