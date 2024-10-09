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

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

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
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E valueOf(Class<E> clazz, int ordinal) { // NOSONAR 该方法弃用，但不删掉
        Preconditions.checkNotNull(clazz, "Clazz must not be null.");
        E[] values = clazz.getEnumConstants();
        PreconditionsExt.checkCondition((ordinal >= 0 && ordinal < values.length),
                () -> new EnumConstantNotPresentException(clazz, Integer.toString(ordinal)));
        return values[ordinal];
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>          枚举的类型
     * @param clazz        枚举的类型信息
     * @param ordinal      序号
     * @param defaultValue 默认值
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E valueOf(Class<E> clazz, @Nullable Integer ordinal, E defaultValue) { // NOSONAR 该方法弃用，但不删掉
        if (null == ordinal) {
            return defaultValue;
        }
        return valueOf(clazz, ordinal);
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>          枚举的类型
     * @param clazz        枚举的类型信息
     * @param ordinal      序号
     * @param defaultValue 默认值
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueOrDefault( // NOSONAR 该方法弃用，但不删掉
            Class<E> clazz,
            @Nullable Integer ordinal,
            Supplier<E> defaultValue) {
        if (null == ordinal) {
            return defaultValue.get();
        }
        return valueOf(clazz, ordinal);
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueOrDefault(Class<E> clazz, @Nullable Integer ordinal) { // NOSONAR 该方法弃用，但不删掉
        return getValueOrDefault(clazz, ordinal, () -> {
            Preconditions.checkNotNull(clazz, "Clazz must not be null.");
            E[] values = clazz.getEnumConstants();
            return values[0];
        });
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 序号
     * @return 枚举对象
     * @deprecated 不推荐使用枚举的 ordinal。
     */
    @Deprecated
    public static <E extends Enum<?>> E getValueNullable(Class<E> clazz, @Nullable Integer ordinal) { // NOSONAR 该方法弃用，但不删掉
        return valueOf(clazz, ordinal, null);
    }

    public static <E extends Enum<?>> Integer checkOrdinal(Class<E> clazz, Integer ordinal) {
        Preconditions.checkNotNull(clazz, "Clazz must not be null.");
        Preconditions.checkNotNull(ordinal, "Ordinal must not be null.");
        E[] values = clazz.getEnumConstants();
        if (ordinal >= 0 && ordinal < values.length) {
            return ordinal;
        }
        throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
    }

    /**
     * 校验枚举的 ordinal。
     * 
     * @param <E>     枚举类型
     * @param clazz   枚举类型
     * @param ordinal The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalNullable(Class<E> clazz, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(clazz, ordinal, null);
    }

    /**
     * 校验枚举的 ordinal，如果 ordinal 为 {@code null}，则返回 {@code 0}。
     * 
     * @param <E>     枚举类型
     * @param clazz   枚举类型
     * @param ordinal The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(Class<E> clazz, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(clazz, ordinal, 0);
    }

    /**
     * 校验枚举的 ordinal，如果 ordinal 为 {@code null}，则返回 {@code defaultValue}。
     * 
     * @param <E>     枚举类型
     * @param clazz   枚举类型
     * @param ordinal The ordinal
     * @return The ordinal
     */
    @Nullable
    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(
            Class<E> clazz,
            @Nullable Integer ordinal,
            @Nullable Integer defaultValue) {
        if (ordinal != null) {
            return checkOrdinal(clazz, ordinal);
        }
        return defaultValue;
    }
}
