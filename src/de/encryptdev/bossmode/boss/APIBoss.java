package de.encryptdev.bossmode.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.ai.BossAISpecialAttack;
import de.encryptdev.bossmode.boss.event.BossDamageEvent;
import de.encryptdev.bossmode.boss.event.BossDeathEvent;
import de.encryptdev.bossmode.boss.event.BossSpawnEvent;
import de.encryptdev.bossmode.boss.path.BossPathfinderEdited;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import de.encryptdev.bossmode.boss.util.BossSettings;
import de.encryptdev.bossmode.ref.Reflection;
import de.encryptdev.bossmode.util.BossBarV1_8;
import de.encryptdev.bossmode.util.ItemCreator;
import de.encryptdev.bossmode.util.exception.BossAlreadySpawn;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * Created by EncryptDev
 */
public abstract class APIBoss implements IBoss {

    private static final Map<IBoss, BukkitTask> task = new HashMap<>();
    private static final Map<IBoss, BukkitTask> task0 = new HashMap<>();

    private String worldName;
    private int livingId;
    private BossBar bossBar;
    private BossPathfinderEdited bossPathfinderEdited;
    private BossSettings bossSettings;
    private LivingEntity livingBossEntity;
    private String name;
    private Location spawnLocation;
    private EntityType type;
    private Biome biome;
    private BossAISpecialAttack specialAttack;
    private List<SpecialAttack> specialAttacks;
    private boolean hasSpawner;
    private boolean is1_8;
    private BossBarV1_8 bossBar1_8;
    private List<Player> nearbyPlayers;

    public APIBoss(BossSettings bossSettings, int livingId, String name, Location spawnLocation, EntityType type) {
        this.bossSettings = bossSettings;
        this.livingId = livingId;
        this.name = name;
        this.is1_8 = BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R1 || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R2 ||
                BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R3;
        this.bossBar1_8 = is1_8 ? new BossBarV1_8(BossMode.getInstance(), name) : null;
        this.spawnLocation = spawnLocation;
        this.type = type;
        this.biome = bossSettings.getBiome();
        this.specialAttacks = bossSettings.getSpecialAttacks();
        this.hasSpawner = false;
        this.worldName = spawnLocation != null ? spawnLocation.getWorld().getName() : "world";
        this.nearbyPlayers = new LinkedList<>();
    }

    private void checkPlayers() {
        task.put(this, Bukkit.getScheduler().runTaskTimerAsynchronously(BossMode.getInstance(), () -> {
            for (Entity ent : livingBossEntity.getNearbyEntities(getBossSettings().getNearbyRad(), getBossSettings().getNearbyRad(), getBossSettings().getNearbyRad())) {
                if (ent != null)
                    if (ent instanceof Player) {
                        if (nearbyPlayers.contains((Player) ent))
                            continue;
                        nearbyPlayers.add((Player) ent);
                        if (is1_8)
                            bossBar1_8.addPlayer((Player) ent);
                        else
                            bossBar.addPlayer((Player) ent);
                    }
            }

            List<Player> copy = nearbyPlayers;

            if (!copy.isEmpty()) {
                for (Player player : copy) {
                    if ((player.getLocation().distanceSquared(livingBossEntity.getLocation()) > getBossSettings().getNearbyRad())) {
                        nearbyPlayers.remove(player);
                        if (!is1_8)
                            bossBar.removePlayer(player);
                        else
                            bossBar1_8.removePlayer(player);
                    }
                }
            }

        }, 0, 20));
    }

    private void createBossBar(List<Player> players) {
        if (is1_8) {
            players.forEach(player -> bossBar1_8.setProgress(300));
        } else {
            bossBar = Bukkit.createBossBar(name, BarColor.RED, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY);
            bossBar.setProgress(1);
        }
    }

    @Override
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public int getLivingID() {
        return livingId;
    }

    private void initAI() {
        for (SpecialAttack sa : specialAttacks)
            sa.setIBoss(this);

        if (getSpecialAttacks() != null)
            if (getSpecialAttacks().size() > 0)
                for (int i = 0; i < specialAttacks.size(); i++)
                    for (int j = 0; j < getSpecialAttacks().size(); j++)
                        if (!specialAttacks.get(i).getClass().equals(getSpecialAttacks().get(j).getClass())) {
                            SpecialAttack sa = getSpecialAttacks().get(j);
                            sa.setIBoss(this);
                            specialAttacks.add(sa);
                        }
        this.specialAttack = new BossAISpecialAttack(this, bossSettings.getSpecialAttackTicks());
    }

    @Override
    public void setHasSpawner(boolean hasSpawner) {
        this.hasSpawner = hasSpawner;
    }

    @Override
    public boolean hasSpawner() {
        return hasSpawner;
    }

    @Override
    public void spawnBoss(Location location) {
        if (location != null)
            spawnLocation = location;

        if (spawnLocation == null && biome == null)
            throw new RuntimeException("Spawnlocation and biome is null");
        if (spawnLocation == null)
            throw new RuntimeException("Spawnlocation for the boss '" + getBossID() + "' is not set");

        this.livingBossEntity = this.getBossSettings().createLivingEntity(type, spawnLocation, livingId);

        this.checkPlayers();

        this.worldName = livingBossEntity.getWorld().getName();
        this.bossPathfinderEdited = new BossPathfinderEdited(this);

        this.livingBossEntity.setCustomName(name);
        createBossBar(nearbyPlayers);
        this.livingBossEntity.setCustomNameVisible(true);
        this.initAI();
        this.executeSpecialAttack();

        if (BossMode.getInstance().getConfig().getBoolean("alert")) {
            int rad = BossMode.getInstance().getConfig().getInt("alertRad");

            String message = ChatColor.translateAlternateColorCodes('&',
                    BossMode.getInstance().getConfig().getString("alertMessage")
                            .replace("%BossName%", ChatColor.translateAlternateColorCodes('&', name)));

            for (Entity entity : spawnLocation.getWorld().getNearbyEntities(spawnLocation, rad * 2, rad * 2, rad * 2)) {
                if (entity != null)
                    if (entity instanceof Player) {
                        entity.sendMessage(message);
                    }
            }
        }
        BossMode.getInstance().getBossManager().getNaturalSpawnManager().getNaturalSpawned().add(this);

        Bukkit.getPluginManager().callEvent(new BossSpawnEvent(this, spawnLocation));
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBossPathfinderEdit(BossPathfinderEdited bossPathfinderEdited) {
        if (this.livingBossEntity != null)
            throw new BossAlreadySpawn(this.getBossID());
        this.bossPathfinderEdited = bossPathfinderEdited;
    }

    private void executeSpecialAttack() {
        task0.put(this, Bukkit.getScheduler().runTaskTimer(BossMode.getInstance(), () -> {

            if (!nearbyPlayers.isEmpty()) {
                Player[] players = new Player[nearbyPlayers.size()];
                if (players.length > 0)
                    for (int i = 0; i < players.length; i++)
                        players[i] = nearbyPlayers.get(i);
                specialAttack.execute(players);
            }

        }, 0, this.specialAttack.getTicks()));
    }

    @Override
    public World getWorld() {
        return livingBossEntity != null ? livingBossEntity.getWorld() : Bukkit.getWorld(worldName);
    }

    @Override
    public void setBossSettings(BossSettings bossSettings) {
        this.bossSettings = bossSettings;
    }

    @Override
    public org.bukkit.boss.BossBar getBossBar() {
        return bossBar;
    }

    @Override
    public String getBossName() {
        return name;
    }

    @Override
    public int getBossID() {
        return bossSettings.getBossId();
    }

    @Override
    public List<SpecialAttack> getSpecialAttacks() {
        return specialAttacks;
    }

    @Override
    public void damageEntity(double damage) {
        this.livingBossEntity.damage(damage);

        double progress = (livingBossEntity.getHealth() - damage) / bossSettings.getMaxHealth();

        double progress1_8 = (livingBossEntity.getHealth() * 100) / livingBossEntity.getMaxHealth() * 3;

        if (!is1_8)
            if (progress <= 0)
                this.bossBar.setProgress(0);
            else
                this.bossBar.setProgress(progress);
        else if (progress <= 0)
            nearbyPlayers.forEach(player -> bossBar1_8.setProgress(progress1_8));
        else
            nearbyPlayers.forEach(player -> bossBar1_8.setProgress(progress1_8));

        Bukkit.getPluginManager().callEvent(new BossDamageEvent(this, damage, false));
    }

    @Override
    public void death() {
        Bukkit.getPluginManager().callEvent(new BossDeathEvent(this, livingBossEntity != null ? livingBossEntity.getLocation() : null));

        if (!is1_8) {
            if (this.bossBar != null) {
                for (Player pl : this.bossBar.getPlayers())
                    this.bossBar.removePlayer(pl);
                this.bossBar.removeAll();
            }
        } else {
            nearbyPlayers.forEach(player -> bossBar1_8.clear());
        }

        if (this.livingBossEntity != null && bossSettings.getNaturalDrops() != null)
            if (!this.bossSettings.getNaturalDrops().isEmpty())
                for (ItemStack item : bossSettings.getNaturalDrops())
                    this.livingBossEntity.getWorld().dropItemNaturally(livingBossEntity.getLocation(), item);
        if (task.containsKey(this)) {
            task.get(this).cancel();
            task.remove(this);
        }
        if (task0.containsKey(this)) {
            task0.get(this).cancel();
            task0.remove(this);
        }
        BossMode.getInstance().getBossManager().getNaturalSpawnManager().getNaturalSpawned().remove(this);
        if (this.livingBossEntity != null)
            this.livingBossEntity.remove();
    }

    @Override
    public void heal(double amount) {
        if (this.livingBossEntity.getHealth() + amount > this.bossSettings.getMaxHealth())
            this.livingBossEntity.setHealth(this.bossSettings.getMaxHealth());
        else
            this.livingBossEntity.setHealth(this.livingBossEntity.getHealth() + amount);

        double progress = (livingBossEntity.getHealth() + amount) / this.bossSettings.getMaxHealth();

        double progress1_8 = (livingBossEntity.getHealth() * 100) / livingBossEntity.getMaxHealth() * 3;

        if (!is1_8) {
            if (progress >= 1)
                this.bossBar.setProgress(1);
            else
                this.bossBar.setProgress(progress);
        } else {
            if (progress >= 1)
                this.nearbyPlayers.forEach(player -> bossBar1_8.setProgress(progress1_8));
            else
                this.nearbyPlayers.forEach(player -> bossBar1_8.setProgress(progress1_8));
        }
    }

    @Override
    public BossPathfinderEdited editBossPathfinder() {
        return bossPathfinderEdited;
    }

    @Override
    public EntityType getEntityType() {
        return type;
    }

    @Override
    public LivingEntity getBossEntity() {
        return livingBossEntity;
    }

    @Override
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public BossSettings getBossSettings() {
        return bossSettings;
    }

    @Override
    public ItemStack getEgg() {
        return ItemCreator.getItem(Material.MONSTER_EGG, "§eSpawnEgg: " + getBossName(),
                1, (byte) type.getTypeId(), Arrays.asList("§eBossID: " + getBossID(),
                        "§eLivingID: " + getLivingID(),
                        " ",
                        "  ",
                        "§eRight click to spawn"));
    }

    @Override
    public String toString() {
        return "IBoss[name=" + this.name + ",id=" + this.getBossID() + ",naturalSpawn=" + (this.biome != null) +
                ",type=" + this.type + ",hasSpawner=" + this.hasSpawner + "]";
    }

}
