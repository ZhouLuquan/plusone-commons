package xyz.zhouxy.plusone.commons.annotation;

/**
 * 标识该方法是可覆写的。不带 final 的方法都是可被子类覆写的，该注解用于提醒、强调父类虽然有默认实现，但子类可以根据自己的需要覆写。
 * 
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @since 0.1.0
 */
public @interface Overridable {
}
