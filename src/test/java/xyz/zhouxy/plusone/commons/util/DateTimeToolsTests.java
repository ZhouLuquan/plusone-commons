package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DateTimeToolsTests {

    private static final Logger log = LoggerFactory.getLogger(DateTimeToolsTests.class);

    @Test
    void testLocalNowStr() {
        String nowStr = DateTimeTools.nowStr("yyyy/MM/dd HH:mm:ss.SSS");
        log.info(nowStr);
        assertEquals(23, nowStr.length());
    }

    @Test
    void testToJoda() {
        LocalDateTime dt = LocalDateTime.of(2008, 8, 8, 20, 18, 59, 108000000);
        log.info("src: {}", dt);
        org.joda.time.LocalDateTime dt2 = DateTimeTools.toJodaLocalDateTime(dt);
        log.info("result: {}", dt2);
        org.joda.time.format.DateTimeFormatter f = org.joda.time.format.DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss.SSS");

        assertEquals("2008-08-08 20:18:59.108", f.print(dt2));
    }

    @Test
    void testToInstant() {
        ZonedDateTime dt = ZonedDateTime.of(2008, 1, 8, 10, 23, 50, 108000000, ZoneId.systemDefault());
        Instant instant = DateTimeTools.toInstant(dt.toInstant().toEpochMilli());
        String str = DateTimeTools.toString("yy-M-d HH:mm:ss.SSS", instant);
        log.info(str);
        assertEquals("08-1-8 10:23:50.108", str);
    }

    @Test
    void testToJodaDateTime() {
        ZonedDateTime dt = ZonedDateTime.of(2008, 1, 8, 10, 23, 50, 108000000, ZoneId.systemDefault());
        Instant instant = DateTimeTools.toInstant(dt.toInstant().toEpochMilli());

        org.joda.time.format.DateTimeFormatter f = org.joda.time.format.DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss.SSS");

        org.joda.time.DateTime jodaDateTime = DateTimeTools.toJodaDateTime(instant, ZoneId.of("+08:00"));
        log.info("jodaDateTime: {}", jodaDateTime);
        assertEquals("2008-01-08 10:23:50.108", f.print(jodaDateTime));

        jodaDateTime = DateTimeTools.toJodaDateTime(instant, ZoneId.of("+02:00"));
        log.info("jodaDateTime: {}", jodaDateTime);
        assertEquals("2008-01-08 04:23:50.108", f.print(jodaDateTime));
    }

    @Test
    void test() {
        java.time.Instant now = java.time.Instant.now();
        org.joda.time.DateTime jodaDateTime = DateTimeTools.toJodaDateTime(now, ZoneId.of("America/New_York"));
        org.joda.time.format.DateTimeFormatter formatter = org.joda.time.format.DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        log.info(formatter.print(jodaDateTime));
        log.info(jodaDateTime.getZone().toString());
        log.info(jodaDateTime.toString());
        log.info("==========================================");
        org.joda.time.Instant instant = new org.joda.time.Instant(System.currentTimeMillis() - 500000);
        log.info(instant.toString());
        log.info(DateTimeTools.toJavaInstant(instant).toString());
        log.info(DateTimeTools.toZonedDateTime(instant, org.joda.time.DateTimeZone.forID("America/New_York"))
                .toString());
    }

    @Test
    void testToJodaInstant() {
        java.time.Instant javaInstant = java.time.Instant.now();
        log.info("javaInstant: {}", javaInstant);

        org.joda.time.Instant jodaInstant = DateTimeTools.toJodaInstant(javaInstant);
        log.info("jodaInstant: {}", jodaInstant);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter formatter2 = formatter.withZone(ZoneId.systemDefault());
        log.info("{}", formatter);
        log.info("{}", formatter2);
    }
}
