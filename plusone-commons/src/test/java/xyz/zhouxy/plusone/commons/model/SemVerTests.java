/*
 * Copyright 2025 the original author or authors.
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

package xyz.zhouxy.plusone.commons.model;

import static org.apache.commons.lang3.ObjectUtils.compare;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemVerTests {

    @ParameterizedTest
    @ValueSource(strings = {
        "25.10.17",
        "25.10.17.11",
        "25.10.17.11.38",
        "25.10.17-RC1",
        "25.10.17.11-RC1",
        "25.10.17.11.38-RC1",
        "25.10.17+build.a20251017.1",
        "25.10.17.11+build.a20251017.1",
        "25.10.17.11.38+build.a20251017.1",
        "25.10.17-RC1+build.a20251017.1",
        "25.10.17.11-RC1+build.a20251017.1",
        "25.10.17.11.38-RC1+build.a20251017.1",
    })
    void test_of_success(String value) {
        assertDoesNotThrow(() -> SemVer.of(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "25",
        "25.10",
        "x.10.17",
        "25.x.17",
        "25.10.x",
        "25.10.17.11.38.20",
        "025.10.17",
        "25.010.17",
        "25.10.017",
    })
    void test_of_wrongValue(String value) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> SemVer.of(value));
        assertEquals("版本号格式错误", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "25.1.1",
        "25.1.1-RC1",
        "25.1.1+build.a20250101.1",
        "25.1.1-RC1+build.a20250101.1",
    })
    void compareTo_Major(String version_25_x_x) { // NOSONAR sonarqube(java:S117)
        assertTrue(compare(SemVer.of(version_25_x_x), SemVer.of("24.12.30")) > 0);
        assertTrue(compare(SemVer.of(version_25_x_x), SemVer.of("24.12.30.1")) > 0);
        assertTrue(compare(SemVer.of(version_25_x_x), SemVer.of("24.12.30-RC2")) > 0);
        assertTrue(compare(SemVer.of(version_25_x_x), SemVer.of("24.12.30+build.z20241230.1")) > 0);
        assertTrue(compare(SemVer.of(version_25_x_x), SemVer.of("24.12.30-RC2+build.z20241230.1")) > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "25.10.1",
        "25.10.1-RC1",
        "25.10.1+build.a20251001.1",
        "25.10.1-RC1+build.a20251001.1",
    })
    void compareTo_Minor(String version_25_10_x) { // NOSONAR sonarqube(java:S117)
        assertTrue(compare(SemVer.of(version_25_10_x), SemVer.of("25.9.30")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_x), SemVer.of("25.9.30.1")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_x), SemVer.of("25.9.30-RC2")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_x), SemVer.of("25.9.30+build.z20250930.1")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_x), SemVer.of("25.9.30-RC2+build.z20250930.1")) > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "25.10.17",
        "25.10.17-RC1",
        "25.10.17+build.a20251017.1",
        "25.10.17-RC1+build.a20251017.1",
    })
    void compareTo_Patch(String version_25_10_17) { // NOSONAR sonarqube(java:S117)

        assertTrue(compare(SemVer.of(version_25_10_17), SemVer.of("25.10.16")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_17), SemVer.of("25.10.16.1")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_17), SemVer.of("25.10.16-RC2")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_17), SemVer.of("25.10.16+build.z20251016.2")) > 0);
        assertTrue(compare(SemVer.of(version_25_10_17), SemVer.of("25.10.16-RC2+build.z20251016.2")) > 0);
    }

    @Test
    void compareTo_MoreVersionNumber() {

        assertTrue(compare(SemVer.of("25.10.17.1"), SemVer.of("25.10.17")) > 0);
        assertTrue(compare(SemVer.of("25.10.17.11"), SemVer.of("25.10.17.1")) > 0);
        assertEquals(0, compare(SemVer.of("25.10.17.11"), SemVer.of("25.10.17.11")));

        assertTrue(compare(SemVer.of("25.10.17.11.1"), SemVer.of("25.10.17.11")) > 0);
        assertTrue(compare(SemVer.of("25.10.17.11.38"), SemVer.of("25.10.17.11.1")) > 0);
        assertEquals(0, compare(SemVer.of("25.10.17.11.38"), SemVer.of("25.10.17.11.38")));
    }

    @Test
    void compareTo_PreReleaseVersion() {

        // 先行版的优先级低于相关联的标准版本
        assertTrue(compare(SemVer.of("25.10.17"), SemVer.of("25.10.17-0")) > 0);
        assertTrue(compare(SemVer.of("25.10.17"), SemVer.of("25.10.17-RC1")) > 0);

        // 只有数字的标识符以数值高低比较
        assertAll(
                () -> assertTrue(compare("25.10.17-RC.11", "25.10.17-RC.2") < 0),
                () -> assertTrue(compare(SemVer.of("25.10.17-RC.11"), SemVer.of("25.10.17-RC.2")) > 0)
        );

        // 纯数字优先级低于非数字
        assertAll(
                () -> assertTrue(compare("25.10.17-999", "25.10.17-A") < 0),
                () -> assertTrue(compare(SemVer.of("25.10.17-A.A"), SemVer.of("25.10.17-A.99")) > 0)
        );

        SemVer[] versions = {
                SemVer.of("25.10.17-a"),
                SemVer.of("25.10.17-aa"),
                SemVer.of("25.10.17-A"),
                SemVer.of("25.10.17-AA"),

                SemVer.of("25.10.17--"),

                SemVer.of("25.10.17-999"),

                SemVer.of("25.10.17-z"),
                SemVer.of("25.10.17-zz"),
                SemVer.of("25.10.17-Z"),
                SemVer.of("25.10.17-ZZ"),
        };

        assertArrayEquals(
                new SemVer[] {
                        // 纯数字优先级低于非数字
                        SemVer.of("25.10.17-999"),

                        // 有字母或连接号时逐字符以 ASCII 的排序比较
                        SemVer.of("25.10.17--"),

                        SemVer.of("25.10.17-A"),
                        SemVer.of("25.10.17-AA"),
                        SemVer.of("25.10.17-Z"),
                        SemVer.of("25.10.17-ZZ"),
                        SemVer.of("25.10.17-a"),
                        SemVer.of("25.10.17-aa"),
                        SemVer.of("25.10.17-z"),
                        SemVer.of("25.10.17-zz"),
                },
                Arrays.stream(versions)
                        .sorted()
                        .toArray(SemVer[]::new));

        // 若开头的标识符都相同时，栏位比较多的先行版本号优先级比较高
        assertAll(
                () -> assertTrue(compare(SemVer.of("25.10.17-ABC.DEF.1"), SemVer.of("25.10.17-ABC.DEF")) > 0),
                () -> assertTrue(compare(SemVer.of("25.10.17-ABC.DEF.G"), SemVer.of("25.10.17-ABC.DEF")) > 0));

    }

    @Test
    void compareTo_ignoreBuildMeta() {

        assertNotEquals(SemVer.of("25.10.17"), SemVer.of("25.10.17+build.20251017.01"));
        assertTrue(compare(SemVer.of("25.10.17"), SemVer.of("25.10.17+build.20251017.01")) == 0); // NOSONAR sonarqube(java:S5785)

        assertNotEquals(SemVer.of("25.10.17+build.20251017.02"), SemVer.of("25.10.17+build.20251017.01"));
        assertTrue(compare(SemVer.of("25.10.17+build.20251017.02"), SemVer.of("25.10.17+build.20251017.01")) == 0); // NOSONAR sonarqube(java:S5785)

        assertNotEquals(SemVer.of("25.10.17-ABC.DEF"), SemVer.of("25.10.17-ABC.DEF+build.20251017.01"));
        assertTrue(compare(SemVer.of("25.10.17-ABC.DEF"), SemVer.of("25.10.17-ABC.DEF+build.20251017.01")) == 0); // NOSONAR sonarqube(java:S5785)

        assertNotEquals(SemVer.of("25.10.17-ABC.DEF+build.20251017.02"), SemVer.of("25.10.17-ABC.DEF+build.20251017.01"));
        assertTrue(compare(SemVer.of("25.10.17-ABC.DEF+build.20251017.02"), SemVer.of("25.10.17-ABC.DEF+build.20251017.01")) == 0); // NOSONAR sonarqube(java:S5785)
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "25.10.17",
        "25.10.17-ABC.DEF.1",
        "25.10.17+build.20251017.a2",
        "25.10.17-ABC.DEF.1+build.20251017.a2",
    })
    void test_equals(String value) {
        final SemVer v1 = SemVer.of(value);
        final SemVer v2 = SemVer.of(value);

        assertTrue(v1.compareTo(v1) == 0); // NOSONAR sonarqube(java:S5785)
        assertTrue(v1.compareTo(v2) == 0); // NOSONAR sonarqube(java:S5785)

        assertEquals(v1, v1);
        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v1, v2);
    }

    @Test
    void test_equals_null() {
        assertFalse(SemVer.of("25.10.17").equals(null)); // NOSONAR sonarqube(java:S5785)
    }

    @Test
    void details() {
        final String s = "25.10.17-ABC.DEF.1+build.20251017.02";
        final SemVer v = SemVer.of(s);
        assertEquals(25, v.getMajor());
        assertEquals(10, v.getMinor());
        assertEquals(17, v.getPatch());

        assertEquals("ABC.DEF.1", v.getPreReleaseVersion());
        assertEquals(s, v.getValue());
        assertEquals("v" + s, v.toString());

        assertEquals("build.20251017.02", v.getBuildMetadata());
    }
}
