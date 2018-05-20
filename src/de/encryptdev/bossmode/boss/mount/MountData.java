package de.encryptdev.bossmode.boss.mount;

import net.minecraft.server.v1_12_R1.EntityHorse;
import net.minecraft.server.v1_12_R1.EntityHorseAbstract;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class MountData implements ConfigurationSerializable {

    private double maxHealth;
    private boolean adult;
    private double jumpStrength;
    private Material armor;

    public MountData(double maxHealth, boolean adult, double jumpStrength, Material armor) {
        this.maxHealth = maxHealth;
        this.adult = adult;
        this.jumpStrength = jumpStrength;
        this.armor = armor;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getJumpStrength() {
        return jumpStrength;
    }

    public boolean isAdult() {
        return adult;
    }

    public Material getArmor() {
        return armor;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("maxHealth", maxHealth);
        map.put("adault", adult);
        map.put("jumpStrength", jumpStrength);
        map.put("armor", armor.toString());
        return map;
    }
}
