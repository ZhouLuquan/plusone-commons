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

package xyz.zhouxy.plusone.commons.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.util.RegexTools;

/**
 * 带校验的字符串值对象
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public abstract class ValidatableStringRecord
        implements Comparable<ValidatableStringRecord> {
    private final String value;

    protected ValidatableStringRecord(String value, Pattern pattern) {
        Preconditions.checkNotNull(pattern, "The pattern must not be null.");
        Preconditions.checkNotNull(StringUtils.isNotBlank(value), "The value must not be null.");
        Preconditions.checkArgument(RegexTools.matches(value, pattern));
        this.value = value;
    }

    /**
     * 值对象的字符串值。
     * 
     * @return 字符串（不为空）
     */
    public final String value() {
        return this.value;
    }

    @Override
    public int compareTo(ValidatableStringRecord o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValidatableStringRecord other = (ValidatableStringRecord) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return this.value();
    }
}
