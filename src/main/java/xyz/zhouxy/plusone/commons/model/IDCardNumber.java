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
    private static final long serialVersionUID = 20241011231527L;

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
