package de.encryptdev.bossmode.listener;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.util.BossSettings;
import de.encryptdev.bossmode.boss.util.BossUtil;
import de.encryptdev.bossmode.ref.Reflection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * Created by EncryptDev
 */
public class ListenerPlayerDamageByBoss implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof LivingEntity) {
            Player player = (Player) event.getEntity();
            if (BossUtil.isBoss((LivingEntity) event.getDamager())) {
                IBoss iBoss = BossUtil.getBossByMetadata((LivingEntity) event.getDamager());

                if(BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R1 || BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R2 ||
                        BossMode.getInstance().getNmsVersion() == Reflection.NMSVersion.V1_8_R3) {
                    if(iBoss.getBossEntity().hasMetadata(BossSettings.META_DATA_BOSS_POTION)) {
                        List<PotionEffect> peList = (List<PotionEffect>) iBoss.getBossEntity().getMetadata(BossSettings.META_DATA_BOSS_POTION).get(0).value();
                        if(!peList.isEmpty())
                            for(PotionEffect pe : peList)
                                player.addPotionEffect(new PotionEffect(pe.getType(), 400, pe.getAmplifier()));
                    }
                    float damage = iBoss.getBossSettings().getDamage();
                    ((Player) event.getEntity()).damage(damage);

                } else {
                    if(iBoss.getBossEntity().hasMetadata(BossSettings.META_DATA_BOSS_POTION)) {
                        List<PotionEffect> peList = (List<PotionEffect>) iBoss.getBossEntity().getMetadata(BossSettings.META_DATA_BOSS_POTION).get(0).value();
                        if(!peList.isEmpty())
                            for(PotionEffect pe : peList)
                                player.addPotionEffect(new PotionEffect(pe.getType(), 400, pe.getAmplifier()));
                    }
                }
            }
        }

    }
}
