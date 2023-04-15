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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

/**
 * 枚举类
 */
public abstract class Enumeration<T extends Enumeration<T>> {
    protected final int value;
    @Nonnull
    protected final String name;

    protected Enumeration(int value, @Nonnull String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Nonnull
    public String getName() {
        return name;
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
        Enumeration<?> other = (Enumeration<?>) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return "[" + value + ": " + name + "]";
    }

    protected static final class EnumerationValuesHolder<T extends Enumeration<T>> {
        private final Map<Integer, T> constants = new ConcurrentHashMap<>();
    
        @SafeVarargs
        public EnumerationValuesHolder(T... values) {
            for (T value : values) {
                Objects.requireNonNull(value);
                put(value);
            }
        }
    
        private void put(@Nonnull T constant) {
            this.constants.put(constant.getValue(), constant);
        }

        @Nonnull
        public T get(int value) {
            T val = this.constants.get(value);
            Objects.requireNonNull(val);
            return val;
        }
    }
}
