package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.google.common.base.Stopwatch;
import com.google.common.io.Resources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipToolsTests {

    static byte[] bytes;

    @BeforeAll
    static void setup() throws IOException {
        URL resource = Resources.getResource("xyz/zhouxy/plusone/commons/util/LoremIpsum.txt");
        bytes = Resources.toByteArray(resource);
    }

    @Test
    void zip_WithDefaultLevel() throws IOException, DataFormatException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        byte[] zip = ZipTools.zip(bytes);
        stopwatch.stop();
        log.info("default level, size: {} ({})", zip.length, stopwatch);
        assertArrayEquals(bytes, ZipTools.unzip(zip));

        assertNull(ZipTools.zip(null));
        assertNull(ZipTools.unzip(null));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })
    void zip_WithLevel(int level) throws IOException, DataFormatException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        byte[] zip = ZipTools.zip(bytes, level);
        stopwatch.stop();
        log.info("level: {}, size: {} ({})", level, zip.length, stopwatch);
        assertArrayEquals(bytes, ZipTools.unzip(zip));

        assertNull(ZipTools.zip(null, level));
        assertNull(ZipTools.unzip(null));
    }

    @Test
    void zip_WithWrongLevel() throws IOException, DataFormatException {
        Random random = new Random();
        final int levelGtMax = random.nextInt() + 9;
        assertThrows(IllegalArgumentException.class, () -> ZipTools.zip(bytes, levelGtMax));

        final int levelLtMin = -1 - random.nextInt();
        assertThrows(IllegalArgumentException.class, () -> ZipTools.zip(bytes, levelLtMin));
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = ZipTools.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }
}
