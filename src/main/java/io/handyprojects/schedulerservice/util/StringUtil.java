package io.handyprojects.schedulerservice.util;

import java.util.Objects;

public class StringUtil {

    public static String valueOf(Object o) {
        if (o == null)
            return null;
        return String.valueOf(o);
    }

    public static String getFirstNCharacterOf(String s, int n) {
        if (Objects.isNull(s))
            return null;
        return s.substring(0, Math.min(s.length(), n));
    }
}
