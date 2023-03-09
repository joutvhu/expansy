package com.joutvhu.expansy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {
    public int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    public boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
