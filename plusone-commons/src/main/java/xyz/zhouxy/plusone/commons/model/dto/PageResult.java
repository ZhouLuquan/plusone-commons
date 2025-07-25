/*
 * Copyright 2024-2025 the original author or authors.
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

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.collection.CollectionTools;

/**
 * 返回分页查询的结果
 *
 * @param <T> 内容列表的元素类型
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @see PagingAndSortingQueryParams
 */
public class PageResult<T> {

    private final long total;

    private final List<T> content;

    private PageResult(@Nullable final List<T> content, final long total) {
        this.content = CollectionTools.nullToEmptyList(content);
        this.total = total;
    }

    /**
     * 创建一个分页查询的结果
     *
     * @param <T>     内容类型
     * @param content 一页数据
     * @param total   总数据量
     * @return 分页查询的结果
     */
    @StaticFactoryMethod(PageResult.class)
    public static <T> PageResult<T> of(@Nullable final List<T> content, final long total) {
        return new PageResult<>(content, total);
    }

    /**
     * 创建一个空的分页查询的结果
     *
     * @param <T> 内容类型
     * @return 空结果
     */
    @StaticFactoryMethod(PageResult.class)
    public static <T> PageResult<T> empty() {
        return new PageResult<>(Collections.emptyList(), 0L);
    }

    /**
     * 总数据量
     *
     * @return 总数据量
     */
    public long getTotal() {
        return total;
    }

    /**
     * 一页数据
     *
     * @return 一页数据
     */
    public List<T> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "PageResult [total=" + total + ", content=" + content + "]";
    }
}
