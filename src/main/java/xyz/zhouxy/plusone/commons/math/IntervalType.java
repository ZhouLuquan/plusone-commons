package xyz.zhouxy.plusone.commons.math;

public enum IntervalType {
    /** 开区间。(a,b)={x|a < x < b} */
    OPEN(false, false),
    /** 闭区间。[a,b]={x|a ≤ x ≤ b} */
    CLOSED(true, true),
    /** 左闭右开区间。[a,b)={x|a ≤ x < b} */
    CLOSED_OPEN(true, false),
    /** 左开右闭区间。(a,b]={x|a < x ≤ b} */
    OPEN_CLOSED(false, true);

    private final boolean leftClosed;
    private final boolean rightClosed;

    IntervalType(boolean leftClosed, boolean rightClosed) {
        this.leftClosed = leftClosed;
        this.rightClosed = rightClosed;
    }

    public final boolean isLeftClosed() {
        return leftClosed;
    }

    public final boolean isRightClosed() {
        return rightClosed;
    }

    public final <T extends Comparable<T>> Interval<T> buildInterval(T left, T right) {
        return new Interval<>(this, left, right);
    }
}
