/*
 * Copyright 2022-2025 the original author or authors.
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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkCondition;
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkNotNull;

import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * 枚举工具类
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public final class EnumTools {

    private EnumTools() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>      枚举的类型
     * @param enumType 枚举的类型信息
     * @param ordinal  序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    private static <E extends Enum<?>> E valueOfInternal(Class<E> enumType, int ordinal) { // NOSONAR 该方法弃用，但不删掉
        E[] values = enumType.getEnumConstants();
        checkCondition((ordinal >= 0 && ordinal < values.length),
                () -> new EnumConstantNotPresentException(enumType, Integer.toString(ordinal)));
        return values[ordinal];
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>      枚举的类型
     * @param enumType 枚举的类型信息
     * @param ordinal  序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E valueOf(Class<E> enumType, int ordinal) { // NOSONAR 该方法弃用，但不删掉
        checkNotNull(enumType, "Enum type must not be null.");
        return valueOfInternal(enumType, ordinal);
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>          枚举的类型
     * @param enumType     枚举的类型信息
     * @param ordinal      序号
     * @param defaultValue 默认值
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E valueOf(Class<E> enumType, // NOSONAR 该方法弃用，但不删掉
            @Nullable Integer ordinal, @Nullable E defaultValue) {
        checkNotNull(enumType);
        return null == ordinal ? defaultValue : valueOfInternal(enumType, ordinal);
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>          枚举的类型
     * @param enumType     枚举的类型信息
     * @param ordinal      序号
     * @param defaultValue 默认值
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueOrDefault( // NOSONAR 该方法弃用，但不删掉
            Class<E> enumType,
            @Nullable Integer ordinal,
            Supplier<E> defaultValue) {
        checkNotNull(enumType);
        checkNotNull(defaultValue);
        return null == ordinal ? defaultValue.get() : valueOfInternal(enumType, ordinal);
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>      枚举的类型
     * @param enumType 枚举的类型信息
     * @param ordinal  序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueOrDefault(Class<E> enumType, @Nullable Integer ordinal) { // NOSONAR 该方法弃用，但不删掉
        return getValueOrDefault(enumType, ordinal, () -> {
            checkNotNull(enumType, "Enum type must not be null.");
            E[] values = enumType.getEnumConstants();
            return values[0];
        });
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>      枚举的类型
     * @param enumType 枚举的类型信息
     * @param ordinal  序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueNullable(Class<E> enumType, @Nullable Integer ordinal) { // NOSONAR 该方法弃用，但不删掉
        return valueOf(enumType, ordinal, null);
    }

    public static <E extends Enum<?>> Integer checkOrdinal(Class<E> enumType, Integer ordinal) {
        checkNotNull(enumType, "Enum type must not be null.");
        checkNotNull(ordinal, "Ordinal must not be null.");
        E[] values = enumType.getEnumConstants();
        checkCondition(ordinal >= 0 && ordinal < values.length,
                () -> new EnumConstantNotPresentException(enumType, Integer.toString(ordinal)));
        return ordinal;
    }

    /**
     * 校验枚举的 ordinal。
     *
     * @param <E>      枚举类型
     * @param enumType 枚举类型
     * @param ordinal  The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalNullable(Class<E> enumType, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(enumType, ordinal, (Integer) null);
    }

    /**
     * 校验枚举的 ordinal，如果 ordinal 为 {@code null}，则返回 {@code 0}。
     *
     * @param <E>      枚举类型
     * @param enumType 枚举类型
     * @param ordinal  The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(Class<E> enumType, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(enumType, ordinal, 0);
    }

    /**
     * 校验枚举的 ordinal，如果 ordinal 为 {@code null}，则返回 {@code defaultValue}。
     *
     * @param <E>      枚举类型
     * @param enumType 枚举类型
     * @param ordinal  The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(
            Class<E> enumType,
            @Nullable Integer ordinal,
            @Nullable Integer defaultValue) {
        checkNotNull(enumType);
        return checkOrdinalOrGetInternal(enumType, ordinal, () -> checkOrdinalOrDefaultInternal(enumType, defaultValue, null));
    }

    /**
     * 仅对 {@code ordinal} 进行判断，不对 {@code defaultValue} 进行判断
     */
    @Nullable
    private static <E extends Enum<?>> Integer checkOrdinalOrDefaultInternal(
            Class<E> enumType,
            @Nullable Integer ordinal,
            @Nullable Integer defaultValue) {
        return ordinal != null
                ? checkOrdinal(enumType, ordinal)
                : defaultValue;
    }

    @Nullable
    private static <E extends Enum<?>> Integer checkOrdinalOrGetInternal(
            Class<E> enumType,
            @Nullable Integer ordinal,
            Supplier<Integer> defaultValueSupplier) {
        return ordinal != null
                ? checkOrdinal(enumType, ordinal)
                : defaultValueSupplier.get();
    }
}
