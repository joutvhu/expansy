package com.joutvhu.expansy.exception;

import java.text.MessageFormat;

public class DuplicateException extends ExpansyException {
    private static final long serialVersionUID = -4757070429210931510L;

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
