package de.encryptdev.bossmode.error;

import org.bukkit.Bukkit;

/**
 * Created by EncryptDev
 */
public class ErrorPrinter {

    public static void print(RuntimeError error) {
        Bukkit.getConsoleSender().sendMessage("§5§lBOSSMODE-ERROR");
        Bukkit.getConsoleSender().sendMessage("§4ErrorID: " + error.getErrorId());
        Bukkit.getConsoleSender().sendMessage("§4ErrorMessage: " + error.getErrorMessage());
    }

}
