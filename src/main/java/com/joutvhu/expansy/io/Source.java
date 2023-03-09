package com.joutvhu.expansy.io;

public interface Source {
    int length();

    String read(int offset, int length);
}
