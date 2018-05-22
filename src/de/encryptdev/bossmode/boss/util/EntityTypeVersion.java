package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.ref.Reflection;

/**
 * Created by EncryptDev
 */
public class EntityTypeVersion {

    public static final EntityTypeVersion WITHER_SKELETON = new EntityTypeVersion(5, Reflection.getNMSClassSkipped("EntitySkeletonWither"), "Wither Skeleton");
    public static final EntityTypeVersion STRAY = new EntityTypeVersion(6, Reflection.getNMSClassSkipped("EntitySkeletonStray"), "Stray");
    public static final EntityTypeVersion HUSK = new EntityTypeVersion(23, Reflection.getNMSClassSkipped("EntityZombieHusk"), "Husk");
    public static final EntityTypeVersion ZOMBIE_VILLAGER = new EntityTypeVersion(27, Reflection.getNMSClassSkipped("EntityZombieVillager"), "Zombie Villager");
    public static final EntityTypeVersion SKELETON_HORSE = new EntityTypeVersion(28, Reflection.getNMSClassSkipped("EntityHorseSkeleton"), "Skeleton Horse");
    public static final EntityTypeVersion ZOMBIE_HORSE = new EntityTypeVersion(29, Reflection.getNMSClassSkipped("EntityHorseZombie"), "Zombie Horse");
    public static final EntityTypeVersion DONKEY = new EntityTypeVersion(31, Reflection.getNMSClassSkipped("EntityHorseDonkey"), "Donkey");
    public static final EntityTypeVersion MULE = new EntityTypeVersion(32, Reflection.getNMSClassSkipped("EntityHorseMule"), "Mule");
    public static final EntityTypeVersion EVOKER_FANGS = new EntityTypeVersion(33, Reflection.getNMSClassSkipped("EntityEvokerFangs"), "Evoker Fangs");
    public static final EntityTypeVersion EVOKER = new EntityTypeVersion(34, Reflection.getNMSClassSkipped("EntityEvoker"), "Evoker");
    public static final EntityTypeVersion VEX = new EntityTypeVersion(35, Reflection.getNMSClassSkipped("EntityVex"), "Vex");
    public static final EntityTypeVersion VINDICATOR = new EntityTypeVersion(36, Reflection.getNMSClassSkipped("EntityVindicator"), "Vindicator");
    public static final EntityTypeVersion ILLUSIONER = new EntityTypeVersion(37, Reflection.getNMSClassSkipped("EntityIllagerIllusioner"), "Illusioner");
    public static final EntityTypeVersion CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassSkipped("EntityCreeper"), "Creeper");
    public static final EntityTypeVersion SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassSkipped("EntitySkeleton"), "Skeleton");
    public static final EntityTypeVersion SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassSkipped("EntitySpider"), "Spider");
    public static final EntityTypeVersion GIANT = new EntityTypeVersion(53, Reflection.getNMSClassSkipped("EntityGiantZombie"), "Giant");
    public static final EntityTypeVersion ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassSkipped("EntityZombie"), "Zombie");
    public static final EntityTypeVersion SLIME = new EntityTypeVersion(55, Reflection.getNMSClassSkipped("EntitySlime"), "Slime");
    public static final EntityTypeVersion GHAST = new EntityTypeVersion(56, Reflection.getNMSClassSkipped("EntityGhast"), "Ghast");
    public static final EntityTypeVersion PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassSkipped("EntityPigZombie"), "Pig Zombie");
    public static final EntityTypeVersion ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassSkipped("EntityEnderman"), "Enderman");
    public static final EntityTypeVersion CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassSkipped("EntityCaveSpider"), "Cave Spider");
    public static final EntityTypeVersion SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassSkipped("EntitySilverfish"), "Silverfish");
    public static final EntityTypeVersion BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassSkipped("EntityBlaze"), "Blaze");
    public static final EntityTypeVersion MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassSkipped("EntityMagmaCube"), "Magma Cube");
    public static final EntityTypeVersion ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassSkipped("EntityEnderDragon"), "Ender Dragon");
    public static final EntityTypeVersion WITHER = new EntityTypeVersion(64, Reflection.getNMSClassSkipped("EntityWither"), "Wither");
    public static final EntityTypeVersion BAT = new EntityTypeVersion(65, Reflection.getNMSClassSkipped("EntityBat"), "Bat");
    public static final EntityTypeVersion WITCH = new EntityTypeVersion(66, Reflection.getNMSClassSkipped("EntityWitch"), "Witch");
    public static final EntityTypeVersion ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassSkipped("EntityEndermite"), "Endermite");
    public static final EntityTypeVersion GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassSkipped("EntityGuardian"), "Guardian");
    public static final EntityTypeVersion SHULKER = new EntityTypeVersion(69, Reflection.getNMSClassSkipped("EntityShulker"), "Shulker");
    public static final EntityTypeVersion PIG = new EntityTypeVersion(90, Reflection.getNMSClassSkipped("EntityPig"), "Pig");
    public static final EntityTypeVersion SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassSkipped("EntitySheep"), "Sheep");
    public static final EntityTypeVersion COW = new EntityTypeVersion(92, Reflection.getNMSClassSkipped("EntityCow"), "Cow");
    public static final EntityTypeVersion CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassSkipped("EntityChicken"), "Chicken");
    public static final EntityTypeVersion SQUID = new EntityTypeVersion(94, Reflection.getNMSClassSkipped("EntitySquid"), "Squid");
    public static final EntityTypeVersion WOLF = new EntityTypeVersion(95, Reflection.getNMSClassSkipped("EntityWolf"), "Wolf");
    public static final EntityTypeVersion MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassSkipped("EntityMushroomCow"), "Mushroom Cow");
    public static final EntityTypeVersion SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassSkipped("EntitySnowman"), "Snowman");
    public static final EntityTypeVersion OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassSkipped("EntityOcelot"), "Ocelot");
    public static final EntityTypeVersion IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassSkipped("EntityIronGolem"), "Iron Golem");
    public static final EntityTypeVersion HORSE = new EntityTypeVersion(100, Reflection.getNMSClassSkipped("EntityHorse"), "Horse");
    public static final EntityTypeVersion RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassSkipped("EntityRabbit"), "Rabbit");
    public static final EntityTypeVersion POLAR_BEAR = new EntityTypeVersion(102, Reflection.getNMSClassSkipped("EntityPolarBear"), "Polar Bear");
    public static final EntityTypeVersion LLAMA = new EntityTypeVersion(103, Reflection.getNMSClassSkipped("EntityLlama"), "Llama");
    public static final EntityTypeVersion PARROT = new EntityTypeVersion(105, Reflection.getNMSClassSkipped("EntityParrot"), "Parrot");
    public static final EntityTypeVersion VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassSkipped("EntityVillager"), "Villager");

    //Size: 8
    public static final EntityTypeVersion[] BOSS_MONSTER = {ZOMBIE, SKELETON, CREEPER, ENDERMAN, PIG_ZOMBIE, SPIDER, CAVE_SPIDER, GIANT};

    //Size: 32
    public static final EntityTypeVersion[] V1_8 = {CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER, SILVERFISH,
            BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITHER, BAT, WITCH, ENDERMITE,
            GUARDIAN, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE, RABBIT, VILLAGER};

    //Size: 33
    public static final EntityTypeVersion[] V1_9 = {CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER, SILVERFISH,
            BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITHER, BAT, WITCH, ENDERMITE,
            GUARDIAN, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE, RABBIT, VILLAGER, SHULKER};

    //Size: 34
    public static final EntityTypeVersion[] V1_10 = {CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER, SILVERFISH,
            BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITHER, BAT, WITCH, ENDERMITE,
            GUARDIAN, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE, RABBIT, VILLAGER, SHULKER, POLAR_BEAR};

    //Size: 47
    public static final EntityTypeVersion[] V1_11 = {WITHER_SKELETON, CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER, SILVERFISH,
            BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITHER, BAT, WITCH, ENDERMITE,
            GUARDIAN, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE, RABBIT, VILLAGER, SHULKER, POLAR_BEAR, STRAY, HUSK, ZOMBIE_VILLAGER,
            SKELETON_HORSE, ZOMBIE_HORSE, DONKEY, MULE, EVOKER, EVOKER_FANGS, VEX, VINDICATOR, LLAMA};

    //Size: 49
    public static final EntityTypeVersion[] V1_12 = {WITHER_SKELETON, CREEPER, SKELETON, SPIDER, GIANT, ZOMBIE, SLIME, GHAST, PIG_ZOMBIE, ENDERMAN, CAVE_SPIDER, SILVERFISH,
            BLAZE, MAGMA_CUBE, ENDER_DRAGON, WITHER, BAT, WITCH, ENDERMITE,
            GUARDIAN, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MUSHROOM_COW, SNOWMAN, OCELOT, IRON_GOLEM, HORSE, RABBIT, VILLAGER, SHULKER, POLAR_BEAR, STRAY, HUSK, ZOMBIE_VILLAGER,
            SKELETON_HORSE, ZOMBIE_HORSE, DONKEY, MULE, EVOKER, EVOKER_FANGS, VEX, VINDICATOR, LLAMA, ILLUSIONER, PARROT};

    private String name;
    private int id;
    private Class<?> nmsClass;

    public EntityTypeVersion(int id, Class<?> nmsClass, String name) {
        this.id = id;
        this.nmsClass = nmsClass;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public Class<?> getNmsClass() {
        return nmsClass;
    }

    public static EntityTypeVersion[] getVersionArray(Reflection.NMSVersion version) {
        switch (version) {
            case V1_8_R1:
            case V1_8_R2:
            case V1_8_R3:
                return V1_8;
            case V1_9_R1:
            case V1_9_R2:
                return V1_9;
            case V1_10_R1:
                return V1_10;
            case V1_11_R1:
                return V1_11;
            case V1_12_R1:
                return V1_12;
        }

        return V1_8;
    }

    public static EntityTypeVersion valueOf(String name) {
        for (EntityTypeVersion etv : V1_12)
            if (etv != null)
                if (etv.getName().equals(name))
                    return etv;
        return null;
    }

}
