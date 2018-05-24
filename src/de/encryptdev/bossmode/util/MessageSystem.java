package de.encryptdev.bossmode.util;

import de.encryptdev.bossmode.BossMode;

/**
 * Created by EncryptDev
 */
public class MessageSystem {

    private BMFileManager fileManager;

    public MessageSystem() {
        this.fileManager = new BMFileManager("messages");
    }

    public void loadDefaultMessages(){
        fileManager.copyDefault();
        fileManager.addDefault("alreadyInEditorMode", "&4You are already in the editor mode");
        fileManager.addDefault("bossNotExist", "&4The boss with the id %id% doesn't exist");
        fileManager.addDefault("useColorCodes", "&eYou can use color codes with &");
        fileManager.addDefault("createBoss", "&eYou create the boss");
        fileManager.addDefault("spawnerItemNull", "&4The boss doesn't have a spawner item");
        fileManager.addDefault("getSpawner", "&eYou get the spawner");
        fileManager.addDefault("setName", "&eYou set the name");
        fileManager.addDefault("setSpawnLocation", "&eYou set the spawn location");
        fileManager.addDefault("addMessageSpecialAttack", "&eYou add the message to the message list");
        fileManager.addDefault("leftEditorModeWithoutSave", "&eYou left the editor mode. Not saved");
        fileManager.addDefault("finishEditorMode", "&eYou are finish");
        fileManager.addDefault("setNameClosedInventory", "&eSet the name of the entity");
        fileManager.addDefault("spawnLocationClosedInventory", "&eGo to the spawn location and write \"#finish\" in the chat");
        fileManager.addDefault("writeIdNow", "&eWrite now the id from the boss");
        fileManager.addDefault("messagesSpecialAttack", "&eWrite the messages, if you finish write #finish");
        fileManager.addDefault("emptyBossList", "&eYou don't have any boss created");
        fileManager.addDefault("buildSpawner", "&eYou build the spawner");
        fileManager.addDefault("killBoss", "&eYou kill %Amount% bosses");
        fileManager.addDefault("killAllBosses", "&eYou killed all bosses %Amount%");
        fileManager.addDefault("setSpecialAttackArmyChance", "&eSet the chance, when the boss get a hit to use this special attack (0 - 100)");
        fileManager.addDefault("deleteBossSuccessful", "&eYou delete the boss successful");
        fileManager.save();
    }

    public String getMessage(String path) {
        if(this.fileManager.get(path, String.class) == null)
            return "message with path '" + path + "' doesn't exist";
        return BossMode.PREFIX + fileManager.get(path, String.class);
    }

}
