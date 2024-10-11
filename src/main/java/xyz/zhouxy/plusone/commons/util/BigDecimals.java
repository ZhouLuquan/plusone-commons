package xyz.zhouxy.plusone.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

public class BigDecimals {

    public static final BigDecimal ZERO = new BigDecimal("0.00");

    public static boolean equals(@Nullable BigDecimal a, @Nullable BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) > 0);
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        return gt(a, b) || equals(a, b);
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) < 0);
    }

    public static boolean le(BigDecimal a, BigDecimal b) {
        return lt(a, b) || equals(a, b);
    }

    public static BigDecimal of(final String val) {
        return (StringTools.isNotBlank(val)) ? new BigDecimal(val) : ZERO;
    }

    /**
     * 默认除法运算精度
     */
    private static final int DEFAULT_STR_SCALE = 2;

    private static final RoundingMode DEFAULT_STR_ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * 使用四舍五入（{@link RoundingMode#HALF_UP}），保留两位小数，转为字符串。
     */
    public static String toPlainString(@Nonnull BigDecimal value) {
        AssertTools.checkParameterNotNull("value", value);
        return toPlainStringInternal(value, DEFAULT_STR_SCALE, DEFAULT_STR_ROUNDING_MODE);
    }

    /**
     * 使用四舍五入（{@link RoundingMode#HALF_UP}），保留指定位的小数，转为字符串。
     */
    public static String toPlainString(@Nonnull BigDecimal value, int scale) {
        AssertTools.checkParameterNotNull("value", value);
        return toPlainStringInternal(value, scale, DEFAULT_STR_ROUNDING_MODE);
    }

    /**
     * 使用指定 {@link RoundingMode}，保留两位小数，转为字符串。
     */
    public static String toPlainString(@Nonnull BigDecimal value, @Nonnull RoundingMode roundingMode) {
        AssertTools.checkParameterNotNull("value", value);
        AssertTools.checkParameterNotNull("rounding mode", roundingMode);
        return toPlainStringInternal(value, DEFAULT_STR_SCALE, roundingMode);
    }

    /**
     * 使用指定 {@link RoundingMode}，保留指定位的小数，转为字符串。
     */
    public static String toPlainString(@Nonnull BigDecimal value,
            int scale, @Nonnull RoundingMode roundingMode) {
        AssertTools.checkParameterNotNull("value", value);
        AssertTools.checkParameterNotNull("rounding mode", roundingMode);
        return toPlainStringInternal(value, scale, roundingMode);
    }

    private static String toPlainStringInternal(@Nonnull BigDecimal value,
            int scale, @Nonnull RoundingMode roundingMode) {
        return value.setScale(scale, roundingMode).toPlainString();
    }

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = BigDecimal.valueOf(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的类型转换(Float)
     *
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static float convertToFloat(double v) {
        BigDecimal b = BigDecimal.valueOf(v);
        return b.floatValue();
    }

    /**
     * 提供精确的类型转换(Int)不进行四舍五入
     *
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static int convertsToInt(double v) {
        BigDecimal b = BigDecimal.valueOf(v);
        return b.intValue();
    }

    /**
     * 提供精确的类型转换(Long)
     *
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static long convertsToLong(double v) {
        BigDecimal b = BigDecimal.valueOf(v);
        return b.longValue();
    }

    /**
     * 返回两个数中大的一个值
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中大的一个值
     */
    public static double returnMax(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.max(b2).doubleValue();
    }

    /**
     * 返回两个数中小的一个值
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中小的一个值
     */
    public static double returnMin(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.min(b2).doubleValue();
    }

    /**
     * 精确对比两个数字
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */
    public static int compareTo(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.compareTo(b2);
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
