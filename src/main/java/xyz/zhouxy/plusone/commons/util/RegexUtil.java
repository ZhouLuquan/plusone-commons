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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final Map<String, @Nonnull Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();

    public static Pattern getPattern(final String regex) {
        Objects.requireNonNull(regex);
        if (PATTERN_CACHE.containsKey(regex)) {
            return PATTERN_CACHE.get(regex);
        }
        Pattern pattern = Pattern.compile(regex);
        if (pattern == null) {
            throw new IllegalArgumentException("Regex must not be null.");
        }
        PATTERN_CACHE.put(regex, pattern);
        return pattern;
    }

    public static boolean matches(@Nullable CharSequence input, String regex) {
        return matches(input, getPattern(regex));
    }

    public static boolean matches(@Nullable CharSequence input, Pattern pattern) {
        Assert.notNull(pattern, "Pattern must not be null.");
        return pattern.matcher(input).matches();
    }

    public static boolean matchesOr(@Nullable CharSequence input, String... regexes) {
        boolean isMatched;
        for (String regex : regexes) {
            isMatched = matches(input, regex);
            if (isMatched) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchesOr(@Nullable CharSequence input, Pattern... patterns) {
        boolean isMatched;
        for (Pattern pattern : patterns) {
            isMatched = matches(input, pattern);
            if (isMatched) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchesAnd(@Nullable CharSequence input, String... regexes) {
        boolean isMatched;
        for (String regex : regexes) {
            isMatched = matches(input, regex);
            if (!isMatched) {
                return false;
            }
        }
        return true;
    }

    public static boolean matchesAnd(@Nullable CharSequence input, Pattern... patterns) {
        boolean isMatched;
        for (Pattern pattern : patterns) {
            isMatched = matches(input, pattern);
            if (!isMatched) {
                return false;
            }
        }
        return true;
    }

    private RegexUtil() {
        throw new IllegalStateException("Utility class");
    }
}
