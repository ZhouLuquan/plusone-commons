package xyz.zhouxy.plusone.util;

import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public final class EnumUtil {

    private EnumUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E valueOf(Class<E> clazz, int ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return values[ordinal];
        } catch (IndexOutOfBoundsException e) {
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueOrDefault(Class<E> clazz, Integer ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return Objects.nonNull(ordinal) ? values[ordinal] : values[0];
        } catch (IndexOutOfBoundsException e) {
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    /**
     * 通过 ordinal 获取枚举实例
     *
     * @param <E>     枚举的类型
     * @param clazz   枚举的类型信息
     * @param ordinal 数据库中对应的数值
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E getValueNullable(Class<E> clazz, Integer ordinal) {
        E[] values = clazz.getEnumConstants();
        try {
            return Objects.nonNull(ordinal) ? values[ordinal] : null;
        } catch (IndexOutOfBoundsException e) {
            throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
        }
    }

    public static <E extends Enum<?>> Integer checkOrdinal(Class<E> clazz, Integer ordinal) {
        if (ordinal == null) {
            throw new IllegalArgumentException("ordinal 不能为空");
        }
        E[] values = clazz.getEnumConstants();
        if (ordinal >= 0 && ordinal < values.length) {
            return ordinal;
        }
        throw new EnumConstantNotPresentException(clazz, Integer.toString(ordinal));
    }

    public static <E extends Enum<?>> Integer checkOrdinalNullable(Class<E> clazz, Integer ordinal) {
        if (Objects.isNull(ordinal)) {
            return null;
        }
        return checkOrdinal(clazz, ordinal);
    }

    public static <E extends Enum<?>> Integer checkOrdinalOrDefault(Class<E> clazz, Integer ordinal) {
        if (Objects.isNull(ordinal)) {
            return 0;
        }
        return checkOrdinal(clazz, ordinal);
    }
}
