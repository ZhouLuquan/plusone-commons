/*
 * Copyright 2022-2025 the original author or authors.
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
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @see PagingParams
 * @see PageResult
 */
public class PagingAndSortingQueryParams {

    private static final int DEFAULT_PAGE_SIZE = 15;

    private Integer size;
    private Long pageNum;
    private List<String> orderBy;

    private static final Pattern SORT_STR_PATTERN = Pattern.compile("^[a-zA-Z][\\w-]{0,63}-(desc|asc|DESC|ASC)$");

    private final Map<String, String> sortableProperties;

    /**
     * 构造分页排序查询参数
     *
     * @param sortableProperties 可排序的属性。不可为空。
     */
    public PagingAndSortingQueryParams(Map<String, String> sortableProperties) {
        AssertTools.checkArgument(CollectionTools.isNotEmpty(sortableProperties),
                "Sortable properties can not be empty.");
        sortableProperties.forEach((k, v) ->
                AssertTools.checkArgument(StringTools.isNotBlank(k) && StringTools.isNotBlank(v),
                "Property name must not be blank."));
        this.sortableProperties = ImmutableMap.copyOf(sortableProperties);
    }

    // Setters

    /**
     * 设置排序规则
     *
     * @param orderBy 排序规则，不能为空
     */
    public final void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 设置每页大小
     *
     * @param size 每页大小
     */
    public final void setSize(@Nullable Integer size) {
        this.size = size;
    }

    /**
     * 设置页码
     *
     * @param pageNum 页码
     */
    public final void setPageNum(@Nullable Long pageNum) {
        this.pageNum = pageNum;
    }

    // Setters end

    /**
     * 构建分页参数
     *
     * @return {@code PagingParams} 对象
     */
    public final PagingParams buildPagingParams() {
        final int sizeValue = this.size != null ? this.size : defaultSizeInternal();
        final long pageNumValue = this.pageNum != null ? this.pageNum : 1L;
        AssertTools.checkArgument(CollectionTools.isNotEmpty(this.orderBy),
                "The 'orderBy' cannot be empty");
        final List<SortableProperty> propertiesToSort = this.orderBy.stream()
                .map(this::generateSortableProperty)
                .collect(Collectors.toList());
        return new PagingParams(sizeValue, pageNumValue, propertiesToSort);
    }

    /**
     * 默认每页大小
     *
     * <p>NOTE: 可覆写此方法</p>
     *
     * @return 默认每页大小
     */
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
        AssertTools.checkArgument(StringTools.isNotBlank(orderByStr));
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

    /**
     * 可排序属性
     */
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

        /**
         * 属性名
         *
         * @return 属性名
         */
        public String getPropertyName() {
            return propertyName;
        }

        /**
         * 对应数据库中列名称
         *
         * @return 列名称
         */
        public String getColumnName() {
            return columnName;
        }

        /**
         * 排序方式
         *
         * @return 排序方式
         */
        public String getOrderType() {
            return orderType;
        }

        /**
         * SQL 片段
         *
         * @return SQL 片段
         */
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
