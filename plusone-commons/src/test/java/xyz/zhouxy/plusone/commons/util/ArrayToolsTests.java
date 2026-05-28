/*
 * Copyright 2024-present ZhouXY
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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

@SuppressWarnings("null")
public class ArrayToolsTests {

    static final String[] NULL_STRING_ARRAY = null;
    static final Integer[] NULL_INTEGER_ARRAY = null;
    static final char[] NULL_CHAR_ARRAY = null;
    static final byte[] NULL_BYTE_ARRAY = null;
    static final short[] NULL_SHORT_ARRAY = null;
    static final int[] NULL_INT_ARRAY = null;
    static final long[] NULL_LONG_ARRAY = null;
    static final float[] NULL_FLOAT_ARRAY = null;
    static final double[] NULL_DOUBLE_ARRAY = null;

    static final String[] EMPTY_STRING_ARRAY = {};
    static final Integer[] EMPTY_INTEGER_ARRAY = {};
    static final char[] EMPTY_CHAR_ARRAY = {};
    static final byte[] EMPTY_BYTE_ARRAY = {};
    static final short[] EMPTY_SHORT_ARRAY = {};
    static final int[] EMPTY_INT_ARRAY = {};
    static final long[] EMPTY_LONG_ARRAY = {};
    static final float[] EMPTY_FLOAT_ARRAY = {};
    static final double[] EMPTY_DOUBLE_ARRAY = {};

    // ================================
    // #region - isEmpty
    // ================================

    @Test
    void isEmpty_NullArray_ReturnsTrue() {
        assertAll(
                () -> assertTrue(ArrayTools.isEmpty(NULL_STRING_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_INTEGER_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_CHAR_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_BYTE_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_SHORT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_INT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_LONG_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_FLOAT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(NULL_DOUBLE_ARRAY)));
    }

    @Test
    void isEmpty_EmptyArray_ReturnsTrue() {
        assertAll(
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_STRING_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_INTEGER_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_CHAR_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_BYTE_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_SHORT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_INT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_LONG_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_FLOAT_ARRAY)),
                () -> assertTrue(ArrayTools.isEmpty(EMPTY_DOUBLE_ARRAY)));
    }

    @Test
    void isEmpty_NonEmptyArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isEmpty(new String[] { "a" })),
                () -> assertFalse(ArrayTools.isEmpty(new Integer[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new char[] { 'a' })),
                () -> assertFalse(ArrayTools.isEmpty(new byte[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new short[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new int[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new long[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new float[] { 1 })),
                () -> assertFalse(ArrayTools.isEmpty(new double[] { 1 })));
    }

    // ================================
    // #endregion - isEmpty
    // ================================

    // ================================
    // #region - isNotEmpty
    // ================================

    @Test
    void isNotEmpty_NullArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_STRING_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_INTEGER_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_CHAR_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_BYTE_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_SHORT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_INT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_LONG_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_FLOAT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(NULL_DOUBLE_ARRAY)));
    }

    @Test
    void isNotEmpty_EmptyArray_ReturnsFalse() {
        assertAll(
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_STRING_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_INTEGER_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_CHAR_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_BYTE_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_SHORT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_INT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_LONG_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_FLOAT_ARRAY)),
                () -> assertFalse(ArrayTools.isNotEmpty(EMPTY_DOUBLE_ARRAY)));
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

    // ================================
    // #endregion - isNotEmpty
    // ================================

    // ================================
    // #region - isAllElementsNotNull
    // ================================

    @Test
    void isAllElementsNotNull_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.isAllElementsNotNull(NULL_STRING_ARRAY));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.isAllElementsNotNull(NULL_INTEGER_ARRAY));
    }

    @Test
    void isAllElementsNotNull_EmptyArray_ReturnsTrue() {
        assertTrue(ArrayTools.isAllElementsNotNull(EMPTY_STRING_ARRAY));
        assertTrue(ArrayTools.isAllElementsNotNull(EMPTY_INTEGER_ARRAY));
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

    // ================================
    // #endregion - isAllElementsNotNull
    // ================================

    // ================================
    // #region - concat
    // ================================

    static final List<char[]> charArrays;
    static final List<byte[]> byteArrays;
    static final List<short[]> shortArrays;
    static final List<int[]> intArrays;
    static final List<long[]> longArrays;
    static final List<float[]> floatArrays;
    static final List<double[]> doubleArrays;

    static {
        charArrays = new ArrayList<>();
        charArrays.add(null);
        charArrays.add(new char[0]);
        charArrays.add(new char[] { 'a', 'b' });
        charArrays.add(new char[] { 'c', 'd', 'e' });

        byteArrays = new ArrayList<>();
        byteArrays.add(null);
        byteArrays.add(new byte[0]);
        byteArrays.add(new byte[] { 1, 2 });
        byteArrays.add(new byte[] { 3, 4, 5 });

        shortArrays = new ArrayList<>();
        shortArrays.add(null);
        shortArrays.add(new short[0]);
        shortArrays.add(new short[] { 10, 20 });
        shortArrays.add(new short[] { 30, 40, 50 });

        intArrays = new ArrayList<>();
        intArrays.add(null);
        intArrays.add(new int[0]);
        intArrays.add(new int[] { 100, 200 });
        intArrays.add(new int[] { 300, 400, 500 });

        longArrays = new ArrayList<>();
        longArrays.add(null);
        longArrays.add(new long[0]);
        longArrays.add(new long[] { 1000L, 2000L });
        longArrays.add(new long[] { 3000L, 4000L, 5000L });

        floatArrays = new ArrayList<>();
        floatArrays.add(null);
        floatArrays.add(new float[0]);
        floatArrays.add(new float[] { 1000.1f, 2000.2f });
        floatArrays.add(new float[] { 3000.3f, 4000.4f, 5000.5f });

        doubleArrays = new ArrayList<>();
        doubleArrays.add(null);
        doubleArrays.add(new double[0]);
        doubleArrays.add(new double[] { 1000.1d, 2000.2d });
        doubleArrays.add(new double[] { 3000.3d, 4000.4d, 5000.5d });
    }

    @Test
    void concat_NullOrEmptyCollection_ReturnsEmptyArray() {
        assertEquals(0, ArrayTools.concatCharArray(null).length);
        assertEquals(0, ArrayTools.concatCharArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatByteArray(null).length);
        assertEquals(0, ArrayTools.concatByteArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatShortArray(null).length);
        assertEquals(0, ArrayTools.concatShortArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatIntArray(null).length);
        assertEquals(0, ArrayTools.concatIntArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatLongArray(null).length);
        assertEquals(0, ArrayTools.concatLongArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatFloatArray(null).length);
        assertEquals(0, ArrayTools.concatFloatArray(Collections.emptyList()).length);

        assertEquals(0, ArrayTools.concatDoubleArray(null).length);
        assertEquals(0, ArrayTools.concatDoubleArray(Collections.emptyList()).length);
    }

    @Test
    void concat_ValidCollection_ReturnsConcatenatedArray() {
        assertArrayEquals(new char[] { 'a', 'b', 'c', 'd', 'e' },
                ArrayTools.concatCharArray(charArrays));

        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5 },
                ArrayTools.concatByteArray(byteArrays));

        assertArrayEquals(new short[] { 10, 20, 30, 40, 50 },
                ArrayTools.concatShortArray(shortArrays));

        assertArrayEquals(new int[] { 100, 200, 300, 400, 500 },
                ArrayTools.concatIntArray(intArrays));

        assertArrayEquals(new long[] { 1000L, 2000L, 3000L, 4000L, 5000L },
                ArrayTools.concatLongArray(longArrays));

        assertArrayEquals(new float[] { 1000.1f, 2000.2f, 3000.3f, 4000.4f, 5000.5f },
                ArrayTools.concatFloatArray(floatArrays));

        assertArrayEquals(new double[] { 1000.1d, 2000.2d, 3000.3d, 4000.4d, 5000.5d },
                ArrayTools.concatDoubleArray(doubleArrays));
    }

    @Test
    void concatToList_NullOrEmptyCollection_ReturnsEmptyList() {
        assertTrue(ArrayTools.concatToList(null).isEmpty());
        assertTrue(ArrayTools.concatToList(Collections.emptyList()).isEmpty());
    }

    @Test
    void concatToList_ValidCollection_ReturnsConcatenatedList() {
        Character[] charArray1 = { 'a', 'b' };
        Character[] charArray2 = { 'c', 'd', 'e' };
        List<Character> expected = Arrays.asList('a', 'b', 'c', 'd', 'e');
        List<Character> result = ArrayTools.concatToList(Arrays.asList(charArray1, charArray2));
        assertEquals(expected, result);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - repeat
    // ================================

    @Test
    void repeat_NullArray_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_CHAR_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_BYTE_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_SHORT_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_INT_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_LONG_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_FLOAT_ARRAY, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_DOUBLE_ARRAY, 2));

        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_CHAR_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_BYTE_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_SHORT_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_INT_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_LONG_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_FLOAT_ARRAY, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(NULL_DOUBLE_ARRAY, 2, 2));
    }

    @Test
    void repeat_NegativeTimes_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new char[]{ 'a' }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new byte[]{ 1 }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new short[]{ 1 }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new int[]{ 1 }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new long[]{ 1 }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new float[]{ 1 }, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new double[]{ 1 }, -1));

        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new char[]{ 'a' }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new byte[]{ 1 }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new short[]{ 1 }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new int[]{ 1 }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new long[]{ 1 }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new float[]{ 1 }, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new double[]{ 1 }, -1, 2));
    }

    @Test
    void repeat_NegativeMaxLength_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new char[]{ 'a' }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new byte[]{ 1 }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new short[]{ 1 }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new int[]{ 1 }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new long[]{ 1 }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new float[]{ 1 }, 2, -1));
        assertThrows(IllegalArgumentException.class, () -> ArrayTools.repeat(new double[]{ 1 }, 2, -1));
    }

    @Test
    void repeat_ZeroTimes_ReturnsEmptyArray() {
        assertEquals(0, ArrayTools.repeat(new char[]{ 'a' }, 0).length);
        assertEquals(0, ArrayTools.repeat(new byte[]{ 1 }, 0).length);
        assertEquals(0, ArrayTools.repeat(new short[]{ 1 }, 0).length);
        assertEquals(0, ArrayTools.repeat(new int[]{ 1 }, 0).length);
        assertEquals(0, ArrayTools.repeat(new long[]{ 1 }, 0).length);
        assertEquals(0, ArrayTools.repeat(new float[]{ 1 }, 0).length);
        assertEquals(0, ArrayTools.repeat(new double[]{ 1 }, 0).length);
    }

    @Test
    void repeat_NormalCase_RepeatsArray() {
        assertArrayEquals(new char[] { 'a', 'b', 'a', 'b', 'a', 'b' }, ArrayTools.repeat(new char[] { 'a', 'b' }, 3));
        assertArrayEquals(new byte[] { 1, 2, 1, 2, 1, 2 }, ArrayTools.repeat(new byte[] { 1, 2 }, 3));
        assertArrayEquals(new short[] { 1, 2, 1, 2, 1, 2 }, ArrayTools.repeat(new short[] { 1, 2 }, 3));
        assertArrayEquals(new int[] { 1, 2, 1, 2, 1, 2 }, ArrayTools.repeat(new int[] { 1, 2 }, 3));
        assertArrayEquals(new long[] { 1L, 2L, 1L, 2L, 1L, 2L }, ArrayTools.repeat(new long[] { 1L, 2L }, 3));
        assertArrayEquals(new float[] { 1.1F, 2.2F, 1.1F, 2.2F, 1.1F, 2.2F }, ArrayTools.repeat(new float[] { 1.1F, 2.2F }, 3));
        assertArrayEquals(new double[] { 1.12, 2.23, 1.12, 2.23, 1.12, 2.23 }, ArrayTools.repeat(new double[] { 1.12, 2.23 }, 3));
    }

    @Test
    void repeat_WithMaxLength_TruncatesResult() {
        assertArrayEquals(new char[] { 'a', 'b', 'a', 'b', 'a' }, ArrayTools.repeat(new char[] { 'a', 'b' }, 3, 5));
        assertArrayEquals(new byte[] { 1, 2, 1, 2, 1 }, ArrayTools.repeat(new byte[] { 1, 2 }, 3, 5));
        assertArrayEquals(new short[] { 1, 2, 1, 2, 1 }, ArrayTools.repeat(new short[] { 1, 2 }, 3, 5));
        assertArrayEquals(new int[] { 1, 2, 1, 2, 1 }, ArrayTools.repeat(new int[] { 1, 2 }, 3, 5));
        assertArrayEquals(new long[] { 1L, 2L, 1L, 2L, 1L }, ArrayTools.repeat(new long[] { 1L, 2L }, 3, 5));
        assertArrayEquals(new float[] { 1.1F, 2.2F, 1.1F, 2.2F, 1.1F }, ArrayTools.repeat(new float[] { 1.1F, 2.2F }, 3, 5));
        assertArrayEquals(new double[] { 1.12, 2.23, 1.12, 2.23, 1.12 }, ArrayTools.repeat(new double[] { 1.12, 2.23 }, 3, 5));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - fill
    // ================================

    @Test
    void fill_WithValues() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, new char[] {'a', 'b', 'c'});
        assertArrayEquals(new char[] { 'a', 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'c', 'a' }, charArray);
        ArrayTools.fill(charArray, "abcd");
        assertArrayEquals(new char[] { 'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd', 'a', 'b' }, charArray);

        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, new byte[] { 1, 2, 3 });
        assertArrayEquals(new byte[] { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1 }, byteArray);

        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, new short[] { 1, 2, 3 });
        assertArrayEquals(new short[] { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1 }, shortArray);

        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, new int[] { 1, 2, 3 });
        assertArrayEquals(new int[] { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1 }, intArray);

        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, new long[] { 1, 2, 3 });
        assertArrayEquals(new long[] { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1 }, longArray);

        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, new float[] { 1.1F, 2.2F, 3.3F });
        assertArrayEquals(new float[] { 1.1F, 2.2F, 3.3F, 1.1F, 2.2F, 3.3F, 1.1F, 2.2F, 3.3F, 1.1F }, floatArray);

        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, new double[] { 1.1, 2.2, 3.3 });
        assertArrayEquals(new double[] { 1.1, 2.2, 3.3, 1.1, 2.2, 3.3, 1.1, 2.2, 3.3, 1.1 }, doubleArray);

        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, new String[] { "aa", "bb", "cc" });
        assertArrayEquals(new String[] { "aa", "bb", "cc", "aa", "bb", "cc", "aa", "bb", "cc", "aa" }, stringArray);
    }

    @Test
    void fill_WithEmptyValues() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, EMPTY_CHAR_ARRAY);
        assertArrayEquals(new char[10], charArray);
        ArrayTools.fill(charArray, "");
        assertArrayEquals(new char[10], charArray);

        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, EMPTY_BYTE_ARRAY);
        assertArrayEquals(new byte[10], byteArray);

        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, EMPTY_SHORT_ARRAY);
        assertArrayEquals(new short[10], shortArray);

        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, EMPTY_INT_ARRAY);
        assertArrayEquals(new int[10], intArray);

        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, EMPTY_LONG_ARRAY);
        assertArrayEquals(new long[10], longArray);

        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, EMPTY_FLOAT_ARRAY);
        assertArrayEquals(new float[10], floatArray);

        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, EMPTY_DOUBLE_ARRAY);
        assertArrayEquals(new double[10], doubleArray);

        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, EMPTY_STRING_ARRAY);
        assertArrayEquals(new String[10], stringArray);
    }

    @Test
    void fill_WithNullValues() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, NULL_CHAR_ARRAY);
        assertArrayEquals(new char[10], charArray);
        ArrayTools.fill(charArray, (String) null);
        assertArrayEquals(new char[10], charArray);

        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, NULL_BYTE_ARRAY);
        assertArrayEquals(new byte[10], byteArray);

        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, NULL_SHORT_ARRAY);
        assertArrayEquals(new short[10], shortArray);

        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, NULL_INT_ARRAY);
        assertArrayEquals(new int[10], intArray);

        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, NULL_LONG_ARRAY);
        assertArrayEquals(new long[10], longArray);

        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, NULL_FLOAT_ARRAY);
        assertArrayEquals(new float[10], floatArray);

        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, NULL_DOUBLE_ARRAY);
        assertArrayEquals(new double[10], doubleArray);

        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, NULL_STRING_ARRAY);
        assertArrayEquals(new String[10], stringArray);
    }

    @Test
    void fill_WithValuesInRange() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, 2, 8, new char[] { 'x', 'y' });
        assertArrayEquals(new char[] { '\0', '\0', 'x', 'y', 'x', 'y', 'x', 'y', '\0', '\0' }, charArray);
        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, 2, 8, new byte[] { 1, 2 });
        assertArrayEquals(new byte[] { 0, 0, 1, 2, 1, 2, 1, 2, 0, 0 }, byteArray);
        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, 2, 8, new short[] { 1, 2 });
        assertArrayEquals(new short[] { 0, 0, 1, 2, 1, 2, 1, 2, 0, 0 }, shortArray);
        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, 2, 8, new int[] { 1, 2 });
        assertArrayEquals(new int[] { 0, 0, 1, 2, 1, 2, 1, 2, 0, 0 }, intArray);
        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, 2, 8, new long[] { 1, 2 });
        assertArrayEquals(new long[] { 0, 0, 1, 2, 1, 2, 1, 2, 0, 0 }, longArray);
        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, 2, 8, new float[] { 1.1F, 2.2F });
        assertArrayEquals(new float[] { 0F, 0F, 1.1F, 2.2F, 1.1F, 2.2F, 1.1F, 2.2F, 0F, 0F }, floatArray);
        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, 2, 8, new double[] { 0.1, 0.2 });
        assertArrayEquals(new double[] { 0.0, 0.0, 0.1, 0.2, 0.1, 0.2, 0.1, 0.2, 0.0, 0.0 }, doubleArray);
        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, 2, 8, new String[] { "Java", "C++" });
        assertArrayEquals(new String[] { null, null, "Java", "C++", "Java", "C++", "Java", "C++", null, null },
                stringArray);
    }

    @Test
    void fill_WithValues_OutOfRange() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, 10, 20, new char[] { 'x', 'y' });
        assertArrayEquals(new char[10], charArray);
        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, 10, 20, new byte[] { 1, 2 });
        assertArrayEquals(new byte[10], byteArray);
        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, 10, 20, new short[] { 1, 2 });
        assertArrayEquals(new short[10], shortArray);
        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, 10, 20, new int[] { 1, 2 });
        assertArrayEquals(new int[10], intArray);
        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, 10, 20, new long[] { 1, 2 });
        assertArrayEquals(new long[10], longArray);
        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, 10, 20, new float[] { 1.1F, 2.2F });
        assertArrayEquals(new float[10], floatArray);
        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, 10, 20, new double[] { 0.1, 0.2 });
        assertArrayEquals(new double[10], doubleArray);
        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, 10, 20, new String[] { "Java", "C++" });
        assertArrayEquals(new String[10], stringArray);
    }

    @Test
    void fill_WithValues_NegativeRange() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, -5, 5, new char[] {'w'});
        assertArrayEquals(new char[]{'w', 'w', 'w', 'w', 'w', '\0', '\0', '\0', '\0', '\0'}, charArray);
        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, -5, 5, new byte[] {108});
        assertArrayEquals(new byte[]{108, 108, 108, 108, 108, 0, 0, 0, 0, 0}, byteArray);
        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, -5, 5, new short[] {108});
        assertArrayEquals(new short[]{108, 108, 108, 108, 108, 0, 0, 0, 0, 0}, shortArray);
        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, -5, 5, new int[] {108});
        assertArrayEquals(new int[]{108, 108, 108, 108, 108, 0, 0, 0, 0, 0}, intArray);
        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, -5, 5, new long[] {108L});
        assertArrayEquals(new long[]{108L, 108L, 108L, 108L, 108L, 0L, 0L, 0L, 0L, 0L}, longArray);
        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, -5, 5, new float[] {108.01F});
        assertArrayEquals(new float[]{108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F}, floatArray);
        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, -5, 5, new double[] {108.01});
        assertArrayEquals(new double[]{108.01, 108.01, 108.01, 108.01, 108.01, 0.0, 0.0, 0.0, 0.0, 0.0}, doubleArray);
        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, -5, 5, new String[] {"test"});
        assertArrayEquals(new String[]{"test", "test", "test", "test", "test", null, null, null, null, null}, stringArray);
    }

    @Test
    void fill_WithValues_ExactRange() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, 0, 10, new char[] {'w'});
        assertArrayEquals(new char[]{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, charArray);
        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, 0, 10, new byte[] {108});
        assertArrayEquals(new byte[]{108, 108, 108, 108, 108, 108, 108, 108, 108, 108}, byteArray);
        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, 0, 10, new short[] {108});
        assertArrayEquals(new short[]{108, 108, 108, 108, 108, 108, 108, 108, 108, 108}, shortArray);
        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, 0, 10, new int[] {108});
        assertArrayEquals(new int[]{108, 108, 108, 108, 108, 108, 108, 108, 108, 108}, intArray);
        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, 0, 10, new long[] {108L});
        assertArrayEquals(new long[]{108L, 108L, 108L, 108L, 108L, 108L, 108L, 108L, 108L, 108L}, longArray);
        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, 0, 10, new float[] {108.01F});
        assertArrayEquals(new float[]{108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 108.01F, 108.01F}, floatArray);
        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, 0, 10, new double[] {108.01});
        assertArrayEquals(new double[]{108.01, 108.01, 108.01, 108.01, 108.01, 108.01, 108.01, 108.01, 108.01, 108.01}, doubleArray);
        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, 0, 10, new String[] {"test"});
        assertArrayEquals(new String[]{"test", "test", "test", "test", "test", "test", "test", "test", "test", "test"}, stringArray);
    }

    @Test
    void fill_WithValues_Overlap() {
        // fill - char
        char[] charArray = new char[10];
        ArrayTools.fill(charArray, 0, 5, new char[] {'a', 'b', 'c', 'd', 'e', 'f'});
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', '\0', '\0', '\0', '\0', '\0'}, charArray);
        // fill - byte
        byte[] byteArray = new byte[10];
        ArrayTools.fill(byteArray, 0, 5, new byte[] {1, 2, 3, 4, 5, 6});
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0}, byteArray);
        // fill - short
        short[] shortArray = new short[10];
        ArrayTools.fill(shortArray, 0, 5, new short[] {1, 2, 3, 4, 5, 6});
        assertArrayEquals(new short[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0}, shortArray);
        // fill - int
        int[] intArray = new int[10];
        ArrayTools.fill(intArray, 0, 5, new int[] {1, 2, 3, 4, 5, 6});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 0, 0, 0, 0, 0}, intArray);
        // fill - long
        long[] longArray = new long[10];
        ArrayTools.fill(longArray, 0, 5, new long[] {1L, 2L, 3L, 4L, 5L, 6L});
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L, 0L, 0L, 0L, 0L, 0L}, longArray);
        // fill - float
        float[] floatArray = new float[10];
        ArrayTools.fill(floatArray, 0, 5, new float[] {1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 6.6F});
        assertArrayEquals(new float[]{1.1F, 2.2F, 3.3F, 4.4F, 5.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F}, floatArray);
        // fill - double
        double[] doubleArray = new double[10];
        ArrayTools.fill(doubleArray, 0, 5, new double[] {1.1, 2.2, 3.3, 4.4, 5.5, 6.6});
        assertArrayEquals(new double[]{1.1, 2.2, 3.3, 4.4, 5.5, 0.0, 0.0, 0.0, 0.0, 0.0}, doubleArray);
        // fill - T
        String[] stringArray = new String[10];
        ArrayTools.fill(stringArray, 0, 5, new String[] {"aaa", "bbb", "ccc", "ddd", "eee", "fff"});
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc", "ddd", "eee", null, null, null, null, null}, stringArray);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - indexOf
    // ================================

    @Test
    void indexOf_NullPredicate_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ArrayTools.indexOf(new String[] {}, null));
    }

    @Test
    void indexOf_NullArray_ReturnsNotFoundIndex() {
        Predicate<String> predicate = s -> s.equals("test");
        int result = ArrayTools.indexOf(null, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void indexOf_EmptyArray_ReturnsNotFoundIndex() {
        Predicate<String> predicate = s -> s.equals("test");
        int result = ArrayTools.indexOf(new String[] {}, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void indexOf_ArrayContainsMatchingElement_ReturnsIndex() {
        String[] array = { "apple", "banana", "cherry" };
        Predicate<String> predicate = s -> s.equals("banana");
        int result = ArrayTools.indexOf(array, predicate);
        assertEquals(1, result);
    }

    @Test
    void indexOf_ArrayDoesNotContainMatchingElement_ReturnsNotFoundIndex() {
        String[] array = { "apple", "banana", "cherry" };
        Predicate<String> predicate = s -> s.equals("orange");
        int result = ArrayTools.indexOf(array, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void indexOf_NullArray_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((String[]) null, "test"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((char[]) null, 'a'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((byte[]) null, (byte) 1));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((short[]) null, (short) 1));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((int[]) null, 1));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((long[]) null, 1L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((float[]) null, 1.23F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf((double[]) null, 1.23));
    }

    @Test
    void indexOf_EmptyArray_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new String[] {}, "test"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new char[] {}, 'a'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new byte[] {}, (byte) 1));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new short[] {}, (short) 1));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new int[] {}, 1));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new long[] {}, 1L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new float[] {}, 1.23F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new double[] {}, 1.23));
    }

    @Test
    void indexOf_ObjectFound_ReturnsIndex() {
        // T
        assertEquals(1, ArrayTools.indexOf(new String[] { "a", "b", "c" }, "b"));
        // char
        assertEquals(1, ArrayTools.indexOf(new char[] { 'a', 'b', 'c' }, 'b'));
        // byte
        assertEquals(1, ArrayTools.indexOf(new byte[] { 1, 2, 3 }, (byte) 2));
        // short
        assertEquals(1, ArrayTools.indexOf(new short[] { 1, 2, 3 }, (short) 2));
        // int
        assertEquals(1, ArrayTools.indexOf(new int[] { 1, 2, 3 }, 2));
        // long
        assertEquals(1, ArrayTools.indexOf(new long[] { 1000000L, 2000000L, 3000000L }, 2000000L));
        // float
        assertEquals(1, ArrayTools.indexOf(new float[] { 1.11F, 2.22F, 3.33F }, 2.22F));
        // double
        assertEquals(1, ArrayTools.indexOf(new double[] { 1.11, 2.22, 3.33 }, 2.22));
    }

    @Test
    void indexOf_ObjectNotFound_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new String[] { "a", "b", "c" }, "d"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new char[] { 'a', 'b', 'c' }, 'd'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new byte[] { 1, 2, 3 }, (byte) 4));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new short[] { 1, 2, 3 }, (short) 4));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new int[] { 1, 2, 3 }, 4));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX,
                ArrayTools.indexOf(new long[] { 1000000L, 2000000L, 3000000L }, 4000000L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new float[] { 1.11F, 2.22F, 3.33F }, 4.44F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new double[] { 1.11, 2.22, 3.33 }, 4.44));
    }

    @Test
    void indexOf_NullObject_ReturnsNotFound() {
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.indexOf(new String[] { "a", "b", "c" }, (String) null));
    }

    @Test
    void indexOf_ArrayContainsNull_ReturnsIndex() {
        assertEquals(1, ArrayTools.indexOf(new String[] { "a", null, "c" }, (String) null));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - lastIndexOf
    // ================================

    @Test
    void lastIndexOf_NullPredicate_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ArrayTools.lastIndexOf(new String[] {}, null));
    }

    @Test
    void lastIndexOf_NullArray_ReturnsNotFoundIndex() {
        Predicate<String> predicate = s -> s.equals("test");
        int result = ArrayTools.lastIndexOf(null, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void lastIndexOf_EmptyArray_ReturnsNotFoundIndex() {
        Predicate<String> predicate = s -> s.equals("test");
        int result = ArrayTools.lastIndexOf(new String[] {}, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void lastIndexOf_ArrayContainsMatchingElement_ReturnsIndex() {
        String[] array = { "apple", "banana", "banana", "cherry" };
        Predicate<String> predicate = s -> s.equals("banana");
        int result = ArrayTools.lastIndexOf(array, predicate);
        assertEquals(2, result);
    }

    @Test
    void lastIndexOf_ArrayDoesNotContainMatchingElement_ReturnsNotFoundIndex() {
        String[] array = { "apple", "banana", "cherry" };
        Predicate<String> predicate = s -> s.equals("orange");
        int result = ArrayTools.lastIndexOf(array, predicate);
        assertEquals(ArrayTools.NOT_FOUND_INDEX, result);
    }

    @Test
    void lastIndexOf_NullArray_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((String[]) null, "test"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((char[]) null, 'a'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((byte[]) null, (byte) 1));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((short[]) null, (short) 1));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((int[]) null, 1));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((long[]) null, 1L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((float[]) null, 1.23F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf((double[]) null, 1.23));
    }

    @Test
    void lastIndexOf_EmptyArray_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new String[] {}, "test"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new char[] {}, 'a'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new byte[] {}, (byte) 1));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new short[] {}, (short) 1));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new int[] {}, 1));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new long[] {}, 1L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new float[] {}, 1.23F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new double[] {}, 1.23));
    }

    @Test
    void lastIndexOf_ObjectFound_ReturnsIndex() {
        // T
        assertEquals(3, ArrayTools.lastIndexOf(new String[] { "a", "b", "c", "b", "c" }, "b"));
        // char
        assertEquals(3, ArrayTools.lastIndexOf(new char[] { 'a', 'b', 'c', 'b', 'c' }, 'b'));
        // byte
        assertEquals(3, ArrayTools.lastIndexOf(new byte[] { 1, 2, 3, 2, 3 }, (byte) 2));
        // short
        assertEquals(3, ArrayTools.lastIndexOf(new short[] { 1, 2, 3, 2, 3 }, (short) 2));
        // int
        assertEquals(3, ArrayTools.lastIndexOf(new int[] { 1, 2, 3, 2, 3 }, 2));
        // long
        assertEquals(3,
                ArrayTools.lastIndexOf(new long[] { 1000000L, 2000000L, 3000000L, 2000000L, 3000000L }, 2000000L));
        // float
        assertEquals(3, ArrayTools.lastIndexOf(new float[] { 1.11F, 2.22F, 3.33F, 2.22F, 3.33F }, 2.22F));
        // double
        assertEquals(3, ArrayTools.lastIndexOf(new double[] { 1.11, 2.22, 3.33, 2.22, 3.33 }, 2.22));
    }

    @Test
    void lastIndexOf_ObjectNotFound_ReturnsNotFound() {
        // T
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new String[] { "a", "b", "c" }, "d"));
        // char
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new char[] { 'a', 'b', 'c' }, 'd'));
        // byte
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new byte[] { 1, 2, 3 }, (byte) 4));
        // short
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new short[] { 1, 2, 3 }, (short) 4));
        // int
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new int[] { 1, 2, 3 }, 4));
        // long
        assertEquals(ArrayTools.NOT_FOUND_INDEX,
                ArrayTools.lastIndexOf(new long[] { 1000000L, 2000000L, 3000000L }, 4000000L));
        // float
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new float[] { 1.11F, 2.22F, 3.33F }, 4.44F));
        // double
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new double[] { 1.11, 2.22, 3.33 }, 4.44));
    }

    @Test
    void lastIndexOf_NullObject_ReturnsNotFound() {
        assertEquals(ArrayTools.NOT_FOUND_INDEX, ArrayTools.lastIndexOf(new String[] { "a", "b", "c" }, (String) null));
    }

    @Test
    void lastIndexOf_ArrayContainsNull_ReturnsIndex() {
        assertEquals(3, ArrayTools.lastIndexOf(new String[] { "a", null, "c", null, "e" }, (String) null));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - contains
    // ================================

    @Test
    void contains_NullArray_ReturnsFalse() {
        assertFalse(ArrayTools.contains((String[]) null, "test"));
        // char
        assertFalse(ArrayTools.contains((char[]) null, 'a'));
        // byte
        assertFalse(ArrayTools.contains((byte[]) null, (byte) 1));
        // short
        assertFalse(ArrayTools.contains((short[]) null, (short) 1));
        // int
        assertFalse(ArrayTools.contains((int[]) null, 1));
        // long
        assertFalse(ArrayTools.contains((long[]) null, 1L));
        // float
        assertFalse(ArrayTools.contains((float[]) null, 1.2F));
        // double
        assertFalse(ArrayTools.contains((double[]) null, 1.2));
    }

    @Test
    void contains_EmptyArray_ReturnsFalse() {
        assertFalse(ArrayTools.contains(new String[] {}, "test"));
        // char
        assertFalse(ArrayTools.contains(new char[] {}, 'a'));
        // byte
        assertFalse(ArrayTools.contains(new byte[] {}, (byte) 1));
        // short
        assertFalse(ArrayTools.contains(new short[] {}, (short) 1));
        // int
        assertFalse(ArrayTools.contains(new int[] {}, 1));
        // long
        assertFalse(ArrayTools.contains(new long[] {}, 1L));
        // float
        assertFalse(ArrayTools.contains(new float[] {}, 1.2F));
        // double
        assertFalse(ArrayTools.contains(new double[] {}, 1.2));
    }

    @Test
    void contains_ArrayContainsObject_ReturnsTrue() {
        assertTrue(ArrayTools.contains(new String[] { "test", "example" }, "test"));
        // char
        assertTrue(ArrayTools.contains(new char[] { 'a', 'b', 'c' }, 'a'));
        // byte
        assertTrue(ArrayTools.contains(new byte[] { 1, 2, 3 }, (byte) 1));
        // short
        assertTrue(ArrayTools.contains(new short[] { 1, 2, 3 }, (short) 1));
        // int
        assertTrue(ArrayTools.contains(new int[] { 1, 2, 3 }, 1));
        // long
        assertTrue(ArrayTools.contains(new long[] { 1000000L, 2000000L, 3000000L }, 1000000L));
        // float
        assertTrue(ArrayTools.contains(new float[] { 1.11F, 2.22F, 3.33F }, 2.22F));
        // double
        assertTrue(ArrayTools.contains(new double[] { 1.11, 2.22, 3.33 }, 2.22));
    }

    @Test
    void contains_ArrayDoesNotContainObject_ReturnsFalse() {
        // T
        assertFalse(ArrayTools.contains(new String[] { "a", "b", "c" }, "d"));
        // char
        assertFalse(ArrayTools.contains(new char[] { 'a', 'b', 'c' }, 'd'));
        // byte
        assertFalse(ArrayTools.contains(new byte[] { 1, 2, 3 }, (byte) 4));
        // short
        assertFalse(ArrayTools.contains(new short[] { 1, 2, 3 }, (short) 4));
        // int
        assertFalse(ArrayTools.contains(new int[] { 1, 2, 3 }, 4));
        // long
        assertFalse(ArrayTools.contains(new long[] { 1000000L, 2000000L, 3000000L }, 4000000L));
        // float
        assertFalse(ArrayTools.contains(new float[] { 1.11F, 2.22F, 3.33F }, 4.44F));
        // double
        assertFalse(ArrayTools.contains(new double[] { 1.11, 2.22, 3.33 }, 4.44));
    }

    @Test
    void contains_ObjectIsNull_ReturnsFalse() {
        assertFalse(ArrayTools.contains(new String[] { "test", "example" }, null));
    }

    @Test
    void contains_ArrayContainsNull_ReturnsTrue() {
        assertTrue(ArrayTools.contains(new String[] { "test", null }, null));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - invoke constructor
    // ================================

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = ArrayTools.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }

    // ================================
    // #endregion - invoke constructor
    // ================================

}
