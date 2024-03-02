package xyz.zhouxy.plusone.commons.base;

import java.util.function.LongUnaryOperator;

import com.google.common.annotations.Beta;

@Beta
public class LongRef {

    private long value;

    public LongRef(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void apply(LongUnaryOperator operator) {
        this.value = operator.applyAsLong(this.value);
    }

    @Override
    public String toString() {
        return String.format("LongRef[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (value ^ (value >>> 32));
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
        return value == ((LongRef) obj).value;
    }

}
