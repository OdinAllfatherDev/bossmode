package de.encryptdev.bossmode.boss.spawn;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.*;
import org.bukkit.block.Biome;

import java.util.*;
import java.util.logging.Level;

/**
 * This manager spawn a boss natural in the world
 * <p>
 * Created by EncryptDev
 */
public class NaturalSpawnManager {

    private List<IBoss> naturalSpawned;
    private HashMap<IBoss, Integer> naturalMap;

    public NaturalSpawnManager() {
        this.naturalSpawned = new LinkedList<>();
        this.naturalMap = new HashMap<>();
    }

    /**
     * This methode spawn the boss natural.
     * <p>
     * It get the biome and the world from the boss,
     * and iterate about the loaded chunks in the world.
     * If a chunk has the same biome as the boss, then check the chance to spawn
     * If the random value is lower as the chance value then get i the chunk surface
     * by the method: {@link BossUtil#getChunkSurface(Chunk)} and then a random location
     * from the list.
     *
     * @param bossId - the boss id
     */
    public void spawnBossNatural(int bossId) {
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Spawning boss natural...");
        IBoss iBoss = BossMode.getInstance().getBossManager().getBoss(bossId);

        Biome biome = iBoss.getBossSettings().getBiome();

        World world = iBoss.getWorld();
        if (world == null) {
            world = Bukkit.getWorld("world");
            BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] using default world 'world'");
            BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] if it is a other world, please set the world in the boss file manual");
        }


        float chanceToSpawn = iBoss.getBossSettings().getChanceToSpawn();
        int spawnAmount = iBoss.getBossSettings().getSpawnAmount();

        Random random = new Random();
        int rand = random.nextInt(100);

        int spawned = 0;


        for (Chunk chunks : world.getLoadedChunks()) {
            if (chunks.getBlock(1, 1, 1).getBiome() == biome) {
                if (rand < chanceToSpawn) {
                    spawned++;
                    if (spawned > spawnAmount)
                        break;

                    Location spawnLocation = BossUtil.getChunkSurface(chunks)
                            .get(random.nextInt(BossUtil.getChunkSurface(chunks).size() - 1));

                    IBoss toSpawn = BossMode.getInstance().getBossManager().copyBoss(iBoss);
                    toSpawn.spawnBoss(spawnLocation);

                }
            }
        }

        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Spawned boss natural");
    }

    public List<IBoss> getBossList(int id) {
        List<IBoss> list = new ArrayList<>();
        for(IBoss ib : naturalSpawned)
            if(ib.getBossID() == id)
                list.add(ib);
        return list;
    }

    public HashMap<IBoss, Integer> getNaturalMap() {
        return naturalMap;
    }

    public List<IBoss> getNaturalSpawned() {
        return naturalSpawned;
    }
}
