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

package xyz.zhouxy.plusone.commons.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.util.AbstractMapWrapper;
import xyz.zhouxy.plusone.commons.util.Assert;
import xyz.zhouxy.plusone.commons.util.OptionalUtil;

@Beta
public class DbRecord extends AbstractMapWrapper<String, Object, DbRecord> {

    public DbRecord() {
        super(new HashMap<>(), k -> Assert.isNotBlank(k, "Key must has text."), null);
    }

    public DbRecord(Map<String, Object> map) {
        super(map, k -> Assert.isNotBlank(k, "Key must has text."), null);
    }

    public Optional<String> getValueAsString(String key) {
        return this.getAndConvert(key);
    }

    public <T> List<T> getValueAsList(String key) {
        return this.<Collection<T>>getAndConvert(key)
                .map(l -> (l instanceof List) ? (List<T>) l : new ArrayList<>(l))
                .orElse(Collections.emptyList());
    }

    public <T> Set<T> getValueAsSet(String key) {
        return this.<Collection<T>>getAndConvert(key)
                .map(l -> (l instanceof Set) ? (Set<T>) l : new HashSet<>(l))
                .orElse(Collections.<T>emptySet());
    }

    public OptionalInt getValueAsInt(String key) {
        return OptionalUtil.toOptionalInt(this.getAndConvert(key));
    }

    public OptionalLong getValueAsLong(String key) {
        return OptionalUtil.toOptionalLong(this.getAndConvert(key));
    }

    public OptionalDouble getValueAsDouble(String key) {
        return OptionalUtil.toOptionalDouble(this.getAndConvert(key));
    }

    @Override
    protected DbRecord getSelf() {
        return this;
    }

    @Override
    public String toString() {
        return "xyz.zhouxy.plusone.commons.jdbc.DbRecord@" + super.toString();
    }
}
