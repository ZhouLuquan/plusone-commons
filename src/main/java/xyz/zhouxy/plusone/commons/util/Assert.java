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
        if (!Boolean.TRUE.equals(conditions)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void isTrue(@Nullable Boolean conditions, String errorMessageTemplate, Object... args) {
        if (!Boolean.TRUE.equals(conditions)) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, args));
        }
    }

    // isFalse
    public static <E extends Throwable> void isFalse(@Nullable Boolean conditions, Supplier<E> e) throws E {
        if (!Boolean.FALSE.equals(conditions)) {
            throw e.get();
        }
    }

    public static void isFalse(@Nullable Boolean conditions, String errorMessage) {
        if (!Boolean.FALSE.equals(conditions)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void isFalse(@Nullable Boolean conditions, String errorMessageTemplate, Object... args) {
        if (!Boolean.FALSE.equals(conditions)) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, args));
        }
    }

    // between - int
    private static boolean between(int value, int min, int max) {
        return value >= min && value < max;
    }

    public static <E extends Throwable> void between(int value, int min, int max, Supplier<E> e) throws E {
        Assert.isTrue(between(value, min, max), e);
    }

    public static void between(int value, int min, int max, String errorMessage) {
        Assert.isTrue(between(value, min, max), errorMessage);
    }

    public static void between(int value, int min, int max, String errorMessageTemplate, Object... args) {
        Assert.isTrue(between(value, min, max), errorMessageTemplate, args);
    }

    // between - long
    private static boolean between(long value, long min, long max) {
        return value >= min && value < max;
    }

    public static <E extends Throwable> void between(long value, long min, long max, Supplier<E> e) throws E {
        Assert.isTrue(between(value, min, max), e);
    }

    public static void between(long value, long min, long max, String errorMessage) {
        Assert.isTrue(between(value, min, max), errorMessage);
    }

    public static void between(long value, long min, long max, String errorMessageTemplate, Object... args) {
        Assert.isTrue(between(value, min, max), errorMessageTemplate, args);
    }

    // between - double
    private static boolean between(double value, double min, double max) {
        return value >= min && value < max;
    }

    public static <E extends Throwable> void between(double value, double min, double max, Supplier<E> e) throws E {
        Assert.isTrue(between(value, min, max), e);
    }

    public static void between(double value, double min, double max, String errorMessage) {
        Assert.isTrue(between(value, min, max), errorMessage);
    }

    public static void between(double value, double min, double max, String errorMessageTemplate, Object... args) {
        Assert.isTrue(between(value, min, max), errorMessageTemplate, args);
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
    private static <T> boolean isEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T, E extends Throwable> void isEmpty(@Nullable Collection<T> collection, Supplier<E> e) throws E {
        Assert.isTrue(isEmpty(collection), e);
    }

    public static <T> void isEmpty(@Nullable Collection<T> collection, String errorMessage) {
        Assert.isTrue(isEmpty(collection), errorMessage);
    }

    public static <T> void isEmpty(@Nullable Collection<T> collection, String errorMessageTemplate, Object... args) {
        Assert.isTrue(isEmpty(collection), errorMessageTemplate, args);
    }

    // isNotEmpty - Collection
    private static <T> boolean isNotEmpty(@Nullable Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T, E extends Throwable> void isNotEmpty(@Nullable Collection<T> collection, Supplier<E> e) throws E {
        Assert.isTrue(isNotEmpty(collection), e);
    }

    public static <T> void isNotEmpty(@Nullable Collection<T> collection, String errorMessage) {
        Assert.isTrue(isNotEmpty(collection), errorMessage);
    }

    public static <T> void isNotEmpty(@Nullable Collection<T> collection, String errorMessageTemplate, Object... args) {
        Assert.isTrue(isNotEmpty(collection), errorMessageTemplate, args);
    }

    // isEmpty - Array
    private static <T> boolean isEmpty(@Nullable T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static <T, E extends Throwable> void isEmpty(@Nullable T[] arr, Supplier<E> e) throws E {
        Assert.isTrue(isEmpty(arr), e);
    }

    public static <T> void isEmpty(@Nullable T[] arr, String errorMessage) {
        Assert.isTrue(isEmpty(arr), errorMessage);
    }

    public static <T> void isEmpty(@Nullable T[] arr, String errorMessageTemplate, Object... args) {
        Assert.isTrue(isEmpty(arr), errorMessageTemplate, args);
    }

    // isNotEmpty - Array
    private static <T> boolean isNotEmpty(@Nullable T[] arr) {
        return arr != null && arr.length > 0;
    }

    public static <T, E extends Throwable> void isNotEmpty(@Nullable T[] arr, Supplier<E> e) throws E {
        Assert.isTrue(isNotEmpty(arr), e);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessage) {
        Assert.isTrue(isNotEmpty(arr), errorMessage);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessageTemplate, Object... args) {
        Assert.isTrue(isNotEmpty(arr), errorMessageTemplate, args);
    }

    // isEmpty - String
    public static <E extends Throwable> void isEmpty(@Nullable String str, Supplier<E> e) throws E {
        Assert.isTrue(Strings.isNullOrEmpty(str), e);
    }

    public static void isEmpty(@Nullable String str, String errorMessage) {
        Assert.isTrue(Strings.isNullOrEmpty(str), errorMessage);
    }

    public static void isEmpty(@Nullable String str, String errorMessageTemplate, Object... args) {
        Assert.isTrue(Strings.isNullOrEmpty(str), errorMessageTemplate, args);
    }

    // isNotEmpty - String
    public static <E extends Throwable> void isNotEmpty(@Nullable String str, Supplier<E> e) throws E {
        Assert.isTrue(!Strings.isNullOrEmpty(str), e);
    }

    public static void isNotEmpty(@Nullable String str, String errorMessage) {
        Assert.isTrue(!Strings.isNullOrEmpty(str), errorMessage);
    }

    public static void isNotEmpty(@Nullable String str, String errorMessageTemplate, Object... args) {
        Assert.isTrue(!Strings.isNullOrEmpty(str), errorMessageTemplate, args);
    }

    // private constructor
    private Assert() {
        throw new IllegalStateException("Utility class");
    }
}
