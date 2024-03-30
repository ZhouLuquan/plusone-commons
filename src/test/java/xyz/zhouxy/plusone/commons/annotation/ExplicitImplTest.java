package xyz.zhouxy.plusone.commons.annotation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExplicitImplTest {

    @Test
    void test() {

    }
}

interface A {

    void fooA();

    void fooAll();
}

interface B {

    void fooB();

    void fooAll();
}

class C implements A, B {

    @DefinedIn(A.class)
    @Override
    public void fooA() {
    }

    @DefinedIn(B.class)
    @Override
    public void fooB() {
    }

    @DefinedIn({ A.class, B.class })
    @Override
    public void fooAll() {
    }
}
