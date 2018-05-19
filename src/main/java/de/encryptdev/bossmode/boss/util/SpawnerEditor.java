package de.encryptdev.bossmode.boss.util;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by EncryptDev
 */
public class SpawnerEditor {

    private IBoss iBoss;
    private int delay;
    private int maxSpawnDelay;
    private int minSpawnDelay;
    private int requiredPlayerRange;
    private int spawnCount;
    private int spawnRange;

    public SpawnerEditor(IBoss iBoss) {
        this.iBoss = iBoss;
        this.delay = 20;
        this.maxSpawnDelay = 800;
        this.minSpawnDelay = 200;
        this.requiredPlayerRange = 16;
        this.spawnCount = 4;
        this.spawnRange = 4;
    }

    public void setDelay(int delay) {
        if(delay == 0) {
            this.delay = -1;
            return;
        }
        this.delay = delay;
    }


    public void setMaxSpawnDelay(int maxSpawnDelay) {
        this.maxSpawnDelay = maxSpawnDelay;
    }

    public void setMinSpawnDelay(int minSpawnDelay) {
        this.minSpawnDelay = minSpawnDelay;
    }

    public void setRequiredPlayerRange(int requiredPlayerRange) {
        this.requiredPlayerRange = requiredPlayerRange;
    }

    public void setSpawnCount(int spawnCount) {
        this.spawnCount = spawnCount;
    }

    public void setSpawnRange(int spawnRange) {
        this.spawnRange = spawnRange;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§eSpawner: " + iBoss.getBossName());

        List<String> lore = new LinkedList<>();
        lore.add("§eID: " + iBoss.getBossID());
        lore.add("§eDelay: " + delay);
        lore.add("§eMaxSpawnDelay: " + maxSpawnDelay);
        lore.add("§eMinSpawnDelay: " + minSpawnDelay);
        lore.add("§eRequiredPlayerRange: " + requiredPlayerRange);
        lore.add("§eSpawnCount: " + spawnCount);
        lore.add("§eSpawnRange: " + spawnRange);

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack.clone();
    }

}
