/*
 * Copyright 2022-present ZhouXY
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

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import xyz.zhouxy.plusone.commons.collection.CollectionTools;
import xyz.zhouxy.plusone.commons.util.RegexTools;
import xyz.zhouxy.plusone.commons.util.StringTools;

/**
 * 分页排序查询参数
 *
 * <p>
 * 包含三个主要的属性：
 * <ul>
 * <li>size - 每页显示的记录数</li>
 * <li>pageNum - 当前页码</li>
 * <li>orderBy - 排序条件</li>
 * </ul>
 *
 * <p>
 * 分页必须伴随着排序，不然可能出现同一个对象重复出现在不同页，有的对象不被查询到的情况。
 *
 * <p>
 * 其中 {@code orderBy} 是一个 {@code List&lt;String&gt;}，可以指定多个排序条件。
 * 每个排序条件是一个字符串， 格式为“属性名-ASC”或“属性名-DESC”，分别表示升序和降序。
 * 例如，当 {@code orderBy} 的值为 {@code ["name-ASC","age-DESC"]}，
 * 意味着要按 {@code name} 进行升序排列，{@code name} 相同的情况下则按 {@code age} 进行降序排列。
 *
 * <p>
 * 用户可继承 {@link PagingAndSortingQueryParams} 构建自己的分页查询入参，
 * 子类需在构造器中调用 {@link PagingAndSortingQueryParams} 的构造器，
 * 传入一个 {@link PagingParamsBuilder} 用于构建分页参数。
 * 同一场景下，复用一个 {@link PagingParamsBuilder} 实例即可。
 *
 * <p>
 * 构建 {@link PagingParamsBuilder} 时，需传入一个 {@code Map} 作为可排序字段的白名单，
 * {@code key} 是供前端指定用于排序的属性名，{@code value} 是对应数据库中的字段名。
 * 只有在此白名单中的属性名才允许用于排序。
 *
 * <pre>
 * class AccountQueryParams extends PagingAndSortingQueryParams {
 *     private static final Map&lt;String, String&gt; PROPERTY_COLUMN_MAP = ImmutableMap.&lt;String, String&gt;builder()
 *             .put("id", "id")
 *             .put("username", "username")
 *             .build();
 *     private static final PagingParamsBuilder PAGING_PARAMS_BUILDER = PagingAndSortingQueryParams
 *             .pagingParamsBuilder(20, 100, PROPERTY_COLUMN_MAP);
 *
 *     public AccountQueryParams() {
 *         // 所有的 AccountQueryParams 复用同一个 PagingParamsBuilder 实例
 *         super(PAGING_PARAMS_BUILDER);
 *     }
 *
 *     private @Getter @Setter Long id;
 *     private @Getter @Setter String username;
 *     private @Getter @Setter String email;
 *     private @Getter @Setter Integer status;
 * }
 *
 * public PageResult&lt;AccountVO&gt; queryPage(AccountQueryParams params) {
 *     // 获取分页参数
 *     PagingParams pagingParams = params.buildPagingParams();
 *     // 从 params 获取字段查询条件，从 pagingParams 获取分页条件，查询一页数据
 *     List&lt;AccountVO&gt; list = accountQueries.queryAccountList(params, pagingParams);
 *     // 查询总记录数
 *     long count = accountQueries.countAccount(params);
 *     // 返回分页结果
 *     return PageResult.of(list, count);
 * }
 * </pre>
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @see PagingParams
 * @see PageResult
 */
public class PagingAndSortingQueryParams {

    private final PagingParamsBuilder pagingParamsBuilder;

    private Integer size;
    private Long pageNum;
    private List<String> orderBy;

    /**
     * 创建一个 {@code PagingAndSortingQueryParams} 实例
     *
     * @param pagingParamsBuilder
     *        分页参数构造器。
     *        通过 {@link #pagingParamsBuilder(int, int, Map)} 创建，同一场景下只需要共享同一个实例。
     */
    protected PagingAndSortingQueryParams(PagingParamsBuilder pagingParamsBuilder) {
        this.pagingParamsBuilder = pagingParamsBuilder;
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

    @Override
    public String toString() {
        return "PagingAndSortingQueryParams ["
                + "size=" + size
                + ", pageNum=" + pageNum
                + ", orderBy=" + orderBy
                + "]";
    }

    /**
     * 创建一个分页参数构造器
     *
     * @param defaultSize 默认每页大小
     * @param maxSize 最大每页大小
     * @param sortableProperties
     *        可排序属性。
     *        key 是供前端指定用于排序的属性名，value 是对应数据库中的字段名。
     *        只有在此白名单中的属性名才允许用于排序。
     * @return 分页参数构造器
     */
    protected static PagingParamsBuilder pagingParamsBuilder(
            int defaultSize, int maxSize, Map<String, String> sortableProperties) {
        return new PagingParamsBuilder(defaultSize, maxSize, sortableProperties);
    }

    /**
     * 根据当前查询参数，构建分页参数
     *
     * @return 分页参数
     */
    public PagingParams buildPagingParams() {
        return this.pagingParamsBuilder.buildPagingParams(this);
    }

    /**
     * 可排序属性
     */
    public static final class SortableProperty {
        private final String propertyName;
        private final String columnName;
        private final String orderType;

        private final String sqlSnippet;

        private SortableProperty(String propertyName, String columnName, String orderType) {
            this.propertyName = propertyName;
            this.columnName = columnName;
            checkArgument("ASC".equalsIgnoreCase(orderType) || "DESC".equalsIgnoreCase(orderType));
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

    protected static final class PagingParamsBuilder {
        private static final Pattern SORT_STR_PATTERN = Pattern.compile("^[a-zA-Z][\\w-]{0,63}-(desc|asc|DESC|ASC)$");

        private final Map<String, String> sortableProperties;
        protected final int defaultSize;
        protected final int maxSize;

        private PagingParamsBuilder(int defaultSize, int maxSize, Map<String, String> sortableProperties) {
            this.defaultSize = defaultSize;
            this.maxSize = maxSize;
            checkArgument(CollectionTools.isNotEmpty(sortableProperties),
                    "Sortable properties can not be empty.");
            sortableProperties.forEach((k, v) ->
                    checkArgument(StringTools.isNotBlank(k) && StringTools.isNotBlank(v),
                    "Property name must not be blank."));
            this.sortableProperties = ImmutableMap.copyOf(sortableProperties);
        }

        public PagingParams buildPagingParams(PagingAndSortingQueryParams params) {
            final int sizeValue = params.size != null ? params.size : this.defaultSize;
            final long pageNumValue = params.pageNum != null ? params.pageNum : 1L;
            checkArgument(CollectionTools.isNotEmpty(params.orderBy),
                    "The 'orderBy' cannot be empty");
            final List<SortableProperty> propertiesToSort = params.orderBy.stream()
                    .map(this::generateSortableProperty)
                    .collect(Collectors.toList());
            return new PagingParams(sizeValue, pageNumValue, propertiesToSort);
        }

        private SortableProperty generateSortableProperty(String orderByStr) {
            checkArgument(StringTools.isNotBlank(orderByStr));
            checkArgument(RegexTools.matches(orderByStr, SORT_STR_PATTERN));
            String[] propertyNameAndOrderType = orderByStr.split("-");
            checkArgument(propertyNameAndOrderType.length == 2);

            String propertyName = propertyNameAndOrderType[0];
            checkArgument(sortableProperties.containsKey(propertyName),
                    "The property name must be in the set of sortable properties.");
            String columnName = sortableProperties.get(propertyName);
            String orderType = propertyNameAndOrderType[1];
            return new SortableProperty(propertyName, columnName, orderType);
        }
    }
}
