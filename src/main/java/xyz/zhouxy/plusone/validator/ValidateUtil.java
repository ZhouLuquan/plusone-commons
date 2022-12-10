package xyz.zhouxy.plusone.validator;

/**
 * 校验工具类
 * <p>
 * 对 {@link IValidateRequired} 的实现类对象进行校验
 * </p>
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 *
 * @see BaseValidator
 * @see Validator
 * @see IValidateRequired
 */
public class ValidateUtil {
    private ValidateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> void validate(T obj, BaseValidator<T> validator) {
        validator.validate(obj);
    }
}
