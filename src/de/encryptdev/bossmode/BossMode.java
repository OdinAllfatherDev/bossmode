package de.encryptdev.bossmode;

import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.command.CommandBoss;
import de.encryptdev.bossmode.command.CommandStats;
import de.encryptdev.bossmode.listener.ListenerChat;
import de.encryptdev.bossmode.listener.ListenerPlayerDamageByBoss;
import de.encryptdev.bossmode.listener.ListenerSpawner;
import de.encryptdev.bossmode.listener.boss.*;
import de.encryptdev.bossmode.listener.inventory.*;
import de.encryptdev.bossmode.lang.LanguageManager;
import de.encryptdev.bossmode.ref.NBTSpawnerUtil;
import de.encryptdev.bossmode.ref.Reflection;
import de.encryptdev.bossmode.storage.FileStorage;
import de.encryptdev.bossmode.storage.SQLDBStorage;
import de.encryptdev.bossmode.storage.StorageModule;
import de.encryptdev.bossmode.storage.UserData;
import de.encryptdev.bossmode.util.BMFileManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Class
 * <p>
 * Created by EncryptDev
 */
public class BossMode extends JavaPlugin {

    private static final Logger log = Logger.getLogger(BossMode.class.getCanonicalName());
    public static final String PREFIX = "§5§lBOSSMODE §7>> §e";

    private static BossMode instance;

    private BMFileManager bossIdFile;
    private BossManager bossManager;
    private Reflection.NMSVersion nmsVersion;
    private UpdateChecker updateChecker;
    private NBTSpawnerUtil nbtSpawnerUtil;
    private InventoryStorage inventoryStorage;
    private StorageModule<UserData> storageModul;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;

        /* Check the NMS Version */
        String version = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        this.nmsVersion = Reflection.NMSVersion.getNMSVersion(version);
        if (this.nmsVersion == Reflection.NMSVersion.NONE)
            log.log(Level.INFO, "[BossMode-LOG] NMS version can not found, please contact me.");
        else
            log.log(Level.INFO, "[BossMode-LOG] NMS version found. Version: " + version);

        if (this.nmsVersion == Reflection.NMSVersion.V1_8_R1 || this.nmsVersion == Reflection.NMSVersion.V1_8_R2 || this.nmsVersion == Reflection.NMSVersion.V1_8_R3) {
            log.log(Level.INFO, "[BossMode-LOG] You play on version 1.8, the class BossBarV1_8 is used");
        }

        this.languageManager = new LanguageManager(this);
        this.updateChecker = new UpdateChecker();
        this.inventoryStorage = new InventoryStorage();
        this.nbtSpawnerUtil = new NBTSpawnerUtil();
        this.bossIdFile = new BMFileManager("bossid");
        this.bossIdFile.set("livingBossId", 0);
        this.bossManager = new BossManager();
        this.bossManager.init();
        if (getConfig().getBoolean("spawnByStartNatural"))
            this.bossManager.startNaturalSpawnSched();

        getCommand("boss").setExecutor(new CommandBoss());
        getCommand("bmstats").setExecutor(new CommandStats());

        registerListener();
        loadConfig();

        String storage = getConfig().getString("storage");
        if (storage.equalsIgnoreCase("file")) {
            storageModul = new FileStorage();
            storageModul.init();
        } else if (storage.equalsIgnoreCase("sql")) {
            if (getServer().getPluginManager().getPlugin("BMSQLAddon") == null) {
                log.log(Level.INFO, "[BossMode-LOG] SQL Addon is not installed. Please install the SQL Addon or change the save mode to 'file'");
                return;
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    if (getServer().getPluginManager().getPlugin("BMSQLAddon").isEnabled()) {
                        cancel();
                        storageModul = new SQLDBStorage();
                        storageModul.init();
                    }
                }

            }.runTaskTimer(this, 0, 10);
        } else {
            log.log(Level.WARNING, "[BossMode-LOG] Unknow storage mode. Use following storage modes: file or sql");
            log.log(Level.WARNING, "[BossMode-LOG] Use default storage: file");
            this.storageModul = new FileStorage();
            this.storageModul.init();
        }


        log.log(Level.INFO, "-------------------------------------");
        log.log(Level.INFO, "[BossMode-LOG] Developer: EncryptDev");
        log.log(Level.INFO, "[BossMode-LOG] Version: " + getDescription().getVersion());
        log.log(Level.INFO, "[BossMode-LOG] Help? Show in the config");
        log.log(Level.INFO, "[BossMode-LOG] Plugin started successfully");
        log.log(Level.INFO, "-------------------------------------");

        if (this.updateChecker.isAvailable())
            Bukkit.getConsoleSender().sendMessage("§2[BossMode-LOG] A update is available");
        else {
            log.log(Level.INFO, "[BossMode-LOG] You have the new version");
        }

    }

    private void loadConfig() {
        getConfig().addDefault("bossId", 0);
        getConfig().addDefault("livingBossId", 0);
        getConfig().addDefault("storage", "file");
        getConfig().addDefault("lang", "en_US");
        getConfig().addDefault("bossSpawnByChunkLoading", true);
        getConfig().addDefault("specialAttackTicks", 400);
        getConfig().addDefault("spawnByStartNatural", true);
        getConfig().addDefault("alert", false);
        getConfig().addDefault("alertRad", 5.0);
        getConfig().addDefault("alertMessage", "&4%BossName% spawned");
        getConfig().addDefault("bossMessage", "&7[%BossName%] &e> %Message%");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    /**
     * Register all listener
     */
    private void registerListener() {
        getServer().getPluginManager().registerEvents(updateChecker, this);
        getServer().getPluginManager().registerEvents(new ListenerDamageBoss(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryChangeEntityType(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerClosedInventory(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryFirst(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryBossSettings(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryBiome(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerChat(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerPutInventory(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerDeathBoss(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerBossSpawnChunk(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryPotionEffects(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventorySpecialAttack(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerBossHeal(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventorySpawnerSettings(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerBossSpawner(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerSpawner(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerPlayerDamageByBoss(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerBossTeleport(), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryAdvancedSettings(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryEntityTypes(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryEquipment(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryCounterType(bossManager), this);
        getServer().getPluginManager().registerEvents(new ListenerInventoryMountType(bossManager), this);
    }

    @Override
    public void onDisable() {
        /*Remove all living bosses*/
        for (World worlds : Bukkit.getWorlds())
            for (Entity entities : worlds.getEntities())
                if (entities instanceof LivingEntity)
                    if (BossUtil.isBoss((LivingEntity) entities))
                        BossUtil.getLivingBossByMetadata((LivingEntity) entities).death();

        storageModul.close();
    }

    public InventoryStorage getInventoryStorage() {
        return inventoryStorage;
    }

    public BMFileManager getBossIdFile() {
        return bossIdFile;
    }

    public String getTranslatedMessage(String code) {
        return languageManager.getTranslatedMessage(code);
    }

    public StorageModule<UserData> getStorageModul() {
        return storageModul;
    }

    public NBTSpawnerUtil getNbtSpawnerUtil() {
        return nbtSpawnerUtil;
    }

    public static Logger getLog() {
        return log;
    }

    public Reflection.NMSVersion getNmsVersion() {
        return nmsVersion;
    }

    public BossManager getBossManager() {
        return bossManager;
    }

    public static BossMode getInstance() {
        return instance;
    }

}
