package de.encryptdev.bossmode.boss.special;

import de.encryptdev.bossmode.boss.IBoss;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * See {@link MessageSpecialAttack}
 * <p>
 * Created by EncryptDev
 */
public class TeleportSpecialAttack extends SpecialAttack {

    private Random random;
    private IBoss iBoss;

    public TeleportSpecialAttack() {
        super("teleportSpecialAttack");
        this.random = new Random();
    }

    @Override
    public void setIBoss(IBoss iBoss) {
        this.iBoss = iBoss;
    }

    @Override
    public void execute(Player... params) {
        if (params.length > 1) {
            Player randomPlayer = params[random.nextInt(params.length - 1)];
            iBoss.getBossEntity().teleport(randomPlayer.getLocation());
        }
    }

    @Override
    public String[] datas() {
        return new String[0];
    }

    @Override
    public Map<String, Object> serialize() {
        return new HashMap<>();
    }
}
