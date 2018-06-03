package de.encryptdev.bossmode.storage;

/**
 * Created by EncryptDev
 */
public class UserData {

    private String uuid;
    private int killedBosses;

    public UserData(String uuid, int killedBosses) {
        this.uuid = uuid;
        this.killedBosses = killedBosses;
    }

    public void setKilledBosses(int killedBosses) {
        this.killedBosses = killedBosses;
    }

    public String getUuid() {
        return uuid;
    }

    public int getKilledBosses() {
        return killedBosses;
    }

}
