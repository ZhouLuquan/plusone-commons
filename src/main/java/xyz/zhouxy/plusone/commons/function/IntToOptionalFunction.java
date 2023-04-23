package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.IntFunction;

@FunctionalInterface
public interface IntToOptionalFunction<R> extends IntFunction<Optional<R>> {
}
