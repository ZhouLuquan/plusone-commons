package xyz.zhouxy.plusone.commons.annotation;

import java.lang.annotation.Target;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标记不可变对象。根据约定，该对象的字段只能是不可变对象或基础数据类型，且都是只读的。如：
 * <pre>@ImmutableObject
 * class Foo {
 *     private final @Getter int intVal;
 *     private final @Getter String stringVal;
 *     private final @Getter LocalDate dateVal;
 *     private final @Getter BigDecimal decimalVal;
 * }
 * </pre>
 * <p>
 * 如 guava 的 ImmutableList、ImmutableMap 等不可变集合，不属于此，因为其不要求包含的元素本身是不可变的。
 * <p>
 * 
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
@Beta
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImmutableObject {
}
