package de.encryptdev.bossmode.util.exception;

/**
 * Created by EncryptDev
 */
public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(Class<?> clazz, String fieldName) {
        super("The field in class: '" + clazz.getSimpleName() + "' can not find. Field name: '" + fieldName + "'");
    }

}
