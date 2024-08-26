package xyz.zhouxy.plusone.commons.base;

import java.time.Month;
import java.time.MonthDay;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.util.Numbers;

/**
 * 季度
 * 
 * @author zhouxy
 */
public enum Quarter {
    /** 第一季度 */
    Q1(1),
    /** 第二季度 */
    Q2(2),
    /** 第三季度 */
    Q3(3),
    /** 第四季度 */
    Q4(4),
    ;

    /** 季度值 (1/2/3/4) */
    private final int value;

    /** 季度开始月份 */
    private final Month firstMonth;
    /** 季度开始日期 */
    private final MonthDay firstMonthDay;

    /** 季度结束月份 */
    private final Month lastMonth;
    /** 季度结束日期 */
    private final MonthDay lastMonthDay;

    /** 常量值 */
    private static final Quarter[] ENUMS = Quarter.values();

    /**
     * @param value 季度值 (1/2/3/4)
     */
    Quarter(int value) {
        this.value = value;

        final int lastMonthValue = value * 3;
        final int firstMonthValue = lastMonthValue - 2;

        this.firstMonth = Month.of(firstMonthValue);
        this.firstMonthDay = MonthDay.of(this.firstMonth, 1);
        this.lastMonth = Month.of(lastMonthValue);
        // 季度的最后一个月不可能是 2 月
        this.lastMonthDay = MonthDay.of(this.lastMonth, this.lastMonth.maxLength());
    }

    /**
     * 根据给定的月份值返回对应的季度
     * 
     * @param monthValue 月份值，取值范围为1到12
     * @return 对应的季度
     * @throws IllegalArgumentException 如果月份值不在有效范围内（1到12），将抛出异常
     */
    public static Quarter fromMonth(int monthValue) {
        Preconditions.checkArgument(Numbers.between(monthValue, 1, 13), "Invalid value for MonthOfYear: " + monthValue);
        return of(computeQuarterValueInternal(monthValue));
    }

    /**
     * 根据给定的月份返回对应的季度
     * 
     * @param month 月份
     * @return 对应的季度
     */
    public static Quarter fromMonth(Month month) {
        final int monthValue = month.getValue();
        return of(computeQuarterValueInternal(monthValue));
    }

    /**
     * 根据指定的年份，获取一个新的 YearQuarter 实例
     * 此方法允许在保持当前季度信息不变的情况下，更改年份
     *
     * @param year 指定的年份
     * @return 返回一个新的 YearQuarter 实例，年份更新为指定的年份
     */
    public final YearQuarter atYear(int year) {
        return YearQuarter.of(year, this);
    }

    /**
     * 根据给定的季度值返回对应的季度
     * 
     * @param value 季度值 (1/2/3/4)
     * @return 对应的季度
     * @throws IllegalArgumentException 如果季度值不在有效范围内（1到4），将抛出异常
     */
    public static Quarter of(int value) {
        if (value < 1 || value > 4) {
            throw new IllegalArgumentException("Invalid value for Quarter: " + value);
        }
        return ENUMS[value - 1];
    }

    // Getters

    public int getValue() {
        return value;
    }

    public Month firstMonth() {
        return firstMonth;
    }

    public int firstMonthValue() {
        return firstMonth.getValue();
    }

    public Month lastMonth() {
        return lastMonth;
    }

    public int lastMonthValue() {
        return lastMonth.getValue();
    }

    public MonthDay firstMonthDay() {
        return firstMonthDay;
    }

    public MonthDay lastMonthDay() {
        return lastMonthDay;
    }

    public int firstDayOfYear(boolean leapYear) {
        return this.firstMonth.firstDayOfYear(leapYear);
    }

    // Getters end

    // Internal

    /**
     * 计算给定月份对应的季度值
     * 
     * @param monthValue 月份值，取值范围为1到12
     * @return 对应的季度值
     */
    private static int computeQuarterValueInternal(int monthValue) {
        return (monthValue - 1) / 3 + 1;
    }
}
