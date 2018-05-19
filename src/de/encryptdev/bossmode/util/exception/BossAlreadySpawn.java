package de.encryptdev.bossmode.util.exception;

/**
 * Created by EncryptDev
 */
public class BossAlreadySpawn extends RuntimeException {

    public BossAlreadySpawn(int id) {
        super("The boss '" + id + "' is already spawned");
    }

}
