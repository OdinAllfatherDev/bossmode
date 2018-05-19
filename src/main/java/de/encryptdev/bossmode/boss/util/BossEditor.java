package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.Boss;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.LinkedList;
import java.util.List;

/**
 * This class build the boss
 * <p>
 * Created by EncryptDev
 */
public class BossEditor {

    private IBoss iBoss;
    private int id;
    private EntityType type;
    private String name;
    private double maxHealth;
    private double damage;
    private ItemStack[] armor;
    private ItemStack mainHand;
    private ItemStack offHand;
    private List<ItemStack> naturalDrops;
    private float dropChanceMainHand;
    private float dropChanceOffHand;
    private float chanceToSpawn;
    private float chanceToSpawnByEntitySpawn;
    private double speed;
    private double nearbyRad;
    private double spawnRadius;
    private int spawnAmount;
    private int droppedXp;
    private List<PotionEffect> potionEffects;
    private Location spawnLocation;
    private Biome biome;
    private List<SpecialAttack> specialAttacks;
    private boolean finish;

    private List<String> nearAttackEntities;
    private boolean lookAtPlayer;

    private BossEditor(int id, IBoss iBoss, EntityType type,
                       ItemStack[] armor, double speed, String name, double maxHealth,
                       double damage, ItemStack mainHand, ItemStack offHand, List<ItemStack> naturalDrops,
                       float dropChanceMainHand, float dropChanceOffHand, float chanceToSpawn,
                       int spawnAmount, Location spawnLocation,
                       float chanceToSpawnByEntitySpawn, double nearbyRad,
                       double spawnRadius, int droppedXp, List<PotionEffect> potionEffects,
                       Biome biome, List<SpecialAttack> specialAttacks, List<String> nearAttackEntities, boolean lookAtPlayer) {
        this.id = id;
        this.iBoss = iBoss;
        this.type = type;
        this.armor = armor;
        this.armor[0] = iBoss != null ? iBoss.getBossSettings().getArmor()[0] : null;
        this.armor[1] = iBoss != null ? iBoss.getBossSettings().getArmor()[1] : null;
        this.armor[2] = iBoss != null ? iBoss.getBossSettings().getArmor()[2] : null;
        this.armor[3] = iBoss != null ? iBoss.getBossSettings().getArmor()[3] : null;
        this.speed = speed;
        this.name = name;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.spawnRadius = spawnRadius;
        this.droppedXp = droppedXp;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.naturalDrops = naturalDrops;
        this.nearbyRad = nearbyRad;
        this.dropChanceMainHand = dropChanceMainHand;
        this.dropChanceOffHand = dropChanceOffHand;
        this.chanceToSpawn = chanceToSpawn;
        this.chanceToSpawnByEntitySpawn = chanceToSpawnByEntitySpawn;
        this.spawnAmount = spawnAmount;
        this.potionEffects = potionEffects;
        this.spawnLocation = spawnLocation;
        this.biome = biome;
        this.specialAttacks = specialAttacks;
        this.finish = false;
        this.nearAttackEntities = nearAttackEntities;
        this.lookAtPlayer = lookAtPlayer;
    }

    public BossEditor(IBoss iboss) {
        this(iboss.getBossID(), iboss, iboss.getEntityType(),
                iboss.getBossSettings().getArmor(), iboss.getBossSettings().getSpeed(),
                iboss.getBossName(), iboss.getBossSettings().getMaxHealth(), iboss.getBossSettings().getDamage(),
                iboss.getBossSettings().getWeaponMainHand(), iboss.getBossSettings().getWeaponSecondHand(),
                iboss.getBossSettings().getNaturalDrops(), iboss.getBossSettings().getDropChanceWeaponMainHand(),
                iboss.getBossSettings().getDropChanceWeaponSecondHand(), iboss.getBossSettings().getChanceToSpawn(),
                iboss.getBossSettings().getSpawnAmount(),
                iboss.getSpawnLocation(), iboss.getBossSettings().getChanceToSpawnBySpawnEntity(),
                iboss.getBossSettings().getNearbyRad(), iboss.getBossSettings().getSpawnRadius(), iboss.getBossSettings().getDroppedXp(),
                iboss.getBossSettings().getPotionEffects(), iboss.getBossSettings().getBiome(), iboss.getSpecialAttacks(), iboss.getBossSettings().getNearAttackEntities(),
                iboss.getBossSettings().isLookAtPlayer());
    }

    public BossEditor(EntityType type) {
        this(BossUtil.getBossIds() + 1, null, type, new ItemStack[4], -1,
                "", 20, 10, null,
                null, new LinkedList<>(), 1,
                1, 1, 20, null,
                0, 100, 5, 1, new LinkedList<>(), null, new LinkedList<>(),
                new LinkedList<>(), true);
    }

    public boolean isLookAtPlayer() {
        return lookAtPlayer;
    }

    public void setDroppedXp(int droppedXp) {
        this.droppedXp = droppedXp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void addPotionEffect(PotionEffect potionEffect) {
        this.potionEffects.add(potionEffect);
    }

    public void setNearbyRad(double nearbyRad) {
        this.nearbyRad = nearbyRad;
    }

    public void setSpawnRadius(double spawnRadius) {
        this.spawnRadius = spawnRadius;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void clearPotionEffect(){
        this.potionEffects.clear();
    }

    public void setChanceToSpawnByEntitySpawn(float chanceToSpawnByEntitySpawn) {
        this.chanceToSpawnByEntitySpawn = chanceToSpawnByEntitySpawn;
    }

    public void setHelmet(ItemStack helmet) {
        this.armor[0] = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.armor[1] = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.armor[2] = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.armor[3] = boots;
    }

    public void setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
    }

    public void setOffHand(ItemStack offHand) {
        this.offHand = offHand;
    }

    public void setNaturalDrops(ItemStack naturalDrops) {
        if(naturalDrops == null)
            return;
        this.naturalDrops.add(naturalDrops);
    }

    public void setDropChanceMainHand(float dropChanceMainHand) {
        this.dropChanceMainHand = dropChanceMainHand;
    }

    public void setDropChanceOffHand(float dropChanceOffHand) {
        this.dropChanceOffHand = dropChanceOffHand;
    }

    public void setChanceToSpawn(float chanceToSpawn) {
        this.chanceToSpawn = chanceToSpawn;
    }

    public void addSpecialAttack(SpecialAttack specialAttack) {
        this.specialAttacks.add(specialAttack);
    }

    public void setSpawnAmount(int spawnAmount) {
        this.spawnAmount = spawnAmount;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isNaturalSpawn() {
        return biome != null;
    }

    public void setLookAtPlayer(boolean lookAtPlayer) {
        this.lookAtPlayer = lookAtPlayer;
    }

    public boolean addNearEntityClass(String clazz) {
        if(this.nearAttackEntities.contains(clazz))
            return false;
        this.nearAttackEntities.add(clazz);
        return true;
    }

    public boolean removeNearEntityClass(String clazz) {
        if(!this.nearAttackEntities.contains(clazz))
            return false;
        this.nearAttackEntities.remove(clazz);
        return true;
    }

    public int nearEntitySize() {
        return this.nearAttackEntities.size();
    }

    public IBoss build() {

        BossSettings settings = new BossSettings(id, maxHealth,
                armor, mainHand, offHand, dropChanceMainHand, dropChanceOffHand, naturalDrops,
                (float) damage, chanceToSpawn, speed, spawnAmount, chanceToSpawnByEntitySpawn, nearbyRad,
                spawnRadius, droppedXp,
                potionEffects, biome, specialAttacks, nearAttackEntities, lookAtPlayer);

        if (iBoss != null) {
            iBoss.setName(name);
            iBoss.setBossSettings(settings);
            return iBoss;
        }

        int livingId = BossUtil.getLivingBossIds() + 1;

        BossMode.getInstance().getConfig().set("livingBossId", livingId);
        BossMode.getInstance().saveConfig();

        finish = true;

        return new Boss(settings, livingId, name, spawnLocation, type);
    }

}
