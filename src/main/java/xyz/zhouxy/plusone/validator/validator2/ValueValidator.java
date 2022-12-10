package xyz.zhouxy.plusone.validator.validator2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import cn.hutool.core.util.StrUtil;
import xyz.zhouxy.plusone.constant.RegexConsts;
import xyz.zhouxy.plusone.util.RegexUtil;
import xyz.zhouxy.plusone.validator.InvalidInputException;

public class ValueValidator<DTO, PROPERTY> {
    Function<DTO, PROPERTY> getter;
    List<Consumer<PROPERTY>> rules = new ArrayList<>();

    public ValueValidator(Function<DTO, PROPERTY> getter) {
        this.getter = getter;
    }

    private <E extends RuntimeException> void withRule(Predicate<PROPERTY> condition,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> {
            if (!condition.test(value)) {
                throw exceptionCreator.apply(value);
            }
        });
    }

    private <E extends RuntimeException> void withRule(Consumer<PROPERTY> rule) {
        this.rules.add(rule);
    }

    // ====================
    // ====== Object ======
    // ====================

    // ====== notNull =====

    public ValueValidator<DTO, PROPERTY> notNull() {
        return notNull("Value could not be null.");
    }

    public ValueValidator<DTO, PROPERTY> notNull(String errMsg) {
        return notNull(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notNull(Supplier<E> exceptionCreator) {
        return notNull(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notNull(Function<PROPERTY, E> exceptionCreator) {
        withRule(Objects::nonNull, exceptionCreator);
        return this;
    }

    // ====== isNull =====

    public ValueValidator<DTO, PROPERTY> isNull(String errMsg) {
        return isNull(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isNull(Supplier<E> exceptionCreator) {
        return isNull(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isNull(Function<PROPERTY, E> exceptionCreator) {
        withRule(Objects::isNull, exceptionCreator);
        return this;
    }

    // ===== equals =====

    public ValueValidator<DTO, PROPERTY> equalsThat(Object that) {
        return equalsThat(that, value -> new InvalidInputException(String.format("(%s) 必须与 (%s) 相等", value, that)));
    }

    public ValueValidator<DTO, PROPERTY> equalsThat(Object that, String errMsg) {
        return equalsThat(that, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> equalsThat(
            Object that, Supplier<E> exceptionCreator) {
        return equalsThat(that, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> equalsThat(
            Object that, Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> Objects.equals(value, that), exceptionCreator);
        return this;
    }

    // ===== state =====

    public ValueValidator<DTO, PROPERTY> state(Predicate<PROPERTY> condition) {
        return state(condition, "无效的用户输入");
    }

    public ValueValidator<DTO, PROPERTY> state(Predicate<PROPERTY> condition, String errMsg) {
        return state(condition, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> state(
            Predicate<PROPERTY> condition,
            Supplier<E> exceptionCreator) {
        return state(condition, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> state(
            Predicate<PROPERTY> condition,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(condition, exceptionCreator);
        return this;
    }

    // =================
    // ====== int ======
    // =================

    public ValueValidator<DTO, PROPERTY> between(int min, int max) {
        return between(min, max, String.format("数值不在 %s 和 %s 之间", String.valueOf(min), String.valueOf(max)));
    }

    public ValueValidator<DTO, PROPERTY> between(int min, int max, String errMsg) {
        return between(min, max, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> between(int min, int max,
            Supplier<E> exceptionCreator) {
        return between(min, max, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> between(int min, int max,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> ((int) value >= min && (int) value < max), exceptionCreator);
        return this;
    }

    // ====================
    // ====== double ======
    // ====================

    public ValueValidator<DTO, PROPERTY> between(double min, double max) {
        return between(min, max, String.format("数值不在 %s 和 %s 之间", String.valueOf(min), String.valueOf(max)));
    }

    public ValueValidator<DTO, PROPERTY> between(double min, double max, String errMsg) {
        return between(min, max, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> between(double min, double max,
            Supplier<E> exceptionCreator) {
        return between(min, max, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> between(double min, double max,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> ((double) value >= min && (double) value < max), exceptionCreator);
        return this;
    }

    // ================================
    // ====== Collection, String ======
    // ================================

    // ====== notEmpty =====

    public ValueValidator<DTO, PROPERTY> notEmpty(String errMsg) {
        return notEmpty(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notEmpty(Supplier<E> exceptionCreator) {
        return notEmpty(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notEmpty(Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> {
            if (value == null) {
                return false;
            }
            if (value instanceof Collection) {
                return !((Collection<?>) value).isEmpty();
            }
            if (value instanceof String) {
                return !((String) value).isEmpty();
            }
            return false;
        }, exceptionCreator);
        return this;
    }

    // ====== isEmpty =====

    public ValueValidator<DTO, PROPERTY> isEmpty(String errMsg) {
        return isEmpty(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isEmpty(Supplier<E> exceptionCreator) {
        return isEmpty(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isEmpty(Function<PROPERTY, E> exceptionCreator) {
        withRule(value -> {
            if (value == null) {
                return false;
            }
            if (value instanceof Collection) {
                return ((Collection<?>) value).isEmpty();
            }
            if (value instanceof String) {
                return ((String) value).isEmpty();
            }
            return false;
        }, exceptionCreator);
        return this;
    }

    // =====================
    // ====== boolean ======
    // =====================

    // ====== isTrue ======

    public ValueValidator<DTO, PROPERTY> isTrue() {
        return isTrue("The value must be true.");
    }

    public ValueValidator<DTO, PROPERTY> isTrue(String errMsg) {
        return isTrue(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isTrue(Supplier<E> exceptionCreator) {
        return isTrue(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isTrue(Function<PROPERTY, E> exceptionCreator) {
        withRule(Boolean.TRUE::equals, exceptionCreator);
        return this;
    }

    // ====== isFalse ======

    public ValueValidator<DTO, PROPERTY> isFalse() {
        return isFalse("The value must be false.");
    }

    public ValueValidator<DTO, PROPERTY> isFalse(String errMsg) {
        return isFalse(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isFalse(Supplier<E> exceptionCreator) {
        return isFalse(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> isFalse(Function<PROPERTY, E> exceptionCreator) {
        withRule(Boolean.FALSE::equals, exceptionCreator);
        return this;
    }

    // ====================
    // ====== String ======
    // ====================

    // ===== matches =====

    public ValueValidator<DTO, PROPERTY> matches(String regex, String errMsg) {
        return matches(regex, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matches(
            String regex,
            Supplier<E> exceptionCreator) {
        return matches(regex, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matches(
            String regex,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(input -> RegexUtil.matches((String) input, regex), exceptionCreator);
        return this;
    }

    // ===== matchesOr =====

    public ValueValidator<DTO, PROPERTY> matchesOr(String[] regexs, String errMsg) {
        return matchesOr(regexs, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matchesOr(
            String[] regexs,
            Supplier<E> exceptionCreator) {
        return matchesOr(regexs, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matchesOr(
            String[] regexs,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(input -> RegexUtil.matchesOr((String) input, regexs), exceptionCreator);
        return this;
    }

    // ===== matchesAnd =====

    public ValueValidator<DTO, PROPERTY> matchesAnd(String[] regexs, String errMsg) {
        return matchesAnd(regexs, convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matchesAnd(
            String[] regexs,
            Supplier<E> exceptionCreator) {
        return matchesAnd(regexs, convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> matchesAnd(
            String[] regexs,
            Function<PROPERTY, E> exceptionCreator) {
        withRule(input -> RegexUtil.matchesAnd((String) input, regexs), exceptionCreator);
        return this;
    }

    // ===== notBlank =====

    public ValueValidator<DTO, PROPERTY> notBlank() {
        return notBlank("This String argument must have text; it must not be null, empty, or blank");
    }

    public ValueValidator<DTO, PROPERTY> notBlank(String errMsg) {
        return notBlank(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notBlank(Supplier<E> exceptionCreator) {
        return notBlank(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> notBlank(Function<PROPERTY, E> exceptionCreator) {
        withRule(input -> StrUtil.isNotBlank((String) input), exceptionCreator);
        return this;
    }

    // ===== email =====

    public ValueValidator<DTO, PROPERTY> email() {
        return email("The value is not an email address.");
    }

    public ValueValidator<DTO, PROPERTY> email(String errMsg) {
        return email(convertExceptionCreator(errMsg));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> email(Supplier<E> exceptionCreator) {
        return email(convertExceptionCreator(exceptionCreator));
    }

    public <E extends RuntimeException> ValueValidator<DTO, PROPERTY> email(Function<PROPERTY, E> exceptionCreator) {
        return matches(RegexConsts.EMAIL, exceptionCreator);
    }

    // ========================================================================

    void validateProperty(DTO obj) {
        PROPERTY value = this.getter.apply(obj);
        for (Consumer<PROPERTY> rule : this.rules) {
            rule.accept(value);
        }
    }

    private static <V> Function<V, InvalidInputException> convertExceptionCreator(String errMsg) {
        return convertExceptionCreator(errMsg);
    }

    private static <V, E extends RuntimeException> Function<V, E> convertExceptionCreator(
            Supplier<E> exceptionSupplier) {
        return value -> exceptionSupplier.get();
    }
}
