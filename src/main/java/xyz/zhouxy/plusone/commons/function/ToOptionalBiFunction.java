package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.BiFunction;

@FunctionalInterface
public interface ToOptionalBiFunction<T, U, R> extends BiFunction<T, U, Optional<R>> {
}
