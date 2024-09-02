package xyz.zhouxy.plusone.commons.time;

import static java.time.temporal.ChronoField.YEAR;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

/**
 * 表示年份与季度
 * 
 * @author zhouxy
 */
public final class YearQuarter implements Comparable<YearQuarter>, Serializable {
    private static final long serialVersionUID = 3804145964419489753L;

    /** 年份 */
    private final int year;
    /** 季度 */
    private final Quarter quarter;
    /** 季度开始日期 */
    private final LocalDate firstDate;
    /** 季度结束日期 */
    private final LocalDate lastDate;

    private YearQuarter(int year, @Nonnull Quarter quarter) {
        Preconditions.checkNotNull(quarter, "Quarter can not be null.");
        this.year = year;
        this.quarter = quarter;
        this.firstDate = quarter.firstMonthDay().atYear(year);
        this.lastDate = quarter.lastMonthDay().atYear(year);
    }

    /**
     * 根据指定年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param year 年份
     * @param quarter 季度
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(int year, int quarter) {
        return of(year, Quarter.of(quarter));
    }

    /**
     * 根据指定年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param year 年份
     * @param quarter 季度
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(int year, @Nonnull Quarter quarter) {
        return new YearQuarter(year, quarter);
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(@Nonnull LocalDate date) {
        return of(date.getYear(), Quarter.fromMonth(date.getMonth()));
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(@Nonnull Date date) {
        @SuppressWarnings("deprecation")
        final int year = date.getYear() + 1900;
        @SuppressWarnings("deprecation")
        final int month = date.getMonth() + 1;
        return of(year, Quarter.fromMonth(month));
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(Calendar date) {
        return of(date.get(Calendar.YEAR), Quarter.fromMonth(date.get(Calendar.MONTH) + 1));
    }

    /**
     * 根据指定年月，判断其所在的年份与季度，创建 {@link YearQuarter} 实例
     * 
     * @param yearMonth 年月
     * @return {@link YearQuarter} 实例
     */
    public static YearQuarter of(YearMonth yearMonth) {
        return of(yearMonth.getYear(), Quarter.fromMonth(yearMonth.getMonth()));
    }

    // Getters

    public int getYear() {
        return year;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public YearMonth firstYearMonth() {
        return YearMonth.of(this.year, this.quarter.firstMonth());
    }

    public Month firstMonth() {
        return this.quarter.firstMonth();
    }

    public int firstMonthValue() {
        return this.quarter.firstMonthValue();
    }

    public YearMonth lastYearMonth() {
        return YearMonth.of(this.year, this.quarter.lastMonth());
    }

    public Month lastMonth() {
        return this.quarter.lastMonth();
    }

    public int lastMonthValue() {
        return this.quarter.lastMonthValue();
    }

    public LocalDate firstDate() {
        return firstDate;
    }

    public LocalDate lastDate() {
        return lastDate;
    }

    // Getters end

    // computes

    public YearQuarter plusQuarters(long quartersToAdd) { // TODO 单元测试
        if (quartersToAdd == 0) {
            return this;
        }
        long quarterCount = this.year * 4L + (this.quarter.getValue() - 1);
        long calcQuarters = quarterCount + quartersToAdd; // safe overflow
        int newYear = YEAR.checkValidIntValue(Math.floorDiv(calcQuarters, 4));
        int newQuarter = (int) Math.floorMod(calcQuarters, 4) + 1;
        return of(newYear, Quarter.of(newQuarter));
    }

    public YearQuarter minusQuarters(long quartersToAdd) { // TODO 单元测试
        return plusQuarters(-quartersToAdd);
    }

    public YearQuarter plusYears(long yearsToAdd) { // TODO 单元测试
        if (yearsToAdd == 0) {
            return this;
        }
        int newYear = YEAR.checkValidIntValue(this.year + yearsToAdd);  // safe overflow
        return of(newYear, this.quarter);
    }

    public YearQuarter minusYears(long yearsToAdd) {
        return plusYears(-yearsToAdd);
    }

    // computes end

    // hashCode & equals

    @Override
    public int hashCode() {
        return Objects.hash(year, quarter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        YearQuarter other = (YearQuarter) obj;
        return year == other.year && quarter == other.quarter;
    }

    // compareTo

    @Override
    public int compareTo(YearQuarter other) {
        int cmp = (this.year - other.year);
        if (cmp == 0) {
            cmp = this.quarter.compareTo(other.quarter);
        }
        return cmp;
    }

    public boolean isBefore(YearQuarter other) {
        return this.compareTo(other) < 0;
    }

    public boolean isAfter(YearQuarter other) {
        return this.compareTo(other) > 0;
    }

    // toString

    /**
     * 返回 {@link YearQuarter} 的字符串表示形式，如 "Q3 2024"
     * 
     * @return {@link YearQuarter} 的字符串表示形式
     */
    @Override
    public String toString() {
        return this.quarter.name() + " " + this.year;
    }
}
