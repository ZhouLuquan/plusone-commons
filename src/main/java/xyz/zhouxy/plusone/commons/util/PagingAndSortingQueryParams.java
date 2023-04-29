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

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import xyz.zhouxy.plusone.commons.annotation.Overridable;

/**
 * 分页排序查询参数
 *
 * <p>
 * 根据传入的 {@code size} 和 {@code pageNum}，
 * 提供 {@code getOffset} 方法计算 SQL 语句中 {@code offset} 的值。
 * </p>
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see PageDTO
 */
public class PagingAndSortingQueryParams {

    protected @Nullable String orderBy;
    protected int size;
    protected long pageNum;

    private final Set<String> sortableColNames;

    public PagingAndSortingQueryParams() {
        sortableColNames = ImmutableSet.of();
    }

    public PagingAndSortingQueryParams(String... sortableColNames) {
        for (String colName : sortableColNames) {
            Assert.hasText(colName, "Column name must has text.");
        }
        this.sortableColNames = ImmutableSet.copyOf(sortableColNames);
    }

    // Getters

    @Nullable
    public final String getOrderBy() {
        return this.orderBy;
    }

    public final int getSize() {
        return this.size;
    }

    public final long getPageNum() {
        return this.pageNum;
    }

    public final long getOffset() {
        return (this.pageNum - 1) * this.size;
    }

    // Getters end

    // Setters

    public final void setOrderBy(@Nullable String orderBy) {
        if (orderBy != null) {
            Assert.isTrue(this.sortableColNames.contains(orderBy),
                    "The column name must be in the set of sortable columns.");
        }
        this.orderBy = orderBy;
    }

    public final void setSize(@Nullable Integer size) {
        this.size = size != null ? size : getDefaultSize();
    }

    public final void setPageNum(@Nullable Long pageNum) {
        this.pageNum = pageNum != null ? pageNum : getDefaultPageNum();
    }

    // Setters end

    @Overridable
    protected int getDefaultSize() {
        return 15;
    }

    @Overridable
    protected int getDefaultPageNum() {
        return 1;
    }
}
