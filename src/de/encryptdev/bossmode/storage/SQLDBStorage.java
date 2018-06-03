package de.encryptdev.bossmode.storage;

import de.encryptdev.bossmode.addon.sql.BossModeSQL;
import de.encryptdev.bossmode.addon.sql.NetHikariSQL;

/**
 * Created by EncryptDev
 */
public class SQLDBStorage implements StorageModule<UserData> {

    private NetHikariSQL hikari;

    @Override
    public void init() {
        this.hikari = new NetHikariSQL(BossModeSQL.getInstance().getHikariConfig());
        this.hikari.createTable();
    }

    @Override
    public void insertUserData(UserData userData) {
        this.hikari.insertUserData(userData.getUuid(), 1);
    }

    @Override
    public void updateUserData(UserData userData) {
        this.hikari.updateUserData(userData.getUuid(), userData.getKilledBosses());
    }

    @Override
    public UserData getUserData(String uuid) {
        return new UserData(uuid, hikari.getKilledBosses(uuid));
    }

    @Override
    public void close() {
        this.hikari.closeConnection();
    }
}
