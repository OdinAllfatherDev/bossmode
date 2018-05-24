package de.encryptdev.bossmode.ref;

import de.encryptdev.bossmode.BossMode;

/**
 * Created by EncryptDev
 */
public class NBTTagInstance<T> extends Reflection {

    private NBTSpawnerUtil.NBTTagClass nbtTagClass;
    private T type;
    private Class<?> primitive;

    public NBTTagInstance(NBTSpawnerUtil.NBTTagClass nbtTagClass, T type, Class<?> primitive) {
        super(BossMode.getInstance().getNmsVersion());
        this.nbtTagClass = nbtTagClass;
        this.type = type;
        this.primitive = primitive;
    }

    public Object getNBTTagCompound() {
        return nbtTagClass == NBTSpawnerUtil.NBTTagClass.COMPOUND ? getNewInstanceClass(nbtTagClass.getNbtClass()) : null;
    }

    public Object getNBTList() {
        return nbtTagClass == NBTSpawnerUtil.NBTTagClass.LIST ? getNewInstanceClass(nbtTagClass.getNbtClass()) : null;
    }

    public Object getInstance() {
        return getNewInstanceConstructor(nbtTagClass.getNbtClass(), new Class<?>[]{primitive}, new Object[]{type});
    }
}
