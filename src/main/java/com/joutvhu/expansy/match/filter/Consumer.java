package com.joutvhu.expansy.match.filter;

public interface Consumer {
    StopPoint next();

    StopPoint next(int length);

    void stack();

    void stack(int index);

    void push();

    void push(int index);

    void close();

    void complete();

    void error(String message);
}
