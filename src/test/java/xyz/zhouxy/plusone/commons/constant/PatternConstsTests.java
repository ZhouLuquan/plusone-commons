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

package xyz.zhouxy.plusone.commons.constant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public //
class PatternConstsTests {

    // ================================
    // #region - BASIC_ISO_DATE
    // ================================

    @Test
    void testBasicIsoDate_ValidDate() {
        Matcher matcher = PatternConsts.BASIC_ISO_DATE.matcher("20241229");
        assertTrue(matcher.matches());

        assertEquals("2024", matcher.group(1));
        assertEquals("12", matcher.group(2));
        assertEquals("29", matcher.group(3));
        assertEquals("2024", matcher.group("yyyy"));
        assertEquals("12", matcher.group("MM"));
        assertEquals("29", matcher.group("dd"));

        // LeapYearFeb29()
        assertTrue(PatternConsts.BASIC_ISO_DATE.matcher("20200229").matches());

        // BoundaryMin()
        assertTrue(PatternConsts.BASIC_ISO_DATE.matcher("00000101").matches());

        // BoundaryMax()
        assertTrue(PatternConsts.BASIC_ISO_DATE.matcher("9999999991231").matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "20231301", // InvalidMonth
        "20230230", // InvalidDay
        "20210229", // NonLeapYearFeb29
    })
    void testBasicIsoDate_InvalidDate_butMatches(String date) {
        // 虽然日期有误，但这个正则无法判断。实际工作中，应使用日期时间 API。
        Matcher matcher = PatternConsts.BASIC_ISO_DATE.matcher(date);
        assertTrue(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2023041", // TooShort
        "99999999990415", // TooLong
        "2023-04-15", // NonNumeric
    })
    void testBasicIsoDate_InvalidDate_Mismatches(String date) {
        Matcher matcher = PatternConsts.BASIC_ISO_DATE.matcher(date);
        assertFalse(matcher.matches());
    }

    // ================================
    // #endregion - BASIC_ISO_DATE
    // ================================

    // ================================
    // #region - ISO_LOCAL_DATE
    // ================================

    @Test
    void testIsoLocalDate_ValidDate() {
        Matcher matcher = PatternConsts.ISO_LOCAL_DATE.matcher("2024-12-29");
        assertTrue(matcher.matches());
        assertEquals("2024", matcher.group("yyyy"));
        assertEquals("12", matcher.group("MM"));
        assertEquals("29", matcher.group("dd"));

        // LeapYearFeb29()
        assertTrue(PatternConsts.ISO_LOCAL_DATE.matcher("2020-02-29").matches());

        // BoundaryMin()
        assertTrue(PatternConsts.ISO_LOCAL_DATE.matcher("0000-01-01").matches());

        // BoundaryMax()
        assertTrue(PatternConsts.ISO_LOCAL_DATE.matcher("999999999-12-31").matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2023-13-01", // InvalidMonth
        "2023-02-30", // InvalidDay
        "2021-02-29", // NonLeapYearFeb29
    })
    void testIsoLocalDate_InvalidDate_butMatches(String date) {
        // 虽然日期有误，但这个正则无法判断。实际工作中，应使用日期时间 API。
        Matcher matcher = PatternConsts.ISO_LOCAL_DATE.matcher(date);
        assertTrue(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "2023-04-1", // TooShort
        "9999999999-04-15", // TooLong
        "20230415",
    })
    void testIsoLocalDate_InvalidDate_Mismatches(String date) {
        Matcher matcher = PatternConsts.ISO_LOCAL_DATE.matcher(date);
        assertFalse(matcher.matches());
    }

    // ================================
    // #endregion - ISO_LOCAL_DATE
    // ================================

    // ================================
    // #region - PASSWORD
    // ================================

    @Test
    void testPassword_ValidPassword_Matches() {
        assertTrue(PatternConsts.PASSWORD.matcher("Abc123!@#").matches());
    }

    @Test
    void testPassword_InvalidPassword_Mismatches() {
        assertFalse(PatternConsts.PASSWORD.matcher("Abc123 !@#").matches()); // 带空格
        assertFalse(PatternConsts.PASSWORD.matcher("Abc123!@# ").matches()); // 带空格
        assertFalse(PatternConsts.PASSWORD.matcher(" Abc123!@#").matches()); // 带空格
        assertFalse(PatternConsts.PASSWORD.matcher(" Abc123!@# ").matches()); // 带空格
        assertFalse(PatternConsts.PASSWORD.matcher("77553366998844113322").matches()); // 纯数字
        assertFalse(PatternConsts.PASSWORD.matcher("poiujhgbfdsazxcfvghj").matches()); // 纯小写字母
        assertFalse(PatternConsts.PASSWORD.matcher("POIUJHGBFDSAZXCFVGHJ").matches()); // 纯大写字母
        assertFalse(PatternConsts.PASSWORD.matcher("!#$%&'*\\+-/=?^`{|}~@()[]\",.;':").matches()); // 纯特殊字符
        assertFalse(PatternConsts.PASSWORD.matcher("sdfrghbv525842582752").matches()); // 没有小写字母
        assertFalse(PatternConsts.PASSWORD.matcher("SDFRGHBV525842582752").matches()); // 没有小写字母
        assertFalse(PatternConsts.PASSWORD.matcher("sdfrghbvSDFRGHBV").matches()); // 没有数字
        assertFalse(PatternConsts.PASSWORD.matcher("Abc1!").matches()); // 太短
        assertFalse(PatternConsts.PASSWORD.matcher("Abc1!Abc1!Abc1!Abc1!Abc1!Abc1!Abc1!").matches()); // 太长
        assertFalse(PatternConsts.PASSWORD.matcher("").matches());
        assertFalse(PatternConsts.PASSWORD.matcher(" ").matches());
    }

    // ================================
    // #endregion - PASSWORD
    // ================================

    // ================================
    // #region - EMAIL
    // ================================

    @Test
    public void testValidEmails() {
        assertTrue(PatternConsts.EMAIL.matcher("test@example.com").matches());
        assertTrue(PatternConsts.EMAIL.matcher("user.name+tag+sorting@example.com").matches());
        assertTrue(PatternConsts.EMAIL.matcher("user@sub.example.com").matches());
        assertTrue(PatternConsts.EMAIL.matcher("user@123.123.123.123").matches());
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(PatternConsts.EMAIL.matcher(".username@example.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("@missingusername.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("plainaddress").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username..username@example.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username.@example.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@-example.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@-example.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@.com.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@.com.my").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@.com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@com.").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@example..com").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@example.com-").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@example.com.").matches());
        assertFalse(PatternConsts.EMAIL.matcher("username@example").matches());
    }

    // ================================
    // #endregion - EMAIL
    // ================================

    // ================================
    // #region - Chinese2ndIdCardNumber
    // ================================

    @ParameterizedTest
    @ValueSource(strings = {
        "44520019900101456X",
        "44520019900101456x",
        "445200199001014566",
    })
    void testChinese2ndIdCardNumber_ValidChinese2ndIdCardNumber(String value) {
        Matcher matcher = PatternConsts.CHINESE_2ND_ID_CARD_NUMBER.matcher(value);
        assertTrue(matcher.matches());
        assertEquals("44", matcher.group("province"));
        assertEquals("4452", matcher.group("city"));
        assertEquals("445200", matcher.group("county"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "4452200199001014566",
        "44520199001014566",
        " ",
        "",
    })
    void testChinese2ndIdCardNumber_InvalidChinese2ndIdCardNumber(String value) {
        assertFalse(PatternConsts.CHINESE_2ND_ID_CARD_NUMBER.matcher(value).matches());
    }

    // ================================
    // #endregion - Chinese2ndIdCardNumber
    // ================================
}
