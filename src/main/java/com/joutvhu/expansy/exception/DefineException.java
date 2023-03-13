package com.joutvhu.expansy.exception;

public class DefineException extends ExpansyException {
    private static final long serialVersionUID = -6600630469092640337L;

    public DefineException(String message) {
        super(message);
    }

    public DefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefineException(Throwable cause) {
        super(cause);
    }
}
