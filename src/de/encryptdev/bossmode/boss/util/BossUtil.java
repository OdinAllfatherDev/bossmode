package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.InventoryStorage;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.ref.Reflection;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This Util class is only for the boss
 * <p>
 * Created by EncryptDev
 */
public class BossUtil {

    public static int getBossIds() {
        return BossMode.getInstance().getConfig().getInt("bossId");
    }

    public static int getLivingBossIds() {
        return BossMode.getInstance().getConfig().getInt("livingBossId");
    }

    public static boolean isBoss(LivingEntity entity) {
        return entity.hasMetadata(BossSettings.META_DATA_BOSS_ID);
    }

    public static IBoss getLivingBossByMetadata(LivingEntity entity) {
        for (IBoss boss : BossMode.getInstance().getBossManager().getAllSpawnedBosses()) {
            if (boss.getLivingID() == entity.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt()) {
                return boss;
            }
        }
        return null;
    }

    public static boolean checkDefaultValue(InventoryStorage.CounterType counterType, double value) {
        return counterType.getDefaultValue() == value;
    }

    public static String makeEnumNameNormal(Enum e) {
        String etvStr = e.toString();
        String lowerCase = etvStr.toLowerCase();
        String result;

        if (lowerCase.contains("_")) {
            char firstIndex = lowerCase.charAt(0);
            char afterSplit = lowerCase.charAt(lowerCase.indexOf("_") + 1);

            String restLeft = lowerCase.substring(1, lowerCase.indexOf("_"));
            String restRight = lowerCase.substring(lowerCase.indexOf("_") + 2, lowerCase.length());
            result = String.valueOf(firstIndex).toUpperCase() + restLeft + " " + String.valueOf(afterSplit).toUpperCase() + restRight;
        } else {
            char firstIndex = lowerCase.charAt(0);
            String rest = lowerCase.substring(1, lowerCase.length());
            result = String.valueOf(firstIndex).toUpperCase() + rest;
        }
        return result;
    }

    public static boolean is1_8() {
        return BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R1
                || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R2
                || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R3;
    }

    /**
     * Get the spawnable surface from the chunk
     * <p>
     * I iterate 16 blocks in x and z, and i iterate 256 in y
     * Then i get the block, if the block is not from material AIR
     * then i check the left, right, up, forward, back and down block.
     * if the block is not from type; AIR, WATER, LAVA or LEAVES then i add to the
     * locations list.
     *
     * @param chunk
     * @return
     */
    public static List<Location> getChunkSurface(Chunk chunk) {

        List<Location> locations = new LinkedList<>();

        for (int x = 0; x <= 16; x++) {
            for (int y = 0; y <= 256; y++) {
                for (int z = 0; z <= 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block.getType() == Material.AIR) {

                        Block down = block.getLocation().subtract(0, 1, 0).getBlock();
                        if (down.getType() != Material.AIR && down.getType() != Material.WATER && down.getType() != Material.LAVA
                                && !down.getType().toString().contains("LEAVES")) {
                            locations.add(down.getLocation());
                        }

                        Block leftX = block.getLocation().subtract(1, 0, 0).getBlock();
                        if (leftX.getType() != Material.AIR && leftX.getType() != Material.WATER && leftX.getType() != Material.LAVA
                                && !down.getType().toString().contains("LEAVES")) {
                            locations.add(leftX.getLocation());
                        }

                        Block rightX = block.getLocation().add(1, 0, 0).getBlock();
                        if (rightX.getType() != Material.AIR && rightX.getType() != Material.WATER && rightX.getType() != Material.LAVA
                                && !down.getType().toString().contains("LEAVES")) {
                            locations.add(rightX.getLocation());
                        }

                        Block leftZ = block.getLocation().subtract(0, 0, 1).getBlock();
                        if (leftZ.getType() != Material.AIR && leftZ.getType() != Material.WATER && leftZ.getType() != Material.LAVA
                                && !down.getType().toString().contains("LEAVES")) {
                            locations.add(leftZ.getLocation());
                        }

                        Block rightZ = block.getLocation().add(0, 0, 1).getBlock();
                        if (rightX.getType() != Material.AIR && rightZ.getType() != Material.WATER && rightZ.getType() != Material.LAVA
                                && !down.getType().toString().contains("LEAVES")) {
                            locations.add(rightZ.getLocation());
                        }
                    }

                }
            }
        }
        return locations;
    }

    public static String wrappBossSettings(IBoss iBoss) {
        BossSettings settings = iBoss.getBossSettings();
        String wrapped = "";
        for (Map.Entry<String, Object> str : settings.entrySet()) {
            wrapped = wrapped + str.getKey() + ":" + str.getValue() + "~";
        }
        return wrapped.substring(0, wrapped.length() - 1);
    }

}
