/*
 * Copyright 2025-present ZhouXY
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


import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.google.common.base.Splitter;

import xyz.zhouxy.plusone.commons.util.StringTools;

/**
 * SemVer 语义版本号
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.1.0
 *
 * @see <a href="https://semver.org/">Semantic Versioning 2.0.0</a>
 */
public class SemVer implements Comparable<SemVer>, Serializable {
    private static final long serialVersionUID = 458265121025514002L;

    private final String value;

    private final int[] versionNumbers;
    @Nullable
    private final String preReleaseVersion;
    @Nullable
    private final String buildMetadata;

    private static final String VERSION_NUMBERS = "(?<numbers>(?<major>0|[1-9]\\d*)\\.(?<minor>0|[1-9]\\d*)\\.(?<patch>0|[1-9]\\d*)(\\.(0|[1-9]\\d*)){0,2})";
    private static final String PRE_RELEASE_VERSION = "(?:-(?<prerelease>(?:0|[1-9]\\d{0,41}|\\d{0,18}[a-zA-Z-][0-9a-zA-Z-]{0,18})(?:\\.(?:0|[1-9]\\d{0,41}|\\d{0,18}[a-zA-Z-][0-9a-zA-Z-]{0,18})){0,18}))?";
    private static final String BUILD_METADATA = "(?:\\+(?<buildmetadata>[0-9a-zA-Z-]{1,18}(?:\\.[0-9a-zA-Z-]{1,18}){0,18}))?";

    private static final Pattern PATTERN = Pattern.compile(
            "^" + VERSION_NUMBERS + PRE_RELEASE_VERSION + BUILD_METADATA + "$");

    /**
     * 创建语义化版本号的值对象
     *
     * @param value 字符串值
     * @param versionNumbers 主版本号、次版本号、修订号
     * @param preReleaseVersion 先行版本号
     * @param buildMetadata 版本编译信息
     */
    private SemVer(String value,
            int[] versionNumbers,
            @Nullable String preReleaseVersion,
            @Nullable String buildMetadata) {
        this.value = value;
        this.versionNumbers = versionNumbers;
        this.preReleaseVersion = preReleaseVersion;
        this.buildMetadata = buildMetadata;
    }

    /**
     * 创建 SemVer 对象
     *
     * @param value 语义化版本号
     * @return SemVer 对象
     */
    public static SemVer of(final String value) {
        checkArgument(StringTools.isNotBlank(value), "版本号不能为空");
        final Matcher matcher = PATTERN.matcher(value);
        checkArgument(matcher.matches(), "版本号格式错误");
        // 数字版本部分
        final String versionNumbersPart = matcher.group("numbers");
        // 先行版本号部分
        final String preReleaseVersionPart = matcher.group("prerelease");
        // 版本编译信息部分
        final String buildMetadataPart = matcher.group("buildmetadata");

        final int[] versionNumbers = Splitter.on('.')
                .splitToStream(versionNumbersPart)
                // 必须都是数字
                .mapToInt(Integer::parseInt)
                .toArray();
        return new SemVer(value, versionNumbers, preReleaseVersionPart, buildMetadataPart);
    }

    /**
     * 获取主版本号
     *
     * @return 主版本号
     */
    public int getMajor() {
        return this.versionNumbers[0];
    }

    /**
     * 获取次版本号
     *
     * @return 次版本号
     */
    public int getMinor() {
        return this.versionNumbers[1];
    }

    /**
     * 获取修订号
     *
     * @return 修订号
     */
    public int getPatch() {
        return this.versionNumbers[2];
    }

    /**
     * 获取先行版本号
     *
     * @return 先行版本号
     */
    @Nullable
    public String getPreReleaseVersion() {
        return this.preReleaseVersion;
    }

    /**
     * 获取版本编译信息
     *
     * @return 版本编译信息
     */
    @Nullable
    public String getBuildMetadata() {
        return buildMetadata;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(@SuppressWarnings("null") SemVer that) {
        if (this == that) {
            return 0;
        }
        int result = compareVersionNumbers(that);
        if (result != 0) {
            return result;
        }
        return comparePreReleaseVersion(that);
    }

    /**
     * 获取字符串值
     *
     * @return 版本字符串
     */
    public String getValue() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SemVer))
            return false;
        SemVer other = (SemVer) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * 获取 SemVer 的字符串表示。如 {@code v1.2.3-alpha.1+build.1234}
     */
    @Override
    public String toString() {
        return 'v' + value;
    }

    /**
     * 比较主版本号、次版本号、修订号
     */
    private int compareVersionNumbers(SemVer that) {
        final int minLength = Integer.min(this.versionNumbers.length, that.versionNumbers.length);

        for (int i = 0; i < minLength; i++) {
            final int currentVersionNumberOfThis = this.versionNumbers[i];
            final int currentVersionNumberOfThat = that.versionNumbers[i];
            if (currentVersionNumberOfThis != currentVersionNumberOfThat) {
                return currentVersionNumberOfThis - currentVersionNumberOfThat;
            }
        }
        return this.versionNumbers.length - that.versionNumbers.length;
    }

    /**
     * 比较先行版本号
     */
    private int comparePreReleaseVersion(SemVer that) {
        int thisWithoutPreReleaseVersionFlag = bool2Int(this.preReleaseVersion == null);
        int thatWithoutPreReleaseVersionFlag = bool2Int(that.preReleaseVersion == null);
        if (isTrue(thisWithoutPreReleaseVersionFlag | thatWithoutPreReleaseVersionFlag)) {
            return thisWithoutPreReleaseVersionFlag - thatWithoutPreReleaseVersionFlag;
        }

        Splitter splitter = Splitter.on('.');

        final String[] preReleaseVersionOfThis = splitter
                .splitToStream(this.preReleaseVersion) // NOSONAR
                .toArray(String[]::new);
        final String[] preReleaseVersionOfThat = splitter
                .splitToStream(that.preReleaseVersion) // NOSONAR
                .toArray(String[]::new);
        final int minLength = Integer.min(preReleaseVersionOfThis.length, preReleaseVersionOfThat.length);
        for (int i = 0; i < minLength; i++) {
            int r = comparePartOfPreReleaseVersion(preReleaseVersionOfThis[i], preReleaseVersionOfThat[i]);
            if (r != 0) {
                return r;
            }
        }
        return preReleaseVersionOfThis.length - preReleaseVersionOfThat.length;
    }

    /**
     * 比较先行版本号的组成部分
     */
    private static int comparePartOfPreReleaseVersion(String p1, String p2) {
        boolean p1IsNumber = isAllDigits(p1);
        boolean p2IsNumber = isAllDigits(p2);

        if (p1IsNumber) {
            return p2IsNumber
                    ? Integer.parseInt(p1) - Integer.parseInt(p2) // 都是数字
                    : -1; // p1 是数字，p2 是字符串
        }
        // 如果 p1 是字符串，p2 是数字，则返回 1（字符串优先于纯数字）
        return p2IsNumber ? 1 : p1.compareTo(p2);
    }

    /**
     * 判断字符串是否全为数字
     */
    private static boolean isAllDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static int bool2Int(boolean expression) {
        return expression ? 1 : 0;
    }

    private static boolean isTrue(int b) {
        return b != 0;
    }
}
