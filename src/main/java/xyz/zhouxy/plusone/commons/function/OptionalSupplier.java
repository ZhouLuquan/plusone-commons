package xyz.zhouxy.plusone.commons.function;

import java.util.Optional;
import java.util.function.Supplier;

@FunctionalInterface
public interface OptionalSupplier<T> extends Supplier<Optional<T>> {
}
