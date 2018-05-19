package de.encryptdev.bossmode.boss.special;

import de.encryptdev.bossmode.util.Executable;
import de.encryptdev.bossmode.util.Nameable;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public abstract class SpecialAttack implements Executable<Player>, Nameable, ConfigurationSerializable {

    private String name;

    public SpecialAttack(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract String[] datas();

}
