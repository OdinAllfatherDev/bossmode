package de.encryptdev.bossmode.util;

import de.encryptdev.bossmode.boss.IBoss;

/**
 * A interface for execute everything
 * Use for the special attacks
 *
 * See a specialattack class
 *
 * Created by EncryptDev
 */
public interface Executable<T> {

    //The main execute methode
    void execute(T... params);

    //Set the IBoss instance
    void setIBoss(IBoss iBoss);

}
