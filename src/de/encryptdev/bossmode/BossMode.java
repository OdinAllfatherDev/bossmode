package de.encryptdev.bossmode;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.command.CommandBoss;
import de.encryptdev.bossmode.listener.ListenerChat;
import de.encryptdev.bossmode.listener.ListenerPlayerDamageByBoss;
import de.encryptdev.bossmode.listener.ListenerSpawner;
import de.encryptdev.bossmode.listener.boss.*;
import de.encryptdev.bossmode.listener.inventory.*;
import de.encryptdev.bossmode.ref.NBTSpawnerUtil;
import de.encryptdev.bossmode.ref.Reflection;
import de.encryptdev.bossmode.util.MessageSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

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

    private MessageSystem messageSystem;
    private BossManager bossManager;
    private Reflection.NMSVersion nmsVersion;
    private UpdateChecker updateChecker;
    private NBTSpawnerUtil nbtSpawnerUtil;
    private InventoryStorage inventoryStorage;

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

        this.messageSystem = new MessageSystem();
        this.messageSystem.loadDefaultMessages();
        this.updateChecker = new UpdateChecker();
        this.inventoryStorage = new InventoryStorage();
        this.nbtSpawnerUtil = new NBTSpawnerUtil();
        this.bossManager = new BossManager();
        this.bossManager.init();
        if (getConfig().getBoolean("spawnByStartNatural"))
            this.bossManager.startNaturalSpawnSched();

        getCommand("boss").setExecutor(new CommandBoss());
        registerListener();
        saveDefaultConfig();


        log.log(Level.INFO, "-------------------------------------");
        log.log(Level.INFO, "[BossMode-LOG] Developer: EncryptDev");
        log.log(Level.INFO, "[BossMode-LOG] Version: " + getDescription().getVersion() + "-BETA");
        log.log(Level.INFO, "[BossMode-LOG] Help? Show in the config");
        log.log(Level.INFO, "[BossMode-LOG] Plugin started successfully");
        log.log(Level.INFO, "-------------------------------------");

        if (this.updateChecker.isAvailable())
            Bukkit.getConsoleSender().sendMessage("§2[BossMode-LOG] A update is available");
        else {
            log.log(Level.INFO, "[BossMode-LOG] You have the new version");
        }

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
    }

    @Override
    public void onDisable() {
        /*Remove all living bosses*/
        for (IBoss boss : bossManager.getAllSpawnedBosses())
            boss.death();

        getConfig().set("livingBossId", 0);
        saveConfig();
    }

    public InventoryStorage getInventoryStorage() {
        return inventoryStorage;
    }

    public String getTranslatedMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.messageSystem.getMessage(path));
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
