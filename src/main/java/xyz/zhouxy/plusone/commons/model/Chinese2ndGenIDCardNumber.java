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

package xyz.zhouxy.plusone.commons.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Matcher;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;

import xyz.zhouxy.plusone.commons.annotation.ReaderMethod;
import xyz.zhouxy.plusone.commons.annotation.ValueObject;
import xyz.zhouxy.plusone.commons.constant.PatternConsts;
import xyz.zhouxy.plusone.commons.util.AssertTools;
import xyz.zhouxy.plusone.commons.util.StringTools;

/**
 * Chinese2ndGenIDCardNumber
 *
 * <p>
 * 中国第二代居民身份证号
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0
 * @see xyz.zhouxy.plusone.commons.constant.PatternConsts#CHINESE_2ND_ID_CARD_NUMBER
 */
@ValueObject
@Immutable
public class Chinese2ndGenIDCardNumber
        extends ValidatableStringRecord<Chinese2ndGenIDCardNumber>
        implements IDCardNumber {

    /** 省份编码 */
    private final String provinceCode;
    /** 市级编码 */
    private final String cityCode;
    /** 县级编码 */
    private final String countyCode;
    /** 性别 */
    private final Gender gender;
    /** 出生日期 */
    private final LocalDate birthDate;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private Chinese2ndGenIDCardNumber(String value) {
        super(value.toUpperCase(),  PatternConsts.CHINESE_2ND_ID_CARD_NUMBER, () -> "二代居民身份证校验失败：" + value);

        final Matcher matcher = getMatcher();

        final String provinceCodeValue = matcher.group("province");
        AssertTools.checkArgument(Chinese2ndGenIDCardNumber.PROVINCE_CODES.containsKey(provinceCodeValue));

        final String cityCodeValue = matcher.group("city");
        final String countyCodeValue = matcher.group("county");

        final Gender genderValue;
        final LocalDate birthDateValue;

        try {
            // 出生日期
            final String birthDateStr = matcher.group("birthDate");
            birthDateValue = LocalDate.parse(birthDateStr, DATE_FORMATTER);

            // 性别
            final int genderCode = Integer.parseInt(matcher.group("gender"));
            genderValue = genderCode % 2 == 0 ? Gender.FEMALE : Gender.MALE;
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        this.provinceCode = provinceCodeValue;
        this.cityCode = cityCodeValue;
        this.countyCode = countyCodeValue;
        this.gender = genderValue;
        this.birthDate = birthDateValue;
    }

    public static Chinese2ndGenIDCardNumber of(final String value) {
        AssertTools.checkArgument(StringTools.isNotBlank(value), "二代居民身份证校验失败：号码为空");
        return new Chinese2ndGenIDCardNumber(value);
    }

    // ================================
    // #region - reader methods
    // ================================

    @ReaderMethod
    public String getProvinceCode() {
        return provinceCode;
    }

    @ReaderMethod
    public String getProvinceName() {
        return PROVINCE_CODES.get(this.provinceCode);
    }

    @ReaderMethod
    public String getFullProvinceCode() {
        return Strings.padEnd(this.provinceCode, 12, '0');
    }

    @ReaderMethod
    public String getCityCode() {
        return cityCode;
    }

    @ReaderMethod
    public String getFullCityCode() {
        return Strings.padEnd(this.cityCode, 12, '0');
    }

    @ReaderMethod
    public String getCountyCode() {
        return countyCode;
    }

    @ReaderMethod
    public String getFullCountyCode() {
        return Strings.padEnd(this.countyCode, 12, '0');
    }

    @ReaderMethod
    @Override
    public Gender getGender() {
        return gender;
    }

    @ReaderMethod
    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // ================================
    // #endregion - reader methods
    // ================================

    /**
     * 省份代码表
     */
    public static final Map<String, String> PROVINCE_CODES;

    static {
        PROVINCE_CODES = ImmutableMap.<String, String>builder()
                .put("11", "北京")
                .put("12", "天津")
                .put("13", "河北")
                .put("14", "山西")
                .put("15", "内蒙古")
                .put("21", "辽宁")
                .put("22", "吉林")
                .put("23", "黑龙江")
                .put("31", "上海")
                .put("32", "江苏")
                .put("33", "浙江")
                .put("34", "安徽")
                .put("35", "福建")
                .put("36", "江西")
                .put("37", "山东")
                .put("41", "河南")
                .put("42", "湖北")
                .put("43", "湖南")
                .put("44", "广东")
                .put("45", "广西")
                .put("46", "海南")
                .put("50", "重庆")
                .put("51", "四川")
                .put("52", "贵州")
                .put("53", "云南")
                .put("54", "西藏")
                .put("61", "陕西")
                .put("62", "甘肃")
                .put("63", "青海")
                .put("64", "宁夏")
                .put("65", "新疆")
                .put("71", "台湾")
                .put("81", "香港")
                .put("82", "澳门")
                .put("83", "台湾") // 台湾身份证号码以83开头，但是行政区划为71
                .put("91", "国外")
                .build();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
