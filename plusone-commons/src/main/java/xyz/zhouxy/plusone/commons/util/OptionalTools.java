/*
 * Copyright 2023-present ZhouXY
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
 * OptionalTools
 *
 * <p>
 * 提供一些 Optional 相关的方法
 *
 * @author ZhouXY
 * @since 1.0.0
 * @see Optional
 * @see OptionalInt
 * @see OptionalLong
 * @see OptionalDouble
 */
public class OptionalTools {

    /**
     * 将包装类 {@link Integer} 转为 {@link OptionalInt}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalInt} 后，由
     * {@link OptionalInt#empty()} 表示值的缺失。
     *
     * @param value 包装对象
     * @return {@link OptionalInt} 实例
     */
    public static OptionalInt optionalOf(@Nullable Integer value) {
        return value != null ? OptionalInt.of(value) : OptionalInt.empty();
    }

    /**
     * 将 {@code Optional<Integer>} 对象转为 {@link OptionalInt} 对象。
     * <p>
     * {@code Optional<Integer>} 将整数包装了两次，改为使用 {@link OptionalInt} 包装其中的整数数据。
     *
     * @param optionalObj {@code Optional<Integer>} 对象
     * @return {@link OptionalInt} 实例
     */
    public static OptionalInt toOptionalInt(Optional<Integer> optionalObj) {
        return optionalObj.map(OptionalInt::of).orElseGet(OptionalInt::empty);
    }

    /**
     * 将包装类 {@link Long} 转为 {@link OptionalLong}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalLong} 后，由
     * {@link OptionalLong#empty()} 表示值的缺失。
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
     *
     * @param optionalObj 包装对象
     * @return {@link OptionalLong} 实例
     */
    public static OptionalLong toOptionalLong(Optional<Long> optionalObj) {
        return optionalObj.map(OptionalLong::of).orElseGet(OptionalLong::empty);
    }

    /**
     * 将包装类 {@link Double} 转为 {@link OptionalDouble}（not null）。
     * <p>
     * 包装类为 {@code null} 表示值的缺失，转为 {@link OptionalDouble} 后，由
     * {@link OptionalDouble#empty()} 表示值的缺失。
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
     *
     * @param optionalObj 包装对象
     * @return {@link OptionalDouble} 实例
     */
    public static OptionalDouble toOptionalDouble(Optional<Double> optionalObj) {
        return optionalObj.map(OptionalDouble::of).orElseGet(OptionalDouble::empty);
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
    @Nullable
    public static <T> T orElseNull(Optional<T> optionalObj) {
        return optionalObj.orElse(null);
    }

    /**
     * 将 {@link OptionalInt} 转为 {@link Integer}
     *
     * @param optionalObj optional 对象
     * @return {@link Integer} 对象。如果 {@code OptionalInt} 的值缺失，返回 {@code null}。
     */
    @Beta
    @Nullable
    public static Integer toInteger(OptionalInt optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsInt() : null;
    }

    /**
     * 将 {@link OptionalLong} 转为 {@link Long}
     *
     * @param optionalObj optional 对象
     * @return {@link Long} 对象。如果 {@code OptionalLong} 的值缺失，返回 {@code null}。
     */
    @Beta
    @Nullable
    public static Long toLong(OptionalLong optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsLong() : null;
    }

    /**
     * 将 {@link OptionalDouble} 转为 {@link Double}
     *
     * @param optionalObj optional 对象
     * @return {@link Double} 对象。如果 {@code OptionalDouble} 的值缺失，返回 {@code null}。
     */
    @Beta
    @Nullable
    public static Double toDouble(OptionalDouble optionalObj) {
        return optionalObj.isPresent() ? optionalObj.getAsDouble() : null;
    }

    private OptionalTools() {
        throw new IllegalStateException("Utility class");
    }
}
