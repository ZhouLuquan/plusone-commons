/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.time;

import static java.time.temporal.ChronoField.YEAR;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.errorprone.annotations.Immutable;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.util.AssertTools;

/**
 * 表示年份与季度
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
@Immutable
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

    private YearQuarter(int year, Quarter quarter) {
        this.year = year;
        this.quarter = quarter;
        this.firstDate = quarter.firstMonthDay().atYear(year);
        this.lastDate = quarter.lastMonthDay().atYear(year);
    }

    // #region - StaticFactory

    /**
     * 根据指定年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param year 年份
     * @param quarter 季度
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(int year, int quarter) {
        return new YearQuarter(YEAR.checkValidIntValue(year), Quarter.of(quarter));
    }

    /**
     * 根据指定年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param year 年份
     * @param quarter 季度
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(int year, Quarter quarter) {
        return new YearQuarter(YEAR.checkValidIntValue(year), Objects.requireNonNull(quarter));
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(LocalDate date) {
        AssertTools.checkNotNull(date);
        return new YearQuarter(date.getYear(), Quarter.fromMonth(date.getMonth()));
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(Date date) {
        AssertTools.checkNotNull(date);
        @SuppressWarnings("deprecation")
        final int yearValue = YEAR.checkValidIntValue(date.getYear() + 1900L);
        @SuppressWarnings("deprecation")
        final int monthValue = date.getMonth() + 1;
        return new YearQuarter(yearValue, Quarter.fromMonth(monthValue));
    }

    /**
     * 根据指定日期，判断日期所在的年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param date 日期
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(Calendar date) {
        AssertTools.checkNotNull(date);
        final int yearValue = ChronoField.YEAR.checkValidIntValue(date.get(Calendar.YEAR));
        final int monthValue = date.get(Calendar.MONTH) + 1;
        return new YearQuarter(yearValue, Quarter.fromMonth(monthValue));
    }

    /**
     * 根据指定年月，判断其所在的年份与季度，创建 {@link YearQuarter} 实例
     *
     * @param yearMonth 年月
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter of(YearMonth yearMonth) {
        AssertTools.checkNotNull(yearMonth);
        return of(yearMonth.getYear(), Quarter.fromMonth(yearMonth.getMonth()));
    }

    /**
     * 根据现在的日期，判断所在的年份与季度，创建 {@link YearQuarter} 实例
     *
     * @return {@link YearQuarter} 实例
     */
    @StaticFactoryMethod(YearQuarter.class)
    public static YearQuarter now() {
        return of(LocalDate.now());
    }

    // #endregion

    // #region - Getters

    /**
     * 年份
     *
     * @return 年份
     */
    public int getYear() {
        return this.year;
    }

    /**
     * 季度
     *
     * @return 季度
     */
    public Quarter getQuarter() {
        return this.quarter;
    }

    /**
     * 季度值。从 1 开始。
     *
     * @return 季度值
     */
    public int getQuarterValue() {
        return this.quarter.getValue();
    }

    /**
     * 该季度第一个月
     *
     * @return {@link YearMonth} 对象
     */
    public YearMonth firstYearMonth() {
        return YearMonth.of(this.year, this.quarter.firstMonth());
    }

    /**
     * 该季度第一个月
     *
     * @return {@link Month} 对象
     */
    public Month firstMonth() {
        return this.quarter.firstMonth();
    }

    /**
     * 该季度的第一个月
     *
     * @return 结果。月份值从 1 开始，1 表示 1月，以此类推。
     */
    public int firstMonthValue() {
        return this.quarter.firstMonthValue();
    }

    /**
     * 该季度的最后一个月
     *
     * @return {@link YearMonth} 对象
     */
    public YearMonth lastYearMonth() {
        return YearMonth.of(this.year, this.quarter.lastMonth());
    }

    /**
     * 该季度的最后一个月
     *
     * @return {@link Month} 对象
     */
    public Month lastMonth() {
        return this.quarter.lastMonth();
    }

    /**
     * 该季度的最后一个月
     *
     * @return 结果。月份值从 1 开始，1 表示 1月，以此类推。
     */
    public int lastMonthValue() {
        return this.quarter.lastMonthValue();
    }

    /**
     * 该季度的第一天
     *
     * @return {@link LocalDate} 对象
     */
    public LocalDate firstDate() {
        return firstDate;
    }

    /**
     * 该季度的最后一天
     *
     * @return {@link LocalDate} 对象
     */
    public LocalDate lastDate() {
        return lastDate;
    }

    // #endregion

    // #region - computes

    public YearQuarter plusQuarters(long quartersToAdd) {
        if (quartersToAdd == 0L) {
            return this;
        }
        long quarterCount = this.year * 4L + (this.quarter.getValue() - 1);
        long calcQuarters = quarterCount + quartersToAdd; // safe overflow
        int newYear = YEAR.checkValidIntValue(Math.floorDiv(calcQuarters, 4));
        int newQuarter = (int) Math.floorMod(calcQuarters, 4) + 1;
        return new YearQuarter(newYear, Quarter.of(newQuarter));
    }

    public YearQuarter minusQuarters(long quartersToAdd) {
        return plusQuarters(-quartersToAdd);
    }

    public YearQuarter nextQuarter() {
        return plusQuarters(1L);
    }

    public YearQuarter lastQuarter() {
        return minusQuarters(1L);
    }

    public YearQuarter plusYears(long yearsToAdd) {
        if (yearsToAdd == 0L) {
            return this;
        }
        int newYear = YEAR.checkValidIntValue(this.year + yearsToAdd); // safe overflow
        return new YearQuarter(newYear, this.quarter);
    }

    public YearQuarter minusYears(long yearsToAdd) {
        return plusYears(-yearsToAdd);
    }

    public YearQuarter nextYear() {
        return plusYears(1L);
    }

    public YearQuarter lastYear() {
        return minusYears(1L);
    }

    // #endregion

    // #region - hashCode & equals

    @Override
    public int hashCode() {
        return Objects.hash(year, quarter);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        YearQuarter other = (YearQuarter) obj;
        return year == other.year && quarter == other.quarter;
    }

    // #endregion

    // #region - compare

    @SuppressWarnings("null")
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

    // #endregion

    // #region - toString

    /**
     * 返回 {@link YearQuarter} 的字符串表示形式，如 "2024 Q3"
     *
     * @return {@link YearQuarter} 的字符串表示形式
     */
    @Override
    public String toString() {
        return this.year + " " + this.quarter.name();
    }

    // #endregion
}
