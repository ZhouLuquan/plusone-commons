package xyz.zhouxy.plusone.commons.function;

import java.util.function.Predicate;

public class Predicates<T> {

    public final Predicate<T> of(Predicate<? super T> predicate) {
        return predicate::test;
    }
}
