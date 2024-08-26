package xyz.zhouxy.plusone.commons.base;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class YearQuarterTests {

    @Test
    void of_ValidYearAndQuarter_CreatesYearQuarter() {
        int year = 2023;
        Quarter quarter = Quarter.Q1;

        YearQuarter expected = YearQuarter.of(year, quarter);
        YearQuarter actual = YearQuarter.of(LocalDate.of(year, 2, 28));

        assertEquals(expected, actual);

        assertEquals("Q1 2023", actual.toString());
    }

    @Test
    @SuppressWarnings("null")
    void of_InvalidQuarter_ThrowsNullPointerException() {
        int year = 2023;
        Quarter quarter = null;

        assertThrows(NullPointerException.class, () -> YearQuarter.of(year, quarter));
    }

    @Test
    void of_ValidYearQuarter_GetsCorrectStartAndEndDate() {

        for (int year = 1990; year <= 2024; year++) {
            for (int qrtr = 1; qrtr <= 4; qrtr++) {
                Quarter quarter = Quarter.of(qrtr);
                YearQuarter yearQuarter = YearQuarter.of(year, quarter);

                LocalDate expectedStartDate = quarter.firstMonthDay().atYear(year);
                log.info("{} - expectedStartDate: {}", yearQuarter, expectedStartDate);
                LocalDate expectedEndDate = quarter.lastMonthDay().atYear(year);
                log.info("{} - expectedEndDate: {}", yearQuarter, expectedEndDate);
                
                assertEquals(expectedStartDate, yearQuarter.firstDate());
                assertEquals(expectedEndDate, yearQuarter.lastDate());
            }
        }
    }
}
