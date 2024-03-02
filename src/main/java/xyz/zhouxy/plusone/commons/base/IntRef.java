package xyz.zhouxy.plusone.commons.base;

import java.util.function.IntUnaryOperator;

import com.google.common.annotations.Beta;

@Beta
public class IntRef {

    private int value;

    public IntRef(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void apply(IntUnaryOperator operator) {
        this.value = operator.applyAsInt(this.value);
    }

    @Override
    public String toString() {
        return String.format("IntRef[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return value == ((IntRef) obj).value;
    }

}
