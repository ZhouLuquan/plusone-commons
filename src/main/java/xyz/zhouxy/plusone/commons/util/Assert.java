package xyz.zhouxy.plusone.commons.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

import javax.annotation.Nullable;

public class Assert {

    // isTrue
    public static <E extends Throwable> void isTrue(boolean conditions, Supplier<E> e) throws E {
        if (!conditions) {
            throw e.get();
        }
    }

    public static void isTrue(boolean conditions, String errorMessage) {
        Assert.isTrue(conditions, () -> new IllegalArgumentException(errorMessage));
    }

    public static void isTrue(boolean conditions, String errorMessageTemplate, Object... args) {
        Assert.isTrue(conditions, String.format(errorMessageTemplate, args));
    }

    // isFalse
    public static <E extends Throwable> void isFalse(boolean conditions, Supplier<E> e) throws E {
        Assert.isTrue(!conditions, e);
    }

    public static void isFalse(boolean conditions, String errorMessage) {
        Assert.isTrue(!conditions, () -> new IllegalArgumentException(errorMessage));
    }

    public static void isFalse(boolean conditions, String errorMessageTemplate, Object... args) {
        Assert.isTrue(!conditions, String.format(errorMessageTemplate, args));
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

    // isNotEmpty - Collection
    public static <T, E extends Throwable> void isNotEmpty(@Nullable T[] arr, Supplier<E> e) throws E {
        Assert.isFalse((arr == null || arr.length == 0), e);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessage) {
        Assert.isFalse((arr == null || arr.length == 0), errorMessage);
    }

    public static <T> void isNotEmpty(@Nullable T[] arr, String errorMessageTemplate, Object... args) {
        Assert.isFalse((arr == null || arr.length == 0), errorMessageTemplate, args);
    }

    // isEmpty - Array
    public static <E extends Throwable> void isEmpty(@Nullable String arr, Supplier<E> e) throws E {
        Assert.isTrue((arr == null || arr.length() == 0), e);
    }

    public static void isEmpty(@Nullable String arr, String errorMessage) {
        Assert.isTrue((arr == null || arr.length() == 0), errorMessage);
    }

    public static void isEmpty(@Nullable String arr, String errorMessageTemplate, Object... args) {
        Assert.isTrue((arr == null || arr.length() == 0), errorMessageTemplate, args);
    }

    // isNotEmpty - Collection
    public static <E extends Throwable> void isNotEmpty(@Nullable String arr, Supplier<E> e) throws E {
        Assert.isFalse((arr == null || arr.length() == 0), e);
    }

    public static void isNotEmpty(@Nullable String arr, String errorMessage) {
        Assert.isFalse((arr == null || arr.length() == 0), errorMessage);
    }

    public static void isNotEmpty(@Nullable String arr, String errorMessageTemplate, Object... args) {
        Assert.isFalse((arr == null || arr.length() == 0), errorMessageTemplate, args);
    }

    // private consrtuctor
    private Assert() {
        throw new IllegalStateException("Utility class");
    }
}
