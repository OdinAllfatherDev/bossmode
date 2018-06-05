package de.encryptdev.bossmode.command;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.lang.LanguageCode;
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
            player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.STATS_HEADER));
            player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.STATS_LINE).replace("%Kills%",
                    String.valueOf(BossMode.getInstance().getStorageModul().getUserData(player.getUniqueId().toString()).getKilledBosses())));
            player.sendMessage(BossMode.getInstance().getTranslatedMessage(LanguageCode.STATS_FOOTER));
        }
    }

    @Override
    public void consoleCommand(ConsoleCommandSender ccs, String[] args) {

    }
}
