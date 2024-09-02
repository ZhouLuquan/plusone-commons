package xyz.zhouxy.plusone.commons.math;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.util.Numbers;

public class Interval<T extends Comparable<T>> {
    @Nonnull
    private final IntervalType intervalType;
    private final T lowerBound;
    private final T upperBound;

    public Interval(@Nonnull IntervalType intervalType, T lowerBound, T upperBound) {
        Preconditions.checkNotNull(intervalType);
        if (intervalType.isLeftClosed()) {
            Preconditions.checkArgument(lowerBound != null,
                    "The lower bound cannot be null, when the interval is left-closed.");
        }
        if (intervalType.isRightClosed()) {
            Preconditions.checkArgument(upperBound != null,
                    "The upper bound cannot be null, when the interval is right-closed.");
        }
        if (lowerBound != null && upperBound != null) {
            Preconditions.checkArgument(lowerBound.compareTo(upperBound) <= 0,
                    "The lower bound must less than the upper bound.");
        }
        this.intervalType = intervalType;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Nonnull
    public IntervalType getIntervalType() {
        return intervalType;
    }

    @Nonnull
    public Optional<T> getLowerBound() {
        return Optional.ofNullable(lowerBound);
    }

    @Nonnull
    public Optional<T> getUpperBound() {
        return Optional.ofNullable(upperBound);
    }

    public boolean isLeftClosed() {
        return this.intervalType.isLeftClosed();
    }

    public boolean isRightClosed() {
        return this.intervalType.isRightClosed();
    }

    public boolean validValue(@Nonnull T value) {
        return Numbers.between(value, this.lowerBound, this.upperBound, this.intervalType);
    }
}
