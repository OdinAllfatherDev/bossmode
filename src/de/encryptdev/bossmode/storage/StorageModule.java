package de.encryptdev.bossmode.storage;

/**
 * Created by EncryptDev
 */
public interface StorageModule<UserData> {

    void init();

    void insertUserData(UserData userData);

    void updateUserData(UserData userData);

    UserData getUserData(String uuid);

    void close();

}
