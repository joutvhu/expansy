package com.joutvhu.expansy.io;

public interface Source {
    void reset();

    long back(long offset);

    String read(int length);

    String read(long offset, int length);
}
