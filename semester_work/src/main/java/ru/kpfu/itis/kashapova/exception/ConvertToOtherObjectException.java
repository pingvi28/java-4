package ru.kpfu.itis.kashapova.exception;

public class ConvertToOtherObjectException extends Exception {
    public ConvertToOtherObjectException(ReflectiveOperationException e) {
        super(e);
    }
}
