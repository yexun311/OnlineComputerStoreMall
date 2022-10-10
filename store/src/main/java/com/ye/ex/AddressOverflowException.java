package com.ye.ex;

/**
 * 地址数量超出规定值
 */
public class AddressOverflowException extends ServiceException {
    public AddressOverflowException() {
    }

    public AddressOverflowException(String message) {
        super(message);
    }

    public AddressOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressOverflowException(Throwable cause) {
        super(cause);
    }

    public AddressOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
