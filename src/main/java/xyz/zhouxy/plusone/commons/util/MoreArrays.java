package xyz.zhouxy.plusone.commons.util;

import javax.annotation.Nullable;

public class MoreArrays {

    public static boolean isEmpty(@Nullable int[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNotEmpty(@Nullable int[] arr) {
        return arr != null && arr.length > 0;
    }

    public static boolean isEmpty(@Nullable long[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNotEmpty(@Nullable long[] arr) {
        return arr != null && arr.length > 0;
    }

    public static boolean isEmpty(@Nullable double[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNotEmpty(@Nullable double[] arr) {
        return arr != null && arr.length > 0;
    }

    public static <T> boolean isEmpty(@Nullable T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static <T> boolean isNotEmpty(@Nullable T[] arr) {
        return arr != null && arr.length > 0;
    }

    private MoreArrays() {
        throw new IllegalStateException("Utility class");
    }
}
