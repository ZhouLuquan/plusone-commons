/*
 * Copyright 2024 the original author or authors.
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

import java.time.DateTimeException;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;

import com.google.common.collect.Range;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.base.IWithIntCode;
import xyz.zhouxy.plusone.commons.util.AssertTools;

/**
 * 季度
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public enum Quarter implements IWithIntCode {
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

    private final Range<Integer> monthRange;

    /** 常量值 */
    private static final Quarter[] ENUMS = Quarter.values();

    /**
     * @param value 季度值 (1/2/3/4)
     */
    Quarter(int value) {
        this.value = value;

        final int lastMonth = value * 3;
        final int firstMonth = lastMonth - 2;

        this.monthRange = Range.closed(firstMonth, lastMonth);
    }

    // StaticFactoryMethods

    /**
     * 根据给定的月份值返回对应的季度
     *
     * @param monthValue 月份值，取值范围为1到12
     * @return 对应的季度
     * @throws IllegalArgumentException 如果月份值不在有效范围内（1到12），将抛出异常
     */
    @StaticFactoryMethod(Quarter.class)
    public static Quarter fromMonth(int monthValue) {
        ChronoField.MONTH_OF_YEAR.checkValidValue(monthValue);
        return of(computeQuarterValueInternal(monthValue));
    }

    /**
     * 根据给定的月份返回对应的季度
     *
     * @param month 月份
     * @return 对应的季度
     */
    @StaticFactoryMethod(Quarter.class)
    public static Quarter fromMonth(Month month) {
        AssertTools.checkNotNull(month);
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
    @StaticFactoryMethod(Quarter.class)
    public static Quarter of(int value) {
        return ENUMS[checkValidIntValue(value) - 1];
    }

    // StaticFactoryMethods end

    // computes

    public Quarter plus(long quarters) {
        final int amount = (int) ((quarters % 4) + 4);
        return ENUMS[(ordinal() + amount) % 4];
    }

    public Quarter minus(long quarters) {
        return plus(-(quarters % 4));
    }

    // computes end

    // Getters

    public int getValue() {
        return value;
    }

    @Override
    public int getCode() {
        return getValue();
    }

    public Month firstMonth() {
        return Month.of(firstMonthValue());
    }

    public int firstMonthValue() {
        return this.monthRange.lowerEndpoint();
    }

    public Month lastMonth() {
        return Month.of(lastMonthValue());
    }

    public int lastMonthValue() {
        return this.monthRange.upperEndpoint();
    }

    public MonthDay firstMonthDay() {
        return MonthDay.of(firstMonth(), 1);
    }

    public MonthDay lastMonthDay() {
        // 季度的最后一个月不可能是 2 月
        final Month month = lastMonth();
        return MonthDay.of(month, month.maxLength());
    }

    public int firstDayOfYear(boolean leapYear) {
        return firstMonth().firstDayOfYear(leapYear);
    }

    // Getters end

    public static int checkValidIntValue(int value) {
        AssertTools.checkCondition(value >= 1 && value <= 4,
                () -> new DateTimeException("Invalid value for Quarter: " + value));
        return value;
    }

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
