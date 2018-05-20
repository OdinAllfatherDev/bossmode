package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.Boss;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.util.BMFileManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class BossManager {

    public static final String CHAT_COMMAND_SPAWN_LOCATION = "cc_spawn_location";
    public static final String CHAT_COMMAND_EDIT_BOSS = "cc_edit_boss";
    public static final String CHAT_COMMAND_SPECIAL_ATTACK_MESSAGE = "cc_special_attack_message";
    public static final String CHAT_COMMAND_SPAWNER = "cc_spawner";
    public static final String CHAT_COMMAND_NAME = "cc_name";

    private List<IBoss> bosses;
    private List<Player> editors;
    private List<Player> editBoss;
    private BossSaver bossSaver;
    private HashMap<Player, BossEditor> playerBossEditor;
    private HashMap<Player, SpawnerEditor> playerSpawnerEditor;
    private HashMap<Player, String> chatCommand;
    private HashMap<IBoss, Integer> naturalMap;
    private List<IBoss> allSpawnedBosses;

    public BossManager() {
        this.bosses = new LinkedList<>();
        this.editors = new LinkedList<>();
        this.playerBossEditor = new HashMap<>();
        this.playerSpawnerEditor = new HashMap<>();
        this.editBoss = new LinkedList<>();
        this.chatCommand = new HashMap<>();
        this.naturalMap = new HashMap<>();
        this.allSpawnedBosses = new LinkedList<>();
    }

    public void init() {
        this.bossSaver = new BossSaver(this);
        this.bossSaver.loadBossFromList();
    }

    public void createBoss(IBoss iBoss, boolean edit) {
        this.bossSaver.saveBoss(iBoss, edit);
    }

    public boolean deleteBoss(IBoss iBoss) {
        return this.bossSaver.delete(iBoss);
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
        for (IBoss iBoss : allSpawnedBosses) {
            if (iBoss.getLivingID() == livingId)
                return iBoss;
        }
        return null;
    }

    public void spawnBoss(IBoss iBoss, Location location) {
        IBoss copy = copyBoss(iBoss);
        copy.spawnBoss(location);
        allSpawnedBosses.add(copy);
    }

    /**
     * This methode spawn the boss natural.
     * <p>
     * It get the biome and the world from the boss,
     * and iterate about the loaded chunks in the world.
     * If a chunk has the same biome as the boss, then check the chance to spawn
     * If the random value is lower as the chance value then get i the chunk surface
     * by the method: {@link BossUtil#getChunkSurface(Chunk)} and then a random location
     * from the list.
     *
     * @param bossId - the boss id
     */
    public void spawnBossNatural(int bossId) {
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Spawning boss natural...");
        IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(bossId);

        Biome biome = iBoss.getBossSettings().getBiome();

        World world = iBoss.getWorld();
        if (world == null) {
            world = Bukkit.getWorld("world");
            BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] using default world 'world'");
            BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] if it is a other world, please set the world in the boss file manual");
        }


        float chanceToSpawn = iBoss.getBossSettings().getChanceToSpawn();
        int spawnAmount = iBoss.getBossSettings().getSpawnAmount();

        Random random = new Random();
        int rand = random.nextInt(100);

        int spawned = 0;


        for (Chunk chunks : world.getLoadedChunks()) {
            if (chunks.getBlock(1, 1, 1).getBiome() == biome) {
                if (rand < chanceToSpawn) {
                    spawned++;
                    if (spawned > spawnAmount)
                        break;

                    Location spawnLocation = BossUtil.getChunkSurface(chunks)
                            .get(random.nextInt(BossUtil.getChunkSurface(chunks).size() - 1));

                    IBoss toSpawn = BossMode.getInstance().getBossManager().copyBoss(iBoss);
                    toSpawn.spawnBoss(spawnLocation);

                }
            }
        }

        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Spawned boss natural");
    }

    public void startNaturalSpawnSched() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(BossMode.getInstance(),
                () -> {

                    for (IBoss iBoss : getBosses())
                        if (iBoss.getBossSettings().isNaturalSpawn()) {
                            if (naturalMap.containsKey(iBoss)) {
                                int amount = naturalMap.get(iBoss);
                                if (amount < iBoss.getBossSettings().getSpawnAmount()) {
                                    amount++;
                                    naturalMap.replace(iBoss, amount);
                                    spawnBossNatural(iBoss.getBossID());
                                    BossMode.getLog().log(Level.INFO,
                                            "[BossMode-LOG] Spawn a boss natural [bossId='" +
                                                    iBoss.getLivingID() + "']");
                                }
                            } else {
                                naturalMap.put(iBoss, 1);
                                spawnBossNatural(iBoss.getBossID());
                                BossMode.getLog().log(Level.INFO,
                                        "[BossMode-LOG] Spawn a boss natural [bossId='"
                                                + iBoss.getLivingID() + "']");
                            }
                        }
                }, 0, 20 * 60);
    }

    public List<IBoss> getSpecificBoss(int id) {
        List<IBoss> iBosses = new ArrayList<>();

        for (IBoss b : allSpawnedBosses)
            if (b.getBossID() == id)
                iBosses.add(b);

        return iBosses;
    }

    public HashMap<IBoss, Integer> getNaturalMap() {
        return naturalMap;
    }

    public List<IBoss> getAllSpawnedBosses() {
        return allSpawnedBosses;
    }

    public HashMap<Player, String> getChatCommand() {
        return chatCommand;
    }

    public HashMap<Player, SpawnerEditor> getPlayerSpawnerEditor() {
        return playerSpawnerEditor;
    }

    public List<Player> getEditBoss() {
        return editBoss;
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
