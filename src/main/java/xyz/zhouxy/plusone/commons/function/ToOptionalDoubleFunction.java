package xyz.zhouxy.plusone.commons.function;

import java.util.OptionalDouble;
import java.util.function.Function;

@FunctionalInterface
public interface ToOptionalDoubleFunction<T> extends Function<T, OptionalDouble> {
}
