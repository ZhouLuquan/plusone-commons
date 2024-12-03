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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@SuppressWarnings("null")
public class ArrayToolsTests {

    // TODO 【优化】 检查、完善测试用例

    //#region null or empty

    @Test
    void isNullOrEmpty_NullArray_ReturnsTrue() {
        assertAll(
                () -> assertTrue(ArrayTools.isNullOrEmpty((String[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((Integer[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((char[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((byte[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((short[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((int[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((long[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((float[]) null)),
                () -> assertTrue(ArrayTools.isNullOrEmpty((double[]) null)));
    }

    @Test
    void isNullOrEmpty_EmptyArray_ReturnsTrue() {
        assertAll(
                () -> assertTrue(ArrayTools.isNullOrEmpty(new String[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new Integer[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new char[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new byte[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new short[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new int[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new long[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new float[] {})),
                () -> assertTrue(ArrayTools.isNullOrEmpty(new double[] {})));
    }

    @Test
    void isNullOrEmpty_NonEmptyArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isNullOrEmpty(new String[] { "a" })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new Integer[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new char[] { 'a' })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new byte[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new short[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new int[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new long[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new float[] { 1 })),
                () -> assertFalse(ArrayTools.isNullOrEmpty(new double[] { 1 })));
    }

    @Test
    void isNotEmpty_NullArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isNotEmpty((String[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((Integer[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((char[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((byte[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((short[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((int[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((long[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((float[]) null)),
                () -> assertFalse(ArrayTools.isNotEmpty((double[]) null)));
    }

    @Test
    void isNotEmpty_EmptyArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isNotEmpty(new String[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new Integer[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new char[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new byte[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new short[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new int[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new long[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new float[] {})),
                () -> assertFalse(ArrayTools.isNotEmpty(new double[] {})));
    }

    @Test
    void isNotEmpty_NonEmptyArray_ReturnsTrue() {
        assertAll(
                () -> assertTrue(ArrayTools.isNotEmpty(new String[] { "a" })),
                () -> assertTrue(ArrayTools.isNotEmpty(new Integer[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new char[] { 'a' })),
                () -> assertTrue(ArrayTools.isNotEmpty(new byte[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new short[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new int[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new long[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new float[] { 1 })),
                () -> assertTrue(ArrayTools.isNotEmpty(new double[] { 1 })));
    }

    @Test
    void isAllElementsNotNull_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.isAllElementsNotNull((String[]) null));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.isAllElementsNotNull((Integer[]) null));
    }

    @Test
    void isAllElementsNotNull_EmptyArray_ReturnsTrue() {
        assertTrue(ArrayTools.isAllElementsNotNull(new String[] {}));
        assertTrue(ArrayTools.isAllElementsNotNull(new Integer[] {}));
    }

    @Test
    void isAllElementsNotNull_ArrayWithNullElement_ReturnsFalse() {
        assertFalse(ArrayTools.isAllElementsNotNull(new String[] { "a", null }));
        assertFalse(ArrayTools.isAllElementsNotNull(new Integer[] { 1, null }));
    }

    @Test
    void isAllElementsNotNull_ArrayWithoutNullElements_ReturnsTrue() {
        assertTrue(ArrayTools.isAllElementsNotNull(new String[] { "a", "b" }));
        assertTrue(ArrayTools.isAllElementsNotNull(new Integer[] { 1, 2 }));
    }

    // #endregion

    // #region - concat

    private static List<char[]> charArrays;
    private static List<byte[]> byteArrays;
    private static List<short[]> shortArrays;
    private static List<int[]> intArrays;
    private static List<long[]> longArrays;
    private static List<float[]> floatArrays;
    private static List<double[]> doubleArrays;

    @BeforeAll
    public static void setUp() {
        charArrays = new ArrayList<>();
        charArrays.add(new char[] { 'a', 'b' });
        charArrays.add(new char[] { 'c', 'd', 'e' });
        charArrays.add(null);

        byteArrays = new ArrayList<>();
        byteArrays.add(new byte[] { 1, 2 });
        byteArrays.add(new byte[] { 3, 4, 5 });
        byteArrays.add(null);

        shortArrays = new ArrayList<>();
        shortArrays.add(new short[] { 10, 20 });
        shortArrays.add(new short[] { 30, 40, 50 });
        shortArrays.add(null);

        intArrays = new ArrayList<>();
        intArrays.add(new int[] { 100, 200 });
        intArrays.add(new int[] { 300, 400, 500 });
        intArrays.add(null);

        longArrays = new ArrayList<>();
        longArrays.add(new long[] { 1000L, 2000L });
        longArrays.add(new long[] { 3000L, 4000L, 5000L });
        longArrays.add(null);

        floatArrays = new ArrayList<>();
        floatArrays.add(new float[] { 1000.1f, 2000.2f });
        floatArrays.add(new float[] { 3000.3f, 4000.4f, 5000.5f });
        floatArrays.add(null);

        doubleArrays = new ArrayList<>();
        doubleArrays.add(new double[] { 1000.1d, 2000.2d });
        doubleArrays.add(new double[] { 3000.3d, 4000.4d, 5000.5d });
        doubleArrays.add(null);
    }

    @Test
    public void testConcatCharArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new char[] {}, ArrayTools.concatCharArray(null));
        assertEquals(0, ArrayTools.concatCharArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatCharArray_ValidCollection_ReturnsConcatenatedArray() {
        char[] expected = { 'a', 'b', 'c', 'd', 'e' };
        char[] result = ArrayTools.concatCharArray(charArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatByteArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new byte[0], ArrayTools.concatByteArray(null));
        assertEquals(0, ArrayTools.concatByteArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatByteArray_ValidCollection_ReturnsConcatenatedArray() {
        byte[] expected = { 1, 2, 3, 4, 5 };
        byte[] result = ArrayTools.concatByteArray(byteArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatShortArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new short[0], ArrayTools.concatShortArray(null));
        assertEquals(0, ArrayTools.concatShortArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatShortArray_ValidCollection_ReturnsConcatenatedArray() {
        short[] expected = { 10, 20, 30, 40, 50 };
        short[] result = ArrayTools.concatShortArray(shortArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatIntArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new int[0], ArrayTools.concatIntArray(null));
        assertEquals(0, ArrayTools.concatIntArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatIntArray_ValidCollection_ReturnsConcatenatedArray() {
        int[] expected = { 100, 200, 300, 400, 500 };
        int[] result = ArrayTools.concatIntArray(intArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatLongArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new long[0], ArrayTools.concatLongArray(null));
        assertEquals(0, ArrayTools.concatLongArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatLongArray_ValidCollection_ReturnsConcatenatedArray() {
        long[] expected = { 1000L, 2000L, 3000L, 4000L, 5000L };
        long[] result = ArrayTools.concatLongArray(longArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatFloatArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new float[0], ArrayTools.concatFloatArray(null));
        assertEquals(0, ArrayTools.concatFloatArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatFloatArray_ValidCollection_ReturnsConcatenatedArray() {
        float[] expected = { 1000.1f, 2000.2f, 3000.3f, 4000.4f, 5000.5f };
        float[] result = ArrayTools.concatFloatArray(floatArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatDoubleArray_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertArrayEquals(new double[] {}, ArrayTools.concatDoubleArray(null));
        assertEquals(0, ArrayTools.concatDoubleArray(Collections.emptyList()).length);
    }

    @Test
    public void testConcatDoubleArray_ValidCollection_ReturnsConcatenatedArray() {
        double[] expected = { 1000.1d, 2000.2d, 3000.3d, 4000.4d, 5000.5d };
        double[] result = ArrayTools.concatDoubleArray(doubleArrays);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConcatToList_NullOrEmptyCollection_ReturnsEmptyList() {
        assertTrue(ArrayTools.concatToList(null).isEmpty());
        assertTrue(ArrayTools.concatToList(Collections.emptyList()).isEmpty());
    }

    @Test
    public void testConcatToList_ValidCollection_ReturnsConcatenatedList() {
        Character[] charArray1 = { 'a', 'b' };
        Character[] charArray2 = { 'c', 'd', 'e' };
        List<Character> expected = Arrays.asList('a', 'b', 'c', 'd', 'e');
        List<Character> result = ArrayTools.concatToList(Arrays.asList(charArray1, charArray2));
        assertEquals(expected, result);
    }

    // #endregion

    // #region - repeat

    @Test
    void repeat_CharArray_TimesZero_ReturnsEmptyArray() {
        char[] input = { 'a', 'b', 'c' };
        char[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_CHAR_ARRAY, result);
    }

    @Test
    void repeat_CharArray_TimesOne_ReturnsSameArray() {
        char[] input = { 'a', 'b', 'c' };
        char[] result = ArrayTools.repeat(input, 1);
        assertArrayEquals(input, result);
    }

    @Test
    void repeat_CharArray_TimesTwo_ReturnsRepeatedArray() {
        char[] input = { 'a', 'b', 'c' };
        char[] expected = { 'a', 'b', 'c', 'a', 'b', 'c' };
        char[] result = ArrayTools.repeat(input, 2);
        assertArrayEquals(expected, result);
    }

    @Test
    void repeat_CharArray_WithMaxLength_ReturnsTruncatedArray() {
        char[] input = { 'a', 'b', 'c' };
        char[] expected = { 'a', 'b', 'c', 'a', 'b' };
        char[] result = ArrayTools.repeat(input, 2, 5);
        assertArrayEquals(expected, result);
    }

    @Test
    void repeat_ByteArray_TimesZero_ReturnsEmptyArray() {
        byte[] input = { 1, 2, 3 };
        byte[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_BYTE_ARRAY, result);
    }

    @Test
    void repeat_ShortArray_TimesZero_ReturnsEmptyArray() {
        short[] input = { 1, 2, 3 };
        short[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_SHORT_ARRAY, result);
    }

    @Test
    void repeat_IntArray_TimesZero_ReturnsEmptyArray() {
        int[] input = { 1, 2, 3 };
        int[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_INT_ARRAY, result);
    }

    @Test
    void repeat_LongArray_TimesZero_ReturnsEmptyArray() {
        long[] input = { 1, 2, 3 };
        long[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_LONG_ARRAY, result);
    }

    @Test
    void repeat_FloatArray_TimesZero_ReturnsEmptyArray() {
        float[] input = { 1, 2, 3 };
        float[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_FLOAT_ARRAY, result);
    }

    @Test
    void repeat_DoubleArray_TimesZero_ReturnsEmptyArray() {
        double[] input = { 1, 2, 3 };
        double[] result = ArrayTools.repeat(input, 0);
        assertArrayEquals(ArrayTools.EMPTY_DOUBLE_ARRAY, result);
    }

    @Test
    void repeat_CharArray_ThrowsExceptionForNullArray() {
        Executable executable = () -> ArrayTools.repeat((char[]) null, 2);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void repeat_CharArray_ThrowsExceptionForNegativeTimes() {
        char[] input = { 'a', 'b', 'c' };
        Executable executable = () -> ArrayTools.repeat(input, -1);
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void repeat_CharArray_ThrowsExceptionForNegativeMaxLength() {
        char[] input = { 'a', 'b', 'c' };
        Executable executable = () -> ArrayTools.repeat(input, 2, -1);
        assertThrows(IllegalArgumentException.class, executable);
    }

    // #endregion

    // TODO 【添加】 补充测试用例

    // #region - fill
    // #endregion

    // #region - indexOf
    // #endregion

    // #region - lastIndexOf
    // #endregion

    // #region - contains
    // #endregion

}
