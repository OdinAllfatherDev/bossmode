package de.encryptdev.bossmode.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.ref.Reflection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class BossBarV1_8 extends BukkitRunnable {

    private Reflection ref;
    private String title;
    private Map<Player, Object> withers;

    public BossBarV1_8(BossMode bm, String title) {
        this.ref = new Reflection(BossMode.getInstance().getNmsVersion());
        this.withers = new HashMap<>();
        this.title = title;
        runTaskTimer(bm, 0, 10);
    }

    public void addPlayer(Player p) {

        Object world = ref.invokeMethod(ref.getClass("org.bukkit.craftbukkit." + ref.getVersion().getVersion(), "CraftWorld"), "getHandle",
                p.getWorld(), new Class<?>[0], new Object[0]);

        Object wither = ref.getNewInstanceConstructor(Reflection.getNMSClassStatic("EntityWither"), new Class<?>[]{ref.getNMSClass("World")}, new Object[]{world});
        Location l = getWitherLocation(p.getLocation());

        ref.invokeMethod(ref.getNMSClass("Entity"), "setCustomName", wither, new Class<?>[]{String.class}, new Object[]{title});
        ref.invokeMethod(ref.getNMSClass("Entity"), "setInvisible", wither, new Class<?>[]{boolean.class}, new Object[]{true});
        ref.invokeMethod(ref.getNMSClass("Entity"), "setLocation", wither, new Class<?>[]{double.class, double.class, double.class, float.class, float.class},
                new Object[]{l.getX(), l.getY(), l.getZ(), 0, 0});

        Object packet = ref.getNewInstanceConstructor(ref.getNMSClass("PacketPlayOutSpawnEntityLiving"), new Class<?>[]{ref.getNMSClass("EntityLiving")},
                new Object[]{wither});
        ref.sendPacket(p, packet);
        withers.put(p, wither);
    }

    public void removePlayer(Player p) {
        Object intArray = Array.newInstance(int.class, 1);
        Array.set(intArray, 0, getId(p));

        Object packet = ref.getNewInstanceConstructor(ref.getNMSClass("PacketPlayOutEntityDestroy"), new Class<?>[]{intArray.getClass()}, new Object[]{intArray});
        ref.sendPacket(p, packet);
        withers.remove(p);
    }

    private int getId(Player player) {
        return (int) ref.invokeMethod(ref.getNMSClass("Entity"), "getId", withers.get(player), new Class<?>[0], new Object[0]);
    }


    public void setProgress(double progress) {
        for (Map.Entry<Player, Object> entry : withers.entrySet()) {
            Object wither = entry.getValue();

            ref.invokeMethod(ref.getNMSClass("EntityLiving"), "setHealth", wither, new Class<?>[]{float.class},
                    new Object[]{(float) (progress)});

            Object dataWatcher = ref.invokeMethod(ref.getNMSClass("Entity"), "getDataWatcher", wither, new Class<?>[0], new Object[0]);

            Object packet = ref.getNewInstanceConstructor(ref.getNMSClass("PacketPlayOutEntityMetadata"), new Class<?>[]{int.class, ref.getNMSClass("DataWatcher"),
                    boolean.class}, new Object[]{getId(entry.getKey()), dataWatcher, true});
            ref.sendPacket(entry.getKey(), packet);
        }
    }

    public Location getWitherLocation(Location l) {
        return l.add(l.getDirection().multiply(60));
    }

    public void clear() {
        for (Map.Entry<Player, Object> en : withers.entrySet()) {
            Object intArray = Array.newInstance(int.class, 1);
            Array.set(intArray, 0, getId(en.getKey()));

            Object packet = ref.getNewInstanceConstructor(ref.getNMSClass("PacketPlayOutEntityDestroy"), new Class<?>[]{intArray.getClass()}, new Object[]{intArray});
            ref.sendPacket(en.getKey(), packet);
        }
        withers.clear();
        cancel();
    }

    @Override
    public void run() {
        for (Map.Entry<Player, Object> en : withers.entrySet()) {
            Object wither = en.getValue();

            Location l = getWitherLocation(en.getKey().getLocation());

            ref.invokeMethod(ref.getNMSClass("Entity"), "setLocation", wither, new Class<?>[]{double.class, double.class, double.class, float.class, float.class},
                    new Object[]{l.getX(), l.getY(), l.getZ(), 0, 0});

            Object packet = ref.getNewInstanceConstructor(ref.getNMSClass("PacketPlayOutEntityTeleport"), new Class<?>[]{ref.getNMSClass("Entity")}, new Object[]{wither});
            ref.sendPacket(en.getKey(), packet);
        }
    }

}
