package com.joutvhu.expansy.exception;

public class DefineException extends ExpansyException  {
    public DefineException() {
    }

    public DefineException(String message) {
        super(message);
    }

    public DefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefineException(Throwable cause) {
        super(cause);
    }

    public DefineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
