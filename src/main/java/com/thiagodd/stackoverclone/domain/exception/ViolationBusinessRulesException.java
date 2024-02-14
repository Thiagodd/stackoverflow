package com.thiagodd.stackoverclone.domain.exception;

public class ViolationBusinessRulesException extends RuntimeException{

    public ViolationBusinessRulesException() {
        super("Business rules violated.");
    }

    public ViolationBusinessRulesException(String message) {
        super(message);
    }

    public ViolationBusinessRulesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViolationBusinessRulesException(Throwable cause) {
        super(cause);
    }

    public ViolationBusinessRulesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
