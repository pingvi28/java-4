package ru.kpfu.itis.kashapova.exeption;

public class WebFrameworkException extends RuntimeException{
    public WebFrameworkException() {
        super();
    }

    public WebFrameworkException(String message) {
        super(message);
    }

    public WebFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebFrameworkException(Throwable cause) {
        super(cause);
    }

    protected WebFrameworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
