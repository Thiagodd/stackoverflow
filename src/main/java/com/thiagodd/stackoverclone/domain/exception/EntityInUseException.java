package com.thiagodd.stackoverclone.domain.exception;

public class EntityInUseException extends RuntimeException{

    public EntityInUseException() {
        super("Entity in use.");
    }

    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityInUseException(Throwable cause) {
        super(cause);
    }

    public EntityInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
