package de.encryptdev.bossmode.boss.special;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EncryptDev
 */
public class StompSpecialAttack extends SpecialAttack {

    private IBoss iBoss;
    private double strength;

    public StompSpecialAttack(double strength) {
        super("stompSpecialAttack");
        this.strength = strength;
    }

    @Override
    public void execute(Player... params) {
        for (Player player : params) {
            player.setVelocity(new Vector(0, strength / 10, 0));
        }
    }

    @Override
    public void setIBoss(IBoss iBoss) {
        this.iBoss = iBoss;
    }

    public IBoss getiBoss() {
        return iBoss;
    }

    @Override
    public String[] datas() {
        return new String[]{String.valueOf(strength)};
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("strength", strength);
        return map;
    }
}
