package xyz.zhouxy.plusone.commons.function;

import com.google.common.annotations.Beta;

@Beta
@FunctionalInterface
public interface CharUnaryOperator {
    char applyAsChar(char operand);
}
