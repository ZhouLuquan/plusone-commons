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

package xyz.zhouxy.plusone.commons.base;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 规定实现类带有 {@code getCode} 方法。
 * 用于像自定义异常等需要带有 {@code code} 字段的类，
 * 方便其它地方的程序判断该类的是否实现了此接口，以此获取其实例的 {@code code} 字段的值。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public interface IWithCode<T> {

    /**
     * 获取码值
     * @return 码值
     */
    @Nonnull
    T getCode();

    /**
     * 判断 {@code code} 与给定的值是否相等
     *
     * @param code 用于判断的值
     * @return 判断结果
     */
    default boolean isCodeEquals(@Nullable T code) {
        return Objects.equals(getCode(), code);
    }

    /**
     * 判断是否与给定的 {@link IWithCode} 有着相等的 {@code code}
     *
     * @param other 用于比较的对象
     * @return 判断结果
     */
    default boolean isSameCodeAs(@Nullable IWithCode<?> other) {
        return other != null && Objects.equals(getCode(), other.getCode());
    }

    /**
     * 判断是否与给定的 {@link IWithIntCode} 有着相等的 {@code code}
     *
     * @param other 用于比较的对象
     * @return 判断结果
     */
    default boolean isSameCodeAs(@Nullable IWithIntCode other) {
        return other != null && Objects.equals(getCode(), other.getCode());
    }

    /**
     * 判断是否与给定的 {@link IWithLongCode} 有着相等的 {@code code}
     *
     * @param other 用于比较的对象
     * @return 判断结果
     */
    default boolean isSameCodeAs(@Nullable IWithLongCode other) {
        return other != null && Objects.equals(getCode(), other.getCode());
    }
}
