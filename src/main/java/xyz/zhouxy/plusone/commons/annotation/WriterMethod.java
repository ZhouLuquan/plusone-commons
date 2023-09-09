package xyz.zhouxy.plusone.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO 添加 Javadoc
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WriterMethod {
}
