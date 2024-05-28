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
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import xyz.zhouxy.plusone.commons.annotation.Virtual;

/**
 * 分页排序查询参数
 *
 * <p>
 * 根据传入的 {@code size} 和 {@code pageNum}，
 * 提供 {@code getOffset} 方法计算 SQL 语句中 {@code offset} 的值。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @see PageDTO
 */
public class PagingAndSortingQueryParams {

    // TODO 【优化】 进一步优化 API

    private static final int DEFAULT_PAGE_SIZE = 15;

    private int size;
    private long pageNum;
    private final List<SortableProperty> orderBy = new LinkedList<>();

    private static final Pattern orderByStrPattern = Pattern.compile("^[a-zA-Z]\\w+-(desc|asc|DESC|ASC)$");

    private final Map<String, String> sortableProperties;

    public PagingAndSortingQueryParams(Map<String, String> sortableProperties) {
        Preconditions.checkArgument(sortableProperties != null && !sortableProperties.isEmpty(),
                "Sortable properties can not be empty.");
        sortableProperties.forEach((k, v) -> 
                Preconditions.checkArgument(StringUtils.isNotBlank(k) && StringUtils.isNotBlank(v),
                "Property name must not be blank."));
        this.sortableProperties = ImmutableMap.copyOf(sortableProperties);
    }

    // Getters

    public final List<SortableProperty> getOrderBy() {
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

    public final void setOrderBy(@Nullable List<String> orderByStrList) {
        this.orderBy.clear();
        if (orderByStrList != null) {
            orderByStrList.stream()
                    .map(this::generateSortableProperty)
                    .forEach(this.orderBy::add);
        }
    }

    public final void setSize(@Nullable Integer size) {
        this.size = size != null ? size : defaultSizeInternal();
    }

    public final void setPageNum(@Nullable Long pageNum) {
        this.pageNum = pageNum != null ? pageNum : 1L;
    }

    // Setters end

    @Virtual
    protected int defaultSizeInternal() {
        return DEFAULT_PAGE_SIZE;
    }

    @Override
    public String toString() {
        return "PagingAndSortingQueryParams ["
                + "size=" + size
                + ", pageNum=" + pageNum
                + ", offset=" + getOffset()
                + ", orderBy=" + orderBy
                + ", sortableProperties=" + sortableProperties + "]";
    }

    private SortableProperty generateSortableProperty(String orderByStr) {
        Preconditions.checkArgument(orderByStrPattern.matcher(orderByStr).matches());
        String[] propertyNameAndOrderType = orderByStr.split("-");
        Preconditions.checkArgument(propertyNameAndOrderType.length == 2);

        String propertyName = propertyNameAndOrderType[0];
        Preconditions.checkArgument(sortableProperties.containsKey(propertyName),
                "The property name must be in the set of sortable properties.");
        String columnName = sortableProperties.get(propertyName);
        String orderType = propertyNameAndOrderType[1];
        return new SortableProperty(propertyName, columnName, orderType);
    }

    public static final class SortableProperty {
        private final String propertyName;
        private final String columnName;
        private final String orderType;

        private SortableProperty(String propertyName, String columnName, String orderType) {
            this.propertyName = propertyName;
            this.columnName = columnName;
            Preconditions.checkArgument("ASC".equalsIgnoreCase(orderType) || "DESC".equalsIgnoreCase(orderType));
            this.orderType = orderType.toUpperCase();
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getOrderType() {
            return orderType;
        }
    }
}
