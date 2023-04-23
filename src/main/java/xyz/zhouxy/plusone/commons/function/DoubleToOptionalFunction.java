package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.DoubleFunction;

@FunctionalInterface
public interface DoubleToOptionalFunction<R> extends DoubleFunction<Optional<R>> {
}
