package de.encryptdev.bossmode.error;

/**
 * Created by EncryptDev
 */
public class RuntimeError {

    private String errorId;
    private String errorMessage;

    public RuntimeError(String errorId, String errorMessage) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
