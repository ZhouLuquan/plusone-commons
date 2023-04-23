package xyz.zhouxy.plusone.commons.function;

import java.util.OptionalLong;
import java.util.function.Function;

@FunctionalInterface
public interface ToOptionalLongFunction<T> extends Function<T, OptionalLong> {
}
