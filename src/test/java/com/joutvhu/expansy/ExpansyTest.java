package com.joutvhu.expansy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpansyTest {
    @Test
    public void test() {
        String a = "a";
        String ax = a.concat("x");
        Assertions.assertEquals("a", a);
        Assertions.assertEquals("ax", ax);
    }
}
