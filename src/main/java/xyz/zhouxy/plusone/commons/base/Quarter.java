package xyz.zhouxy.plusone.commons.base;

import java.time.Month;
import java.time.MonthDay;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.util.Numbers;

public enum Quarter {
    Q1(1, "Q1"),
    Q2(2, "Q2"),
    Q3(3, "Q3"),
    Q4(4, "Q4"),
    ;

    private final int value;
    private final String displayName;

    private final int startMonthValue;
    private final MonthDay startMonthDay;

    private final int lastMonthValue;
    private final MonthDay lastMonthDay;

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

    public static Quarter fromMonth(int monthValue) {
        Preconditions.checkArgument(Numbers.between(monthValue, 1, 13), "Invalid value for MonthOfYear: " + monthValue);
        return of(computeQuarterValueInternal(monthValue));
    }

    public static Quarter fromMonth(Month month) {
        final int monthValue = month.getValue();
        return of(computeQuarterValueInternal(monthValue));
    }

    public final YearQuarter atYear(int year) {
        return YearQuarter.of(year, this);
    }

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

    /**
     * 计算季度
     * 
     * @param monthValue 1~12
     * @return 季度。1~4
     */
    private static int computeQuarterValueInternal(int monthValue) {
        return (monthValue - 1) / 3 + 1;
    }
}
