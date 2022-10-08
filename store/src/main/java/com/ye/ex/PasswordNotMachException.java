package com.ye.ex;

public class PasswordNotMachException extends ServiceException {
    public PasswordNotMachException() {
    }

    public PasswordNotMachException(String message) {
        super(message);
    }

    public PasswordNotMachException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMachException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMachException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
