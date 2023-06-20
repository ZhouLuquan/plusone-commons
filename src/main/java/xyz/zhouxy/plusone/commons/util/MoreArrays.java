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
public class MoreArrays {

    public static float[] concatFloatArray(Collection<float[]> arrays) {
        int length = 0;
        for (float[] arr : arrays) {
            length += arr.length;
        }
        float[] result = new float[length];
        int i = 0;
        for (float[] arr : arrays) {
            for (float item : arr) {
                result[i++] = item;
            }
        }
        return result;
    }

    public static double[] concatDoubleArray(Collection<double[]> arrays) {
        int length = 0;
        for (double[] arr : arrays) {
            length += arr.length;
        }
        double[] result = new double[length];
        int i = 0;
        for (double[] arr : arrays) {
            for (double item : arr) {
                result[i++] = item;
            }
        }
        return result;
    }

    public static byte[] concatByteArray(Collection<byte[]> arrays) {
        int length = 0;
        for (byte[] arr : arrays) {
            length += arr.length;
        }
        byte[] result = new byte[length];
        int i = 0;
        for (byte[] arr : arrays) {
            for (byte item : arr) {
                result[i++] = item;
            }
        }
        return result;
    }

    public static long[] concatLongArray(Collection<long[]> arrays) {
        int length = 0;
        for (long[] arr : arrays) {
            length += arr.length;
        }
        long[] result = new long[length];
        int i = 0;
        for (long[] arr : arrays) {
            for (long item : arr) {
                result[i++] = item;
            }
        }
        return result;
    }

    public static int[] concatIntArray(Collection<int[]> arrays) {
        int length = 0;
        for (int[] arr : arrays) {
            length += arr.length;
        }
        int[] result = new int[length];
        int i = 0;
        for (int[] arr : arrays) {
            for (int item : arr) {
                result[i++] = item;
            }
        }
        return result;
    }

    public static <T> List<T> concatToList(@Nullable Collection<T[]> arrays) {
        if (arrays == null || arrays.isEmpty()) {
            return Collections.emptyList();
        }
        int length = 0;
        for (T[] arr : arrays) {
            length += arr.length;
        }
        final List<T> result = new ArrayList<>(length);
        for (T[] arr : arrays) {
            Collections.addAll(result, arr);
        }
        return result;
    }

    private MoreArrays() {
        throw new IllegalStateException("Utility class");
    }
}
