package de.encryptdev.bossmode.boss.mount;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class BossMount implements ConfigurationSerializable {

    public enum BossMountType {

        ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE), SKELETON_HORSE(EntityType.SKELETON_HORSE);

        private EntityType type;

        BossMountType(EntityType type) {
            this.type = type;
        }

        public EntityType getType() {
            return type;
        }
    }

    private IBoss iBoss;
    private BossMountType bossMountType;
    private LivingEntity mount;
    private MountData mountData;

    public BossMount(IBoss iBoss, BossMountType bossMountType, MountData mountData) {
        this.iBoss = iBoss;
        this.bossMountType = bossMountType;
        this.mount = (LivingEntity) iBoss.getWorld().spawnEntity(iBoss.getSpawnLocation(), bossMountType.getType());
        this.mountData = mountData;
    }

    public void spawn() {
        double maxHealth = this.mountData.getMaxHealth() > 2048 ? 2048 : this.mountData.getMaxHealth();
        AbstractHorse ah = (AbstractHorse) mount;
        ah.setMaxDomestication(2);
        ah.setDomestication(1);
        ah.setJumpStrength(mountData.getJumpStrength());
        if (this.mountData.isAdult())
            ah.setAdult();
        ah.getInventory().setItem(1, new ItemStack(mountData.getArmor()));
        ah.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
        ah.setHealth(maxHealth);
        ah.addPassenger(iBoss.getBossEntity());
    }

    public void remove() {
        this.mount.remove();
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bossMountType", bossMountType);
        map.put("mountData", mountData);
        map.put("riding_boss", iBoss.getBossID());
        return map;
    }
}
