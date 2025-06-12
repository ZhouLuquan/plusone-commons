/*
 * Copyright 2023-2025 the original author or authors.
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

package xyz.zhouxy.plusone.commons.model;

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.annotation.ReaderMethod;

/**
 * 带校验的字符串值对象
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 *
 * @deprecated 弃用。使用工厂方法创建对象，并在其中进行校验即可。
 */
@Deprecated
public abstract class ValidatableStringRecord<T extends ValidatableStringRecord<T>> // NOSONAR 暂不删除
        implements Comparable<T> {

    @Nonnull
    private final String value;

    private final Matcher matcher;

    /**
     * 构造字符串值对象
     *
     * @param value   字符串值
     * @param pattern 正则
     */
    protected ValidatableStringRecord(String value, Pattern pattern) {
        this(value, pattern, "Invalid value");
    }

    /**
     * 构造字符串值对象
     *
     * @param value                字符串值
     * @param pattern              正则
     * @param errorMessageSupplier 正则不匹配时的错误信息
     */
    protected ValidatableStringRecord(String value, Pattern pattern,
            Supplier<String> errorMessageSupplier) {
        this(value, pattern, errorMessageSupplier.get());
    }

    /**
     * 构造字符串值对象
     *
     * @param value        字符串值
     * @param pattern      正则
     * @param errorMessage 正则不匹配时的错误信息
     */
    protected ValidatableStringRecord(String value, Pattern pattern, String errorMessage) {
        checkArgument(Objects.nonNull(value), "The value cannot be null.");
        checkArgument(Objects.nonNull(pattern), "The pattern cannot be null.");
        this.matcher = pattern.matcher(value);
        checkArgument(this.matcher.matches(), errorMessage);
        this.value = value;
    }

    /**
     * 值对象的字符串值。
     *
     * @return 字符串（不为空）
     */
    @ReaderMethod
    public final String value() {
        return this.value;
    }

    @Override
    public int compareTo(@SuppressWarnings("null") T o) {
        return this.value.compareTo(o.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), value);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        @SuppressWarnings("unchecked")
        T other = (T) obj;
        return Objects.equals(value, other.value());
    }

    @Override
    public String toString() {
        return this.value();
    }

    /**
     * 获取正则匹配结果
     *
     * @return {@code Matcher} 对象
     */
    protected final Matcher getMatcher() {
        return matcher;
    }

}
