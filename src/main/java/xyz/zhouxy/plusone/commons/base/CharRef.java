package xyz.zhouxy.plusone.commons.base;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.function.CharUnaryOperator;

@Beta
public class CharRef {

    private char value;

    public CharRef(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void apply(CharUnaryOperator operator) {
        this.value = operator.applyAsChar(this.value);
    }

    @Override
    public String toString() {
        return String.format("CharRef[%s]", value);
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
        return value == ((CharRef) obj).value;
    }

}
