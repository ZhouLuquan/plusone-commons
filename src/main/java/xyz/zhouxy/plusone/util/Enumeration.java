package xyz.zhouxy.plusone.util;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Enumeration<T extends Enumeration<T>> {
    protected final int value;
    protected final String name;

    protected Enumeration(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

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
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(value).append(": ").append(name).append("]");
        return builder.toString();
    }

    protected static final class EnumerationValuesHolder<T extends Enumeration<T>> {
        private final Map<Integer, T> constants = new ConcurrentHashMap<>();
    
        @SafeVarargs
        public EnumerationValuesHolder(T... values) {
            for (T value : values) {
                put(value);
            }
        }
    
        private void put(T constant) {
            this.constants.put(constant.getValue(), constant);
        }
    
        public T get(int value) {
            return this.constants.get(value);
        }
    }
}
