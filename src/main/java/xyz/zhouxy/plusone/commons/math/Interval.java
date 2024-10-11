/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
