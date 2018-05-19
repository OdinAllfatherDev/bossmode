package de.encryptdev.bossmode.boss.special;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.util.Executable;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This special attack implements the interface {@link Executable}
 * the class implement the method {@code execute(T... params)} where the code is
 * <p>
 * Created by EncryptDev
 */
public class MessageSpecialAttack extends SpecialAttack {

    private IBoss iBoss;
    private List<String> messages;
    private Random random;

    public MessageSpecialAttack(List<String> messages) {
        super("messageSpecialAttack");
        this.messages = messages;
        this.random = new Random();
    }

    @Override
    public void setIBoss(IBoss iBoss) {
        this.iBoss = iBoss;
    }

    /**
     * Write here the code, for the special attack
     *
     * @param params - the params
     */
    @Override
    public void execute(Player... params) {
        for (Player players : params) {
            if (messages.isEmpty())
                continue;
            String rMessage = messages.size() == 1 ?
                    messages.get(0) : messages.get(random.nextInt(messages.size() - 1));
            String message = BossMode.getInstance().getConfig().getString("bossMessage");
            message = message.replace("%BossName%", iBoss.getBossName()).replace("%Message%", rMessage);
            players.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    @Override
    public String[] datas() {
        String[] data = new String[messages.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = messages.get(i);
        }
        return data;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("messages", messages);
        return map;
    }
}
