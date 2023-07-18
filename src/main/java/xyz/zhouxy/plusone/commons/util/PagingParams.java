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

import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * PageInfo
 * 
 * <p>
 * 推荐的实现是，请求处理方法用两个 RequestParam 分别接收请求中的 pageNumber 和 size，
 * 应用服务使用 {@link PagingParams#of(Long pageNumber, Integer size)} 构建 PagingParams 对象,
 * 用于查询数据库。
 * </p>
 * 
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public class PagingParams {
    private final long pageNumber;
    private final int size;
    private final long offset;

    private PagingParams(long pageNumber, int size) {
        this.pageNumber = pageNumber;
        this.size = size;
        this.offset = (pageNumber - 1) * size;
    }

    @StaticFactoryMethod(PagingParams.class)
    public static PagingParams of(@Nullable Long pageNumber, @Nullable Integer size) {
        pageNumber = pageNumber == null ? 1 : pageNumber;
        size = size == null ? 15 : size;
        return new PagingParams(pageNumber, size);
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }

    public long getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "PageInfo [pageNumber=" + pageNumber + ", size=" + size + "]";
    }
}
