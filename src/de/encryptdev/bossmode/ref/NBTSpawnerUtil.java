package de.encryptdev.bossmode.ref;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class NBTSpawnerUtil extends Reflection {

    public enum NBTTagClass {

        BYTE(Reflection.getNMSClassStatic("NBTTagByte")), DOUBLE(Reflection.getNMSClassStatic("NBTTagDouble")),
        FLOAT(Reflection.getNMSClassStatic("NBTTagFloat")), INT(Reflection.getNMSClassStatic("NBTTagInt")),
        LIST(Reflection.getNMSClassStatic("NBTTagList")), LONG(Reflection.getNMSClassStatic("NBTTagLong")),
        SHORT(Reflection.getNMSClassStatic("NBTTagShort")), STRING(Reflection.getNMSClassStatic("NBTTagString")),
        COMPOUND(Reflection.getNMSClassStatic("NBTTagCompound"));

        private Class<?> nbtClass;

        NBTTagClass(Class<?> nbtClass) {
            this.nbtClass = nbtClass;
        }

        public Class<?> getNbtClass() {
            return nbtClass;
        }
    }

    public NBTSpawnerUtil() {
        super(BossMode.getInstance().getNmsVersion());
    }

    public Object getTiledEntityMobSpawner(Location blockLocation) {
        Object blockPosition = getNewInstanceConstructor(getNMSClass("BlockPosition"), new Class<?>[]{double.class, double.class, double.class},
                new Object[]{blockLocation.getX(), blockLocation.getY(), blockLocation.getZ()});

        Object world = invokeMethod(getClass("org.bukkit.craftbukkit." + getVersion().getVersion(), "CraftWorld"),
                "getHandle", blockLocation.getWorld(),
                new Class<?>[0], new Object[0]);

        Object tiledEntityMobSpawner = invokeMethod(getNMSClass("WorldServer"), "getTileEntity", world, new Class<?>[]{blockPosition.getClass()},
                new Object[]{blockPosition});
        return tiledEntityMobSpawner;
    }

    /**
     * This is a specific method for the plugin, when you would this class for your plugin, must be replaced ItemStack itemstack with your information
     *
     * @param itemStack
     */
    public void modifySpawner(ItemStack itemStack, Location location) {
        if (!itemStack.hasItemMeta())
            throw new RuntimeException("Could not create spawner, itemstack has no itemmeta");
        if (itemStack.getItemMeta().getLore() == null)
            throw new RuntimeException("Could not create spawner, itemmeta has no lore");
        if (itemStack.getItemMeta().getLore().size() < 6)
            throw new RuntimeException("Could not create spawner, the lore has no 6 lines");

        List<String> lore = itemStack.getItemMeta().getLore();

        int id = getInformationFromLore(lore, 0);
        int delay = getInformationFromLore(lore, 1);
        int maxSpawnDelay = getInformationFromLore(lore, 2);
        int minSpawnDelay = getInformationFromLore(lore, 3);
        int requiredPlayerRange = getInformationFromLore(lore, 4);
        int spawnCount = getInformationFromLore(lore, 5);
        int spawnRange = getInformationFromLore(lore, 6);

        Object tiledEntitySpawner = getTiledEntityMobSpawner(location);

        if (minSpawnDelay >= maxSpawnDelay)
            minSpawnDelay = minSpawnDelay - maxSpawnDelay <= 0 ? 20 : minSpawnDelay - maxSpawnDelay;
        if (maxSpawnDelay <= minSpawnDelay)
            maxSpawnDelay = maxSpawnDelay + (minSpawnDelay - maxSpawnDelay) + 20;

        IBoss boss = BossMode.getInstance().getBossManager().getBoss(id);

        Object nbtSpawnerTag = null;
        if (getVersion() == NMSVersion.V1_12_R1 || getVersion() == NMSVersion.V1_11_R1)
            nbtSpawnerTag = invokeMethod(tiledEntitySpawner.getClass(), "d", tiledEntitySpawner, new Class<?>[0], new Object[0]);
        else if (getVersion() == NMSVersion.V1_10_R1)
            nbtSpawnerTag = invokeMethod(tiledEntitySpawner.getClass(), "c", tiledEntitySpawner, new Class<?>[0], new Object[0]);
        else if(getVersion() == NMSVersion.V1_8_R3 || getVersion() == NMSVersion.V1_8_R2 || getVersion() == NMSVersion.V1_8_R1 || getVersion() == NMSVersion.V1_9_R1 ||
                getVersion() == NMSVersion.V1_9_R2) {
            Object packetPlayOutEntityTileData = invokeMethod(tiledEntitySpawner.getClass(), "getUpdatePacket", tiledEntitySpawner, new Class<?>[0], new Object[0]);
            nbtSpawnerTag = getField(packetPlayOutEntityTileData.getClass(), packetPlayOutEntityTileData, "c");
        }


        setNBTTagValue(nbtSpawnerTag, "SpawnRange", new NBTTagInstance<>(NBTTagClass.INT, spawnRange, int.class));
        setNBTTagValue(nbtSpawnerTag, "MaxNearbyEntities", new NBTTagInstance<>(NBTTagClass.INT, 3, int.class));
        setNBTTagValue(nbtSpawnerTag, "MinSpawnDelay", new NBTTagInstance<>(NBTTagClass.INT, minSpawnDelay, int.class));
        setNBTTagValue(nbtSpawnerTag, "MaxSpawnDelay", new NBTTagInstance<>(NBTTagClass.INT, maxSpawnDelay, int.class));
        setNBTTagValue(nbtSpawnerTag, "Delay", new NBTTagInstance<>(NBTTagClass.INT, delay == 0 || delay == -1 ? -1 : delay * 20, int.class));
        setNBTTagValue(nbtSpawnerTag, "RequiredPlayerRange", new NBTTagInstance<>(NBTTagClass.INT, requiredPlayerRange, int.class));
        setNBTTagValue(nbtSpawnerTag, "SpawnCount", new NBTTagInstance<>(NBTTagClass.INT, spawnCount, int.class));

        Object handList = createNBTTagList();
        Object armorList = createNBTTagList();

        Object mainHand = createNBTTagCompound();
        Object offHand = createNBTTagCompound();

        if (boss.getBossSettings().getWeaponMainHand() != null) {
            setNBTTagValue(mainHand, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getWeaponMainHand().getType()), String.class));
            setNBTTagValue(mainHand, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }
        if (boss.getBossSettings().getWeaponSecondHand() != null) {
            setNBTTagValue(offHand, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getWeaponSecondHand().getType()), String.class));
            setNBTTagValue(offHand, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }

        Object enchantmentsList = createNBTTagList();

        if (boss.getBossSettings().getWeaponMainHand() != null) {
            if (boss.getBossSettings().getWeaponMainHand().getItemMeta().hasEnchants()) {
                for (Enchantment ench : boss.getBossSettings().getWeaponMainHand().getItemMeta().getEnchants().keySet()) {
                    Object enchTag = createNBTTagCompound();
                    setNBTTagValue(enchTag, "id", new NBTTagInstance<>(NBTTagClass.INT, ench.getId(), int.class));
                    setNBTTagValue(enchTag, "lvl", new NBTTagInstance<>(NBTTagClass.INT, boss.getBossSettings().getWeaponMainHand().getItemMeta().getEnchantLevel(ench), int.class));
                    invokeMethod(enchantmentsList.getClass(), "add", enchantmentsList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{enchTag});
                }
            }
        }

        Object enchantmentsList0 = createNBTTagList();
        if (boss.getBossSettings().getWeaponSecondHand() != null) {
            if (boss.getBossSettings().getWeaponSecondHand().getItemMeta().hasEnchants()) {
                for (Enchantment ench : boss.getBossSettings().getWeaponSecondHand().getItemMeta().getEnchants().keySet()) {
                    Object enchTag = createNBTTagCompound();
                    setNBTTagValue(enchTag, "id", new NBTTagInstance<>(NBTTagClass.INT, ench.getId(), int.class));
                    setNBTTagValue(enchTag, "lvl", new NBTTagInstance<>(NBTTagClass.INT, boss.getBossSettings().getWeaponSecondHand().getItemMeta().getEnchantLevel(ench), int.class));
                    invokeMethod(enchantmentsList0.getClass(), "add", enchantmentsList0, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{enchTag});
                }
            }
        }

        Object nbtEnch = createNBTTagCompound();
        setNBTTagObject(nbtEnch, "ench", enchantmentsList);
        setNBTTagObject(mainHand, "tag", nbtEnch);
        invokeMethod(handList.getClass(), "add", handList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{mainHand});

        Object nbtEnch0 = createNBTTagCompound();
        setNBTTagObject(nbtEnch0, "ench", enchantmentsList0);
        setNBTTagObject(offHand, "tag", nbtEnch0);
        invokeMethod(handList.getClass(), "add", handList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{offHand});

        Object[] armor = createNBTArmor(boss);
        invokeMethod(armorList.getClass(), "add", armorList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{armor[0]});
        invokeMethod(armorList.getClass(), "add", armorList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{armor[1]});
        invokeMethod(armorList.getClass(), "add", armorList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{armor[2]});
        invokeMethod(armorList.getClass(), "add", armorList, new Class<?>[]{getNMSClass("NBTBase")}, new Object[]{armor[3]});

        Object spawnData = createNBTTagCompound();
        setNBTTagValue(spawnData, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftEntityID(boss.getEntityType()), String.class));
        setNBTTagObject(spawnData, "HandItems", handList);
        setNBTTagObject(spawnData, "ArmorItems", armorList);

        setNBTTagObject(nbtSpawnerTag, "SpawnData", spawnData);

        if (getVersion() == NMSVersion.V1_12_R1) {
            invokeMethod(tiledEntitySpawner.getClass(), "load", tiledEntitySpawner, new Class<?>[]{getNMSClass("NBTTagCompound")}, new Object[]{nbtSpawnerTag});
        } else if (getVersion() == NMSVersion.V1_11_R1 || getVersion() == NMSVersion.V1_10_R1 || getVersion() == NMSVersion.V1_9_R2 || getVersion() == NMSVersion.V1_9_R1 ||
                getVersion() == NMSVersion.V1_8_R1 || getVersion() == NMSVersion.V1_8_R2 || getVersion() == NMSVersion.V1_8_R3) {
            invokeMethod(tiledEntitySpawner.getClass(), "a", tiledEntitySpawner, new Class<?>[]{getNMSClass("NBTTagCompound")}, new Object[]{nbtSpawnerTag});
        }else {
            throw new RuntimeException("Can not found method 'a' or 'load'");
        }
    }

    /**
     * index 0 = helmet
     * index 1 = chestplate
     * index 2 = leggings
     * index 3 = boots
     *
     * @param boss
     * @return
     */
    public Object[] createNBTArmor(IBoss boss) {
        Object[] armor = new Object[4];

        Object nbtHelmet = createNBTTagCompound();
        Object nbtChestplate = createNBTTagCompound();
        Object nbtLeggings = createNBTTagCompound();
        Object nbtBoots = createNBTTagCompound();

        if (boss.getBossSettings().getArmor()[0] != null) {
            setNBTTagValue(nbtHelmet, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getArmor()[0].getType()), String.class));
            setNBTTagValue(nbtHelmet, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }
        if (boss.getBossSettings().getArmor()[1] != null) {
            setNBTTagValue(nbtChestplate, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getArmor()[1].getType()), String.class));
            setNBTTagValue(nbtChestplate, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }
        if (boss.getBossSettings().getArmor()[2] != null) {
            setNBTTagValue(nbtLeggings, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getArmor()[2].getType()), String.class));
            setNBTTagValue(nbtLeggings, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }
        if (boss.getBossSettings().getArmor()[3] != null) {
            setNBTTagValue(nbtBoots, "id", new NBTTagInstance<>(NBTTagClass.STRING, getMinecraftID(boss.getBossSettings().getArmor()[3].getType()), String.class));
            setNBTTagValue(nbtBoots, "Count", new NBTTagInstance<>(NBTTagClass.INT, 1, int.class));
        }

        armor[0] = nbtHelmet;
        armor[1] = nbtChestplate;
        armor[2] = nbtLeggings;
        armor[3] = nbtBoots;

        return armor;
    }

    public void setNBTTagValue(Object nbtTag, String nbtName, NBTTagInstance<?> instance) {
        invokeMethod(getNMSClass("NBTTagCompound"), "set", nbtTag, new Class<?>[]{String.class, getNMSClass("NBTBase")},
                new Object[]{nbtName, instance.getInstance()});
    }

    public void setNBTTagObject(Object nbtTag, String nbtName, Object nbtObject) {
        invokeMethod(getNMSClass("NBTTagCompound"), "set", nbtTag, new Class<?>[]{String.class, getNMSClass("NBTBase")},
                new Object[]{nbtName, nbtObject});
    }

    private int getInformationFromLore(List<String> lore, int index) {
        return Integer.parseInt(lore.get(index).split(":")[1].trim());
    }

    public Object createNBTTagList() {
        return new NBTTagInstance<>(NBTTagClass.LIST, null, null).getNBTList();
    }

    public Object createNBTTagCompound() {
        return new NBTTagInstance<>(NBTTagClass.COMPOUND, null, null).getNBTTagCompound();
    }

    public String getMinecraftID(Material material) {
        String result = "minecraft:";
        String normalName = BossUtil.makeEnumNameNormal(material);
        result = result + normalName.toLowerCase().replace(" ", "_");
        return result;
    }

    public String getMinecraftEntityID(EntityType type) {
        String enumNormal = BossUtil.makeEnumNameNormal(type);
        enumNormal = enumNormal.toLowerCase();
        return enumNormal;
    }

    private class NBTTagInstance<T> extends Reflection {

        private NBTTagClass nbtTagClass;
        private T type;
        private Class<?> primitive;

        public NBTTagInstance(NBTTagClass nbtTagClass, T type, Class<?> primitive) {
            super(BossMode.getInstance().getNmsVersion());
            this.nbtTagClass = nbtTagClass;
            this.type = type;
            this.primitive = primitive;
        }

        public Object getNBTTagCompound() {
            return nbtTagClass == NBTTagClass.COMPOUND ? getNewInstanceClass(nbtTagClass.getNbtClass()) : null;
        }

        public Object getNBTList() {
            return nbtTagClass == NBTTagClass.LIST ? getNewInstanceClass(nbtTagClass.getNbtClass()) : null;
        }

        public Object getInstance() {
            return getNewInstanceConstructor(nbtTagClass.getNbtClass(), new Class<?>[]{primitive}, new Object[]{type});
        }
    }

}
