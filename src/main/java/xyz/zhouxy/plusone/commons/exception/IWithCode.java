package xyz.zhouxy.plusone.commons.exception;

/**
 * 规定实现类带有 {@code getCode} 方法。
 * 用于像自定义异常等需要带有 {@code code} 字段的类，
 * 方便其它地方的程序判断该类的是否实现了此接口，以此获取其实例的 {@code code} 字段的值。
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see BaseException
 */
public interface IWithCode {
    int getCode();
}
