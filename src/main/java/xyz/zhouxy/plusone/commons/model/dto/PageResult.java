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

package xyz.zhouxy.plusone.commons.model.dto;

import java.util.List;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * 返回分页查询的结果
 *
 * @param <T> 内容列表的元素类型
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @see PagingAndSortingQueryParams
 */
public class PageResult<T> {

    private final long total;

    private final List<T> content;

    private PageResult(List<T> content, long total) {
        Preconditions.checkNotNull(content, "Content must not be null.");
        this.content = content;
        this.total = total;
    }

    @StaticFactoryMethod(PageResult.class)
    public static <T> PageResult<T> of(List<T> content, long total) {
        return new PageResult<>(content, total);
    }

    public long getTotal() {
        return total;
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "PageDTO [total=" + total + ", content=" + content + "]";
    }
}
