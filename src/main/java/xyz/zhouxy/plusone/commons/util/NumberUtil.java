/*
 * Copyright 2022-2023 the original author or authors.
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

package xyz.zhouxy.plusone.commons.util;

/**
 * NumberUtil
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class NumberUtil {

    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int sum(short... numbers) {
        int result = 0;
        for (short number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(int... numbers) {
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(long... numbers) {
        long result = 0;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }

    public static double sum(float... numbers) {
        double result = 0;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }

    public static double sum(double... numbers) {
        double result = 0;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }
}
