package xyz.zhouxy.plusone.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * UnsupportedOperation
 *
 * <p>标识方法为不支持的操作。该方法将抛出 {@link UnsupportedOperationException}。
 *
 * @author zhouxy
 * @version 1.0
 * @since 1.0
 * @see UnsupportedOperationException
 */
@Documented
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UnsupportedOperation {
}
