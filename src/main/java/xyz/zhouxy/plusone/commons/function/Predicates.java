package xyz.zhouxy.plusone.commons.function;

import java.util.function.Predicate;

public class Predicates {

    public static <T> Predicate<T> of(Predicate<? super T> predicate) {
        return predicate::test;
    }

    private Predicates() {
        throw new IllegalStateException("Utility class");
    }
}
