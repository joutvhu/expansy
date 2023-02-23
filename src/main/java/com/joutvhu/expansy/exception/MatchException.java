package com.joutvhu.expansy.exception;

public class MatchException extends ExpansyException {
    public MatchException() {
    }

    public MatchException(String message) {
        super(message);
    }

    public MatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchException(Throwable cause) {
        super(cause);
    }

    public MatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
