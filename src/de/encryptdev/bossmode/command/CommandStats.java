package de.encryptdev.bossmode.command;

import de.encryptdev.bossmode.BossMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public class CommandStats extends ACommand {


    @Override
    public void playerCommand(Player player, String[] args) {
        if (!player.hasPermission("bossmode.stats") || !player.isOp() || !player.hasPermission("bossmode.*"))
            return;
        if (args.length == 0) {
            player.sendMessage(BossMode.getInstance().getTranslatedMessage("statsHeader"));
            player.sendMessage(BossMode.getInstance().getTranslatedMessage("statsLine").replace("%Kills%",
                    String.valueOf(BossMode.getInstance().getStorageModul().getUserData(player.getUniqueId().toString()).getKilledBosses())));
            player.sendMessage(BossMode.getInstance().getTranslatedMessage("statsFooter"));
        }
    }

    @Override
    public void consoleCommand(ConsoleCommandSender ccs, String[] args) {

    }
}
