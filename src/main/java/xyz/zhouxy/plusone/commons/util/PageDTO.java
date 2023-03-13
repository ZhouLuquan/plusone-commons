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

import java.util.List;

import lombok.Setter;
import lombok.ToString;

/**
 * 返回分页查询的结果
 *
 * @param <T> 内容列表的元素类型
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 * @see PagingAndSortingQueryParams
 */
@ToString
@Setter
public class PageDTO<T> {

    private Long total;
    private List<T> content;

    private PageDTO(List<T> content, Long total) {
        this.content = content;
        this.total = total;
    }

    public static <T> PageDTO<T> of(List<T> content, Long total) {
        return new PageDTO<>(content, total);
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getContent() {
        return content;
    }

}
