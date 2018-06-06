package de.encryptdev.bossmode.lang;

import de.encryptdev.bossmode.BossMode;

import java.io.File;
import java.util.logging.Level;

/**
 * Created by EncryptDev
 */
public class LanguageManager {

    private LanguageFile currentLanguage;

    public LanguageManager(BossMode bossMode) {
        String langName = bossMode.getConfig().getString("lang");
        File dir = new File(BossMode.getInstance().getDataFolder().getAbsolutePath() + "/lang/");
        if(dir == null) {
            this.currentLanguage = new LanguageFile("en_US");
            this.currentLanguage.saveLanguage();
            this.currentLanguage.loadLanguage();
            BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Use language: [" + langName + "]");
            BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] Dir field is null, please contact me");
        }
        if(!dir.exists())
            dir.mkdir();
        if(dir.listFiles().length > 0) {
            for (File file : dir.listFiles()) {
                String fileName = file.getName().replace(".lang", "");
                if (fileName.equals(langName)) {
                    this.currentLanguage = new LanguageFile(langName);
                    this.currentLanguage.loadLanguage();
                    BossMode.getLog().log(Level.INFO, "[BossMode-LOG] Use language: [" + langName + "]");
                }
            }
            if (this.currentLanguage == null) {
                this.currentLanguage = new LanguageFile("en_US");
                this.currentLanguage.saveLanguage();
                this.currentLanguage.loadLanguage();
                BossMode.getLog().log(Level.WARNING, "[BossMode-LOG] Can not load other language. Use default language [en_US]");
            }
        }
    }

    public String getTranslatedMessage(String code) {
        return this.currentLanguage.getTranslatedMessage(code);
    }

}
