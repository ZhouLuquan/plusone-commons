package xyz.zhouxy.plusone.commons.base;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import com.google.common.annotations.Beta;

@Beta
public final class Ref<T> {

    private T value;

    public Ref() {
        this.value = null;
    }

    public Ref(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void transform(UnaryOperator<T> operator) {
        this.value = operator.apply(this.value);
    }

    public boolean isNull() {
        return this.value == null;
    }

    public boolean isNotNull() {
        return this.value != null;
    }

    public void execute(Consumer<T> consumer) {
        consumer.accept(value);
    }

    @Override
    public String toString() {
        return String.format("Ref[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ref<?> other = (Ref<?>) obj;
        return Objects.equals(this.value, other.value);
    }

}
