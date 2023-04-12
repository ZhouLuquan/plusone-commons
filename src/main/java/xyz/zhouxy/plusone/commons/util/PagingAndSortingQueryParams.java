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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    protected String orderBy;
    protected Integer size;
    protected Long pageNum;

    private final List<String> sortableColNames;

    public PagingAndSortingQueryParams() {
        sortableColNames = Collections.emptyList();
    }

    public PagingAndSortingQueryParams(String... sortableColNames) {
        this.sortableColNames = Arrays.asList(sortableColNames);
    }

    public String getOrderBy() {
        return orderBy != null && sortableColNames.contains(orderBy) ? orderBy : null;
    }

    public int getSize() {
        return this.size != null ? this.size : 15;
    }

    public long getPageNum() {
        return this.pageNum != null ? this.pageNum : 1;
    }

    public long getOffset() {
        return (getPageNum() - 1) * getSize();
    }

    // Setters

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    // Setters end
}
