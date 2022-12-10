package xyz.zhouxy.plusone.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 校验器
 *
 * <p>
 * 可以使用以下方式初始化一个校验器：
 * </p>
 *
 * <pre>
 * BaseValidator&lt;Integer&gt; validator = new BaseValidator&lt;&gt;() {
 *     {
 *         withRule(value -> Objects.nonNull(value), "value 不能为空");
 *         withRule(value -> (value >= 0 && value <= 500), "value 应在 [0, 500] 内");
 *     }
 * };
 * </pre>
 *
 * <p>
 * 也可以通过继承本类，定义一个校验器（可使用单例模式）。
 * </p>
 *
 * <p>
 * 然后通过校验器的 {@link #validate} 方法，或
 * {@link ValidateUtil#validate(Object, Validator)} 对指定对象进行校验。
 * </p>
 *
 * <pre>
 * ValidateUtil.validate(255, validator);
 * </pre>
 *
 * <pre>
 * validator.validate(666);
 * </pre>
 * </p>
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see IValidateRequired
 * @see ValidateUtil
 * @see Validator
 */
public abstract class BaseValidator<T> {

    private final List<RuleInfo<T, ?>> rules = new ArrayList<>();

    protected BaseValidator() {
    }

    protected final void withRule(Predicate<T> rule, String errorMessage) {
        withRule(rule, () -> new InvalidInputException(errorMessage));
    }

    protected final <E extends RuntimeException> void withRule(Predicate<T> rule, Supplier<E> exceptionCreator) {
        withRule(rule, value -> exceptionCreator.get());
    }

    protected final <E extends RuntimeException> void withRule(Predicate<T> rule, Function<T, E> exceptionCreator) {
        this.rules.add(new RuleInfo<>(rule, exceptionCreator));
    }

    public void validate(T obj) {
        this.rules.forEach(ruleInfo -> ruleInfo.validate(obj));
    }

    protected static class RuleInfo<T, E extends RuntimeException> {
        private final Predicate<T> rule;
        private final Function<T, E> exceptionCreator;

        private RuleInfo(Predicate<T> rule, Function<T, E> exceptionCreator) {
            this.rule = rule;
            this.exceptionCreator = exceptionCreator;
        }

        private void validate(T obj) {
            if (!rule.test(obj)) {
                throw exceptionCreator.apply(obj);
            }
        }
    }
}
