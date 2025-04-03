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

package xyz.zhouxy.plusone.commons.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

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
        AssertTools.checkArgument(StringTools.isNotBlank(name), "Name of enumeration must has text.");
        this.id = id;
        this.name = name;
    }

    /**
     * 枚举整数码值
     *
     * @return 整数码值
     */
    public final int getId() {
        return id;
    }

    /**
     * 枚举名称
     *
     * @return 枚举名称
     */
    public final String getName() {
        return name;
    }

    @SuppressWarnings("null")
    @Override
    public final int compareTo(final T o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final boolean equals(@Nullable final Object obj) {
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

    /**
     * 枚举值集合
     */
    protected static final class ValueSet<T extends Enumeration<T>> {
        private final Map<Integer, T> valueMap;

        private ValueSet(Map<Integer, T> valueMap) {
            this.valueMap = valueMap;
        }

        /**
         * 创建枚举值集合
         *
         * @param <T>    枚举类型
         * @param values 枚举值
         * @return 枚举值集合
         */
        @StaticFactoryMethod(ValueSet.class)
        public static <T extends Enumeration<T>> ValueSet<T> of(T[] values) {
            Map<Integer, T> temp = Arrays.stream(values)
                    .collect(Collectors.toMap(Enumeration::getId, Function.identity()));
            return new ValueSet<>(Collections.unmodifiableMap(temp));
        }

        /**
         * 根据整数码值获取枚举对象
         *
         * @param id 整数码
         * @return 枚举对象
         */
        public T get(int id) {
            AssertTools.checkArgument(this.valueMap.containsKey(id), "[%s] 对应的值不存在", id);
            return this.valueMap.get(id);
        }

        /**
         * 获取所有枚举对象
         *
         * @return 所有枚举对象
         */
        public Collection<T> getValues() {
            return this.valueMap.values();
        }
    }
}
