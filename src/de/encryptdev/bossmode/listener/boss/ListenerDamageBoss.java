package de.encryptdev.bossmode.listener.boss;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossSettings;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by EncryptDev
 */
public class ListenerDamageBoss implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity
                && !(event.getEntity() instanceof ArmorStand)) {
            LivingEntity le = (LivingEntity) event.getEntity();
            if (le.hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                int id = le.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                IBoss boss = BossMode.getInstance().getBossManager().getSpawnedBoss(id);
                boss.damageEntity(event.getFinalDamage());
            }
        }
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        if(event.getEntity() instanceof  LivingEntity && !(event.getEntity() instanceof ArmorStand) &&
                (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
            LivingEntity le = (LivingEntity) event.getEntity();
            if (le.hasMetadata(BossSettings.META_DATA_BOSS_LIVING_ID)) {
                int id = le.getMetadata(BossSettings.META_DATA_BOSS_LIVING_ID).get(0).asInt();
                IBoss boss = BossMode.getInstance().getBossManager().getSpawnedBoss(id);
                boss.damageEntity(event.getDamage());
            }
        }
    }

}
