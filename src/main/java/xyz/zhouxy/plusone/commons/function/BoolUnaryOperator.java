package xyz.zhouxy.plusone.commons.function;

import com.google.common.annotations.Beta;

@Beta
@FunctionalInterface
public interface BoolUnaryOperator {
    boolean applyAsBool(boolean operand);

    static BoolUnaryOperator not() {
        return b -> !b;
    }
}
