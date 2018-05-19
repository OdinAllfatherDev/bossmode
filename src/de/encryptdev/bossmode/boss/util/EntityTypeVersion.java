package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.ref.Reflection;

/**
 * Created by EncryptDev
 */
public class EntityTypeVersion {

    public static final EntityTypeVersion WITHER_SKELETON;
    public static final EntityTypeVersion STRAY;
    public static final EntityTypeVersion HUSK;
    public static final EntityTypeVersion ZOMBIE_VILLAGER;
    public static final EntityTypeVersion SKELETON_HORSE;
    public static final EntityTypeVersion ZOMBIE_HORSE;
    public static final EntityTypeVersion DONKEY;
    public static final EntityTypeVersion MULE;
    public static final EntityTypeVersion EVOKER_FANGS;
    public static final EntityTypeVersion EVOKER;
    public static final EntityTypeVersion VEX;
    public static final EntityTypeVersion VINDICATOR;
    public static final EntityTypeVersion ILLUSIONER;
    public static final EntityTypeVersion CREEPER;
    public static final EntityTypeVersion SKELETON;
    public static final EntityTypeVersion SPIDER;
    public static final EntityTypeVersion GIANT;
    public static final EntityTypeVersion ZOMBIE;
    public static final EntityTypeVersion SLIME;
    public static final EntityTypeVersion GHAST;
    public static final EntityTypeVersion PIG_ZOMBIE;
    public static final EntityTypeVersion ENDERMAN;
    public static final EntityTypeVersion CAVE_SPIDER;
    public static final EntityTypeVersion SILVERFISH;
    public static final EntityTypeVersion BLAZE;
    public static final EntityTypeVersion MAGMA_CUBE;
    public static final EntityTypeVersion ENDER_DRAGON;
    public static final EntityTypeVersion WITHER;
    public static final EntityTypeVersion BAT;
    public static final EntityTypeVersion WITCH;
    public static final EntityTypeVersion ENDERMITE;
    public static final EntityTypeVersion GUARDIAN;
    public static final EntityTypeVersion SHULKER;
    public static final EntityTypeVersion PIG;
    public static final EntityTypeVersion SHEEP;
    public static final EntityTypeVersion COW;
    public static final EntityTypeVersion CHICKEN;
    public static final EntityTypeVersion SQUID;
    public static final EntityTypeVersion WOLF;
    public static final EntityTypeVersion MUSHROOM_COW;
    public static final EntityTypeVersion SNOWMAN;
    public static final EntityTypeVersion OCELOT;
    public static final EntityTypeVersion IRON_GOLEM;
    public static final EntityTypeVersion HORSE;
    public static final EntityTypeVersion RABBIT;
    public static final EntityTypeVersion POLAR_BEAR;
    public static final EntityTypeVersion LLAMA;
    public static final EntityTypeVersion PARROT;
    public static final EntityTypeVersion VILLAGER;

    static {

        /*Normal is this all in a enum, but when i initialise all direct, then throw the plugin a exception when
          the nms class not found.

          Example:
          You play on version 1.8 then go the plugin in this class and the plugin initialise all variables here. Now, the plugin come to STRAY, then throw a exception because
          the EntityStray class doesn't exist in the 1.8
        */
        Reflection.NMSVersion nmsVersion = BossMode.getInstance().getNmsVersion();
        Reflection ref = new Reflection(nmsVersion);

        boolean is1_8 = nmsVersion == Reflection.NMSVersion.V1_8_R1 || nmsVersion == Reflection.NMSVersion.V1_8_R2 || nmsVersion == Reflection.NMSVersion.V1_8_R3;
        boolean is1_9 = nmsVersion == Reflection.NMSVersion.V1_9_R1 || nmsVersion == Reflection.NMSVersion.V1_9_R2;
        boolean is1_10 = nmsVersion == Reflection.NMSVersion.V1_10_R1;
        boolean is1_11 = nmsVersion == Reflection.NMSVersion.V1_11_R1;
        boolean is1_12 = nmsVersion == Reflection.NMSVersion.V1_12_R1;

        if (is1_8 && !is1_9 && !is1_10 && !is1_11 && !is1_12) {

            CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassStatic("EntityCreeper"), "Creeper");
            SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassStatic("EntitySkeleton"), "Skeleton");
            SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassStatic("EntitySpider"), "Spider");
            GIANT = new EntityTypeVersion(53, Reflection.getNMSClassStatic("EntityGiantZombie"), "Giant");
            ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassStatic("EntityZombie"), "Zombie");
            SLIME = new EntityTypeVersion(55, Reflection.getNMSClassStatic("EntitySlime"), "Slime");
            GHAST = new EntityTypeVersion(56, Reflection.getNMSClassStatic("EntityGhast"), "Ghast");
            PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassStatic("EntityPigZombie"), "Pig Zombie");
            ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassStatic("EntityEnderman"), "Enderman");
            CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassStatic("EntityCaveSpider"), "Cave Spider");
            SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassStatic("EntitySilverfish"), "Silverfish");
            BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassStatic("EntityBlaze"), "Blaze");
            MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassStatic("EntityMagmaCube"), "Magma Cube");
            ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassStatic("EntityEnderDragon"), "Ender Dragon");
            WITHER = new EntityTypeVersion(64, Reflection.getNMSClassStatic("EntityWither"), "Wither");
            BAT = new EntityTypeVersion(65, Reflection.getNMSClassStatic("EntityBat"), "Bat");
            WITCH = new EntityTypeVersion(66, Reflection.getNMSClassStatic("EntityWitch"), "Witch");
            ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassStatic("EntityEndermite"), "Endermite");
            GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassStatic("EntityGuardian"), "Guardian");
            PIG = new EntityTypeVersion(90, Reflection.getNMSClassStatic("EntityPig"), "Pig");
            SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassStatic("EntitySheep"), "Sheep");
            COW = new EntityTypeVersion(92, Reflection.getNMSClassStatic("EntityCow"), "Cow");
            CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassStatic("EntityChicken"), "Chicken");
            SQUID = new EntityTypeVersion(94, Reflection.getNMSClassStatic("EntitySquid"), "Squid");
            WOLF = new EntityTypeVersion(95, Reflection.getNMSClassStatic("EntityWolf"), "Wolf");
            MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassStatic("EntityMushroomCow"), "Mushroom Cow");
            SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassStatic("EntitySnowman"), "Snowman");
            OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassStatic("EntityOcelot"), "Ocelot");
            IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassStatic("EntityIronGolem"), "Iron Golem");
            HORSE = new EntityTypeVersion(100, Reflection.getNMSClassStatic("EntityHorse"), "Horse");
            RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassStatic("EntityRabbit"), "Rabbit");
            VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassStatic("EntityVillager"), "Villager");
            SHULKER = null;
            POLAR_BEAR = null;
            STRAY = null;
            HUSK = null;
            ZOMBIE_VILLAGER = null;
            SKELETON_HORSE = null;
            ZOMBIE_HORSE = null;
            DONKEY = null;
            MULE = null;
            EVOKER_FANGS = null;
            EVOKER = null;
            VEX = null;
            VINDICATOR = null;
            LLAMA = null;
            PARROT = null;
            WITHER_SKELETON = null;
            ILLUSIONER = null;

        } else if (!is1_8 && is1_9 && !is1_10 && !is1_11 && !is1_12) {

            CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassStatic("EntityCreeper"), "Creeper");
            SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassStatic("EntitySkeleton"), "Skeleton");
            SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassStatic("EntitySpider"), "Spider");
            GIANT = new EntityTypeVersion(53, Reflection.getNMSClassStatic("EntityGiantZombie"), "Giant");
            ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassStatic("EntityZombie"), "Zombie");
            SLIME = new EntityTypeVersion(55, Reflection.getNMSClassStatic("EntitySlime"), "Slime");
            GHAST = new EntityTypeVersion(56, Reflection.getNMSClassStatic("EntityGhast"), "Ghast");
            PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassStatic("EntityPigZombie"), "Pig Zombie");
            ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassStatic("EntityEnderman"), "Enderman");
            CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassStatic("EntityCaveSpider"), "Cave Spider");
            SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassStatic("EntitySilverfish"), "Silverfish");
            BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassStatic("EntityBlaze"), "Blaze");
            MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassStatic("EntityMagmaCube"), "Magma Cube");
            ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassStatic("EntityEnderDragon"), "Ender Dragon");
            WITHER = new EntityTypeVersion(64, Reflection.getNMSClassStatic("EntityWither"), "Wither");
            BAT = new EntityTypeVersion(65, Reflection.getNMSClassStatic("EntityBat"), "Bat");
            WITCH = new EntityTypeVersion(66, Reflection.getNMSClassStatic("EntityWitch"), "Witch");
            ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassStatic("EntityEndermite"), "Endermite");
            GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassStatic("EntityGuardian"), "Guardian");
            SHULKER = new EntityTypeVersion(69, Reflection.getNMSClassStatic("EntityShulker"), "Shulker");
            PIG = new EntityTypeVersion(90, Reflection.getNMSClassStatic("EntityPig"), "Pig");
            SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassStatic("EntitySheep"), "Sheep");
            COW = new EntityTypeVersion(92, Reflection.getNMSClassStatic("EntityCow"), "Cow");
            CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassStatic("EntityChicken"), "Chicken");
            SQUID = new EntityTypeVersion(94, Reflection.getNMSClassStatic("EntitySquid"), "Squid");
            WOLF = new EntityTypeVersion(95, Reflection.getNMSClassStatic("EntityWolf"), "Wolf");
            MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassStatic("EntityMushroomCow"), "Mushroom Cow");
            SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassStatic("EntitySnowman"), "Snowman");
            OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassStatic("EntityOcelot"), "Ocelot");
            IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassStatic("EntityIronGolem"), "Iron Golem");
            HORSE = new EntityTypeVersion(100, Reflection.getNMSClassStatic("EntityHorse"), "Horse");
            RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassStatic("EntityRabbit"), "Rabbit");
            VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassStatic("EntityVillager"), "Villager");
            POLAR_BEAR = null;
            STRAY = null;
            HUSK = null;
            ZOMBIE_VILLAGER = null;
            SKELETON_HORSE = null;
            ZOMBIE_HORSE = null;
            DONKEY = null;
            MULE = null;
            EVOKER_FANGS = null;
            EVOKER = null;
            VEX = null;
            VINDICATOR = null;
            LLAMA = null;
            PARROT = null;
            WITHER_SKELETON = null;
            ILLUSIONER = null;

        } else if (!is1_8 && !is1_9 && is1_10 && !is1_11 && !is1_12) {

            CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassStatic("EntityCreeper"), "Creeper");
            SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassStatic("EntitySkeleton"), "Skeleton");
            SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassStatic("EntitySpider"), "Spider");
            GIANT = new EntityTypeVersion(53, Reflection.getNMSClassStatic("EntityGiantZombie"), "Giant");
            ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassStatic("EntityZombie"), "Zombie");
            SLIME = new EntityTypeVersion(55, Reflection.getNMSClassStatic("EntitySlime"), "Slime");
            GHAST = new EntityTypeVersion(56, Reflection.getNMSClassStatic("EntityGhast"), "Ghast");
            PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassStatic("EntityPigZombie"), "Pig Zombie");
            ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassStatic("EntityEnderman"), "Enderman");
            CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassStatic("EntityCaveSpider"), "Cave Spider");
            SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassStatic("EntitySilverfish"), "Silverfish");
            BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassStatic("EntityBlaze"), "Blaze");
            MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassStatic("EntityMagmaCube"), "Magma Cube");
            ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassStatic("EntityEnderDragon"), "Ender Dragon");
            WITHER = new EntityTypeVersion(64, Reflection.getNMSClassStatic("EntityWither"), "Wither");
            BAT = new EntityTypeVersion(65, Reflection.getNMSClassStatic("EntityBat"), "Bat");
            WITCH = new EntityTypeVersion(66, Reflection.getNMSClassStatic("EntityWitch"), "Witch");
            ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassStatic("EntityEndermite"), "Endermite");
            GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassStatic("EntityGuardian"), "Guardian");
            SHULKER = new EntityTypeVersion(69, Reflection.getNMSClassStatic("EntityShulker"), "Shulker");
            PIG = new EntityTypeVersion(90, Reflection.getNMSClassStatic("EntityPig"), "Pig");
            SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassStatic("EntitySheep"), "Sheep");
            COW = new EntityTypeVersion(92, Reflection.getNMSClassStatic("EntityCow"), "Cow");
            CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassStatic("EntityChicken"), "Chicken");
            SQUID = new EntityTypeVersion(94, Reflection.getNMSClassStatic("EntitySquid"), "Squid");
            WOLF = new EntityTypeVersion(95, Reflection.getNMSClassStatic("EntityWolf"), "Wolf");
            MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassStatic("EntityMushroomCow"), "Mushroom Cow");
            SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassStatic("EntitySnowman"), "Snowman");
            OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassStatic("EntityOcelot"), "Ocelot");
            IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassStatic("EntityIronGolem"), "Iron Golem");
            HORSE = new EntityTypeVersion(100, Reflection.getNMSClassStatic("EntityHorse"), "Horse");
            RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassStatic("EntityRabbit"), "Rabbit");
            VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassStatic("EntityVillager"), "Villager");
            POLAR_BEAR = new EntityTypeVersion(102, Reflection.getNMSClassStatic("EntityPolarBear"), "Polar Bear");
            STRAY = null;
            HUSK = null;
            ZOMBIE_VILLAGER = null;
            SKELETON_HORSE = null;
            ZOMBIE_HORSE = null;
            DONKEY = null;
            MULE = null;
            EVOKER_FANGS = null;
            EVOKER = null;
            VEX = null;
            VINDICATOR = null;
            LLAMA = null;
            PARROT = null;
            WITHER_SKELETON = null;
            ILLUSIONER = null;

        } else if (!is1_8 && !is1_9 && !is1_10 && is1_11 && !is1_12) {

            WITHER_SKELETON = new EntityTypeVersion(5, ref.getNMSClass("EntitySkeletonWither"), "Wither Skeleton");
            STRAY = new EntityTypeVersion(6, Reflection.getNMSClassStatic("EntitySkeletonStray"), "Stray");
            HUSK = new EntityTypeVersion(23, Reflection.getNMSClassStatic("EntityZombieHusk"), "Husk");
            ZOMBIE_VILLAGER = new EntityTypeVersion(27, Reflection.getNMSClassStatic("EntityZombieVillager"), "Zombie Villager");
            SKELETON_HORSE = new EntityTypeVersion(28, Reflection.getNMSClassStatic("EntityHorseSkeleton"), "Skeleton Horse");
            ZOMBIE_HORSE = new EntityTypeVersion(29, Reflection.getNMSClassStatic("EntityHorseZombie"), "Zombie Horse");
            DONKEY = new EntityTypeVersion(31, Reflection.getNMSClassStatic("EntityHorseDonkey"), "Donkey");
            MULE = new EntityTypeVersion(32, Reflection.getNMSClassStatic("EntityHorseMule"), "Mule");
            EVOKER_FANGS = new EntityTypeVersion(33, Reflection.getNMSClassStatic("EntityEvokerFangs"), "Evoker Fangs");
            EVOKER = new EntityTypeVersion(34, Reflection.getNMSClassStatic("EntityEvoker"), "Evoker");
            VEX = new EntityTypeVersion(35, Reflection.getNMSClassStatic("EntityVex"), "Vex");
            VINDICATOR = new EntityTypeVersion(36, Reflection.getNMSClassStatic("EntityVindicator"), "Vindicator");
            CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassStatic("EntityCreeper"), "Creeper");
            SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassStatic("EntitySkeleton"), "Skeleton");
            SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassStatic("EntitySpider"), "Spider");
            GIANT = new EntityTypeVersion(53, Reflection.getNMSClassStatic("EntityGiantZombie"), "Giant");
            ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassStatic("EntityZombie"), "Zombie");
            SLIME = new EntityTypeVersion(55, Reflection.getNMSClassStatic("EntitySlime"), "Slime");
            GHAST = new EntityTypeVersion(56, Reflection.getNMSClassStatic("EntityGhast"), "Ghast");
            PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassStatic("EntityPigZombie"), "Pig Zombie");
            ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassStatic("EntityEnderman"), "Enderman");
            CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassStatic("EntityCaveSpider"), "Cave Spider");
            SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassStatic("EntitySilverfish"), "Silverfish");
            BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassStatic("EntityBlaze"), "Blaze");
            MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassStatic("EntityMagmaCube"), "Magma Cube");
            ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassStatic("EntityEnderDragon"), "Ender Dragon");
            WITHER = new EntityTypeVersion(64, Reflection.getNMSClassStatic("EntityWither"), "Wither");
            BAT = new EntityTypeVersion(65, Reflection.getNMSClassStatic("EntityBat"), "Bat");
            WITCH = new EntityTypeVersion(66, Reflection.getNMSClassStatic("EntityWitch"), "Witch");
            ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassStatic("EntityEndermite"), "Endermite");
            GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassStatic("EntityGuardian"), "Guardian");
            SHULKER = new EntityTypeVersion(69, Reflection.getNMSClassStatic("EntityShulker"), "Shulker");
            PIG = new EntityTypeVersion(90, Reflection.getNMSClassStatic("EntityPig"), "Pig");
            SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassStatic("EntitySheep"), "Sheep");
            COW = new EntityTypeVersion(92, Reflection.getNMSClassStatic("EntityCow"), "Cow");
            CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassStatic("EntityChicken"), "Chicken");
            SQUID = new EntityTypeVersion(94, Reflection.getNMSClassStatic("EntitySquid"), "Squid");
            WOLF = new EntityTypeVersion(95, Reflection.getNMSClassStatic("EntityWolf"), "Wolf");
            MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassStatic("EntityMushroomCow"), "Mushroom Cow");
            SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassStatic("EntitySnowman"), "Snowman");
            OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassStatic("EntityOcelot"), "Ocelot");
            IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassStatic("EntityIronGolem"), "Iron Golem");
            HORSE = new EntityTypeVersion(100, Reflection.getNMSClassStatic("EntityHorse"), "Horse");
            RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassStatic("EntityRabbit"), "Rabbit");
            POLAR_BEAR = new EntityTypeVersion(102, Reflection.getNMSClassStatic("EntityPolarBear"), "Polar Bear");
            LLAMA = new EntityTypeVersion(103, Reflection.getNMSClassStatic("EntityLlama"), "Llama");
            VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassStatic("EntityVillager"), "Villager");
            PARROT = null;
            ILLUSIONER = null;

        } else {
            WITHER_SKELETON = new EntityTypeVersion(5, ref.getNMSClass("EntitySkeletonWither"), "Wither Skeleton");
            STRAY = new EntityTypeVersion(6, Reflection.getNMSClassStatic("EntitySkeletonStray"), "Stray");
            HUSK = new EntityTypeVersion(23, Reflection.getNMSClassStatic("EntityZombieHusk"), "Husk");
            ZOMBIE_VILLAGER = new EntityTypeVersion(27, Reflection.getNMSClassStatic("EntityZombieVillager"), "Zombie Villager");
            SKELETON_HORSE = new EntityTypeVersion(28, Reflection.getNMSClassStatic("EntityHorseSkeleton"), "Skeleton Horse");
            ZOMBIE_HORSE = new EntityTypeVersion(29, Reflection.getNMSClassStatic("EntityHorseZombie"), "Zombie Horse");
            DONKEY = new EntityTypeVersion(31, Reflection.getNMSClassStatic("EntityHorseDonkey"), "Donkey");
            MULE = new EntityTypeVersion(32, Reflection.getNMSClassStatic("EntityHorseMule"), "Mule");
            EVOKER_FANGS = new EntityTypeVersion(33, Reflection.getNMSClassStatic("EntityEvokerFangs"), "Evoker Fangs");
            EVOKER = new EntityTypeVersion(34, Reflection.getNMSClassStatic("EntityEvoker"), "Evoker");
            VEX = new EntityTypeVersion(35, Reflection.getNMSClassStatic("EntityVex"), "Vex");
            VINDICATOR = new EntityTypeVersion(36, Reflection.getNMSClassStatic("EntityVindicator"), "Vindicator");
            ILLUSIONER = new EntityTypeVersion(37, Reflection.getNMSClassStatic("EntityIllagerIllusioner"), "Illusioner");
            CREEPER = new EntityTypeVersion(50, Reflection.getNMSClassStatic("EntityCreeper"), "Creeper");
            SKELETON = new EntityTypeVersion(51, Reflection.getNMSClassStatic("EntitySkeleton"), "Skeleton");
            SPIDER = new EntityTypeVersion(52, Reflection.getNMSClassStatic("EntitySpider"), "Spider");
            GIANT = new EntityTypeVersion(53, Reflection.getNMSClassStatic("EntityGiantZombie"), "Giant");
            ZOMBIE = new EntityTypeVersion(54, Reflection.getNMSClassStatic("EntityZombie"), "Zombie");
            SLIME = new EntityTypeVersion(55, Reflection.getNMSClassStatic("EntitySlime"), "Slime");
            GHAST = new EntityTypeVersion(56, Reflection.getNMSClassStatic("EntityGhast"), "Ghast");
            PIG_ZOMBIE = new EntityTypeVersion(57, Reflection.getNMSClassStatic("EntityPigZombie"), "Pig Zombie");
            ENDERMAN = new EntityTypeVersion(58, Reflection.getNMSClassStatic("EntityEnderman"), "Enderman");
            CAVE_SPIDER = new EntityTypeVersion(59, Reflection.getNMSClassStatic("EntityCaveSpider"), "Cave Spider");
            SILVERFISH = new EntityTypeVersion(60, Reflection.getNMSClassStatic("EntitySilverfish"), "Silverfish");
            BLAZE = new EntityTypeVersion(61, Reflection.getNMSClassStatic("EntityBlaze"), "Blaze");
            MAGMA_CUBE = new EntityTypeVersion(62, Reflection.getNMSClassStatic("EntityMagmaCube"), "Magma Cube");
            ENDER_DRAGON = new EntityTypeVersion(63, Reflection.getNMSClassStatic("EntityEnderDragon"), "Ender Dragon");
            WITHER = new EntityTypeVersion(64, Reflection.getNMSClassStatic("EntityWither"), "Wither");
            BAT = new EntityTypeVersion(65, Reflection.getNMSClassStatic("EntityBat"), "Bat");
            WITCH = new EntityTypeVersion(66, Reflection.getNMSClassStatic("EntityWitch"), "Witch");
            ENDERMITE = new EntityTypeVersion(67, Reflection.getNMSClassStatic("EntityEndermite"), "Endermite");
            GUARDIAN = new EntityTypeVersion(68, Reflection.getNMSClassStatic("EntityGuardian"), "Guardian");
            SHULKER = new EntityTypeVersion(69, Reflection.getNMSClassStatic("EntityShulker"), "Shulker");
            PIG = new EntityTypeVersion(90, Reflection.getNMSClassStatic("EntityPig"), "Pig");
            SHEEP = new EntityTypeVersion(91, Reflection.getNMSClassStatic("EntitySheep"), "Sheep");
            COW = new EntityTypeVersion(92, Reflection.getNMSClassStatic("EntityCow"), "Cow");
            CHICKEN = new EntityTypeVersion(93, Reflection.getNMSClassStatic("EntityChicken"), "Chicken");
            SQUID = new EntityTypeVersion(94, Reflection.getNMSClassStatic("EntitySquid"), "Squid");
            WOLF = new EntityTypeVersion(95, Reflection.getNMSClassStatic("EntityWolf"), "Wolf");
            MUSHROOM_COW = new EntityTypeVersion(96, Reflection.getNMSClassStatic("EntityMushroomCow"), "Mushroom Cow");
            SNOWMAN = new EntityTypeVersion(97, Reflection.getNMSClassStatic("EntitySnowman"), "Snowman");
            OCELOT = new EntityTypeVersion(98, Reflection.getNMSClassStatic("EntityOcelot"), "Ocelot");
            IRON_GOLEM = new EntityTypeVersion(99, Reflection.getNMSClassStatic("EntityIronGolem"), "Iron Golem");
            HORSE = new EntityTypeVersion(100, Reflection.getNMSClassStatic("EntityHorse"), "Horse");
            RABBIT = new EntityTypeVersion(101, Reflection.getNMSClassStatic("EntityRabbit"), "Rabbit");
            POLAR_BEAR = new EntityTypeVersion(102, Reflection.getNMSClassStatic("EntityPolarBear"), "Polar Bear");
            LLAMA = new EntityTypeVersion(103, Reflection.getNMSClassStatic("EntityLlama"), "Llama");
            PARROT = new EntityTypeVersion(105, Reflection.getNMSClassStatic("EntityParrot"), "Parrot");
            VILLAGER = new EntityTypeVersion(120, Reflection.getNMSClassStatic("EntityVillager"), "Villager");
        }
    }

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
        switch(version) {
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
        for(EntityTypeVersion etv : V1_12)
            if(etv != null)
                if(etv.getName().equals(name))
                    return etv;
        return null;
    }

}
