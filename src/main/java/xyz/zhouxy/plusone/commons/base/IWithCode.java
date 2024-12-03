/*
 * Copyright 2022-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.base;

import java.util.Objects;

import javax.annotation.Nonnull;

/**
 * 规定实现类带有 {@code getCode} 方法。
 * 用于像自定义异常等需要带有 {@code code} 字段的类，
 * 方便其它地方的程序判断该类的是否实现了此接口，以此获取其实例的 {@code code} 字段的值。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public interface IWithCode<T> {
    @Nonnull
    T getCode();

    default boolean equalsCode(T code) {
        return Objects.equals(getCode(), code);
    }
    default boolean equalsCode(IWithCode<?> obj) { // TODO 单元测试
        return obj != null && obj.getCode().equals(getCode());
    }

    default boolean equalsCode(IWithIntCode obj) { // TODO 单元测试
        return obj != null && getCode().equals(obj.getCode());
    }

    default boolean equalsCode(IWithLongCode obj) { // TODO 单元测试
        return obj != null && getCode().equals(obj.getCode());
    }
}
