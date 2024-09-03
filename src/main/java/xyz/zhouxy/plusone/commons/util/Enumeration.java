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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * 枚举类
 *
 * 参考 <a href="https://lostechies.com/jimmybogard/2008/08/12/enumeration-classes/">Enumeration classes</a>
 *
 * @deprecated 设计 Enumeration 的灵感来自于 .net 社区，因为 C# 的枚举不带行为。
 * 但 Java 的枚举可以带行为，故大多数情况下不需要这种设计。
 */
@Deprecated
public abstract class Enumeration<T extends Enumeration<T>> // NOSONAR 暂不移除
        implements Comparable<T> {
    protected final int id;
    protected final String name;

    protected Enumeration(final int id, final String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name of enumeration must has text.");
        this.id = id;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final int compareTo(final T o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Enumeration<?> other = (Enumeration<?>) obj;
        return id == other.id;
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName() + '(' + id + ":" + name + ')';
    }

    protected static final class ValueSet<T extends Enumeration<T>> {
        private final Map<Integer, T> valueMap;

        private ValueSet(Map<Integer, T> valueMap) {
            this.valueMap = valueMap;
        }

        @StaticFactoryMethod(ValueSet.class)
        public static <T extends Enumeration<T>> ValueSet<T> of(T[] values) {
            Map<Integer, T> temp = Arrays.stream(values)
                    .collect(Collectors.toMap(Enumeration::getId, Function.identity()));
            return new ValueSet<>(Collections.unmodifiableMap(temp));
        }

        public T get(int id) {
            Preconditions.checkArgument(this.valueMap.containsKey(id), "[%s] 对应的值不存在", id);
            return this.valueMap.get(id);
        }

        public Collection<T> getValues() {
            return this.valueMap.values();
        }
    }
}
