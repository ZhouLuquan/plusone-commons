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

import xyz.zhouxy.plusone.commons.model.dto.PagingAndSortingQueryParams.SortableProperty;

/**
 * 分页参数
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @see PagingAndSortingQueryParams
 */
public class PagingParams {

    /** 每页大小 */
    private final int size;
    /** 当前页码 */
    private final long pageNum;
    /** 偏移量 */
    private final long offset;
    /** 排序 */
    private final List<SortableProperty> orderBy;

    PagingParams(int size, long pageNum, List<SortableProperty> orderBy) {
        this.size = size;
        this.pageNum = pageNum;
        this.offset = (pageNum - 1) * size;
        this.orderBy = orderBy;
    }

    // Getters

    /**
     * 排序规则
     *
     * @return 排序规则
     */
    public final List<SortableProperty> getOrderBy() {
        return Collections.unmodifiableList(this.orderBy);
    }

    /**
     * 每页大小
     *
     * @return 每页大小
     */
    public final int getSize() {
        return this.size;
    }

    /**
     * 当前页码
     *
     * @return 当前页码
     */
    public final long getPageNum() {
        return this.pageNum;
    }

    /**
     * 偏移量
     *
     * @return 偏移量
     */
    public final long getOffset() {
        return this.offset;
    }

    // Getters end

    @Override
    public String toString() {
        return "PageInfo [size=" + size + ", pageNum=" + pageNum + ", orderBy=" + orderBy + ", offset="
                + getOffset() + "]";
    }
}
