package com.joutvhu.dynamic.expression.analysis.match;

public abstract class LinearFilter {
    private StopPoint point;

    public abstract StopPoint next();

    public abstract StopPoint next(int length);

    public abstract void push();

    public abstract void enough();

    public abstract void complete();

    public abstract void error(String message);
}
