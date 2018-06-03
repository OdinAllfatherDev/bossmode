package de.encryptdev.bossmode.storage;

import de.encryptdev.bossmode.util.BMFileManager;

/**
 * Created by EncryptDev
 */
public class FileStorage implements StorageModule<UserData> {

    @Override
    public void init() {

    }

    @Override
    public void insertUserData(UserData fileUser) {
        BMFileManager fileManager = new BMFileManager(fileUser.getUuid(), "userdata");
        fileManager.copyDefault();
        fileManager.addDefault("uuid", fileUser.getUuid());
        fileManager.addDefault("killedBosses", fileUser.getKilledBosses());
        fileManager.save();
    }

    @Override
    public void updateUserData(UserData fileUser) {
        BMFileManager fileManager = new BMFileManager(fileUser.getUuid(), "userdata");
        fileManager.set("killedBosses", fileUser.getKilledBosses());
    }

    @Override
    public UserData getUserData(String uuid) {
        BMFileManager fileManager = new BMFileManager(uuid, "userdata");
        if (!fileManager.contains("killedBosses"))
            return new UserData(uuid, 0);
        return new UserData(uuid, fileManager.get("killedBosses", int.class));
    }

    @Override
    public void close() {

    }
}
