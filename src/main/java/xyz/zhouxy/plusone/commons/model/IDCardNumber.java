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
import java.time.Period;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

/**
 * 身份证号
 */
public abstract class IDCardNumber extends ValidatableStringRecord {

    protected IDCardNumber(@Nonnull String idNumber, @Nonnull Pattern pattern)
            throws IllegalArgumentException{
        super(idNumber, pattern);
    }

    protected IDCardNumber(@Nonnull String idNumber, @Nonnull Pattern pattern,
            @Nonnull String errorMessage) {
        super(idNumber, pattern, errorMessage);
    }

    protected IDCardNumber(@Nonnull String idNumber, @Nonnull Pattern pattern,
            @Nonnull Supplier<String> errorMessage) {
        super(idNumber, pattern, errorMessage);
    }

    /**
     * 根据身份证号判断性别
     */
    public abstract Gender getGender();

    /**
     * 获取出生日期
     */
    public abstract LocalDate getBirthDate();

    /** 计算年龄 */
    public final int calculateAge() {
        LocalDate now = LocalDate.now();
        return Period.between(getBirthDate(), now).getYears();
    }
}
