package xyz.zhouxy.plusone.util;

import java.util.HashMap;
import java.util.Map;

public final class EnumerationValuesHolder<T extends Enumeration<T>> {
    private final Map<Integer, T> constants = new HashMap<>();

    public EnumerationValuesHolder(T[] values) {
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
