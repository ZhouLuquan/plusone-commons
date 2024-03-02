package xyz.zhouxy.plusone.commons.base;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.function.BoolUnaryOperator;

@Beta
public class BoolRef {

    private boolean value;

    public BoolRef(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void apply(BoolUnaryOperator operator) {
        this.value = operator.applyAsBool(this.value);
    }

    @Override
    public String toString() {
        return String.format("BoolRef[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (value ? 1231 : 1237);
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
        return value == ((BoolRef) obj).value;
    }

}
