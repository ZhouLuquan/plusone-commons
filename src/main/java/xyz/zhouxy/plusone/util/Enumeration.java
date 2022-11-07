package xyz.zhouxy.plusone.util;

import java.util.Objects;

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
}
