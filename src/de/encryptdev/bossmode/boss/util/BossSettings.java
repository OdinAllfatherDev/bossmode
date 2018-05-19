package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.special.SpecialAttack;
import de.encryptdev.bossmode.ref.Reflection;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class BossSettings {

    public static final String META_DATA_BOSS_ID = "bm_boss_id";
    public static final String META_DATA_BOSS_LIVING_ID = "bm_boss_living_id";
    public static final String META_DATA_BOSS_POTION = "bm_potion";

    private int bossId;
    private LivingEntity bossEntity;
    private double maxHealth;
    private BossEquipment equipment;
    private float dropChanceWeaponMainHand;
    private float dropChanceWeaponSecondHand;
    private float damage;
    private float chanceToSpawn;
    private float chanceToSpawnBySpawnEntity;
    private int droppedXp;
    private double speed;
    private double nearbyRad;
    private double spawnRadius;
    private int spawnAmount;
    private int specialAttackTicks;
    private List<PotionEffect> potionEffects;
    private List<ItemStack> naturalDrops;
    private Biome biome;
    private List<SpecialAttack> specialAttacks;

    //Advanced Settings
    private List<String> nearAttackEntities;
    private boolean lookAtPlayer;
    private boolean is1_8;

    public BossSettings(int bossId, double maxHealth, BossEquipment equipment,
                        float dropChanceWeaponMainHand, float dropChanceWeaponSecondHand, List<ItemStack> naturalDrops, float damage,
                        float chanceToSpawn, double speed, int spawnAmount, float chanceToSpawnBySpawnEntity,
                        double nearbyRad, double spawnRadius, int droppedXp,
                        List<PotionEffect> potionEffects, Biome biome, List<SpecialAttack> specialAttacks, List<String> nearAttackEntities, boolean lookAtPlayer) {
        this.bossId = bossId;
        this.maxHealth = maxHealth;
        this.equipment = equipment;
        this.dropChanceWeaponMainHand = dropChanceWeaponMainHand;
        this.dropChanceWeaponSecondHand = dropChanceWeaponSecondHand;
        this.naturalDrops = naturalDrops;
        this.damage = damage;
        this.chanceToSpawn = chanceToSpawn;
        this.speed = speed;
        this.spawnAmount = spawnAmount;
        this.droppedXp = droppedXp;
        this.spawnRadius = spawnRadius;
        this.chanceToSpawnBySpawnEntity = chanceToSpawnBySpawnEntity;
        this.biome = biome;
        this.nearbyRad = nearbyRad;
        this.potionEffects = potionEffects;
        this.specialAttacks = specialAttacks;
        this.specialAttackTicks = BossMode.getInstance().getConfig().getInt("specialAttackTicks");
        this.nearAttackEntities = nearAttackEntities;
        this.lookAtPlayer = lookAtPlayer;
        this.is1_8 = BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R1 || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R2 ||
                BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R3;
    }

    public LivingEntity createLivingEntity(EntityType type, Location location, int livingId) {
        Entity spawned = location.getWorld().spawnEntity(location, type);
        bossEntity = (LivingEntity) spawned;

        if (equipment != null) {
            if (equipment.getHelmet() != null)
                bossEntity.getEquipment().setHelmet(equipment.getHelmet());

            if (equipment.getChestplate() != null)
                bossEntity.getEquipment().setChestplate(equipment.getChestplate());

            if (equipment.getLeggings() != null)
                bossEntity.getEquipment().setLeggings(equipment.getLeggings());

            if (equipment.getBoots() != null)
                bossEntity.getEquipment().setBoots(equipment.getBoots());
        }

        if (equipment.getMainHand() != null) {
            if (!is1_8) {
                bossEntity.getEquipment().setItemInMainHand(equipment.getMainHand());
                bossEntity.getEquipment().setItemInMainHandDropChance(dropChanceWeaponMainHand);
            } else {
                bossEntity.getEquipment().setItemInHand(equipment.getMainHand());
            }
        }


        if (equipment.getOffHand() != null) {
            if (!is1_8) {
                bossEntity.getEquipment().setItemInOffHand(equipment.getOffHand());
                bossEntity.getEquipment().setItemInOffHandDropChance(dropChanceWeaponMainHand);
            }
        }

        if (!is1_8) {
            bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
            if (bossEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null)
                bossEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
            bossEntity.setHealth(maxHealth);
        } else {
            bossEntity.setMaxHealth(maxHealth >= 2048 ? 2048 : maxHealth);
            bossEntity.setHealth(maxHealth >= 2048 ? 2048 : maxHealth);
        }

        List<PotionEffect> potionEffectList = new ArrayList<>();

        if (potionEffects != null)
            if (potionEffects.size() > 0)
                for (PotionEffect pe : potionEffects) {
                    if (pe.getType().getName().equalsIgnoreCase(PotionEffectType.POISON.getName()) || pe.getType().getName().equalsIgnoreCase(PotionEffectType.BLINDNESS.getName())
                            || pe.getType().getName().equalsIgnoreCase(PotionEffectType.WITHER.getName())
                            || pe.getType().getName().equalsIgnoreCase(PotionEffectType.HUNGER.getName())
                            || pe.getType().getName().equalsIgnoreCase(PotionEffectType.CONFUSION.getName())) {
                        potionEffectList.add(pe);
                    } else {
                        bossEntity.addPotionEffect(pe);
                    }
                }


        //SetMetadata
        bossEntity.setMetadata(META_DATA_BOSS_ID, new FixedMetadataValue(BossMode.getInstance(), bossId));
        bossEntity.setMetadata(META_DATA_BOSS_LIVING_ID, new FixedMetadataValue(BossMode.getInstance(), livingId));
        bossEntity.setMetadata(META_DATA_BOSS_POTION, new FixedMetadataValue(BossMode.getInstance(), potionEffectList));

        return bossEntity;
    }

    public BossEquipment getEquipment() {
        return equipment;
    }

    public boolean isLookAtPlayer() {
        return lookAtPlayer;
    }

    public List<String> getNearAttackEntities() {
        return nearAttackEntities;
    }

    public int getDroppedXp() {
        return droppedXp;
    }

    public double getSpawnRadius() {
        return spawnRadius;
    }

    public double getNearbyRad() {
        return nearbyRad;
    }

    public List<PotionEffect> getPotionEffects() {
        return potionEffects;
    }

    public float getChanceToSpawnBySpawnEntity() {
        return chanceToSpawnBySpawnEntity;
    }

    public List<SpecialAttack> getSpecialAttacks() {
        return specialAttacks;
    }

    public double getSpeed() {
        return speed;
    }

    public Biome getBiome() {
        return biome;
    }

    public int getSpecialAttackTicks() {
        return specialAttackTicks;
    }

    public float getChanceToSpawn() {
        return chanceToSpawn;
    }

    public int getSpawnAmount() {
        return spawnAmount;
    }

    public int getBossId() {
        return bossId;
    }

    public float getDropChanceWeaponMainHand() {
        return dropChanceWeaponMainHand;
    }

    public float getDropChanceWeaponSecondHand() {
        return dropChanceWeaponSecondHand;
    }

    public List<ItemStack> getNaturalDrops() {
        return naturalDrops;
    }


    public double getMaxHealth() {
        return maxHealth;
    }

    public float getDamage() {
        return damage;
    }

    public boolean isNaturalSpawn() {
        return biome != null;
    }
}
