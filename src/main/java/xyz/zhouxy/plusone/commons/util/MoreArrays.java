package xyz.zhouxy.plusone.commons.util;

import java.util.Arrays;

import com.google.common.annotations.Beta;

@Beta
public class MoreArrays {

    public static Object[] asObjectArray(final Object... objects) {
        return objects;
    }

    public static char[] newCharArrayWith(int length, char c) {
        char[] arr = new char[length];
        Arrays.fill(arr, c);
        return arr;
    }

    public static String[] newStringArrayWith(int length, String c) {
        String[] arr = new String[length];
        Arrays.fill(arr, c);
        return arr;
    }

    private MoreArrays() {
        throw new IllegalStateException("Utility class");
    }
}
