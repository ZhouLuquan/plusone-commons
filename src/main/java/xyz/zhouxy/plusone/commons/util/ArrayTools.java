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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

@Beta
public class ArrayTools {

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

    // concat

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

    private ArrayTools() {
        throw new IllegalStateException("Utility class");
    }
}
