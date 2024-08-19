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
    Q1(1, "Q1"),
    /** 第二季度 */
    Q2(2, "Q2"),
    /** 第三季度 */
    Q3(3, "Q3"),
    /** 第四季度 */
    Q4(4, "Q4"),
    ;

    /** 季度值 (1/2/3/4) */
    private final int value;
    /** 季度名称 */
    private final String displayName;

    /** 季度开始月份 */
    private final int startMonthValue;
    /** 季度开始日期 */
    private final MonthDay startMonthDay;

    /** 季度结束月份 */
    private final int lastMonthValue;
    /** 季度结束日期 */
    private final MonthDay lastMonthDay;

    /**
     * @param value 季度值 (1/2/3/4)
     * @param str   季度名称
     */
    Quarter(int value, String str) {
        this.value = value;
        this.displayName = str;

        final int lastMonth = value * 3;
        final int startMonth = lastMonth - 2;

        this.startMonthValue = startMonth;
        this.startMonthDay = MonthDay.of(startMonth, 1);
        this.lastMonthValue = lastMonth;
        this.lastMonthDay = MonthDay.of(lastMonth, (value == 1 || value == 4) ? 31 : 30);
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
        switch (value) {
            case 1:
                return Q1;
            case 2:
                return Q2;
            case 3:
                return Q3;
            case 4:
                return Q4;
            default:
                throw new EnumConstantNotPresentException(Quarter.class, Integer.toString(value));
        }
    }

    /**
     * 根据给定的季度名称返回对应的季度
     * 
     * @param str 季度名称
     * @return 对应的季度
     * @throws IllegalArgumentException 如果季度名称不在有效范围内（Q1/Q2/Q3/Q4），将抛出异常
     */
    public static Quarter of(String str) {
        switch (str) {
            case "Q1":
                return Q1;
            case "Q2":
                return Q2;
            case "Q3":
                return Q3;
            case "Q4":
                return Q4;
            default:
                throw new EnumConstantNotPresentException(Quarter.class, str);
        }
    }

    // Getters

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Month getStartMonth() {
        return Month.of(startMonthValue);
    }

    public int getStartMonthValue() {
        return startMonthValue;
    }

    public Month getLastMonth() {
        return Month.of(lastMonthValue);
    }

    public int getLastMonthValue() {
        return lastMonthValue;
    }

    public MonthDay getStartMonthDay() {
        return startMonthDay;
    }

    public MonthDay getLastMonthDay() {
        return lastMonthDay;
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
