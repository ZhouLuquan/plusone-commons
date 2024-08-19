package xyz.zhouxy.plusone.commons.base;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Preconditions;

public final class YearQuarter {
    private final int year;
    private final Quarter quarter;
    private final LocalDate startDate;
    private final LocalDate lastDate;

    private YearQuarter(int year, Quarter quarter) {
        Preconditions.checkNotNull(quarter, "Quarter can not be null.");
        this.year = year;
        this.quarter = quarter;
        this.startDate = quarter.getStartMonthDay().atYear(year);
        this.lastDate = quarter.getLastMonthDay().atYear(year);
    }

    public static YearQuarter of(int year, Quarter quarter) {
        return new YearQuarter(year, quarter);
    }

    public static YearQuarter of(LocalDate date) {
        return new YearQuarter(date.getYear(), Quarter.fromMonth(date.getMonth()));
    }

    public static YearQuarter of(Date date) {
        @SuppressWarnings("deprecation")
        final int year = date.getYear() + 1900;
        @SuppressWarnings("deprecation")
        final int month = date.getMonth() + 1;
        return of(year, Quarter.fromMonth(month));
    }

    public static YearQuarter of(Calendar date) {
        return of(date.get(Calendar.YEAR), Quarter.fromMonth(date.get(Calendar.MONTH) + 1));
    }

    public static YearQuarter of(YearMonth yearMonth) {
        return of(yearMonth.getYear(), Quarter.fromMonth(yearMonth.getMonth()));
    }

    public int getYear() {
        return year;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public Month getStartMonth() {
        return this.quarter.getStartMonth();
    }

    public int getStartMonthValue() {
        return this.quarter.getStartMonthValue();
    }

    public Month getLastMonth() {
        return this.quarter.getLastMonth();
    }

    public int getLastMonthValue() {
        return this.quarter.getLastMonthValue();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }
}
