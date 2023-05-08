package xyz.zhouxy.plusone.commons.util;

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

@Beta
public class DbRecord extends AbstractMapWrapper<String, Object, DbRecord> {

    public DbRecord() {
        super(new HashMap<>(), k -> Assert.isNotBlank(k, "Key can not be null."), null);
    }

    public DbRecord(Map<String, Object> map) {
        super(map, k -> Assert.isNotBlank(k, "Key can not be null."), null);
    }

    public Optional<String> getValueAsString(String key) {
        return this.<String>getAndConvert(key);
    }

    public <T> List<T> getValueAsList(String key) {
        return this.<Collection<T>>getAndConvert(key)
                .<List<T>>map(l -> (l instanceof List) ? (List<T>) l : new ArrayList<>(l))
                .orElse(Collections.<T>emptyList());
    }

    public <T> Set<T> getValueAsSet(String key) {
        return this.<Collection<T>>getAndConvert(key)
                .<Set<T>>map(l -> (l instanceof Set) ? (Set<T>) l : new HashSet<>(l))
                .orElse(Collections.<T>emptySet());
    }

    public OptionalInt getValueAsInt(String key) {
        return OptionalUtil.toOptionalInt(this.<Integer>getAndConvert(key));
    }

    public OptionalLong getValueAsLong(String key) {
        return OptionalUtil.toOptionalLong(this.<Long>getAndConvert(key));
    }

    public OptionalDouble getValueAsDouble(String key) {
        return OptionalUtil.toOptionalDouble(this.<Double>getAndConvert(key));
    }

    @Override
    protected DbRecord getSelf() {
        return this;
    }
}
