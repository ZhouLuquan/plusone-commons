package xyz.zhouxy.plusone.commons.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DateTimeUtilTests {

    private static final Logger log = LoggerFactory.getLogger(DateTimeUtilTests.class);

    @Test
    void testLocalNowStr() {
        log.info(DateTimeUtil.nowStr("yyyy/MM/dd HH:mm:ss.SSS"));
    }

    @Test
    void testToJoda() {
        LocalDateTime now = LocalDateTime.now();
        log.info("now: {}", now);
        org.joda.time.LocalDateTime now2 = DateTimeUtil.toJodaLocalDateTime(now);
        log.info("now2: {}", now2);
    }

    @Test
    void testToInstant() {
        Instant now = DateTimeUtil.toInstant(System.currentTimeMillis());
        String str = DateTimeUtil.toString("yy-M-d HH:mm:ss.SSS", now);
        log.info(str);
    }

    @Test
    void testToJodaDateTime() {
        DateTime jodaDateTime = DateTimeUtil.toJodaDateTime(Instant.now(), ZoneId.of("null"));
        log.info("jodaDateTime: {}", jodaDateTime);
    }

    @Test
    void test() {
        Instant now = Instant.now();
        DateTime jodaDateTime = DateTimeUtil.toJodaDateTime(now, ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        log.info(formatter.print(jodaDateTime));
        log.info(jodaDateTime.getZone().toString());
        log.info(jodaDateTime.toString());
        log.info("==========================================");
        org.joda.time.Instant instant = new org.joda.time.Instant(System.currentTimeMillis() - 500000);
        log.info(instant.toString());
        log.info(DateTimeUtil.toJavaInstant(instant).toString());
        log.info(DateTimeUtil.toZonedDateTime(instant, DateTimeZone.forID("America/New_York")).toString());
    }
}
