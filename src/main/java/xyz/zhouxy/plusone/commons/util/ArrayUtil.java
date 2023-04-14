/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.util;

public class ArrayUtil {

    @SafeVarargs
    public static <T> T[] newArray(final T... values) {
        return values;
    }

    public static short[] newArray(final short... values) {
        return values;
    }

    public static int[] newArray(final int... values) {
        return values;
    }

    public static long[] newArray(final long... values) {
        return values;
    }

    public static byte[] newArray(final byte... values) {
        return values;
    }

    public static boolean[] newArray(final boolean... values) {
        return values;
    }

    public static char[] newArray(final char... values) {
        return values;
    }

    public static double[] newArray(final double... values) {
        return values;
    }

    public static float[] newArray(final float... values) {
        return values;
    }

    private ArrayUtil() {
        throw new IllegalStateException("Utility class");
    }
}
