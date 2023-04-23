package xyz.zhouxy.plusone.commons.function;

import java.util.OptionalInt;
import java.util.function.Function;

@FunctionalInterface
public interface ToOptionalIntFunction<T> extends Function<T, OptionalInt> {
}
