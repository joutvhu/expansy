package com.joutvhu.dynamic.expression.analysis.exception;

public class ParseErrorException extends ExpressionException {
    public ParseErrorException() {
    }

    public ParseErrorException(String message) {
        super(message);
    }

    public ParseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseErrorException(Throwable cause) {
        super(cause);
    }

    public ParseErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
