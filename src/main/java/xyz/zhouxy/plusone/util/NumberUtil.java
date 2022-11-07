package xyz.zhouxy.plusone.util;

/**
 * NumberUtil
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class NumberUtil {

    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int sum(int... numbers) {
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(long... numbers) {
        long result = 0;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }
}
