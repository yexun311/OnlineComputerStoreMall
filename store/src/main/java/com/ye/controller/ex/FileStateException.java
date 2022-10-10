package com.ye.controller.ex;

/**
 * 文件状态异常（文件处于打开状态）
 */
public class FileStateException extends FileUploadException {

    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
