package de.encryptdev.bossmode;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class UpdateChecker implements Listener {

    private String url = "https://api.spigotmc.org/legacy/update.php?resource=";
    private String id = "56720";

    private boolean isAvailable;

    public UpdateChecker() {
        isAvailable = checkUpdate();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        if(event.getPlayer().isOp())
            if(isAvailable)
                event.getPlayer().sendMessage(BossMode.PREFIX + "A update is available");
    }

    private boolean checkUpdate() {
        BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Check for updates...");
        try {
            String localVersion = BossMode.getInstance().getDescription().getVersion();
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url + id).openConnection();
            connection.setRequestMethod("GET");
            String raw = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            String remoteVersion;
            if(raw.contains("-")) {
                remoteVersion = raw.split("-")[0].trim();
            } else {
                remoteVersion = raw;
            }

            if(!localVersion.equalsIgnoreCase(remoteVersion))
                return true;

        } catch (IOException e) {
            return false;
        }
        return false;
    }

}
