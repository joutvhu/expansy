package com.joutvhu.expansy.exception;

public class ParseErrorException extends ExpansyException {
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
}
