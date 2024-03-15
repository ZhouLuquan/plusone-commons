package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.base.BoolRef;
import xyz.zhouxy.plusone.commons.base.CharRef;
import xyz.zhouxy.plusone.commons.base.DoubleRef;
import xyz.zhouxy.plusone.commons.base.IntRef;
import xyz.zhouxy.plusone.commons.base.LongRef;
import xyz.zhouxy.plusone.commons.base.Ref;

@Slf4j
class RefTests {

    @Test
    void testRef() {
        Ref<String> strRef = new Ref<>("ZhouXY");
        apply(strRef);
        assertEquals("Hello ZhouXY", strRef.getValue());
        log.info("strRef: {}", strRef);
    }

    void apply(Ref<String> strRef) {
        strRef.transform(str -> "Hello " + str);
    }

    @Test
    void testBoolRef() {
        BoolRef boolRef = new BoolRef(false);
        apply(boolRef);
        assertTrue(boolRef.getValue());
        log.info("boolRef: {}", boolRef);
    }

    void apply(BoolRef boolRef) {
        boolRef.setValue(true);
    }

    @Test
    void testCharRef() {
        CharRef charRef = new CharRef('T');

        apply(false, charRef);
        assertEquals('0', charRef.getValue());
        log.info("charRef: {}", charRef);

        apply(true, charRef);
        assertEquals('1', charRef.getValue());
        log.info("charRef: {}", charRef);
    }

    void apply(boolean condition, CharRef charRef) {
        charRef.apply(c -> condition ? '1' : '0');
    }

    @Test
    void testDoubleRef() {
        DoubleRef doubleRef = new DoubleRef(2.33);
        apply(88.108, doubleRef);
        assertEquals(2.33 * 88.108, doubleRef.getValue());
        log.info("doubleRef: {}", doubleRef);
    }

    void apply(double num, DoubleRef doubleRef) {
        doubleRef.apply(d -> d * num);
    }

    @Test
    void testIntRef() {
        IntRef intRef = new IntRef(108);
        apply(88, intRef);
        assertEquals(108 - 88, intRef.getValue());
        log.info("intRef: {}", intRef);
    }

    void apply(int num, IntRef intRef) {
        intRef.apply(d -> d - num);
    }

    @Test
    void testLongRef() {
        LongRef longRef = new LongRef(108L);
        apply(88L, longRef);
        assertEquals(108L + 88L, longRef.getValue());
        log.info("intRef: {}", longRef);
    }

    void apply(long num, LongRef longRef) {
        longRef.apply(d -> d + num);
    }
}
