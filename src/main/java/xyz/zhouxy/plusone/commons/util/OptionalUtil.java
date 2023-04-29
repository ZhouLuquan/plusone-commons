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

    public static OptionalInt optionalOf(@Nullable Integer value) {
        return value != null ? OptionalInt.of(value) : OptionalInt.empty();
    }

    public static OptionalInt toOptionalInt(Optional<Integer> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    public static OptionalLong optionalOf(@Nullable Long value) {
        return value != null ? OptionalLong.of(value) : OptionalLong.empty();
    }

    public static OptionalLong toOptionalLong(Optional<Long> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    public static OptionalDouble optionalOf(@Nullable Double value) {
        return value != null ? OptionalDouble.of(value) : OptionalDouble.empty();
    }

    public static OptionalDouble toOptionalDouble(Optional<Double> objectOptional) {
        return optionalOf(objectOptional.orElse(null));
    }

    private OptionalUtil() {
        throw new IllegalStateException("Utility class");
    }
}
