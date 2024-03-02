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

import xyz.zhouxy.plusone.commons.annotation.UnsupportedOperation;
import xyz.zhouxy.plusone.commons.base.IWithCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * 统一结果，对返回给前端的数据进行封装。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public abstract class UnifiedResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -6198220274571286031L;

    private static final String STATUS_KEY = "status";
    private static final String MESSAGE_KEY = "message";
    private static final String DATA_KEY = "data";

    public static UnifiedResponse success() {
        return new SuccessResult();
    }

    public static UnifiedResponse success(@Nullable String message) {
        return new SuccessResult(message);
    }

    public static UnifiedResponse success(@Nullable String message, @Nullable Object data) {
        return new SuccessResult(message, data);
    }

    public static UnifiedResponse error(@Nullable String message) {
        return new ErrorResult(message);
    }

    public static UnifiedResponse error(@Nullable String message, @Nullable Object data) {
        return new ErrorResult(message, data);
    }

    public static UnifiedResponse error(Object status, @Nullable String message) {
        return new ErrorResult(status, message);
    }

    public static UnifiedResponse error(Object status, @Nullable String message, @Nullable Object data) {
        return new ErrorResult(status, message, data);
    }

    public static UnifiedResponse error(Object status, Throwable e) {
        return new ErrorResult(status, e);
    }

    public static <E extends Throwable & IWithCode<?>> UnifiedResponse error(E e) {
        return new ErrorResult(e);
    }

    public static UnifiedResponse of(Object status, @Nullable String message) {
        return new CustomResult(status, message);
    }

    public static UnifiedResponse of(Object status, @Nullable String message, @Nullable Object data) {
        return new CustomResult(status, message, data);
    }

    public static UnifiedResponse of(final boolean isSuccess,
            final Supplier<UnifiedResponse> success, final Supplier<UnifiedResponse> error) {
        Preconditions.checkNotNull(success, "Success supplier must not be null.");
        Preconditions.checkNotNull(error, "Error supplier must not be null.");
        return isSuccess ? success.get() : error.get();
    }

    public static UnifiedResponse of(final BooleanSupplier isSuccess,
            final Supplier<UnifiedResponse> success, final Supplier<UnifiedResponse> error) {
        Preconditions.checkNotNull(isSuccess, "Conditions for success must not be null.");
        Preconditions.checkNotNull(success, "Success supplier must not be null.");
        Preconditions.checkNotNull(error, "Error supplier must not be null.");
        return isSuccess.getAsBoolean() ? success.get() : error.get();
    }

    protected UnifiedResponse(Object status, @Nullable String message) {
        setStatus(status);
        setMessage(message);
    }

    protected UnifiedResponse(Object status, @Nullable String message, @Nullable Object data) {
        setStatus(status);
        setMessage(message);
        setData(data);
    }

    private void setStatus(Object status) {
        Objects.requireNonNull(status);
        if (status instanceof String) {
            super.put(STATUS_KEY, status);
        } else {
            super.put(STATUS_KEY, status.toString());
        }
    }

    private void setData(@Nullable Object data) {
        if (data != null) {
            super.put(DATA_KEY, data);
        }
    }

    private void setMessage(@Nullable String message) {
        super.put(MESSAGE_KEY, message);
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object put(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public void putAll(Map<? extends String, ?> m) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object putIfAbsent(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public boolean replace(String key, Object oldValue, Object newValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object replace(String key, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Unsupported operation.
     */
    @UnsupportedOperation
    @Deprecated
    @Override
    public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
        throw new UnsupportedOperationException();
    }
}
