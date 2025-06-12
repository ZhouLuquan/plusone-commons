/*
 * Copyright 2024-2025 the original author or authors.
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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

/**
 * ArrayTools
 *
 * <p>
 * 数组工具类
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 */
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

    // #region - isEmpty

    // isEmpty

    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @param <T> 数组中元素的类型
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static <T> boolean isEmpty(@Nullable T[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - char
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable char[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - byte
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable byte[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - short
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable short[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - int
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable int[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - long
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable long[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - float
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable float[] arr) {
        return arr == null || arr.length == 0;
    }

    // isEmpty - double
    /**
     * 检查给定数组是否为空
     *
     * @param arr 待检查的数组，可以为 {@code null}
     * @return 如果数组为 {@code null} 或长度为 0，则返回  {@code true}；否则返回  {@code false}
     */
    public static boolean isEmpty(@Nullable double[] arr) {
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
    public static <T> boolean isAllElementsNotNull(final T[] arr) {
        checkArgument(arr != null, "The array cannot be null.");
        return Arrays.stream(arr).allMatch(Objects::nonNull);
    }

    // #endregion

    // #region - concat

    /**
     * 拼接多个数组
     *
     * @param arrays 数组集合，可以为 {@code null}
     * @return 拼接后的数组
     */
    public static char[] concatCharArray(@Nullable final Collection<char[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new char[0];
        }
        final Collection<char[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final char[] result = new char[length];
        int i = 0;
        for (char[] arr : arraysToConcat) {
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
    public static byte[] concatByteArray(@Nullable final Collection<byte[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new byte[0];
        }
        final Collection<byte[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final byte[] result = new byte[length];
        int i = 0;
        for (byte[] arr : arraysToConcat) {
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
    public static short[] concatShortArray(@Nullable final Collection<short[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new short[0];
        }
        final Collection<short[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final short[] result = new short[length];
        int i = 0;
        for (short[] arr : arraysToConcat) {
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
    public static int[] concatIntArray(@Nullable final Collection<int[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new int[0];
        }
        final Collection<int[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final int[] result = new int[length];
        int i = 0;
        for (int[] arr : arraysToConcat) {
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
    public static long[] concatLongArray(@Nullable final Collection<long[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new long[0];
        }
        final Collection<long[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final long[] result = new long[length];
        int i = 0;
        for (long[] arr : arraysToConcat) {
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
    public static float[] concatFloatArray(@Nullable final Collection<float[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new float[0];
        }
        final Collection<float[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final float[] result = new float[length];
        int i = 0;
        for (float[] arr : arraysToConcat) {
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
    public static double[] concatDoubleArray(@Nullable final Collection<double[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return new double[0];
        }
        final Collection<double[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final double[] result = new double[length];
        int i = 0;
        for (double[] arr : arraysToConcat) {
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
    public static <T> List<T> concatToList(@Nullable final Collection<T[]> arrays) {
        // 如果输入的集合是否为空，则直接返回一个空列表
        if (arrays == null || arrays.isEmpty()) {
            return Collections.emptyList();
        }

        final Collection<T[]> arraysToConcat = arrays
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 计算所有数组的总长度，用于初始化列表的容量
        final int length = arraysToConcat.stream().mapToInt(a -> a.length).sum();
        final List<T> result = new ArrayList<>(length);

        for (T[] arr : arraysToConcat) {
            Collections.addAll(result, arr);
        }

        // 返回连接后的列表
        return result;
    }

    // #endregion

    // #region - repeat

    // repeat - char

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static char[] repeat(char[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static char[] repeat(char[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static byte[] repeat(byte[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static byte[] repeat(byte[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static short[] repeat(short[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static short[] repeat(short[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static int[] repeat(int[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static int[] repeat(int[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static long[] repeat(long[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static long[] repeat(long[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static float[] repeat(float[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static float[] repeat(float[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 重复数组中的元素
     *
     * @param arr   重复内容
     * @param times 重复次数
     * @return 重复后的数组
     */
    public static double[] repeat(double[] arr, int times) {
        return repeat(arr, times, Integer.MAX_VALUE);
    }

    /**
     * 重复数组中的元素
     * @param arr       重复内容
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 重复后的数组
     */
    public static double[] repeat(double[] arr, int times, int maxLength) {
        checkArgument(Objects.nonNull(arr));
        checkArgument(times >= 0,
                "The number of times must be greater than or equal to zero");
        checkArgument(maxLength >= 0,
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

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(char[] a, @Nullable char[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(char[] a, @Nullable String values) {
        fill(a, 0, a.length, values != null ? values.toCharArray() : EMPTY_CHAR_ARRAY);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(char[] a, int fromIndex, int toIndex, @Nullable char[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - byte

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(byte[] a, @Nullable byte[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(byte[] a, int fromIndex, int toIndex, @Nullable byte[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - short

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(short[] a, @Nullable short[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(short[] a, int fromIndex, int toIndex, @Nullable short[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - int

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(int[] a, @Nullable int[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(int[] a, int fromIndex, int toIndex, @Nullable int[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - long

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(long[] a, @Nullable long[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(long[] a, int fromIndex, int toIndex, @Nullable long[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - float

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(float[] a, @Nullable float[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(float[] a, int fromIndex, int toIndex, @Nullable float[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - double

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static void fill(double[] a, @Nullable double[] values) {
        fill(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static void fill(double[] a, int fromIndex, int toIndex, @Nullable double[] values) {
        checkArgument(Objects.nonNull(a));
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

    // fill - T

    /**
     * 填充数组
     *
     * @param a      要填充的数组
     * @param values 填充内容
     */
    public static <T> void fill(T[] a, @Nullable T[] values) {
        fillInternal(a, 0, a.length, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    public static <T> void fill(T[] a, int fromIndex, int toIndex, @Nullable T[] values) {
        fillInternal(a, fromIndex, toIndex, values);
    }

    /**
     * 填充数组
     *
     * @param a         要填充的数组
     * @param fromIndex 开始位置
     * @param toIndex   结束位置
     * @param values    填充内容
     */
    private static <T> void fillInternal(T[] a, int fromIndex, int toIndex, @Nullable T[] values) {
        checkArgument(Objects.nonNull(a));
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

    public static <T> int indexOf(@Nullable T[] arr, Predicate<? super T> predicate) {
        checkNotNull(predicate);
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (predicate.test(arr[i])) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int indexOf(@Nullable T[] arr, @Nullable T obj) {
        return indexOf(arr, item -> Objects.equals(item, obj));
    }

    public static int indexOf(@Nullable char[] arr, char value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable byte[] arr, byte value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable short[] arr, short value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable long[] arr, long value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable float[] arr, float value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int indexOf(@Nullable double[] arr, double value) {
        if (arr == null || arr.length == 0) {
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

    public static <T> int lastIndexOf(@Nullable T[] arr, Predicate<? super T> predicate) {
        checkNotNull(predicate);
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (predicate.test(arr[i])) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static <T> int lastIndexOf(T[] arr, T obj) {
        return lastIndexOf(arr, item -> Objects.equals(item, obj));
    }

    public static int lastIndexOf(@Nullable char[] arr, char value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable byte[] arr, byte value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable short[] arr, short value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable long[] arr, long value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable float[] arr, float value) {
        if (arr == null || arr.length == 0) {
            return NOT_FOUND_INDEX;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (value == arr[i]) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    public static int lastIndexOf(@Nullable double[] arr, double value) {
        if (arr == null || arr.length == 0) {
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

    public static <T> boolean contains(@Nullable T[] arr, @Nullable T obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable char[] arr, char obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable byte[] arr, byte obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable short[] arr, short obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable int[] arr, int obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable long[] arr, long obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable float[] arr, float obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean contains(@Nullable double[] arr, double obj) {
        return indexOf(arr, obj) > NOT_FOUND_INDEX;
    }

    public static boolean containsValue(@Nullable BigDecimal[] arr, @Nullable BigDecimal obj) {
        return indexOf(arr, item -> BigDecimals.equalsValue(item, obj)) > NOT_FOUND_INDEX;
    }

    // #endregion

    // #region - private constructor

    private ArrayTools() {
        throw new IllegalStateException("Utility class");
    }

    // #endregion
}
