package com.joutvhu.expansy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtils {
    public char[] clone(char[] array) {
        return array == null ? null : (char[]) array.clone();
    }

    public char[] addAll(char[] array1, char... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            char[] joinedArray = new char[array1.length + array2.length];
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
        }
    }
}
