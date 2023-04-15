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

import java.util.Objects;

import javax.annotation.Nonnull;
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
    public static <E extends Enum<?>> E valueOf(@Nonnull Class<E> clazz, int ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return values[ordinal];
        } catch (IndexOutOfBoundsException e) {
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueOrDefault(@Nonnull Class<E> clazz, @Nullable Integer ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return Objects.nonNull(ordinal) ? values[ordinal] : values[0];
        } catch (IndexOutOfBoundsException e) {
            Objects.requireNonNull(ordinal);
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueNullable(@Nonnull Class<E> clazz, @Nullable Integer ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return Objects.nonNull(ordinal) ? values[ordinal] : null;
        } catch (IndexOutOfBoundsException e) {
            Objects.requireNonNull(ordinal);
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    public static <E extends Enum<?>> Integer checkOrdinal(@Nonnull Class<E> clazz, @Nonnull Integer ordinal) {
        E[] values = clazz.getEnumConstants();
        if (ordinal >= 0 && ordinal < values.length) {
            return ordinal;
        }
        throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
    }

    public static <E extends Enum<?>> Integer checkOrdinalNullable(@Nonnull Class<E> clazz, @Nullable Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return checkOrdinal(clazz, ordinal);
    }

    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(@Nonnull Class<E> clazz, @Nullable Integer ordinal) {
        if (ordinal == null) {
            return 0;
        }
        return checkOrdinal(clazz, ordinal);
    }
}
