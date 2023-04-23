package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.LongFunction;

@FunctionalInterface
public interface LongToOptionalFunction<R> extends LongFunction<Optional<R>> {
}
