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
        fileManager.addDefault("spawnerItemNull", "&4The boss doesn't have a spawn item");
        fileManager.addDefault("getSpawner", "&eYou get the spawner");
        fileManager.addDefault("setName", "&eYou set the name");
        fileManager.addDefault("setDamage", "&eYou set the damage");
        fileManager.addDefault("setMaxHealth", "&eYou set the max health");
        fileManager.addDefault("setDropChanceMainHand", "&eYou set the drop chance for the main hand item");
        fileManager.addDefault("setDropChanceOffHand", "&eYou set the drop chance for the off hand item");
        fileManager.addDefault("setSpawnLocation", "&eYou set the spawn location");
        fileManager.addDefault("setSpawnChance", "&eYou set the chance to spawn value for the boss");
        fileManager.addDefault("setSpeed", "&eYou set the speed for the boss");
        fileManager.addDefault("setMaxSpawnAmount", "&eYou set the max spawn amount for the boss");
        fileManager.addDefault("addMessageSpecialAttack", "&eYou add the message to the message list");
        fileManager.addDefault("leftEditorModeWithoutSave", "&eYou left the editor mode. Not saved");
        fileManager.addDefault("finishEditorMode", "&eYou are finish");
        fileManager.addDefault("setHealthClosedInventory", "&eSet the health for the entity");
        fileManager.addDefault("setDamageClosedInventory", "&eSet the damage for the entity");
        fileManager.addDefault("setWalkSpeedClosedInventory", "&eSet the walk speed for the entity");
        fileManager.addDefault("setNearbyRadiusClosedInventory", "&eSet the nearby radius in blocks");
        fileManager.addDefault("setSpawnRadiusClosedInventory", "&eSet the spawn radius in blocks");
        fileManager.addDefault("setNameClosedInventory", "&eSet the name of the entity");
        fileManager.addDefault("setDropChanceMainHandClosedInventory", "&eSet the drop chance for the main hand item (0 - 100)");
        fileManager.addDefault("setChanceClosedInventory", "&eSet the chance");
        fileManager.addDefault("setDropChanceOffHandItemClosedInventory", "&eSet the drop chance for the off hand item (0 - 100)");
        fileManager.addDefault("spawnLocationClosedInventory", "&eGo to the spawn location and write \"#finish\" in the chat");
        fileManager.addDefault("chanceToSpawnClosedInventory", "&eSet the chance to spawn for the boss");
        fileManager.addDefault("setMaxSpawnAmountClosedInventory", "&eSet the max spawn amount for the boss");
        fileManager.addDefault("writeIdNow", "&eWrite now the id from the boss");
        fileManager.addDefault("messagesSpecialAttack", "&eWrite the messages, if you finish write #finish");
        fileManager.addDefault("strengthSpecialAttack", "&eWrite the strength for the special attack");
        fileManager.addDefault("amountOfEntitySpecialAttack", "&eWrite the amount of entities where the boss should spawn");
        fileManager.addDefault("setDroppedEXPClosedInventory", "&eWrite the amount of exp where the boss drop");
        fileManager.addDefault("emptyBossList", "&eYou don't have any boss created");
        fileManager.addDefault("setAttackWalkSpeedClosedInventory", "&eSet the walk speed, when the boss attack the player");
        fileManager.addDefault("setDelayClosedInventory", "&eSet the delay in seconds");
        fileManager.addDefault("setMinSpawnDelayClosedInventory", "&eSet the min delay in seconds");
        fileManager.addDefault("setMaxSpawnDelayClosedInventory", "&eSet the max spawn delay in seconds");
        fileManager.addDefault("setRequiredPlayerRangeClosedInventory", "&eSet the maximum distance (squared) a player can be in order for this spawner to be active");
        fileManager.addDefault("setSpawnCountClosedInventory", "&eSet the spawn count");
        fileManager.addDefault("setSpawnRangeClosedInventory", "&eSet the spawn range (squared) for the spawner");
        fileManager.addDefault("buildSpawner", "&eYou build the spawner");
        fileManager.addDefault("killBoss", "&eYou kill %Amount% bosses");
        fileManager.addDefault("killAllBosses", "&eYou killed all bosses %Amount%");
        fileManager.save();
    }

    public String getMessage(String path) {
        if(this.fileManager.get(path, String.class) == null)
            return "message with path '" + path + "' doesn't exist";
        return BossMode.PREFIX + fileManager.get(path, String.class);
    }

}
