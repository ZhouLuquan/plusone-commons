package xyz.zhouxy.plusone.commons.util;

public abstract class Arrays2 {

    @SafeVarargs
    public static <T> T[] of(final T... values) {
        return values;
    }

    public static short[] of(final short... values) {
        return values;
    }

    public static int[] of(final int... values) {
        return values;
    }

    public static long[] of(final long... values) {
        return values;
    }

    public static byte[] of(final byte... values) {
        return values;
    }

    public static boolean[] of(final boolean... values) {
        return values;
    }

    public static char[] of(final char... values) {
        return values;
    }

    private Arrays2() {
        throw new IllegalStateException("Utility class");
    }
}
