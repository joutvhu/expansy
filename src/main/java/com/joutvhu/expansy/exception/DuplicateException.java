package com.joutvhu.expansy.exception;

import java.text.MessageFormat;

public class DuplicateException extends ExpansyException {
    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Object... values) {
        super(MessageFormat.format(message, values));
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(Throwable cause) {
        super(cause);
    }
}
