package de.encryptdev.bossmode.lang;

import de.encryptdev.bossmode.BossMode;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class LanguageFile {

    private final int defaultSize = 24;

    private File file;
    private Charset charset;
    private HashMap<String, String> messages;
    private PrintWriter printWriter;

    public LanguageFile(String langName) {
        File dir = new File(BossMode.getInstance().getDataFolder().getAbsolutePath());
        if (!dir.exists())
            dir.mkdir();
        this.file = new File(dir + "/lang/" + langName + ".lang");
        if (!this.file.exists())
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.messages = new HashMap<>();
    }

    public void loadLanguage() {
        List<String> fileContent = getContent();
        if (fileContent.size() == 0)
            saveLanguage();
        for (String str : fileContent) {
            String[] split = str.split("§");
            if (split[0].equalsIgnoreCase("charset")) {
                try {
                    this.charset = Charset.forName(split[1].trim());
                } catch (UnsupportedCharsetException uce) {
                    BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] Charset '" + split[1].trim() + "' can not found. Using default charset 'UTF-8'");
                    this.charset = Charset.forName("UTF-8");
                }
                continue;
            }
            messages.put(split[0], new String(split[1].getBytes(), charset));
        }
    }

    private List<String> getContent() {
        List<String> fileContent = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine())
                fileContent.add(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public void saveLanguage() {
        try {
            printWriter = new PrintWriter(this.file);
            printWriter.println("charset§ UTF-8");
            printWriter.println(LanguageCode.ALREADY_IN_EDITOR_MODE + "§ &4You are already in the editor mode");
            printWriter.println(LanguageCode.BOSS_NOT_EXIST + "§ &4The boss with the id %id% doesn't exist");
            printWriter.println(LanguageCode.USE_COLOR_CODES + "§ &eYou can use color codes with &");
            printWriter.println(LanguageCode.CREATE_BOSS + "§ &eYou create the boss");
            printWriter.println(LanguageCode.SPAWNER_ITEM_NULL + "§ &4The boss doesn't have a spawner item");
            printWriter.println(LanguageCode.GET_SPAWNER + "§ &eYou get the spawner");
            printWriter.println(LanguageCode.SET_NAME + "§ &eYou set the name");
            printWriter.println(LanguageCode.SET_SPAWN_LOCATION + "§ &eYou set the spawn location");
            printWriter.println(LanguageCode.ADD_MESSAGE_SPECIAL_ATTACK + "§ &eYou add the lang to the lang list");
            printWriter.println(LanguageCode.LEFT_EDITOR_MODE_WITHOUT_SAVE + "§ &eYou left the editor mode. Not saved");
            printWriter.println(LanguageCode.FINISH_EDITOR_MODE + "§ &eYou are finish");
            printWriter.println(LanguageCode.SET_NAME_CLOSED_INVENTORY + "§ &eSet the name of the entity");
            printWriter.println(LanguageCode.SPAWN_LOCATION_CLOSED_INVENTORY + "§ &eGo to the spawn location and write \"#finish\" in the chat");
            printWriter.println(LanguageCode.WRITE_ID_NOW + "§ &eWrite now the id from the boss");
            printWriter.println(LanguageCode.MESSAGE_SPECIAL_ATTACK + "§ &eWrite the messages, if you finish write #finish");
            printWriter.println(LanguageCode.EMPTY_BOSS_LIST + "§ &eYou don't have any boss created");
            printWriter.println(LanguageCode.BUILD_SPAWNER + "§ &eYou build the spawner");
            printWriter.println(LanguageCode.KILL_BOSS + "§ &eYou kill %Amount% bosses");
            printWriter.println(LanguageCode.KILL_ALL_BOSSES + "§ &eYou killed all bosses %Amount%");
            printWriter.println(LanguageCode.DELETE_BOSS_SUCCESSFUL + "§ &eYou delete the boss successful");
            printWriter.println(LanguageCode.STATS_HEADER + "§ &7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            printWriter.println(LanguageCode.STATS_LINE + "§ &eYour kills: %Kills%");
            printWriter.println(LanguageCode.STATS_FOOTER + "§ &7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            printWriter.println(LanguageCode.GET_EGG + "$ &eYou get the egg");
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTranslatedMessage(String code) {
        if (this.messages.get(code) == null)
            return "lang with code '" + code + "' doesn't exist";
        return BossMode.PREFIX + ChatColor.translateAlternateColorCodes('&', this.messages.get(code));
    }

}
