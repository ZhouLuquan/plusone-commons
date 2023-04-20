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

/**
 * 枚举工具类
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public final class EnumUtil {

    private EnumUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E valueOf(Class<E> clazz, int ordinal) {
        Assert.notNull(clazz, "Clazz must not be null.");
        E[] values = clazz.getEnumConstants();
        Assert.isTrue((ordinal >= 0 && ordinal < values.length),
                () -> new EnumConstantNotPresentException(clazz, Integer.toString(ordinal)));
        return values[ordinal];
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>          枚举的类型
     * @param clazz        枚举的类型信息
     * @param ordinal      数据库中对应的数值
     * @param defaultValue 默认值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E valueOf(Class<E> clazz, @Nullable Integer ordinal, E defaultValue) {
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
     * @param ordinal      数据库中对应的数值
     * @param defaultValue 默认值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueOrDefault(
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
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueOrDefault(Class<E> clazz, @Nullable Integer ordinal) {
        return getValueOrDefault(clazz, ordinal, () -> {
            Assert.notNull(clazz, "Clazz must not be null.");
            E[] values = clazz.getEnumConstants();
            return values[0];
        });
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueNullable(Class<E> clazz, @Nullable Integer ordinal) {
        return valueOf(clazz, ordinal, null);
    }

    public static <E extends Enum<?>> Integer checkOrdinal(Class<E> clazz, Integer ordinal) {
        Assert.notNull(clazz, "Clazz must not be null.");
        Assert.notNull(ordinal, "Ordinal must not be null.");
        E[] values = clazz.getEnumConstants();
        if (ordinal >= 0 && ordinal < values.length) {
            return ordinal;
        }
        throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
    }

    public static <E extends Enum<?>> Integer checkOrdinalNullable(Class<E> clazz, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(clazz, ordinal, null);
    }

    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(Class<E> clazz, @Nullable Integer ordinal) {
        return checkOrdinalOrDefault(clazz, ordinal, 0);
    }

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
