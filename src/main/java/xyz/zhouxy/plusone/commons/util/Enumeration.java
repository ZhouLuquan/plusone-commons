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

import java.util.Collection;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * 枚举类
 */
public abstract class Enumeration<T extends Enumeration<T>> implements Comparable<T> {
    protected final int id;
    protected final String name;

    protected Enumeration(final int id, final String name) {
        Assert.hasText(name, "Name of enumeration must has text.");
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
        private final ImmutableMap<Integer, T> valueMap;

        private ValueSet(ImmutableMap<Integer, T> valueMap) {
            this.valueMap = valueMap;
        }

        @SafeVarargs
        @StaticFactoryMethod(ValueSet.class)
        public static <T extends Enumeration<T>> ValueSet<T> of(T... values) {
            Builder<Integer, T> builder = ImmutableMap.builder();
            for (T value : values) {
                builder.put(value.getId(), value);
            }
            return new ValueSet<>(builder.build());
        }

        public T get(int id) {
            Assert.isTrue(this.valueMap.containsKey(id), "%s 对应的值不存在", id);
            return this.valueMap.get(id);
        }

        public Collection<T> getValues() {
            return this.valueMap.values();
        }
    }
}
