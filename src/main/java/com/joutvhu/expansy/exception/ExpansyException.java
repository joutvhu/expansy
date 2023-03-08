package com.joutvhu.expansy.exception;

public class ExpansyException extends RuntimeException {
    public ExpansyException() {
    }

    public ExpansyException(String message) {
        super(message);
    }

    public ExpansyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpansyException(Throwable cause) {
        super(cause);
    }

    public static ExpansyException of(Throwable e) {
        if (e instanceof ExpansyException)
            return (ExpansyException) e;
        else
            return new ExpansyException(e);
    }

    public static ExpansyException or(ExpansyException e1, ExpansyException e2) {
        if (e2 == null)
            return e1;
        if (e1 == null)
            return e2;
        if (e1 instanceof MatchException) {
            if (e2 instanceof MatchException)
                return MatchException.or((MatchException) e1, (MatchException) e2);
            else
                return e1;
        }
        if (e2 instanceof MatchException)
            return e2;
        return e1;
    }

    public static ExpansyException or(Throwable e1, Throwable e2) {
        if (e2 == null) {
            if (e1 instanceof ExpansyException)
                return (ExpansyException) e1;
            else
                return new ExpansyException(e1);
        }
        if (e1 == null) {
            if (e2 instanceof ExpansyException)
                return (ExpansyException) e2;
            else
                return new ExpansyException(e2);
        }
        if (e1 instanceof ExpansyException && e2 instanceof ExpansyException)
            return or((ExpansyException) e1, (ExpansyException) e2);
        if (e1 instanceof ExpansyException)
            return (ExpansyException) e1;
        if (e2 instanceof ExpansyException)
            return (ExpansyException) e2;
        return new ExpansyException(e1);
    }
}
