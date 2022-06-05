package ru.kpfu.itis.kashapova.exception;

public class NotEqualPasswordsException extends RuntimeException {
    public NotEqualPasswordsException() {
    }

    public NotEqualPasswordsException(String message) {
        super(message);
    }

    public NotEqualPasswordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualPasswordsException(Throwable cause) {
        super(cause);
    }

    public NotEqualPasswordsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
