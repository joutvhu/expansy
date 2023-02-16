package com.joutvhu.expansy.match.filter;

public interface Filter {
    StopPoint next();

    StopPoint next(int length);

    void push();

    void push(int index);

    void close();

    void complete();

    void error(String message);
}
