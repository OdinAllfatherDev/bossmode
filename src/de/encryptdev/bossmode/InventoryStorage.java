package de.encryptdev.bossmode;

import de.encryptdev.bossmode.boss.mount.MountType;
import de.encryptdev.bossmode.boss.util.BossEditor;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.boss.util.EntityTypeVersion;
import de.encryptdev.bossmode.ref.Reflection;
import de.encryptdev.bossmode.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class store every inventory from the plugin
 * <p>
 * Created by EncryptDev
 */
public class InventoryStorage {

    public InventoryStorage() {

    }

    public Inventory changeEntityType() {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eSet the entity type");

        List<ItemStack> items = new ArrayList<>();

        for (EntityTypeVersion etv : EntityTypeVersion.BOSS_MONSTER)
            items.add(ItemCreator.getItem(Material.MONSTER_EGG, "§a§l" + etv.getName(), 1, (byte) etv.getId()));

        for (int i = 0; i < items.size(); i++)
            inventory.setItem(i, items.get(i));

        return inventory;
    }

    public Inventory newOrEditBoss() {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eNEW BOSS | CHANGE BOSS");

        fillInventory(inventory);

        ItemStack newBoss = ItemCreator.getItem(Material.FEATHER,
                "§5§lNew boss", 1, (byte) 0, Arrays.asList("§eCreate a new boss"));
        ItemStack editBoss = ItemCreator.getItem(Material.STICK,
                "§5§lEdit boss", 1, (byte) 0, Arrays.asList("§eEdit a boss"));

        ItemStack spawner = ItemCreator.getItem(Material.MOB_SPAWNER, "§eSpawner", 1, (byte) 0,
                Arrays.asList("§eCreate a new spawner block for the boss"));

        inventory.setItem(3, newBoss);
        inventory.setItem(4, spawner);
        inventory.setItem(5, editBoss);

        return inventory;
    }

    public Inventory bossSettings(boolean naturalSpawnB) {
        Inventory inventory = Bukkit.createInventory(null, 54, "§eBoss Settings");

        fillInventory(inventory);

        ItemStack health = ItemCreator.getItem(Material.APPLE, "§2Health", 1, (byte) 0,
                Arrays.asList("§eChange the max health", "§eMax: 2048"));
        ItemStack damage = ItemCreator.getItem(Material.DIAMOND_SWORD, "§4Damage", 1, (byte) 0,
                Arrays.asList("§eChange the damage"), ItemFlag.HIDE_ATTRIBUTES);

        ItemStack equipment = ItemCreator.getItem(Material.IRON_CHESTPLATE, "§bEquipment", 1, (byte) 0,
                Arrays.asList("§eSet the equipment"), ItemFlag.HIDE_ATTRIBUTES);


        ItemStack naturalDrops = ItemCreator.getItem(Material.ROTTEN_FLESH, "§6Drops", 1, (byte) 0,
                Arrays.asList("§eSet the drops for the boss"));

        ItemStack name = ItemCreator.getItem(Material.NAME_TAG, "§eName", 1, (byte) 0,
                Arrays.asList("§eSet the name"));

        ItemStack finishBuild = ItemCreator.getItem(Material.DIAMOND, "§eFinish", 1, (byte) 0,
                Arrays.asList("§eIf you finish, click here"));

        ItemStack speed = ItemCreator.getItem(Material.FEATHER, "§eWalk Speed", 1, (byte) 0,
                Arrays.asList("§eChange the Walk speed", "§eDefault: 1.4"));

        ItemStack naturalSpawn;
        if (naturalSpawnB) {
            naturalSpawn = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 10,
                    Arrays.asList("§eNatural Drops: §2ON"));
        } else {
            naturalSpawn = ItemCreator.getItem(Material.INK_SACK, "§2Natural Spawn", 1, (byte) 8,
                    Arrays.asList("§eNatural Drops: §4OFF"));
        }

        ItemStack exp = ItemCreator.getItem(Material.EXP_BOTTLE, "§aEXP", 1, (byte) 0,
                Arrays.asList("§eSet the exp, where the boss drop"));

        ItemStack specialAttacks;
        if (BossUtil.is1_8() || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_9_R1 ||
                BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_9_R2)
            specialAttacks = ItemCreator.getItem(Material.GOLDEN_CARROT, "§aSpecial Attack", 1, (byte) 0,
                    Arrays.asList("§eSet the special attacks"));
        else
            specialAttacks = ItemCreator.getItem(Material.TOTEM, "§aSpecial Attack", 1, (byte) 0,
                    Arrays.asList("§eSet the special attacks"));


        ItemStack spawnLocation = ItemCreator.getItem(Material.BED, "§bSpawn Location", 1, (byte) 0,
                Arrays.asList("§eSet the spawn location", "§eNOTE: If natural Spawn is on", "§ethen the spawn location is null"));

        ItemStack potionEffects = ItemCreator.getItem(Material.GLASS_BOTTLE, "§ePotion Effects", 1, (byte) 0,
                Arrays.asList("§eAdd potion effects"));

        ItemStack advanced = ItemCreator.getItem(Material.REDSTONE, "§4Advanced Settings", 1, (byte) 0,
                Arrays.asList("§eSet advanced settings for the boss"));

        if (naturalSpawnB) {
            ItemStack item = ItemCreator.getItem(Material.GRASS, "§aBiome", 1, (byte) 0,
                    Arrays.asList("§eSet the biome who the boss spawn"));

            ItemStack item2 = ItemCreator.getItem(Material.GHAST_TEAR, "§aChance to spawn", 1, (byte) 0,
                    Arrays.asList("§eSet the chance to spawn", "§eMin: 1", "§eMax: 100"));

            ItemStack item3 = ItemCreator.getItem(Material.ARROW, "§eSpawn Amount", 1, (byte) 0,
                    Arrays.asList("§eSet the maximal spawn amount"));

            inventory.setItem(42, item);
            inventory.setItem(43, item2);
            inventory.setItem(44, item3);
        }

        ItemStack spawnRadius = ItemCreator.getItem(Material.FENCE, "§eSpawn Radius", 1, (byte) 0,
                Arrays.asList("§eSet the radius, where the player must be to spawn the boss"));

        ItemStack nearbyRad = ItemCreator.getItem(Material.GHAST_TEAR, "§eNearby Radius", 1, (byte) 0,
                Arrays.asList("§eSet the radius where the boss follow player"));

        ItemStack mount = ItemCreator.getItem(Material.SADDLE, "§eMount", 1, (byte) 0,
                Arrays.asList("§eSet the mount"));

        inventory.setItem(8, advanced);

        inventory.setItem(13, specialAttacks);
        inventory.setItem(14, spawnRadius);
        inventory.setItem(15, health);
        inventory.setItem(16, damage);
        inventory.setItem(17, speed);

        inventory.setItem(10, name);
        inventory.setItem(19, equipment);
        inventory.setItem(20, mount);
        inventory.setItem(28, potionEffects);
        inventory.setItem(37, exp);

        inventory.setItem(22, nearbyRad);
        inventory.setItem(34, spawnLocation);
        inventory.setItem(40, naturalDrops);
        inventory.setItem(41, naturalSpawn);

        inventory.setItem(53, finishBuild);

        return inventory;
    }

    public Inventory equipment() {
        Inventory inventory = Bukkit.createInventory(null, 27, "§bEquipment");

        fillInventory(inventory);

        ItemStack armorHelmet = ItemCreator.getItem(Material.DIAMOND_HELMET, "§bArmor - Helmet", 1, (byte) 0,
                Arrays.asList("§eSet the helmet, for the boss", " ", "  ", "§eEmpty inventory = no helmet"), ItemFlag.HIDE_ATTRIBUTES);
        ItemStack armorChestplate = ItemCreator.getItem(Material.DIAMOND_CHESTPLATE, "§bArmor - Chestplate", 1, (byte) 0,
                Arrays.asList("§eSet the chestplate, for the boss", " ", "  ", "§eEmpty inventory = no chestplate"), ItemFlag.HIDE_ATTRIBUTES);
        ItemStack armorLeggings = ItemCreator.getItem(Material.DIAMOND_LEGGINGS, "§bArmor - Leggings", 1, (byte) 0,
                Arrays.asList("§eSet the leggings, for the boss", " ", "  ", "§eEmpty inventory = no leggings"), ItemFlag.HIDE_ATTRIBUTES);
        ItemStack armorBoots = ItemCreator.getItem(Material.DIAMOND_BOOTS, "§bArmor - Boots", 1, (byte) 0,
                Arrays.asList("§eSet the boots, for the boss", " ", "  ", "§eEmpty inventory = no boots"), ItemFlag.HIDE_ATTRIBUTES);
        ItemStack mainHand = ItemCreator.getItem(Material.GOLD_AXE, "§aMain Hand", 1, (byte) 0,
                Arrays.asList("§eChange the main hand item"), ItemFlag.HIDE_ATTRIBUTES);
        ItemStack offHand = ItemCreator.getItem(Material.GOLD_HOE, "§aOff Hand", 1, (byte) 0,
                Arrays.asList("§eChange the off hand item"), ItemFlag.HIDE_ATTRIBUTES);

        ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowDown"));

        inventory.setItem(10, armorHelmet);
        inventory.setItem(11, armorChestplate);
        inventory.setItem(12, armorLeggings);
        inventory.setItem(13, armorBoots);
        inventory.setItem(15, mainHand);
        inventory.setItem(16, offHand);
        inventory.setItem(26, back);

        return inventory;
    }

    public Inventory openPutContentInventory(PutType type) {
        Inventory inventory = Bukkit.createInventory(null, 9, type.getInvName());
        if(type != PutType.DROPS)
            fillInventoryWithoutOne(inventory);
        inventory.setItem(8, ItemCreator.getItem(Material.DIAMOND, "§6§lSet"));
        return inventory;
    }

    public Inventory spawnerInventory() {
        Inventory inventory = Bukkit.createInventory(null, 36, "§eSpawner Settings");

        fillInventory(inventory);

        ItemStack delay = ItemCreator.getItem(Material.WATCH, "§eDelay", 1, (byte) 0,
                Arrays.asList("§eSet the delay in seconds",
                        "§eDefault: 1",
                        "§eWhen its -1 then is a random value,", "§ebetween 'minSpawnDelay' and 'maxSpawnDelay'"));

        ItemStack minSpawnDelay = ItemCreator.getItem(Material.INK_SACK, "§eMin Spawn Delay", 1, (byte) 12,
                Arrays.asList("§eSet the min spawn delay in seconds",
                        "§eDefault: 10"));
        ItemStack maxSpawnDelay = ItemCreator.getItem(Material.INK_SACK, "§eMax Spawn Delay", 1, (byte) 12,
                Arrays.asList("§eSet the max spawn delay in ticks seconds",
                        "§eDefault: 40"));

        ItemStack requiredPlayerRange = ItemCreator.getItem(Material.LEASH, "§eRequired Player Range", 1, (byte) 0,
                Arrays.asList("§eSet the maximum distance (squared)", "a player can be in order for this spawner to be active",
                        "§eDefault: 16"));

        ItemStack spawnCount = ItemCreator.getItem(Material.BONE, "§eSpawn Count", 1, (byte) 0,
                Arrays.asList("§eSet the spawn count",
                        "§eDefault: 4"));

        ItemStack spawnRange = ItemCreator.getItem(Material.ARROW, "§eSpawn Range", 1, (byte) 0,
                Arrays.asList("§eSet the spawn range (squared) for the spawner",
                        "§eDefault: 4"));

        ItemStack finish = ItemCreator.getItem(Material.DIAMOND, "§eFinish", 1, (byte) 0,
                Arrays.asList("§eIf you finish, then click here"));


        inventory.setItem(12, minSpawnDelay);
        inventory.setItem(13, delay);
        inventory.setItem(14, maxSpawnDelay);

        inventory.setItem(21, requiredPlayerRange);
        inventory.setItem(22, spawnCount);
        inventory.setItem(23, spawnRange);

        inventory.setItem(35, finish);

        return inventory;
    }

    public Inventory getBiomeInventory(int side) {
        Inventory inventory = Bukkit.createInventory(null, 36, "§eBiome " + side);

        List<ItemStack> biomeItems = new ArrayList<>();

        for (Biome b : Biome.values()) {
            if (b == Biome.VOID)
                continue;
            biomeItems.add(ItemCreator.getItem(Material.GRASS, "§a§l" + b.toString()));
        }

        if (side == 1) {

            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, biomeItems.get(i));
            }

            ItemStack next = ItemCreator.getSkull("§eNext", Bukkit.getOfflinePlayer("MHF_ArrowRight"));
            ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowLeft"));
            inventory.setItem(27, back);
            inventory.setItem(35, next);
        } else if (side == 2) {

            List<ItemStack> subList = biomeItems.subList(27, 54);

            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, subList.get(i));
            }

            ItemStack next = ItemCreator.getSkull("§eNext", Bukkit.getOfflinePlayer("MHF_ArrowRight"));
            ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowLeft"));

            inventory.setItem(35, next);
            inventory.setItem(27, back);

        } else if (side == 3) {
            List<ItemStack> subList = biomeItems.subList(55, biomeItems.size() - 1);
            ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowLeft"));

            for (int i = 0; i < subList.size(); i++) {
                inventory.setItem(i, subList.get(i));
            }

            inventory.setItem(27, back);
        }

        return inventory;
    }

    public Inventory specialAttacks() {
        Inventory inventory = Bukkit.createInventory(null, 9, "§5Special Attacks");

        fillInventory(inventory);

        ItemStack message = ItemCreator.getItem(Material.PAPER, "§bMessages", 1, (byte) 0,
                Arrays.asList("§eThe boss send messages to the near players"));
        ItemStack teleport = ItemCreator.getItem(Material.ENDER_PEARL, "§5Teleport", 1, (byte) 0,
                Arrays.asList("§eThe boss teleport to a random player in the near"));
        ItemStack stomp = ItemCreator.getItem(Material.DIAMOND_BOOTS, "§eStomp", 1, (byte) 0,
                Arrays.asList("§eStomp!"), ItemFlag.HIDE_ENCHANTS);
        ItemStack army = ItemCreator.getItem(Material.ARMOR_STAND, "§eArmy", 1, (byte) 0,
                Arrays.asList("§eThe boss spawn helper"));

        inventory.setItem(0, message);
        inventory.setItem(1, teleport);
        inventory.setItem(2, stomp);
        inventory.setItem(3, army);

        return inventory;
    }

    public Inventory potionEffects() {
        Inventory inventory = Bukkit.createInventory(null, 36, "§ePotion Effects");

        fillInventory(inventory);

        ItemStack regeneration = ItemCreator.getItem(Material.POTION, "§eRegeneration", 1, (byte) 8193, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack swifftness = ItemCreator.getItem(Material.POTION, "§eSwiftness", 1, (byte) 8194, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack fireRessistence = ItemCreator.getItem(Material.POTION, "§eFire Resistance", 1, (byte) 8195, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack healing = ItemCreator.getItem(Material.POTION, "§eHealing", 1, (byte) 8191, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack poison = ItemCreator.getItem(Material.POTION, "§ePoison", 1, (byte) 8198,
                Arrays.asList("§eIf the boss have the poision effect,", "§eand the boss hit a player then", "§ethe player became poison"),
                ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack strength = ItemCreator.getItem(Material.POTION, "§eStrength", 1, (byte) 8201, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack slowness = ItemCreator.getItem(Material.POTION, "§eSlowness", 1, (byte) 8202, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack invisibility = ItemCreator.getItem(Material.POTION, "§eInvisibility", 1, (byte) 8206, null, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack blindness = ItemCreator.getItem(Material.POTION, "§eBlindness", 1, (byte) 8207, Arrays.asList("§eIf the boss have the blindness effect",
                "§eand the boss hit a player then", "§ethe player became blindness"), ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack nausea = ItemCreator.getItem(Material.POTION, "§eNausea", 1, (byte) 8207, Arrays.asList("§eIf the boss have the nausea effect",
                "§eand the boss hit a player then", "§ethe player became nausea"), ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack hunger = ItemCreator.getItem(Material.POTION, "§eHunger", 1, (byte) 8207, Arrays.asList("§eIf the boss have the hunger effect",
                "§eand the boss hit a player then", "§ethe player became hunger"), ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
        ItemStack wither = ItemCreator.getItem(Material.POTION, "§eWither", 1, (byte) 8209, Arrays.asList("§eIf the boss have the wither effect",
                "§eand the boss hit a player then", "§ethe player became wither"), ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);

        ItemStack clear = ItemCreator.getItem(Material.BARRIER, "§eClear", 1, (byte) 0,
                Arrays.asList("§eRemove all potion effects"));

        ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowDown"));

        inventory.setItem(10, nausea);
        inventory.setItem(11, blindness);
        inventory.setItem(12, regeneration);
        inventory.setItem(13, swifftness);
        inventory.setItem(14, fireRessistence);
        inventory.setItem(15, wither);
        inventory.setItem(19, hunger);
        inventory.setItem(20, healing);
        inventory.setItem(21, strength);
        inventory.setItem(22, poison);
        inventory.setItem(23, slowness);
        inventory.setItem(24, invisibility);

        inventory.setItem(31, clear);
        inventory.setItem(35, back);

        return inventory;
    }

    private void fillInventory(Inventory inventory) {
        ItemStack filler = ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15);

        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, filler);
    }

    private void fillInventoryWithoutOne(Inventory inventory) {
        ItemStack filler = ItemCreator.getItem(Material.STAINED_GLASS_PANE, "§0", 1, (byte) 15);

        for (int i = 1; i < inventory.getSize(); i++)
            inventory.setItem(i, filler);
    }

    public Inventory potionEffectSettings(String potionEffect) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eSettings | " + potionEffect);

        fillInventory(inventory);

        ItemStack tierOne = ItemCreator.getItem(Material.GLOWSTONE_DUST, "§eTier 1", 1, (byte) 0,
                Arrays.asList("§eClick if you want tier 1"));

        ItemStack tierTwo = ItemCreator.getItem(Material.GLOWSTONE_DUST, "§eTier 2", 1, (byte) 0,
                Arrays.asList("§eClick if you want tier 2"));
        ItemStack exit = ItemCreator.getItem(Material.BARRIER, "§eBack", 1, (byte) 0,
                Arrays.asList("§eClick if you do not want tier 2"));

        inventory.setItem(3, tierOne);
        inventory.setItem(5, tierTwo);
        inventory.setItem(8, exit);

        return inventory;
    }

    public Inventory advancedSettings(BossEditor be) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§4Advanced Settings");
        fillInventory(inventory);

        ItemStack nearEntity = ItemCreator.getItem(Material.SKULL_ITEM, "§eAttack Entity", 1, (byte) 2,
                Arrays.asList("§eChange entities, where the boss attack when the entities in the near"));
        ItemStack lookAtPlayer = ItemCreator.getItem(Material.EYE_OF_ENDER, "§eLook at player", 1, (byte) 0,
                Arrays.asList(be.isLookAtPlayer() ? "§2ON" : "§4OFF", " ", "§eIf this is on, then the boss look always to the player"));

        ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowDown"));

        inventory.setItem(0, nearEntity);
        inventory.setItem(1, lookAtPlayer);
        inventory.setItem(8, back);

        return inventory;
    }

    public Inventory entityTypesInventory(int side) {
        Inventory inventory = Bukkit.createInventory(null, 45, "§4Entity Type #" + side);

        List<ItemStack> items = new ArrayList<>();

        Reflection.NMSVersion version = BossMode.getInstance().getNmsVersion();

        EntityTypeVersion[] types = EntityTypeVersion.getVersionArray(version);
        for (EntityTypeVersion etv : types)
            items.add(ItemCreator.getItem(Material.MONSTER_EGG, "§e" + etv.getName(), 1, (byte) etv.getId()));

        ItemStack next = ItemCreator.getSkull("§eNext", Bukkit.getOfflinePlayer("MHF_ArrowRight"));
        ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowLeft"));

        ItemStack finish = ItemCreator.getItem(Material.DIAMOND, "§eFinish", 1, (byte) 0, Arrays.asList("§eClick if you are finish"));

        if ((items.size() == 32 || items.size() == 33 || items.size() == 34) && side == 1) {
            for (int i = 0; i < items.size(); i++)
                inventory.setItem(i, items.get(i));
        } else if (items.size() == 47 || items.size() == 49) {
            if (side == 1) {
                for (int i = 0; i < 36; i++)
                    inventory.setItem(i, items.get(i));
                inventory.setItem(44, next);
            } else if (side == 2) {
                int slot = 0;
                for (int i = 36; i < items.size(); i++) {
                    inventory.setItem(slot, items.get(i));
                    slot++;
                }
                inventory.setItem(36, back);
            }
        }

        inventory.setItem(40, finish);

        return inventory;
    }

    public Inventory counterInventory(CounterType counterType, double value) {
        Inventory inventory = Bukkit.createInventory(null, 27, counterType.getInventoryName());

        fillInventory(inventory);

        if (counterType.isDecimal()) {
            ItemStack minus0_1 = ItemCreator.getItem(Material.ARROW, "§e-0.1");
            ItemStack minus0_5 = ItemCreator.getItem(Material.ARROW, "§e-0.5");
            ItemStack minus1 = ItemCreator.getItem(Material.ARROW, "§e-1");
            ItemStack plus0_1 = ItemCreator.getItem(Material.ARROW, "§e+0.1");
            ItemStack plus0_5 = ItemCreator.getItem(Material.ARROW, "§e+0.5");
            ItemStack plus1 = ItemCreator.getItem(Material.ARROW, "§e+1");
            ItemStack ct = ItemCreator.getItem(Material.GOLD_NUGGET, counterType.getInventoryName() + ": §a§l" + (value == -1 ? counterType.getDefaultValue() : value));

            inventory.setItem(10, minus0_1);
            inventory.setItem(11, minus0_5);
            inventory.setItem(12, minus1);
            inventory.setItem(13, ct);
            inventory.setItem(14, plus0_1);
            inventory.setItem(15, plus0_5);
            inventory.setItem(16, plus1);
        } else {
            ItemStack minus1 = ItemCreator.getItem(Material.ARROW, "§e-1");
            ItemStack minus5 = ItemCreator.getItem(Material.ARROW, "§e-5");
            ItemStack minus10 = ItemCreator.getItem(Material.ARROW, "§e-10");
            ItemStack minus50 = ItemCreator.getItem(Material.ARROW, "§e-50");
            ItemStack plus1 = ItemCreator.getItem(Material.ARROW, "§e+1");
            ItemStack plus5 = ItemCreator.getItem(Material.ARROW, "§e+5");
            ItemStack plus10 = ItemCreator.getItem(Material.ARROW, "§e+10");
            ItemStack plus50 = ItemCreator.getItem(Material.ARROW, "§e+50");
            ItemStack ct = ItemCreator.getItem(Material.GOLD_NUGGET, counterType.getInventoryName() + ": §a§l" + (value == -1 ? counterType.getDefaultValue() : value));
            inventory.setItem(9, minus1);
            inventory.setItem(10, minus5);
            inventory.setItem(11, minus10);
            inventory.setItem(12, minus50);
            inventory.setItem(13, ct);
            inventory.setItem(14, plus1);
            inventory.setItem(15, plus5);
            inventory.setItem(16, plus10);
            inventory.setItem(17, plus50);
        }

        ItemStack finish = ItemCreator.getItem(Material.DIAMOND, "§eFinish");

        inventory.setItem(26, finish);

        return inventory;
    }

    public Inventory mountTypes() {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eMountTypes");

        ItemStack back = ItemCreator.getSkull("§eBack", Bukkit.getOfflinePlayer("MHF_ArrowDown"));
        ItemStack reset = ItemCreator.getItem(Material.BARRIER, "§4Reset", 1, (byte) 0, Arrays.asList("§eReset the mount"));

        List<ItemStack> items = new ArrayList<>();

        for (MountType mt : MountType.values())
            items.add(ItemCreator.getItem(Material.MONSTER_EGG, "§a" + BossUtil.makeEnumNameNormal(mt), 1, (byte) mt.getEntityType().getTypeId()));

        for (int i = 0; i < items.size(); i++)
            inventory.setItem(i, items.get(i));

        inventory.setItem(7, reset);
        inventory.setItem(8, back);

        return inventory;
    }

    public enum CounterType {

        HEALTH("§eHealth", false, 20.0),
        DAMAGE("§eDamage", true, 7.0),
        DROP_CHANCE_MAIN_HAND("§eDrop Chance Main Hand", false, 1.0),
        DROP_CHANCE_OFF_HAND("§eDrop Chance Off Hand", false, 1.0),
        CHANCE_TO_SPAWN("§eChance To Spawn", false, 1.0),
        SPEED("§eSpeed", true, 1.4),
        SPAWN_AMOUNT("§eSpawn Amount", false, 10.0),
        NEARBY_RADIUS("§eNearby Radius", false, 100.0),
        SPAWN_RADIUS("§eSpawn Raidus", false, 5.0),
        SPECIAL_ATTACK_STOMP_STRENGTH("§eSpecialAttack Stomp Strength", false, 10.0),
        SPAWNER_DELAY("§4Spawner Delay", false, 20.0),
        SPAWNER_MIN_DELAY("§4Spawner Min Delay", false, 200.0),
        SPAWNER_MAX_DELAY("§4Spawner Max Delay", false, 800.0),
        SPAWNER_REQUIRED_PLAYERS_RANGE("§4Spawner Required Players Range", false, 16.0),
        SPAWNER_SPAWN_RANGE("§4Spawner Spawn Range", false, 4.0),
        SPAWNER_SPAWN_COUNT("§4Spawner Spawn Count", false, 4.0),
        DROPPED_XP("§eDropped XP", false, 100.0),
        SPECIAL_ATTACK_ARMY_CHANCE("§eSpecialAttack Army Chance", false, 10.0),
        SPECIAL_ATTACK_ARMY_AMOUNT("§eSpecialAttack Army Amount", false, 5.0),
        MOUNT_HEALTH_PIG("§4MountSettings | §aPig", false, 20.0),
        MOUNT_HEALTH_COW("§4MountSettings | §aCow", false, 20.0),
        MOUNT_HEALTH_BAT("§4MountSettings | §aBat", false, 20.0);

        private String inventoryName;
        private boolean isDecimal;
        private double defaultValue;

        CounterType(String inventoryName, boolean isDecimal, double defaultValue) {
            this.inventoryName = inventoryName;
            this.isDecimal = isDecimal;
            this.defaultValue = defaultValue;
        }

        public double getDefaultValue() {
            return defaultValue;
        }

        public boolean isDecimal() {
            return isDecimal;
        }

        public String getInventoryName() {
            return inventoryName;
        }

    }


    public enum PutType {

        ARMOR_HELMET("§bArmor - Helmet"), ARMOR_CHESTPLATE("§bArmor - Chestplate"),
        ARMOR_LEGGINGS("§bArmor - Leggings"), ARMOR_BOOTS("§bArmor - Boots"),
        MAIN_HAND("§aMain Hand"), OFF_HAND("§aOff Hand"), DROPS("§6Drops");

        private String invName;

        PutType(String invName) {
            this.invName = invName;
        }

        public String getInvName() {
            return invName;
        }
    }

}
