package de.encryptdev.bossmode.util.exception;

/**
 * Created by EncryptDev
 */
public class MethodNotFoundException extends RuntimeException {

    public MethodNotFoundException(Class<?> clazz, String methodName) {
        super("The method in class: '" + clazz.getSimpleName() + "' can not found. Method name: '" + methodName + "'");
    }

}
