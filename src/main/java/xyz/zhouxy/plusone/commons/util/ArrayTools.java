/*
 * Copyright 2024 the original author or authors.
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArrayTools {

    // #region - empty arrays

    public static final char[] EMPTY_CHAR_ARRAY = {};
    public static final byte[] EMPTY_BYTE_ARRAY = {};
    public static final short[] EMPTY_SHORT_ARRAY = {};
    public static final int[] EMPTY_INT_ARRAY = {};
    public static final long[] EMPTY_LONG_ARRAY = {};
    public static final float[] EMPTY_FLOAT_ARRAY = {};
    public static final double[] EMPTY_DOUBLE_ARRAY = {};

    // #endregion

    public static final int NOT_FOUND_INDEX = -1;

    // #region - isNullOrEmpty

    // isNullOrEmpty

    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @param <T> 数组中元素的类型
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static <T> boolean isNullOrEmpty(@Nullable T[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - char
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable char[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - byte
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable byte[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - short
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable short[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - int
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable int[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - long
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable long[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - float
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable float[] arr) {
        return arr == null || arr.length == 0;
    }

    // isNullOrEmpty - double
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNullOrEmpty(@Nullable double[] arr) {
        return arr == null || arr.length == 0;
    }

    // #endregion

    // #region - isNotEmpty

    // isNotEmpty
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @param <T> 数组中元素的类型
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static <T> boolean isNotEmpty(@Nullable T[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - char
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable char[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - byte
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable byte[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - short
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable short[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - int
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable int[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - long
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable long[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - float
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable float[] arr) {
        return arr != null && arr.length > 0;
    }

    // isNotEmpty - double
    /**
     * 检查给定数组是否不为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组不为 {@code null} 且长度大于 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isNotEmpty(@Nullable double[] arr) {
        return arr != null && arr.length > 0;
    }

    // #endregion

    // #region - isAllElementsNotNull

    /**
     * 判断数组的所有元素是否都不为空：
     * <ol>
     * <li><b>数组为 {@code null}</b>：抛出异常，因为传入 {@code null} 通常被视为编程错误。
     * <li><b>数组不为 {@code null} 但长度为 0</b>：在这种情况下，通常认为数组中的所有元素都不为 {@code null}，因此返回 {@code null}。
     * <li><b>数组中有至少一个元素为 {@code null}</b>：返回 {@code false}。
     * <li><b>数组中所有元素都不为 {@code null}</b>：返回 {@code true}。
     * </ol>
     *
     * @param <T> 数组元素的类型
     * @param arr 待检查的数组。不为 {@code null}
     * @return 如果数组的所有元素都不为 {@code null}，则返回  {@code true}；否则返回  {@code false}
     *
     * @throws IllegalArgumentException 当参数为空时抛出
     */
    public static <T> boolean isAllElementsNotNull(@Nonnull final T[] arr) {
        AssertTools.checkArgument(arr != null, "The array cannot be null.");
        for (T element : arr) {
            if (element == null) {
                return false;
            }
        }
        return true;
    }

    // #endregion

    // #region - concat

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static char[] concatCharArray(@Nullable Collection<char[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new char[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final char[] result = new char[length];
        int i = 0;
        for (char[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static byte[] concatByteArray(@Nullable Collection<byte[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new byte[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final byte[] result = new byte[length];
        int i = 0;
        for (byte[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static short[] concatShortArray(@Nullable Collection<short[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new short[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final short[] result = new short[length];
        int i = 0;
        for (short[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static int[] concatIntArray(@Nullable Collection<int[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new int[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final int[] result = new int[length];
        int i = 0;
        for (int[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static long[] concatLongArray(@Nullable Collection<long[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new long[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final long[] result = new long[length];
        int i = 0;
        for (long[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static float[] concatFloatArray(@Nullable Collection<float[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new float[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final float[] result = new float[length];
        int i = 0;
        for (float[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static double[] concatDoubleArray(@Nullable Collection<double[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new double[0];
        }
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final double[] result = new double[length];
        int i = 0;
        for (double[] arr : arrays) {
            System.arraycopy(arr, 0, result, i, arr.length);
            i += arr.length;
        }
        return result;
    }

    /**
     * 将集合中的数组连接为一个列表
     *
     * @param arrays 可能包含多个数组的集合，这些数组中的元素将被连接到一个列表中
     * @param <T> 泛型参数，表示数组的元素类型
     * @return 返回连接后的列表，如果输入的集合为空或包含空数组，则返回空列表
     */
    public static <T> List<T> concatToList(@Nullable Collection<T[]> arrays) {
        // 如果输入的集合是否为空，则直接返回一个空列表
        if (arrays == null || arrays.isEmpty()) {
            return Collections.emptyList();
        }

        // 计算所有数组的总长度，用于初始化列表的容量
        final int length = arrays.stream().mapToInt(a -> a.length).sum();
        final List<T> result = new ArrayList<>(length);

        for (T[] arr : arrays) {
            Collections.addAll(result, arr);
        }

        // 返回连接后的列表
        return result;
    }

    // #endregion

    // #region - repeat

    // repeat - char

    public static char[] repeat(char[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static char[] repeat(char[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final char[] result = new char[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - byte

    public static byte[] repeat(byte[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static byte[] repeat(byte[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final byte[] result = new byte[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - short

    public static short[] repeat(short[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static short[] repeat(short[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final short[] result = new short[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - int

    public static int[] repeat(int[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static int[] repeat(int[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_INT_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final int[] result = new int[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - long

    public static long[] repeat(long[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static long[] repeat(long[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_LONG_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final long[] result = new long[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - float

    public static float[] repeat(float[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static float[] repeat(float[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final float[] result = new float[length];
        fill(result, 0, length, arr);
        return result;
    }

    // repeat - double

    public static double[] repeat(double[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    public static double[] repeat(double[] arr, int times, int maxLength) {
        AssertTools.checkArgumentNotNull(arr);
        AssertTools.checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        AssertTools.checkArgument(maxLength >= 0,
                "The max length must be greater than or equal to zero");
        if (times == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        final int length = Integer.min(arr.length * times, maxLength);
        final double[] result = new double[length];
        fill(result, 0, length, arr);
        return result;
    }

    // #endregion

    // #region - fill

    // fill - char

    public static void fill(char[] a, char... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(char[] a, String values) {
        fill(a, 0, a.length, values != null ? values.toCharArray() : EMPTY_CHAR_ARRAY);
    }

    public static void fill(char[] a, int fromIndex, int toIndex, char... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - byte

    public static void fill(byte[] a, byte... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(byte[] a, int fromIndex, int toIndex, byte... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - short

    public static void fill(short[] a, short... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(short[] a, int fromIndex, int toIndex, short... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - int

    public static void fill(int[] a, int... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(int[] a, int fromIndex, int toIndex, int... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - long

    public static void fill(long[] a, long... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(long[] a, int fromIndex, int toIndex, long... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - float

    public static void fill(float[] a, float... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(float[] a, int fromIndex, int toIndex, float... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - double

    public static void fill(double[] a, double... values) {
        fill(a, 0, a.length, values);
    }

    public static void fill(double[] a, int fromIndex, int toIndex, double... values) {
        AssertTools.checkArgumentNotNull(a);
        if (values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // fill - T

    public static <T> void fill(@Nonnull T[] a, T[] values) {
        fillInternal(a, 0, a.length, values);
    }

    public static <T> void fill(@Nonnull T[] a, int fromIndex, int toIndex, T[] values) {
        fillInternal(a, fromIndex, toIndex, values);
    }

    private static <T> void fillInternal(@Nonnull T[] a, int fromIndex, int toIndex, @Nullable T[] values) {
        AssertTools.checkArgumentNotNull(a);
        if (values == null || values.length == 0) {
            return;
        }
        final int start = Integer.max(fromIndex, 0);
        final int end = Integer.min(toIndex, a.length);
        if (start >= end) {
            return;
        }
        for (int i = start; i < end; i += values.length) {
            for (int j = 0; j < values.length; j++) {
                final int k = (i + j);
                if (k < end) {
                    a[k] = values[j];
                }
                else {
                    break;
                }
            }
        }
    }

    // #endregion

    // #region - indexOf

    public static <T> int indexOf(T[] arr, Predicate<? super T> predicate) { // TODO 单元测试
        AssertTools.checkNotNull(predicate);
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (predicate.test(arr[i])) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int indexOf(T[] arr, T obj) { // TODO 单元测试
        return indexOf(arr, item -> Objects.equals(item, obj));
    }

    public static int indexOf(char[] arr, char value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(byte[] arr, byte value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(short[] arr, short value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(int[] arr, int value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(long[] arr, long value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(float[] arr, float value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(double[] arr, double value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    // #endregion

    // #region - lastIndexOf

    public static <T> int lastIndexOf(T[] arr, @Nonnull Predicate<? super T> predicate) { // TODO 单元测试
        AssertTools.checkNotNull(predicate);
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (predicate.test(arr[i])) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(T[] arr, T obj) { // TODO 单元测试
        return lastIndexOf(arr, item -> Objects.equals(item, obj));
    }

    public static <T> int lastIndexOf(char[] arr, char value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(byte[] arr, byte value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(short[] arr, short value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(int[] arr, int value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(long[] arr, long value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(float[] arr, float value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(double[] arr, double value) { // TODO 单元测试
        if (isNullOrEmpty(arr)) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    // #endregion

    // #region - contains

    public static <T> boolean contains(T[] arr, T obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(char[] arr, char obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(byte[] arr, byte obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(short[] arr, short obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(int[] arr, int obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(long[] arr, long obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(float[] arr, float obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(double[] arr, double obj) { // TODO 单元测试
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean containsValue(BigDecimal[] arr, BigDecimal obj) { // TODO 单元测试
        return indexOf(arr, item -> BigDecimals.equalsValue(item, obj)) > NOT_FOUND_INDEX;
    }

    // #endregion

    // #region - private constructor

    private ArrayTools() {
        throw new IllegalStateException("Utility class");
    }

    // #endregion
}
