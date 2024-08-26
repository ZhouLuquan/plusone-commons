package xyz.zhouxy.plusone.commons.base;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;
import java.time.MonthDay;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public
class QuarterTests {

    @Test
    void testQ1() {
        Quarter quarter = Quarter.of(1);
        assertSame(Quarter.Q1, quarter);
        assertSame(quarter, Quarter.valueOf("Q1"));
        assertEquals(1, quarter.getValue());
        assertEquals("Q1", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.of(0);
        });

        // ==========

        int startMonthValue = quarter.firstMonthValue();
        log.info("startMonthValue: {}", startMonthValue);
        assertEquals(1, startMonthValue);

        Month startMonth = quarter.firstMonth();
        log.info("startMonth: {}", startMonth);
        assertEquals(Month.JANUARY, startMonth);

        assertEquals(startMonthValue, startMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(3, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.MARCH, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay startMonthDay = quarter.firstMonthDay();
        log.info("startMonthDay: {}", startMonthDay);
        assertEquals(startMonthDay, MonthDay.of(1, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(3, 31));
    }

    @Test
    void testQ2() {
        Quarter quarter = Quarter.of(2);
        assertSame(Quarter.Q2, quarter);
        assertSame(quarter, Quarter.valueOf("Q2"));
        assertEquals(2, quarter.getValue());
        assertEquals("Q2", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.of(5);
        });

        // ==========

        int startMonthValue = quarter.firstMonthValue();
        log.info("startMonthValue: {}", startMonthValue);
        assertEquals(4, startMonthValue);

        Month startMonth = quarter.firstMonth();
        log.info("startMonth: {}", startMonth);
        assertEquals(Month.APRIL, startMonth);

        assertEquals(startMonthValue, startMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(6, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.JUNE, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay startMonthDay = quarter.firstMonthDay();
        log.info("startMonthDay: {}", startMonthDay);
        assertEquals(startMonthDay, MonthDay.of(4, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(6, 30));
    }

    @Test
    void testQ3() {
        Quarter quarter = Quarter.of(3);
        assertSame(Quarter.Q3, quarter);
        assertSame(quarter, Quarter.valueOf("Q3"));
        assertEquals(3, quarter.getValue());
        assertEquals("Q3", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.valueOf("Abc");
        });

        // ==========

        int startMonthValue = quarter.firstMonthValue();
        log.info("startMonthValue: {}", startMonthValue);
        assertEquals(7, startMonthValue);

        Month startMonth = quarter.firstMonth();
        log.info("startMonth: {}", startMonth);
        assertEquals(Month.JULY, startMonth);

        assertEquals(startMonthValue, startMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(9, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.SEPTEMBER, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay startMonthDay = quarter.firstMonthDay();
        log.info("startMonthDay: {}", startMonthDay);
        assertEquals(startMonthDay, MonthDay.of(7, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(9, 30));
    }

    @Test
    void testQ4() {
        Quarter quarter = Quarter.of(4);
        assertSame(Quarter.Q4, quarter);
        assertSame(quarter, Quarter.valueOf("Q4"));
        assertEquals(4, quarter.getValue());
        assertEquals("Q4", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.valueOf("Q5");
        });

        // ==========

        int startMonthValue = quarter.firstMonthValue();
        log.info("startMonthValue: {}", startMonthValue);
        assertEquals(10, startMonthValue);

        Month startMonth = quarter.firstMonth();
        log.info("startMonth: {}", startMonth);
        assertEquals(Month.OCTOBER, startMonth);

        assertEquals(startMonthValue, startMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(12, lastMonthValue);
        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.DECEMBER, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay startMonthDay = quarter.firstMonthDay();
        log.info("startMonthDay: {}", startMonthDay);
        assertEquals(startMonthDay, MonthDay.of(10, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(12, 31));
    }
}
