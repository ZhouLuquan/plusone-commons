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
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import xyz.zhouxy.plusone.commons.annotation.Virtual;
import xyz.zhouxy.plusone.commons.collection.CollectionTools;
import xyz.zhouxy.plusone.commons.util.AssertTools;
import xyz.zhouxy.plusone.commons.util.RegexTools;
import xyz.zhouxy.plusone.commons.util.StringTools;

/**
 * 分页排序查询参数
 *
 * <p>
 * 根据传入的 {@code size} 和 {@code pageNum}，
 * 提供 {@code getOffset} 方法计算 SQL 语句中 {@code offset} 的值。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @see PageResult
 */
public class PagingAndSortingQueryParams {

    private static final int DEFAULT_PAGE_SIZE = 15;

    private Integer size;
    private Long pageNum;
    private List<String> orderBy;

    private static final Pattern SORT_STR_PATTERN = Pattern.compile("^[a-zA-Z]\\w+-(desc|asc|DESC|ASC)$");

    private final Map<String, String> sortableProperties;

    public PagingAndSortingQueryParams(@Nonnull Map<String, String> sortableProperties) {
        AssertTools.checkArgument(CollectionTools.isNotEmpty(sortableProperties),
                "Sortable properties can not be empty.");
        sortableProperties.forEach((k, v) ->
                AssertTools.checkArgument(StringTools.isNotBlank(k) && StringTools.isNotBlank(v),
                "Property name must not be blank."));
        this.sortableProperties = ImmutableMap.copyOf(sortableProperties);
    }

    // Setters

    public final void setOrderBy(@Nullable List<String> orderBy) {
        this.orderBy = orderBy;
    }

    public final void setSize(@Nullable Integer size) {
        this.size = size;
    }

    public final void setPageNum(@Nullable Long pageNum) {
        this.pageNum = pageNum;
    }

    // Setters end

    public final PagingParams buildPagingParams() {
        final int sizeValue = this.size != null ? this.size : defaultSizeInternal();
        final long pageNumValue = this.pageNum != null ? this.pageNum : 1L;
        final List<SortableProperty> propertiesToSort = this.orderBy.stream().map(this::generateSortableProperty)
                .collect(Collectors.toList());
        return new PagingParams(sizeValue, pageNumValue, propertiesToSort);
    }

    @Virtual
    protected int defaultSizeInternal() {
        return DEFAULT_PAGE_SIZE;
    }

    @Override
    public String toString() {
        return "PagingAndSortingQueryParams ["
                + "size=" + size
                + ", pageNum=" + pageNum
                + ", orderBy=" + orderBy
                + ", sortableProperties=" + sortableProperties
                + "]";
    }

    private SortableProperty generateSortableProperty(String orderByStr) {
        AssertTools.checkArgument(RegexTools.matches(orderByStr, SORT_STR_PATTERN));
        String[] propertyNameAndOrderType = orderByStr.split("-");
        AssertTools.checkArgument(propertyNameAndOrderType.length == 2);

        String propertyName = propertyNameAndOrderType[0];
        AssertTools.checkArgument(sortableProperties.containsKey(propertyName),
                "The property name must be in the set of sortable properties.");
        String columnName = sortableProperties.get(propertyName);
        String orderType = propertyNameAndOrderType[1];
        return new SortableProperty(propertyName, columnName, orderType);
    }

    public static final class SortableProperty {
        private final String propertyName;
        private final String columnName;
        private final String orderType;

        private final String sqlSnippet;

        SortableProperty(String propertyName, String columnName, String orderType) {
            this.propertyName = propertyName;
            this.columnName = columnName;
            AssertTools.checkArgument("ASC".equalsIgnoreCase(orderType) || "DESC".equalsIgnoreCase(orderType));
            this.orderType = orderType.toUpperCase();

            this.sqlSnippet = this.propertyName + " " + this.orderType;
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

        public String getSqlSnippet() {
            return sqlSnippet;
        }

        @Override
        public String toString() {
            return "SortableProperty ["
                    + "propertyName=" + propertyName
                    + ", columnName=" + columnName
                    + ", orderType=" + orderType
                    + "]";
        }
    }

}
