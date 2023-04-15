package xyz.zhouxy.plusone.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * StaticFactoryMethod
 *
 * <p>标识方法为静态工厂方法
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StaticFactoryMethod {
    Class<?> type();
}
