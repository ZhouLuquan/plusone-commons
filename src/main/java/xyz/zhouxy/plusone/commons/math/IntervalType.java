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
