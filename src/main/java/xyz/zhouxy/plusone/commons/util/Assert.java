package xyz.zhouxy.plusone.commons.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.base.Strings;

public class Assert {

    // isTrue
    public static <E extends Throwable> void isTrue(@Nullable Boolean conditions, Supplier<E> e) throws E {
        if (!Boolean.TRUE.equals(conditions)) {
            throw e.get();
        }
    }

    public static void isTrue(@Nullable Boolean conditions, String errorMessage) {
        Assert.isTrue(conditions, () -> new IllegalArgumentException(errorMessage));
    }

    public static void isTrue(@Nullable Boolean conditions, String errorMessageTemplate, Object... args) {
        Assert.isTrue(conditions, String.format(errorMessageTemplate, args));
    }

    // isFalse
    public static <E extends Throwable> void isFalse(@Nullable Boolean conditions, Supplier<E> e) throws E {
        Assert.isTrue(Boolean.FALSE.equals(conditions), e);
    }

    public static void isFalse(@Nullable Boolean conditions, String errorMessage) {
        Assert.isTrue(Boolean.FALSE.equals(conditions), () -> new IllegalArgumentException(errorMessage));
    }

    public static void isFalse(@Nullable Boolean conditions, String errorMessageTemplate, Object... args) {
        Assert.isTrue(Boolean.FALSE.equals(conditions), String.format(errorMessageTemplate, args));
    }

    // between - int
    public static <E extends Throwable> void between(int value, int min, int max, Supplier<E> e) throws E {
        Assert.isTrue((value >= min && value < max), e);
    }

    public static void between(int value, int min, int max, String errorMessage) {
        Assert.isTrue((value >= min && value < max), errorMessage);
    }

    public static void between(int value, int min, int max, String errorMessageTemplate, Object... args) {
        Assert.isTrue((value >= min && value < max), errorMessageTemplate, args);
    }

    // between - long
    public static <E extends Throwable> void between(long value, long min, long max, Supplier<E> e) throws E {
        Assert.isTrue((value >= min && value < max), e);
    }

    public static void between(long value, long min, long max, String errorMessage) {
        Assert.isTrue((value >= min && value < max), errorMessage);
    }

    public static void between(long value, long min, long max, String errorMessageTemplate, Object... args) {
        Assert.isTrue((value >= min && value < max), errorMessageTemplate, args);
    }

    // between - double
    public static <E extends Throwable> void between(double value, double min, double max, Supplier<E> e) throws E {
        Assert.isTrue((value >= min && value < max), e);
    }

    public static void between(double value, double min, double max, String errorMessage) {
        Assert.isTrue((value >= min && value < max), errorMessage);
    }

    public static void between(double value, double min, double max, String errorMessageTemplate, Object... args) {
        Assert.isTrue((value >= min && value < max), errorMessageTemplate, args);
    }

    // notNull
    public static <E extends Throwable> void notNull(Object value, Supplier<E> e) throws E {
        Assert.isTrue(Objects.nonNull(value), e);
    }

    public static <E extends Throwable> void notNull(Object value, String errorMessage) throws E {
        Assert.isTrue(Objects.nonNull(value), errorMessage);
    }

    public static void notNull(Object value, String errorMessageTemplate, Object... args) {
        Assert.isTrue(Objects.nonNull(value), errorMessageTemplate, args);
    }

    // isEmpty - Collection
    public static <T, E extends Throwable> void isEmpty(@Nullable Collection<T> collection, Supplier<E> e) throws E {
        Assert.isTrue((collection == null || collection.isEmpty()), e);
    }

    public static <T> void isEmpty(@Nullable Collection<T> collection, String errorMessage) {
        Assert.isTrue((collection == null || collection.isEmpty()), errorMessage);
    }

    public static <T> void isEmpty(@Nullable Collection<T> collection, String errorMessageTemplate, Object... args) {
        Assert.isTrue((collection == null || collection.isEmpty()), errorMessageTemplate, args);
    }

    // isNotEmpty - Collection
    public static <T, E extends Throwable> void isNotEmpty(@Nullable Collection<T> collection, Supplier<E> e) throws E {
        Assert.isFalse((collection == null || collection.isEmpty()), e);
    }

    public static <T> void isNotEmpty(@Nullable Collection<T> collection, String errorMessage) {
        Assert.isFalse((collection == null || collection.isEmpty()), errorMessage);
    }

    public static <T> void isNotEmpty(@Nullable Collection<T> collection, String errorMessageTemplate, Object... args) {
        Assert.isFalse((collection == null || collection.isEmpty()), errorMessageTemplate, args);
    }

    // isEmpty - Array
    public static <T, E extends Throwable> void isEmpty(@Nullable T[] arr, Supplier<E> e) throws E {
        Assert.isTrue((arr == null || arr.length == 0), e);
    }

    public static <T> void isEmpty(@Nullable T[] arr, String errorMessage) {
        Assert.isTrue((arr == null || arr.length == 0), errorMessage);
    }

    public static <T> void isEmpty(@Nullable T[] arr, String errorMessageTemplate, Object... args) {
        Assert.isTrue((arr == null || arr.length == 0), errorMessageTemplate, args);
    }

    // isNotEmpty - Array
    public static <T, E extends Throwable> void isNotEmpty(@Nullable T[] arr, Supplier<E> e) throws E {
        Assert.isFalse((arr == null || arr.length == 0), e);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessage) {
        Assert.isFalse((arr == null || arr.length == 0), errorMessage);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessageTemplate, Object... args) {
        Assert.isFalse((arr == null || arr.length == 0), errorMessageTemplate, args);
    }

    // isEmpty - String
    public static <E extends Throwable> void isEmpty(@Nullable String str, Supplier<E> e) throws E {
        Assert.isTrue((str == null || str.isEmpty()), e);
    }

    public static void isEmpty(@Nullable String str, String errorMessage) {
        Assert.isTrue((str == null || str.isEmpty()), errorMessage);
    }

    public static void isEmpty(@Nullable String str, String errorMessageTemplate, Object... args) {
        Assert.isTrue((str == null || str.isEmpty()), errorMessageTemplate, args);
    }

    // isNotEmpty - String
    public static <E extends Throwable> void isNotEmpty(@Nullable String str, Supplier<E> e) throws E {
        Assert.isFalse(Strings.isNullOrEmpty(str), e);
    }

    public static void isNotEmpty(@Nullable String str, String errorMessage) {
        Assert.isFalse(Strings.isNullOrEmpty(str), errorMessage);
    }

    public static void isNotEmpty(@Nullable String str, String errorMessageTemplate, Object... args) {
        Assert.isFalse(Strings.isNullOrEmpty(str), errorMessageTemplate, args);
    }

    // private constructor
    private Assert() {
        throw new IllegalStateException("Utility class");
    }
}
