package de.encryptdev.bossmode.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by EncryptDev
 */
public abstract class ACommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            consoleCommand((ConsoleCommandSender) commandSender, strings);
        else
            playerCommand((Player) commandSender, strings);
        return true;
    }

    public abstract void playerCommand(Player player, String[] args);

    public abstract void consoleCommand(ConsoleCommandSender ccs, String[] args);
}
