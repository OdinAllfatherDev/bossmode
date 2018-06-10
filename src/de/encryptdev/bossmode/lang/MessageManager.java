package de.encryptdev.bossmode.lang;

import de.encryptdev.bossmode.util.BMFileManager;

/**
 * Created by EncryptDev
 */
public class MessageManager {

    private BMFileManager fileManager;

    public MessageManager() {

    }

    public void loadMessages() {
        fileManager = new BMFileManager("messages");
        fileManager.copyDefault();
        fileManager.addDefault(LanguageCode.ALREADY_IN_EDITOR_MODE, "&4You are already in the editor mode");
        fileManager.addDefault(LanguageCode.BOSS_NOT_EXIST, "&4The boss with the id %id% doesn't exist");
        fileManager.addDefault(LanguageCode.USE_COLOR_CODES, "&eYou can use color codes with &");
        fileManager.addDefault(LanguageCode.CREATE_BOSS, "&eYou create the boss");
        fileManager.addDefault(LanguageCode.SPAWNER_ITEM_NULL, "&4The boss doesn't have a spawner item");
        fileManager.addDefault(LanguageCode.GET_SPAWNER, "&eYou get the spawner");
        fileManager.addDefault(LanguageCode.SET_NAME, "&eYou set the name");
        fileManager.addDefault(LanguageCode.SET_SPAWN_LOCATION, "&eYou set the spawn location");
        fileManager.addDefault(LanguageCode.ADD_MESSAGE_SPECIAL_ATTACK, "&eYou add the lang to the lang list");
        fileManager.addDefault(LanguageCode.LEFT_EDITOR_MODE_WITHOUT_SAVE, "&eYou left the editor mode. Not saved");
        fileManager.addDefault(LanguageCode.FINISH_EDITOR_MODE, "&eYou are finish");
        fileManager.addDefault(LanguageCode.SET_NAME_CLOSED_INVENTORY, "&eSet the name of the entity");
        fileManager.addDefault(LanguageCode.SPAWN_LOCATION_CLOSED_INVENTORY, "&eGo to the spawn location and write \"#finish\" in the chat");
        fileManager.addDefault(LanguageCode.WRITE_ID_NOW, "&eWrite now the id from the boss");
        fileManager.addDefault(LanguageCode.MESSAGE_SPECIAL_ATTACK, "&eWrite the messages, if you finish write #finish");
        fileManager.addDefault(LanguageCode.EMPTY_BOSS_LIST, "&eYou don't have any boss created");
        fileManager.addDefault(LanguageCode.BUILD_SPAWNER, "&eYou build the spawner");
        fileManager.addDefault(LanguageCode.KILL_ALL_BOSSES, "&eYou killed all bosses %Amount%");
        fileManager.addDefault(LanguageCode.DELETE_BOSS_SUCCESSFUL, "&eYou delete the boss successful");
        fileManager.addDefault(LanguageCode.STATS_HEADER, "&7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        fileManager.addDefault(LanguageCode.STATS_LINE, "&eYour kills: %Kills%");
        fileManager.addDefault(LanguageCode.STATS_FOOTER, "&7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        fileManager.addDefault(LanguageCode.GET_EGG, "&eYou get the egg");
        fileManager.save();
    }

    public String getMessage(String code) {
        return fileManager.get(code) == null ? "mesasge with code '" + code + "' doesn't exist" : fileManager.get(code, String.class);
    }

}
