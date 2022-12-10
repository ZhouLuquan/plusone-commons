package xyz.zhouxy.plusone.validator.validator2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class BaseValidator2<T> {

    private List<ValueValidator<T, ?>> valueValidators = new ArrayList<>();

    protected final <R> ValueValidator<T, R> ruleFor(Function<T, R> getter) {
        ValueValidator<T, R> validValueHolder = new ValueValidator<>(getter);
        valueValidators.add(validValueHolder);
        return validValueHolder;
    }

    public void validate(T obj) {
        for (ValueValidator<T, ?> valueValidator : this.valueValidators) {
            valueValidator.validateProperty(obj);
        }
    }
}
