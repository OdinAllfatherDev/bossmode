package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.mount.Mount;
import de.encryptdev.bossmode.boss.util.BossManager;
import de.encryptdev.bossmode.boss.util.BossSettings;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.storage.UserData;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerDeathBoss implements Listener {

    private BossManager bossManager;

    public ListenerDeathBoss(BossManager bossManager) {
        this.bossManager = bossManager;
    }

    @EventHandler
    public void on(EntityDeathEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity entity = event.getEntity();

            if(BossUtil.isBoss(entity)) {
                IBoss iBoss = BossUtil.getLivingBossByMetadata(entity);
                if (iBoss != null) {
                    List<Player> nearPlayer = iBoss.getNearPlayers();
                    if(!nearPlayer.isEmpty()) {
                        for(Player player : nearPlayer) {
                            UserData userData = BossMode.getInstance().getStorageModul().getUserData(player.getUniqueId().toString());
                            if(userData == null) {
                                BossMode.getInstance().getStorageModul().insertUserData(new UserData(player.getUniqueId().toString(), 1));
                            } else {
                                int killedBosses = userData.getKilledBosses() + 1;
                                userData.setKilledBosses(killedBosses);
                                BossMode.getInstance().getStorageModul().updateUserData(userData);
                            }
                        }
                    }

                    iBoss.death();
                    event.setDroppedExp(iBoss.getBossSettings().getDroppedXp());
                } else {
                    event.setDroppedExp(100);
                }
            } else {
                if(entity.hasMetadata(Mount.META_DATA)) {
                    IBoss iBoss = (IBoss) entity.getMetadata(Mount.META_DATA).get(0).value();
                    iBoss.getMount().die();
                    iBoss.setMount(null);
                }
            }
        }

    }

    @EventHandler
    public void on(EntityExplodeEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            if (event.getEntity() instanceof Creeper) {
                if (event.getEntity().hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                    int id = event.getEntity().getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                    IBoss iBoss = bossManager.getSpawnedBoss(id);
                    iBoss.death();
                }
            }
        }
    }

}
