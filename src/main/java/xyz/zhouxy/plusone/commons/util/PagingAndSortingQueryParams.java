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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import xyz.zhouxy.plusone.commons.annotation.Virtual;

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

    private static final int DEFAULT_PAGE_SIZE = 15;

    private int size;
    private long pageNum;
    private final List<String> orderBy = new LinkedList<>();

    private final Set<String> sortableProperties;

    public PagingAndSortingQueryParams() {
        this.sortableProperties = Collections.emptySet();
    }

    public PagingAndSortingQueryParams(String... sortableProperties) {
        for (String p : sortableProperties) {
            Preconditions.checkArgument(StringUtils.isNotBlank(p), "Property name must not be blank.");
        }
        this.sortableProperties = ImmutableSet.<String>builder().add(sortableProperties).build();
    }

    // Getters

    public final List<String> getOrderBy() {
        return Collections.unmodifiableList(this.orderBy);
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

    public final void setOrderBy(@Nullable List<String> properties) {
        this.orderBy.clear();
        if (properties != null && !properties.isEmpty()) {
            for (String colName : properties) {
                Preconditions.checkArgument(this.sortableProperties.contains(colName),
                        "The property name must be in the set of sortable properties.");
            }
            this.orderBy.addAll(properties);
        }
    }

    public final void setSize(@Nullable Integer size) {
        this.size = size != null ? size : getDefaultSize();
    }

    public final void setPageNum(@Nullable Long pageNum) {
        this.pageNum = pageNum != null ? pageNum : 1L;
    }

    // Setters end

    @Virtual
    protected int getDefaultSize() {
        return DEFAULT_PAGE_SIZE;
    }

    @Override
    public String toString() {
        return "PagingAndSortingQueryParams [size=" + size + ", pageNum=" + pageNum + ", orderBy=" + orderBy
                + ", sortableProperties=" + sortableProperties + "]";
    }
}
