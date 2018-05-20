package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.List;
import java.util.Random;

/**
 * Created by EncryptDev
 */
public class ListenerBossSpawnChunk implements Listener {

    private Random random;
    private BossManager bossManager;
    
    public ListenerBossSpawnChunk(BossManager bossManager) {
        this.random = new Random();
        this.bossManager = bossManager;
    }
    
    @EventHandler
    public void on(ChunkLoadEvent event) {
        if(BossMode.getInstance().getConfig().getBoolean("bossSpawnByChunkLoading")) {
            Chunk chunk = event.getChunk();

            for(IBoss boss : bossManager.getBosses())
                if(chunk.getBlock(1, 1, 1).getBiome() == boss.getBossSettings().getBiome())
                    if(boss.getBossSettings().isNaturalSpawn()) {
                        List<Location> surface = BossUtil.getChunkSurface(chunk);
                        boss.spawnBoss(surface.size() == 1 ? surface.get(0) : surface.get(random.nextInt(surface.size() - 1)));
                    }

        }
    }

}
