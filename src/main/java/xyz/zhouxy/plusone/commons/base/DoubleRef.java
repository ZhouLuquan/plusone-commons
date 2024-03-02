package xyz.zhouxy.plusone.commons.base;

import java.util.function.DoubleUnaryOperator;

import com.google.common.annotations.Beta;

@Beta
public class DoubleRef {

    private double value;

    public DoubleRef(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void apply(DoubleUnaryOperator operator) {
        this.value = operator.applyAsDouble(this.value);
    }

    @Override
    public String toString() {
        return String.format("DoubleRef[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        final DoubleRef other = (DoubleRef) obj;
        return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
    }

}
