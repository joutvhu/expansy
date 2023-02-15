package com.joutvhu.expansy.io;

public interface Source {
    long back(long offset);

    String read(int length);

    String read(long offset, int length);

    void close();
}
