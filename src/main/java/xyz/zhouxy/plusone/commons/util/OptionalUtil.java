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

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

/**
 * OptionalUtil
 *
 * <p>
 * 提供一些 Optional 相关的方法
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @since 0.1.0
 * @see Optional
 * @see OptionalInt
 * @see OptionalLong
 * @see OptionalDouble
 */
public class OptionalUtil {

    /**
     * 将包装类 {@link Integer} 转为 {@link OptionalInt}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalInt} 后，由
     * {@link OptionalInt#empty()} 表示值的缺失。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalInt} 实例
     */
    public static OptionalInt optionalOf(@Nullable Integer value) {
        return value != null ? OptionalInt.of(value) : OptionalInt.empty();
    }

    /**
     * 将 {@code Optional<Integer>} 转为 {@link OptionalInt}。
     * <p>
     * {@code Optional<Integer>} 将整数包装了两次，改为使用 {@link OptionalInt} 包装其中的整数数据。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalInt} 实例
     */
    public static OptionalInt toOptionalInt(Optional<Integer> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    /**
     * 将包装类 {@link Long} 转为 {@link OptionalLong}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalLong} 后，由
     * {@link OptionalLong#empty()} 表示值的缺失。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalLong} 实例
     */
    public static OptionalLong optionalOf(@Nullable Long value) {
        return value != null ? OptionalLong.of(value) : OptionalLong.empty();
    }

    /**
     * 将 {@code Optional<Long>} 转为 {@link OptionalLong}。
     * <p>
     * {@code Optional<Long>} 将整数包装了两次，改为使用 {@link OptionalLong} 包装其中的整数数据。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalLong} 实例
     */
    public static OptionalLong toOptionalLong(Optional<Long> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    /**
     * 将包装类 {@link Double} 转为 {@link OptionalDouble}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalDouble} 后，由
     * {@link OptionalDouble#empty()} 表示值的缺失。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalDouble} 实例
     */
    public static OptionalDouble optionalOf(@Nullable Double value) {
        return value != null ? OptionalDouble.of(value) : OptionalDouble.empty();
    }

    /**
     * 将 {@code Optional<Double>} 转为 {@link OptionalDouble}。
     * <p>
     * {@code Optional<Double>} 将整数包装了两次，改为使用 {@link OptionalDouble} 包装其中的整数数据。
     * </p>
     * 
     * @param value 包装对象
     * @return {@link OptionalDouble} 实例
     */
    public static OptionalDouble toOptionalDouble(Optional<Double> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    /**
     * return the value of the optional object if present,
     * otherwise {@code null}.
     * 
     * @param <T>         the class of the value
     * @param optionalObj {@link Optional} object, which must be non-null.
     * @return the value of the optional object if present, otherwise {@code null}.
     */
    @Beta
    public static <T> T orElseNull(Optional<T> optionalObj) {
        return optionalObj.orElse(null);
    }

    @Beta
    public static Integer toInteger(OptionalInt optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsInt() : null;
    }

    @Beta
    public static Long toLong(OptionalLong optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsLong() : null;
    }

    @Beta
    public static Double toDouble(OptionalDouble optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsDouble() : null;
    }

    private OptionalUtil() {
        throw new IllegalStateException("Utility class");
    }
}
