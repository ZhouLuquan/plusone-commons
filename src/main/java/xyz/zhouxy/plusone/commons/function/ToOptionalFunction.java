package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface ToOptionalFunction<T, R> extends Function<T, Optional<R>> {
}
