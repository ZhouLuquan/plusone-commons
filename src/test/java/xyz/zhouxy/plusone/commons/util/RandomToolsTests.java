package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("null")
public class RandomToolsTests {

    private static Random random;
    private static SecureRandom secureRandom;
    private static char[] sourceCharactersArray;
    private static String sourceCharactersString;

    @BeforeAll
    public static void setUp() {
        random = new Random();
        secureRandom = new SecureRandom();
        sourceCharactersArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        sourceCharactersString = "abcdefghijklmnopqrstuvwxyz";
    }

    @Test
    public void randomStr_NullRandom_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersArray, 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersString, 5));
    }

    @Test
    public void randomStr_NullSourceCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, (char[]) null, 5));
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, (String) null, 5));
    }

    @Test
    public void randomStr_NegativeLength_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, sourceCharactersArray, -1));
    }

    @Test
    public void randomStr_ZeroLength_ReturnsEmptyString() {
        assertAll(
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersString, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersString, 0)));
    }

    @Test
    public void randomStr_PositiveLength_ReturnsRandomString() {
        assertAll(
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersString, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersString, 5).length()));
    }

    @Test
    public void randomStr_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void randomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void secureRandomStr_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void secureRandomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }
}
